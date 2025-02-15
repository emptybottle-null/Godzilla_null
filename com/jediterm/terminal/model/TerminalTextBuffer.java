/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.StyledTextConsumer;
import com.jediterm.terminal.StyledTextConsumerAdapter;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.ChangeWidthOperation;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.JediTerminal;
import com.jediterm.terminal.model.LinesBuffer;
import com.jediterm.terminal.model.StyleState;
import com.jediterm.terminal.model.TerminalLine;
import com.jediterm.terminal.model.TerminalModelListener;
import com.jediterm.terminal.model.TerminalSelection;
import com.jediterm.terminal.model.hyperlinks.TextProcessing;
import com.jediterm.terminal.util.Pair;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerminalTextBuffer {
    private static final Logger LOG = Logger.getLogger(TerminalTextBuffer.class);
    @NotNull
    private final StyleState myStyleState;
    private LinesBuffer myHistoryBuffer;
    private LinesBuffer myScreenBuffer;
    private int myWidth;
    private int myHeight;
    private final int myHistoryLinesCount;
    private final Lock myLock;
    private LinesBuffer myHistoryBufferBackup;
    private LinesBuffer myScreenBufferBackup;
    private boolean myAlternateBuffer;
    private boolean myUsingAlternateBuffer;
    private final List<TerminalModelListener> myListeners;
    @Nullable
    private final TextProcessing myTextProcessing;

    public TerminalTextBuffer(int width, int height, @NotNull StyleState styleState) {
        if (styleState == null) {
            TerminalTextBuffer.$$$reportNull$$$0(0);
        }
        this(width, height, styleState, null);
    }

    public TerminalTextBuffer(int width, int height, @NotNull StyleState styleState, @Nullable TextProcessing textProcessing) {
        if (styleState == null) {
            TerminalTextBuffer.$$$reportNull$$$0(1);
        }
        this(width, height, styleState, 5000, textProcessing);
    }

    public TerminalTextBuffer(int width, int height, @NotNull StyleState styleState, int historyLinesCount, @Nullable TextProcessing textProcessing) {
        if (styleState == null) {
            TerminalTextBuffer.$$$reportNull$$$0(2);
        }
        this.myLock = new ReentrantLock();
        this.myAlternateBuffer = false;
        this.myUsingAlternateBuffer = false;
        this.myListeners = new CopyOnWriteArrayList<TerminalModelListener>();
        this.myStyleState = styleState;
        this.myWidth = width;
        this.myHeight = height;
        this.myHistoryLinesCount = historyLinesCount;
        this.myTextProcessing = textProcessing;
        this.myScreenBuffer = this.createScreenBuffer();
        this.myHistoryBuffer = this.createHistoryBuffer();
    }

    @NotNull
    private LinesBuffer createScreenBuffer() {
        return new LinesBuffer(-1, this.myTextProcessing);
    }

    @NotNull
    private LinesBuffer createHistoryBuffer() {
        return new LinesBuffer(this.myHistoryLinesCount, this.myTextProcessing);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Dimension resize(@NotNull Dimension pendingResize, @NotNull RequestOrigin origin, int cursorX, int cursorY, @NotNull JediTerminal.ResizeHandler resizeHandler, @Nullable TerminalSelection mySelection) {
        if (pendingResize == null) {
            TerminalTextBuffer.$$$reportNull$$$0(3);
        }
        if (origin == null) {
            TerminalTextBuffer.$$$reportNull$$$0(4);
        }
        if (resizeHandler == null) {
            TerminalTextBuffer.$$$reportNull$$$0(5);
        }
        this.lock();
        try {
            Dimension dimension = this.doResize(pendingResize, origin, cursorX, cursorY, resizeHandler, mySelection);
            return dimension;
        } finally {
            this.unlock();
        }
    }

    private Dimension doResize(@NotNull Dimension pendingResize, @NotNull RequestOrigin origin, int cursorX, int cursorY, @NotNull JediTerminal.ResizeHandler resizeHandler, @Nullable TerminalSelection mySelection) {
        int oldHeight;
        if (pendingResize == null) {
            TerminalTextBuffer.$$$reportNull$$$0(6);
        }
        if (origin == null) {
            TerminalTextBuffer.$$$reportNull$$$0(7);
        }
        if (resizeHandler == null) {
            TerminalTextBuffer.$$$reportNull$$$0(8);
        }
        int newWidth = pendingResize.width;
        int newHeight = pendingResize.height;
        int newCursorX = cursorX;
        int newCursorY = cursorY;
        if (this.myWidth != newWidth) {
            ChangeWidthOperation changeWidthOperation = new ChangeWidthOperation(this, newWidth, newHeight);
            Point cursor = new Point(cursorX, cursorY - 1);
            changeWidthOperation.addPointToTrack(cursor);
            if (mySelection != null) {
                changeWidthOperation.addPointToTrack(mySelection.getStart());
                changeWidthOperation.addPointToTrack(mySelection.getEnd());
            }
            changeWidthOperation.run();
            this.myWidth = newWidth;
            this.myHeight = newHeight;
            Point newCursor = changeWidthOperation.getTrackedPoint(cursor);
            newCursorX = newCursor.x;
            newCursorY = newCursor.y + 1;
            if (mySelection != null) {
                mySelection.getStart().setLocation(changeWidthOperation.getTrackedPoint(mySelection.getStart()));
                mySelection.getEnd().setLocation(changeWidthOperation.getTrackedPoint(mySelection.getEnd()));
            }
        }
        if (newHeight < (oldHeight = this.myHeight)) {
            int count = oldHeight - newHeight;
            if (!this.myAlternateBuffer) {
                int emptyLinesDeleted = this.myScreenBuffer.removeBottomEmptyLines(oldHeight - 1, count);
                this.myScreenBuffer.moveTopLinesTo(count - emptyLinesDeleted, this.myHistoryBuffer);
                newCursorY = cursorY - (count - emptyLinesDeleted);
            } else {
                newCursorY = cursorY;
            }
            if (mySelection != null) {
                mySelection.shiftY(-count);
            }
        } else if (newHeight > oldHeight) {
            if (!this.myAlternateBuffer) {
                int historyLinesCount = Math.min(newHeight - oldHeight, this.myHistoryBuffer.getLineCount());
                this.myHistoryBuffer.moveBottomLinesTo(historyLinesCount, this.myScreenBuffer);
                newCursorY = cursorY + historyLinesCount;
            } else {
                newCursorY = cursorY;
            }
            if (mySelection != null) {
                mySelection.shiftY(newHeight - cursorY);
            }
        }
        this.myWidth = newWidth;
        this.myHeight = newHeight;
        resizeHandler.sizeUpdated(this.myWidth, this.myHeight, newCursorX, newCursorY);
        this.fireModelChangeEvent();
        return pendingResize;
    }

    public void addModelListener(TerminalModelListener listener) {
        this.myListeners.add(listener);
    }

    public void removeModelListener(TerminalModelListener listener) {
        this.myListeners.remove(listener);
    }

    private void fireModelChangeEvent() {
        for (TerminalModelListener modelListener : this.myListeners) {
            modelListener.modelChanged();
        }
    }

    private TextStyle createEmptyStyleWithCurrentColor() {
        return this.myStyleState.getCurrent().createEmptyWithColors();
    }

    private TerminalLine.TextEntry createFillerEntry() {
        return new TerminalLine.TextEntry(this.createEmptyStyleWithCurrentColor(), new CharBuffer('\u0000', this.myWidth));
    }

    public void deleteCharacters(int x, int y, int count) {
        if (y > this.myHeight - 1 || y < 0) {
            LOG.error("attempt to delete in line " + y + "\nargs were x:" + x + " count:" + count);
        } else if (count < 0) {
            LOG.error("Attempt to delete negative chars number: count:" + count);
        } else if (count > 0) {
            this.myScreenBuffer.deleteCharacters(x, y, count, this.createEmptyStyleWithCurrentColor());
            this.fireModelChangeEvent();
        }
    }

    public void insertBlankCharacters(int x, int y, int count) {
        if (y > this.myHeight - 1 || y < 0) {
            LOG.error("attempt to insert blank chars in line " + y + "\nargs were x:" + x + " count:" + count);
        } else if (count < 0) {
            LOG.error("Attempt to insert negative blank chars number: count:" + count);
        } else if (count > 0) {
            this.myScreenBuffer.insertBlankCharacters(x, y, count, this.myWidth, this.createEmptyStyleWithCurrentColor());
            this.fireModelChangeEvent();
        }
    }

    public void writeString(int x, int y, @NotNull CharBuffer str) {
        if (str == null) {
            TerminalTextBuffer.$$$reportNull$$$0(9);
        }
        this.writeString(x, y, str, this.myStyleState.getCurrent());
    }

    public void addLine(@NotNull TerminalLine line) {
        if (line == null) {
            TerminalTextBuffer.$$$reportNull$$$0(10);
        }
        this.myScreenBuffer.addLines(Lists.newArrayList(line));
        this.fireModelChangeEvent();
    }

    private void writeString(int x, int y, @NotNull CharBuffer str, @NotNull TextStyle style) {
        if (str == null) {
            TerminalTextBuffer.$$$reportNull$$$0(11);
        }
        if (style == null) {
            TerminalTextBuffer.$$$reportNull$$$0(12);
        }
        this.myScreenBuffer.writeString(x, y - 1, str, style);
        this.fireModelChangeEvent();
    }

    public void scrollArea(int scrollRegionTop, int dy, int scrollRegionBottom) {
        if (dy == 0) {
            return;
        }
        if (dy > 0) {
            this.insertLines(scrollRegionTop - 1, dy, scrollRegionBottom);
        } else {
            LinesBuffer removed = this.deleteLines(scrollRegionTop - 1, -dy, scrollRegionBottom);
            if (scrollRegionTop == 1) {
                removed.moveTopLinesTo(removed.getLineCount(), this.myHistoryBuffer);
            }
            this.fireModelChangeEvent();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getStyleLines() {
        final HashMap hashMap = Maps.newHashMap();
        this.myLock.lock();
        try {
            final StringBuilder sb = new StringBuilder();
            this.myScreenBuffer.processLines(0, this.myHeight, new StyledTextConsumerAdapter(){
                int count = 0;

                @Override
                public void consume(int x, int y, @NotNull TextStyle style, @NotNull CharBuffer characters, int startRow) {
                    int styleNum;
                    if (style == null) {
                        1.$$$reportNull$$$0(0);
                    }
                    if (characters == null) {
                        1.$$$reportNull$$$0(1);
                    }
                    if (x == 0) {
                        sb.append("\n");
                    }
                    if (!hashMap.containsKey(styleNum = style.getId())) {
                        hashMap.put(styleNum, this.count++);
                    }
                    sb.append(String.format("%02d ", hashMap.get(styleNum)));
                }

                private static /* synthetic */ void $$$reportNull$$$0(int n) {
                    Object[] objectArray;
                    Object[] objectArray2 = new Object[3];
                    switch (n) {
                        default: {
                            objectArray = objectArray2;
                            objectArray2[0] = "style";
                            break;
                        }
                        case 1: {
                            objectArray = objectArray2;
                            objectArray2[0] = "characters";
                            break;
                        }
                    }
                    objectArray[1] = "com/jediterm/terminal/model/TerminalTextBuffer$1";
                    objectArray[2] = "consume";
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
                }
            });
            String string = sb.toString();
            return string;
        } finally {
            this.myLock.unlock();
        }
    }

    public TerminalLine getLine(int index) {
        if (index >= 0) {
            if (index >= this.getHeight()) {
                LOG.error("Attempt to get line out of bounds: " + index + " >= " + this.getHeight());
                return TerminalLine.createEmpty();
            }
            return this.myScreenBuffer.getLine(index);
        }
        if (index < -this.getHistoryLinesCount()) {
            LOG.error("Attempt to get line out of bounds: " + index + " < " + -this.getHistoryLinesCount());
            return TerminalLine.createEmpty();
        }
        return this.myHistoryBuffer.getLine(this.getHistoryLinesCount() + index);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String getScreenLines() {
        this.myLock.lock();
        try {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < this.myHeight; ++row) {
                StringBuilder line = new StringBuilder(this.myScreenBuffer.getLine(row).getText());
                for (int i = line.length(); i < this.myWidth; ++i) {
                    line.append(' ');
                }
                if (line.length() > this.myWidth) {
                    line.setLength(this.myWidth);
                }
                sb.append((CharSequence)line);
                sb.append('\n');
            }
            String string = sb.toString();
            return string;
        } finally {
            this.myLock.unlock();
        }
    }

    public void processScreenLines(int yStart, int yCount, @NotNull StyledTextConsumer consumer) {
        if (consumer == null) {
            TerminalTextBuffer.$$$reportNull$$$0(13);
        }
        this.myScreenBuffer.processLines(yStart, yCount, consumer);
    }

    public void lock() {
        this.myLock.lock();
    }

    public void unlock() {
        this.myLock.unlock();
    }

    public boolean tryLock() {
        return this.myLock.tryLock();
    }

    public int getWidth() {
        return this.myWidth;
    }

    public int getHeight() {
        return this.myHeight;
    }

    public int getHistoryLinesCount() {
        return this.myHistoryBuffer.getLineCount();
    }

    public int getScreenLinesCount() {
        return this.myScreenBuffer.getLineCount();
    }

    public char getBuffersCharAt(int x, int y) {
        return this.getLine(y).charAt(x);
    }

    public TextStyle getStyleAt(int x, int y) {
        return this.getLine(y).getStyleAt(x);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Pair<Character, TextStyle> getStyledCharAt(int x, int y) {
        LinesBuffer linesBuffer = this.myScreenBuffer;
        synchronized (linesBuffer) {
            TerminalLine line = this.getLine(y);
            return new Pair<Character, TextStyle>(Character.valueOf(line.charAt(x)), line.getStyleAt(x));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public char getCharAt(int x, int y) {
        LinesBuffer linesBuffer = this.myScreenBuffer;
        synchronized (linesBuffer) {
            TerminalLine line = this.getLine(y);
            return line.charAt(x);
        }
    }

    public boolean isUsingAlternateBuffer() {
        return this.myUsingAlternateBuffer;
    }

    public void useAlternateBuffer(boolean enabled) {
        this.myAlternateBuffer = enabled;
        if (enabled) {
            if (!this.myUsingAlternateBuffer) {
                this.myScreenBufferBackup = this.myScreenBuffer;
                this.myHistoryBufferBackup = this.myHistoryBuffer;
                this.myScreenBuffer = this.createScreenBuffer();
                this.myHistoryBuffer = this.createHistoryBuffer();
                this.myUsingAlternateBuffer = true;
            }
        } else if (this.myUsingAlternateBuffer) {
            this.myScreenBuffer = this.myScreenBufferBackup;
            this.myHistoryBuffer = this.myHistoryBufferBackup;
            this.myScreenBufferBackup = this.createScreenBuffer();
            this.myHistoryBufferBackup = this.createHistoryBuffer();
            this.myUsingAlternateBuffer = false;
        }
        this.fireModelChangeEvent();
    }

    public LinesBuffer getHistoryBuffer() {
        return this.myHistoryBuffer;
    }

    public void insertLines(int y, int count, int scrollRegionBottom) {
        this.myScreenBuffer.insertLines(y, count, scrollRegionBottom - 1, this.createFillerEntry());
        this.fireModelChangeEvent();
    }

    public LinesBuffer deleteLines(int y, int count, int scrollRegionBottom) {
        LinesBuffer linesBuffer = this.myScreenBuffer.deleteLines(y, count, scrollRegionBottom - 1, this.createFillerEntry());
        this.fireModelChangeEvent();
        return linesBuffer;
    }

    public void clearLines(int startRow, int endRow) {
        this.myScreenBuffer.clearLines(startRow, endRow, this.createFillerEntry());
        this.fireModelChangeEvent();
    }

    public void eraseCharacters(int leftX, int rightX, int y) {
        TextStyle style = this.createEmptyStyleWithCurrentColor();
        if (y >= 0) {
            this.myScreenBuffer.clearArea(leftX, y, rightX, y + 1, style);
            this.fireModelChangeEvent();
            if (this.myTextProcessing != null && y < this.getHeight()) {
                this.myTextProcessing.processHyperlinks(this.myScreenBuffer, this.getLine(y));
            }
        } else {
            LOG.error("Attempt to erase characters in line: " + y);
        }
    }

    public void clearAll() {
        this.myScreenBuffer.clearAll();
        this.fireModelChangeEvent();
    }

    public void processHistoryAndScreenLines(int scrollOrigin, int maximalLinesToProcess, StyledTextConsumer consumer) {
        if (maximalLinesToProcess < 0) {
            maximalLinesToProcess = this.myHistoryBuffer.getLineCount() + this.myScreenBuffer.getLineCount();
        }
        int linesFromHistory = Math.min(-scrollOrigin, maximalLinesToProcess);
        int y = this.myHistoryBuffer.getLineCount() + scrollOrigin;
        if (y < 0) {
            y = 0;
        }
        this.myHistoryBuffer.processLines(y, linesFromHistory, consumer, y);
        if (linesFromHistory < maximalLinesToProcess) {
            this.myScreenBuffer.processLines(0, maximalLinesToProcess - linesFromHistory, consumer, -linesFromHistory);
        }
    }

    public void clearHistory() {
        this.myHistoryBuffer.clearAll();
        this.fireModelChangeEvent();
    }

    void moveScreenLinesToHistory() {
        this.myLock.lock();
        try {
            this.myScreenBuffer.removeBottomEmptyLines(this.myScreenBuffer.getLineCount() - 1, this.myScreenBuffer.getLineCount());
            this.myScreenBuffer.moveTopLinesTo(this.myScreenBuffer.getLineCount(), this.myHistoryBuffer);
            if (this.myHistoryBuffer.getLineCount() > 0) {
                this.myHistoryBuffer.getLine(this.myHistoryBuffer.getLineCount() - 1).setWrapped(false);
            }
        } finally {
            this.myLock.unlock();
        }
    }

    @NotNull
    LinesBuffer getHistoryBufferOrBackup() {
        LinesBuffer linesBuffer = this.myUsingAlternateBuffer ? this.myHistoryBufferBackup : this.myHistoryBuffer;
        if (linesBuffer == null) {
            TerminalTextBuffer.$$$reportNull$$$0(14);
        }
        return linesBuffer;
    }

    @NotNull
    LinesBuffer getScreenBufferOrBackup() {
        LinesBuffer linesBuffer = this.myUsingAlternateBuffer ? this.myScreenBufferBackup : this.myScreenBuffer;
        if (linesBuffer == null) {
            TerminalTextBuffer.$$$reportNull$$$0(15);
        }
        return linesBuffer;
    }

    public int findScreenLineIndex(@NotNull TerminalLine line) {
        if (line == null) {
            TerminalTextBuffer.$$$reportNull$$$0(16);
        }
        return this.myScreenBuffer.findLineIndex(line);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        RuntimeException runtimeException;
        Object[] objectArray;
        Object[] objectArray2;
        int n2;
        String string;
        switch (n) {
            default: {
                string = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            }
            case 14: 
            case 15: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 14: 
            case 15: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "styleState";
                break;
            }
            case 3: 
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "pendingResize";
                break;
            }
            case 4: 
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "origin";
                break;
            }
            case 5: 
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "resizeHandler";
                break;
            }
            case 9: 
            case 11: {
                objectArray2 = objectArray3;
                objectArray3[0] = "str";
                break;
            }
            case 10: 
            case 16: {
                objectArray2 = objectArray3;
                objectArray3[0] = "line";
                break;
            }
            case 12: {
                objectArray2 = objectArray3;
                objectArray3[0] = "style";
                break;
            }
            case 13: {
                objectArray2 = objectArray3;
                objectArray3[0] = "consumer";
                break;
            }
            case 14: 
            case 15: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/TerminalTextBuffer";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/TerminalTextBuffer";
                break;
            }
            case 14: {
                objectArray = objectArray2;
                objectArray2[1] = "getHistoryBufferOrBackup";
                break;
            }
            case 15: {
                objectArray = objectArray2;
                objectArray2[1] = "getScreenBufferOrBackup";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                objectArray = objectArray;
                objectArray[2] = "resize";
                break;
            }
            case 6: 
            case 7: 
            case 8: {
                objectArray = objectArray;
                objectArray[2] = "doResize";
                break;
            }
            case 9: 
            case 11: 
            case 12: {
                objectArray = objectArray;
                objectArray[2] = "writeString";
                break;
            }
            case 10: {
                objectArray = objectArray;
                objectArray[2] = "addLine";
                break;
            }
            case 13: {
                objectArray = objectArray;
                objectArray[2] = "processScreenLines";
                break;
            }
            case 14: 
            case 15: {
                break;
            }
            case 16: {
                objectArray = objectArray;
                objectArray[2] = "findScreenLineIndex";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 14: 
            case 15: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

