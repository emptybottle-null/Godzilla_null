/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import javax.swing.text.TabExpander;
import org.fife.ui.rsyntaxtextarea.DefaultTokenPainter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;

class VisibleWhitespaceTokenPainter
extends DefaultTokenPainter {
    VisibleWhitespaceTokenPainter() {
    }

    @Override
    protected float paintImpl(Token token, Graphics2D g, float x, float y, RSyntaxTextArea host, TabExpander e, float clipStart, boolean selected, boolean useSTC) {
        int origX = (int)x;
        int textOffs = token.getTextOffset();
        char[] text = token.getTextArray();
        int end = textOffs + token.length();
        float nextX = x;
        int flushLen = 0;
        int flushIndex = textOffs;
        Color fg = useSTC ? host.getSelectedTextColor() : host.getForegroundForToken(token);
        Color bg = selected ? null : host.getBackgroundForToken(token);
        g.setFont(host.getFontForTokenType(token.getType()));
        FontMetrics fm = host.getFontMetricsForTokenType(token.getType());
        int ascent = fm.getAscent();
        int height = fm.getHeight();
        block4: for (int i = textOffs; i < end; ++i) {
            switch (text[i]) {
                case '\t': {
                    nextX = x + (float)fm.charsWidth(text, flushIndex, flushLen);
                    float nextNextX = e.nextTabStop(nextX, 0);
                    if (bg != null) {
                        this.paintBackground(x, y, nextNextX - x, height, g, ascent, host, bg);
                    }
                    g.setColor(fg);
                    if (flushLen > 0) {
                        g.drawChars(text, flushIndex, flushLen, (int)x, (int)y);
                        flushLen = 0;
                    }
                    flushIndex = i + 1;
                    int halfHeight = height / 2;
                    int quarterHeight = halfHeight / 2;
                    int ymid = (int)y - ascent + halfHeight;
                    g.drawLine((int)nextX, ymid, (int)nextNextX, ymid);
                    g.drawLine((int)nextNextX, ymid, (int)nextNextX - 4, ymid - quarterHeight);
                    g.drawLine((int)nextNextX, ymid, (int)nextNextX - 4, ymid + quarterHeight);
                    x = nextNextX;
                    continue block4;
                }
                case ' ': {
                    nextX = x + (float)fm.charsWidth(text, flushIndex, flushLen + 1);
                    int width = fm.charWidth(' ');
                    if (bg != null) {
                        this.paintBackground(x, y, nextX - x, height, g, ascent, host, bg);
                    }
                    g.setColor(fg);
                    if (flushLen > 0) {
                        g.drawChars(text, flushIndex, flushLen, (int)x, (int)y);
                        flushLen = 0;
                    }
                    int dotX = (int)(nextX - (float)width / 2.0f);
                    int dotY = (int)(y - (float)ascent + (float)height / 2.0f);
                    g.drawLine(dotX, dotY, dotX, dotY);
                    flushIndex = i + 1;
                    x = nextX;
                    continue block4;
                }
                default: {
                    ++flushLen;
                }
            }
        }
        nextX = x + (float)fm.charsWidth(text, flushIndex, flushLen);
        if (flushLen > 0 && nextX >= clipStart) {
            if (bg != null) {
                this.paintBackground(x, y, nextX - x, height, g, ascent, host, bg);
            }
            g.setColor(fg);
            g.drawChars(text, flushIndex, flushLen, (int)x, (int)y);
        }
        if (host.getUnderlineForToken(token)) {
            g.setColor(fg);
            int y2 = (int)(y + 1.0f);
            g.drawLine(origX, y2, (int)nextX, y2);
        }
        if (host.getPaintTabLines() && origX == host.getMargin().left) {
            this.paintTabLines(token, origX, (int)y, (int)nextX, g, e, host);
        }
        return nextX;
    }
}

