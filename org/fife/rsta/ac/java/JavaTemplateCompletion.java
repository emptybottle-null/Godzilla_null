/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import javax.swing.Icon;
import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.JavaShorthandCompletion;
import org.fife.rsta.ac.java.JavaSourceCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.TemplateCompletion;

public class JavaTemplateCompletion
extends TemplateCompletion
implements JavaSourceCompletion {
    private String icon;

    public JavaTemplateCompletion(CompletionProvider provider, String inputText, String definitionString, String template) {
        this(provider, inputText, definitionString, template, null);
    }

    public JavaTemplateCompletion(CompletionProvider provider, String inputText, String definitionString, String template, String shortDesc) {
        this(provider, inputText, definitionString, template, shortDesc, null);
    }

    public JavaTemplateCompletion(CompletionProvider provider, String inputText, String definitionString, String template, String shortDesc, String summary) {
        super(provider, inputText, definitionString, template, shortDesc, summary);
        this.setIcon("templateIcon");
    }

    @Override
    public Icon getIcon() {
        return IconFactory.get().getIcon(this.icon);
    }

    @Override
    public void rendererText(Graphics g, int x, int y, boolean selected) {
        JavaShorthandCompletion.renderText(g, this.getInputText(), this.getShortDescription(), x, y, selected);
    }

    public void setIcon(String iconId) {
        this.icon = iconId;
    }
}

