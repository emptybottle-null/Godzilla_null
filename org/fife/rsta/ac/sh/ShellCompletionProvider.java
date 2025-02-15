/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.sh;

import org.fife.rsta.ac.c.CCompletionProvider;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class ShellCompletionProvider
extends CCompletionProvider {
    private static boolean useLocalManPages;

    @Override
    protected void addShorthandCompletions(DefaultCompletionProvider codeCP) {
    }

    @Override
    protected CompletionProvider createStringCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        cp.setAutoActivationRules(true, null);
        return cp;
    }

    @Override
    public char getParameterListEnd() {
        return '\u0000';
    }

    @Override
    public char getParameterListStart() {
        return '\u0000';
    }

    public static boolean getUseLocalManPages() {
        return useLocalManPages;
    }

    @Override
    protected String getXmlResource() {
        return "data/sh.xml";
    }

    public static void setUseLocalManPages(boolean use) {
        useLocalManPages = use;
    }
}

