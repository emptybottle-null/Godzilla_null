/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.View;
import org.fife.ui.rtextarea.AbstractGutterComponent;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.GutterIconInfo;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextAreaUI;

public class IconRowHeader
extends AbstractGutterComponent
implements MouseListener {
    protected List<GutterIconImpl> trackingIcons;
    protected int width;
    private boolean bookmarkingEnabled;
    private Icon bookmarkIcon;
    protected Rectangle visibleRect;
    protected Insets textAreaInsets;
    protected int activeLineRangeStart;
    protected int activeLineRangeEnd;
    private Color activeLineRangeColor;
    private boolean inheritsGutterBackground;

    public IconRowHeader(RTextArea textArea) {
        super(textArea);
    }

    public GutterIconInfo addOffsetTrackingIcon(int offs, Icon icon) throws BadLocationException {
        return this.addOffsetTrackingIcon(offs, icon, null);
    }

    public GutterIconInfo addOffsetTrackingIcon(int offs, Icon icon, String tip) throws BadLocationException {
        int index;
        if (offs < 0 || offs > this.textArea.getDocument().getLength()) {
            throw new BadLocationException("Offset " + offs + " not in required range of 0-" + this.textArea.getDocument().getLength(), offs);
        }
        Position pos = this.textArea.getDocument().createPosition(offs);
        GutterIconImpl ti = new GutterIconImpl(icon, pos, tip);
        if (this.trackingIcons == null) {
            this.trackingIcons = new ArrayList<GutterIconImpl>(1);
        }
        if ((index = Collections.binarySearch(this.trackingIcons, ti)) < 0) {
            index = -(index + 1);
        }
        this.trackingIcons.add(index, ti);
        this.repaint();
        return ti;
    }

    public void clearActiveLineRange() {
        if (this.activeLineRangeStart != -1 || this.activeLineRangeEnd != -1) {
            this.activeLineRangeEnd = -1;
            this.activeLineRangeStart = -1;
            this.repaint();
        }
    }

    public Color getActiveLineRangeColor() {
        return this.activeLineRangeColor;
    }

    public Icon getBookmarkIcon() {
        return this.bookmarkIcon;
    }

    public GutterIconInfo[] getBookmarks() {
        ArrayList<GutterIconImpl> retVal = new ArrayList<GutterIconImpl>(1);
        if (this.trackingIcons != null) {
            for (int i = 0; i < this.trackingIcons.size(); ++i) {
                GutterIconImpl ti = this.getTrackingIcon(i);
                if (ti.getIcon() != this.bookmarkIcon) continue;
                retVal.add(ti);
            }
        }
        GutterIconInfo[] array = new GutterIconInfo[retVal.size()];
        return retVal.toArray(array);
    }

    @Override
    void handleDocumentEvent(DocumentEvent e) {
        int newLineCount = this.textArea.getLineCount();
        if (newLineCount != this.currentLineCount) {
            this.currentLineCount = newLineCount;
            this.repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int h = this.textArea != null ? this.textArea.getHeight() : 100;
        return new Dimension(this.width, h);
    }

    @Override
    public String getToolTipText(MouseEvent e) {
        try {
            GutterIconInfo[] infos;
            int line = this.viewToModelLine(e.getPoint());
            if (line > -1 && (infos = this.getTrackingIcons(line)).length > 0) {
                return infos[infos.length - 1].getToolTip();
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return null;
    }

    protected GutterIconImpl getTrackingIcon(int index) {
        return this.trackingIcons.get(index);
    }

    public GutterIconInfo[] getTrackingIcons(int line) throws BadLocationException {
        ArrayList<GutterIconImpl> retVal = new ArrayList<GutterIconImpl>(1);
        if (this.trackingIcons != null) {
            int start = this.textArea.getLineStartOffset(line);
            int end = this.textArea.getLineEndOffset(line);
            if (line == this.textArea.getLineCount() - 1) {
                ++end;
            }
            for (int i = 0; i < this.trackingIcons.size(); ++i) {
                GutterIconImpl ti = this.getTrackingIcon(i);
                int offs = ti.getMarkedOffset();
                if (offs >= start && offs < end) {
                    retVal.add(ti);
                    continue;
                }
                if (offs >= end) break;
            }
        }
        GutterIconInfo[] array = new GutterIconInfo[retVal.size()];
        return retVal.toArray(array);
    }

    @Override
    protected void init() {
        super.init();
        this.visibleRect = new Rectangle();
        this.width = 16;
        this.addMouseListener(this);
        this.activeLineRangeEnd = -1;
        this.activeLineRangeStart = -1;
        this.setActiveLineRangeColor(null);
        this.updateBackground();
        ToolTipManager.sharedInstance().registerComponent(this);
    }

    public boolean isBookmarkingEnabled() {
        return this.bookmarkingEnabled;
    }

    @Override
    void lineHeightsChanged() {
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.bookmarkingEnabled && this.bookmarkIcon != null) {
            try {
                int line = this.viewToModelLine(e.getPoint());
                if (line > -1) {
                    this.toggleBookmark(line);
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
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
        this.paintBackgroundImpl(g, this.visibleRect);
        if (this.textArea.getLineWrap()) {
            this.paintComponentWrapped(g);
            return;
        }
        Document doc = this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        this.textAreaInsets = this.textArea.getInsets(this.textAreaInsets);
        if (this.visibleRect.y < this.textAreaInsets.top) {
            this.visibleRect.height -= this.textAreaInsets.top - this.visibleRect.y;
            this.visibleRect.y = this.textAreaInsets.top;
        }
        int cellHeight = this.textArea.getLineHeight();
        int topLine = (this.visibleRect.y - this.textAreaInsets.top) / cellHeight;
        int bottomLine = Math.min(topLine + this.visibleRect.height / cellHeight + 1, root.getElementCount());
        int y = topLine * cellHeight + this.textAreaInsets.top;
        if (this.activeLineRangeStart >= topLine && this.activeLineRangeStart <= bottomLine || this.activeLineRangeEnd >= topLine && this.activeLineRangeEnd <= bottomLine || this.activeLineRangeStart <= topLine && this.activeLineRangeEnd >= bottomLine) {
            g.setColor(this.activeLineRangeColor);
            int firstLine = Math.max(this.activeLineRangeStart, topLine);
            int y1 = firstLine * cellHeight + this.textAreaInsets.top;
            int lastLine = Math.min(this.activeLineRangeEnd, bottomLine);
            int y2 = (lastLine + 1) * cellHeight + this.textAreaInsets.top - 1;
            for (int j = y1; j <= y2; j += 2) {
                int yEnd = Math.min(y2, j + this.getWidth());
                int xEnd = yEnd - j;
                g.drawLine(0, j, xEnd, yEnd);
            }
            for (int i = 2; i < this.getWidth(); i += 2) {
                int yEnd = y1 + this.getWidth() - i;
                g.drawLine(i, y1, this.getWidth(), yEnd);
            }
            if (firstLine == this.activeLineRangeStart) {
                g.drawLine(0, y1, this.getWidth(), y1);
            }
            if (lastLine == this.activeLineRangeEnd) {
                g.drawLine(0, y2, this.getWidth(), y2);
            }
        }
        if (this.trackingIcons != null) {
            int lastLine = bottomLine;
            for (int i = this.trackingIcons.size() - 1; i >= 0; --i) {
                GutterIconImpl ti = this.getTrackingIcon(i);
                int offs = ti.getMarkedOffset();
                if (offs < 0 || offs > doc.getLength()) continue;
                int line = root.getElementIndex(offs);
                if (line <= lastLine && line >= topLine) {
                    Icon icon = ti.getIcon();
                    if (icon == null) continue;
                    int y2 = y + (line - topLine) * cellHeight;
                    ti.getIcon().paintIcon(this, g, 0, y2 += (cellHeight - icon.getIconHeight()) / 2);
                    lastLine = line - 1;
                    continue;
                }
                if (line < topLine) break;
            }
        }
    }

    protected void paintBackgroundImpl(Graphics g, Rectangle visibleRect) {
        Color bg = this.getBackground();
        if (this.inheritsGutterBackground && this.getGutter() != null) {
            bg = this.getGutter().getBackground();
        }
        g.setColor(bg);
        g.fillRect(0, visibleRect.y, this.width, visibleRect.height);
    }

    private void paintComponentWrapped(Graphics g) {
        RTextAreaUI ui = (RTextAreaUI)this.textArea.getUI();
        View v = ui.getRootView(this.textArea).getView(0);
        Document doc = this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        int lineCount = root.getElementCount();
        int topPosition = this.textArea.viewToModel(new Point(this.visibleRect.x, this.visibleRect.y));
        int topLine = root.getElementIndex(topPosition);
        Rectangle visibleEditorRect = ui.getVisibleEditorRect();
        Rectangle r = IconRowHeader.getChildViewBounds(v, topLine, visibleEditorRect);
        int y = r.y;
        int visibleBottom = this.visibleRect.y + this.visibleRect.height;
        int currentIcon = -1;
        if (this.trackingIcons != null) {
            for (int i = 0; i < this.trackingIcons.size(); ++i) {
                int line;
                GutterIconImpl icon = this.getTrackingIcon(i);
                int offs = icon.getMarkedOffset();
                if (offs < 0 || offs > doc.getLength() || (line = root.getElementIndex(offs)) < topLine) continue;
                currentIcon = i;
                break;
            }
        }
        g.setColor(this.getForeground());
        int cellHeight = this.textArea.getLineHeight();
        while (y < visibleBottom) {
            r = IconRowHeader.getChildViewBounds(v, topLine, visibleEditorRect);
            if (currentIcon > -1) {
                Icon icon;
                GutterIconImpl toPaint = null;
                while (currentIcon < this.trackingIcons.size()) {
                    GutterIconImpl ti = this.getTrackingIcon(currentIcon);
                    int offs = ti.getMarkedOffset();
                    if (offs >= 0 && offs <= doc.getLength()) {
                        int line = root.getElementIndex(offs);
                        if (line == topLine) {
                            toPaint = ti;
                        } else if (line > topLine) break;
                    }
                    ++currentIcon;
                }
                if (toPaint != null && (icon = toPaint.getIcon()) != null) {
                    int y2 = y + (cellHeight - icon.getIconHeight()) / 2;
                    icon.paintIcon(this, g, 0, y2);
                }
            }
            y += r.height;
            if (++topLine < lineCount) continue;
            break;
        }
    }

    public void removeTrackingIcon(GutterIconInfo tag) {
        if (this.trackingIcons != null && this.trackingIcons.remove(tag)) {
            this.repaint();
        }
    }

    public void removeAllTrackingIcons() {
        if (this.trackingIcons != null && this.trackingIcons.size() > 0) {
            this.trackingIcons.clear();
            this.repaint();
        }
    }

    private void removeBookmarkTrackingIcons() {
        if (this.trackingIcons != null) {
            this.trackingIcons.removeIf(ti -> ti.getIcon() == this.bookmarkIcon);
        }
    }

    public void setActiveLineRange(int startLine, int endLine) {
        if (startLine != this.activeLineRangeStart || endLine != this.activeLineRangeEnd) {
            this.activeLineRangeStart = startLine;
            this.activeLineRangeEnd = endLine;
            this.repaint();
        }
    }

    public void setActiveLineRangeColor(Color color) {
        if (color == null) {
            color = Gutter.DEFAULT_ACTIVE_LINE_RANGE_COLOR;
        }
        if (!color.equals(this.activeLineRangeColor)) {
            this.activeLineRangeColor = color;
            this.repaint();
        }
    }

    public void setBookmarkIcon(Icon icon) {
        this.removeBookmarkTrackingIcons();
        this.bookmarkIcon = icon;
        this.repaint();
    }

    public void setBookmarkingEnabled(boolean enabled) {
        if (enabled != this.bookmarkingEnabled) {
            this.bookmarkingEnabled = enabled;
            if (!enabled) {
                this.removeBookmarkTrackingIcons();
            }
            this.repaint();
        }
    }

    public void setInheritsGutterBackground(boolean inherits) {
        if (inherits != this.inheritsGutterBackground) {
            this.inheritsGutterBackground = inherits;
            this.repaint();
        }
    }

    @Override
    public void setTextArea(RTextArea textArea) {
        this.removeAllTrackingIcons();
        super.setTextArea(textArea);
    }

    public boolean toggleBookmark(int line) throws BadLocationException {
        if (!this.isBookmarkingEnabled() || this.getBookmarkIcon() == null) {
            return false;
        }
        GutterIconInfo[] icons = this.getTrackingIcons(line);
        if (icons.length == 0) {
            int offs = this.textArea.getLineStartOffset(line);
            this.addOffsetTrackingIcon(offs, this.bookmarkIcon);
            return true;
        }
        boolean found = false;
        for (GutterIconInfo icon : icons) {
            if (icon.getIcon() != this.bookmarkIcon) continue;
            this.removeTrackingIcon(icon);
            found = true;
        }
        if (!found) {
            int offs = this.textArea.getLineStartOffset(line);
            this.addOffsetTrackingIcon(offs, this.bookmarkIcon);
        }
        return !found;
    }

    private void updateBackground() {
        Color bg = UIManager.getColor("Panel.background");
        if (bg == null) {
            bg = new JPanel().getBackground();
        }
        this.setBackground(bg);
    }

    @Override
    public void updateUI() {
        super.updateUI();
        this.updateBackground();
    }

    private int viewToModelLine(Point p) throws BadLocationException {
        int offs = this.textArea.viewToModel(p);
        return offs > -1 ? this.textArea.getLineOfOffset(offs) : -1;
    }

    private static class GutterIconImpl
    implements GutterIconInfo,
    Comparable<GutterIconInfo> {
        private Icon icon;
        private Position pos;
        private String toolTip;

        GutterIconImpl(Icon icon, Position pos, String toolTip) {
            this.icon = icon;
            this.pos = pos;
            this.toolTip = toolTip;
        }

        @Override
        public int compareTo(GutterIconInfo other) {
            if (other != null) {
                return this.pos.getOffset() - other.getMarkedOffset();
            }
            return -1;
        }

        public boolean equals(Object o) {
            return o == this;
        }

        @Override
        public Icon getIcon() {
            return this.icon;
        }

        @Override
        public int getMarkedOffset() {
            return this.pos.getOffset();
        }

        @Override
        public String getToolTip() {
            return this.toolTip;
        }

        public int hashCode() {
            return this.icon.hashCode();
        }
    }
}

