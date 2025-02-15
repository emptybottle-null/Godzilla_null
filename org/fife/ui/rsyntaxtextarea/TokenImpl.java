/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import javax.swing.text.Segment;
import javax.swing.text.TabExpander;
import javax.swing.text.Utilities;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;

public class TokenImpl
implements Token {
    public char[] text = null;
    public int textOffset = -1;
    public int textCount = -1;
    private int offset;
    private int type;
    private boolean hyperlink;
    private Token nextToken;
    private int languageIndex;

    public TokenImpl() {
        this.setType(0);
        this.setOffset(-1);
        this.hyperlink = false;
        this.nextToken = null;
    }

    public TokenImpl(Segment line, int beg, int end, int startOffset, int type, int languageIndex) {
        this(line.array, beg, end, startOffset, type, languageIndex);
    }

    public TokenImpl(char[] line, int beg, int end, int startOffset, int type, int languageIndex) {
        this();
        this.set(line, beg, end, startOffset, type);
        this.setLanguageIndex(languageIndex);
    }

    public TokenImpl(Token t2) {
        this();
        this.copyFrom(t2);
    }

    @Override
    public StringBuilder appendHTMLRepresentation(StringBuilder sb, RSyntaxTextArea textArea, boolean fontFamily) {
        return this.appendHTMLRepresentation(sb, textArea, fontFamily, false);
    }

    @Override
    public StringBuilder appendHTMLRepresentation(StringBuilder sb, RSyntaxTextArea textArea, boolean fontFamily, boolean tabsToSpaces) {
        boolean needsFontTag;
        SyntaxScheme colorScheme = textArea.getSyntaxScheme();
        Style scheme = colorScheme.getStyle(this.getType());
        Font font = textArea.getFontForTokenType(this.getType());
        if (font.isBold()) {
            sb.append("<b>");
        }
        if (font.isItalic()) {
            sb.append("<em>");
        }
        if (scheme.underline || this.isHyperlink()) {
            sb.append("<u>");
        }
        boolean bl = needsFontTag = fontFamily || !this.isWhitespace();
        if (needsFontTag) {
            sb.append("<font");
            if (fontFamily) {
                sb.append(" face=\"").append(font.getFamily()).append('\"');
            }
            if (!this.isWhitespace()) {
                sb.append(" color=\"").append(TokenImpl.getHTMLFormatForColor(scheme.foreground)).append('\"');
            }
            sb.append('>');
        }
        this.appendHtmlLexeme(textArea, sb, tabsToSpaces);
        if (needsFontTag) {
            sb.append("</font>");
        }
        if (scheme.underline || this.isHyperlink()) {
            sb.append("</u>");
        }
        if (font.isItalic()) {
            sb.append("</em>");
        }
        if (font.isBold()) {
            sb.append("</b>");
        }
        return sb;
    }

    private StringBuilder appendHtmlLexeme(RSyntaxTextArea textArea, StringBuilder sb, boolean tabsToSpaces) {
        int i;
        boolean lastWasSpace = false;
        int lastI = i = this.textOffset;
        String tabStr = null;
        while (i < this.textOffset + this.textCount) {
            char ch = this.text[i];
            switch (ch) {
                case ' ': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append(lastWasSpace ? "&nbsp;" : " ");
                    lastWasSpace = true;
                    break;
                }
                case '\t': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    if (tabsToSpaces && tabStr == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < textArea.getTabSize(); ++j) {
                            stringBuilder.append("&nbsp;");
                        }
                        tabStr = stringBuilder.toString();
                    }
                    sb.append(tabsToSpaces ? tabStr : "&#09;");
                    lastWasSpace = false;
                    break;
                }
                case '&': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append("&amp;");
                    lastWasSpace = false;
                    break;
                }
                case '<': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append("&lt;");
                    lastWasSpace = false;
                    break;
                }
                case '>': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append("&gt;");
                    lastWasSpace = false;
                    break;
                }
                case '\'': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append("&#39;");
                    lastWasSpace = false;
                    break;
                }
                case '\"': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append("&#34;");
                    lastWasSpace = false;
                    break;
                }
                case '/': {
                    sb.append(this.text, lastI, i - lastI);
                    lastI = i + 1;
                    sb.append("&#47;");
                    lastWasSpace = false;
                    break;
                }
                default: {
                    lastWasSpace = false;
                }
            }
            ++i;
        }
        if (lastI < this.textOffset + this.textCount) {
            sb.append(this.text, lastI, this.textOffset + this.textCount - lastI);
        }
        return sb;
    }

    @Override
    public char charAt(int index) {
        return this.text[this.textOffset + index];
    }

    @Override
    public boolean containsPosition(int pos) {
        return pos >= this.getOffset() && pos < this.getOffset() + this.textCount;
    }

    public void copyFrom(Token t2) {
        this.text = t2.getTextArray();
        this.textOffset = t2.getTextOffset();
        this.textCount = t2.length();
        this.setOffset(t2.getOffset());
        this.setType(t2.getType());
        this.hyperlink = t2.isHyperlink();
        this.languageIndex = t2.getLanguageIndex();
        this.nextToken = t2.getNextToken();
    }

    @Override
    public int documentToToken(int pos) {
        return pos + (this.textOffset - this.getOffset());
    }

    @Override
    public boolean endsWith(char[] ch) {
        if (ch == null || ch.length > this.textCount) {
            return false;
        }
        int start = this.textOffset + this.textCount - ch.length;
        for (int i = 0; i < ch.length; ++i) {
            if (this.text[start + i] == ch[i]) continue;
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Token)) {
            return false;
        }
        Token t2 = (Token)obj;
        return this.offset == t2.getOffset() && this.type == t2.getType() && this.languageIndex == t2.getLanguageIndex() && this.hyperlink == t2.isHyperlink() && (this.getLexeme() == null && t2.getLexeme() == null || this.getLexeme() != null && this.getLexeme().equals(t2.getLexeme()));
    }

    @Override
    public int getEndOffset() {
        return this.offset + this.textCount;
    }

    private static String getHTMLFormatForColor(Color color) {
        String hexBlue;
        String hexGreen;
        if (color == null) {
            return "black";
        }
        String hexRed = Integer.toHexString(color.getRed());
        if (hexRed.length() == 1) {
            hexRed = "0" + hexRed;
        }
        if ((hexGreen = Integer.toHexString(color.getGreen())).length() == 1) {
            hexGreen = "0" + hexGreen;
        }
        if ((hexBlue = Integer.toHexString(color.getBlue())).length() == 1) {
            hexBlue = "0" + hexBlue;
        }
        return "#" + hexRed + hexGreen + hexBlue;
    }

    @Override
    public String getHTMLRepresentation(RSyntaxTextArea textArea) {
        StringBuilder buf = new StringBuilder();
        this.appendHTMLRepresentation(buf, textArea, true);
        return buf.toString();
    }

    @Override
    public int getLanguageIndex() {
        return this.languageIndex;
    }

    @Override
    public Token getLastNonCommentNonWhitespaceToken() {
        TokenImpl last = null;
        block3: for (Token t = this; t != null && t.isPaintable(); t = t.getNextToken()) {
            switch (t.getType()) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 21: {
                    continue block3;
                }
                default: {
                    last = t;
                }
            }
        }
        return last;
    }

    @Override
    public Token getLastPaintableToken() {
        Token t = this;
        while (t.isPaintable()) {
            Token next = t.getNextToken();
            if (next == null || !next.isPaintable()) {
                return t;
            }
            t = next;
        }
        return null;
    }

    @Override
    public String getLexeme() {
        if (this.text == null) {
            return null;
        }
        return this.isPaintable() ? new String(this.text, this.textOffset, this.textCount) : null;
    }

    @Override
    public int getListOffset(RSyntaxTextArea textArea, TabExpander e, float x0, float x) {
        if (x0 >= x) {
            return this.getOffset();
        }
        float currX = x0;
        float nextX = x0;
        float stableX = x0;
        int last = this.getOffset();
        FontMetrics fm = null;
        for (TokenImpl token = this; token != null && token.isPaintable(); token = (TokenImpl)token.getNextToken()) {
            fm = textArea.getFontMetricsForTokenType(token.getType());
            char[] text = token.text;
            int start = token.textOffset;
            int end = start + token.textCount;
            for (int i = start; i < end; ++i) {
                currX = nextX;
                if (text[i] == '\t') {
                    stableX = nextX = e.nextTabStop(nextX, 0);
                    start = i + 1;
                } else {
                    nextX = stableX + (float)fm.charsWidth(text, start, i - start + 1);
                }
                if (!(x >= currX) || !(x < nextX)) continue;
                if (x - currX < nextX - x) {
                    return last + i - token.textOffset;
                }
                return last + i + 1 - token.textOffset;
            }
            stableX = nextX;
            last += token.textCount;
        }
        return last;
    }

    @Override
    public Token getNextToken() {
        return this.nextToken;
    }

    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public int getOffsetBeforeX(RSyntaxTextArea textArea, TabExpander e, float startX, float endBeforeX) {
        int i;
        FontMetrics fm = textArea.getFontMetricsForTokenType(this.getType());
        int stop = i + this.textCount;
        float x = startX;
        for (i = this.textOffset; i < stop; ++i) {
            x = this.text[i] == '\t' ? e.nextTabStop(x, 0) : (x += (float)fm.charWidth(this.text[i]));
            if (!(x > endBeforeX)) continue;
            int intoToken = Math.max(i - this.textOffset, 1);
            return this.getOffset() + intoToken;
        }
        return this.getOffset() + this.textCount - 1;
    }

    @Override
    public char[] getTextArray() {
        return this.text;
    }

    @Override
    public int getTextOffset() {
        return this.textOffset;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public float getWidth(RSyntaxTextArea textArea, TabExpander e, float x0) {
        return this.getWidthUpTo(this.textCount, textArea, e, x0);
    }

    @Override
    public float getWidthUpTo(int numChars, RSyntaxTextArea textArea, TabExpander e, float x0) {
        float width = x0;
        FontMetrics fm = textArea.getFontMetricsForTokenType(this.getType());
        if (fm != null) {
            int w;
            int currentStart = this.textOffset;
            int endBefore = this.textOffset + numChars;
            for (int i = currentStart; i < endBefore; ++i) {
                if (this.text[i] != '\t') continue;
                w = i - currentStart;
                if (w > 0) {
                    width += (float)fm.charsWidth(this.text, currentStart, w);
                }
                currentStart = i + 1;
                width = e.nextTabStop(width, 0);
            }
            w = endBefore - currentStart;
            width += (float)fm.charsWidth(this.text, currentStart, w);
        }
        return width - x0;
    }

    public int hashCode() {
        return this.offset + (this.getLexeme() == null ? 0 : this.getLexeme().hashCode());
    }

    @Override
    public boolean is(char[] lexeme) {
        if (this.textCount == lexeme.length) {
            for (int i = 0; i < this.textCount; ++i) {
                if (this.text[this.textOffset + i] == lexeme[i]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean is(int type, char[] lexeme) {
        if (this.getType() == type && this.textCount == lexeme.length) {
            for (int i = 0; i < this.textCount; ++i) {
                if (this.text[this.textOffset + i] == lexeme[i]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean is(int type, String lexeme) {
        return this.getType() == type && this.textCount == lexeme.length() && lexeme.equals(this.getLexeme());
    }

    @Override
    public boolean isComment() {
        return this.getType() >= 1 && this.getType() <= 5;
    }

    @Override
    public boolean isCommentOrWhitespace() {
        return this.isComment() || this.isWhitespace();
    }

    @Override
    public boolean isHyperlink() {
        return this.hyperlink;
    }

    @Override
    public boolean isIdentifier() {
        return this.getType() == 20;
    }

    @Override
    public boolean isLeftCurly() {
        return this.getType() == 22 && this.isSingleChar('{');
    }

    @Override
    public boolean isRightCurly() {
        return this.getType() == 22 && this.isSingleChar('}');
    }

    @Override
    public boolean isPaintable() {
        return this.getType() > 0;
    }

    @Override
    public boolean isSingleChar(char ch) {
        return this.textCount == 1 && this.text[this.textOffset] == ch;
    }

    @Override
    public boolean isSingleChar(int type, char ch) {
        return this.getType() == type && this.isSingleChar(ch);
    }

    @Override
    public boolean isWhitespace() {
        return this.getType() == 21;
    }

    @Override
    public int length() {
        return this.textCount;
    }

    @Override
    public Rectangle listOffsetToView(RSyntaxTextArea textArea, TabExpander e, int pos, int x0, Rectangle rect) {
        int stableX = x0;
        FontMetrics fm = null;
        Segment s = new Segment();
        for (TokenImpl token = this; token != null && token.isPaintable(); token = (TokenImpl)token.getNextToken()) {
            fm = textArea.getFontMetricsForTokenType(token.getType());
            if (fm == null) {
                return rect;
            }
            char[] text = token.text;
            int start = token.textOffset;
            int end = start + token.textCount;
            if (token.containsPosition(pos)) {
                s.array = token.text;
                s.offset = token.textOffset;
                s.count = pos - token.getOffset();
                int w = Utilities.getTabbedTextWidth(s, fm, stableX, e, token.getOffset());
                rect.x = stableX + w;
                end = token.documentToToken(pos);
                rect.width = text[end] == '\t' ? fm.charWidth(' ') : fm.charWidth(text[end]);
                return rect;
            }
            s.array = token.text;
            s.offset = token.textOffset;
            s.count = token.textCount;
            stableX += Utilities.getTabbedTextWidth(s, fm, stableX, e, token.getOffset());
        }
        rect.x = stableX;
        rect.width = 1;
        return rect;
    }

    public void makeStartAt(int pos) {
        if (pos < this.getOffset() || pos >= this.getOffset() + this.textCount) {
            throw new IllegalArgumentException("pos " + pos + " is not in range " + this.getOffset() + "-" + (this.getOffset() + this.textCount - 1));
        }
        int shift = pos - this.getOffset();
        this.setOffset(pos);
        this.textOffset += shift;
        this.textCount -= shift;
    }

    public void moveOffset(int amt) {
        if (amt < 0 || amt > this.textCount) {
            throw new IllegalArgumentException("amt " + amt + " is not in range 0-" + this.textCount);
        }
        this.setOffset(this.getOffset() + amt);
        this.textOffset += amt;
        this.textCount -= amt;
    }

    public void set(char[] line, int beg, int end, int offset, int type) {
        this.text = line;
        this.textOffset = beg;
        this.textCount = end - beg + 1;
        this.setType(type);
        this.setOffset(offset);
        this.nextToken = null;
    }

    @Override
    public void setHyperlink(boolean hyperlink) {
        this.hyperlink = hyperlink;
    }

    @Override
    public void setLanguageIndex(int languageIndex) {
        this.languageIndex = languageIndex;
    }

    public void setNextToken(Token nextToken) {
        this.nextToken = nextToken;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean startsWith(char[] chars) {
        if (chars.length <= this.textCount) {
            for (int i = 0; i < chars.length; ++i) {
                if (this.text[this.textOffset + i] == chars[i]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int tokenToDocument(int pos) {
        return pos + (this.getOffset() - this.textOffset);
    }

    public String toString() {
        return "[Token: " + (this.getType() == 0 ? "<null token>" : "text: '" + (this.text == null ? "<null>" : this.getLexeme() + "'; offset: " + this.getOffset() + "; type: " + this.getType() + "; isPaintable: " + this.isPaintable() + "; nextToken==null: " + (this.nextToken == null))) + "]";
    }
}

