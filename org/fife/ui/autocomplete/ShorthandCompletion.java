/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class ShorthandCompletion
extends BasicCompletion {
    private String inputText;

    public ShorthandCompletion(CompletionProvider provider, String inputText, String replacementText) {
        super(provider, replacementText);
        this.inputText = inputText;
    }

    public ShorthandCompletion(CompletionProvider provider, String inputText, String replacementText, String shortDesc) {
        super(provider, replacementText, shortDesc);
        this.inputText = inputText;
    }

    public ShorthandCompletion(CompletionProvider provider, String inputText, String replacementText, String shortDesc, String summary) {
        super(provider, replacementText, shortDesc, summary);
        this.inputText = inputText;
    }

    @Override
    public String getInputText() {
        return this.inputText;
    }

    @Override
    public String getSummary() {
        String summary = super.getSummary();
        return summary != null ? summary : "<html><body>" + this.getSummaryBody();
    }

    protected String getSummaryBody() {
        return "<code>" + this.getReplacementText();
    }
}

