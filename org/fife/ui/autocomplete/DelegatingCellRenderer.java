/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

class DelegatingCellRenderer
extends DefaultListCellRenderer {
    private ListCellRenderer<Object> fallback;

    DelegatingCellRenderer() {
    }

    public ListCellRenderer<Object> getFallbackCellRenderer() {
        return this.fallback;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean hasFocus) {
        Completion c = (Completion)value;
        CompletionProvider p = c.getProvider();
        ListCellRenderer<Object> r = p.getListCellRenderer();
        if (r != null) {
            return r.getListCellRendererComponent(list, value, index, selected, hasFocus);
        }
        if (this.fallback == null) {
            return super.getListCellRendererComponent(list, value, index, selected, hasFocus);
        }
        return this.fallback.getListCellRendererComponent(list, value, index, selected, hasFocus);
    }

    public void setFallbackCellRenderer(ListCellRenderer<Object> fallback) {
        this.fallback = fallback;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.fallback instanceof JComponent && this.fallback != this) {
            ((JComponent)((Object)this.fallback)).updateUI();
        }
    }
}

