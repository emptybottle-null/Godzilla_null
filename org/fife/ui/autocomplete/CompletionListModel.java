/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractListModel;
import org.fife.ui.autocomplete.Completion;

class CompletionListModel
extends AbstractListModel<Completion> {
    private List<Completion> delegate = new ArrayList<Completion>();

    CompletionListModel() {
    }

    public void clear() {
        int end = this.delegate.size() - 1;
        this.delegate.clear();
        if (end >= 0) {
            this.fireIntervalRemoved(this, 0, end);
        }
    }

    @Override
    public Completion getElementAt(int index) {
        return this.delegate.get(index);
    }

    @Override
    public int getSize() {
        return this.delegate.size();
    }

    public void setContents(Collection<Completion> contents) {
        this.clear();
        int count = contents.size();
        if (count > 0) {
            this.delegate.addAll(contents);
            this.fireIntervalAdded(this, 0, count - 1);
        }
    }
}

