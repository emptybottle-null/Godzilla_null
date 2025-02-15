/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.css.BasicCssCompletion;
import org.fife.rsta.ac.css.CompletionGenerator;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

class BorderStyleCompletionGenerator
implements CompletionGenerator {
    private static final String ICON_KEY = "css_propertyvalue_identifier";

    BorderStyleCompletionGenerator() {
    }

    @Override
    public List<Completion> generate(CompletionProvider provider, String input) {
        ArrayList<Completion> completions = new ArrayList<Completion>();
        completions.add(new BorderStyleCompletion(provider, "none"));
        completions.add(new BorderStyleCompletion(provider, "hidden"));
        completions.add(new BorderStyleCompletion(provider, "dotted"));
        completions.add(new BorderStyleCompletion(provider, "dashed"));
        completions.add(new BorderStyleCompletion(provider, "solid"));
        completions.add(new BorderStyleCompletion(provider, "double"));
        completions.add(new BorderStyleCompletion(provider, "groove"));
        completions.add(new BorderStyleCompletion(provider, "ridge"));
        completions.add(new BorderStyleCompletion(provider, "inset"));
        completions.add(new BorderStyleCompletion(provider, "outset"));
        return completions;
    }

    private static class BorderStyleCompletion
    extends BasicCssCompletion {
        public BorderStyleCompletion(CompletionProvider provider, String value) {
            super(provider, value, BorderStyleCompletionGenerator.ICON_KEY);
        }
    }
}

