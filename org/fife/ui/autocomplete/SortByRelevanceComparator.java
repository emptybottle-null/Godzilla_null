/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.io.Serializable;
import java.util.Comparator;
import org.fife.ui.autocomplete.Completion;

public class SortByRelevanceComparator
implements Comparator<Completion>,
Serializable {
    @Override
    public int compare(Completion c1, Completion c2) {
        int rel1 = c1.getRelevance();
        int rel2 = c2.getRelevance();
        int diff = rel2 - rel1;
        return diff == 0 ? c1.compareTo(c2) : diff;
    }
}

