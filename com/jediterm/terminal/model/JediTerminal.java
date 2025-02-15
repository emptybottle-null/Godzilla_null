/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.CursorShape;
import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.StyledTextConsumer;
import com.jediterm.terminal.SubstringFinder;
import com.jediterm.terminal.Terminal;
import com.jediterm.terminal.TerminalDisplay;
import com.jediterm.terminal.TerminalKeyEncoder;
import com.jediterm.terminal.TerminalMode;
import com.jediterm.terminal.TerminalOutputStream;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.emulator.charset.CharacterSet;
import com.jediterm.terminal.emulator.charset.GraphicSet;
import com.jediterm.terminal.emulator.charset.GraphicSetState;
import com.jediterm.terminal.emulator.mouse.MouseFormat;
import com.jediterm.terminal.emulator.mouse.MouseMode;
import com.jediterm.terminal.emulator.mouse.TerminalMouseListener;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.StoredCursor;
import com.jediterm.terminal.model.StyleState;
import com.jediterm.terminal.model.SubCharBuffer;
import com.jediterm.terminal.model.Tabulator;
import com.jediterm.terminal.model.TerminalTextBuffer;
import com.jediterm.terminal.model.hyperlinks.LinkInfo;
import com.jediterm.terminal.ui.TerminalCoordinates;
import com.jediterm.terminal.util.CharUtils;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JediTerminal
implements Terminal,
TerminalMouseListener,
TerminalCoordinates {
    private static final Logger LOG = Logger.getLogger(JediTerminal.class.getName());
    private static final int MIN_WIDTH = 5;
    private static final int MIN_HEIGHT = 2;
    private int myScrollRegionTop;
    private int myScrollRegionBottom;
    private volatile int myCursorX = 0;
    private volatile int myCursorY = 1;
    private int myTerminalWidth;
    private int myTerminalHeight;
    private final TerminalDisplay myDisplay;
    private final TerminalTextBuffer myTerminalTextBuffer;
    private final StyleState myStyleState;
    private StoredCursor myStoredCursor = null;
    private final EnumSet<TerminalMode> myModes = EnumSet.noneOf(TerminalMode.class);
    private final TerminalKeyEncoder myTerminalKeyEncoder = new TerminalKeyEncoder();
    private final Tabulator myTabulator;
    private final GraphicSetState myGraphicSetState;
    private MouseFormat myMouseFormat = MouseFormat.MOUSE_FORMAT_XTERM;
    @Nullable
    private TerminalOutputStream myTerminalOutput = null;
    private MouseMode myMouseMode = MouseMode.MOUSE_REPORTING_NONE;
    private Point myLastMotionReport = null;
    private boolean myCursorYChanged;

    public JediTerminal(TerminalDisplay display, TerminalTextBuffer buf, StyleState initialStyleState) {
        this.myDisplay = display;
        this.myTerminalTextBuffer = buf;
        this.myStyleState = initialStyleState;
        this.myTerminalWidth = display.getColumnCount();
        this.myTerminalHeight = display.getRowCount();
        this.myScrollRegionTop = 1;
        this.myScrollRegionBottom = this.myTerminalHeight;
        this.myTabulator = new DefaultTabulator(this.myTerminalWidth);
        this.myGraphicSetState = new GraphicSetState();
        this.reset();
    }

    @Override
    public void setModeEnabled(TerminalMode mode, boolean enabled) {
        if (enabled) {
            this.myModes.add(mode);
        } else {
            this.myModes.remove((Object)mode);
        }
        mode.setEnabled(this, enabled);
    }

    @Override
    public void disconnected() {
        this.myDisplay.setCursorVisible(false);
    }

    private void wrapLines() {
        if (this.myCursorX >= this.myTerminalWidth) {
            this.myCursorX = 0;
            this.myTerminalTextBuffer.getLine(this.myCursorY - 1).deleteCharacters(this.myTerminalWidth);
            if (this.isAutoWrap()) {
                this.myTerminalTextBuffer.getLine(this.myCursorY - 1).setWrapped(true);
                ++this.myCursorY;
            }
        }
    }

    private void finishText() {
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
        this.scrollY();
    }

    @Override
    public void writeCharacters(String string) {
        this.writeDecodedCharacters(this.decodeUsingGraphicalState(string));
    }

    private void writeDecodedCharacters(char[] string) {
        this.myTerminalTextBuffer.lock();
        try {
            if (this.myCursorYChanged && string.length > 0) {
                this.myCursorYChanged = false;
                if (this.myCursorY > 1) {
                    this.myTerminalTextBuffer.getLine(this.myCursorY - 2).setWrapped(false);
                }
            }
            this.wrapLines();
            this.scrollY();
            if (string.length != 0) {
                CharBuffer characters = new CharBuffer(string, 0, string.length);
                this.myTerminalTextBuffer.writeString(this.myCursorX, this.myCursorY, characters);
                this.myCursorX += characters.length();
            }
            this.finishText();
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void writeDoubleByte(char[] bytesOfChar) throws UnsupportedEncodingException {
        this.writeCharacters(new String(bytesOfChar, 0, 2));
    }

    private char[] decodeUsingGraphicalState(String string) {
        StringBuilder result = new StringBuilder();
        for (char c : string.toCharArray()) {
            result.append(this.myGraphicSetState.map(c));
        }
        return result.toString().toCharArray();
    }

    @Override
    public void writeUnwrappedString(String string) {
        int amountInLine;
        int length = string.length();
        for (int off = 0; off < length; off += amountInLine) {
            amountInLine = Math.min(this.distanceToLineEnd(), length - off);
            this.writeCharacters(string.substring(off, off + amountInLine));
            this.wrapLines();
            this.scrollY();
        }
    }

    public void scrollY() {
        this.myTerminalTextBuffer.lock();
        try {
            if (this.myCursorY > this.myScrollRegionBottom) {
                int dy = this.myScrollRegionBottom - this.myCursorY;
                this.myCursorY = this.myScrollRegionBottom;
                this.scrollArea(this.myScrollRegionTop, this.scrollingRegionSize(), dy);
                this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
            }
            if (this.myCursorY < this.myScrollRegionTop) {
                this.myCursorY = this.myScrollRegionTop;
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    public void crnl() {
        this.carriageReturn();
        this.newLine();
    }

    @Override
    public void newLine() {
        this.myCursorYChanged = true;
        ++this.myCursorY;
        this.scrollY();
        if (this.isAutoNewLine()) {
            this.carriageReturn();
        }
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void mapCharsetToGL(int num) {
        this.myGraphicSetState.setGL(num);
    }

    @Override
    public void mapCharsetToGR(int num) {
        this.myGraphicSetState.setGR(num);
    }

    @Override
    public void designateCharacterSet(int tableNumber, char charset) {
        GraphicSet gs = this.myGraphicSetState.getGraphicSet(tableNumber);
        this.myGraphicSetState.designateGraphicSet(gs, charset);
    }

    @Override
    public void singleShiftSelect(int num) {
        this.myGraphicSetState.overrideGL(num);
    }

    @Override
    public void setAnsiConformanceLevel(int level) {
        if (level == 1 || level == 2) {
            this.myGraphicSetState.designateGraphicSet(0, CharacterSet.ASCII);
            this.myGraphicSetState.designateGraphicSet(1, CharacterSet.DEC_SUPPLEMENTAL);
            this.mapCharsetToGL(0);
            this.mapCharsetToGR(1);
        } else if (level == 3) {
            this.designateCharacterSet(0, 'B');
            this.mapCharsetToGL(0);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setWindowTitle(String name) {
        this.myDisplay.setWindowTitle(name);
    }

    @Override
    public void setCurrentPath(String path) {
        this.myDisplay.setCurrentPath(path);
    }

    @Override
    public void backspace() {
        --this.myCursorX;
        if (this.myCursorX < 0) {
            --this.myCursorY;
            this.myCursorX = this.myTerminalWidth - 1;
        }
        this.adjustXY(-1);
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void carriageReturn() {
        this.myCursorX = 0;
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void horizontalTab() {
        if (this.myCursorX >= this.myTerminalWidth) {
            return;
        }
        int length = this.myTerminalTextBuffer.getLine(this.myCursorY - 1).getText().length();
        int stop = this.myTabulator.nextTab(this.myCursorX);
        this.myCursorX = Math.max(this.myCursorX, length);
        if (this.myCursorX < stop) {
            char[] chars = new char[stop - this.myCursorX];
            Arrays.fill(chars, ' ');
            this.writeDecodedCharacters(chars);
        } else {
            this.myCursorX = stop;
        }
        this.adjustXY(1);
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void eraseInDisplay(int arg) {
        this.myTerminalTextBuffer.lock();
        try {
            int endY;
            int beginY;
            switch (arg) {
                case 0: {
                    if (this.myCursorX < this.myTerminalWidth) {
                        this.myTerminalTextBuffer.eraseCharacters(this.myCursorX, -1, this.myCursorY - 1);
                    }
                    beginY = this.myCursorY;
                    endY = this.myTerminalHeight;
                    break;
                }
                case 1: {
                    this.myTerminalTextBuffer.eraseCharacters(0, this.myCursorX + 1, this.myCursorY - 1);
                    beginY = 0;
                    endY = this.myCursorY - 1;
                    break;
                }
                case 2: {
                    beginY = 0;
                    endY = this.myTerminalHeight - 1;
                    this.myTerminalTextBuffer.moveScreenLinesToHistory();
                    break;
                }
                default: {
                    LOG.error("Unsupported erase in display mode:" + arg);
                    beginY = 1;
                    endY = 1;
                }
            }
            if (beginY != endY) {
                this.clearLines(beginY, endY);
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    public void clearLines(int beginY, int endY) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myTerminalTextBuffer.clearLines(beginY, endY);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void clearScreen() {
        this.clearLines(0, this.myTerminalHeight - 1);
    }

    @Override
    public void setCursorVisible(boolean visible) {
        this.myDisplay.setCursorVisible(visible);
    }

    @Override
    public void useAlternateBuffer(boolean enabled) {
        this.myTerminalTextBuffer.useAlternateBuffer(enabled);
        this.myDisplay.setScrollingEnabled(!enabled);
    }

    @Override
    public byte[] getCodeForKey(int key, int modifiers) {
        return this.myTerminalKeyEncoder.getCode(key, modifiers);
    }

    @Override
    public void setApplicationArrowKeys(boolean enabled) {
        if (enabled) {
            this.myTerminalKeyEncoder.arrowKeysApplicationSequences();
        } else {
            this.myTerminalKeyEncoder.arrowKeysAnsiCursorSequences();
        }
    }

    @Override
    public void setApplicationKeypad(boolean enabled) {
        if (enabled) {
            this.myTerminalKeyEncoder.keypadApplicationSequences();
        } else {
            this.myTerminalKeyEncoder.keypadAnsiSequences();
        }
    }

    @Override
    public void setAutoNewLine(boolean enabled) {
        this.myTerminalKeyEncoder.setAutoNewLine(enabled);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void eraseInLine(int arg) {
        this.myTerminalTextBuffer.lock();
        try {
            switch (arg) {
                case 0: {
                    if (this.myCursorX < this.myTerminalWidth) {
                        this.myTerminalTextBuffer.eraseCharacters(this.myCursorX, -1, this.myCursorY - 1);
                    }
                    this.myTerminalTextBuffer.getLine(this.myCursorY - 1).setWrapped(false);
                    return;
                }
                case 1: {
                    int extent = Math.min(this.myCursorX + 1, this.myTerminalWidth);
                    this.myTerminalTextBuffer.eraseCharacters(0, extent, this.myCursorY - 1);
                    return;
                }
                case 2: {
                    this.myTerminalTextBuffer.eraseCharacters(0, -1, this.myCursorY - 1);
                    return;
                }
                default: {
                    LOG.error("Unsupported erase in line mode:" + arg);
                    return;
                }
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void deleteCharacters(int count) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myTerminalTextBuffer.deleteCharacters(this.myCursorX, this.myCursorY - 1, count);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void insertBlankCharacters(int count) {
        this.myTerminalTextBuffer.lock();
        try {
            int extent = Math.min(count, this.myTerminalWidth - this.myCursorX);
            this.myTerminalTextBuffer.insertBlankCharacters(this.myCursorX, this.myCursorY - 1, extent);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void eraseCharacters(int count) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myTerminalTextBuffer.eraseCharacters(this.myCursorX, this.myCursorX + count, this.myCursorY - 1);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void clearTabStopAtCursor() {
        this.myTabulator.clearTabStop(this.myCursorX);
    }

    @Override
    public void clearAllTabStops() {
        this.myTabulator.clearAllTabStops();
    }

    @Override
    public void setTabStopAtCursor() {
        this.myTabulator.setTabStop(this.myCursorX);
    }

    @Override
    public void insertLines(int count) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myTerminalTextBuffer.insertLines(this.myCursorY - 1, count, this.myScrollRegionBottom);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void deleteLines(int count) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myTerminalTextBuffer.deleteLines(this.myCursorY - 1, count, this.myScrollRegionBottom);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void setBlinkingCursor(boolean enabled) {
        this.myDisplay.setBlinkingCursor(enabled);
    }

    @Override
    public void cursorUp(int countY) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myCursorYChanged = true;
            this.myCursorY -= countY;
            this.myCursorY = Math.max(this.myCursorY, this.scrollingRegionTop());
            this.adjustXY(-1);
            this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void cursorDown(int dY) {
        this.myTerminalTextBuffer.lock();
        try {
            this.myCursorYChanged = true;
            this.myCursorY += dY;
            this.myCursorY = Math.min(this.myCursorY, this.scrollingRegionBottom());
            this.adjustXY(-1);
            this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void index() {
        this.myTerminalTextBuffer.lock();
        try {
            if (this.myCursorY == this.myScrollRegionBottom) {
                this.scrollArea(this.myScrollRegionTop, this.scrollingRegionSize(), -1);
            } else {
                ++this.myCursorY;
                this.adjustXY(-1);
                this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    private void scrollArea(int scrollRegionTop, int scrollRegionSize, int dy) {
        this.myDisplay.scrollArea(scrollRegionTop, scrollRegionSize, dy);
        this.myTerminalTextBuffer.scrollArea(scrollRegionTop, dy, scrollRegionTop + scrollRegionSize - 1);
    }

    @Override
    public void nextLine() {
        this.myTerminalTextBuffer.lock();
        try {
            this.myCursorX = 0;
            if (this.myCursorY == this.myScrollRegionBottom) {
                this.scrollArea(this.myScrollRegionTop, this.scrollingRegionSize(), -1);
            } else {
                ++this.myCursorY;
            }
            this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    private int scrollingRegionSize() {
        return this.myScrollRegionBottom - this.myScrollRegionTop + 1;
    }

    @Override
    public void reverseIndex() {
        this.myTerminalTextBuffer.lock();
        try {
            if (this.myCursorY == this.myScrollRegionTop) {
                this.scrollArea(this.myScrollRegionTop, this.scrollingRegionSize(), 1);
            } else {
                --this.myCursorY;
                this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    private int scrollingRegionTop() {
        return this.isOriginMode() ? this.myScrollRegionTop : 1;
    }

    private int scrollingRegionBottom() {
        return this.isOriginMode() ? this.myScrollRegionBottom : this.myTerminalHeight;
    }

    @Override
    public void cursorForward(int dX) {
        this.myCursorX += dX;
        this.myCursorX = Math.min(this.myCursorX, this.myTerminalWidth - 1);
        this.adjustXY(1);
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void cursorBackward(int dX) {
        this.myCursorX -= dX;
        this.myCursorX = Math.max(this.myCursorX, 0);
        this.adjustXY(-1);
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void cursorShape(CursorShape shape) {
        this.myDisplay.setCursorShape(shape);
    }

    @Override
    public void cursorHorizontalAbsolute(int x) {
        this.cursorPosition(x, this.myCursorY);
    }

    @Override
    public void linePositionAbsolute(int y) {
        this.myCursorY = y;
        this.adjustXY(-1);
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void cursorPosition(int x, int y) {
        this.myCursorY = this.isOriginMode() ? y + this.scrollingRegionTop() - 1 : y;
        if (this.myCursorY > this.scrollingRegionBottom()) {
            this.myCursorY = this.scrollingRegionBottom();
        }
        this.myCursorX = Math.max(0, x - 1);
        this.myCursorX = Math.min(this.myCursorX, this.myTerminalWidth - 1);
        this.myCursorY = Math.max(0, this.myCursorY);
        this.adjustXY(-1);
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    @Override
    public void setScrollingRegion(int top, int bottom) {
        if (top > bottom) {
            LOG.error("Top margin of scroll region can't be greater then bottom: " + top + ">" + bottom);
        }
        this.myScrollRegionTop = Math.max(1, top);
        this.myScrollRegionBottom = Math.min(this.myTerminalHeight, bottom);
        this.cursorPosition(1, 1);
    }

    @Override
    public void scrollUp(int count) {
        this.scrollDown(-count);
    }

    @Override
    public void scrollDown(int count) {
        this.myTerminalTextBuffer.lock();
        try {
            this.scrollArea(this.myScrollRegionTop, this.scrollingRegionSize(), count);
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @Override
    public void resetScrollRegions() {
        this.setScrollingRegion(1, this.myTerminalHeight);
    }

    @Override
    public void characterAttributes(TextStyle textStyle) {
        this.myStyleState.setCurrent(textStyle);
    }

    @Override
    public void beep() {
        this.myDisplay.beep();
    }

    @Override
    public int distanceToLineEnd() {
        return this.myTerminalWidth - this.myCursorX;
    }

    @Override
    public void saveCursor() {
        this.myStoredCursor = this.createCursorState();
    }

    private StoredCursor createCursorState() {
        return new StoredCursor(this.myCursorX, this.myCursorY, this.myStyleState.getCurrent(), this.isAutoWrap(), this.isOriginMode(), this.myGraphicSetState);
    }

    @Override
    public void restoreCursor() {
        if (this.myStoredCursor != null) {
            this.restoreCursor(this.myStoredCursor);
        } else {
            this.setModeEnabled(TerminalMode.OriginMode, false);
            this.cursorPosition(1, 1);
            this.myStyleState.reset();
            this.myGraphicSetState.resetState();
        }
        this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
    }

    public void restoreCursor(@NotNull StoredCursor storedCursor) {
        if (storedCursor == null) {
            JediTerminal.$$$reportNull$$$0(0);
        }
        this.myCursorX = storedCursor.getCursorX();
        this.myCursorY = storedCursor.getCursorY();
        this.adjustXY(-1);
        this.myStyleState.setCurrent(storedCursor.getTextStyle());
        this.setModeEnabled(TerminalMode.AutoWrap, storedCursor.isAutoWrap());
        this.setModeEnabled(TerminalMode.OriginMode, storedCursor.isOriginMode());
        CharacterSet[] designations = storedCursor.getDesignations();
        for (int i = 0; i < designations.length; ++i) {
            this.myGraphicSetState.designateGraphicSet(i, designations[i]);
        }
        this.myGraphicSetState.setGL(storedCursor.getGLMapping());
        this.myGraphicSetState.setGR(storedCursor.getGRMapping());
        if (storedCursor.getGLOverride() >= 0) {
            this.myGraphicSetState.overrideGL(storedCursor.getGLOverride());
        }
    }

    @Override
    public void reset() {
        this.myGraphicSetState.resetState();
        this.myStyleState.reset();
        this.myTerminalTextBuffer.clearAll();
        this.myDisplay.setScrollingEnabled(true);
        this.initModes();
        this.initMouseModes();
        this.cursorPosition(1, 1);
    }

    private void initMouseModes() {
        this.setMouseMode(MouseMode.MOUSE_REPORTING_NONE);
        this.setMouseFormat(MouseFormat.MOUSE_FORMAT_XTERM);
    }

    private void initModes() {
        this.myModes.clear();
        this.setModeEnabled(TerminalMode.AutoWrap, true);
        this.setModeEnabled(TerminalMode.AutoNewLine, false);
        this.setModeEnabled(TerminalMode.CursorVisible, true);
        this.setModeEnabled(TerminalMode.CursorBlinking, true);
    }

    public boolean isAutoNewLine() {
        return this.myModes.contains((Object)TerminalMode.AutoNewLine);
    }

    public boolean isOriginMode() {
        return this.myModes.contains((Object)TerminalMode.OriginMode);
    }

    public boolean isAutoWrap() {
        return this.myModes.contains((Object)TerminalMode.AutoWrap);
    }

    private static int createButtonCode(MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event)) {
            return 0;
        }
        if (SwingUtilities.isMiddleMouseButton(event)) {
            return 1;
        }
        if (SwingUtilities.isRightMouseButton(event)) {
            return -1;
        }
        if (event instanceof MouseWheelEvent) {
            if (((MouseWheelEvent)event).getWheelRotation() > 0) {
                return 5;
            }
            return 4;
        }
        return -1;
    }

    private byte[] mouseReport(int button, int x, int y) {
        StringBuilder sb = new StringBuilder();
        String charset = "UTF-8";
        switch (this.myMouseFormat) {
            case MOUSE_FORMAT_XTERM_EXT: {
                sb.append(String.format("\u001b[M%c%c%c", Character.valueOf((char)(32 + button)), Character.valueOf((char)(32 + x)), Character.valueOf((char)(32 + y))));
                break;
            }
            case MOUSE_FORMAT_URXVT: {
                sb.append(String.format("\u001b[%d;%d;%dM", 32 + button, x, y));
                break;
            }
            case MOUSE_FORMAT_SGR: {
                if ((button & 0x80) != 0) {
                    sb.append(String.format("\u001b[<%d;%d;%dm", button ^ 0x80, x, y));
                    break;
                }
                sb.append(String.format("\u001b[<%d;%d;%dM", button, x, y));
                break;
            }
            default: {
                charset = "ISO-8859-1";
                sb.append(String.format("\u001b[M%c%c%c", Character.valueOf((char)(32 + button)), Character.valueOf((char)(32 + x)), Character.valueOf((char)(32 + y))));
            }
        }
        LOG.debug((Object)((Object)this.myMouseFormat) + " (" + charset + ") report : " + button + ", " + x + "x" + y + " = " + sb);
        return sb.toString().getBytes(Charset.forName(charset));
    }

    private boolean shouldSendMouseData(MouseMode ... eligibleModes) {
        if (this.myMouseMode == MouseMode.MOUSE_REPORTING_NONE || this.myTerminalOutput == null) {
            return false;
        }
        if (this.myMouseMode == MouseMode.MOUSE_REPORTING_ALL_MOTION) {
            return true;
        }
        for (MouseMode m : eligibleModes) {
            if (this.myMouseMode != m) continue;
            return true;
        }
        return false;
    }

    @Override
    public void mousePressed(int x, int y, MouseEvent event) {
        int cb;
        if (this.shouldSendMouseData(MouseMode.MOUSE_REPORTING_NORMAL, MouseMode.MOUSE_REPORTING_BUTTON_MOTION) && (cb = JediTerminal.createButtonCode(event)) != -1) {
            if (cb == 4 || cb == 5) {
                int offset = 4;
                cb -= offset;
                cb |= 0x40;
            }
            cb = JediTerminal.applyModifierKeys(event, cb);
            if (this.myTerminalOutput != null) {
                this.myTerminalOutput.sendBytes(this.mouseReport(cb, x + 1, y + 1));
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y, MouseEvent event) {
        int cb;
        if (this.shouldSendMouseData(MouseMode.MOUSE_REPORTING_NORMAL, MouseMode.MOUSE_REPORTING_BUTTON_MOTION) && (cb = JediTerminal.createButtonCode(event)) != -1) {
            cb = this.myMouseFormat == MouseFormat.MOUSE_FORMAT_SGR ? (cb |= 0x80) : 3;
            cb = JediTerminal.applyModifierKeys(event, cb);
            if (this.myTerminalOutput != null) {
                this.myTerminalOutput.sendBytes(this.mouseReport(cb, x + 1, y + 1));
            }
        }
        this.myLastMotionReport = null;
    }

    @Override
    public void mouseMoved(int x, int y, MouseEvent event) {
        if (this.myLastMotionReport != null && this.myLastMotionReport.equals(new Point(x, y))) {
            return;
        }
        if (this.shouldSendMouseData(MouseMode.MOUSE_REPORTING_ALL_MOTION) && this.myTerminalOutput != null) {
            this.myTerminalOutput.sendBytes(this.mouseReport(3, x + 1, y + 1));
        }
        this.myLastMotionReport = new Point(x, y);
    }

    @Override
    public void mouseDragged(int x, int y, MouseEvent event) {
        int cb;
        if (this.myLastMotionReport != null && this.myLastMotionReport.equals(new Point(x, y))) {
            return;
        }
        if (this.shouldSendMouseData(MouseMode.MOUSE_REPORTING_BUTTON_MOTION) && (cb = JediTerminal.createButtonCode(event)) != -1) {
            cb |= 0x20;
            cb = JediTerminal.applyModifierKeys(event, cb);
            if (this.myTerminalOutput != null) {
                this.myTerminalOutput.sendBytes(this.mouseReport(cb, x + 1, y + 1));
            }
        }
        this.myLastMotionReport = new Point(x, y);
    }

    @Override
    public void mouseWheelMoved(int x, int y, MouseWheelEvent event) {
        this.mousePressed(x, y, event);
    }

    private static int applyModifierKeys(MouseEvent event, int cb) {
        if (event.isControlDown()) {
            cb |= 0x10;
        }
        if (event.isShiftDown()) {
            cb |= 4;
        }
        if ((event.getModifiersEx() & 4) != 0) {
            cb |= 8;
        }
        return cb;
    }

    @Override
    public void setTerminalOutput(TerminalOutputStream terminalOutput) {
        this.myTerminalOutput = terminalOutput;
    }

    @Override
    public void setMouseMode(@NotNull MouseMode mode) {
        if (mode == null) {
            JediTerminal.$$$reportNull$$$0(1);
        }
        this.myMouseMode = mode;
        this.myDisplay.terminalMouseModeSet(mode);
    }

    @Override
    public void setAltSendsEscape(boolean enabled) {
        this.myTerminalKeyEncoder.setAltSendsEscape(enabled);
    }

    @Override
    public void deviceStatusReport(String str) {
        if (this.myTerminalOutput != null) {
            this.myTerminalOutput.sendString(str);
        }
    }

    @Override
    public void deviceAttributes(byte[] response) {
        if (this.myTerminalOutput != null) {
            this.myTerminalOutput.sendBytes(response);
        }
    }

    @Override
    public void setLinkUriStarted(@NotNull String uri) {
        if (uri == null) {
            JediTerminal.$$$reportNull$$$0(2);
        }
        TextStyle style = this.myStyleState.getCurrent();
        this.myStyleState.setCurrent(new HyperlinkStyle(style, new LinkInfo(() -> {
            try {
                Desktop.getDesktop().browse(new URI(uri));
            } catch (Exception exception) {
                // empty catch block
            }
        })));
    }

    @Override
    public void setLinkUriFinished() {
        TextStyle prevTextStyle;
        TextStyle current = this.myStyleState.getCurrent();
        if (current instanceof HyperlinkStyle && (prevTextStyle = ((HyperlinkStyle)current).getPrevTextStyle()) != null) {
            this.myStyleState.setCurrent(prevTextStyle);
        }
    }

    @Override
    public void setMouseFormat(MouseFormat mouseFormat) {
        this.myMouseFormat = mouseFormat;
    }

    private void adjustXY(int dirX) {
        if (this.myCursorY > -this.myTerminalTextBuffer.getHistoryLinesCount() && Character.isLowSurrogate(this.myTerminalTextBuffer.getCharAt(this.myCursorX, this.myCursorY - 1))) {
            this.myCursorX = dirX > 0 ? (this.myCursorX == this.myTerminalWidth ? --this.myCursorX : ++this.myCursorX) : --this.myCursorX;
        }
    }

    @Override
    public int getX() {
        return this.myCursorX;
    }

    @Override
    public void setX(int x) {
        this.myCursorX = x;
        this.adjustXY(-1);
    }

    @Override
    public int getY() {
        return this.myCursorY;
    }

    @Override
    public void setY(int y) {
        this.myCursorY = y;
        this.adjustXY(-1);
    }

    public void writeString(String s) {
        this.writeCharacters(s);
    }

    @Override
    public void resize(@NotNull Dimension newTermSize, @NotNull RequestOrigin origin) {
        if (newTermSize == null) {
            JediTerminal.$$$reportNull$$$0(3);
        }
        if (origin == null) {
            JediTerminal.$$$reportNull$$$0(4);
        }
        this.resize(newTermSize, origin, CompletableFuture.completedFuture(null));
    }

    @Override
    public void resize(@NotNull Dimension newTermSize, @NotNull RequestOrigin origin, @NotNull CompletableFuture<?> promptUpdated) {
        if (newTermSize == null) {
            JediTerminal.$$$reportNull$$$0(5);
        }
        if (origin == null) {
            JediTerminal.$$$reportNull$$$0(6);
        }
        if (promptUpdated == null) {
            JediTerminal.$$$reportNull$$$0(7);
        }
        int oldHeight = this.myTerminalHeight;
        JediTerminal.ensureTermMinimumSize(newTermSize);
        if (newTermSize.width == this.myTerminalWidth && newTermSize.height == this.myTerminalHeight) {
            return;
        }
        if (newTermSize.width == this.myTerminalWidth) {
            this.doResize(newTermSize, origin, oldHeight);
        } else {
            this.myTerminalWidth = newTermSize.width;
            this.myTerminalHeight = newTermSize.height;
            promptUpdated.thenRun(() -> this.doResize(newTermSize, origin, oldHeight));
        }
    }

    private void doResize(@NotNull Dimension newTermSize, @NotNull RequestOrigin origin, int oldHeight) {
        if (newTermSize == null) {
            JediTerminal.$$$reportNull$$$0(8);
        }
        if (origin == null) {
            JediTerminal.$$$reportNull$$$0(9);
        }
        this.myDisplay.requestResize(newTermSize, origin, this.myCursorX, this.myCursorY, (termWidth, termHeight, cursorX, cursorY) -> {
            this.myTerminalWidth = termWidth;
            this.myTerminalHeight = termHeight;
            this.myCursorY = cursorY;
            this.myCursorX = Math.min(cursorX, this.myTerminalWidth - 1);
            this.myDisplay.setCursor(this.myCursorX, this.myCursorY);
            this.myTabulator.resize(this.myTerminalWidth);
        });
        this.myScrollRegionBottom += this.myTerminalHeight - oldHeight;
    }

    public static void ensureTermMinimumSize(@NotNull Dimension termSize) {
        if (termSize == null) {
            JediTerminal.$$$reportNull$$$0(10);
        }
        termSize.setSize(Math.max(5, termSize.width), Math.max(2, termSize.height));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void fillScreen(char c) {
        this.myTerminalTextBuffer.lock();
        try {
            char[] chars = new char[this.myTerminalWidth];
            Arrays.fill(chars, c);
            for (int row = 1; row <= this.myTerminalHeight; ++row) {
                this.myTerminalTextBuffer.writeString(0, row, this.newCharBuf(chars));
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    @NotNull
    private CharBuffer newCharBuf(char[] str) {
        char[] buf;
        int dwcCount = CharUtils.countDoubleWidthCharacters(str, 0, str.length, this.myDisplay.ambiguousCharsAreDoubleWidth());
        if (dwcCount > 0) {
            buf = new char[str.length + dwcCount];
            int j = 0;
            for (int i = 0; i < str.length; ++i) {
                buf[j] = str[i];
                int codePoint = Character.codePointAt(str, i);
                boolean doubleWidthCharacter = CharUtils.isDoubleWidthCharacter(codePoint, this.myDisplay.ambiguousCharsAreDoubleWidth());
                if (doubleWidthCharacter) {
                    buf[++j] = 57344;
                }
                ++j;
            }
        } else {
            buf = str;
        }
        return new CharBuffer(buf, 0, buf.length);
    }

    @Override
    public int getTerminalWidth() {
        return this.myTerminalWidth;
    }

    @Override
    public int getTerminalHeight() {
        return this.myTerminalHeight;
    }

    @Override
    public int getCursorX() {
        return this.myCursorX + 1;
    }

    @Override
    public int getCursorY() {
        return this.myCursorY;
    }

    @Override
    public StyleState getStyleState() {
        return this.myStyleState;
    }

    public SubstringFinder.FindResult searchInTerminalTextBuffer(String pattern, boolean ignoreCase) {
        if (pattern.length() == 0) {
            return null;
        }
        final SubstringFinder finder = new SubstringFinder(pattern, ignoreCase);
        this.myTerminalTextBuffer.processHistoryAndScreenLines(-this.myTerminalTextBuffer.getHistoryLinesCount(), -1, new StyledTextConsumer(){

            @Override
            public void consume(int x, int y, @NotNull TextStyle style, @NotNull CharBuffer characters, int startRow) {
                if (style == null) {
                    1.$$$reportNull$$$0(0);
                }
                if (characters == null) {
                    1.$$$reportNull$$$0(1);
                }
                int offset = 0;
                int length = characters.length();
                if (characters instanceof SubCharBuffer) {
                    SubCharBuffer subCharBuffer = (SubCharBuffer)characters;
                    characters = subCharBuffer.getParent();
                    offset = subCharBuffer.getOffset();
                }
                for (int i = offset; i < offset + length; ++i) {
                    finder.nextChar(x, y - startRow, characters, i);
                }
            }

            @Override
            public void consumeNul(int x, int y, int nulIndex, @NotNull TextStyle style, @NotNull CharBuffer characters, int startRow) {
                if (style == null) {
                    1.$$$reportNull$$$0(2);
                }
                if (characters == null) {
                    1.$$$reportNull$$$0(3);
                }
            }

            @Override
            public void consumeQueue(int x, int y, int nulIndex, int startRow) {
            }

            private static /* synthetic */ void $$$reportNull$$$0(int n) {
                Object[] objectArray;
                Object[] objectArray2;
                Object[] objectArray3 = new Object[3];
                switch (n) {
                    default: {
                        objectArray2 = objectArray3;
                        objectArray3[0] = "style";
                        break;
                    }
                    case 1: 
                    case 3: {
                        objectArray2 = objectArray3;
                        objectArray3[0] = "characters";
                        break;
                    }
                }
                objectArray2[1] = "com/jediterm/terminal/model/JediTerminal$1";
                switch (n) {
                    default: {
                        objectArray = objectArray2;
                        objectArray2[2] = "consume";
                        break;
                    }
                    case 2: 
                    case 3: {
                        objectArray = objectArray2;
                        objectArray2[2] = "consumeNul";
                        break;
                    }
                }
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
            }
        });
        return finder.getResult();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2;
        Object[] objectArray3 = new Object[3];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "storedCursor";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "mode";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "uri";
                break;
            }
            case 3: 
            case 5: 
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "newTermSize";
                break;
            }
            case 4: 
            case 6: 
            case 9: {
                objectArray2 = objectArray3;
                objectArray3[0] = "origin";
                break;
            }
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "promptUpdated";
                break;
            }
            case 10: {
                objectArray2 = objectArray3;
                objectArray3[0] = "termSize";
                break;
            }
        }
        objectArray2[1] = "com/jediterm/terminal/model/JediTerminal";
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[2] = "restoreCursor";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[2] = "setMouseMode";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[2] = "setLinkUriStarted";
                break;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                objectArray = objectArray2;
                objectArray2[2] = "resize";
                break;
            }
            case 8: 
            case 9: {
                objectArray = objectArray2;
                objectArray2[2] = "doResize";
                break;
            }
            case 10: {
                objectArray = objectArray2;
                objectArray2[2] = "ensureTermMinimumSize";
                break;
            }
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }

    private static class DefaultTabulator
    implements Tabulator {
        private static final int TAB_LENGTH = 8;
        private final SortedSet<Integer> myTabStops = new TreeSet<Integer>();
        private int myWidth;
        private int myTabLength;

        public DefaultTabulator(int width) {
            this(width, 8);
        }

        public DefaultTabulator(int width, int tabLength) {
            this.myWidth = width;
            this.myTabLength = tabLength;
            this.initTabStops(width, tabLength);
        }

        private void initTabStops(int columns, int tabLength) {
            for (int i = tabLength; i < columns; i += tabLength) {
                this.myTabStops.add(i);
            }
        }

        @Override
        public void resize(int columns) {
            if (columns > this.myWidth) {
                for (int i = this.myTabLength * (this.myWidth / this.myTabLength); i < columns; i += this.myTabLength) {
                    if (i < this.myWidth) continue;
                    this.myTabStops.add(i);
                }
            } else {
                Iterator it = this.myTabStops.iterator();
                while (it.hasNext()) {
                    int i = (Integer)it.next();
                    if (i <= columns) continue;
                    it.remove();
                }
            }
            this.myWidth = columns;
        }

        @Override
        public void clearTabStop(int position) {
            this.myTabStops.remove(position);
        }

        @Override
        public void clearAllTabStops() {
            this.myTabStops.clear();
        }

        @Override
        public int getNextTabWidth(int position) {
            return this.nextTab(position) - position;
        }

        @Override
        public int getPreviousTabWidth(int position) {
            return position - this.previousTab(position);
        }

        @Override
        public int nextTab(int position) {
            int tabStop = Integer.MAX_VALUE;
            SortedSet<Integer> tailSet = this.myTabStops.tailSet(position + 1);
            if (!tailSet.isEmpty()) {
                tabStop = tailSet.first();
            }
            return Math.min(tabStop, this.myWidth - 1);
        }

        @Override
        public int previousTab(int position) {
            int tabStop = 0;
            SortedSet<Integer> headSet = this.myTabStops.headSet(position);
            if (!headSet.isEmpty()) {
                tabStop = headSet.last();
            }
            return Math.max(0, tabStop);
        }

        @Override
        public void setTabStop(int position) {
            this.myTabStops.add(position);
        }
    }

    public static interface ResizeHandler {
        public void sizeUpdated(int var1, int var2, int var3, int var4);
    }
}

