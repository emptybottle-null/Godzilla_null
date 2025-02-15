/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.builder;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import com.jgoodies.forms.FormsSetup;
import com.jgoodies.forms.builder.FormBuilder;
import com.jgoodies.forms.factories.ComponentFactory;
import com.jgoodies.forms.factories.Forms;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.internal.InternalFocusSetupUtils;
import com.jgoodies.forms.util.FocusTraversalType;
import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public final class ListViewBuilder {
    private ComponentFactory factory;
    private JComponent label;
    private JComponent filterView;
    private JComponent listView;
    private JComponent listBarView;
    private JComponent listExtrasView;
    private JComponent detailsView;
    private JComponent listStackView;
    private Border border;
    private boolean honorsVisibility = true;
    private Component initialComponent;
    private FocusTraversalType focusTraversalType;
    private FocusTraversalPolicy focusTraversalPolicy;
    private String namePrefix = "ListView";
    private String filterViewColSpec = "[100dlu, p]";
    private String listViewRowSpec = "fill:[100dlu, d]:grow";
    private JComponent panel;

    private ListViewBuilder() {
    }

    public static ListViewBuilder create() {
        return new ListViewBuilder();
    }

    public ListViewBuilder border(Border border) {
        this.border = border;
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder padding(EmptyBorder padding) {
        this.border(padding);
        return this;
    }

    public ListViewBuilder padding(String paddingSpec, Object ... args) {
        this.padding(Paddings.createPadding(paddingSpec, args));
        return this;
    }

    public ListViewBuilder initialComponent(JComponent initialComponent) {
        Preconditions.checkNotNull(initialComponent, "The %1$s must not be null.", "initial component");
        Preconditions.checkState(this.initialComponent == null, "The initial component must be set once only.");
        this.checkValidFocusTraversalSetup();
        this.initialComponent = initialComponent;
        return this;
    }

    public ListViewBuilder focusTraversalType(FocusTraversalType focusTraversalType) {
        Preconditions.checkNotNull(focusTraversalType, "The %1$s must not be null.", "focus traversal type");
        Preconditions.checkState(this.focusTraversalType == null, "The focus traversal type must be set once only.");
        this.checkValidFocusTraversalSetup();
        this.focusTraversalType = focusTraversalType;
        return this;
    }

    public ListViewBuilder focusTraversalPolicy(FocusTraversalPolicy policy) {
        Preconditions.checkNotNull(policy, "The %1$s must not be null.", "focus traversal policy");
        Preconditions.checkState(this.focusTraversalPolicy == null, "The focus traversal policy must be set once only.");
        this.checkValidFocusTraversalSetup();
        this.focusTraversalPolicy = policy;
        return this;
    }

    public ListViewBuilder honorVisibility(boolean b) {
        this.honorsVisibility = b;
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder namePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
        return this;
    }

    public ListViewBuilder factory(ComponentFactory factory) {
        this.factory = factory;
        return this;
    }

    public ListViewBuilder label(JComponent labelView) {
        this.label = labelView;
        this.overrideNameIfBlank(labelView, "label");
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder labelText(String markedText, Object ... args) {
        this.label(this.getFactory().createLabel(Strings.get(markedText, args)));
        return this;
    }

    public ListViewBuilder headerText(String markedText, Object ... args) {
        this.label(this.getFactory().createHeaderLabel(Strings.get(markedText, args)));
        return this;
    }

    public ListViewBuilder filterView(JComponent filterView) {
        this.filterView = filterView;
        this.overrideNameIfBlank(filterView, "filter");
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder filterViewColumn(String colSpec, Object ... args) {
        Preconditions.checkNotNull(colSpec, "The %1$s must not be null, empty, or whitespace.", "filter view column specification");
        this.filterViewColSpec = Strings.get(colSpec, args);
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder listView(JComponent listView) {
        Preconditions.checkNotNull(listView, "The %1$s must not be null, empty, or whitespace.", "list view");
        this.listView = listView instanceof JTable || listView instanceof JList || listView instanceof JTree ? new JScrollPane(listView) : listView;
        this.overrideNameIfBlank(listView, "listView");
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder listViewRow(String rowSpec, Object ... args) {
        Preconditions.checkNotNull(rowSpec, "The %1$s must not be null, empty, or whitespace.", "list view row specification");
        this.listViewRowSpec = Strings.get(rowSpec, args);
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder listBarView(JComponent listBarView) {
        this.listBarView = listBarView;
        this.overrideNameIfBlank(listBarView, "listBarView");
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder listBar(JComponent ... buttons) {
        this.listBarView(Forms.buttonBar(buttons));
        return this;
    }

    public ListViewBuilder listStackView(JComponent listStackView) {
        this.listStackView = listStackView;
        this.overrideNameIfBlank(listStackView, "listStackView");
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder listStack(JComponent ... buttons) {
        this.listStackView(Forms.buttonStack(buttons));
        return this;
    }

    public ListViewBuilder listExtrasView(JComponent listExtrasView) {
        this.listExtrasView = listExtrasView;
        this.overrideNameIfBlank(listExtrasView, "listExtrasView");
        this.invalidatePanel();
        return this;
    }

    public ListViewBuilder detailsView(JComponent detailsView) {
        this.detailsView = detailsView;
        this.overrideNameIfBlank(detailsView, "detailsView");
        this.invalidatePanel();
        return this;
    }

    public JComponent build() {
        if (this.panel == null) {
            this.panel = this.buildPanel();
        }
        return this.panel;
    }

    private ComponentFactory getFactory() {
        if (this.factory == null) {
            this.factory = FormsSetup.getComponentFactoryDefault();
        }
        return this.factory;
    }

    private void invalidatePanel() {
        this.panel = null;
    }

    private JComponent buildPanel() {
        JLabel theLabel;
        Preconditions.checkNotNull(this.listView, "The list view must be set before #build is invoked.");
        String stackGap = this.hasStack() ? "$rg" : "0";
        String detailsGap = this.hasDetails() ? "14dlu" : "0";
        FormBuilder builder = FormBuilder.create().columns("fill:default:grow, %s, p", stackGap).rows("p, %1$s, p, %2$s, p", this.listViewRowSpec, detailsGap).honorsVisibility(this.honorsVisibility).border(this.border).add(this.hasHeader(), this.buildHeader()).xy(1, 1).add(true, this.listView).xy(1, 2).add(this.hasOperations(), this.buildOperations()).xy(1, 3).add(this.hasStack(), this.listStackView).xy(3, 2).add(this.hasDetails(), this.detailsView).xy(1, 5);
        if (this.label instanceof JLabel && (theLabel = (JLabel)this.label).getLabelFor() == null) {
            theLabel.setLabelFor(this.listView);
        }
        InternalFocusSetupUtils.setupFocusTraversalPolicyAndProvider(builder.getPanel(), this.focusTraversalPolicy, this.focusTraversalType, this.initialComponent);
        return builder.build();
    }

    private JComponent buildHeader() {
        if (!this.hasHeader()) {
            return null;
        }
        String columnSpec = this.hasFilter() ? "default:grow, 9dlu, %s" : "default:grow, 0,    0";
        return FormBuilder.create().columns(columnSpec, this.filterViewColSpec).rows("[14dlu, p], $lcg", new Object[0]).labelForFeatureEnabled(false).add(this.hasLabel(), this.label).xy(1, 1).add(this.hasFilter(), this.filterView).xy(3, 1).build();
    }

    private JComponent buildOperations() {
        if (!this.hasOperations()) {
            return null;
        }
        String gap = this.hasListExtras() ? "9dlu" : "0";
        return FormBuilder.create().columns("left:default, %s:grow, right:pref", gap).rows("$rgap, p", new Object[0]).honorsVisibility(this.honorsVisibility).add(this.hasListBar(), this.listBarView).xy(1, 2).add(this.hasListExtras(), this.listExtrasView).xy(3, 2).build();
    }

    private boolean hasLabel() {
        return this.label != null;
    }

    private boolean hasFilter() {
        return this.filterView != null;
    }

    private boolean hasHeader() {
        return this.hasLabel() || this.hasFilter();
    }

    private boolean hasListBar() {
        return this.listBarView != null;
    }

    private boolean hasListExtras() {
        return this.listExtrasView != null;
    }

    private boolean hasOperations() {
        return this.hasListBar() || this.hasListExtras();
    }

    private boolean hasStack() {
        return this.listStackView != null;
    }

    private boolean hasDetails() {
        return this.detailsView != null;
    }

    private void overrideNameIfBlank(JComponent component, String suffix) {
        if (component != null && Strings.isBlank(component.getName())) {
            component.setName(this.namePrefix + '.' + suffix);
        }
    }

    private void checkValidFocusTraversalSetup() {
        InternalFocusSetupUtils.checkValidFocusTraversalSetup(this.focusTraversalPolicy, this.focusTraversalType, this.initialComponent);
    }
}

