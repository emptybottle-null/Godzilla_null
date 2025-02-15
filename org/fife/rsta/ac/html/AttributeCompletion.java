/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.html;

import org.fife.ui.autocomplete.AbstractCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public class AttributeCompletion
extends AbstractCompletion {
    private ParameterizedCompletion.Parameter param;

    public AttributeCompletion(CompletionProvider provider, ParameterizedCompletion.Parameter param) {
        super(provider);
        this.param = param;
    }

    @Override
    public String getSummary() {
        return this.param.getDescription();
    }

    @Override
    public String getReplacementText() {
        return this.param.getName();
    }
}

