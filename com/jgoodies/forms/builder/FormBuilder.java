/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.builder;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import com.jgoodies.forms.FormsSetup;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.factories.ComponentFactory;
import com.jgoodies.forms.factories.Forms;
import com.jgoodies.forms.factories.Paddings;
import com.jgoodies.forms.internal.FocusTraversalUtilsAccessor;
import com.jgoodies.forms.internal.InternalFocusSetupUtils;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.LayoutMap;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.util.FocusTraversalType;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FocusTraversalPolicy;
import java.lang.ref.WeakReference;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class FormBuilder {
    private static final String LABELED_BY_PROPERTY = "labeledBy";
    private LayoutMap layoutMap;
    private ColumnSpec[] columnSpecs;
    private RowSpec[] rowSpecs;
    private FormLayout layout;
    private JPanel panel;
    private JComponent initialComponent;
    private FocusTraversalType focusTraversalType;
    private FocusTraversalPolicy focusTraversalPolicy;
    private boolean debug;
    private int offsetX = 0;
    private int offsetY = 0;
    private boolean labelForFeatureEnabled;
    private LabelType defaultLabelType = LabelType.DEFAULT;
    private ComponentFactory factory;
    private WeakReference mostRecentlyAddedLabelReference = null;

    protected FormBuilder() {
        this.labelForFeatureEnabled(FormsSetup.getLabelForFeatureEnabledDefault());
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public static FormBuilder create() {
        return new FormBuilder();
    }

    public JPanel build() {
        return this.getPanel();
    }

    public FormBuilder layoutMap(LayoutMap layoutMap) {
        this.layoutMap = layoutMap;
        return this;
    }

    public FormBuilder columns(String encodedColumnSpecs, Object ... args) {
        this.columnSpecs = ColumnSpec.decodeSpecs(Strings.get(encodedColumnSpecs, args), this.getLayoutMap());
        return this;
    }

    public FormBuilder appendColumns(String encodedColumnSpecs, Object ... args) {
        ColumnSpec[] newColumnSpecs;
        for (ColumnSpec columnSpec : newColumnSpecs = ColumnSpec.decodeSpecs(Strings.get(encodedColumnSpecs, args), this.getLayoutMap())) {
            this.getLayout().appendColumn(columnSpec);
        }
        return this;
    }

    public FormBuilder rows(String encodedRowSpecs, Object ... args) {
        this.rowSpecs = RowSpec.decodeSpecs(Strings.get(encodedRowSpecs, args), this.getLayoutMap());
        return this;
    }

    public FormBuilder appendRows(String encodedRowSpecs, Object ... args) {
        RowSpec[] newRowSpecs;
        for (RowSpec rowSpec : newRowSpecs = RowSpec.decodeSpecs(Strings.get(encodedRowSpecs, args), this.getLayoutMap())) {
            this.getLayout().appendRow(rowSpec);
        }
        return this;
    }

    public FormBuilder columnGroup(int ... columnIndices) {
        this.getLayout().setColumnGroup(columnIndices);
        return this;
    }

    public FormBuilder columnGroups(int[] ... multipleColumnGroups) {
        this.getLayout().setColumnGroups(multipleColumnGroups);
        return this;
    }

    public FormBuilder rowGroup(int ... rowIndices) {
        this.getLayout().setRowGroup(rowIndices);
        return this;
    }

    public FormBuilder rowGroups(int[] ... multipleRowGroups) {
        this.getLayout().setRowGroups(multipleRowGroups);
        return this;
    }

    public FormBuilder honorsVisibility(boolean b) {
        this.getLayout().setHonorsVisibility(b);
        return this;
    }

    public FormBuilder honorsVisibility(JComponent c, boolean b) {
        this.getLayout().setHonorsVisibility(c, b);
        return this;
    }

    public FormBuilder layout(FormLayout layout) {
        this.layout = Preconditions.checkNotNull(layout, "The %1$s must not be null.", "layout");
        return this;
    }

    public FormBuilder panel(JPanel panel) {
        this.panel = Preconditions.checkNotNull(panel, "The %1$s must not be null.", "panel");
        this.panel.setLayout(this.getLayout());
        return this;
    }

    public FormBuilder debug(boolean b) {
        this.debug = b;
        return this;
    }

    public FormBuilder name(String panelName) {
        this.getPanel().setName(panelName);
        return this;
    }

    public FormBuilder background(Color background) {
        this.getPanel().setBackground(background);
        this.opaque(true);
        return this;
    }

    public FormBuilder border(Border border) {
        this.getPanel().setBorder(border);
        return this;
    }

    @Deprecated
    public FormBuilder border(String paddingSpec) {
        return this.padding(paddingSpec, new Object[0]);
    }

    public FormBuilder padding(EmptyBorder padding) {
        this.getPanel().setBorder(padding);
        return this;
    }

    public FormBuilder padding(String paddingSpec, Object ... args) {
        this.padding(Paddings.createPadding(paddingSpec, args));
        return this;
    }

    public FormBuilder opaque(boolean b) {
        this.getPanel().setOpaque(b);
        return this;
    }

    public FormBuilder initialComponent(JComponent initialComponent) {
        Preconditions.checkState(this.initialComponent == null, "The initial component must be set once only.");
        this.checkValidFocusTraversalSetup();
        this.initialComponent = initialComponent;
        this.setupFocusTraversalPolicyAndProvider();
        return this;
    }

    public FormBuilder focusTraversalType(FocusTraversalType focusTraversalType) {
        Preconditions.checkNotNull(focusTraversalType, "The %1$s must not be null.", "focus traversal type");
        Preconditions.checkState(this.focusTraversalType == null, "The focus traversal type must be set once only.");
        this.checkValidFocusTraversalSetup();
        this.focusTraversalType = focusTraversalType;
        this.setupFocusTraversalPolicyAndProvider();
        return this;
    }

    public FormBuilder focusTraversalPolicy(FocusTraversalPolicy policy) {
        Preconditions.checkNotNull(policy, "The %1$s must not be null.", "focus traversal policy");
        Preconditions.checkState(this.focusTraversalPolicy == null, "The focus traversal policy must be set once only.");
        this.checkValidFocusTraversalSetup();
        this.focusTraversalPolicy = policy;
        this.setupFocusTraversalPolicyAndProvider();
        return this;
    }

    public FormBuilder focusGroup(AbstractButton ... buttons) {
        FocusTraversalUtilsAccessor.tryToBuildAFocusGroup(buttons);
        return this;
    }

    public JPanel getPanel() {
        if (this.panel == null) {
            this.panel = this.debug ? new FormDebugPanel() : new JPanel(null);
            this.panel.setOpaque(FormsSetup.getOpaqueDefault());
        }
        return this.panel;
    }

    public FormBuilder factory(ComponentFactory factory) {
        this.factory = factory;
        return this;
    }

    public FormBuilder labelForFeatureEnabled(boolean b) {
        this.labelForFeatureEnabled = b;
        return this;
    }

    public FormBuilder offset(int offsetX, int offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        return this;
    }

    public FormBuilder translate(int dX, int dY) {
        this.offsetX += dX;
        this.offsetY += dY;
        return this;
    }

    public FormBuilder defaultLabelType(LabelType newValue) {
        this.defaultLabelType = newValue;
        return this;
    }

    public ComponentAdder add(Component c) {
        return this.add(true, c);
    }

    public ComponentAdder addRaw(Component c) {
        return this.addRaw(true, c);
    }

    public ComponentAdder addScrolled(Component c) {
        return this.addScrolled(true, c);
    }

    public ComponentAdder addBar(JButton ... buttons) {
        return this.addBar(true, buttons);
    }

    public ComponentAdder addBar(JCheckBox ... checkBoxes) {
        return this.addBar(true, checkBoxes);
    }

    public ComponentAdder addBar(JRadioButton ... radioButtons) {
        return this.addBar(true, radioButtons);
    }

    public ComponentAdder addStack(JButton ... buttons) {
        return this.addStack(true, buttons);
    }

    public ComponentAdder addStack(JCheckBox ... checkBoxes) {
        return this.addStack(true, checkBoxes);
    }

    public ComponentAdder addStack(JRadioButton ... radioButtons) {
        return this.addStack(true, radioButtons);
    }

    public ViewAdder add(FormBuildingView view) {
        return this.add(true, view);
    }

    public ComponentAdder add(String markedLabelText, Object ... args) {
        return this.add(true, markedLabelText, args);
    }

    public ComponentAdder addLabel(String markedText, Object ... args) {
        return this.addLabel(true, markedText, args);
    }

    public ComponentAdder addROLabel(String markedText, Object ... args) {
        return this.addROLabel(true, markedText, args);
    }

    public ComponentAdder addTitle(String markedText, Object ... args) {
        return this.addTitle(true, markedText, args);
    }

    public ComponentAdder addSeparator(String markedText, Object ... args) {
        return this.addSeparator(true, markedText, args);
    }

    public ComponentAdder add(Icon image) {
        return this.add(true, image);
    }

    public ComponentAdder add(boolean expression, Component c) {
        if (!expression || c == null) {
            return new NoOpComponentAdder(this);
        }
        if (c instanceof JTable || c instanceof JList || c instanceof JTree) {
            return this.addScrolled(expression, c);
        }
        return this.addRaw(expression, c);
    }

    public ComponentAdder addRaw(boolean expression, Component c) {
        if (!expression || c == null) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(c);
    }

    public ComponentAdder addScrolled(boolean expression, Component c) {
        if (!expression || c == null) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(new JScrollPane(c));
    }

    public ComponentAdder addBar(boolean expression, JButton ... buttons) {
        if (!expression || buttons == null) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(Forms.buttonBar(buttons));
    }

    public ComponentAdder addBar(boolean expression, JCheckBox ... checkBoxes) {
        if (!expression) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(Forms.checkBoxBar(checkBoxes));
    }

    public ComponentAdder addBar(boolean expression, JRadioButton ... radioButtons) {
        if (!expression) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(Forms.radioButtonBar(radioButtons));
    }

    public ComponentAdder addStack(boolean expression, JButton ... buttons) {
        if (!expression || buttons == null) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(Forms.buttonStack(buttons));
    }

    public ComponentAdder addStack(boolean expression, JCheckBox ... checkBoxes) {
        if (!expression) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(Forms.checkBoxStack(checkBoxes));
    }

    public ComponentAdder addStack(boolean expression, JRadioButton ... radioButtons) {
        if (!expression || radioButtons == null) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(Forms.radioButtonStack(radioButtons));
    }

    public ViewAdder add(boolean expression, FormBuildingView view) {
        return new ViewAdder(this, expression, view);
    }

    public ComponentAdder add(boolean expression, String markedLabelText, Object ... args) {
        return this.defaultLabelType == LabelType.DEFAULT ? this.addLabel(expression, markedLabelText, args) : this.addROLabel(expression, markedLabelText, args);
    }

    public ComponentAdder addLabel(boolean expression, String markedText, Object ... args) {
        return this.addRaw(expression, this.getFactory().createLabel(Strings.get(markedText, args)));
    }

    public ComponentAdder addROLabel(boolean expression, String markedText, Object ... args) {
        return this.addRaw(expression, this.getFactory().createReadOnlyLabel(Strings.get(markedText, args)));
    }

    public ComponentAdder addTitle(boolean expression, String markedText, Object ... args) {
        String text = Strings.get(markedText, args);
        return this.addRaw(expression, this.getFactory().createTitle(text));
    }

    public ComponentAdder addSeparator(boolean expression, String markedText, Object ... args) {
        int alignment = this.isLeftToRight() ? 2 : 4;
        String text = Strings.get(markedText, args);
        return this.addRaw(expression, this.getFactory().createSeparator(text, alignment));
    }

    public ComponentAdder add(boolean expression, Icon image) {
        if (!expression || image == null) {
            return new NoOpComponentAdder(this);
        }
        return this.addImpl(new JLabel(image));
    }

    protected LayoutMap getLayoutMap() {
        if (this.layoutMap == null) {
            this.layoutMap = LayoutMap.getRoot();
        }
        return this.layoutMap;
    }

    protected FormLayout getLayout() {
        if (this.layout != null) {
            return this.layout;
        }
        Preconditions.checkNotNull(this.columnSpecs, "The layout columns must be specified.");
        Preconditions.checkNotNull(this.rowSpecs, "The layout rows must be specified.");
        this.layout = new FormLayout(this.columnSpecs, this.rowSpecs);
        return this.layout;
    }

    protected ComponentFactory getFactory() {
        if (this.factory == null) {
            this.factory = FormsSetup.getComponentFactoryDefault();
        }
        return this.factory;
    }

    protected ComponentAdder addImpl(Component c) {
        if (this.getPanel().getLayout() == null) {
            this.panel.setLayout(this.getLayout());
        }
        return new ComponentAdder(this, c);
    }

    void addImpl(Component component, CellConstraints rawConstraints) {
        CellConstraints translatedConstraints = rawConstraints.translate(this.offsetX, this.offsetY);
        this.getPanel().add(component, translatedConstraints);
        this.manageLabelsAndComponents(component);
    }

    private void manageLabelsAndComponents(Component c) {
        if (!this.labelForFeatureEnabled) {
            return;
        }
        if (c instanceof JLabel) {
            JLabel label = (JLabel)c;
            if (label.getLabelFor() == null) {
                this.setMostRecentlyAddedLabel(label);
            } else {
                this.clearMostRecentlyAddedLabel();
            }
            return;
        }
        JLabel mostRecentlyAddedLabel = this.getMostRecentlyAddedLabel();
        if (mostRecentlyAddedLabel != null && FormBuilder.isLabelForApplicable(mostRecentlyAddedLabel, c)) {
            FormBuilder.setLabelFor(mostRecentlyAddedLabel, c);
            this.clearMostRecentlyAddedLabel();
        }
    }

    private static boolean isLabelForApplicable(JLabel label, Component component) {
        if (label.getLabelFor() != null) {
            return false;
        }
        if (!component.isFocusable()) {
            return false;
        }
        if (!(component instanceof JComponent)) {
            return true;
        }
        JComponent c = (JComponent)component;
        return c.getClientProperty(LABELED_BY_PROPERTY) == null;
    }

    private static void setLabelFor(JLabel label, Component component) {
        Component labeledComponent;
        if (component instanceof JScrollPane) {
            JScrollPane scrollPane = (JScrollPane)component;
            labeledComponent = scrollPane.getViewport().getView();
        } else {
            labeledComponent = component;
        }
        label.setLabelFor(labeledComponent);
    }

    private JLabel getMostRecentlyAddedLabel() {
        if (this.mostRecentlyAddedLabelReference == null) {
            return null;
        }
        JLabel label = (JLabel)this.mostRecentlyAddedLabelReference.get();
        if (label == null) {
            return null;
        }
        return label;
    }

    private void setMostRecentlyAddedLabel(JLabel label) {
        this.mostRecentlyAddedLabelReference = new WeakReference<JLabel>(label);
    }

    private void clearMostRecentlyAddedLabel() {
        this.mostRecentlyAddedLabelReference = null;
    }

    private boolean isLeftToRight() {
        ComponentOrientation orientation = this.getPanel().getComponentOrientation();
        return orientation.isLeftToRight() || !orientation.isHorizontal();
    }

    private void checkValidFocusTraversalSetup() {
        InternalFocusSetupUtils.checkValidFocusTraversalSetup(this.focusTraversalPolicy, this.focusTraversalType, this.initialComponent);
    }

    private void setupFocusTraversalPolicyAndProvider() {
        InternalFocusSetupUtils.setupFocusTraversalPolicyAndProvider(this.getPanel(), this.focusTraversalPolicy, this.focusTraversalType, this.initialComponent);
    }

    private static final class NoOpComponentAdder
    extends ComponentAdder {
        NoOpComponentAdder(FormBuilder builder) {
            super(builder, null);
        }

        @Override
        protected FormBuilder add(CellConstraints constraints) {
            return this.builder;
        }
    }

    public static class ComponentAdder {
        protected final FormBuilder builder;
        private final Component component;
        private boolean labelForSet;

        ComponentAdder(FormBuilder builder, Component component) {
            this.builder = builder;
            this.component = component;
            this.labelForSet = false;
        }

        public final ComponentAdder labelFor(Component c) {
            Preconditions.checkArgument(this.component instanceof JLabel, "#labelFor is applicable only to JLabels");
            Preconditions.checkArgument(!this.labelForSet, "You must set the label-for-relation only once.");
            ((JLabel)this.component).setLabelFor(c);
            this.labelForSet = true;
            return this;
        }

        public final FormBuilder at(CellConstraints constraints) {
            return this.add(constraints);
        }

        public final FormBuilder xy(int col, int row) {
            return this.at(CC.xy(col, row));
        }

        public final FormBuilder xy(int col, int row, String encodedAlignments) {
            return this.at(CC.xy(col, row, encodedAlignments));
        }

        public final FormBuilder xy(int col, int row, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
            return this.at(CC.xy(col, row, colAlign, rowAlign));
        }

        public final FormBuilder xyw(int col, int row, int colSpan) {
            return this.at(CC.xyw(col, row, colSpan));
        }

        public final FormBuilder xyw(int col, int row, int colSpan, String encodedAlignments) {
            return this.at(CC.xyw(col, row, colSpan, encodedAlignments));
        }

        public final FormBuilder xyw(int col, int row, int colSpan, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
            return this.at(CC.xyw(col, row, colSpan, colAlign, rowAlign));
        }

        public final FormBuilder xywh(int col, int row, int colSpan, int rowSpan) {
            return this.at(CC.xywh(col, row, colSpan, rowSpan));
        }

        public final FormBuilder xywh(int col, int row, int colSpan, int rowSpan, String encodedAlignments) {
            return this.at(CC.xywh(col, row, colSpan, rowSpan, encodedAlignments));
        }

        public final FormBuilder xywh(int col, int row, int colSpan, int rowSpan, CellConstraints.Alignment colAlign, CellConstraints.Alignment rowAlign) {
            return this.at(CC.xywh(col, row, colSpan, rowSpan, colAlign, rowAlign));
        }

        public final FormBuilder rc(int row, int col) {
            return this.at(CC.rc(row, col));
        }

        public final FormBuilder rc(int row, int col, String encodedAlignments) {
            return this.at(CC.rc(row, col, encodedAlignments));
        }

        public final FormBuilder rc(int row, int col, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
            return this.at(CC.rc(row, col, rowAlign, colAlign));
        }

        public final FormBuilder rcw(int row, int col, int colSpan) {
            return this.at(CC.rcw(row, col, colSpan));
        }

        public final FormBuilder rcw(int row, int col, int colSpan, String encodedAlignments) {
            return this.at(CC.rcw(row, col, colSpan, encodedAlignments));
        }

        public final FormBuilder rcw(int row, int col, int colSpan, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
            return this.at(CC.rcw(row, col, colSpan, rowAlign, colAlign));
        }

        public final FormBuilder rchw(int row, int col, int rowSpan, int colSpan) {
            return this.at(CC.rchw(row, col, rowSpan, colSpan));
        }

        public final FormBuilder rchw(int row, int col, int rowSpan, int colSpan, String encodedAlignments) {
            return this.at(CC.rchw(row, col, rowSpan, colSpan, encodedAlignments));
        }

        public final FormBuilder rchw(int row, int col, int rowSpan, int colSpan, CellConstraints.Alignment rowAlign, CellConstraints.Alignment colAlign) {
            return this.at(CC.rchw(col, row, rowSpan, colSpan, colAlign, rowAlign));
        }

        protected FormBuilder add(CellConstraints constraints) {
            this.builder.addImpl(this.component, constraints);
            return this.builder;
        }
    }

    public static final class ViewAdder {
        private final FormBuilder builder;
        private final boolean expression;
        private final FormBuildingView view;

        ViewAdder(FormBuilder builder, boolean expression, FormBuildingView view) {
            this.builder = builder;
            this.expression = expression;
            this.view = view;
        }

        public FormBuilder xy(int col, int row) {
            if (this.expression && this.view != null) {
                this.builder.translate(col, row);
                this.view.buildInto(this.builder);
                this.builder.translate(-col, -row);
            }
            return this.builder;
        }
    }

    public static interface FormBuildingView {
        public void buildInto(FormBuilder var1);
    }

    public static enum LabelType {
        DEFAULT,
        READ_ONLY;

    }
}

