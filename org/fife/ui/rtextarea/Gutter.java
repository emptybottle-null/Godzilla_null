/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.ActiveLineRangeEvent;
import org.fife.ui.rsyntaxtextarea.ActiveLineRangeListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.AbstractGutterComponent;
import org.fife.ui.rtextarea.FoldIndicator;
import org.fife.ui.rtextarea.GutterIconInfo;
import org.fife.ui.rtextarea.IconRowHeader;
import org.fife.ui.rtextarea.LineNumberList;
import org.fife.ui.rtextarea.RDocument;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextAreaEditorKit;

public class Gutter
extends JPanel {
    public static final Color DEFAULT_ACTIVE_LINE_RANGE_COLOR = new Color(51, 153, 255);
    private RTextArea textArea;
    private LineNumberList lineNumberList;
    private Color lineNumberColor;
    private int lineNumberingStartIndex = 1;
    private Font lineNumberFont;
    private IconRowHeader iconArea;
    private boolean iconRowHeaderInheritsGutterBackground = false;
    private int spacingBetweenLineNumbersAndFoldIndicator;
    private FoldIndicator foldIndicator;
    private transient TextAreaListener listener = new TextAreaListener();

    public Gutter(RTextArea textArea) {
        this.lineNumberColor = Color.gray;
        this.lineNumberFont = RTextArea.getDefaultFont();
        this.setTextArea(textArea);
        this.setLayout(new BorderLayout());
        if (this.textArea != null) {
            this.setLineNumbersEnabled(true);
            if (this.textArea instanceof RSyntaxTextArea) {
                RSyntaxTextArea rsta = (RSyntaxTextArea)this.textArea;
                this.setFoldIndicatorEnabled(rsta.isCodeFoldingEnabled());
            }
        }
        this.setBorder(new GutterBorder(0, 0, 0, 1));
        Color bg = null;
        if (textArea != null) {
            bg = textArea.getBackground();
        }
        this.setBackground(bg != null ? bg : Color.WHITE);
    }

    public GutterIconInfo addLineTrackingIcon(int line, Icon icon) throws BadLocationException {
        return this.addLineTrackingIcon(line, icon, null);
    }

    public GutterIconInfo addLineTrackingIcon(int line, Icon icon, String tip) throws BadLocationException {
        int offs = this.textArea.getLineStartOffset(line);
        return this.addOffsetTrackingIcon(offs, icon, tip);
    }

    public GutterIconInfo addOffsetTrackingIcon(int offs, Icon icon) throws BadLocationException {
        return this.addOffsetTrackingIcon(offs, icon, null);
    }

    public GutterIconInfo addOffsetTrackingIcon(int offs, Icon icon, String tip) throws BadLocationException {
        return this.iconArea.addOffsetTrackingIcon(offs, icon, tip);
    }

    private void clearActiveLineRange() {
        this.iconArea.clearActiveLineRange();
    }

    public Color getActiveLineRangeColor() {
        return this.iconArea.getActiveLineRangeColor();
    }

    public Color getArmedFoldBackground() {
        return this.foldIndicator.getFoldIconArmedBackground();
    }

    public Icon getBookmarkIcon() {
        return this.iconArea.getBookmarkIcon();
    }

    public GutterIconInfo[] getBookmarks() {
        return this.iconArea.getBookmarks();
    }

    public Color getBorderColor() {
        return ((GutterBorder)this.getBorder()).getColor();
    }

    public Color getFoldBackground() {
        return this.foldIndicator.getFoldIconBackground();
    }

    public Color getFoldIndicatorForeground() {
        return this.foldIndicator.getForeground();
    }

    public boolean getIconRowHeaderInheritsGutterBackground() {
        return this.iconRowHeaderInheritsGutterBackground;
    }

    public Color getLineNumberColor() {
        return this.lineNumberColor;
    }

    public Font getLineNumberFont() {
        return this.lineNumberFont;
    }

    public int getLineNumberingStartIndex() {
        return this.lineNumberingStartIndex;
    }

    public boolean getLineNumbersEnabled() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            if (this.getComponent(i) != this.lineNumberList) continue;
            return true;
        }
        return false;
    }

    public boolean getShowCollapsedRegionToolTips() {
        return this.foldIndicator.getShowCollapsedRegionToolTips();
    }

    public int getSpacingBetweenLineNumbersAndFoldIndicator() {
        return this.spacingBetweenLineNumbersAndFoldIndicator;
    }

    public GutterIconInfo[] getTrackingIcons(Point p) throws BadLocationException {
        int offs = this.textArea.viewToModel(new Point(0, p.y));
        int line = this.textArea.getLineOfOffset(offs);
        return this.iconArea.getTrackingIcons(line);
    }

    public boolean isFoldIndicatorEnabled() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            if (this.getComponent(i) != this.foldIndicator) continue;
            return true;
        }
        return false;
    }

    public boolean isBookmarkingEnabled() {
        return this.iconArea.isBookmarkingEnabled();
    }

    public boolean isIconRowHeaderEnabled() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            if (this.getComponent(i) != this.iconArea) continue;
            return true;
        }
        return false;
    }

    public void removeAllTrackingIcons() {
        this.iconArea.removeAllTrackingIcons();
    }

    public void removeTrackingIcon(GutterIconInfo tag) {
        this.iconArea.removeTrackingIcon(tag);
    }

    public void setActiveLineRangeColor(Color color) {
        this.iconArea.setActiveLineRangeColor(color);
    }

    private void setActiveLineRange(int startLine, int endLine) {
        this.iconArea.setActiveLineRange(startLine, endLine);
    }

    public void setArmedFoldBackground(Color bg) {
        this.foldIndicator.setFoldIconArmedBackground(bg);
    }

    public void setBookmarkIcon(Icon icon) {
        this.iconArea.setBookmarkIcon(icon);
    }

    public void setBookmarkingEnabled(boolean enabled) {
        this.iconArea.setBookmarkingEnabled(enabled);
        if (enabled && !this.isIconRowHeaderEnabled()) {
            this.setIconRowHeaderEnabled(true);
        }
    }

    public void setBorderColor(Color color) {
        ((GutterBorder)this.getBorder()).setColor(color);
        this.repaint();
    }

    @Override
    public void setComponentOrientation(ComponentOrientation o) {
        if (this.getBorder() instanceof GutterBorder) {
            if (o.isLeftToRight()) {
                ((GutterBorder)this.getBorder()).setEdges(0, 0, 0, 1);
            } else {
                ((GutterBorder)this.getBorder()).setEdges(0, 1, 0, 0);
            }
        }
        super.setComponentOrientation(o);
    }

    public void setFoldIcons(Icon collapsedIcon, Icon expandedIcon) {
        if (this.foldIndicator != null) {
            this.foldIndicator.setFoldIcons(collapsedIcon, expandedIcon);
        }
    }

    public void setFoldIndicatorEnabled(boolean enabled) {
        if (this.foldIndicator != null) {
            if (enabled) {
                this.add((Component)this.foldIndicator, "After");
            } else {
                this.remove(this.foldIndicator);
            }
            this.revalidate();
        }
    }

    public void setFoldBackground(Color bg) {
        if (bg == null) {
            bg = FoldIndicator.DEFAULT_FOLD_BACKGROUND;
        }
        this.foldIndicator.setFoldIconBackground(bg);
    }

    public void setFoldIndicatorForeground(Color fg) {
        if (fg == null) {
            fg = FoldIndicator.DEFAULT_FOREGROUND;
        }
        this.foldIndicator.setForeground(fg);
    }

    void setIconRowHeaderEnabled(boolean enabled) {
        if (this.iconArea != null) {
            if (enabled) {
                this.add((Component)this.iconArea, "Before");
            } else {
                this.remove(this.iconArea);
            }
            this.revalidate();
        }
    }

    public void setIconRowHeaderInheritsGutterBackground(boolean inherits) {
        if (inherits != this.iconRowHeaderInheritsGutterBackground) {
            this.iconRowHeaderInheritsGutterBackground = inherits;
            if (this.iconArea != null) {
                this.iconArea.setInheritsGutterBackground(inherits);
            }
        }
    }

    public void setLineNumberColor(Color color) {
        if (color != null && !color.equals(this.lineNumberColor)) {
            this.lineNumberColor = color;
            if (this.lineNumberList != null) {
                this.lineNumberList.setForeground(color);
            }
        }
    }

    public void setLineNumberFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("font cannot be null");
        }
        if (!font.equals(this.lineNumberFont)) {
            this.lineNumberFont = font;
            if (this.lineNumberList != null) {
                this.lineNumberList.setFont(font);
            }
        }
    }

    public void setLineNumberingStartIndex(int index) {
        if (index != this.lineNumberingStartIndex) {
            this.lineNumberingStartIndex = index;
            this.lineNumberList.setLineNumberingStartIndex(index);
        }
    }

    void setLineNumbersEnabled(boolean enabled) {
        if (this.lineNumberList != null) {
            if (enabled) {
                this.add(this.lineNumberList);
            } else {
                this.remove(this.lineNumberList);
            }
            this.revalidate();
        }
    }

    public void setShowCollapsedRegionToolTips(boolean show) {
        if (this.foldIndicator != null) {
            this.foldIndicator.setShowCollapsedRegionToolTips(show);
        }
    }

    public void setSpacingBetweenLineNumbersAndFoldIndicator(int spacing) {
        if (spacing != this.spacingBetweenLineNumbersAndFoldIndicator) {
            this.spacingBetweenLineNumbersAndFoldIndicator = spacing;
            this.foldIndicator.setAdditionalLeftMargin(spacing);
            this.revalidate();
            this.repaint();
        }
    }

    void setTextArea(RTextArea textArea) {
        if (this.textArea != null) {
            this.listener.uninstall();
        }
        if (textArea != null) {
            RTextAreaEditorKit kit = (RTextAreaEditorKit)textArea.getUI().getEditorKit(textArea);
            if (this.lineNumberList == null) {
                this.lineNumberList = kit.createLineNumberList(textArea);
                this.lineNumberList.setFont(this.getLineNumberFont());
                this.lineNumberList.setForeground(this.getLineNumberColor());
                this.lineNumberList.setLineNumberingStartIndex(this.getLineNumberingStartIndex());
            } else {
                this.lineNumberList.setTextArea(textArea);
            }
            if (this.iconArea == null) {
                this.iconArea = kit.createIconRowHeader(textArea);
                this.iconArea.setInheritsGutterBackground(this.getIconRowHeaderInheritsGutterBackground());
            } else {
                this.iconArea.setTextArea(textArea);
            }
            if (this.foldIndicator == null) {
                this.foldIndicator = new FoldIndicator(textArea);
            } else {
                this.foldIndicator.setTextArea(textArea);
            }
            this.listener.install(textArea);
        }
        this.textArea = textArea;
    }

    public boolean toggleBookmark(int line) throws BadLocationException {
        return this.iconArea.toggleBookmark(line);
    }

    @Override
    public void setBorder(Border border) {
        if (border instanceof GutterBorder) {
            super.setBorder(border);
        }
    }

    private class TextAreaListener
    extends ComponentAdapter
    implements DocumentListener,
    PropertyChangeListener,
    ActiveLineRangeListener {
        private boolean installed;

        private TextAreaListener() {
        }

        @Override
        public void activeLineRangeChanged(ActiveLineRangeEvent e) {
            if (e.getMin() == -1) {
                Gutter.this.clearActiveLineRange();
            } else {
                Gutter.this.setActiveLineRange(e.getMin(), e.getMax());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        @Override
        public void componentResized(ComponentEvent e) {
            Gutter.this.revalidate();
        }

        protected void handleDocumentEvent(DocumentEvent e) {
            for (int i = 0; i < Gutter.this.getComponentCount(); ++i) {
                AbstractGutterComponent agc = (AbstractGutterComponent)Gutter.this.getComponent(i);
                agc.handleDocumentEvent(e);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            this.handleDocumentEvent(e);
        }

        public void install(RTextArea textArea) {
            if (this.installed) {
                this.uninstall();
            }
            textArea.addComponentListener(this);
            textArea.getDocument().addDocumentListener(this);
            textArea.addPropertyChangeListener(this);
            if (textArea instanceof RSyntaxTextArea) {
                RSyntaxTextArea rsta = (RSyntaxTextArea)textArea;
                rsta.addActiveLineRangeListener(this);
                rsta.getFoldManager().addPropertyChangeListener(this);
            }
            this.installed = true;
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("font".equals(name) || "RSTA.syntaxScheme".equals(name)) {
                for (int i = 0; i < Gutter.this.getComponentCount(); ++i) {
                    AbstractGutterComponent agc = (AbstractGutterComponent)Gutter.this.getComponent(i);
                    agc.lineHeightsChanged();
                }
            } else if ("RSTA.codeFolding".equals(name)) {
                boolean foldingEnabled = (Boolean)e.getNewValue();
                if (Gutter.this.lineNumberList != null) {
                    Gutter.this.lineNumberList.updateCellWidths();
                }
                Gutter.this.setFoldIndicatorEnabled(foldingEnabled);
            } else if ("FoldsUpdated".equals(name)) {
                Gutter.this.repaint();
            } else if ("document".equals(name)) {
                RDocument newDoc;
                RDocument old = (RDocument)e.getOldValue();
                if (old != null) {
                    old.removeDocumentListener(this);
                }
                if ((newDoc = (RDocument)e.getNewValue()) != null) {
                    newDoc.addDocumentListener(this);
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            this.handleDocumentEvent(e);
        }

        public void uninstall() {
            if (this.installed) {
                Gutter.this.textArea.removeComponentListener(this);
                Gutter.this.textArea.getDocument().removeDocumentListener(this);
                Gutter.this.textArea.removePropertyChangeListener(this);
                if (Gutter.this.textArea instanceof RSyntaxTextArea) {
                    RSyntaxTextArea rsta = (RSyntaxTextArea)Gutter.this.textArea;
                    rsta.removeActiveLineRangeListener(this);
                    rsta.getFoldManager().removePropertyChangeListener(this);
                }
                this.installed = false;
            }
        }
    }

    public static class GutterBorder
    extends EmptyBorder {
        private Color color = new Color(221, 221, 221);
        private Rectangle visibleRect = new Rectangle();

        public GutterBorder(int top, int left, int bottom, int right) {
            super(top, left, bottom, right);
        }

        public Color getColor() {
            return this.color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            this.visibleRect = g.getClipBounds(this.visibleRect);
            if (this.visibleRect == null) {
                this.visibleRect = ((JComponent)c).getVisibleRect();
            }
            g.setColor(this.color);
            if (this.left == 1) {
                g.drawLine(0, this.visibleRect.y, 0, this.visibleRect.y + this.visibleRect.height);
            } else {
                g.drawLine(width - 1, this.visibleRect.y, width - 1, this.visibleRect.y + this.visibleRect.height);
            }
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public void setEdges(int top, int left, int bottom, int right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
    }
}

