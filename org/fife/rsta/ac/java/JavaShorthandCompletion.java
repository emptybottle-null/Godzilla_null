/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.JavaSourceCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

class JavaShorthandCompletion
extends ShorthandCompletion
implements JavaSourceCompletion {
    private static final Color SHORTHAND_COLOR = new Color(0, 127, 174);

    public JavaShorthandCompletion(CompletionProvider provider, String inputText, String replacementText) {
        super(provider, inputText, replacementText);
    }

    public JavaShorthandCompletion(CompletionProvider provider, String inputText, String replacementText, String shortDesc) {
        super(provider, inputText, replacementText, shortDesc);
    }

    @Override
    public Icon getIcon() {
        return IconFactory.get().getIcon("templateIcon");
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        JavaShorthandCompletion.renderText(g, this.getInputText(), this.getReplacementText(), x, y, selected);
    }

    public static void renderText(Graphics g, String input, String shortDesc, int x, int y, boolean selected) {
        Color orig = g.getColor();
        if (!selected && shortDesc != null) {
            g.setColor(SHORTHAND_COLOR);
        }
        g.drawString(input, x, y);
        if (shortDesc != null) {
            x += g.getFontMetrics().stringWidth(input);
            if (!selected) {
                g.setColor(orig);
            }
            String temp = " - ";
            g.drawString(temp, x, y);
            x += g.getFontMetrics().stringWidth(temp);
            if (!selected) {
                g.setColor(Color.GRAY);
            }
            g.drawString(shortDesc, x, y);
        }
    }
}

