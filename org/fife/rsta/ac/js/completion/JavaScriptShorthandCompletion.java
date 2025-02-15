/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import javax.swing.Icon;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.completion.JSCompletionUI;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class JavaScriptShorthandCompletion
extends ShorthandCompletion
implements JSCompletionUI {
    private static final String PREFIX = "<html><nobr>";

    public JavaScriptShorthandCompletion(CompletionProvider provider, String inputText, String replacementText) {
        super(provider, inputText, replacementText);
    }

    public JavaScriptShorthandCompletion(CompletionProvider provider, String inputText, String replacementText, String shortDesc) {
        super(provider, inputText, replacementText, shortDesc);
    }

    public JavaScriptShorthandCompletion(CompletionProvider provider, String inputText, String replacementText, String shortDesc, String summary) {
        super(provider, inputText, replacementText, shortDesc, summary);
    }

    @Override
    public Icon getIcon() {
        return IconFactory.getIcon("template");
    }

    @Override
    public int getRelevance() {
        return 0;
    }

    public String getShortDescriptionText() {
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(this.getInputText());
        sb.append(" - ");
        sb.append(this.getShortDescription());
        return sb.toString();
    }
}

