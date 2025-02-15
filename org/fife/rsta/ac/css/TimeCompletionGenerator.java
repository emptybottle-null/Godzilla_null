/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.fife.rsta.ac.css.BasicCssCompletion;
import org.fife.rsta.ac.css.CompletionGenerator;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

class TimeCompletionGenerator
implements CompletionGenerator {
    private static final String ICON_KEY = "css_propertyvalue_identifier";
    private static final Pattern DIGITS = Pattern.compile("\\d*");

    TimeCompletionGenerator() {
    }

    @Override
    public List<Completion> generate(CompletionProvider provider, String input) {
        ArrayList<Completion> completions = new ArrayList<Completion>();
        if (DIGITS.matcher(input).matches()) {
            completions.add(new TimeCompletion(provider, input + "s"));
            completions.add(new TimeCompletion(provider, input + "ms"));
        }
        return completions;
    }

    private static class TimeCompletion
    extends BasicCssCompletion {
        public TimeCompletion(CompletionProvider provider, String value) {
            super(provider, value, TimeCompletionGenerator.ICON_KEY);
        }
    }
}

