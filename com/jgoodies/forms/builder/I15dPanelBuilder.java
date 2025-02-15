/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.builder;

import com.jgoodies.common.internal.ResourceBundleAccessor;
import com.jgoodies.common.internal.StringResourceAccess;
import com.jgoodies.common.internal.StringResourceAccessor;
import com.jgoodies.forms.FormsSetup;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.util.ResourceBundle;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

@Deprecated
public class I15dPanelBuilder
extends PanelBuilder {
    private final StringResourceAccessor resources;
    private boolean debugToolTipsEnabled = FormsSetup.getDebugToolTipsEnabledDefault();

    public I15dPanelBuilder(FormLayout layout, ResourceBundle bundle) {
        this(layout, bundle, new JPanel(null));
    }

    public I15dPanelBuilder(FormLayout layout, ResourceBundle bundle, JPanel container) {
        this(layout, new ResourceBundleAccessor(bundle), container);
    }

    public I15dPanelBuilder(FormLayout layout, StringResourceAccessor localizer) {
        this(layout, localizer, new JPanel(null));
    }

    public I15dPanelBuilder(FormLayout layout, StringResourceAccessor localizer, JPanel container) {
        super(layout, container);
        this.resources = localizer;
    }

    @Override
    public I15dPanelBuilder background(Color background) {
        super.background(background);
        return this;
    }

    @Override
    public I15dPanelBuilder border(Border border) {
        super.border(border);
        return this;
    }

    @Override
    public I15dPanelBuilder border(String emptyBorderSpec) {
        super.border(emptyBorderSpec);
        return this;
    }

    @Override
    public I15dPanelBuilder padding(EmptyBorder padding) {
        super.padding(padding);
        return this;
    }

    @Override
    public I15dPanelBuilder padding(String paddingSpec, Object ... args) {
        super.padding(paddingSpec, new Object[0]);
        return this;
    }

    @Override
    public I15dPanelBuilder opaque(boolean b) {
        super.opaque(b);
        return this;
    }

    @Override
    public I15dPanelBuilder focusTraversal(FocusTraversalPolicy policy) {
        super.focusTraversal(policy);
        return this;
    }

    public I15dPanelBuilder debugToolTipsEnabled(boolean b) {
        this.debugToolTipsEnabled = b;
        return this;
    }

    public final JLabel addI15dLabel(String resourceKey, CellConstraints constraints) {
        JLabel label = this.addLabel(this.getResourceString(resourceKey), constraints);
        if (this.isDebugToolTipsEnabled()) {
            label.setToolTipText(resourceKey);
        }
        return label;
    }

    public final JLabel addI15dLabel(String resourceKey, String encodedConstraints) {
        return this.addI15dLabel(resourceKey, new CellConstraints(encodedConstraints));
    }

    public final JLabel addI15dLabel(String resourceKey, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
        JLabel label = this.addLabel(this.getResourceString(resourceKey), labelConstraints, component, componentConstraints);
        if (this.isDebugToolTipsEnabled()) {
            label.setToolTipText(resourceKey);
        }
        return label;
    }

    public final JLabel addI15dROLabel(String resourceKey, CellConstraints constraints) {
        JLabel label = this.addROLabel(this.getResourceString(resourceKey), constraints);
        if (this.isDebugToolTipsEnabled()) {
            label.setToolTipText(resourceKey);
        }
        return label;
    }

    public final JLabel addI15dROLabel(String resourceKey, String encodedConstraints) {
        return this.addI15dROLabel(resourceKey, new CellConstraints(encodedConstraints));
    }

    public final JLabel addI15dROLabel(String resourceKey, CellConstraints labelConstraints, Component component, CellConstraints componentConstraints) {
        JLabel label = this.addROLabel(this.getResourceString(resourceKey), labelConstraints, component, componentConstraints);
        if (this.isDebugToolTipsEnabled()) {
            label.setToolTipText(resourceKey);
        }
        return label;
    }

    public final JComponent addI15dSeparator(String resourceKey, CellConstraints constraints) {
        JComponent component = this.addSeparator(this.getResourceString(resourceKey), constraints);
        if (this.isDebugToolTipsEnabled()) {
            component.setToolTipText(resourceKey);
        }
        return component;
    }

    public final JComponent addI15dSeparator(String resourceKey, String encodedConstraints) {
        return this.addI15dSeparator(resourceKey, new CellConstraints(encodedConstraints));
    }

    public final JLabel addI15dTitle(String resourceKey, CellConstraints constraints) {
        JLabel label = this.addTitle(this.getResourceString(resourceKey), constraints);
        if (this.isDebugToolTipsEnabled()) {
            label.setToolTipText(resourceKey);
        }
        return label;
    }

    public final JLabel addI15dTitle(String resourceKey, String encodedConstraints) {
        return this.addI15dTitle(resourceKey, new CellConstraints(encodedConstraints));
    }

    protected final boolean isDebugToolTipsEnabled() {
        return this.debugToolTipsEnabled;
    }

    protected final String getResourceString(String key) {
        return StringResourceAccess.getResourceString(this.resources, key, new Object[0]);
    }
}

