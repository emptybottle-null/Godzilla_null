/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.jsp;

import org.fife.rsta.ac.jsp.JspCompletionProvider;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.MarkupTagCompletion;

class TldElement
extends MarkupTagCompletion {
    public TldElement(JspCompletionProvider provider, String name, String desc) {
        super((CompletionProvider)provider, name);
        this.setDescription(desc);
    }
}

