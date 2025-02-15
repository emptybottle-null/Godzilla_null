/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import org.fife.ui.rtextarea.RTextArea;

class LineHighlightManager {
    private RTextArea textArea;
    private List<LineHighlightInfo> lineHighlights;
    private LineHighlightInfoComparator comparator;

    LineHighlightManager(RTextArea textArea) {
        this.textArea = textArea;
        this.comparator = new LineHighlightInfoComparator();
    }

    public Object addLineHighlight(int line, Color color) throws BadLocationException {
        int index;
        int offs = this.textArea.getLineStartOffset(line);
        LineHighlightInfo lhi = new LineHighlightInfo(this.textArea.getDocument().createPosition(offs), color);
        if (this.lineHighlights == null) {
            this.lineHighlights = new ArrayList<LineHighlightInfo>(1);
        }
        if ((index = Collections.binarySearch(this.lineHighlights, lhi, this.comparator)) < 0) {
            index = -(index + 1);
        }
        this.lineHighlights.add(index, lhi);
        this.repaintLine(lhi);
        return lhi;
    }

    protected List<Object> getCurrentLineHighlightTags() {
        return this.lineHighlights == null ? Collections.emptyList() : new ArrayList<LineHighlightInfo>(this.lineHighlights);
    }

    protected int getLineHighlightCount() {
        return this.lineHighlights == null ? 0 : this.lineHighlights.size();
    }

    public void paintLineHighlights(Graphics g) {
        int count;
        int n = count = this.lineHighlights == null ? 0 : this.lineHighlights.size();
        if (count > 0) {
            int docLen = this.textArea.getDocument().getLength();
            Rectangle vr = this.textArea.getVisibleRect();
            int lineHeight = this.textArea.getLineHeight();
            try {
                for (int i = 0; i < count; ++i) {
                    int y;
                    LineHighlightInfo lhi = this.lineHighlights.get(i);
                    int offs = lhi.getOffset();
                    if (offs < 0 || offs > docLen || (y = this.textArea.yForLineContaining(offs)) <= vr.y - lineHeight) continue;
                    if (y < vr.y + vr.height) {
                        g.setColor(lhi.getColor());
                        g.fillRect(0, y, this.textArea.getWidth(), lineHeight);
                        continue;
                    }
                    break;
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
    }

    public void removeAllLineHighlights() {
        if (this.lineHighlights != null) {
            this.lineHighlights.clear();
            this.textArea.repaint();
        }
    }

    public void removeLineHighlight(Object tag) {
        if (tag instanceof LineHighlightInfo) {
            this.lineHighlights.remove(tag);
            this.repaintLine((LineHighlightInfo)tag);
        }
    }

    private void repaintLine(LineHighlightInfo lhi) {
        int offs = lhi.getOffset();
        if (offs >= 0 && offs <= this.textArea.getDocument().getLength()) {
            try {
                int y = this.textArea.yForLineContaining(offs);
                if (y > -1) {
                    this.textArea.repaint(0, y, this.textArea.getWidth(), this.textArea.getLineHeight());
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
    }

    private static class LineHighlightInfoComparator
    implements Comparator<LineHighlightInfo> {
        private LineHighlightInfoComparator() {
        }

        @Override
        public int compare(LineHighlightInfo lhi1, LineHighlightInfo lhi2) {
            if (lhi1.getOffset() < lhi2.getOffset()) {
                return -1;
            }
            return lhi1.getOffset() == lhi2.getOffset() ? 0 : 1;
        }
    }

    private static class LineHighlightInfo {
        private Position offs;
        private Color color;

        LineHighlightInfo(Position offs, Color c) {
            this.offs = offs;
            this.color = c;
        }

        public boolean equals(Object other) {
            if (other instanceof LineHighlightInfo) {
                LineHighlightInfo lhi2 = (LineHighlightInfo)other;
                return this.getOffset() == lhi2.getOffset() && Objects.equals(this.getColor(), lhi2.getColor());
            }
            return false;
        }

        public Color getColor() {
            return this.color;
        }

        public int getOffset() {
            return this.offs.getOffset();
        }

        public int hashCode() {
            return this.getOffset();
        }
    }
}

