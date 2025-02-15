/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.LayeredHighlighter;
import javax.swing.text.Position;
import javax.swing.text.View;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rtextarea.ChangeableHighlightPainter;
import org.fife.ui.rtextarea.RTextArea;

public class RTextAreaHighlighter
extends BasicTextUI.BasicHighlighter {
    protected RTextArea textArea;
    private List<HighlightInfo> markAllHighlights = new ArrayList<HighlightInfo>();

    Object addMarkAllHighlight(int start, int end, Highlighter.HighlightPainter p) throws BadLocationException {
        Document doc = this.textArea.getDocument();
        TextUI mapper = this.textArea.getUI();
        LayeredHighlightInfoImpl i = new LayeredHighlightInfoImpl();
        i.setPainter(p);
        ((HighlightInfoImpl)i).p0 = doc.createPosition(start);
        ((HighlightInfoImpl)i).p1 = doc.createPosition(end - 1);
        this.markAllHighlights.add(i);
        mapper.damageRange(this.textArea, start, end);
        return i;
    }

    void clearMarkAllHighlights() {
        for (HighlightInfo info : this.markAllHighlights) {
            this.repaintListHighlight(info);
        }
        this.markAllHighlights.clear();
    }

    @Override
    public void deinstall(JTextComponent c) {
        this.textArea = null;
        this.markAllHighlights.clear();
    }

    public int getMarkAllHighlightCount() {
        return this.markAllHighlights.size();
    }

    public List<DocumentRange> getMarkAllHighlightRanges() {
        ArrayList<DocumentRange> list = new ArrayList<DocumentRange>(this.markAllHighlights.size());
        for (HighlightInfo info : this.markAllHighlights) {
            int start = info.getStartOffset();
            int end = info.getEndOffset() + 1;
            DocumentRange range = new DocumentRange(start, end);
            list.add(range);
        }
        return list;
    }

    @Override
    public void install(JTextComponent c) {
        super.install(c);
        this.textArea = (RTextArea)c;
    }

    @Override
    public void paintLayeredHighlights(Graphics g, int lineStart, int lineEnd, Shape viewBounds, JTextComponent editor, View view) {
        this.paintListLayered(g, lineStart, lineEnd, viewBounds, editor, view, this.markAllHighlights);
        super.paintLayeredHighlights(g, lineStart, lineEnd, viewBounds, editor, view);
    }

    protected void paintListLayered(Graphics g, int lineStart, int lineEnd, Shape viewBounds, JTextComponent editor, View view, List<? extends HighlightInfo> highlights) {
        for (int i = highlights.size() - 1; i >= 0; --i) {
            HighlightInfo tag = highlights.get(i);
            if (!(tag instanceof LayeredHighlightInfo)) continue;
            LayeredHighlightInfo lhi = (LayeredHighlightInfo)tag;
            int highlightStart = lhi.getStartOffset();
            int highlightEnd = lhi.getEndOffset() + 1;
            if ((lineStart >= highlightStart || lineEnd <= highlightStart) && (lineStart < highlightStart || lineStart >= highlightEnd)) continue;
            lhi.paintLayeredHighlights(g, lineStart, lineEnd, viewBounds, editor, view);
        }
    }

    protected void repaintListHighlight(HighlightInfo info) {
        if (info instanceof LayeredHighlightInfoImpl) {
            LayeredHighlightInfoImpl lhi = (LayeredHighlightInfoImpl)info;
            if (lhi.width > 0 && lhi.height > 0) {
                this.textArea.repaint(lhi.x, lhi.y, lhi.width, lhi.height);
            }
        } else {
            TextUI ui = this.textArea.getUI();
            ui.damageRange(this.textArea, info.getStartOffset(), info.getEndOffset());
        }
    }

    protected static class LayeredHighlightInfoImpl
    extends HighlightInfoImpl
    implements LayeredHighlightInfo {
        public int x;
        public int y;
        public int width;
        public int height;

        protected LayeredHighlightInfoImpl() {
        }

        void union(Shape bounds) {
            Rectangle alloc;
            if (bounds == null) {
                return;
            }
            Rectangle rectangle = alloc = bounds instanceof Rectangle ? (Rectangle)bounds : bounds.getBounds();
            if (this.width == 0 || this.height == 0) {
                this.x = alloc.x;
                this.y = alloc.y;
                this.width = alloc.width;
                this.height = alloc.height;
            } else {
                this.width = Math.max(this.x + this.width, alloc.x + alloc.width);
                this.height = Math.max(this.y + this.height, alloc.y + alloc.height);
                this.x = Math.min(this.x, alloc.x);
                this.width -= this.x;
                this.y = Math.min(this.y, alloc.y);
                this.height -= this.y;
            }
        }

        @Override
        public void paintLayeredHighlights(Graphics g, int p0, int p1, Shape viewBounds, JTextComponent editor, View view) {
            int start = this.getStartOffset();
            int end = this.getEndOffset();
            p0 = Math.max(start, p0);
            p1 = Math.min(++end, p1);
            if (this.getColor() != null && this.getPainter() instanceof ChangeableHighlightPainter) {
                ((ChangeableHighlightPainter)this.getPainter()).setPaint(this.getColor());
            }
            this.union(((LayeredHighlighter.LayerPainter)this.getPainter()).paintLayer(g, p0, p1, viewBounds, editor, view));
        }
    }

    protected static class HighlightInfoImpl
    implements HighlightInfo {
        private Position p0;
        private Position p1;
        private Highlighter.HighlightPainter painter;

        protected HighlightInfoImpl() {
        }

        public Color getColor() {
            return null;
        }

        @Override
        public int getStartOffset() {
            return this.p0.getOffset();
        }

        @Override
        public int getEndOffset() {
            return this.p1.getOffset();
        }

        @Override
        public Highlighter.HighlightPainter getPainter() {
            return this.painter;
        }

        public void setStartOffset(Position startOffset) {
            this.p0 = startOffset;
        }

        public void setEndOffset(Position endOffset) {
            this.p1 = endOffset;
        }

        public void setPainter(Highlighter.HighlightPainter painter) {
            this.painter = painter;
        }
    }

    public static interface LayeredHighlightInfo
    extends HighlightInfo {
        public void paintLayeredHighlights(Graphics var1, int var2, int var3, Shape var4, JTextComponent var5, View var6);
    }

    public static interface HighlightInfo
    extends Highlighter.Highlight {
    }
}

