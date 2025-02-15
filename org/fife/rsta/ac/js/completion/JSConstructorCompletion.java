/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import javax.swing.Icon;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.completion.JSFunctionCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class JSConstructorCompletion
extends JSFunctionCompletion {
    public JSConstructorCompletion(CompletionProvider provider, MethodInfo method) {
        super(provider, method);
    }

    @Override
    public Icon getIcon() {
        return IconFactory.getIcon("function");
    }
}

