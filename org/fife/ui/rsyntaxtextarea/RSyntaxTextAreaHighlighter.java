/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;
import org.fife.ui.rsyntaxtextarea.DocumentRange;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;
import org.fife.ui.rtextarea.RTextAreaHighlighter;
import org.fife.ui.rtextarea.SmartHighlightPainter;

public class RSyntaxTextAreaHighlighter
extends RTextAreaHighlighter {
    private List<SyntaxLayeredHighlightInfoImpl> markedOccurrences = new ArrayList<SyntaxLayeredHighlightInfoImpl>();
    private List<SyntaxLayeredHighlightInfoImpl> parserHighlights = new ArrayList<SyntaxLayeredHighlightInfoImpl>(0);
    private static final Color DEFAULT_PARSER_NOTICE_COLOR = Color.RED;

    Object addMarkedOccurrenceHighlight(int start, int end, SmartHighlightPainter p) throws BadLocationException {
        Document doc = this.textArea.getDocument();
        TextUI mapper = this.textArea.getUI();
        SyntaxLayeredHighlightInfoImpl i = new SyntaxLayeredHighlightInfoImpl();
        i.setPainter(p);
        i.setStartOffset(doc.createPosition(start));
        i.setEndOffset(doc.createPosition(end - 1));
        this.markedOccurrences.add(i);
        mapper.damageRange(this.textArea, start, end);
        return i;
    }

    RTextAreaHighlighter.HighlightInfo addParserHighlight(ParserNotice notice, Highlighter.HighlightPainter p) throws BadLocationException {
        Document doc = this.textArea.getDocument();
        TextUI mapper = this.textArea.getUI();
        int start = notice.getOffset();
        int end = 0;
        if (start == -1) {
            int line = notice.getLine();
            Element root = doc.getDefaultRootElement();
            if (line >= 0 && line < root.getElementCount()) {
                Element elem = root.getElement(line);
                start = elem.getStartOffset();
                end = elem.getEndOffset();
            }
        } else {
            end = start + notice.getLength();
        }
        SyntaxLayeredHighlightInfoImpl i = new SyntaxLayeredHighlightInfoImpl();
        i.setPainter(p);
        i.setStartOffset(doc.createPosition(start));
        i.setEndOffset(doc.createPosition(end - 1));
        i.notice = notice;
        this.parserHighlights.add(i);
        mapper.damageRange(this.textArea, start, end);
        return i;
    }

    void clearMarkOccurrencesHighlights() {
        for (RTextAreaHighlighter.HighlightInfo highlightInfo : this.markedOccurrences) {
            this.repaintListHighlight(highlightInfo);
        }
        this.markedOccurrences.clear();
    }

    void clearParserHighlights() {
        for (SyntaxLayeredHighlightInfoImpl parserHighlight : this.parserHighlights) {
            this.repaintListHighlight(parserHighlight);
        }
        this.parserHighlights.clear();
    }

    public void clearParserHighlights(Parser parser) {
        Iterator<SyntaxLayeredHighlightInfoImpl> i = this.parserHighlights.iterator();
        while (i.hasNext()) {
            SyntaxLayeredHighlightInfoImpl info = i.next();
            if (info.notice.getParser() != parser) continue;
            if (info.width > 0 && info.height > 0) {
                this.textArea.repaint(info.x, info.y, info.width, info.height);
            }
            i.remove();
        }
    }

    @Override
    public void deinstall(JTextComponent c) {
        super.deinstall(c);
        this.markedOccurrences.clear();
        this.parserHighlights.clear();
    }

    public List<DocumentRange> getMarkedOccurrences() {
        ArrayList<DocumentRange> list = new ArrayList<DocumentRange>(this.markedOccurrences.size());
        for (RTextAreaHighlighter.HighlightInfo highlightInfo : this.markedOccurrences) {
            int end;
            int start = highlightInfo.getStartOffset();
            if (start > (end = highlightInfo.getEndOffset() + 1)) continue;
            DocumentRange range = new DocumentRange(start, end);
            list.add(range);
        }
        return list;
    }

    @Override
    public void paintLayeredHighlights(Graphics g, int lineStart, int lineEnd, Shape viewBounds, JTextComponent editor, View view) {
        this.paintListLayered(g, lineStart, lineEnd, viewBounds, editor, view, this.markedOccurrences);
        super.paintLayeredHighlights(g, lineStart, lineEnd, viewBounds, editor, view);
    }

    public void paintParserHighlights(Graphics g, int lineStart, int lineEnd, Shape viewBounds, JTextComponent editor, View view) {
        this.paintListLayered(g, lineStart, lineEnd, viewBounds, editor, view, this.parserHighlights);
    }

    void removeParserHighlight(RTextAreaHighlighter.HighlightInfo tag) {
        this.repaintListHighlight(tag);
        this.parserHighlights.remove(tag);
    }

    private static class SyntaxLayeredHighlightInfoImpl
    extends RTextAreaHighlighter.LayeredHighlightInfoImpl {
        private ParserNotice notice;

        private SyntaxLayeredHighlightInfoImpl() {
        }

        @Override
        public Color getColor() {
            Color color = null;
            if (this.notice != null && (color = this.notice.getColor()) == null) {
                color = DEFAULT_PARSER_NOTICE_COLOR;
            }
            return color;
        }

        public String toString() {
            return "[SyntaxLayeredHighlightInfoImpl: startOffs=" + this.getStartOffset() + ", endOffs=" + this.getEndOffset() + ", color=" + this.getColor() + "]";
        }
    }
}

