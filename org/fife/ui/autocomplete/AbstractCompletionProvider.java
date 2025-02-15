/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.CompletionProviderBase;
import org.fife.ui.autocomplete.Util;

public abstract class AbstractCompletionProvider
extends CompletionProviderBase {
    protected List<Completion> completions;
    protected CaseInsensitiveComparator comparator = new CaseInsensitiveComparator();

    public AbstractCompletionProvider() {
        this.clearParameterizedCompletionParams();
        this.completions = new ArrayList<Completion>();
    }

    public void addCompletion(Completion c) {
        this.checkProviderAndAdd(c);
        Collections.sort(this.completions);
    }

    public void addCompletions(List<Completion> completions) {
        for (Completion c : completions) {
            this.checkProviderAndAdd(c);
        }
        Collections.sort(this.completions);
    }

    protected void addWordCompletions(String[] words) {
        int count = words == null ? 0 : words.length;
        for (int i = 0; i < count; ++i) {
            this.completions.add(new BasicCompletion((CompletionProvider)this, words[i]));
        }
        Collections.sort(this.completions);
    }

    protected void checkProviderAndAdd(Completion c) {
        if (c.getProvider() != this) {
            throw new IllegalArgumentException("Invalid CompletionProvider");
        }
        this.completions.add(c);
    }

    public void clear() {
        this.completions.clear();
    }

    public List<Completion> getCompletionByInputText(String inputText) {
        int start;
        int end = Collections.binarySearch(this.completions, inputText, this.comparator);
        if (end < 0) {
            return null;
        }
        for (start = end; start > 0 && this.comparator.compare(this.completions.get(start - 1), inputText) == 0; --start) {
        }
        int count = this.completions.size();
        while (++end < count && this.comparator.compare(this.completions.get(end), inputText) == 0) {
        }
        return this.completions.subList(start, end);
    }

    @Override
    protected List<Completion> getCompletionsImpl(JTextComponent comp) {
        ArrayList<Completion> retVal = new ArrayList<Completion>();
        String text = this.getAlreadyEnteredText(comp);
        if (text != null) {
            Completion c;
            int index = Collections.binarySearch(this.completions, text, this.comparator);
            if (index < 0) {
                index = -index - 1;
            } else {
                for (int pos = index - 1; pos > 0 && this.comparator.compare(this.completions.get(pos), text) == 0; --pos) {
                    retVal.add(this.completions.get(pos));
                }
            }
            while (index < this.completions.size() && Util.startsWithIgnoreCase((c = this.completions.get(index)).getInputText(), text)) {
                retVal.add(c);
                ++index;
            }
        }
        return retVal;
    }

    public boolean removeCompletion(Completion c) {
        int index = Collections.binarySearch(this.completions, c);
        if (index < 0) {
            return false;
        }
        this.completions.remove(index);
        return true;
    }

    public static class CaseInsensitiveComparator
    implements Comparator,
    Serializable {
        public int compare(Object o1, Object o2) {
            String s1 = o1 instanceof String ? (String)o1 : ((Completion)o1).getInputText();
            String s2 = o2 instanceof String ? (String)o2 : ((Completion)o2).getInputText();
            return String.CASE_INSENSITIVE_ORDER.compare(s1, s2);
        }
    }
}

