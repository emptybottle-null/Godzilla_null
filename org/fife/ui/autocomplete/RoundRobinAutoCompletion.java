/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public class RoundRobinAutoCompletion
extends AutoCompletion {
    private List<CompletionProvider> cycle = new ArrayList<CompletionProvider>();

    public RoundRobinAutoCompletion(CompletionProvider provider) {
        super(provider);
        this.cycle.add(provider);
        this.setHideOnCompletionProviderChange(false);
        this.setHideOnNoText(false);
        this.setAutoCompleteSingleChoices(false);
    }

    public void addCompletionProvider(CompletionProvider provider) {
        this.cycle.add(provider);
    }

    public boolean advanceProvider() {
        CompletionProvider currentProvider = this.getCompletionProvider();
        int i = (this.cycle.indexOf(currentProvider) + 1) % this.cycle.size();
        this.setCompletionProvider(this.cycle.get(i));
        return i == 0;
    }

    @Override
    protected Action createAutoCompleteAction() {
        return new CycleAutoCompleteAction();
    }

    public void resetProvider() {
        CompletionProvider defaultProvider;
        CompletionProvider currentProvider = this.getCompletionProvider();
        if (currentProvider != (defaultProvider = this.cycle.get(0))) {
            this.setCompletionProvider(defaultProvider);
        }
    }

    private class CycleAutoCompleteAction
    extends AutoCompletion.AutoCompleteAction {
        private CycleAutoCompleteAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (RoundRobinAutoCompletion.this.isAutoCompleteEnabled()) {
                List<Completion> completions;
                if (RoundRobinAutoCompletion.this.isPopupVisible()) {
                    RoundRobinAutoCompletion.this.advanceProvider();
                } else {
                    RoundRobinAutoCompletion.this.resetProvider();
                }
                for (int i = 1; i < RoundRobinAutoCompletion.this.cycle.size() && (completions = RoundRobinAutoCompletion.this.getCompletionProvider().getCompletions(RoundRobinAutoCompletion.this.getTextComponent())).size() <= 0; ++i) {
                    RoundRobinAutoCompletion.this.advanceProvider();
                }
            }
            super.actionPerformed(e);
        }
    }
}

