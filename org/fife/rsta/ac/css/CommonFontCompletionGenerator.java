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

class CommonFontCompletionGenerator
implements CompletionGenerator {
    private static final String ICON_KEY = "css_propertyvalue_identifier";

    CommonFontCompletionGenerator() {
    }

    @Override
    public List<Completion> generate(CompletionProvider provider, String input) {
        ArrayList<Completion> completions = new ArrayList<Completion>();
        completions.add(new FontFamilyCompletion(provider, "Georgia"));
        completions.add(new FontFamilyCompletion(provider, "\"Times New Roman\""));
        completions.add(new FontFamilyCompletion(provider, "Arial"));
        completions.add(new FontFamilyCompletion(provider, "Helvetica"));
        completions.add(new FontFamilyCompletion(provider, "Impact"));
        completions.add(new FontFamilyCompletion(provider, "\"Lucida Sans Unicode\""));
        completions.add(new FontFamilyCompletion(provider, "Tahoma"));
        completions.add(new FontFamilyCompletion(provider, "Verdana"));
        completions.add(new FontFamilyCompletion(provider, "Geneva"));
        completions.add(new FontFamilyCompletion(provider, "\"Courier New\""));
        completions.add(new FontFamilyCompletion(provider, "Courier"));
        completions.add(new FontFamilyCompletion(provider, "\"Lucida Console\""));
        completions.add(new FontFamilyCompletion(provider, "Menlo"));
        completions.add(new FontFamilyCompletion(provider, "Monaco"));
        completions.add(new FontFamilyCompletion(provider, "Consolas"));
        return completions;
    }

    private static class FontFamilyCompletion
    extends BasicCssCompletion {
        public FontFamilyCompletion(CompletionProvider provider, String value) {
            super(provider, value, CommonFontCompletionGenerator.ICON_KEY);
        }
    }
}

