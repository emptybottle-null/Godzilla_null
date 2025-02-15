/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;
import org.fife.ui.rtextarea.CaretStyle;
import org.fife.ui.rtextarea.ChangeableHighlightPainter;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextAreaEditorKit;

public class ConfigurableCaret
extends DefaultCaret {
    private static transient Action selectWord = null;
    private static transient Action selectLine = null;
    private transient MouseEvent selectedWordEvent = null;
    private transient Segment seg = new Segment();
    private CaretStyle style;
    private ChangeableHighlightPainter selectionPainter;
    private boolean alwaysVisible;
    private boolean pasteOnMiddleMouseClick;

    public ConfigurableCaret() {
        this(CaretStyle.THICK_VERTICAL_LINE_STYLE);
    }

    public ConfigurableCaret(CaretStyle style) {
        this.setStyle(style);
        this.selectionPainter = new ChangeableHighlightPainter();
        this.pasteOnMiddleMouseClick = true;
    }

    private void adjustCaret(MouseEvent e) {
        if ((e.getModifiers() & 1) != 0 && this.getDot() != -1) {
            this.moveCaret(e);
        } else {
            this.positionCaret(e);
        }
    }

    private void adjustFocus(boolean inWindow) {
        RTextArea textArea = this.getTextArea();
        if (textArea != null && textArea.isEnabled() && textArea.isRequestFocusEnabled()) {
            if (inWindow) {
                textArea.requestFocusInWindow();
            } else {
                textArea.requestFocus();
            }
        }
    }

    @Override
    protected synchronized void damage(Rectangle r) {
        if (r != null) {
            this.validateWidth(r);
            this.x = r.x - 1;
            this.y = r.y;
            this.width = r.width + 4;
            this.height = r.height;
            this.repaint();
        }
    }

    @Override
    public void deinstall(JTextComponent c) {
        if (!(c instanceof RTextArea)) {
            throw new IllegalArgumentException("c must be instance of RTextArea");
        }
        super.deinstall(c);
        c.setNavigationFilter(null);
    }

    public boolean getPasteOnMiddleMouseClick() {
        return this.pasteOnMiddleMouseClick;
    }

    protected RTextArea getTextArea() {
        return (RTextArea)this.getComponent();
    }

    public boolean getRoundedSelectionEdges() {
        return ((ChangeableHighlightPainter)this.getSelectionPainter()).getRoundedEdges();
    }

    @Override
    protected Highlighter.HighlightPainter getSelectionPainter() {
        return this.selectionPainter;
    }

    public CaretStyle getStyle() {
        return this.style;
    }

    @Override
    public void install(JTextComponent c) {
        if (!(c instanceof RTextArea)) {
            throw new IllegalArgumentException("c must be instance of RTextArea");
        }
        super.install(c);
        c.setNavigationFilter(new FoldAwareNavigationFilter());
    }

    public boolean isAlwaysVisible() {
        return this.alwaysVisible;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isConsumed()) {
            JTextComponent c;
            RTextArea textArea = this.getTextArea();
            int nclicks = e.getClickCount();
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (nclicks > 2) {
                    switch (nclicks %= 2) {
                        case 0: {
                            this.selectWord(e);
                            this.selectedWordEvent = null;
                            break;
                        }
                        case 1: {
                            Action a = null;
                            ActionMap map = textArea.getActionMap();
                            if (map != null) {
                                a = map.get("select-line");
                            }
                            if (a == null) {
                                if (selectLine == null) {
                                    selectLine = new RTextAreaEditorKit.SelectLineAction();
                                }
                                a = selectLine;
                            }
                            a.actionPerformed(new ActionEvent(textArea, 1001, null, e.getWhen(), e.getModifiers()));
                        }
                    }
                }
            } else if (SwingUtilities.isMiddleMouseButton(e) && this.getPasteOnMiddleMouseClick() && nclicks == 1 && textArea.isEditable() && textArea.isEnabled() && (c = (JTextComponent)e.getSource()) != null) {
                try {
                    Toolkit tk = c.getToolkit();
                    Clipboard buffer = tk.getSystemSelection();
                    if (buffer != null) {
                        Transferable trans;
                        this.adjustCaret(e);
                        TransferHandler th = c.getTransferHandler();
                        if (th != null && (trans = buffer.getContents(null)) != null) {
                            th.importData(c, trans);
                        }
                        this.adjustFocus(true);
                    } else {
                        textArea.paste();
                    }
                } catch (HeadlessException headlessException) {
                    // empty catch block
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JTextComponent c;
        super.mousePressed(e);
        if (!e.isConsumed() && SwingUtilities.isRightMouseButton(e) && (c = this.getComponent()) != null && c.isEnabled() && c.isRequestFocusEnabled()) {
            c.requestFocusInWindow();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (this.isVisible() || this.alwaysVisible) {
            try {
                RTextArea textArea = this.getTextArea();
                g.setColor(textArea.getCaretColor());
                TextUI mapper = textArea.getUI();
                Rectangle r = mapper.modelToView(textArea, this.getDot());
                this.validateWidth(r);
                if (this.width > 0 && this.height > 0 && !this.contains(r.x, r.y, r.width, r.height)) {
                    Rectangle clip = g.getClipBounds();
                    if (clip != null && !clip.contains(this)) {
                        this.repaint();
                    }
                    this.damage(r);
                }
                r.height -= 2;
                switch (this.style) {
                    case BLOCK_STYLE: {
                        Color textAreaBg = textArea.getBackground();
                        if (textAreaBg == null) {
                            textAreaBg = Color.white;
                        }
                        g.setXORMode(textAreaBg);
                        g.fillRect(r.x, r.y, r.width, r.height);
                        break;
                    }
                    case BLOCK_BORDER_STYLE: {
                        g.drawRect(r.x, r.y, r.width - 1, r.height);
                        break;
                    }
                    case UNDERLINE_STYLE: {
                        Color textAreaBg = textArea.getBackground();
                        if (textAreaBg == null) {
                            textAreaBg = Color.white;
                        }
                        g.setXORMode(textAreaBg);
                        int y = r.y + r.height;
                        g.drawLine(r.x, y, r.x + r.width - 1, y);
                        break;
                    }
                    default: {
                        g.drawLine(r.x, r.y, r.x, r.y + r.height);
                        break;
                    }
                    case THICK_VERTICAL_LINE_STYLE: {
                        g.drawLine(r.x, r.y, r.x, r.y + r.height);
                        ++r.x;
                        g.drawLine(r.x, r.y, r.x, r.y + r.height);
                        break;
                    }
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
    }

    private void selectWord(MouseEvent e) {
        if (this.selectedWordEvent != null && this.selectedWordEvent.getX() == e.getX() && this.selectedWordEvent.getY() == e.getY()) {
            return;
        }
        Action a = null;
        RTextArea textArea = this.getTextArea();
        ActionMap map = textArea.getActionMap();
        if (map != null) {
            a = map.get("select-word");
        }
        if (a == null) {
            if (selectWord == null) {
                selectWord = new RTextAreaEditorKit.SelectWordAction();
            }
            a = selectWord;
        }
        a.actionPerformed(new ActionEvent(textArea, 1001, null, e.getWhen(), e.getModifiers()));
        this.selectedWordEvent = e;
    }

    public void setAlwaysVisible(boolean alwaysVisible) {
        if (alwaysVisible != this.alwaysVisible) {
            this.alwaysVisible = alwaysVisible;
            if (!this.isVisible()) {
                this.repaint();
            }
        }
    }

    public void setPasteOnMiddleMouseClick(boolean paste) {
        this.pasteOnMiddleMouseClick = paste;
    }

    public void setRoundedSelectionEdges(boolean rounded) {
        ((ChangeableHighlightPainter)this.getSelectionPainter()).setRoundedEdges(rounded);
    }

    @Override
    public void setSelectionVisible(boolean visible) {
        super.setSelectionVisible(true);
    }

    public void setStyle(CaretStyle style) {
        if (style == null) {
            style = CaretStyle.THICK_VERTICAL_LINE_STYLE;
        }
        if (style != this.style) {
            this.style = style;
            this.repaint();
        }
    }

    private void validateWidth(Rectangle rect) {
        if (rect != null && rect.width <= 1) {
            try {
                RTextArea textArea = this.getTextArea();
                textArea.getDocument().getText(this.getDot(), 1, this.seg);
                Font font = textArea.getFont();
                FontMetrics fm = textArea.getFontMetrics(font);
                rect.width = fm.charWidth(this.seg.array[this.seg.offset]);
                if (rect.width == 0) {
                    rect.width = fm.charWidth(' ');
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
                rect.width = 8;
            }
        }
    }

    private class FoldAwareNavigationFilter
    extends NavigationFilter {
        private FoldAwareNavigationFilter() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void setDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias) {
            block11: {
                RSyntaxTextArea rsta;
                RTextArea textArea = ConfigurableCaret.this.getTextArea();
                if (textArea instanceof RSyntaxTextArea && (rsta = (RSyntaxTextArea)ConfigurableCaret.this.getTextArea()).isCodeFoldingEnabled()) {
                    int lastDot = ConfigurableCaret.this.getDot();
                    FoldManager fm = rsta.getFoldManager();
                    int line = 0;
                    try {
                        line = textArea.getLineOfOffset(dot);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (fm.isLineHidden(line)) {
                        try {
                            if (dot > lastDot) {
                                int lineCount = textArea.getLineCount();
                                while (++line < lineCount && fm.isLineHidden(line)) {
                                }
                                if (line >= lineCount) {
                                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                                    return;
                                }
                                dot = textArea.getLineStartOffset(line);
                                break block11;
                            }
                            if (dot >= lastDot) break block11;
                            while (--line >= 0 && fm.isLineHidden(line)) {
                            }
                            if (line >= 0) {
                                dot = textArea.getLineEndOffset(line) - 1;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
            super.setDot(fb, dot, bias);
        }

        @Override
        public void moveDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias) {
            super.moveDot(fb, dot, bias);
        }
    }
}

