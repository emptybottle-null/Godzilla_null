/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.completion;

import javax.swing.Icon;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.rsta.ac.js.completion.JSCompletionUI;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;

public class JavascriptBasicCompletion
extends BasicCompletion
implements JSCompletionUI {
    public JavascriptBasicCompletion(CompletionProvider provider, String replacementText, String shortDesc, String summary) {
        super(provider, replacementText, shortDesc, summary);
    }

    public JavascriptBasicCompletion(CompletionProvider provider, String replacementText, String shortDesc) {
        super(provider, replacementText, shortDesc);
    }

    public JavascriptBasicCompletion(CompletionProvider provider, String replacementText) {
        super(provider, replacementText);
    }

    @Override
    public Icon getIcon() {
        return IconFactory.getIcon(IconFactory.getEmptyIcon());
    }

    @Override
    public int getRelevance() {
        return 1;
    }
}

