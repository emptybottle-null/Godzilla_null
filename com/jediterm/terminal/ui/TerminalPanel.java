/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.google.common.collect.Lists;
import com.jediterm.terminal.CursorShape;
import com.jediterm.terminal.DefaultTerminalCopyPasteHandler;
import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.StyledTextConsumer;
import com.jediterm.terminal.SubstringFinder;
import com.jediterm.terminal.TerminalCopyPasteHandler;
import com.jediterm.terminal.TerminalDisplay;
import com.jediterm.terminal.TerminalOutputStream;
import com.jediterm.terminal.TerminalStarter;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.emulator.ColorPalette;
import com.jediterm.terminal.emulator.charset.CharacterSets;
import com.jediterm.terminal.emulator.mouse.MouseMode;
import com.jediterm.terminal.emulator.mouse.TerminalMouseListener;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.JediTerminal;
import com.jediterm.terminal.model.LinesBuffer;
import com.jediterm.terminal.model.SelectionUtil;
import com.jediterm.terminal.model.StyleState;
import com.jediterm.terminal.model.TerminalLine;
import com.jediterm.terminal.model.TerminalLineIntervalHighlighting;
import com.jediterm.terminal.model.TerminalModelListener;
import com.jediterm.terminal.model.TerminalSelection;
import com.jediterm.terminal.model.TerminalTextBuffer;
import com.jediterm.terminal.model.hyperlinks.LinkInfo;
import com.jediterm.terminal.ui.Cell;
import com.jediterm.terminal.ui.LineCellInterval;
import com.jediterm.terminal.ui.TerminalAction;
import com.jediterm.terminal.ui.TerminalActionProvider;
import com.jediterm.terminal.ui.TerminalCoordinates;
import com.jediterm.terminal.ui.TerminalPanelListener;
import com.jediterm.terminal.ui.UIUtil;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import com.jediterm.terminal.util.CharUtils;
import com.jediterm.terminal.util.Pair;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.TextHitInfo;
import java.awt.im.InputMethodRequests;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TerminalPanel
extends JComponent
implements TerminalDisplay,
TerminalActionProvider {
    private static final Logger LOG = Logger.getLogger(TerminalPanel.class);
    private static final long serialVersionUID = -1048763516632093014L;
    public static final double SCROLL_SPEED = 0.05;
    private Font myNormalFont;
    private Font myItalicFont;
    private Font myBoldFont;
    private Font myBoldItalicFont;
    private int myDescent;
    private int mySpaceBetweenLines;
    protected Dimension myCharSize;
    private boolean myMonospaced;
    protected Dimension myTermSize;
    private TerminalStarter myTerminalStarter;
    private MouseMode myMouseMode;
    private Point mySelectionStartPoint;
    private TerminalSelection mySelection;
    private final TerminalCopyPasteHandler myCopyPasteHandler;
    private TerminalPanelListener myTerminalPanelListener;
    private final SettingsProvider mySettingsProvider;
    private final TerminalTextBuffer myTerminalTextBuffer;
    private final StyleState myStyleState;
    private final TerminalCursor myCursor;
    private final BoundedRangeModel myBoundedRangeModel;
    private boolean myScrollingEnabled;
    protected int myClientScrollOrigin;
    private final List<KeyListener> myCustomKeyListeners;
    private String myWindowTitle;
    private TerminalActionProvider myNextActionProvider;
    private String myInputMethodUncommittedChars;
    private Timer myRepaintTimer;
    private AtomicInteger scrollDy;
    private AtomicBoolean needRepaint;
    private int myMaxFPS;
    private int myBlinkingPeriod;
    private TerminalCoordinates myCoordsAccessor;
    private String myCurrentPath;
    private SubstringFinder.FindResult myFindResult;
    private LinkInfo myHoveredHyperlink;
    private int myCursorType;
    private final TerminalKeyHandler myTerminalKeyHandler;
    private LinkInfo.HoverConsumer myLinkHoverConsumer;

    public TerminalPanel(@NotNull SettingsProvider settingsProvider, @NotNull TerminalTextBuffer terminalTextBuffer, @NotNull StyleState styleState) {
        if (settingsProvider == null) {
            TerminalPanel.$$$reportNull$$$0(0);
        }
        if (terminalTextBuffer == null) {
            TerminalPanel.$$$reportNull$$$0(1);
        }
        if (styleState == null) {
            TerminalPanel.$$$reportNull$$$0(2);
        }
        this.myDescent = 0;
        this.mySpaceBetweenLines = 0;
        this.myCharSize = new Dimension();
        this.myTermSize = new Dimension(80, 24);
        this.myTerminalStarter = null;
        this.myMouseMode = MouseMode.MOUSE_REPORTING_NONE;
        this.mySelectionStartPoint = null;
        this.mySelection = null;
        this.myCursor = new TerminalCursor();
        this.myBoundedRangeModel = new DefaultBoundedRangeModel(0, 80, 0, 80);
        this.myScrollingEnabled = true;
        this.myCustomKeyListeners = new CopyOnWriteArrayList<KeyListener>();
        this.myWindowTitle = "Terminal";
        this.scrollDy = new AtomicInteger(0);
        this.needRepaint = new AtomicBoolean(true);
        this.myMaxFPS = 50;
        this.myBlinkingPeriod = 500;
        this.myHoveredHyperlink = null;
        this.myCursorType = 0;
        this.myTerminalKeyHandler = new TerminalKeyHandler();
        this.mySettingsProvider = settingsProvider;
        this.myTerminalTextBuffer = terminalTextBuffer;
        this.myStyleState = styleState;
        this.myTermSize.width = terminalTextBuffer.getWidth();
        this.myTermSize.height = terminalTextBuffer.getHeight();
        this.myMaxFPS = this.mySettingsProvider.maxRefreshRate();
        this.myCopyPasteHandler = this.createCopyPasteHandler();
        this.updateScrolling(true);
        this.enableEvents(2056L);
        this.enableInputMethods(true);
        terminalTextBuffer.addModelListener(new TerminalModelListener(){

            @Override
            public void modelChanged() {
                TerminalPanel.this.repaint();
            }
        });
    }

    @NotNull
    protected TerminalCopyPasteHandler createCopyPasteHandler() {
        return new DefaultTerminalCopyPasteHandler();
    }

    public TerminalPanelListener getTerminalPanelListener() {
        return this.myTerminalPanelListener;
    }

    @Override
    public void repaint() {
        this.needRepaint.set(true);
    }

    private void doRepaint() {
        super.repaint();
    }

    @Deprecated
    protected void reinitFontAndResize() {
        this.initFont();
        this.sizeTerminalFromComponent();
    }

    protected void initFont() {
        this.myNormalFont = this.createFont();
        this.myBoldFont = this.myNormalFont.deriveFont(1);
        this.myItalicFont = this.myNormalFont.deriveFont(2);
        this.myBoldItalicFont = this.myNormalFont.deriveFont(3);
        this.establishFontMetrics();
    }

    public void init(@NotNull JScrollBar scrollBar) {
        if (scrollBar == null) {
            TerminalPanel.$$$reportNull$$$0(3);
        }
        this.initFont();
        this.setPreferredSize(new Dimension(this.getPixelWidth(), this.getPixelHeight()));
        this.setFocusable(true);
        this.enableInputMethods(true);
        this.setDoubleBuffered(true);
        this.setFocusTraversalKeysEnabled(false);
        this.addMouseMotionListener(new MouseMotionAdapter(){

            @Override
            public void mouseMoved(MouseEvent e) {
                TerminalPanel.this.handleHyperlinks(e.getPoint(), e.isControlDown());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!TerminalPanel.this.isLocalMouseAction(e)) {
                    return;
                }
                Point charCoords = TerminalPanel.this.panelToCharCoords(e.getPoint());
                if (TerminalPanel.this.mySelection == null) {
                    if (TerminalPanel.this.mySelectionStartPoint == null) {
                        TerminalPanel.this.mySelectionStartPoint = charCoords;
                    }
                    TerminalPanel.this.mySelection = new TerminalSelection(new Point(TerminalPanel.this.mySelectionStartPoint));
                }
                TerminalPanel.this.repaint();
                TerminalPanel.this.mySelection.updateEnd(charCoords);
                if (TerminalPanel.this.mySettingsProvider.copyOnSelect()) {
                    TerminalPanel.this.handleCopyOnSelect();
                }
                if (e.getPoint().y < 0) {
                    TerminalPanel.this.moveScrollBar((int)((double)e.getPoint().y * 0.05));
                }
                if (e.getPoint().y > TerminalPanel.this.getPixelHeight()) {
                    TerminalPanel.this.moveScrollBar((int)((double)(e.getPoint().y - TerminalPanel.this.getPixelHeight()) * 0.05));
                }
            }
        });
        this.addMouseWheelListener(e -> {
            if (this.isLocalMouseAction(e)) {
                this.handleMouseWheelEvent(e, scrollBar);
            }
        });
        this.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseExited(MouseEvent e) {
                if (TerminalPanel.this.myLinkHoverConsumer != null) {
                    TerminalPanel.this.myLinkHoverConsumer.onMouseExited();
                    TerminalPanel.this.myLinkHoverConsumer = null;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1 && e.getClickCount() == 1) {
                    TerminalPanel.this.mySelectionStartPoint = TerminalPanel.this.panelToCharCoords(e.getPoint());
                    TerminalPanel.this.mySelection = null;
                    TerminalPanel.this.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                TerminalPanel.this.requestFocusInWindow();
                TerminalPanel.this.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                HyperlinkStyle hyperlink;
                TerminalPanel.this.requestFocusInWindow();
                HyperlinkStyle hyperlinkStyle = hyperlink = TerminalPanel.this.isFollowLinkEvent(e) ? TerminalPanel.this.findHyperlink(e.getPoint()) : null;
                if (hyperlink != null) {
                    hyperlink.getLinkInfo().navigate();
                } else if (e.getButton() == 1 && TerminalPanel.this.isLocalMouseAction(e)) {
                    int count = e.getClickCount();
                    if (count != 1) {
                        if (count == 2) {
                            Point charCoords = TerminalPanel.this.panelToCharCoords(e.getPoint());
                            Point start = SelectionUtil.getPreviousSeparator(charCoords, TerminalPanel.this.myTerminalTextBuffer);
                            Point stop = SelectionUtil.getNextSeparator(charCoords, TerminalPanel.this.myTerminalTextBuffer);
                            TerminalPanel.this.mySelection = new TerminalSelection(start);
                            TerminalPanel.this.mySelection.updateEnd(stop);
                            if (TerminalPanel.this.mySettingsProvider.copyOnSelect()) {
                                TerminalPanel.this.handleCopyOnSelect();
                            }
                        } else if (count == 3) {
                            int endLine;
                            int startLine;
                            Point charCoords = TerminalPanel.this.panelToCharCoords(e.getPoint());
                            for (startLine = charCoords.y; startLine > -TerminalPanel.this.getScrollBuffer().getLineCount() && TerminalPanel.this.myTerminalTextBuffer.getLine(startLine - 1).isWrapped(); --startLine) {
                            }
                            for (endLine = charCoords.y; endLine < TerminalPanel.this.myTerminalTextBuffer.getHeight() && TerminalPanel.this.myTerminalTextBuffer.getLine(endLine).isWrapped(); ++endLine) {
                            }
                            TerminalPanel.this.mySelection = new TerminalSelection(new Point(0, startLine));
                            TerminalPanel.this.mySelection.updateEnd(new Point(TerminalPanel.this.myTermSize.width, endLine));
                            if (TerminalPanel.this.mySettingsProvider.copyOnSelect()) {
                                TerminalPanel.this.handleCopyOnSelect();
                            }
                        }
                    }
                } else if (e.getButton() == 2 && TerminalPanel.this.mySettingsProvider.pasteOnMiddleMouseClick() && TerminalPanel.this.isLocalMouseAction(e)) {
                    TerminalPanel.this.handlePasteSelection();
                } else if (e.getButton() == 3) {
                    HyperlinkStyle contextHyperlink = TerminalPanel.this.findHyperlink(e.getPoint());
                    JPopupMenu popup = TerminalPanel.this.createPopupMenu(contextHyperlink != null ? contextHyperlink.getLinkInfo() : null, e);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
                TerminalPanel.this.repaint();
            }
        });
        this.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                TerminalPanel.this.sizeTerminalFromComponent();
            }
        });
        this.addFocusListener(new FocusAdapter(){

            @Override
            public void focusGained(FocusEvent e) {
                TerminalPanel.this.myCursor.cursorChanged();
            }

            @Override
            public void focusLost(FocusEvent e) {
                TerminalPanel.this.myCursor.cursorChanged();
                TerminalPanel.this.handleHyperlinks(e.getComponent(), false);
            }
        });
        this.myBoundedRangeModel.addChangeListener(new ChangeListener(){

            @Override
            public void stateChanged(ChangeEvent e) {
                TerminalPanel.this.myClientScrollOrigin = TerminalPanel.this.myBoundedRangeModel.getValue();
                TerminalPanel.this.repaint();
            }
        });
        this.createRepaintTimer();
    }

    private boolean isFollowLinkEvent(@NotNull MouseEvent e) {
        if (e == null) {
            TerminalPanel.$$$reportNull$$$0(4);
        }
        return this.myCursorType == 12 && e.getButton() == 1;
    }

    protected void handleMouseWheelEvent(@NotNull MouseWheelEvent e, @NotNull JScrollBar scrollBar) {
        if (e == null) {
            TerminalPanel.$$$reportNull$$$0(5);
        }
        if (scrollBar == null) {
            TerminalPanel.$$$reportNull$$$0(6);
        }
        if (e.isShiftDown() || e.getUnitsToScroll() == 0 || Math.abs(e.getPreciseWheelRotation()) < 0.01) {
            return;
        }
        this.moveScrollBar(e.getUnitsToScroll());
        e.consume();
    }

    private void handleHyperlinks(@NotNull Point panelPoint, boolean isControlDown) {
        Cell cell;
        HyperlinkStyle linkStyle;
        LinkInfo.HoverConsumer linkHoverConsumer;
        if (panelPoint == null) {
            TerminalPanel.$$$reportNull$$$0(7);
        }
        LinkInfo.HoverConsumer hoverConsumer = linkHoverConsumer = (linkStyle = this.findHyperlink(cell = this.panelPointToCell(panelPoint))) != null ? linkStyle.getLinkInfo().getHoverConsumer() : null;
        if (linkHoverConsumer != this.myLinkHoverConsumer) {
            if (this.myLinkHoverConsumer != null) {
                this.myLinkHoverConsumer.onMouseExited();
            }
            if (linkHoverConsumer != null) {
                LineCellInterval lineCellInterval = this.findIntervalWithStyle(cell, linkStyle);
                linkHoverConsumer.onMouseEntered(this, this.getBounds(lineCellInterval));
            }
        }
        this.myLinkHoverConsumer = linkHoverConsumer;
        if (linkStyle != null && (linkStyle.getHighlightMode() == HyperlinkStyle.HighlightMode.ALWAYS || linkStyle.getHighlightMode() == HyperlinkStyle.HighlightMode.HOVER && isControlDown)) {
            this.updateCursor(12);
            this.myHoveredHyperlink = linkStyle.getLinkInfo();
            return;
        }
        this.myHoveredHyperlink = null;
        if (this.myCursorType != 0) {
            this.updateCursor(0);
            this.repaint();
        }
    }

    @NotNull
    private LineCellInterval findIntervalWithStyle(@NotNull Cell initialCell, @NotNull HyperlinkStyle style) {
        int endColumn;
        int startColumn;
        if (initialCell == null) {
            TerminalPanel.$$$reportNull$$$0(8);
        }
        if (style == null) {
            TerminalPanel.$$$reportNull$$$0(9);
        }
        for (startColumn = initialCell.getColumn(); startColumn > 0 && style == this.myTerminalTextBuffer.getStyleAt(startColumn - 1, initialCell.getLine()); --startColumn) {
        }
        for (endColumn = initialCell.getColumn(); endColumn < this.myTerminalTextBuffer.getWidth() - 1 && style == this.myTerminalTextBuffer.getStyleAt(endColumn + 1, initialCell.getLine()); ++endColumn) {
        }
        return new LineCellInterval(initialCell.getLine(), startColumn, endColumn);
    }

    private void handleHyperlinks(Component component, boolean controlDown) {
        PointerInfo a = MouseInfo.getPointerInfo();
        if (a != null) {
            Point b = a.getLocation();
            SwingUtilities.convertPointFromScreen(b, component);
            this.handleHyperlinks(b, controlDown);
        }
    }

    @Nullable
    private HyperlinkStyle findHyperlink(@NotNull Point p) {
        if (p == null) {
            TerminalPanel.$$$reportNull$$$0(10);
        }
        return this.findHyperlink(this.panelPointToCell(p));
    }

    @Nullable
    private HyperlinkStyle findHyperlink(@Nullable Cell cell) {
        TextStyle style;
        if (cell != null && cell.getColumn() >= 0 && cell.getColumn() < this.myTerminalTextBuffer.getWidth() && cell.getLine() >= -this.myTerminalTextBuffer.getHistoryLinesCount() && cell.getLine() <= this.myTerminalTextBuffer.getHeight() && (style = this.myTerminalTextBuffer.getStyleAt(cell.getColumn(), cell.getLine())) instanceof HyperlinkStyle) {
            return (HyperlinkStyle)style;
        }
        return null;
    }

    private void updateCursor(int cursorType) {
        if (cursorType != this.myCursorType) {
            this.myCursorType = cursorType;
            this.setCursor(new Cursor(this.myCursorType));
        }
    }

    private void createRepaintTimer() {
        if (this.myRepaintTimer != null) {
            this.myRepaintTimer.stop();
        }
        this.myRepaintTimer = new Timer(1000 / this.myMaxFPS, new WeakRedrawTimer(this));
        this.myRepaintTimer.start();
    }

    public boolean isLocalMouseAction(MouseEvent e) {
        return this.mySettingsProvider.forceActionOnMouseReporting() || this.isMouseReporting() == e.isShiftDown();
    }

    public boolean isRemoteMouseAction(MouseEvent e) {
        return this.isMouseReporting() && !e.isShiftDown();
    }

    protected boolean isRetina() {
        return UIUtil.isRetina();
    }

    public void setBlinkingPeriod(int blinkingPeriod) {
        this.myBlinkingPeriod = blinkingPeriod;
    }

    public void setCoordAccessor(TerminalCoordinates coordAccessor) {
        this.myCoordsAccessor = coordAccessor;
    }

    public void setFindResult(SubstringFinder.FindResult findResult) {
        this.myFindResult = findResult;
        this.repaint();
    }

    public SubstringFinder.FindResult getFindResult() {
        return this.myFindResult;
    }

    public SubstringFinder.FindResult.FindItem selectPrevFindResultItem() {
        return this.selectPrevOrNextFindResultItem(false);
    }

    public SubstringFinder.FindResult.FindItem selectNextFindResultItem() {
        return this.selectPrevOrNextFindResultItem(true);
    }

    protected SubstringFinder.FindResult.FindItem selectPrevOrNextFindResultItem(boolean next) {
        if (this.myFindResult != null) {
            SubstringFinder.FindResult.FindItem item;
            SubstringFinder.FindResult.FindItem findItem = item = next ? this.myFindResult.nextFindItem() : this.myFindResult.prevFindItem();
            if (item != null) {
                this.mySelection = new TerminalSelection(new Point(item.getStart().x, item.getStart().y - this.myTerminalTextBuffer.getHistoryLinesCount()), new Point(item.getEnd().x, item.getEnd().y - this.myTerminalTextBuffer.getHistoryLinesCount()));
                if (this.mySelection.getStart().y < this.getTerminalTextBuffer().getHeight() / 2) {
                    this.myBoundedRangeModel.setValue(this.mySelection.getStart().y - this.getTerminalTextBuffer().getHeight() / 2);
                } else {
                    this.myBoundedRangeModel.setValue(0);
                }
                this.repaint();
                return item;
            }
        }
        return null;
    }

    @Override
    public void terminalMouseModeSet(MouseMode mode) {
        this.myMouseMode = mode;
    }

    private boolean isMouseReporting() {
        return this.myMouseMode != MouseMode.MOUSE_REPORTING_NONE;
    }

    private void scrollToBottom() {
        this.myBoundedRangeModel.setValue(this.myTermSize.height);
    }

    private void pageUp() {
        this.moveScrollBar(-this.myTermSize.height);
    }

    private void pageDown() {
        this.moveScrollBar(this.myTermSize.height);
    }

    private void scrollUp() {
        this.moveScrollBar(-1);
    }

    private void scrollDown() {
        this.moveScrollBar(1);
    }

    private void moveScrollBar(int k) {
        this.myBoundedRangeModel.setValue(this.myBoundedRangeModel.getValue() + k);
    }

    protected Font createFont() {
        return this.mySettingsProvider.getTerminalFont();
    }

    @NotNull
    private Point panelToCharCoords(Point p) {
        Cell cell = this.panelPointToCell(p);
        return new Point(cell.getColumn(), cell.getLine());
    }

    @NotNull
    private Cell panelPointToCell(@NotNull Point p) {
        if (p == null) {
            TerminalPanel.$$$reportNull$$$0(11);
        }
        int y = Math.min(p.y / this.myCharSize.height, this.getRowCount() - 1) + this.myClientScrollOrigin;
        TerminalLine buffer = this.myTerminalTextBuffer.getLine(y);
        int bufferLen = buffer.getText().length();
        int insetX = p.x - this.getInsetX();
        int _x = 0;
        int x = 0;
        for (int i = 0; i < bufferLen && insetX > _x; ++i) {
            char c = buffer.charAt(i);
            if (insetX <= (_x += this.getGraphics().getFontMetrics(this.getFontToDisplay(c, TextStyle.EMPTY)).charWidth(c))) continue;
            ++x;
        }
        x = Math.min(x, this.getColumnCount() - 1);
        x = Math.max(0, x);
        return new Cell(y, x);
    }

    private void copySelection(@Nullable Point selectionStart, @Nullable Point selectionEnd, boolean useSystemSelectionClipboardIfAvailable) {
        if (selectionStart == null || selectionEnd == null) {
            return;
        }
        String selectionText = SelectionUtil.getSelectionText(selectionStart, selectionEnd, this.myTerminalTextBuffer);
        if (selectionText.length() != 0) {
            this.myCopyPasteHandler.setContents(selectionText, useSystemSelectionClipboardIfAvailable);
        }
    }

    private void pasteFromClipboard(boolean useSystemSelectionClipboardIfAvailable) {
        String text = this.myCopyPasteHandler.getContents(useSystemSelectionClipboardIfAvailable);
        if (text == null) {
            return;
        }
        try {
            if (!UIUtil.isWindows) {
                text = text.replace("\r\n", "\n");
            }
            text = text.replace('\n', '\r');
            this.myTerminalStarter.sendString(text);
        } catch (RuntimeException e) {
            LOG.info(e);
        }
    }

    @Nullable
    private String getClipboardString() {
        return this.myCopyPasteHandler.getContents(false);
    }

    protected void drawImage(Graphics2D gfx, BufferedImage image, int x, int y, ImageObserver observer) {
        gfx.drawImage(image, x, y, image.getWidth(), image.getHeight(), observer);
    }

    protected BufferedImage createBufferedImage(int width, int height) {
        return new BufferedImage(width, height, 1);
    }

    @Nullable
    public Dimension getTerminalSizeFromComponent() {
        int newWidth = (this.getWidth() - this.getInsetX()) / this.myCharSize.width;
        int newHeight = this.getHeight() / this.myCharSize.height;
        return newHeight > 0 && newWidth > 0 ? new Dimension(newWidth, newHeight) : null;
    }

    private void sizeTerminalFromComponent() {
        Dimension newSize;
        if (this.myTerminalStarter != null && (newSize = this.getTerminalSizeFromComponent()) != null) {
            JediTerminal.ensureTermMinimumSize(newSize);
            if (!this.myTermSize.equals(newSize)) {
                this.myTerminalStarter.postResize(newSize, RequestOrigin.User);
            }
        }
    }

    public void setTerminalStarter(TerminalStarter terminalStarter) {
        this.myTerminalStarter = terminalStarter;
        this.sizeTerminalFromComponent();
    }

    public void addCustomKeyListener(@NotNull KeyListener keyListener) {
        if (keyListener == null) {
            TerminalPanel.$$$reportNull$$$0(12);
        }
        this.myCustomKeyListeners.add(keyListener);
    }

    public void removeCustomKeyListener(@NotNull KeyListener keyListener) {
        if (keyListener == null) {
            TerminalPanel.$$$reportNull$$$0(13);
        }
        this.myCustomKeyListeners.remove(keyListener);
    }

    @Override
    public void requestResize(@NotNull Dimension newSize, RequestOrigin origin, int cursorX, int cursorY, JediTerminal.ResizeHandler resizeHandler) {
        if (newSize == null) {
            TerminalPanel.$$$reportNull$$$0(14);
        }
        if (!newSize.equals(this.myTermSize)) {
            this.myTerminalTextBuffer.resize(newSize, origin, cursorX, cursorY, resizeHandler, this.mySelection);
            this.myTermSize = (Dimension)newSize.clone();
            Dimension pixelDimension = new Dimension(this.getPixelWidth(), this.getPixelHeight());
            this.setPreferredSize(pixelDimension);
            if (this.myTerminalPanelListener != null) {
                this.myTerminalPanelListener.onPanelResize(origin);
            }
            SwingUtilities.invokeLater(() -> this.updateScrolling(true));
        }
    }

    public void setTerminalPanelListener(TerminalPanelListener terminalPanelListener) {
        this.myTerminalPanelListener = terminalPanelListener;
    }

    private void establishFontMetrics() {
        BufferedImage img = this.createBufferedImage(1, 1);
        Graphics2D graphics = img.createGraphics();
        graphics.setFont(this.myNormalFont);
        float lineSpacing = this.mySettingsProvider.getLineSpacing();
        FontMetrics fo = graphics.getFontMetrics();
        this.myCharSize.width = fo.charWidth('W');
        int fontMetricsHeight = fo.getHeight();
        this.myCharSize.height = (int)Math.ceil((float)fontMetricsHeight * lineSpacing);
        this.mySpaceBetweenLines = Math.max(0, (this.myCharSize.height - fontMetricsHeight) / 2 * 2);
        this.myDescent = fo.getDescent();
        if (LOG.isDebugEnabled()) {
            int oldCharHeight = fontMetricsHeight + (int)(lineSpacing * 2.0f) + 2;
            int oldDescent = fo.getDescent() + (int)lineSpacing;
            LOG.debug("charHeight=" + oldCharHeight + "->" + this.myCharSize.height + ", descent=" + oldDescent + "->" + this.myDescent);
        }
        this.myMonospaced = TerminalPanel.isMonospaced(fo);
        if (!this.myMonospaced) {
            LOG.info("WARNING: Font " + this.myNormalFont.getName() + " is non-monospaced");
        }
        img.flush();
        graphics.dispose();
    }

    private static boolean isMonospaced(FontMetrics fontMetrics) {
        boolean isMonospaced = true;
        int charWidth = -1;
        for (int codePoint = 0; codePoint < 128; ++codePoint) {
            char character;
            if (!Character.isValidCodePoint(codePoint) || !TerminalPanel.isWordCharacter(character = (char)codePoint)) continue;
            int w = fontMetrics.charWidth(character);
            if (charWidth != -1) {
                if (w == charWidth) continue;
                isMonospaced = false;
                break;
            }
            charWidth = w;
        }
        return isMonospaced;
    }

    private static boolean isWordCharacter(char character) {
        return Character.isLetterOrDigit(character);
    }

    protected void setupAntialiasing(Graphics graphics) {
        if (graphics instanceof Graphics2D) {
            Graphics2D myGfx = (Graphics2D)graphics;
            Object mode = this.mySettingsProvider.useAntialiasing() ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, mode);
            myGfx.setRenderingHints(hints);
        }
    }

    @Override
    public Color getBackground() {
        return this.getPalette().getBackground(this.myStyleState.getBackground());
    }

    @Override
    public Color getForeground() {
        return this.getPalette().getForeground(this.myStyleState.getForeground());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintComponent(Graphics g) {
        final Graphics2D gfx = (Graphics2D)g;
        this.setupAntialiasing(gfx);
        gfx.setColor(this.getBackground());
        gfx.fillRect(0, 0, this.getWidth(), this.getHeight());
        try {
            this.myTerminalTextBuffer.lock();
            this.updateScrolling(false);
            this.myTerminalTextBuffer.processHistoryAndScreenLines(this.myClientScrollOrigin, this.myTermSize.height, new StyledTextConsumer(){
                final int columnCount;
                {
                    this.columnCount = TerminalPanel.this.getColumnCount();
                }

                @Override
                public void consume(int x, int y, @NotNull TextStyle style, @NotNull CharBuffer characters, int startRow) {
                    Pair<Integer, Integer> interval;
                    List<Pair<Integer, Integer>> ranges;
                    if (style == null) {
                        7.$$$reportNull$$$0(0);
                    }
                    if (characters == null) {
                        7.$$$reportNull$$$0(1);
                    }
                    int row = y - startRow;
                    TerminalPanel.this.drawCharacters(x, row, style, characters, gfx, false);
                    if (TerminalPanel.this.myFindResult != null && (ranges = TerminalPanel.this.myFindResult.getRanges(characters)) != null) {
                        for (Pair<Integer, Integer> range : ranges) {
                            TextStyle foundPatternStyle = TerminalPanel.this.getFoundPattern(style);
                            CharBuffer foundPatternChars = characters.subBuffer(range);
                            TerminalPanel.this.drawCharacters(x + (Integer)range.first, row, foundPatternStyle, foundPatternChars, gfx);
                        }
                    }
                    if (TerminalPanel.this.mySelection != null && (interval = TerminalPanel.this.mySelection.intersect(x, row + TerminalPanel.this.myClientScrollOrigin, characters.length())) != null) {
                        TextStyle selectionStyle = TerminalPanel.this.getSelectionStyle(style);
                        CharBuffer selectionChars = characters.subBuffer((Integer)interval.first - x, (Integer)interval.second);
                        TerminalPanel.this.drawCharacters((Integer)interval.first, row, selectionStyle, selectionChars, gfx);
                    }
                }

                @Override
                public void consumeNul(int x, int y, int nulIndex, TextStyle style, CharBuffer characters, int startRow) {
                    Pair<Integer, Integer> interval;
                    int row = y - startRow;
                    if (TerminalPanel.this.mySelection != null && (interval = TerminalPanel.this.mySelection.intersect(nulIndex, row + TerminalPanel.this.myClientScrollOrigin, this.columnCount - nulIndex)) != null) {
                        TextStyle selectionStyle = TerminalPanel.this.getSelectionStyle(style);
                        TerminalPanel.this.drawCharacters(x, row, selectionStyle, characters, gfx);
                        return;
                    }
                    TerminalPanel.this.drawCharacters(x, row, style, characters, gfx);
                }

                @Override
                public void consumeQueue(int x, int y, int nulIndex, int startRow) {
                    if (x < this.columnCount) {
                        this.consumeNul(x, y, nulIndex, TextStyle.EMPTY, new CharBuffer(' ', this.columnCount - x), startRow);
                    }
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
                    objectArray[1] = "com/jediterm/terminal/ui/TerminalPanel$7";
                    objectArray[2] = "consume";
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
                }
            });
            int cursorY = this.myCursor.getCoordY();
            if (this.myClientScrollOrigin + this.getRowCount() > cursorY && !this.hasUncommittedChars()) {
                int cursorX = this.myCursor.getCoordX();
                Pair<Character, TextStyle> sc = this.myTerminalTextBuffer.getStyledCharAt(cursorX, cursorY);
                String cursorChar = "" + sc.first;
                if (Character.isHighSurrogate(((Character)sc.first).charValue())) {
                    cursorChar = cursorChar + this.myTerminalTextBuffer.getStyledCharAt((int)(cursorX + 1), (int)cursorY).first;
                }
                TextStyle normalStyle = sc.second != null ? (TextStyle)sc.second : this.myStyleState.getCurrent();
                TextStyle selectionStyle = this.getSelectionStyle(normalStyle);
                boolean inSelection = this.inSelection(cursorX, cursorY);
                this.myCursor.drawCursor(cursorChar, gfx, inSelection ? selectionStyle : normalStyle);
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
        this.drawInputMethodUncommitedChars(gfx);
        this.drawMargins(gfx, this.getWidth(), this.getHeight());
    }

    @NotNull
    private TextStyle getSelectionStyle(@NotNull TextStyle style) {
        if (style == null) {
            TerminalPanel.$$$reportNull$$$0(15);
        }
        if (this.mySettingsProvider.useInverseSelectionColor()) {
            return this.getInversedStyle(style);
        }
        TextStyle.Builder builder = style.toBuilder();
        TextStyle mySelectionStyle = this.mySettingsProvider.getSelectionColor();
        builder.setBackground(mySelectionStyle.getBackground());
        builder.setForeground(mySelectionStyle.getForeground());
        if (builder instanceof HyperlinkStyle.Builder) {
            HyperlinkStyle hyperlinkStyle = ((HyperlinkStyle.Builder)builder).build(true);
            if (hyperlinkStyle == null) {
                TerminalPanel.$$$reportNull$$$0(16);
            }
            return hyperlinkStyle;
        }
        TextStyle textStyle = builder.build();
        if (textStyle == null) {
            TerminalPanel.$$$reportNull$$$0(17);
        }
        return textStyle;
    }

    @NotNull
    private TextStyle getFoundPattern(@NotNull TextStyle style) {
        if (style == null) {
            TerminalPanel.$$$reportNull$$$0(18);
        }
        TextStyle.Builder builder = style.toBuilder();
        TextStyle foundPattern = this.mySettingsProvider.getFoundPatternColor();
        builder.setBackground(foundPattern.getBackground());
        builder.setForeground(foundPattern.getForeground());
        TextStyle textStyle = builder.build();
        if (textStyle == null) {
            TerminalPanel.$$$reportNull$$$0(19);
        }
        return textStyle;
    }

    private void drawInputMethodUncommitedChars(Graphics2D gfx) {
        if (this.hasUncommittedChars()) {
            int xCoord = this.computexCoord(this.myCursor.getCoordX() + 1, this.myCursor.getCoordY()) + this.getInsetX();
            int y = this.myCursor.getCoordY() + 1;
            int yCoord = y * this.myCharSize.height - 3;
            int len = this.computexCoordByCharBuffer(0, this.myInputMethodUncommittedChars.length(), new CharBuffer(this.myInputMethodUncommittedChars));
            gfx.setColor(this.getBackground());
            gfx.fillRect(xCoord, (y - 1) * this.myCharSize.height - 3, len, this.myCharSize.height);
            gfx.setColor(this.getForeground());
            gfx.setFont(this.myNormalFont);
            gfx.drawString(this.myInputMethodUncommittedChars, xCoord, yCoord);
            Stroke saved = gfx.getStroke();
            BasicStroke dotted = new BasicStroke(1.0f, 1, 1, 0.0f, new float[]{0.0f, 2.0f, 0.0f, 2.0f}, 0.0f);
            gfx.setStroke(dotted);
            gfx.drawLine(xCoord, yCoord, xCoord + len, yCoord);
            gfx.setStroke(saved);
        }
    }

    private boolean hasUncommittedChars() {
        return this.myInputMethodUncommittedChars != null && this.myInputMethodUncommittedChars.length() > 0;
    }

    private boolean inSelection(int x, int y) {
        return this.mySelection != null && this.mySelection.contains(new Point(x, y));
    }

    @Override
    public void processKeyEvent(KeyEvent e) {
        this.handleKeyEvent(e);
        this.handleHyperlinks(e.getComponent(), e.isControlDown());
    }

    private int computexCoord(int coordX, int coordY) {
        int xCoord = 0;
        Graphics gfx = this.getGraphics();
        char[] chars = this.myTerminalTextBuffer.getLine(this.myClientScrollOrigin + coordY).getText().toCharArray();
        for (int i = 0; i < coordX; ++i) {
            char c = i < chars.length ? chars[i] : (char)' ';
            Font font = this.getFontToDisplay(c, TextStyle.EMPTY);
            xCoord += gfx.getFontMetrics(font).charWidth(c);
        }
        return xCoord;
    }

    private int computexCoordByCharBuffer(int start, int end, CharBuffer buf) {
        int _width = 0;
        int textLength = end - start;
        Graphics gfx = this.getGraphics();
        for (int i = 0; i < textLength; ++i) {
            char c = buf.charAt(i);
            Font font = this.getFontToDisplay(c, TextStyle.EMPTY);
            _width += gfx.getFontMetrics(font).charWidth(c);
        }
        return _width;
    }

    public void handleKeyEvent(@NotNull KeyEvent e) {
        block4: {
            int id;
            block3: {
                if (e == null) {
                    TerminalPanel.$$$reportNull$$$0(20);
                }
                if ((id = e.getID()) != 401) break block3;
                for (KeyListener keyListener : this.myCustomKeyListeners) {
                    keyListener.keyPressed(e);
                }
                break block4;
            }
            if (id != 400) break block4;
            for (KeyListener keyListener : this.myCustomKeyListeners) {
                keyListener.keyTyped(e);
            }
        }
    }

    public int getPixelWidth() {
        return this.myCharSize.width * this.myTermSize.width + this.getInsetX();
    }

    public int getPixelHeight() {
        return this.myCharSize.height * this.myTermSize.height;
    }

    @Override
    public int getColumnCount() {
        return this.myTermSize.width;
    }

    @Override
    public int getRowCount() {
        return this.myTermSize.height;
    }

    public String getWindowTitle() {
        return this.myWindowTitle;
    }

    protected int getInsetX() {
        return 4;
    }

    public void addTerminalMouseListener(final TerminalMouseListener listener) {
        this.addMouseListener(new MouseAdapter(){

            @Override
            public void mousePressed(MouseEvent e) {
                if (TerminalPanel.this.mySettingsProvider.enableMouseReporting() && TerminalPanel.this.isRemoteMouseAction(e)) {
                    Point p = TerminalPanel.this.panelToCharCoords(e.getPoint());
                    listener.mousePressed(p.x, p.y, e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (TerminalPanel.this.mySettingsProvider.enableMouseReporting() && TerminalPanel.this.isRemoteMouseAction(e)) {
                    Point p = TerminalPanel.this.panelToCharCoords(e.getPoint());
                    listener.mouseReleased(p.x, p.y, e);
                }
            }
        });
        this.addMouseWheelListener(new MouseWheelListener(){

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (TerminalPanel.this.mySettingsProvider.enableMouseReporting() && TerminalPanel.this.isRemoteMouseAction(e)) {
                    TerminalPanel.this.mySelection = null;
                    Point p = TerminalPanel.this.panelToCharCoords(e.getPoint());
                    listener.mouseWheelMoved(p.x, p.y, e);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){

            @Override
            public void mouseMoved(MouseEvent e) {
                if (TerminalPanel.this.mySettingsProvider.enableMouseReporting() && TerminalPanel.this.isRemoteMouseAction(e)) {
                    Point p = TerminalPanel.this.panelToCharCoords(e.getPoint());
                    listener.mouseMoved(p.x, p.y, e);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (TerminalPanel.this.mySettingsProvider.enableMouseReporting() && TerminalPanel.this.isRemoteMouseAction(e)) {
                    Point p = TerminalPanel.this.panelToCharCoords(e.getPoint());
                    listener.mouseDragged(p.x, p.y, e);
                }
            }
        });
    }

    @NotNull
    KeyListener getTerminalKeyListener() {
        TerminalKeyHandler terminalKeyHandler = this.myTerminalKeyHandler;
        if (terminalKeyHandler == null) {
            TerminalPanel.$$$reportNull$$$0(21);
        }
        return terminalKeyHandler;
    }

    private int getBlinkingPeriod() {
        if (this.myBlinkingPeriod != this.mySettingsProvider.caretBlinkingMs()) {
            this.setBlinkingPeriod(this.mySettingsProvider.caretBlinkingMs());
        }
        return this.myBlinkingPeriod;
    }

    protected void drawImage(Graphics2D g, BufferedImage image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
        g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }

    @NotNull
    private TextStyle getInversedStyle(@NotNull TextStyle style) {
        if (style == null) {
            TerminalPanel.$$$reportNull$$$0(22);
        }
        TextStyle.Builder builder = new TextStyle.Builder(style);
        builder.setOption(TextStyle.Option.INVERSE, !style.hasOption(TextStyle.Option.INVERSE));
        if (style.getForeground() == null) {
            builder.setForeground(this.myStyleState.getForeground());
        }
        if (style.getBackground() == null) {
            builder.setBackground(this.myStyleState.getBackground());
        }
        TextStyle textStyle = builder.build();
        if (textStyle == null) {
            TerminalPanel.$$$reportNull$$$0(23);
        }
        return textStyle;
    }

    private void drawCharacters(int x, int y, TextStyle style, CharBuffer buf, Graphics2D gfx) {
        this.drawCharacters(x, y, style, buf, gfx, true);
    }

    private void drawCharacters(int x, int y, TextStyle style, CharBuffer buf, Graphics2D gfx, boolean includeSpaceBetweenLines) {
        HyperlinkStyle hyperlinkStyle;
        int xCoord = this.computexCoord(x, y) + this.getInsetX();
        int yCoord = y * this.myCharSize.height + (includeSpaceBetweenLines ? 0 : this.mySpaceBetweenLines / 2);
        if (xCoord < 0 || xCoord > this.getWidth() || yCoord < 0 || yCoord > this.getHeight()) {
            return;
        }
        int textLength = buf.length();
        int height = Math.min(this.myCharSize.height - (includeSpaceBetweenLines ? 0 : this.mySpaceBetweenLines), this.getHeight() - yCoord);
        int width = Math.min(this.computexCoordByCharBuffer(0, textLength, buf), this.getWidth() - xCoord);
        if (style instanceof HyperlinkStyle && ((hyperlinkStyle = (HyperlinkStyle)style).getHighlightMode() == HyperlinkStyle.HighlightMode.ALWAYS || this.isHoveredHyperlink(hyperlinkStyle) && hyperlinkStyle.getHighlightMode() == HyperlinkStyle.HighlightMode.HOVER)) {
            style = hyperlinkStyle.getHighlightStyle();
        }
        Color backgroundColor = this.getPalette().getBackground(this.myStyleState.getBackground(style.getBackgroundForRun()));
        gfx.setColor(backgroundColor);
        gfx.fillRect(xCoord, yCoord, width, height);
        if (buf.isNul()) {
            return;
        }
        this.drawChars(x, y, buf, style, gfx);
        gfx.setColor(this.getStyleForeground(style));
        if (style.hasOption(TextStyle.Option.UNDERLINED)) {
            int baseLine = (y + 1) * this.myCharSize.height - this.mySpaceBetweenLines / 2 - this.myDescent;
            int lineY = baseLine + 3;
            gfx.drawLine(xCoord, lineY, this.computexCoord(x + textLength, lineY) + this.getInsetX(), lineY);
        }
    }

    private boolean isHoveredHyperlink(@NotNull HyperlinkStyle link) {
        if (link == null) {
            TerminalPanel.$$$reportNull$$$0(24);
        }
        return this.myHoveredHyperlink == link.getLinkInfo();
    }

    private void drawChars(int x, int y, CharBuffer buf, TextStyle style, Graphics2D gfx) {
        int blockLen = 1;
        int offset = 0;
        int drawCharsOffset = 0;
        CharBuffer renderingBuffer = this.mySettingsProvider.DECCompatibilityMode() && style.hasOption(TextStyle.Option.BOLD) ? CharUtils.heavyDecCompatibleBuffer(buf) : buf;
        while (offset + blockLen <= buf.length()) {
            Font font = this.getFontToDisplay(buf.charAt(offset + blockLen - 1), style);
            gfx.setFont(font);
            int descent = gfx.getFontMetrics(font).getDescent();
            int baseLine = (y + 1) * this.myCharSize.height - this.mySpaceBetweenLines / 2 - descent;
            int xCoord = this.computexCoord(x + drawCharsOffset, y) + this.getInsetX();
            int yCoord = y * this.myCharSize.height + this.mySpaceBetweenLines / 2;
            gfx.setClip(xCoord, yCoord, this.getWidth() - xCoord, this.getHeight() - yCoord);
            gfx.setColor(this.getStyleForeground(style));
            gfx.drawChars(renderingBuffer.getBuf(), buf.getStart() + offset, blockLen, xCoord, baseLine);
            drawCharsOffset += blockLen;
            offset += blockLen;
            blockLen = 1;
        }
        gfx.setClip(null);
    }

    @NotNull
    private Color getStyleForeground(@NotNull TextStyle style) {
        if (style == null) {
            TerminalPanel.$$$reportNull$$$0(25);
        }
        Color foreground = this.getPalette().getForeground(this.myStyleState.getForeground(style.getForegroundForRun()));
        if (style.hasOption(TextStyle.Option.DIM)) {
            Color background = this.getPalette().getBackground(this.myStyleState.getBackground(style.getBackgroundForRun()));
            foreground = new Color((foreground.getRed() + background.getRed()) / 2, (foreground.getGreen() + background.getGreen()) / 2, (foreground.getBlue() + background.getBlue()) / 2, foreground.getAlpha());
        }
        Color color = foreground;
        if (color == null) {
            TerminalPanel.$$$reportNull$$$0(26);
        }
        return color;
    }

    protected Font getFontToDisplay(char c, TextStyle style) {
        boolean bold = style.hasOption(TextStyle.Option.BOLD);
        boolean italic = style.hasOption(TextStyle.Option.ITALIC);
        if (bold && this.mySettingsProvider.DECCompatibilityMode() && CharacterSets.isDecBoxChar(c)) {
            return this.myNormalFont;
        }
        return bold ? (italic ? this.myBoldItalicFont : this.myBoldFont) : (italic ? this.myItalicFont : this.myNormalFont);
    }

    private ColorPalette getPalette() {
        return this.mySettingsProvider.getTerminalColorPalette();
    }

    private void drawMargins(Graphics2D gfx, int width, int height) {
        gfx.setColor(this.getBackground());
        gfx.fillRect(0, height, this.getWidth(), this.getHeight() - height);
        gfx.fillRect(width, 0, this.getWidth() - width, this.getHeight());
    }

    @Override
    public void scrollArea(int scrollRegionTop, int scrollRegionSize, int dy) {
        this.scrollDy.addAndGet(dy);
        this.mySelection = null;
    }

    private void updateScrolling(boolean forceUpdate) {
        int dy = this.scrollDy.getAndSet(0);
        if (dy == 0 && !forceUpdate) {
            return;
        }
        if (this.myScrollingEnabled) {
            int value = this.myBoundedRangeModel.getValue();
            int historyLineCount = this.myTerminalTextBuffer.getHistoryLinesCount();
            if (value == 0) {
                this.myBoundedRangeModel.setRangeProperties(0, this.myTermSize.height, -historyLineCount, this.myTermSize.height, false);
            } else {
                this.myBoundedRangeModel.setRangeProperties(Math.min(Math.max(value + dy, -historyLineCount), this.myTermSize.height), this.myTermSize.height, -historyLineCount, this.myTermSize.height, false);
            }
        } else {
            this.myBoundedRangeModel.setRangeProperties(0, this.myTermSize.height, 0, this.myTermSize.height, false);
        }
    }

    @Override
    public void setCursor(int x, int y) {
        this.myCursor.setX(x);
        this.myCursor.setY(y);
    }

    @Override
    public void setCursorShape(CursorShape shape) {
        this.myCursor.setShape(shape);
        switch (shape) {
            case STEADY_BLOCK: 
            case STEADY_UNDERLINE: 
            case STEADY_VERTICAL_BAR: {
                this.myCursor.myBlinking = false;
                break;
            }
            case BLINK_BLOCK: 
            case BLINK_UNDERLINE: 
            case BLINK_VERTICAL_BAR: {
                this.myCursor.myBlinking = true;
            }
        }
    }

    @Override
    public void beep() {
        if (this.mySettingsProvider.audibleBell()) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Nullable
    public Rectangle getBounds(@NotNull TerminalLineIntervalHighlighting highlighting) {
        TerminalLine line;
        int index;
        if (highlighting == null) {
            TerminalPanel.$$$reportNull$$$0(27);
        }
        if ((index = this.myTerminalTextBuffer.findScreenLineIndex(line = highlighting.getLine())) >= 0 && !highlighting.isDisposed()) {
            return this.getBounds(new LineCellInterval(index, highlighting.getStartOffset(), highlighting.getEndOffset() + 1));
        }
        return null;
    }

    @NotNull
    private Rectangle getBounds(@NotNull LineCellInterval cellInterval) {
        if (cellInterval == null) {
            TerminalPanel.$$$reportNull$$$0(28);
        }
        Point topLeft = new Point(cellInterval.getStartColumn() * this.myCharSize.width + this.getInsetX(), cellInterval.getLine() * this.myCharSize.height);
        return new Rectangle(topLeft, new Dimension(this.myCharSize.width * cellInterval.getCellCount(), this.myCharSize.height));
    }

    public BoundedRangeModel getBoundedRangeModel() {
        return this.myBoundedRangeModel;
    }

    public TerminalTextBuffer getTerminalTextBuffer() {
        return this.myTerminalTextBuffer;
    }

    @Override
    public TerminalSelection getSelection() {
        return this.mySelection;
    }

    @Override
    public boolean ambiguousCharsAreDoubleWidth() {
        return this.mySettingsProvider.ambiguousCharsAreDoubleWidth();
    }

    public LinesBuffer getScrollBuffer() {
        return this.myTerminalTextBuffer.getHistoryBuffer();
    }

    @Override
    public void setCursorVisible(boolean shouldDrawCursor) {
        this.myCursor.setShouldDrawCursor(shouldDrawCursor);
    }

    @NotNull
    protected JPopupMenu createPopupMenu(@Nullable LinkInfo linkInfo, final @NotNull MouseEvent e) {
        LinkInfo.PopupMenuGroupProvider popupMenuGroupProvider;
        if (e == null) {
            TerminalPanel.$$$reportNull$$$0(29);
        }
        JPopupMenu popup = new JPopupMenu();
        LinkInfo.PopupMenuGroupProvider popupMenuGroupProvider2 = popupMenuGroupProvider = linkInfo != null ? linkInfo.getPopupMenuGroupProvider() : null;
        if (popupMenuGroupProvider != null) {
            TerminalAction.addToMenu(popup, new TerminalActionProvider(){

                @Override
                public List<TerminalAction> getActions() {
                    return popupMenuGroupProvider.getPopupMenuGroup(e);
                }

                @Override
                public TerminalActionProvider getNextProvider() {
                    return TerminalPanel.this;
                }

                @Override
                public void setNextProvider(TerminalActionProvider provider) {
                }
            });
        } else {
            TerminalAction.addToMenu(popup, this);
        }
        JPopupMenu jPopupMenu = popup;
        if (jPopupMenu == null) {
            TerminalPanel.$$$reportNull$$$0(30);
        }
        return jPopupMenu;
    }

    @Override
    public void setScrollingEnabled(boolean scrollingEnabled) {
        this.myScrollingEnabled = scrollingEnabled;
        SwingUtilities.invokeLater(() -> this.updateScrolling(true));
    }

    @Override
    public void setBlinkingCursor(boolean enabled) {
        this.myCursor.setBlinking(enabled);
    }

    public TerminalCursor getTerminalCursor() {
        return this.myCursor;
    }

    public TerminalOutputStream getTerminalOutputStream() {
        return this.myTerminalStarter;
    }

    @Override
    public void setWindowTitle(String name) {
        this.myWindowTitle = name;
        if (this.myTerminalPanelListener != null) {
            this.myTerminalPanelListener.onTitleChanged(this.myWindowTitle);
        }
    }

    @Override
    public void setCurrentPath(String path) {
        this.myCurrentPath = path;
    }

    @Override
    public List<TerminalAction> getActions() {
        return Lists.newArrayList(new TerminalAction(this.mySettingsProvider.getOpenUrlActionPresentation(), input -> this.openSelectionAsURL()).withEnabledSupplier(this::selectionTextIsUrl), new TerminalAction(this.mySettingsProvider.getCopyActionPresentation(), this::handleCopy){

            @Override
            public boolean isEnabled(@Nullable KeyEvent e) {
                return e != null || TerminalPanel.this.mySelection != null;
            }
        }.withMnemonicKey(67), new TerminalAction(this.mySettingsProvider.getPasteActionPresentation(), input -> {
            this.handlePaste();
            return true;
        }).withMnemonicKey(80).withEnabledSupplier(() -> this.getClipboardString() != null), new TerminalAction(this.mySettingsProvider.getSelectAllActionPresentation(), input -> {
            this.selectAll();
            return true;
        }), new TerminalAction(this.mySettingsProvider.getClearBufferActionPresentation(), input -> {
            this.clearBuffer();
            return true;
        }).withMnemonicKey(75).withEnabledSupplier(() -> !this.myTerminalTextBuffer.isUsingAlternateBuffer()).separatorBefore(true), new TerminalAction(this.mySettingsProvider.getPageUpActionPresentation(), input -> {
            this.pageUp();
            return true;
        }).withEnabledSupplier(() -> !this.myTerminalTextBuffer.isUsingAlternateBuffer()).separatorBefore(true), new TerminalAction(this.mySettingsProvider.getPageDownActionPresentation(), input -> {
            this.pageDown();
            return true;
        }).withEnabledSupplier(() -> !this.myTerminalTextBuffer.isUsingAlternateBuffer()), new TerminalAction(this.mySettingsProvider.getLineUpActionPresentation(), input -> {
            this.scrollUp();
            return true;
        }).withEnabledSupplier(() -> !this.myTerminalTextBuffer.isUsingAlternateBuffer()).separatorBefore(true), new TerminalAction(this.mySettingsProvider.getLineDownActionPresentation(), input -> {
            this.scrollDown();
            return true;
        }));
    }

    public void selectAll() {
        this.mySelection = new TerminalSelection(new Point(0, -this.myTerminalTextBuffer.getHistoryLinesCount()), new Point(this.myTermSize.width, this.myTerminalTextBuffer.getScreenLinesCount()));
    }

    @NotNull
    private Boolean selectionTextIsUrl() {
        String selectionText = this.getSelectionText();
        if (selectionText != null) {
            Boolean bl;
            try {
                URI uri = new URI(selectionText);
                uri.toURL();
                bl = true;
            } catch (Exception exception) {
                // empty catch block
            }
            if (bl == null) {
                TerminalPanel.$$$reportNull$$$0(31);
            }
            return bl;
        }
        Boolean bl = false;
        if (bl == null) {
            TerminalPanel.$$$reportNull$$$0(32);
        }
        return bl;
    }

    @Nullable
    private String getSelectionText() {
        if (this.mySelection != null) {
            Pair<Point, Point> points = this.mySelection.pointsForRun(this.myTermSize.width);
            if (points.first != null || points.second != null) {
                return SelectionUtil.getSelectionText((Point)points.first, (Point)points.second, this.myTerminalTextBuffer);
            }
        }
        return null;
    }

    protected boolean openSelectionAsURL() {
        if (Desktop.isDesktopSupported()) {
            try {
                String selectionText = this.getSelectionText();
                if (selectionText != null) {
                    Desktop.getDesktop().browse(new URI(selectionText));
                }
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return false;
    }

    public void clearBuffer() {
        this.clearBuffer(true);
    }

    protected void clearBuffer(boolean keepLastLine) {
        if (!this.myTerminalTextBuffer.isUsingAlternateBuffer()) {
            this.myTerminalTextBuffer.clearHistory();
            if (this.myCoordsAccessor != null) {
                if (keepLastLine) {
                    if (this.myCoordsAccessor.getY() > 0) {
                        TerminalLine lastLine = this.myTerminalTextBuffer.getLine(this.myCoordsAccessor.getY() - 1);
                        this.myTerminalTextBuffer.clearAll();
                        this.myCoordsAccessor.setY(0);
                        this.myCursor.setY(1);
                        this.myTerminalTextBuffer.addLine(lastLine);
                    }
                } else {
                    this.myTerminalTextBuffer.clearAll();
                    this.myCoordsAccessor.setX(0);
                    this.myCoordsAccessor.setY(1);
                    this.myCursor.setX(0);
                    this.myCursor.setY(1);
                }
            }
            this.myBoundedRangeModel.setValue(0);
            this.updateScrolling(true);
            this.myClientScrollOrigin = this.myBoundedRangeModel.getValue();
        }
    }

    @Override
    public TerminalActionProvider getNextProvider() {
        return this.myNextActionProvider;
    }

    @Override
    public void setNextProvider(TerminalActionProvider provider) {
        this.myNextActionProvider = provider;
    }

    private void processTerminalKeyPressed(KeyEvent e) {
        if (this.hasUncommittedChars()) {
            return;
        }
        try {
            int keycode = e.getKeyCode();
            char keychar = e.getKeyChar();
            if (keycode == 127 && keychar == '.') {
                this.myTerminalStarter.sendBytes(new byte[]{46});
                e.consume();
                return;
            }
            if (keychar == ' ' && (e.getModifiers() & 2) != 0) {
                this.myTerminalStarter.sendBytes(new byte[]{0});
                e.consume();
                return;
            }
            byte[] code = this.myTerminalStarter.getCode(keycode, e.getModifiers());
            if (code != null) {
                this.myTerminalStarter.sendBytes(code);
                e.consume();
                if (this.mySettingsProvider.scrollToBottomOnTyping() && TerminalPanel.isCodeThatScrolls(keycode)) {
                    this.scrollToBottom();
                }
            } else if ((e.getModifiersEx() & 0x200) != 0 && Character.isDefined(keychar) && this.mySettingsProvider.altSendsEscape()) {
                this.myTerminalStarter.sendString(new String(new char[]{'\u001b', (char)e.getKeyCode()}));
                e.consume();
            } else if (Character.isISOControl(keychar)) {
                this.processCharacter(e);
            }
        } catch (Exception ex) {
            LOG.error("Error sending pressed key to emulator", ex);
        }
    }

    private void processCharacter(@NotNull KeyEvent e) {
        if (e == null) {
            TerminalPanel.$$$reportNull$$$0(33);
        }
        if ((e.getModifiersEx() & 0x200) != 0 && this.mySettingsProvider.altSendsEscape()) {
            return;
        }
        char keyChar = e.getKeyChar();
        int modifiers = e.getModifiers();
        char[] obuffer = new char[]{keyChar};
        if (keyChar == '`' && (modifiers & 4) != 0) {
            return;
        }
        this.myTerminalStarter.sendString(new String(obuffer));
        e.consume();
        if (this.mySettingsProvider.scrollToBottomOnTyping()) {
            this.scrollToBottom();
        }
    }

    private static boolean isCodeThatScrolls(int keycode) {
        return keycode == 38 || keycode == 40 || keycode == 37 || keycode == 39 || keycode == 8 || keycode == 155 || keycode == 127 || keycode == 10 || keycode == 36 || keycode == 35 || keycode == 33 || keycode == 34;
    }

    private void processTerminalKeyTyped(KeyEvent e) {
        if (this.hasUncommittedChars()) {
            return;
        }
        char keychar = e.getKeyChar();
        if (!Character.isISOControl(keychar)) {
            try {
                this.processCharacter(e);
            } catch (Exception ex) {
                LOG.error("Error sending typed key to emulator", ex);
            }
        }
    }

    private void handlePaste() {
        this.pasteFromClipboard(false);
    }

    private void handlePasteSelection() {
        this.pasteFromClipboard(true);
    }

    private void handleCopy(boolean unselect, boolean useSystemSelectionClipboardIfAvailable) {
        if (this.mySelection != null) {
            Pair<Point, Point> points = this.mySelection.pointsForRun(this.myTermSize.width);
            this.copySelection((Point)points.first, (Point)points.second, useSystemSelectionClipboardIfAvailable);
            if (unselect) {
                this.mySelection = null;
                this.repaint();
            }
        }
    }

    private boolean handleCopy(@Nullable KeyEvent e) {
        boolean ctrlC = e != null && e.getKeyCode() == 67 && e.getModifiersEx() == 128;
        boolean sendCtrlC = ctrlC && this.mySelection == null;
        this.handleCopy(ctrlC, false);
        return !sendCtrlC;
    }

    private void handleCopyOnSelect() {
        this.handleCopy(false, true);
    }

    @Override
    protected void processInputMethodEvent(InputMethodEvent e) {
        int commitCount = e.getCommittedCharacterCount();
        if (commitCount > 0) {
            this.myInputMethodUncommittedChars = null;
            AttributedCharacterIterator text = e.getText();
            if (text != null) {
                StringBuilder sb = new StringBuilder();
                char c = text.first();
                while (commitCount > 0) {
                    if (c >= ' ' && c != '\u007f') {
                        sb.append(c);
                    }
                    c = text.next();
                    --commitCount;
                }
                if (sb.length() > 0) {
                    this.myTerminalStarter.sendString(sb.toString());
                }
            }
        } else {
            this.myInputMethodUncommittedChars = TerminalPanel.uncommittedChars(e.getText());
        }
    }

    private static String uncommittedChars(@Nullable AttributedCharacterIterator text) {
        if (text == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char c = text.first();
        while (c != '\uffff') {
            if (c >= ' ' && c != '\u007f') {
                sb.append(c);
            }
            c = text.next();
        }
        return sb.toString();
    }

    @Override
    public InputMethodRequests getInputMethodRequests() {
        return new MyInputMethodRequests();
    }

    public void dispose() {
        this.myRepaintTimer.stop();
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
            case 16: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 26: 
            case 30: 
            case 31: 
            case 32: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 16: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 26: 
            case 30: 
            case 31: 
            case 32: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "settingsProvider";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "terminalTextBuffer";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "styleState";
                break;
            }
            case 3: 
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "scrollBar";
                break;
            }
            case 4: 
            case 5: 
            case 20: 
            case 29: 
            case 33: {
                objectArray2 = objectArray3;
                objectArray3[0] = "e";
                break;
            }
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "panelPoint";
                break;
            }
            case 8: {
                objectArray2 = objectArray3;
                objectArray3[0] = "initialCell";
                break;
            }
            case 9: 
            case 15: 
            case 18: 
            case 22: 
            case 25: {
                objectArray2 = objectArray3;
                objectArray3[0] = "style";
                break;
            }
            case 10: 
            case 11: {
                objectArray2 = objectArray3;
                objectArray3[0] = "p";
                break;
            }
            case 12: 
            case 13: {
                objectArray2 = objectArray3;
                objectArray3[0] = "keyListener";
                break;
            }
            case 14: {
                objectArray2 = objectArray3;
                objectArray3[0] = "newSize";
                break;
            }
            case 16: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 26: 
            case 30: 
            case 31: 
            case 32: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/ui/TerminalPanel";
                break;
            }
            case 24: {
                objectArray2 = objectArray3;
                objectArray3[0] = "link";
                break;
            }
            case 27: {
                objectArray2 = objectArray3;
                objectArray3[0] = "highlighting";
                break;
            }
            case 28: {
                objectArray2 = objectArray3;
                objectArray3[0] = "cellInterval";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/ui/TerminalPanel";
                break;
            }
            case 16: 
            case 17: {
                objectArray = objectArray2;
                objectArray2[1] = "getSelectionStyle";
                break;
            }
            case 19: {
                objectArray = objectArray2;
                objectArray2[1] = "getFoundPattern";
                break;
            }
            case 21: {
                objectArray = objectArray2;
                objectArray2[1] = "getTerminalKeyListener";
                break;
            }
            case 23: {
                objectArray = objectArray2;
                objectArray2[1] = "getInversedStyle";
                break;
            }
            case 26: {
                objectArray = objectArray2;
                objectArray2[1] = "getStyleForeground";
                break;
            }
            case 30: {
                objectArray = objectArray2;
                objectArray2[1] = "createPopupMenu";
                break;
            }
            case 31: 
            case 32: {
                objectArray = objectArray2;
                objectArray2[1] = "selectionTextIsUrl";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 3: {
                objectArray = objectArray;
                objectArray[2] = "init";
                break;
            }
            case 4: {
                objectArray = objectArray;
                objectArray[2] = "isFollowLinkEvent";
                break;
            }
            case 5: 
            case 6: {
                objectArray = objectArray;
                objectArray[2] = "handleMouseWheelEvent";
                break;
            }
            case 7: {
                objectArray = objectArray;
                objectArray[2] = "handleHyperlinks";
                break;
            }
            case 8: 
            case 9: {
                objectArray = objectArray;
                objectArray[2] = "findIntervalWithStyle";
                break;
            }
            case 10: {
                objectArray = objectArray;
                objectArray[2] = "findHyperlink";
                break;
            }
            case 11: {
                objectArray = objectArray;
                objectArray[2] = "panelPointToCell";
                break;
            }
            case 12: {
                objectArray = objectArray;
                objectArray[2] = "addCustomKeyListener";
                break;
            }
            case 13: {
                objectArray = objectArray;
                objectArray[2] = "removeCustomKeyListener";
                break;
            }
            case 14: {
                objectArray = objectArray;
                objectArray[2] = "requestResize";
                break;
            }
            case 15: {
                objectArray = objectArray;
                objectArray[2] = "getSelectionStyle";
                break;
            }
            case 16: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 26: 
            case 30: 
            case 31: 
            case 32: {
                break;
            }
            case 18: {
                objectArray = objectArray;
                objectArray[2] = "getFoundPattern";
                break;
            }
            case 20: {
                objectArray = objectArray;
                objectArray[2] = "handleKeyEvent";
                break;
            }
            case 22: {
                objectArray = objectArray;
                objectArray[2] = "getInversedStyle";
                break;
            }
            case 24: {
                objectArray = objectArray;
                objectArray[2] = "isHoveredHyperlink";
                break;
            }
            case 25: {
                objectArray = objectArray;
                objectArray[2] = "getStyleForeground";
                break;
            }
            case 27: 
            case 28: {
                objectArray = objectArray;
                objectArray[2] = "getBounds";
                break;
            }
            case 29: {
                objectArray = objectArray;
                objectArray[2] = "createPopupMenu";
                break;
            }
            case 33: {
                objectArray = objectArray;
                objectArray[2] = "processCharacter";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 16: 
            case 17: 
            case 19: 
            case 21: 
            case 23: 
            case 26: 
            case 30: 
            case 31: 
            case 32: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }

    private class MyInputMethodRequests
    implements InputMethodRequests {
        private MyInputMethodRequests() {
        }

        @Override
        public Rectangle getTextLocation(TextHitInfo offset) {
            Rectangle r = new Rectangle(TerminalPanel.this.computexCoord(TerminalPanel.this.myCursor.getCoordX(), TerminalPanel.this.myCursor.getCoordY()) + TerminalPanel.this.getInsetX(), (TerminalPanel.this.myCursor.getCoordY() + 1) * TerminalPanel.this.myCharSize.height, 0, 0);
            Point p = TerminalPanel.this.getLocationOnScreen();
            r.translate(p.x, p.y);
            return r;
        }

        @Override
        @Nullable
        public TextHitInfo getLocationOffset(int x, int y) {
            return null;
        }

        @Override
        public int getInsertPositionOffset() {
            return 0;
        }

        @Override
        public AttributedCharacterIterator getCommittedText(int beginIndex, int endIndex, AttributedCharacterIterator.Attribute[] attributes) {
            return null;
        }

        @Override
        public int getCommittedTextLength() {
            return 0;
        }

        @Override
        @Nullable
        public AttributedCharacterIterator cancelLatestCommittedText(AttributedCharacterIterator.Attribute[] attributes) {
            return null;
        }

        @Override
        @Nullable
        public AttributedCharacterIterator getSelectedText(AttributedCharacterIterator.Attribute[] attributes) {
            return null;
        }
    }

    private class TerminalKeyHandler
    extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!TerminalAction.processEvent(TerminalPanel.this, e)) {
                TerminalPanel.this.processTerminalKeyPressed(e);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            TerminalPanel.this.processTerminalKeyTyped(e);
        }
    }

    public class TerminalCursor {
        private boolean myCursorIsShown;
        protected Point myCursorCoordinates = new Point();
        private CursorShape myShape = CursorShape.BLINK_BLOCK;
        private boolean myShouldDrawCursor = true;
        private boolean myBlinking = true;
        private long myLastCursorChange;
        private boolean myCursorHasChanged;

        public void setX(int x) {
            this.myCursorCoordinates.x = x;
            this.cursorChanged();
        }

        public void setY(int y) {
            this.myCursorCoordinates.y = y;
            this.cursorChanged();
        }

        public int getCoordX() {
            return this.myCursorCoordinates.x;
        }

        public int getCoordY() {
            return this.myCursorCoordinates.y - 1 - TerminalPanel.this.myClientScrollOrigin;
        }

        public void setShouldDrawCursor(boolean shouldDrawCursor) {
            this.myShouldDrawCursor = shouldDrawCursor;
        }

        public void setBlinking(boolean blinking) {
            this.myBlinking = blinking;
        }

        public boolean isBlinking() {
            return this.myBlinking && TerminalPanel.this.getBlinkingPeriod() > 0;
        }

        public void cursorChanged() {
            this.myCursorHasChanged = true;
            this.myLastCursorChange = System.currentTimeMillis();
            TerminalPanel.this.repaint();
        }

        private boolean cursorShouldChangeBlinkState(long currentTime) {
            return currentTime - this.myLastCursorChange > (long)TerminalPanel.this.getBlinkingPeriod();
        }

        public void changeStateIfNeeded() {
            if (!TerminalPanel.this.isFocusOwner()) {
                return;
            }
            long currentTime = System.currentTimeMillis();
            if (this.cursorShouldChangeBlinkState(currentTime)) {
                this.myCursorIsShown = !this.myCursorIsShown;
                this.myLastCursorChange = currentTime;
                this.myCursorHasChanged = false;
                TerminalPanel.this.repaint();
            }
        }

        private TerminalCursorState computeBlinkingState() {
            if (!this.isBlinking() || this.myCursorHasChanged || this.myCursorIsShown) {
                return TerminalCursorState.SHOWING;
            }
            return TerminalCursorState.HIDDEN;
        }

        private TerminalCursorState computeCursorState() {
            if (!this.myShouldDrawCursor) {
                return TerminalCursorState.HIDDEN;
            }
            if (!TerminalPanel.this.isFocusOwner()) {
                return TerminalCursorState.NO_FOCUS;
            }
            return this.computeBlinkingState();
        }

        void drawCursor(String c, Graphics2D gfx, TextStyle style) {
            TerminalCursorState state = this.computeCursorState();
            if (state == TerminalCursorState.HIDDEN) {
                return;
            }
            int x = this.getCoordX();
            int y = this.getCoordY();
            if (y < 0 || y >= TerminalPanel.this.myTermSize.height) {
                return;
            }
            CharBuffer buf = new CharBuffer(c);
            int xCoord = TerminalPanel.this.computexCoord(x, y) + TerminalPanel.this.getInsetX();
            int yCoord = y * TerminalPanel.this.myCharSize.height;
            int textLength = buf.length();
            int height = Math.min(TerminalPanel.this.myCharSize.height, TerminalPanel.this.getHeight() - yCoord);
            int width = Math.min(TerminalPanel.this.computexCoordByCharBuffer(0, textLength, buf), TerminalPanel.this.getWidth() - xCoord);
            int lineStrokeSize = 2;
            Color fgColor = TerminalPanel.this.getPalette().getForeground(TerminalPanel.this.myStyleState.getForeground(style.getForegroundForRun()));
            TextStyle inversedStyle = TerminalPanel.this.getInversedStyle(style);
            Color inverseBg = TerminalPanel.this.getPalette().getBackground(TerminalPanel.this.myStyleState.getBackground(inversedStyle.getBackgroundForRun()));
            switch (this.myShape) {
                case BLINK_BLOCK: 
                case STEADY_BLOCK: {
                    if (state == TerminalCursorState.SHOWING) {
                        gfx.setColor(inverseBg);
                        gfx.fillRect(xCoord, yCoord, width, height);
                        TerminalPanel.this.drawCharacters(x, y, inversedStyle, buf, gfx);
                        break;
                    }
                    gfx.setColor(fgColor);
                    gfx.drawRect(xCoord, yCoord, width, height);
                    break;
                }
                case BLINK_UNDERLINE: 
                case STEADY_UNDERLINE: {
                    gfx.setColor(fgColor);
                    gfx.fillRect(xCoord, yCoord + height, width, lineStrokeSize);
                    break;
                }
                case BLINK_VERTICAL_BAR: 
                case STEADY_VERTICAL_BAR: {
                    gfx.setColor(fgColor);
                    gfx.fillRect(xCoord, yCoord, lineStrokeSize, height);
                }
            }
        }

        void setShape(CursorShape shape) {
            this.myShape = shape;
        }
    }

    public static enum TerminalCursorState {
        SHOWING,
        HIDDEN,
        NO_FOCUS;

    }

    static class WeakRedrawTimer
    implements ActionListener {
        private WeakReference<TerminalPanel> ref;

        public WeakRedrawTimer(TerminalPanel terminalPanel) {
            this.ref = new WeakReference<TerminalPanel>(terminalPanel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TerminalPanel terminalPanel = (TerminalPanel)this.ref.get();
            if (terminalPanel != null) {
                terminalPanel.myCursor.changeStateIfNeeded();
                terminalPanel.updateScrolling(false);
                if (terminalPanel.needRepaint.getAndSet(false)) {
                    try {
                        terminalPanel.doRepaint();
                    } catch (Exception ex) {
                        LOG.error("Error while terminal panel redraw", ex);
                    }
                }
            } else {
                Timer timer = (Timer)e.getSource();
                timer.removeActionListener(this);
                timer.stop();
            }
        }
    }
}

