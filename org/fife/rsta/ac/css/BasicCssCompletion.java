/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import javax.swing.Icon;
import org.fife.rsta.ac.css.IconFactory;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

class BasicCssCompletion
extends BasicCompletion {
    private String iconKey;

    public BasicCssCompletion(CompletionProvider provider, String value, String iconKey) {
        super(provider, value);
        this.iconKey = iconKey;
    }

    @Override
    public Icon getIcon() {
        return IconFactory.get().getIcon(this.iconKey);
    }
}

