/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.text.TabExpander;
import org.fife.ui.rsyntaxtextarea.HtmlUtil;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

public final class TokenUtils {
    private TokenUtils() {
    }

    public static TokenSubList getSubTokenList(Token tokenList, int pos, TabExpander e, RSyntaxTextArea textArea, float x0) {
        return TokenUtils.getSubTokenList(tokenList, pos, e, textArea, x0, null);
    }

    public static TokenSubList getSubTokenList(Token tokenList, int pos, TabExpander e, RSyntaxTextArea textArea, float x0, TokenImpl tempToken) {
        Token t;
        if (tempToken == null) {
            tempToken = new TokenImpl();
        }
        for (t = tokenList; t != null && t.isPaintable() && !t.containsPosition(pos); t = t.getNextToken()) {
            x0 += t.getWidth(textArea, e, x0);
        }
        if (t != null && t.isPaintable()) {
            if (t.getOffset() != pos) {
                int difference = pos - t.getOffset();
                x0 += t.getWidthUpTo(t.length() - difference + 1, textArea, e, x0);
                tempToken.copyFrom(t);
                tempToken.makeStartAt(pos);
                return new TokenSubList(tempToken, x0);
            }
            return new TokenSubList(t, x0);
        }
        return new TokenSubList(tokenList, x0);
    }

    public static int getWhiteSpaceTokenLength(Token t, int tabSize, int curOffs) {
        int length = 0;
        for (int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            if (ch == '\t') {
                int newCurOffs = (curOffs + tabSize) / tabSize * tabSize;
                length += newCurOffs - curOffs;
                curOffs = newCurOffs;
                continue;
            }
            ++length;
            ++curOffs;
        }
        return length;
    }

    public static boolean isBlankOrAllWhiteSpace(Token t) {
        while (t != null && t.isPaintable()) {
            if (!t.isCommentOrWhitespace()) {
                return false;
            }
            t = t.getNextToken();
        }
        return true;
    }

    public static boolean isBlankOrAllWhiteSpaceWithoutComments(Token t) {
        while (t != null && t.isPaintable()) {
            if (!t.isWhitespace()) {
                return false;
            }
            t = t.getNextToken();
        }
        return true;
    }

    public static String tokenToHtml(RSyntaxTextArea textArea, Token token) {
        StringBuilder style = new StringBuilder();
        Font font = textArea.getFontForTokenType(token.getType());
        if (font.isBold()) {
            style.append("font-weight: bold;");
        }
        if (font.isItalic()) {
            style.append("font-style: italic;");
        }
        Color c = textArea.getForegroundForToken(token);
        style.append("color: ").append(HtmlUtil.getHexString(c)).append(";");
        return "<span style=\"" + style + "\">" + HtmlUtil.escapeForHtml(token.getLexeme(), "\n", true) + "</span>";
    }

    public static class TokenSubList {
        public Token tokenList;
        public float x;

        public TokenSubList(Token tokenList, float x) {
            this.tokenList = tokenList;
            this.x = x;
        }
    }
}

