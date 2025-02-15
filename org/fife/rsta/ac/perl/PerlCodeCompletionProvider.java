/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.perl;

import org.fife.rsta.ac.perl.PerlCompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

class PerlCodeCompletionProvider
extends DefaultCompletionProvider {
    private PerlCompletionProvider parent;

    public PerlCodeCompletionProvider(PerlCompletionProvider parent) {
        this.parent = parent;
    }

    @Override
    public char getParameterListEnd() {
        return this.parent.getParameterListEnd();
    }

    @Override
    public char getParameterListStart() {
        return this.parent.getParameterListStart();
    }

    @Override
    public boolean isValidChar(char ch) {
        return super.isValidChar(ch) || ch == '@' || ch == '$' || ch == '%';
    }
}

