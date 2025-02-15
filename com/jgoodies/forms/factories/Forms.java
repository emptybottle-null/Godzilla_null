/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.factories;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.internal.FocusTraversalUtilsAccessor;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public final class Forms {
    private Forms() {
    }

    public static JComponent single(String columnSpec, String rowSpec, JComponent component) {
        Preconditions.checkNotBlank(columnSpec, "The %1$s must not be null, empty, or whitespace.", "column specification");
        Preconditions.checkNotBlank(rowSpec, "The %1$s must not be null, empty, or whitespace.", "row specification");
        Preconditions.checkNotNull(component, "The %1$s must not be null.", "component");
        FormLayout layout = new FormLayout(columnSpec, rowSpec);
        PanelBuilder builder = new PanelBuilder(layout);
        builder.add((Component)component, CC.xy(1, 1));
        return builder.build();
    }

    public static JComponent centered(JComponent component) {
        return Forms.single("center:pref:grow", "c:p:g", component);
    }

    public static JComponent border(Border border, JComponent component) {
        JComponent container = Forms.single("fill:pref", "f:p", component);
        container.setBorder(border);
        return container;
    }

    @Deprecated
    public static JComponent border(String emptyBorderSpec, JComponent component) {
        return Forms.padding(component, emptyBorderSpec, new Object[0]);
    }

    public static JComponent padding(JComponent component, EmptyBorder padding) {
        JComponent container = Forms.single("fill:pref", "f:p", component);
        container.setBorder(padding);
        return container;
    }

    public static JComponent padding(JComponent component, String paddingSpec, Object ... args) {
        return Forms.padding(component, Paddings.createPadding(paddingSpec, args));
    }

    public static JComponent horizontal(String gapColSpec, JComponent ... components) {
        Preconditions.checkNotBlank(gapColSpec, "The %1$s must not be null, empty, or whitespace.", "gap column specification");
        Preconditions.checkNotNull(components, "The %1$s must not be null.", "component array");
        Preconditions.checkArgument(components.length > 1, "You must provide more than one component.");
        FormLayout layout = new FormLayout(components.length - 1 + "*(pref, " + gapColSpec + "), pref", "p");
        PanelBuilder builder = new PanelBuilder(layout);
        int column = 1;
        for (JComponent component : components) {
            builder.add((Component)component, CC.xy(column, 1));
            column += 2;
        }
        return builder.build();
    }

    public static JComponent vertical(String gapRowSpec, JComponent ... components) {
        Preconditions.checkNotBlank(gapRowSpec, "The %1$s must not be null, empty, or whitespace.", "gap row specification");
        Preconditions.checkNotNull(components, "The %1$s must not be null.", "component array");
        Preconditions.checkArgument(components.length > 1, "You must provide more than one component.");
        FormLayout layout = new FormLayout("pref", components.length - 1 + "*(p, " + gapRowSpec + "), p");
        PanelBuilder builder = new PanelBuilder(layout);
        int row = 1;
        for (JComponent component : components) {
            builder.add((Component)component, CC.xy(1, row));
            row += 2;
        }
        return builder.build();
    }

    public static JComponent buttonBar(JComponent ... buttons) {
        return ButtonBarBuilder.create().addButton(buttons).build();
    }

    public static JComponent buttonStack(JComponent ... buttons) {
        return ButtonStackBuilder.create().addButton(buttons).build();
    }

    public static JComponent checkBoxBar(JCheckBox ... checkBoxes) {
        return Forms.buildGroupedButtonBar(checkBoxes);
    }

    public static JComponent checkBoxStack(JCheckBox ... checkBoxes) {
        return Forms.buildGroupedButtonStack(checkBoxes);
    }

    public static JComponent radioButtonBar(JRadioButton ... radioButtons) {
        return Forms.buildGroupedButtonBar(radioButtons);
    }

    public static JComponent radioButtonStack(JRadioButton ... radioButtons) {
        return Forms.buildGroupedButtonStack(radioButtons);
    }

    private static JComponent buildGroupedButtonBar(AbstractButton ... buttons) {
        Preconditions.checkArgument(buttons.length > 1, "You must provide more than one button.");
        FormLayout layout = new FormLayout(String.format("pref, %s*($rgap, pref)", buttons.length - 1), "p");
        PanelBuilder builder = new PanelBuilder(layout);
        int column = 1;
        for (AbstractButton button : buttons) {
            builder.add((Component)button, CC.xy(column, 1));
            column += 2;
        }
        FocusTraversalUtilsAccessor.tryToBuildAFocusGroup(buttons);
        return builder.build();
    }

    private static JComponent buildGroupedButtonStack(AbstractButton ... buttons) {
        Preconditions.checkArgument(buttons.length > 1, "You must provide more than one button.");
        FormLayout layout = new FormLayout("pref", String.format("p, %s*(0, p)", buttons.length - 1));
        PanelBuilder builder = new PanelBuilder(layout);
        int row = 1;
        for (AbstractButton button : buttons) {
            builder.add((Component)button, CC.xy(1, row));
            row += 2;
        }
        FocusTraversalUtilsAccessor.tryToBuildAFocusGroup(buttons);
        return builder.build();
    }
}

