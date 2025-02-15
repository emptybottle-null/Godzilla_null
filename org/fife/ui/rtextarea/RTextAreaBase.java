/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.TextUI;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.StyleContext;
import org.fife.ui.rtextarea.BackgroundPainterStrategy;
import org.fife.ui.rtextarea.BufferedImageBackgroundPainterStrategy;
import org.fife.ui.rtextarea.ColorBackgroundPainterStrategy;
import org.fife.ui.rtextarea.ConfigurableCaret;
import org.fife.ui.rtextarea.ImageBackgroundPainterStrategy;
import org.fife.ui.rtextarea.RTextAreaUI;

public abstract class RTextAreaBase
extends JTextArea {
    public static final String BACKGROUND_IMAGE_PROPERTY = "background.image";
    public static final String CURRENT_LINE_HIGHLIGHT_COLOR_PROPERTY = "RTA.currentLineHighlightColor";
    public static final String CURRENT_LINE_HIGHLIGHT_FADE_PROPERTY = "RTA.currentLineHighlightFade";
    public static final String HIGHLIGHT_CURRENT_LINE_PROPERTY = "RTA.currentLineHighlight";
    public static final String ROUNDED_SELECTION_PROPERTY = "RTA.roundedSelection";
    private boolean tabsEmulatedWithSpaces;
    private boolean highlightCurrentLine;
    private Color currentLineColor;
    private boolean marginLineEnabled;
    private Color marginLineColor;
    private int marginLineX;
    private int marginSizeInChars;
    private boolean fadeCurrentLineHighlight;
    private boolean roundedSelectionEdges;
    private int previousCaretY;
    int currentCaretY;
    private BackgroundPainterStrategy backgroundPainter;
    private RTAMouseListener mouseListener;
    private static final Color DEFAULT_CARET_COLOR = new ColorUIResource(255, 51, 51);
    private static final Color DEFAULT_CURRENT_LINE_HIGHLIGHT_COLOR = new Color(255, 255, 170);
    private static final Color DEFAULT_MARGIN_LINE_COLOR = new Color(255, 224, 224);
    private static final int DEFAULT_TAB_SIZE = 4;
    private static final int DEFAULT_MARGIN_LINE_POSITION = 80;

    public RTextAreaBase() {
        this.init();
    }

    public RTextAreaBase(AbstractDocument doc) {
        super(doc);
        this.init();
    }

    public RTextAreaBase(String text) {
        this.init();
        this.setText(text);
    }

    public RTextAreaBase(int rows, int cols) {
        super(rows, cols);
        this.init();
    }

    public RTextAreaBase(String text, int rows, int cols) {
        super(rows, cols);
        this.init();
        this.setText(text);
    }

    public RTextAreaBase(AbstractDocument doc, String text, int rows, int cols) {
        super(doc, null, rows, cols);
        this.init();
        this.setText(text);
    }

    private void addCurrentLineHighlightListeners() {
        MouseListener[] mouseListeners;
        MouseMotionListener[] mouseMotionListeners;
        boolean add = true;
        for (MouseMotionListener mouseMotionListener : mouseMotionListeners = this.getMouseMotionListeners()) {
            if (mouseMotionListener != this.mouseListener) continue;
            add = false;
            break;
        }
        if (add) {
            this.addMouseMotionListener(this.mouseListener);
        }
        for (MouseListener listener : mouseListeners = this.getMouseListeners()) {
            if (listener != this.mouseListener) continue;
            add = false;
            break;
        }
        if (add) {
            this.addMouseListener(this.mouseListener);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (this.getCaretPosition() != 0) {
            SwingUtilities.invokeLater(this::possiblyUpdateCurrentLineHighlightLocation);
        }
    }

    public void convertSpacesToTabs() {
        int caretPosition = this.getCaretPosition();
        int tabSize = this.getTabSize();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tabSize; ++i) {
            stringBuilder.append(" ");
        }
        String text = this.getText();
        this.setText(text.replaceAll(stringBuilder.toString(), "\t"));
        int newDocumentLength = this.getDocument().getLength();
        if (caretPosition < newDocumentLength) {
            this.setCaretPosition(caretPosition);
        } else {
            this.setCaretPosition(newDocumentLength - 1);
        }
    }

    public void convertTabsToSpaces() {
        int caretPosition = this.getCaretPosition();
        int tabSize = this.getTabSize();
        StringBuilder tabInSpaces = new StringBuilder();
        for (int i = 0; i < tabSize; ++i) {
            tabInSpaces.append(' ');
        }
        String text = this.getText();
        this.setText(text.replaceAll("\t", tabInSpaces.toString()));
        this.setCaretPosition(caretPosition);
    }

    protected abstract RTAMouseListener createMouseListener();

    protected abstract RTextAreaUI createRTextAreaUI();

    protected void forceCurrentLineHighlightRepaint() {
        if (this.isShowing()) {
            this.previousCaretY = -1;
            this.fireCaretUpdate(this.mouseListener);
        }
    }

    @Override
    public final Color getBackground() {
        Object bg = this.getBackgroundObject();
        return bg instanceof Color ? (Color)bg : null;
    }

    public final Image getBackgroundImage() {
        Object bg = this.getBackgroundObject();
        return bg instanceof Image ? (Image)bg : null;
    }

    public final Object getBackgroundObject() {
        if (this.backgroundPainter == null) {
            return null;
        }
        return this.backgroundPainter instanceof ImageBackgroundPainterStrategy ? ((ImageBackgroundPainterStrategy)this.backgroundPainter).getMasterImage() : ((ColorBackgroundPainterStrategy)this.backgroundPainter).getColor();
    }

    public final int getCaretLineNumber() {
        try {
            return this.getLineOfOffset(this.getCaretPosition());
        } catch (BadLocationException ble) {
            return 0;
        }
    }

    public final int getCaretOffsetFromLineStart() {
        try {
            int pos = this.getCaretPosition();
            return pos - this.getLineStartOffset(this.getLineOfOffset(pos));
        } catch (BadLocationException ble) {
            return 0;
        }
    }

    protected int getCurrentCaretY() {
        return this.currentCaretY;
    }

    public Color getCurrentLineHighlightColor() {
        return this.currentLineColor;
    }

    public static Color getDefaultCaretColor() {
        return DEFAULT_CARET_COLOR;
    }

    public static Color getDefaultCurrentLineHighlightColor() {
        return DEFAULT_CURRENT_LINE_HIGHLIGHT_COLOR;
    }

    public static Font getDefaultFont() {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        Font font = null;
        if (RTextAreaBase.isOSX()) {
            font = sc.getFont("Menlo", 0, 12);
            if (!"Menlo".equals(font.getFamily()) && !"Monaco".equals((font = sc.getFont("Monaco", 0, 12)).getFamily())) {
                font = sc.getFont("Monospaced", 0, 13);
            }
        } else {
            font = sc.getFont("Consolas", 0, 13);
            if (!"Consolas".equals(font.getFamily())) {
                font = sc.getFont("Monospaced", 0, 13);
            }
        }
        return font;
    }

    public static Color getDefaultForeground() {
        return Color.BLACK;
    }

    public static Color getDefaultMarginLineColor() {
        return DEFAULT_MARGIN_LINE_COLOR;
    }

    public static int getDefaultMarginLinePosition() {
        return 80;
    }

    public static int getDefaultTabSize() {
        return 4;
    }

    public boolean getFadeCurrentLineHighlight() {
        return this.fadeCurrentLineHighlight;
    }

    public boolean getHighlightCurrentLine() {
        return this.highlightCurrentLine;
    }

    public final int getLineEndOffsetOfCurrentLine() {
        try {
            return this.getLineEndOffset(this.getCaretLineNumber());
        } catch (BadLocationException ble) {
            return 0;
        }
    }

    public int getLineHeight() {
        return this.getRowHeight();
    }

    public final int getLineStartOffsetOfCurrentLine() {
        try {
            return this.getLineStartOffset(this.getCaretLineNumber());
        } catch (BadLocationException ble) {
            return 0;
        }
    }

    public Color getMarginLineColor() {
        return this.marginLineColor;
    }

    public int getMarginLinePixelLocation() {
        return this.marginLineX;
    }

    public int getMarginLinePosition() {
        return this.marginSizeInChars;
    }

    public boolean getRoundedSelectionEdges() {
        return this.roundedSelectionEdges;
    }

    public boolean getTabsEmulated() {
        return this.tabsEmulatedWithSpaces;
    }

    protected void init() {
        this.setRTextAreaUI(this.createRTextAreaUI());
        this.enableEvents(9L);
        this.setHighlightCurrentLine(true);
        this.setCurrentLineHighlightColor(RTextAreaBase.getDefaultCurrentLineHighlightColor());
        this.setMarginLineEnabled(false);
        this.setMarginLineColor(RTextAreaBase.getDefaultMarginLineColor());
        this.setMarginLinePosition(RTextAreaBase.getDefaultMarginLinePosition());
        this.setBackgroundObject(Color.WHITE);
        this.setWrapStyleWord(true);
        this.setTabSize(5);
        this.setForeground(Color.BLACK);
        this.setTabsEmulated(false);
        this.previousCaretY = this.currentCaretY = this.getInsets().top;
        this.mouseListener = this.createMouseListener();
        this.addFocusListener(this.mouseListener);
        this.addCurrentLineHighlightListeners();
    }

    public boolean isMarginLineEnabled() {
        return this.marginLineEnabled;
    }

    public static boolean isOSX() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.startsWith("mac os x");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void paintComponent(Graphics g) {
        this.backgroundPainter.paint(g, this.getVisibleRect());
        TextUI ui = this.getUI();
        if (ui != null) {
            Graphics scratchGraphics = g.create();
            try {
                ui.update(scratchGraphics, this);
            } finally {
                scratchGraphics.dispose();
            }
        }
    }

    protected void possiblyUpdateCurrentLineHighlightLocation() {
        int width = this.getWidth();
        int lineHeight = this.getLineHeight();
        int dot = this.getCaretPosition();
        if (this.getLineWrap()) {
            try {
                Rectangle temp = this.modelToView(dot);
                if (temp != null) {
                    this.currentCaretY = temp.y;
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        } else {
            try {
                Rectangle temp = this.modelToView(dot);
                if (temp != null) {
                    this.currentCaretY = temp.y;
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        this.repaint(0, this.currentCaretY, width, lineHeight);
        if (this.previousCaretY != this.currentCaretY) {
            this.repaint(0, this.previousCaretY, width, lineHeight);
        }
        this.previousCaretY = this.currentCaretY;
    }

    @Override
    protected void processComponentEvent(ComponentEvent e) {
        if (e.getID() == 101 && this.getLineWrap() && this.getHighlightCurrentLine()) {
            this.previousCaretY = -1;
            this.fireCaretUpdate(this.mouseListener);
        }
        super.processComponentEvent(e);
    }

    @Override
    public void setBackground(Color bg) {
        Object oldBG = this.getBackgroundObject();
        if (oldBG instanceof Color) {
            ((ColorBackgroundPainterStrategy)this.backgroundPainter).setColor(bg);
        } else {
            this.backgroundPainter = new ColorBackgroundPainterStrategy(bg);
        }
        this.setOpaque(bg == null || bg.getAlpha() == 255);
        this.firePropertyChange("background", oldBG, bg);
        this.repaint();
    }

    public void setBackgroundImage(Image image) {
        Object oldBG = this.getBackgroundObject();
        if (oldBG instanceof Image) {
            ((ImageBackgroundPainterStrategy)this.backgroundPainter).setImage(image);
        } else {
            BufferedImageBackgroundPainterStrategy strategy = new BufferedImageBackgroundPainterStrategy(this);
            strategy.setImage(image);
            this.backgroundPainter = strategy;
        }
        this.setOpaque(false);
        this.firePropertyChange(BACKGROUND_IMAGE_PROPERTY, oldBG, image);
        this.repaint();
    }

    public void setBackgroundObject(Object newBackground) {
        if (newBackground instanceof Color) {
            this.setBackground((Color)newBackground);
        } else if (newBackground instanceof Image) {
            this.setBackgroundImage((Image)newBackground);
        } else {
            this.setBackground(Color.WHITE);
        }
    }

    public void setCurrentLineHighlightColor(Color color) {
        if (color == null) {
            throw new NullPointerException();
        }
        if (!color.equals(this.currentLineColor)) {
            Color old = this.currentLineColor;
            this.currentLineColor = color;
            this.firePropertyChange(CURRENT_LINE_HIGHLIGHT_COLOR_PROPERTY, old, color);
        }
    }

    public void setFadeCurrentLineHighlight(boolean fade) {
        if (fade != this.fadeCurrentLineHighlight) {
            this.fadeCurrentLineHighlight = fade;
            if (this.getHighlightCurrentLine()) {
                this.forceCurrentLineHighlightRepaint();
            }
            this.firePropertyChange(CURRENT_LINE_HIGHLIGHT_FADE_PROPERTY, !fade, fade);
        }
    }

    @Override
    public void setFont(Font font) {
        if (font != null && font.getSize() <= 0) {
            throw new IllegalArgumentException("Font size must be > 0");
        }
        super.setFont(font);
        if (font != null) {
            this.updateMarginLineX();
            if (this.highlightCurrentLine) {
                this.possiblyUpdateCurrentLineHighlightLocation();
            }
        }
    }

    public void setHighlightCurrentLine(boolean highlight) {
        if (highlight != this.highlightCurrentLine) {
            this.highlightCurrentLine = highlight;
            this.firePropertyChange(HIGHLIGHT_CURRENT_LINE_PROPERTY, !highlight, highlight);
            this.repaint();
        }
    }

    @Override
    public void setLineWrap(boolean wrap) {
        super.setLineWrap(wrap);
        this.forceCurrentLineHighlightRepaint();
    }

    @Override
    public void setMargin(Insets insets) {
        int newTop;
        Insets old = this.getInsets();
        int oldTop = old != null ? old.top : 0;
        int n = newTop = insets != null ? insets.top : 0;
        if (oldTop != newTop) {
            this.previousCaretY = this.currentCaretY = newTop;
        }
        super.setMargin(insets);
    }

    public void setMarginLineColor(Color color) {
        this.marginLineColor = color;
        if (this.marginLineEnabled) {
            Rectangle visibleRect = this.getVisibleRect();
            this.repaint(this.marginLineX, visibleRect.y, 1, visibleRect.height);
        }
    }

    public void setMarginLineEnabled(boolean enabled) {
        if (enabled != this.marginLineEnabled) {
            this.marginLineEnabled = enabled;
            if (this.marginLineEnabled) {
                Rectangle visibleRect = this.getVisibleRect();
                this.repaint(this.marginLineX, visibleRect.y, 1, visibleRect.height);
            }
        }
    }

    public void setMarginLinePosition(int size) {
        this.marginSizeInChars = size;
        if (this.marginLineEnabled) {
            Rectangle visibleRect = this.getVisibleRect();
            this.repaint(this.marginLineX, visibleRect.y, 1, visibleRect.height);
            this.updateMarginLineX();
            this.repaint(this.marginLineX, visibleRect.y, 1, visibleRect.height);
        }
    }

    public void setRoundedSelectionEdges(boolean rounded) {
        if (this.roundedSelectionEdges != rounded) {
            this.roundedSelectionEdges = rounded;
            Caret c = this.getCaret();
            if (c instanceof ConfigurableCaret) {
                ((ConfigurableCaret)c).setRoundedSelectionEdges(rounded);
                if (c.getDot() != c.getMark()) {
                    this.repaint();
                }
            }
            this.firePropertyChange(ROUNDED_SELECTION_PROPERTY, !rounded, rounded);
        }
    }

    protected void setRTextAreaUI(RTextAreaUI ui) {
        super.setUI(ui);
        this.setOpaque(this.getBackgroundObject() instanceof Color);
    }

    public void setTabsEmulated(boolean areEmulated) {
        this.tabsEmulatedWithSpaces = areEmulated;
    }

    @Override
    public void setTabSize(int size) {
        super.setTabSize(size);
        boolean b = this.getLineWrap();
        this.setLineWrap(!b);
        this.setLineWrap(b);
    }

    protected void updateMarginLineX() {
        Font font = this.getFont();
        if (font == null) {
            this.marginLineX = 0;
            return;
        }
        this.marginLineX = this.getFontMetrics(font).charWidth('m') * this.marginSizeInChars;
    }

    public int yForLine(int line) throws BadLocationException {
        return ((RTextAreaUI)this.getUI()).yForLine(line);
    }

    public int yForLineContaining(int offs) throws BadLocationException {
        return ((RTextAreaUI)this.getUI()).yForLineContaining(offs);
    }

    protected static class RTAMouseListener
    extends CaretEvent
    implements MouseListener,
    MouseMotionListener,
    FocusListener {
        protected int dot;
        protected int mark;

        RTAMouseListener(RTextAreaBase textArea) {
            super(textArea);
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public int getDot() {
            return this.dot;
        }

        @Override
        public int getMark() {
            return this.mark;
        }
    }
}

