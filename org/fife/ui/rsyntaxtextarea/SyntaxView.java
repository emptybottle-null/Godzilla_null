/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
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
import org.fife.ui.rsyntaxtextarea.TokenOrientedView;
import org.fife.ui.rsyntaxtextarea.TokenPainter;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;

public class SyntaxView
extends View
implements TabExpander,
TokenOrientedView,
RSTAView {
    private Font font;
    private FontMetrics metrics;
    private Element longLine;
    private float longLineWidth;
    private int tabSize;
    private int tabBase;
    private RSyntaxTextArea host;
    private int lineHeight = 0;
    private int ascent;
    private int clipStart;
    private int clipEnd;
    private TokenImpl tempToken = new TokenImpl();

    public SyntaxView(Element elem) {
        super(elem);
    }

    void calculateLongestLine() {
        Container c = this.getContainer();
        this.font = c.getFont();
        this.metrics = c.getFontMetrics(this.font);
        this.tabSize = this.getTabSize() * this.metrics.charWidth(' ');
        Element lines = this.getElement();
        int n = lines.getElementCount();
        for (int i = 0; i < n; ++i) {
            Element line = lines.getElement(i);
            float w = this.getLineWidth(i);
            if (!(w > this.longLineWidth)) continue;
            this.longLineWidth = w;
            this.longLine = line;
        }
    }

    @Override
    public void changedUpdate(DocumentEvent changes, Shape a, ViewFactory f) {
        this.updateDamage(changes, a, f);
    }

    protected void damageLineRange(int line0, int line1, Shape a, Component host) {
        if (a != null) {
            Rectangle area0 = this.lineToRect(a, line0);
            Rectangle area1 = this.lineToRect(a, line1);
            if (area0 != null && area1 != null) {
                Rectangle dmg = area0.union(area1);
                host.repaint(dmg.x, dmg.y, dmg.width, dmg.height);
            } else {
                host.repaint();
            }
        }
    }

    private float drawLine(TokenPainter painter, Token token, Graphics2D g, float x, float y, int line) {
        float nextX = x;
        boolean paintBG = this.host.getPaintTokenBackgrounds(line, y);
        while (token != null && token.isPaintable() && nextX < (float)this.clipEnd) {
            nextX = painter.paint(token, g, nextX, y, this.host, this, this.clipStart, paintBG);
            token = token.getNextToken();
        }
        if (this.host.getEOLMarkersVisible()) {
            g.setColor(this.host.getForegroundForTokenType(21));
            g.setFont(this.host.getFontForTokenType(21));
            g.drawString("\u00b6", nextX, y);
        }
        return nextX;
    }

    private float drawLineWithSelection(TokenPainter painter, Token token, Graphics2D g, float x, float y, int selStart, int selEnd) {
        float nextX = x;
        boolean useSTC = this.host.getUseSelectedTextColor();
        while (token != null && token.isPaintable() && nextX < (float)this.clipEnd) {
            if (token.containsPosition(selStart)) {
                int tokenLen;
                int selCount;
                if (selStart > token.getOffset()) {
                    this.tempToken.copyFrom(token);
                    this.tempToken.textCount = selStart - this.tempToken.getOffset();
                    nextX = painter.paint(this.tempToken, g, nextX, y, this.host, this, this.clipStart);
                    this.tempToken.textCount = token.length();
                    this.tempToken.makeStartAt(selStart);
                    token = new TokenImpl(this.tempToken);
                }
                if ((selCount = Math.min(tokenLen = token.length(), selEnd - token.getOffset())) == tokenLen) {
                    nextX = painter.paintSelected(token, g, nextX, y, this.host, this, this.clipStart, useSTC);
                } else {
                    this.tempToken.copyFrom(token);
                    this.tempToken.textCount = selCount;
                    nextX = painter.paintSelected(this.tempToken, g, nextX, y, this.host, this, this.clipStart, useSTC);
                    this.tempToken.textCount = token.length();
                    this.tempToken.makeStartAt(token.getOffset() + selCount);
                    token = this.tempToken;
                    nextX = painter.paint(token, g, nextX, y, this.host, this, this.clipStart);
                }
            } else if (token.containsPosition(selEnd)) {
                this.tempToken.copyFrom(token);
                this.tempToken.textCount = selEnd - this.tempToken.getOffset();
                nextX = painter.paintSelected(this.tempToken, g, nextX, y, this.host, this, this.clipStart, useSTC);
                this.tempToken.textCount = token.length();
                this.tempToken.makeStartAt(selEnd);
                token = this.tempToken;
                nextX = painter.paint(token, g, nextX, y, this.host, this, this.clipStart);
            } else {
                nextX = token.getOffset() >= selStart && token.getEndOffset() <= selEnd ? painter.paintSelected(token, g, nextX, y, this.host, this, this.clipStart, useSTC) : painter.paint(token, g, nextX, y, this.host, this, this.clipStart);
            }
            token = token.getNextToken();
        }
        if (this.host.getEOLMarkersVisible()) {
            g.setColor(this.host.getForegroundForTokenType(21));
            g.setFont(this.host.getFontForTokenType(21));
            g.drawString("\u00b6", nextX, y);
        }
        return nextX;
    }

    private float getLineWidth(int lineNumber) {
        Token tokenList = ((RSyntaxDocument)this.getDocument()).getTokenListForLine(lineNumber);
        return RSyntaxUtilities.getTokenListWidth(tokenList, (RSyntaxTextArea)this.getContainer(), this);
    }

    @Override
    public int getNextVisualPositionFrom(int pos, Position.Bias b, Shape a, int direction, Position.Bias[] biasRet) throws BadLocationException {
        return RSyntaxUtilities.getNextVisualPositionFrom(pos, b, a, direction, biasRet, this);
    }

    @Override
    public float getPreferredSpan(int axis) {
        this.updateMetrics();
        switch (axis) {
            case 0: {
                float span = this.longLineWidth + (float)this.getRhsCorrection();
                if (this.host.getEOLMarkersVisible()) {
                    span += (float)this.metrics.charWidth('\u00b6');
                }
                return span;
            }
            case 1: {
                this.lineHeight = this.host != null ? this.host.getLineHeight() : this.lineHeight;
                int visibleLineCount = this.getElement().getElementCount();
                if (this.host.isCodeFoldingEnabled()) {
                    visibleLineCount -= this.host.getFoldManager().getHiddenLineCount();
                }
                return (float)visibleLineCount * (float)this.lineHeight;
            }
        }
        throw new IllegalArgumentException("Invalid axis: " + axis);
    }

    private int getRhsCorrection() {
        int rhsCorrection = 10;
        if (this.host != null) {
            rhsCorrection = this.host.getRightHandSideCorrection();
        }
        return rhsCorrection;
    }

    private int getTabSize() {
        Integer i = (Integer)this.getDocument().getProperty("tabSize");
        int size = i != null ? i : 5;
        return size;
    }

    @Override
    public Token getTokenListForPhysicalLineAbove(int offset) {
        RSyntaxDocument document = (RSyntaxDocument)this.getDocument();
        Element map = document.getDefaultRootElement();
        int line = map.getElementIndex(offset);
        FoldManager fm = this.host.getFoldManager();
        if (fm == null ? --line >= 0 : (line = fm.getVisibleLineAbove(line)) >= 0) {
            return document.getTokenListForLine(line);
        }
        return null;
    }

    @Override
    public Token getTokenListForPhysicalLineBelow(int offset) {
        RSyntaxDocument document = (RSyntaxDocument)this.getDocument();
        Element map = document.getDefaultRootElement();
        int lineCount = map.getElementCount();
        int line = map.getElementIndex(offset);
        if (!this.host.isCodeFoldingEnabled()) {
            if (line < lineCount - 1) {
                return document.getTokenListForLine(line + 1);
            }
        } else {
            FoldManager fm = this.host.getFoldManager();
            line = fm.getVisibleLineBelow(line);
            if (line >= 0 && line < lineCount) {
                return document.getTokenListForLine(line);
            }
        }
        return null;
    }

    @Override
    public void insertUpdate(DocumentEvent changes, Shape a, ViewFactory f) {
        this.updateDamage(changes, a, f);
    }

    protected Rectangle lineToRect(Shape a, int line) {
        Rectangle r = null;
        this.updateMetrics();
        if (this.metrics != null) {
            Rectangle alloc = a.getBounds();
            int n = this.lineHeight = this.host != null ? this.host.getLineHeight() : this.lineHeight;
            if (this.host != null && this.host.isCodeFoldingEnabled()) {
                FoldManager fm = this.host.getFoldManager();
                int hiddenCount = fm.getHiddenLineCountAbove(line);
                line -= hiddenCount;
            }
            r = new Rectangle(alloc.x, alloc.y + line * this.lineHeight, alloc.width, this.lineHeight);
        }
        return r;
    }

    @Override
    public Shape modelToView(int pos, Shape a, Position.Bias b) throws BadLocationException {
        Element map = this.getElement();
        RSyntaxDocument doc = (RSyntaxDocument)this.getDocument();
        int lineIndex = map.getElementIndex(pos);
        Token tokenList = doc.getTokenListForLine(lineIndex);
        Rectangle lineArea = this.lineToRect(a, lineIndex);
        this.tabBase = lineArea.x;
        lineArea = tokenList.listOffsetToView((RSyntaxTextArea)this.getContainer(), this, pos, this.tabBase, lineArea);
        return lineArea;
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
        Rectangle r0 = s0 instanceof Rectangle ? (Rectangle)s0 : s0.getBounds();
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
        RSyntaxDocument document = (RSyntaxDocument)this.getDocument();
        Rectangle alloc = a.getBounds();
        this.tabBase = alloc.x;
        this.host = (RSyntaxTextArea)this.getContainer();
        Rectangle clip = g.getClipBounds();
        this.clipStart = clip.x;
        this.clipEnd = this.clipStart + clip.width;
        this.lineHeight = this.host.getLineHeight();
        this.ascent = this.host.getMaxAscent();
        int heightAbove = clip.y - alloc.y;
        int linesAbove = Math.max(0, heightAbove / this.lineHeight);
        FoldManager fm = this.host.getFoldManager();
        linesAbove += fm.getHiddenLineCountAbove(linesAbove, true);
        Rectangle lineArea = this.lineToRect(a, linesAbove);
        int y = lineArea.y + this.ascent;
        int x = lineArea.x;
        Element map = this.getElement();
        int lineCount = map.getElementCount();
        int selStart = this.host.getSelectionStart();
        int selEnd = this.host.getSelectionEnd();
        RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.host.getHighlighter();
        Graphics2D g2d = (Graphics2D)g;
        TokenPainter painter = this.host.getTokenPainter();
        for (int line = linesAbove; y < clip.y + clip.height + this.ascent && line < lineCount; y += this.lineHeight, ++line) {
            int hiddenLineCount;
            Fold fold = fm.getFoldForLine(line);
            Element lineElement = map.getElement(line);
            int startOffset = lineElement.getStartOffset();
            int endOffset = lineElement.getEndOffset() - 1;
            h.paintLayeredHighlights(g2d, startOffset, endOffset, a, this.host, this);
            Token token = document.getTokenListForLine(line);
            if (selStart == selEnd || startOffset >= selEnd || endOffset < selStart) {
                this.drawLine(painter, token, g2d, x, y, line);
            } else {
                this.drawLineWithSelection(painter, token, g2d, x, y, selStart, selEnd);
            }
            h.paintParserHighlights(g2d, startOffset, endOffset, a, this.host, this);
            if (fold == null || !fold.isCollapsed()) continue;
            Color c = RSyntaxUtilities.getFoldedLineBottomColor(this.host);
            if (c != null) {
                g.setColor(c);
                g.drawLine(x, y + this.lineHeight - this.ascent - 1, this.host.getWidth(), y + this.lineHeight - this.ascent - 1);
            }
            while ((hiddenLineCount = fold.getLineCount()) != 0 && (fold = fm.getFoldForLine(line += hiddenLineCount)) != null && fold.isCollapsed()) {
            }
        }
    }

    private boolean possiblyUpdateLongLine(Element line, int lineNumber) {
        float w = this.getLineWidth(lineNumber);
        if (w > this.longLineWidth) {
            this.longLineWidth = w;
            this.longLine = line;
            return true;
        }
        return false;
    }

    @Override
    public void removeUpdate(DocumentEvent changes, Shape a, ViewFactory f) {
        this.updateDamage(changes, a, f);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        this.updateMetrics();
    }

    protected void updateDamage(DocumentEvent changes, Shape a, ViewFactory f) {
        Element[] removed;
        Container host = this.getContainer();
        this.updateMetrics();
        Element elem = this.getElement();
        DocumentEvent.ElementChange ec = changes.getChange(elem);
        Element[] added = ec != null ? ec.getChildrenAdded() : null;
        Element[] elementArray = removed = ec != null ? ec.getChildrenRemoved() : null;
        if (added != null && added.length > 0 || removed != null && removed.length > 0) {
            if (added != null) {
                int addedAt = ec.getIndex();
                for (int i = 0; i < added.length; ++i) {
                    this.possiblyUpdateLongLine(added[i], addedAt + i);
                }
            }
            if (removed != null) {
                for (Element element : removed) {
                    if (element != this.longLine) continue;
                    this.longLineWidth = -1.0f;
                    this.calculateLongestLine();
                    break;
                }
            }
            this.preferenceChanged(null, true, true);
            host.repaint();
        } else if (changes.getType() == DocumentEvent.EventType.CHANGE) {
            int startLine = changes.getOffset();
            int endLine = changes.getLength();
            this.damageLineRange(startLine, endLine, a, host);
        } else {
            Element map = this.getElement();
            int line = map.getElementIndex(changes.getOffset());
            this.damageLineRange(line, line, a, host);
            if (changes.getType() == DocumentEvent.EventType.INSERT) {
                Element e = map.getElement(line);
                if (e == this.longLine) {
                    this.longLineWidth = this.getLineWidth(line);
                    this.preferenceChanged(null, true, false);
                } else if (this.possiblyUpdateLongLine(e, line)) {
                    this.preferenceChanged(null, true, false);
                }
            } else if (changes.getType() == DocumentEvent.EventType.REMOVE && map.getElement(line) == this.longLine) {
                this.longLineWidth = -1.0f;
                this.calculateLongestLine();
                this.preferenceChanged(null, true, false);
            }
        }
    }

    private void updateMetrics() {
        this.host = (RSyntaxTextArea)this.getContainer();
        Font f = this.host.getFont();
        if (this.font != f) {
            this.calculateLongestLine();
        }
    }

    @Override
    public int viewToModel(float fx, float fy, Shape a, Position.Bias[] bias) {
        bias[0] = Position.Bias.Forward;
        Rectangle alloc = a.getBounds();
        RSyntaxDocument doc = (RSyntaxDocument)this.getDocument();
        int x = (int)fx;
        int y = (int)fy;
        if (y < alloc.y) {
            return this.getStartOffset();
        }
        if (y > alloc.y + alloc.height) {
            return this.host.getLastVisibleOffset();
        }
        Element map = doc.getDefaultRootElement();
        this.lineHeight = this.host.getLineHeight();
        int lineIndex = Math.abs((y - alloc.y) / this.lineHeight);
        FoldManager fm = this.host.getFoldManager();
        if ((lineIndex += fm.getHiddenLineCountAbove(lineIndex, true)) >= map.getElementCount()) {
            return this.host.getLastVisibleOffset();
        }
        Element line = map.getElement(lineIndex);
        if (x < alloc.x) {
            return line.getStartOffset();
        }
        if (x > alloc.x + alloc.width) {
            return line.getEndOffset() - 1;
        }
        int p0 = line.getStartOffset();
        Token tokenList = doc.getTokenListForLine(lineIndex);
        this.tabBase = alloc.x;
        int offs = tokenList.getListOffset((RSyntaxTextArea)this.getContainer(), this, this.tabBase, x);
        return offs != -1 ? offs : p0;
    }

    @Override
    public int yForLine(Rectangle alloc, int line) throws BadLocationException {
        this.updateMetrics();
        if (this.metrics != null) {
            FoldManager fm;
            int n = this.lineHeight = this.host != null ? this.host.getLineHeight() : this.lineHeight;
            if (this.host != null && !(fm = this.host.getFoldManager()).isLineHidden(line)) {
                line -= fm.getHiddenLineCountAbove(line);
                return alloc.y + line * this.lineHeight;
            }
        }
        return -1;
    }

    @Override
    public int yForLineContaining(Rectangle alloc, int offs) throws BadLocationException {
        Element map = this.getElement();
        int line = map.getElementIndex(offs);
        return this.yForLine(alloc, line);
    }
}

