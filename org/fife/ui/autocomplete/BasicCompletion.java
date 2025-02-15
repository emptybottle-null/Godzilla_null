/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import org.fife.ui.autocomplete.AbstractCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class BasicCompletion
extends AbstractCompletion {
    private String replacementText;
    private String shortDesc;
    private String summary;

    public BasicCompletion(CompletionProvider provider, String replacementText) {
        this(provider, replacementText, null);
    }

    public BasicCompletion(CompletionProvider provider, String replacementText, String shortDesc) {
        this(provider, replacementText, shortDesc, null);
    }

    public BasicCompletion(CompletionProvider provider, String replacementText, String shortDesc, String summary) {
        super(provider);
        this.replacementText = replacementText;
        this.shortDesc = shortDesc;
        this.summary = summary;
    }

    @Override
    public String getReplacementText() {
        return this.replacementText;
    }

    public String getShortDescription() {
        return this.shortDesc;
    }

    @Override
    public String getSummary() {
        return this.summary;
    }

    public void setShortDescription(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        if (this.shortDesc == null) {
            return this.getInputText();
        }
        return this.getInputText() + " - " + this.shortDesc;
    }
}

