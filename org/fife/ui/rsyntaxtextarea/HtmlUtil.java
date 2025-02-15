/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenUtils;

public final class HtmlUtil {
    private HtmlUtil() {
    }

    public static String escapeForHtml(String s, String newlineReplacement, boolean inPreBlock) {
        if (newlineReplacement == null) {
            newlineReplacement = "";
        }
        String tabString = inPreBlock ? "    " : "&nbsp;&nbsp;&nbsp;&nbsp;";
        StringBuilder sb = new StringBuilder();
        block8: for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            switch (ch) {
                case ' ': {
                    if (inPreBlock) {
                        sb.append(' ');
                        continue block8;
                    }
                    sb.append("&nbsp;");
                    continue block8;
                }
                case '\n': {
                    sb.append(newlineReplacement);
                    continue block8;
                }
                case '&': {
                    sb.append("&amp;");
                    continue block8;
                }
                case '\t': {
                    sb.append(tabString);
                    continue block8;
                }
                case '<': {
                    sb.append("&lt;");
                    continue block8;
                }
                case '>': {
                    sb.append("&gt;");
                    continue block8;
                }
                default: {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    public static String getHexString(Color c) {
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("#");
        int r = c.getRed();
        if (r < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(r));
        int g = c.getGreen();
        if (g < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(g));
        int b = c.getBlue();
        if (b < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(b));
        return sb.toString();
    }

    public static String getTextAsHtml(RSyntaxTextArea textArea, int start, int end) {
        Token token;
        StringBuilder sb = new StringBuilder("<pre style='").append("font-family: \"").append(textArea.getFont().getFamily()).append("\", courier;");
        if (textArea.getBackground() != null) {
            sb.append(" background: ").append(HtmlUtil.getHexString(textArea.getBackground())).append("'>");
        }
        for (Token t = token = textArea.getTokenListFor(start, end); t != null; t = t.getNextToken()) {
            if (!t.isPaintable()) continue;
            if (t.isSingleChar('\n')) {
                sb.append("<br>");
                continue;
            }
            sb.append(TokenUtils.tokenToHtml(textArea, t));
        }
        sb.append("</pre>");
        return sb.toString();
    }
}

