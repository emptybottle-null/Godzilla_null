/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.internal;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.FormsSetup;
import com.jgoodies.forms.factories.ComponentFactory;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public abstract class AbstractBuilder<B extends AbstractBuilder<B>> {
    private final JPanel panel;
    private final FormLayout layout;
    protected final CellConstraints currentCellConstraints;
    private ComponentFactory componentFactory;

    protected AbstractBuilder(FormLayout layout, JPanel panel) {
        this.layout = Preconditions.checkNotNull(layout, "The %1$s must not be null.", "layout");
        this.panel = Preconditions.checkNotNull(panel, "The %1$s must not be null.", "panel");
        panel.setLayout(layout);
        this.currentCellConstraints = new CellConstraints();
    }

    public final JPanel getPanel() {
        return this.panel;
    }

    public abstract JPanel build();

    @Deprecated
    public final Container getContainer() {
        return this.panel;
    }

    public final FormLayout getLayout() {
        return this.layout;
    }

    public final int getColumnCount() {
        return this.getLayout().getColumnCount();
    }

    public final int getRowCount() {
        return this.getLayout().getRowCount();
    }

    public B background(Color background) {
        this.getPanel().setBackground(background);
        this.opaque(true);
        return (B)this;
    }

    public B border(Border border) {
        this.getPanel().setBorder(border);
        return (B)this;
    }

    @Deprecated
    public B border(String paddingSpec) {
        this.padding(Paddings.createPadding(paddingSpec, new Object[0]));
        return (B)this;
    }

    public B padding(EmptyBorder padding) {
        this.getPanel().setBorder(padding);
        return (B)this;
    }

    public B padding(String paddingSpec, Object ... args) {
        this.padding(Paddings.createPadding(paddingSpec, args));
        return (B)this;
    }

    public B opaque(boolean b) {
        this.getPanel().setOpaque(b);
        return (B)this;
    }

    public final ComponentFactory getComponentFactory() {
        if (this.componentFactory == null) {
            this.componentFactory = this.createComponentFactory();
        }
        return this.componentFactory;
    }

    public final void setComponentFactory(ComponentFactory newFactory) {
        this.componentFactory = newFactory;
    }

    protected ComponentFactory createComponentFactory() {
        return FormsSetup.getComponentFactoryDefault();
    }
}

