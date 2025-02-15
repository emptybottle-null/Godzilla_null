/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.text.TabExpander;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import org.fife.ui.rsyntaxtextarea.RSTAView;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;
import org.fife.ui.rsyntaxtextarea.TokenPainter;
import org.fife.ui.rsyntaxtextarea.TokenUtils;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;
import org.fife.ui.rtextarea.Gutter;

public class WrappedSyntaxView
extends BoxView
implements TabExpander,
RSTAView {
    private int tabBase;
    private int tabSize;
    private Segment s;
    private Segment drawSeg;
    private Rectangle tempRect;
    private RSyntaxTextArea host;
    private FontMetrics metrics;
    private TokenImpl tempToken = new TokenImpl();
    private TokenImpl lineCountTempToken;
    private static final int MIN_WIDTH = 20;

    public WrappedSyntaxView(Element elem) {
        super(elem, 1);
        this.s = new Segment();
        this.drawSeg = new Segment();
        this.tempRect = new Rectangle();
        this.lineCountTempToken = new TokenImpl();
    }

    protected int calculateBreakPosition(int p0, Token tokenList, float x0) {
        int p = p0;
        RSyntaxTextArea textArea = (RSyntaxTextArea)this.getContainer();
        float currentWidth = this.getWidth();
        if (currentWidth == 2.14748365E9f) {
            currentWidth = this.getPreferredSpan(0);
        }
        currentWidth = Math.max(currentWidth, 20.0f);
        for (Token t = tokenList; t != null && t.isPaintable(); t = t.getNextToken()) {
            float tokenWidth = t.getWidth(textArea, this, x0);
            if (tokenWidth > currentWidth) {
                if (p == p0) {
                    return t.getOffsetBeforeX(textArea, this, 0.0f, currentWidth);
                }
                return t.isWhitespace() ? p + t.length() : p;
            }
            currentWidth -= tokenWidth;
            x0 += tokenWidth;
            p += t.length();
        }
        return p + 1;
    }

    @Override
    public void changedUpdate(DocumentEvent e, Shape a, ViewFactory f) {
        this.updateChildren(e, a);
    }

    private void childAllocation2(int line, int y, Rectangle alloc) {
        alloc.x += this.getOffset(0, line);
        alloc.y += y;
        alloc.width = this.getSpan(0, line);
        alloc.height = this.getSpan(1, line);
        Insets margin = this.host.getMargin();
        if (margin != null) {
            alloc.y -= margin.top;
        }
    }

    protected void drawView(TokenPainter painter, Graphics2D g, Rectangle r, View view, int fontHeight, int y, int line) {
        float x = r.x;
        RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.host.getHighlighter();
        RSyntaxDocument document = (RSyntaxDocument)this.getDocument();
        Element map = this.getElement();
        int p0 = view.getStartOffset();
        int lineNumber = map.getElementIndex(p0);
        int p1 = view.getEndOffset();
        this.setSegment(p0, p1 - 1, document, this.drawSeg);
        int start = p0 - this.drawSeg.offset;
        Token token = document.getTokenListForLine(lineNumber);
        if (token != null && token.getType() == 0) {
            h.paintLayeredHighlights(g, p0, p1, r, this.host, this);
            return;
        }
        while (token != null && token.isPaintable()) {
            int p = this.calculateBreakPosition(p0, token, x);
            x = r.x;
            h.paintLayeredHighlights(g, p0, p, r, this.host, this);
            while (token != null && token.isPaintable() && token.getEndOffset() - 1 < p) {
                boolean paintBG = this.host.getPaintTokenBackgrounds(line, y);
                x = painter.paint(token, g, x, y, this.host, this, 0.0f, paintBG);
                token = token.getNextToken();
            }
            if (token != null && token.isPaintable() && token.getOffset() < p) {
                int tokenOffset = token.getOffset();
                this.tempToken.set(this.drawSeg.array, tokenOffset - start, p - 1 - start, tokenOffset, token.getType());
                this.tempToken.setLanguageIndex(token.getLanguageIndex());
                boolean paintBG = this.host.getPaintTokenBackgrounds(line, y);
                painter.paint(this.tempToken, g, x, y, this.host, this, 0.0f, paintBG);
                this.tempToken.copyFrom(token);
                this.tempToken.makeStartAt(p);
                token = new TokenImpl(this.tempToken);
            }
            h.paintParserHighlights(g, p0, p, r, this.host, this);
            p0 = p == p0 ? p1 : p;
            y += fontHeight;
        }
        if (this.host.getEOLMarkersVisible()) {
            g.setColor(this.host.getForegroundForTokenType(21));
            g.setFont(this.host.getFontForTokenType(21));
            g.drawString("\u00b6", x, (float)y - (float)fontHeight);
        }
    }

    protected void drawViewWithSelection(TokenPainter painter, Graphics2D g, Rectangle r, View view, int fontHeight, int y, int selStart, int selEnd) {
        float x = r.x;
        boolean useSTC = this.host.getUseSelectedTextColor();
        RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.host.getHighlighter();
        RSyntaxDocument document = (RSyntaxDocument)this.getDocument();
        Element map = this.getElement();
        int p0 = view.getStartOffset();
        int lineNumber = map.getElementIndex(p0);
        int p1 = view.getEndOffset();
        this.setSegment(p0, p1 - 1, document, this.drawSeg);
        int start = p0 - this.drawSeg.offset;
        Token token = document.getTokenListForLine(lineNumber);
        if (token != null && token.getType() == 0) {
            h.paintLayeredHighlights(g, p0, p1, r, this.host, this);
            return;
        }
        while (token != null && token.isPaintable()) {
            int p = this.calculateBreakPosition(p0, token, x);
            x = r.x;
            h.paintLayeredHighlights(g, p0, p, r, this.host, this);
            while (token != null && token.isPaintable() && token.getEndOffset() - 1 < p) {
                if (token.containsPosition(selStart)) {
                    int selCount;
                    if (selStart > token.getOffset()) {
                        this.tempToken.copyFrom(token);
                        this.tempToken.textCount = selStart - this.tempToken.getOffset();
                        x = painter.paint(this.tempToken, g, x, y, this.host, this);
                        this.tempToken.textCount = token.length();
                        this.tempToken.makeStartAt(selStart);
                        token = new TokenImpl(this.tempToken);
                    }
                    if ((selCount = Math.min(token.length(), selEnd - token.getOffset())) == token.length()) {
                        x = painter.paintSelected(token, g, x, y, this.host, this, useSTC);
                    } else {
                        this.tempToken.copyFrom(token);
                        this.tempToken.textCount = selCount;
                        x = painter.paintSelected(this.tempToken, g, x, y, this.host, this, useSTC);
                        this.tempToken.textCount = token.length();
                        this.tempToken.makeStartAt(token.getOffset() + selCount);
                        token = this.tempToken;
                        x = painter.paint(token, g, x, y, this.host, this);
                    }
                } else if (token.containsPosition(selEnd)) {
                    this.tempToken.copyFrom(token);
                    this.tempToken.textCount = selEnd - this.tempToken.getOffset();
                    x = painter.paintSelected(this.tempToken, g, x, y, this.host, this, useSTC);
                    this.tempToken.textCount = token.length();
                    this.tempToken.makeStartAt(selEnd);
                    token = this.tempToken;
                    x = painter.paint(token, g, x, y, this.host, this);
                } else {
                    x = token.getOffset() >= selStart && token.getEndOffset() <= selEnd ? painter.paintSelected(token, g, x, y, this.host, this, useSTC) : painter.paint(token, g, x, y, this.host, this);
                }
                token = token.getNextToken();
            }
            if (token != null && token.isPaintable() && token.getOffset() < p) {
                int tokenOffset = token.getOffset();
                Token orig = token;
                token = new TokenImpl(this.drawSeg, tokenOffset - start, p - 1 - start, tokenOffset, token.getType(), token.getLanguageIndex());
                token.setLanguageIndex(token.getLanguageIndex());
                if (token.containsPosition(selStart)) {
                    int selCount;
                    if (selStart > token.getOffset()) {
                        this.tempToken.copyFrom(token);
                        this.tempToken.textCount = selStart - this.tempToken.getOffset();
                        x = painter.paint(this.tempToken, g, x, y, this.host, this);
                        this.tempToken.textCount = token.length();
                        this.tempToken.makeStartAt(selStart);
                        token = new TokenImpl(this.tempToken);
                    }
                    if ((selCount = Math.min(token.length(), selEnd - token.getOffset())) == token.length()) {
                        x = painter.paintSelected(token, g, x, y, this.host, this, useSTC);
                    } else {
                        this.tempToken.copyFrom(token);
                        this.tempToken.textCount = selCount;
                        x = painter.paintSelected(this.tempToken, g, x, y, this.host, this, useSTC);
                        this.tempToken.textCount = token.length();
                        this.tempToken.makeStartAt(token.getOffset() + selCount);
                        token = this.tempToken;
                        x = painter.paint(token, g, x, y, this.host, this);
                    }
                } else if (token.containsPosition(selEnd)) {
                    this.tempToken.copyFrom(token);
                    this.tempToken.textCount = selEnd - this.tempToken.getOffset();
                    x = painter.paintSelected(this.tempToken, g, x, y, this.host, this, useSTC);
                    this.tempToken.textCount = token.length();
                    this.tempToken.makeStartAt(selEnd);
                    token = this.tempToken;
                    x = painter.paint(token, g, x, y, this.host, this);
                } else {
                    x = token.getOffset() >= selStart && token.getEndOffset() <= selEnd ? painter.paintSelected(token, g, x, y, this.host, this, useSTC) : painter.paint(token, g, x, y, this.host, this);
                }
                token = new TokenImpl(orig);
                ((TokenImpl)token).makeStartAt(p);
            }
            h.paintParserHighlights(g, p0, p, r, this.host, this);
            p0 = p == p0 ? p1 : p;
            y += fontHeight;
        }
        if (this.host.getEOLMarkersVisible()) {
            g.setColor(this.host.getForegroundForTokenType(21));
            g.setFont(this.host.getFontForTokenType(21));
            g.drawString("\u00b6", x, (float)y - (float)fontHeight);
        }
    }

    @Override
    public Shape getChildAllocation(int index, Shape a) {
        if (a != null) {
            Shape ca = this.getChildAllocationImpl(index, a);
            if (ca != null && !this.isAllocationValid()) {
                Rectangle r;
                Rectangle rectangle = r = ca instanceof Rectangle ? (Rectangle)ca : ca.getBounds();
                if (r.width == 0 && r.height == 0) {
                    return null;
                }
            }
            return ca;
        }
        return null;
    }

    public Shape getChildAllocationImpl(int line, Shape a) {
        Rectangle alloc = this.getInsideAllocation(a);
        this.host = (RSyntaxTextArea)this.getContainer();
        FoldManager fm = this.host.getFoldManager();
        int y = alloc.y;
        for (int i = 0; i < line; ++i) {
            y += this.getSpan(1, i);
            Fold fold = fm.getFoldForLine(i);
            if (fold == null || !fold.isCollapsed()) continue;
            i += fold.getCollapsedLineCount();
        }
        this.childAllocation2(line, y, alloc);
        return alloc;
    }

    @Override
    public float getMaximumSpan(int axis) {
        this.updateMetrics();
        float span = super.getPreferredSpan(axis);
        if (axis == 0) {
            span += (float)this.metrics.charWidth('\u00b6');
        }
        return span;
    }

    @Override
    public float getMinimumSpan(int axis) {
        this.updateMetrics();
        float span = super.getPreferredSpan(axis);
        if (axis == 0) {
            span += (float)this.metrics.charWidth('\u00b6');
        }
        return span;
    }

    @Override
    public float getPreferredSpan(int axis) {
        this.updateMetrics();
        float span = 0.0f;
        if (axis == 0) {
            span = super.getPreferredSpan(axis);
            span += (float)this.metrics.charWidth('\u00b6');
        } else {
            span = super.getPreferredSpan(axis);
            this.host = (RSyntaxTextArea)this.getContainer();
            if (this.host.isCodeFoldingEnabled()) {
                int lineCount = this.host.getLineCount();
                FoldManager fm = this.host.getFoldManager();
                for (int i = 0; i < lineCount; ++i) {
                    if (!fm.isLineHidden(i)) continue;
                    span -= (float)this.getSpan(1, i);
                }
            }
        }
        return span;
    }

    protected int getTabSize() {
        Integer i = (Integer)this.getDocument().getProperty("tabSize");
        int size = i != null ? i : 5;
        return size;
    }

    @Override
    protected View getViewAtPoint(int x, int y, Rectangle alloc) {
        int lineCount = this.getViewCount();
        int curY = alloc.y + this.getOffset(1, 0);
        this.host = (RSyntaxTextArea)this.getContainer();
        FoldManager fm = this.host.getFoldManager();
        for (int line = 1; line < lineCount; ++line) {
            int span = this.getSpan(1, line - 1);
            if (y < curY + span) {
                this.childAllocation2(line - 1, curY, alloc);
                return this.getView(line - 1);
            }
            curY += span;
            Fold fold = fm.getFoldForLine(line - 1);
            if (fold == null || !fold.isCollapsed()) continue;
            line += fold.getCollapsedLineCount();
        }
        this.childAllocation2(lineCount - 1, curY, alloc);
        return this.getView(lineCount - 1);
    }

    @Override
    public void insertUpdate(DocumentEvent changes, Shape a, ViewFactory f) {
        this.updateChildren(changes, a);
        Rectangle alloc = a != null && this.isAllocationValid() ? this.getInsideAllocation(a) : null;
        int pos = changes.getOffset();
        View v = this.getViewAtPosition(pos, alloc);
        if (v != null) {
            v.insertUpdate(changes, alloc, f);
        }
    }

    @Override
    protected void loadChildren(ViewFactory f) {
        Element e = this.getElement();
        int n = e.getElementCount();
        if (n > 0) {
            View[] added = new View[n];
            for (int i = 0; i < n; ++i) {
                added[i] = new WrappedLine(e.getElement(i));
            }
            this.replace(0, 0, added);
        }
    }

    @Override
    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        View v;
        int testPos;
        if (!this.isAllocationValid()) {
            Rectangle alloc = a.getBounds();
            this.setSize(alloc.width, alloc.height);
        }
        boolean isBackward = b == Position.Bias.Backward;
        int n = testPos = isBackward ? Math.max(0, pos - 1) : pos;
        if (isBackward && testPos < this.getStartOffset()) {
            return null;
        }
        int vIndex = this.getViewIndexAtPosition(testPos);
        if (vIndex != -1 && vIndex < this.getViewCount() && (v = this.getView(vIndex)) != null && testPos >= v.getStartOffset() && testPos < v.getEndOffset()) {
            Shape childShape = this.getChildAllocation(vIndex, a);
            if (childShape == null) {
                return null;
            }
            Shape retShape = v.modelToView(pos, childShape, b);
            if (retShape == null && v.getEndOffset() == pos && ++vIndex < this.getViewCount()) {
                v = this.getView(vIndex);
                retShape = v.modelToView(pos, this.getChildAllocation(vIndex, a), b);
            }
            return retShape;
        }
        throw new BadLocationException("Position not represented by view", pos);
    }

    @Override
    public Shape modelToView(int p0, Position.Bias b0, int p1, Position.Bias b1, Shape a) throws BadLocationException {
        Rectangle r1;
        Shape s1;
        Shape s0 = this.modelToView(p0, a, b0);
        if (p1 == this.getEndOffset()) {
            try {
                s1 = this.modelToView(p1, a, b1);
            } catch (BadLocationException ble) {
                s1 = null;
            }
            if (s1 == null) {
                Rectangle alloc = a instanceof Rectangle ? (Rectangle)a : a.getBounds();
                s1 = new Rectangle(alloc.x + alloc.width - 1, alloc.y, 1, alloc.height);
            }
        } else {
            s1 = this.modelToView(p1, a, b1);
        }
        Rectangle r0 = s0.getBounds();
        Rectangle rectangle = r1 = s1 instanceof Rectangle ? (Rectangle)s1 : s1.getBounds();
        if (r0.y != r1.y) {
            Rectangle alloc = a instanceof Rectangle ? (Rectangle)a : a.getBounds();
            r0.x = alloc.x;
            r0.width = alloc.width;
        }
        r0.add(r1);
        if (p1 > p0) {
            r0.width -= r1.width;
        }
        return r0;
    }

    @Override
    public float nextTabStop(float x, int tabOffset) {
        if (this.tabSize == 0) {
            return x;
        }
        int ntabs = ((int)x - this.tabBase) / this.tabSize;
        return (float)this.tabBase + ((float)ntabs + 1.0f) * (float)this.tabSize;
    }

    @Override
    public void paint(Graphics g, Shape a) {
        Rectangle alloc = a instanceof Rectangle ? (Rectangle)a : a.getBounds();
        this.tabBase = alloc.x;
        Graphics2D g2d = (Graphics2D)g;
        this.host = (RSyntaxTextArea)this.getContainer();
        int ascent = this.host.getMaxAscent();
        int fontHeight = this.host.getLineHeight();
        FoldManager fm = this.host.getFoldManager();
        TokenPainter painter = this.host.getTokenPainter();
        Element root = this.getElement();
        int selStart = this.host.getSelectionStart();
        int selEnd = this.host.getSelectionEnd();
        int n = this.getViewCount();
        int x = alloc.x + this.getLeftInset();
        this.tempRect.y = alloc.y + this.getTopInset();
        Rectangle clip = g.getClipBounds();
        for (int i = 0; i < n; ++i) {
            this.tempRect.x = x + this.getOffset(0, i);
            this.tempRect.width = this.getSpan(0, i);
            this.tempRect.height = this.getSpan(1, i);
            if (this.tempRect.intersects(clip)) {
                Element lineElement = root.getElement(i);
                int startOffset = lineElement.getStartOffset();
                int endOffset = lineElement.getEndOffset() - 1;
                View view = this.getView(i);
                if (selStart == selEnd || startOffset >= selEnd || endOffset < selStart) {
                    this.drawView(painter, g2d, alloc, view, fontHeight, this.tempRect.y + ascent, i);
                } else {
                    this.drawViewWithSelection(painter, g2d, alloc, view, fontHeight, this.tempRect.y + ascent, selStart, selEnd);
                }
            }
            this.tempRect.y += this.tempRect.height;
            Fold possibleFold = fm.getFoldForLine(i);
            if (possibleFold == null || !possibleFold.isCollapsed()) continue;
            i += possibleFold.getCollapsedLineCount();
            Color c = RSyntaxUtilities.getFoldedLineBottomColor(this.host);
            if (c == null) continue;
            g.setColor(c);
            g.drawLine(x, this.tempRect.y - 1, this.host.getWidth(), this.tempRect.y - 1);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent changes, Shape a, ViewFactory f) {
        this.updateChildren(changes, a);
        Rectangle alloc = a != null && this.isAllocationValid() ? this.getInsideAllocation(a) : null;
        int pos = changes.getOffset();
        View v = this.getViewAtPosition(pos, alloc);
        if (v != null) {
            v.removeUpdate(changes, alloc, f);
        }
    }

    private void setSegment(int p0, int p1, Document document, Segment seg) {
        try {
            document.getText(p0, p1 - p0, seg);
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }

    @Override
    public void setSize(float width, float height) {
        this.updateMetrics();
        if ((int)width != this.getWidth()) {
            this.preferenceChanged(null, true, true);
            this.setWidthChangePending(true);
        }
        super.setSize(width, height);
        this.setWidthChangePending(false);
    }

    private void setWidthChangePending(boolean widthChangePending) {
        int count = this.getViewCount();
        for (int i = 0; i < count; ++i) {
            View v = this.getView(i);
            if (!(v instanceof WrappedLine)) continue;
            ((WrappedLine)v).widthChangePending = widthChangePending;
        }
    }

    void updateChildren(DocumentEvent e, Shape a) {
        Element elem = this.getElement();
        DocumentEvent.ElementChange ec = e.getChange(elem);
        if (e.getType() == DocumentEvent.EventType.CHANGE) {
            this.getContainer().repaint();
        } else if (ec != null) {
            Element[] removedElems = ec.getChildrenRemoved();
            Element[] addedElems = ec.getChildrenAdded();
            View[] added = new View[addedElems.length];
            for (int i = 0; i < addedElems.length; ++i) {
                added[i] = new WrappedLine(addedElems[i]);
            }
            this.replace(ec.getIndex(), removedElems.length, added);
            if (a != null) {
                this.preferenceChanged(null, true, true);
                this.getContainer().repaint();
            }
        }
        this.updateMetrics();
    }

    final void updateMetrics() {
        Container host = this.getContainer();
        Font f = host.getFont();
        this.metrics = host.getFontMetrics(f);
        this.tabSize = this.getTabSize() * this.metrics.charWidth('m');
    }

    @Override
    public int viewToModel(float x, float y, Shape a, Position.Bias[] bias) {
        View v;
        Rectangle alloc;
        int offs = -1;
        if (!this.isAllocationValid()) {
            alloc = a.getBounds();
            this.setSize(alloc.width, alloc.height);
        }
        if ((v = this.getViewAtPoint((int)x, (int)y, alloc = this.getInsideAllocation(a))) != null) {
            offs = v.viewToModel(x, y, alloc, bias);
            if (this.host.isCodeFoldingEnabled() && v == this.getView(this.getViewCount() - 1) && offs == v.getEndOffset() - 1) {
                offs = this.host.getLastVisibleOffset();
            }
        }
        return offs;
    }

    @Override
    public int yForLine(Rectangle alloc, int line) throws BadLocationException {
        return this.yForLineContaining(alloc, this.getElement().getElement(line).getStartOffset());
    }

    @Override
    public int yForLineContaining(Rectangle alloc, int offs) throws BadLocationException {
        Rectangle r;
        if (this.isAllocationValid() && (r = (Rectangle)this.modelToView(offs, alloc, Position.Bias.Forward)) != null) {
            if (this.host.isCodeFoldingEnabled()) {
                int line = this.host.getLineOfOffset(offs);
                FoldManager fm = this.host.getFoldManager();
                if (fm.isLineHidden(line)) {
                    return -1;
                }
            }
            return r.y;
        }
        return -1;
    }

    class WrappedLine
    extends View {
        private int nlines;
        private boolean widthChangePending;

        WrappedLine(Element elem) {
            super(elem);
        }

        final int calculateLineCount() {
            int nlines = 0;
            int startOffset = this.getStartOffset();
            int p1 = this.getEndOffset();
            RSyntaxTextArea textArea = (RSyntaxTextArea)this.getContainer();
            RSyntaxDocument doc = (RSyntaxDocument)this.getDocument();
            Element map = doc.getDefaultRootElement();
            int line = map.getElementIndex(startOffset);
            Token tokenList = doc.getTokenListForLine(line);
            float x0 = 0.0f;
            int p0 = startOffset;
            while (p0 < p1) {
                ++nlines;
                TokenUtils.TokenSubList subList = TokenUtils.getSubTokenList(tokenList, p0, WrappedSyntaxView.this, textArea, x0, WrappedSyntaxView.this.lineCountTempToken);
                x0 = subList != null ? subList.x : x0;
                tokenList = subList != null ? subList.tokenList : null;
                int p = WrappedSyntaxView.this.calculateBreakPosition(p0, tokenList, x0);
                p0 = p == p0 ? ++p : p;
            }
            return nlines;
        }

        @Override
        public float getPreferredSpan(int axis) {
            switch (axis) {
                case 0: {
                    float width = WrappedSyntaxView.this.getWidth();
                    if (width == 2.14748365E9f) {
                        return 100.0f;
                    }
                    return width;
                }
                case 1: {
                    if (this.nlines == 0 || this.widthChangePending) {
                        this.nlines = this.calculateLineCount();
                        this.widthChangePending = false;
                    }
                    return this.nlines * ((RSyntaxTextArea)this.getContainer()).getLineHeight();
                }
            }
            throw new IllegalArgumentException("Invalid axis: " + axis);
        }

        @Override
        public void paint(Graphics g, Shape a) {
        }

        @Override
        public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
            Rectangle alloc = a.getBounds();
            RSyntaxTextArea textArea = (RSyntaxTextArea)this.getContainer();
            alloc.height = textArea.getLineHeight();
            alloc.width = 1;
            int p0 = this.getStartOffset();
            int p1 = this.getEndOffset();
            int testP = b == Position.Bias.Forward ? pos : Math.max(p0, pos - 1);
            RSyntaxDocument doc = (RSyntaxDocument)this.getDocument();
            Element map = doc.getDefaultRootElement();
            int line = map.getElementIndex(p0);
            Token tokenList = doc.getTokenListForLine(line);
            float x0 = alloc.x;
            while (p0 < p1) {
                TokenUtils.TokenSubList subList = TokenUtils.getSubTokenList(tokenList, p0, WrappedSyntaxView.this, textArea, x0, WrappedSyntaxView.this.lineCountTempToken);
                x0 = subList != null ? subList.x : x0;
                tokenList = subList != null ? subList.tokenList : null;
                int p = WrappedSyntaxView.this.calculateBreakPosition(p0, tokenList, x0);
                if (pos >= p0 && testP < p) {
                    alloc = RSyntaxUtilities.getLineWidthUpTo(textArea, WrappedSyntaxView.this.s, p0, pos, WrappedSyntaxView.this, alloc, alloc.x);
                    return alloc;
                }
                if (p == p1 - 1 && pos == p1 - 1) {
                    if (pos > p0) {
                        alloc = RSyntaxUtilities.getLineWidthUpTo(textArea, WrappedSyntaxView.this.s, p0, pos, WrappedSyntaxView.this, alloc, alloc.x);
                    }
                    return alloc;
                }
                p0 = p == p0 ? p1 : p;
                alloc.y += alloc.height;
            }
            throw new BadLocationException(null, pos);
        }

        @Override
        public int viewToModel(float fx, float fy, Shape a, Position.Bias[] bias) {
            bias[0] = Position.Bias.Forward;
            Rectangle alloc = (Rectangle)a;
            RSyntaxDocument doc = (RSyntaxDocument)this.getDocument();
            int x = (int)fx;
            int y = (int)fy;
            if (y < alloc.y) {
                return this.getStartOffset();
            }
            if (y > alloc.y + alloc.height) {
                return this.getEndOffset() - 1;
            }
            RSyntaxTextArea textArea = (RSyntaxTextArea)this.getContainer();
            alloc.height = textArea.getLineHeight();
            int p1 = this.getEndOffset();
            Element map = doc.getDefaultRootElement();
            int p0 = this.getStartOffset();
            int line = map.getElementIndex(p0);
            Token tlist = doc.getTokenListForLine(line);
            while (p0 < p1) {
                TokenUtils.TokenSubList subList = TokenUtils.getSubTokenList(tlist, p0, WrappedSyntaxView.this, textArea, alloc.x, WrappedSyntaxView.this.lineCountTempToken);
                tlist = subList != null ? subList.tokenList : null;
                int p = WrappedSyntaxView.this.calculateBreakPosition(p0, tlist, alloc.x);
                if (y >= alloc.y && y < alloc.y + alloc.height) {
                    if (x < alloc.x) {
                        return p0;
                    }
                    if (x > alloc.x + alloc.width) {
                        return p - 1;
                    }
                    if (tlist != null) {
                        int n = tlist.getListOffset(textArea, WrappedSyntaxView.this, alloc.x, x);
                        return Math.max(Math.min(n, p - 1), p0);
                    }
                }
                p0 = p == p0 ? p1 : p;
                alloc.y += alloc.height;
            }
            return this.getEndOffset() - 1;
        }

        private void handleDocumentEvent(DocumentEvent e, Shape a, ViewFactory f) {
            int n = this.calculateLineCount();
            if (this.nlines != n) {
                this.nlines = n;
                WrappedSyntaxView.this.preferenceChanged(this, false, true);
                RSyntaxTextArea textArea = (RSyntaxTextArea)this.getContainer();
                textArea.repaint();
                Gutter gutter = RSyntaxUtilities.getGutter(textArea);
                if (gutter != null) {
                    gutter.revalidate();
                    gutter.repaint();
                }
            } else if (a != null) {
                Container c = this.getContainer();
                Rectangle alloc = (Rectangle)a;
                c.repaint(alloc.x, alloc.y, alloc.width, alloc.height);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e, Shape a, ViewFactory f) {
            this.handleDocumentEvent(e, a, f);
        }

        @Override
        public void removeUpdate(DocumentEvent e, Shape a, ViewFactory f) {
            this.handleDocumentEvent(e, a, f);
        }
    }
}

