/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.MouseInputListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.View;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;
import org.fife.ui.rtextarea.AbstractGutterComponent;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextAreaUI;

public class LineNumberList
extends AbstractGutterComponent
implements MouseInputListener {
    private int currentLine;
    private int lastY = -1;
    private int lastVisibleLine;
    private int cellHeight;
    private int cellWidth;
    private int ascent;
    private Map<?, ?> aaHints;
    private int mouseDragStartOffset;
    private Listener l;
    private Insets textAreaInsets;
    private Rectangle visibleRect;
    private int lineNumberingStartIndex;

    public LineNumberList(RTextArea textArea) {
        this(textArea, null);
    }

    public LineNumberList(RTextArea textArea, Color numberColor) {
        super(textArea);
        if (numberColor != null) {
            this.setForeground(numberColor);
        } else {
            this.setForeground(Color.GRAY);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (this.textArea != null) {
            this.l.install(this.textArea);
        }
        this.updateCellWidths();
        this.updateCellHeights();
    }

    private int calculateLastVisibleLineNumber() {
        int lastLine = 0;
        if (this.textArea != null) {
            lastLine = this.textArea.getLineCount() + this.getLineNumberingStartIndex() - 1;
        }
        return lastLine;
    }

    public int getLineNumberingStartIndex() {
        return this.lineNumberingStartIndex;
    }

    @Override
    public Dimension getPreferredSize() {
        int h = this.textArea != null ? this.textArea.getHeight() : 100;
        return new Dimension(this.cellWidth, h);
    }

    private int getRhsBorderWidth() {
        int w = 4;
        if (this.textArea instanceof RSyntaxTextArea && ((RSyntaxTextArea)this.textArea).isCodeFoldingEnabled()) {
            w = 0;
        }
        return w;
    }

    @Override
    void handleDocumentEvent(DocumentEvent e) {
        int newLastLine = this.calculateLastVisibleLineNumber();
        if (newLastLine != this.lastVisibleLine) {
            if (newLastLine / 10 != this.lastVisibleLine / 10) {
                this.updateCellWidths();
            }
            this.lastVisibleLine = newLastLine;
            this.repaint();
        }
    }

    @Override
    protected void init() {
        super.init();
        this.currentLine = 0;
        this.setLineNumberingStartIndex(1);
        this.visibleRect = new Rectangle();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.aaHints = RSyntaxUtilities.getDesktopAntiAliasHints();
    }

    @Override
    void lineHeightsChanged() {
        this.updateCellHeights();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int pos;
        if (this.mouseDragStartOffset > -1 && (pos = this.textArea.viewToModel(new Point(0, e.getY()))) >= 0) {
            this.textArea.setCaretPosition(this.mouseDragStartOffset);
            this.textArea.moveCaretPosition(pos);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.textArea == null) {
            return;
        }
        if (e.getButton() == 1) {
            int pos = this.textArea.viewToModel(new Point(0, e.getY()));
            if (pos >= 0) {
                this.textArea.setCaretPosition(pos);
            }
            this.mouseDragStartOffset = pos;
        } else {
            this.mouseDragStartOffset = -1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.textArea == null) {
            return;
        }
        this.visibleRect = g.getClipBounds(this.visibleRect);
        if (this.visibleRect == null) {
            this.visibleRect = this.getVisibleRect();
        }
        if (this.visibleRect == null) {
            return;
        }
        Color bg = this.getBackground();
        if (this.getGutter() != null) {
            bg = this.getGutter().getBackground();
        }
        g.setColor(bg);
        g.fillRect(0, this.visibleRect.y, this.cellWidth, this.visibleRect.height);
        g.setFont(this.getFont());
        if (this.aaHints != null) {
            ((Graphics2D)g).addRenderingHints(this.aaHints);
        }
        if (this.textArea.getLineWrap()) {
            this.paintWrappedLineNumbers(g, this.visibleRect);
            return;
        }
        this.textAreaInsets = this.textArea.getInsets(this.textAreaInsets);
        if (this.visibleRect.y < this.textAreaInsets.top) {
            this.visibleRect.height -= this.textAreaInsets.top - this.visibleRect.y;
            this.visibleRect.y = this.textAreaInsets.top;
        }
        int topLine = (this.visibleRect.y - this.textAreaInsets.top) / this.cellHeight;
        int actualTopY = topLine * this.cellHeight + this.textAreaInsets.top;
        int y = actualTopY + this.ascent;
        FoldManager fm = null;
        if (this.textArea instanceof RSyntaxTextArea) {
            fm = ((RSyntaxTextArea)this.textArea).getFoldManager();
            topLine += fm.getHiddenLineCountAbove(topLine, true);
        }
        int rhsBorderWidth = this.getRhsBorderWidth();
        g.setColor(this.getForeground());
        boolean ltr = this.getComponentOrientation().isLeftToRight();
        if (ltr) {
            FontMetrics metrics = g.getFontMetrics();
            int rhs = this.getWidth() - rhsBorderWidth;
            for (int line = topLine + 1; y < this.visibleRect.y + this.visibleRect.height + this.ascent && line <= this.textArea.getLineCount(); ++line) {
                int hiddenLineCount;
                String number = Integer.toString(line + this.getLineNumberingStartIndex() - 1);
                int width = metrics.stringWidth(number);
                g.drawString(number, rhs - width, y);
                y += this.cellHeight;
                if (fm == null) continue;
                Fold fold = fm.getFoldForLine(line - 1);
                while (fold != null && fold.isCollapsed() && (hiddenLineCount = fold.getLineCount()) != 0) {
                    fold = fm.getFoldForLine((line += hiddenLineCount) - 1);
                }
            }
        } else {
            for (int line = topLine + 1; y < this.visibleRect.y + this.visibleRect.height && line < this.textArea.getLineCount(); ++line) {
                String number = Integer.toString(line + this.getLineNumberingStartIndex() - 1);
                g.drawString(number, rhsBorderWidth, y);
                y += this.cellHeight;
                if (fm == null) continue;
                Fold fold = fm.getFoldForLine(line - 1);
                while (fold != null && fold.isCollapsed()) {
                    fold = fm.getFoldForLine(line += fold.getLineCount());
                }
            }
        }
    }

    private void paintWrappedLineNumbers(Graphics g, Rectangle visibleRect) {
        int width = this.getWidth();
        RTextAreaUI ui = (RTextAreaUI)this.textArea.getUI();
        View v = ui.getRootView(this.textArea).getView(0);
        Document doc = this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        int lineCount = root.getElementCount();
        int topPosition = this.textArea.viewToModel(new Point(visibleRect.x, visibleRect.y));
        int topLine = root.getElementIndex(topPosition);
        FoldManager fm = null;
        if (this.textArea instanceof RSyntaxTextArea) {
            fm = ((RSyntaxTextArea)this.textArea).getFoldManager();
        }
        Rectangle visibleEditorRect = ui.getVisibleEditorRect();
        Rectangle r = LineNumberList.getChildViewBounds(v, topLine, visibleEditorRect);
        int y = r.y;
        int rhsBorderWidth = this.getRhsBorderWidth();
        boolean ltr = this.getComponentOrientation().isLeftToRight();
        int rhs = ltr ? width - rhsBorderWidth : rhsBorderWidth;
        int visibleBottom = visibleRect.y + visibleRect.height;
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(this.getForeground());
        while (y < visibleBottom) {
            Fold fold;
            r = LineNumberList.getChildViewBounds(v, topLine, visibleEditorRect);
            int index = topLine + 1 + this.getLineNumberingStartIndex() - 1;
            String number = Integer.toString(index);
            if (ltr) {
                int strWidth = metrics.stringWidth(number);
                g.drawString(number, rhs - strWidth, y + this.ascent);
            } else {
                int x = rhsBorderWidth;
                g.drawString(number, x, y + this.ascent);
            }
            y += r.height;
            if (fm != null && (fold = fm.getFoldForLine(topLine)) != null && fold.isCollapsed()) {
                topLine += fold.getCollapsedLineCount();
            }
            if (++topLine < lineCount) continue;
            break;
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        if (this.textArea != null) {
            this.l.uninstall(this.textArea);
        }
    }

    private void repaintLine(int line) {
        int y = this.textArea.getInsets().top;
        this.repaint(0, y += line * this.cellHeight, this.cellWidth, this.cellHeight);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        this.updateCellWidths();
        this.updateCellHeights();
    }

    public void setLineNumberingStartIndex(int index) {
        if (index != this.lineNumberingStartIndex) {
            this.lineNumberingStartIndex = index;
            this.updateCellWidths();
            this.repaint();
        }
    }

    @Override
    public void setTextArea(RTextArea textArea) {
        if (this.l == null) {
            this.l = new Listener();
        }
        if (this.textArea != null) {
            this.l.uninstall(textArea);
        }
        super.setTextArea(textArea);
        this.lastVisibleLine = this.calculateLastVisibleLineNumber();
        if (textArea != null) {
            this.l.install(textArea);
            this.updateCellHeights();
            this.updateCellWidths();
        }
    }

    private void updateCellHeights() {
        if (this.textArea != null) {
            this.cellHeight = this.textArea.getLineHeight();
            this.ascent = this.textArea.getMaxAscent();
        } else {
            this.cellHeight = 20;
            this.ascent = 5;
        }
        this.repaint();
    }

    void updateCellWidths() {
        Font font;
        int oldCellWidth = this.cellWidth;
        this.cellWidth = this.getRhsBorderWidth();
        if (this.textArea != null && (font = this.getFont()) != null) {
            FontMetrics fontMetrics = this.getFontMetrics(font);
            int count = 0;
            int lineCount = this.textArea.getLineCount() + this.getLineNumberingStartIndex() - 1;
            do {
                ++count;
            } while ((lineCount /= 10) >= 10);
            this.cellWidth += fontMetrics.charWidth('9') * (count + 1) + 3;
        }
        if (this.cellWidth != oldCellWidth) {
            this.revalidate();
        }
    }

    private class Listener
    implements CaretListener,
    PropertyChangeListener {
        private boolean installed;

        private Listener() {
        }

        @Override
        public void caretUpdate(CaretEvent e) {
            int dot = LineNumberList.this.textArea.getCaretPosition();
            if (!LineNumberList.this.textArea.getLineWrap()) {
                int line = LineNumberList.this.textArea.getDocument().getDefaultRootElement().getElementIndex(dot);
                if (LineNumberList.this.currentLine != line) {
                    LineNumberList.this.repaintLine(line);
                    LineNumberList.this.repaintLine(LineNumberList.this.currentLine);
                    LineNumberList.this.currentLine = line;
                }
            } else {
                try {
                    int y = LineNumberList.this.textArea.yForLineContaining(dot);
                    if (y != LineNumberList.this.lastY) {
                        LineNumberList.this.lastY = y;
                        LineNumberList.this.currentLine = LineNumberList.this.textArea.getDocument().getDefaultRootElement().getElementIndex(dot);
                        LineNumberList.this.repaint();
                    }
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
            }
        }

        public void install(RTextArea textArea) {
            if (!this.installed) {
                textArea.addCaretListener(this);
                textArea.addPropertyChangeListener(this);
                this.caretUpdate(null);
                this.installed = true;
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("RTA.currentLineHighlight".equals(name) || "RTA.currentLineHighlightColor".equals(name)) {
                LineNumberList.this.repaintLine(LineNumberList.this.currentLine);
            }
        }

        public void uninstall(RTextArea textArea) {
            if (this.installed) {
                textArea.removeCaretListener(this);
                textArea.removePropertyChangeListener(this);
                this.installed = false;
            }
        }
    }
}

