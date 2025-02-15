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

class PercentageOrLengthCompletionGenerator
implements CompletionGenerator {
    private boolean includePercentage;
    private static final String ICON_KEY = "css_propertyvalue_unit";
    private static final Pattern DIGITS = Pattern.compile("\\d*");

    public PercentageOrLengthCompletionGenerator(boolean includePercentage) {
        this.includePercentage = includePercentage;
    }

    @Override
    public List<Completion> generate(CompletionProvider provider, String input) {
        ArrayList<Completion> completions = new ArrayList<Completion>();
        if (DIGITS.matcher(input).matches()) {
            completions.add(new POrLCompletion(provider, input + "em"));
            completions.add(new POrLCompletion(provider, input + "ex"));
            completions.add(new POrLCompletion(provider, input + "ch"));
            completions.add(new POrLCompletion(provider, input + "rem"));
            completions.add(new POrLCompletion(provider, input + "vh"));
            completions.add(new POrLCompletion(provider, input + "vw"));
            completions.add(new POrLCompletion(provider, input + "vmin"));
            completions.add(new POrLCompletion(provider, input + "vmax"));
            completions.add(new POrLCompletion(provider, input + "px"));
            completions.add(new POrLCompletion(provider, input + "in"));
            completions.add(new POrLCompletion(provider, input + "cm"));
            completions.add(new POrLCompletion(provider, input + "mm"));
            completions.add(new POrLCompletion(provider, input + "pt"));
            completions.add(new POrLCompletion(provider, input + "pc"));
            if (this.includePercentage) {
                completions.add(new POrLCompletion(provider, input + "%"));
            }
        }
        return completions;
    }

    private static class POrLCompletion
    extends BasicCssCompletion {
        public POrLCompletion(CompletionProvider provider, String value) {
            super(provider, value, PercentageOrLengthCompletionGenerator.ICON_KEY);
        }
    }
}

