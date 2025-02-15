/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import javax.swing.JList;
import org.fife.rsta.ac.js.IconFactory;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.VariableCompletion;

public class JavaScriptCellRenderer
extends CompletionCellRenderer {
    @Override
    protected void prepareForOtherCompletion(JList list, Completion c, int index, boolean selected, boolean hasFocus) {
        super.prepareForOtherCompletion(list, c, index, selected, hasFocus);
        this.setIconWithDefault(c);
    }

    @Override
    protected void prepareForTemplateCompletion(JList list, TemplateCompletion tc, int index, boolean selected, boolean hasFocus) {
        super.prepareForTemplateCompletion(list, tc, index, selected, hasFocus);
        this.setIconWithDefault(tc, IconFactory.getIcon("template"));
    }

    @Override
    protected void prepareForVariableCompletion(JList list, VariableCompletion vc, int index, boolean selected, boolean hasFocus) {
        super.prepareForVariableCompletion(list, vc, index, selected, hasFocus);
        this.setIconWithDefault(vc, IconFactory.getIcon("local_variable"));
    }

    @Override
    protected void prepareForFunctionCompletion(JList list, FunctionCompletion fc, int index, boolean selected, boolean hasFocus) {
        super.prepareForFunctionCompletion(list, fc, index, selected, hasFocus);
        this.setIconWithDefault(fc, IconFactory.getIcon("default_function"));
    }
}

