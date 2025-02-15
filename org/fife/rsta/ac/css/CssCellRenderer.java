/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import javax.swing.Icon;
import javax.swing.JList;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.VariableCompletion;

class CssCellRenderer
extends CompletionCellRenderer {
    private Icon tagIcon = this.getIcon("../html/tag.png");

    @Override
    protected void prepareForFunctionCompletion(JList list, FunctionCompletion fc, int index, boolean selected, boolean hasFocus) {
        super.prepareForFunctionCompletion(list, fc, index, selected, hasFocus);
        this.setIconWithDefault(fc);
    }

    @Override
    protected void prepareForMarkupTagCompletion(JList list, MarkupTagCompletion c, int index, boolean selected, boolean hasFocus) {
        super.prepareForMarkupTagCompletion(list, c, index, selected, hasFocus);
        this.setIcon(this.tagIcon);
    }

    @Override
    protected void prepareForOtherCompletion(JList list, Completion c, int index, boolean selected, boolean hasFocus) {
        super.prepareForOtherCompletion(list, c, index, selected, hasFocus);
        this.setIconWithDefault(c);
    }

    @Override
    protected void prepareForVariableCompletion(JList list, VariableCompletion vc, int index, boolean selected, boolean hasFocus) {
        super.prepareForVariableCompletion(list, vc, index, selected, hasFocus);
        this.setIcon(this.getEmptyIcon());
    }
}

