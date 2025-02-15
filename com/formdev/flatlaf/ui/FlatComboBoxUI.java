/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatArrowButton;
import com.formdev.flatlaf.ui.FlatTextBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.text.JTextComponent;

public class FlatComboBoxUI
extends BasicComboBoxUI {
    protected int minimumWidth;
    protected int editorColumns;
    protected String buttonStyle;
    protected String arrowType;
    protected boolean isIntelliJTheme;
    protected Color borderColor;
    protected Color disabledBorderColor;
    protected Color editableBackground;
    protected Color disabledBackground;
    protected Color disabledForeground;
    protected Color buttonBackground;
    protected Color buttonEditableBackground;
    protected Color buttonArrowColor;
    protected Color buttonDisabledArrowColor;
    protected Color buttonHoverArrowColor;
    protected Color buttonPressedArrowColor;
    private MouseListener hoverListener;
    protected boolean hover;
    protected boolean pressed;
    private WeakReference<Component> lastRendererComponent;

    public static ComponentUI createUI(JComponent c) {
        return new FlatComboBoxUI();
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        this.hoverListener = new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                FlatComboBoxUI.this.hover = true;
                this.repaintArrowButton();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                FlatComboBoxUI.this.hover = false;
                this.repaintArrowButton();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                FlatComboBoxUI.this.pressed = true;
                this.repaintArrowButton();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                FlatComboBoxUI.this.pressed = false;
                this.repaintArrowButton();
            }

            private void repaintArrowButton() {
                if (FlatComboBoxUI.this.arrowButton != null && !FlatComboBoxUI.this.comboBox.isEditable()) {
                    FlatComboBoxUI.this.arrowButton.repaint();
                }
            }
        };
        this.comboBox.addMouseListener(this.hoverListener);
    }

    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        this.comboBox.removeMouseListener(this.hoverListener);
        this.hoverListener = null;
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(this.comboBox, "opaque", false);
        this.minimumWidth = UIManager.getInt("ComboBox.minimumWidth");
        this.editorColumns = UIManager.getInt("ComboBox.editorColumns");
        this.buttonStyle = UIManager.getString("ComboBox.buttonStyle");
        this.arrowType = UIManager.getString("Component.arrowType");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.borderColor = UIManager.getColor("Component.borderColor");
        this.disabledBorderColor = UIManager.getColor("Component.disabledBorderColor");
        this.editableBackground = UIManager.getColor("ComboBox.editableBackground");
        this.disabledBackground = UIManager.getColor("ComboBox.disabledBackground");
        this.disabledForeground = UIManager.getColor("ComboBox.disabledForeground");
        this.buttonBackground = UIManager.getColor("ComboBox.buttonBackground");
        this.buttonEditableBackground = UIManager.getColor("ComboBox.buttonEditableBackground");
        this.buttonArrowColor = UIManager.getColor("ComboBox.buttonArrowColor");
        this.buttonDisabledArrowColor = UIManager.getColor("ComboBox.buttonDisabledArrowColor");
        this.buttonHoverArrowColor = UIManager.getColor("ComboBox.buttonHoverArrowColor");
        this.buttonPressedArrowColor = UIManager.getColor("ComboBox.buttonPressedArrowColor");
        int maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount");
        if (maximumRowCount > 0 && maximumRowCount != 8 && this.comboBox.getMaximumRowCount() == 8) {
            this.comboBox.setMaximumRowCount(maximumRowCount);
        }
        this.padding = UIScale.scale(this.padding);
        MigLayoutVisualPadding.install(this.comboBox);
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.borderColor = null;
        this.disabledBorderColor = null;
        this.editableBackground = null;
        this.disabledBackground = null;
        this.disabledForeground = null;
        this.buttonBackground = null;
        this.buttonEditableBackground = null;
        this.buttonArrowColor = null;
        this.buttonDisabledArrowColor = null;
        this.buttonHoverArrowColor = null;
        this.buttonPressedArrowColor = null;
        MigLayoutVisualPadding.uninstall(this.comboBox);
    }

    @Override
    protected LayoutManager createLayoutManager() {
        return new BasicComboBoxUI.ComboBoxLayoutManager(){

            @Override
            public void layoutContainer(Container parent) {
                super.layoutContainer(parent);
                if (FlatComboBoxUI.this.editor != null && FlatComboBoxUI.this.padding != null) {
                    FlatComboBoxUI.this.editor.setBounds(FlatUIUtils.subtractInsets(FlatComboBoxUI.this.editor.getBounds(), FlatComboBoxUI.this.padding));
                }
            }
        };
    }

    @Override
    protected FocusListener createFocusListener() {
        return new BasicComboBoxUI.FocusHandler(){

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
                    FlatComboBoxUI.this.comboBox.repaint();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (FlatComboBoxUI.this.comboBox != null && FlatComboBoxUI.this.comboBox.isEditable()) {
                    FlatComboBoxUI.this.comboBox.repaint();
                }
            }
        };
    }

    @Override
    protected PropertyChangeListener createPropertyChangeListener() {
        return new BasicComboBoxUI.PropertyChangeHandler(){

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                super.propertyChange(e);
                Object source = e.getSource();
                String propertyName = e.getPropertyName();
                if (FlatComboBoxUI.this.editor != null && (source == FlatComboBoxUI.this.comboBox && propertyName == "foreground" || source == FlatComboBoxUI.this.editor && propertyName == "enabled")) {
                    FlatComboBoxUI.this.updateEditorColors();
                } else if (FlatComboBoxUI.this.editor != null && source == FlatComboBoxUI.this.comboBox && propertyName == "componentOrientation") {
                    ComponentOrientation o = (ComponentOrientation)e.getNewValue();
                    FlatComboBoxUI.this.editor.applyComponentOrientation(o);
                } else if (FlatComboBoxUI.this.editor != null && "JTextField.placeholderText".equals(propertyName)) {
                    FlatComboBoxUI.this.editor.repaint();
                } else if ("JComponent.roundRect".equals(propertyName)) {
                    FlatComboBoxUI.this.comboBox.repaint();
                } else if ("JComponent.minimumWidth".equals(propertyName)) {
                    FlatComboBoxUI.this.comboBox.revalidate();
                }
            }
        };
    }

    @Override
    protected ComboPopup createPopup() {
        return new FlatComboPopup(this.comboBox);
    }

    @Override
    protected ComboBoxEditor createEditor() {
        ComboBoxEditor comboBoxEditor = super.createEditor();
        Component editor = comboBoxEditor.getEditorComponent();
        if (editor instanceof JTextField) {
            JTextField textField = (JTextField)editor;
            textField.setColumns(this.editorColumns);
            textField.setBorder(BorderFactory.createEmptyBorder());
        }
        return comboBoxEditor;
    }

    @Override
    protected void configureEditor() {
        super.configureEditor();
        if (this.editor instanceof JTextField && ((JTextField)this.editor).getBorder() instanceof FlatTextBorder) {
            ((JTextField)this.editor).setBorder(BorderFactory.createEmptyBorder());
        }
        if (this.editor instanceof JComponent) {
            ((JComponent)this.editor).setOpaque(false);
        }
        this.editor.applyComponentOrientation(this.comboBox.getComponentOrientation());
        this.updateEditorColors();
        if (SystemInfo.isMacOS && this.editor instanceof JTextComponent) {
            InputMap inputMap = ((JTextComponent)this.editor).getInputMap();
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("UP"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_UP"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("DOWN"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("KP_DOWN"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("HOME"));
            new EditorDelegateAction(inputMap, KeyStroke.getKeyStroke("END"));
        }
    }

    private void updateEditorColors() {
        boolean isTextComponent = this.editor instanceof JTextComponent;
        this.editor.setForeground(FlatUIUtils.nonUIResource(this.getForeground(isTextComponent || this.editor.isEnabled())));
        if (isTextComponent) {
            ((JTextComponent)this.editor).setDisabledTextColor(FlatUIUtils.nonUIResource(this.getForeground(false)));
        }
    }

    @Override
    protected JButton createArrowButton() {
        return new FlatComboBoxButton();
    }

    @Override
    public void update(Graphics g, JComponent c) {
        float focusWidth = FlatUIUtils.getBorderFocusWidth(c);
        float arc = FlatUIUtils.getBorderArc(c);
        if (c.isOpaque() && (focusWidth > 0.0f || arc > 0.0f)) {
            FlatUIUtils.paintParentBackground(g, c);
        }
        Graphics2D g2 = (Graphics2D)g;
        Object[] oldRenderingHints = FlatUIUtils.setRenderingHints(g2);
        int width = c.getWidth();
        int height = c.getHeight();
        int arrowX = this.arrowButton.getX();
        int arrowWidth = this.arrowButton.getWidth();
        boolean paintButton = (this.comboBox.isEditable() || "button".equals(this.buttonStyle)) && !"none".equals(this.buttonStyle);
        boolean enabled = this.comboBox.isEnabled();
        boolean isLeftToRight = this.comboBox.getComponentOrientation().isLeftToRight();
        g2.setColor(this.getBackground(enabled));
        FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
        if (enabled) {
            g2.setColor(paintButton ? this.buttonEditableBackground : this.buttonBackground);
            Shape oldClip = g2.getClip();
            if (isLeftToRight) {
                g2.clipRect(arrowX, 0, width - arrowX, height);
            } else {
                g2.clipRect(0, 0, arrowX + arrowWidth, height);
            }
            FlatUIUtils.paintComponentBackground(g2, 0, 0, width, height, focusWidth, arc);
            g2.setClip(oldClip);
        }
        if (paintButton) {
            g2.setColor(enabled ? this.borderColor : this.disabledBorderColor);
            float lw = UIScale.scale(1.0f);
            float lx = isLeftToRight ? (float)arrowX : (float)(arrowX + arrowWidth) - lw;
            g2.fill(new Rectangle2D.Float(lx, focusWidth, lw, (float)(height - 1) - focusWidth * 2.0f));
        }
        FlatUIUtils.resetRenderingHints(g2, oldRenderingHints);
        this.paint(g, c);
    }

    @Override
    public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
        Insets rendererInsets;
        DefaultListCellRenderer renderer = this.comboBox.getRenderer();
        this.uninstallCellPaddingBorder(renderer);
        if (renderer == null) {
            renderer = new DefaultListCellRenderer();
        }
        Component c = renderer.getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, false, false);
        c.setFont(this.comboBox.getFont());
        c.applyComponentOrientation(this.comboBox.getComponentOrientation());
        this.uninstallCellPaddingBorder(c);
        boolean enabled = this.comboBox.isEnabled();
        c.setBackground(this.getBackground(enabled));
        c.setForeground(this.getForeground(enabled));
        boolean shouldValidate = c instanceof JPanel;
        if (this.padding != null) {
            bounds = FlatUIUtils.subtractInsets(bounds, this.padding);
        }
        if ((rendererInsets = this.getRendererComponentInsets(c)) != null) {
            bounds = FlatUIUtils.addInsets(bounds, rendererInsets);
        }
        this.currentValuePane.paintComponent(g, c, this.comboBox, bounds.x, bounds.y, bounds.width, bounds.height, shouldValidate);
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
    }

    protected Color getBackground(boolean enabled) {
        return enabled ? (this.editableBackground != null && this.comboBox.isEditable() ? this.editableBackground : this.comboBox.getBackground()) : (this.isIntelliJTheme ? FlatUIUtils.getParentBackground(this.comboBox) : this.disabledBackground);
    }

    protected Color getForeground(boolean enabled) {
        return enabled ? this.comboBox.getForeground() : this.disabledForeground;
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        Dimension minimumSize = super.getMinimumSize(c);
        minimumSize.width = Math.max(minimumSize.width, UIScale.scale(FlatUIUtils.minimumWidth(c, this.minimumWidth)));
        return minimumSize;
    }

    @Override
    protected Dimension getDefaultSize() {
        ListCellRenderer renderer = this.comboBox.getRenderer();
        this.uninstallCellPaddingBorder(renderer);
        Dimension size = super.getDefaultSize();
        this.uninstallCellPaddingBorder(renderer);
        return size;
    }

    @Override
    protected Dimension getDisplaySize() {
        ListCellRenderer renderer = this.comboBox.getRenderer();
        this.uninstallCellPaddingBorder(renderer);
        Dimension displaySize = super.getDisplaySize();
        if (displaySize.width == 100 + this.padding.left + this.padding.right && this.comboBox.isEditable() && this.comboBox.getItemCount() == 0 && this.comboBox.getPrototypeDisplayValue() == null) {
            int width = this.getDefaultSize().width;
            width = Math.max(width, this.editor.getPreferredSize().width);
            displaySize = new Dimension(width += this.padding.left + this.padding.right, displaySize.height);
        }
        this.uninstallCellPaddingBorder(renderer);
        return displaySize;
    }

    @Override
    protected Dimension getSizeForComponent(Component comp) {
        Dimension size = super.getSizeForComponent(comp);
        Insets rendererInsets = this.getRendererComponentInsets(comp);
        if (rendererInsets != null) {
            size = new Dimension(size.width, size.height - rendererInsets.top - rendererInsets.bottom);
        }
        return size;
    }

    private Insets getRendererComponentInsets(Component rendererComponent) {
        Border rendererBorder;
        if (rendererComponent instanceof JComponent && (rendererBorder = ((JComponent)rendererComponent).getBorder()) != null) {
            return rendererBorder.getBorderInsets(rendererComponent);
        }
        return null;
    }

    private void uninstallCellPaddingBorder(Object o) {
        CellPaddingBorder.uninstall(o);
        if (this.lastRendererComponent != null) {
            CellPaddingBorder.uninstall(this.lastRendererComponent);
            this.lastRendererComponent = null;
        }
    }

    private class EditorDelegateAction
    extends AbstractAction {
        private final KeyStroke keyStroke;

        EditorDelegateAction(InputMap inputMap, KeyStroke keyStroke) {
            this.keyStroke = keyStroke;
            inputMap.put(keyStroke, this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ActionListener action = FlatComboBoxUI.this.comboBox.getActionForKeyStroke(this.keyStroke);
            if (action != null) {
                action.actionPerformed(new ActionEvent(FlatComboBoxUI.this.comboBox, e.getID(), e.getActionCommand(), e.getWhen(), e.getModifiers()));
            }
        }
    }

    private static class CellPaddingBorder
    extends AbstractBorder {
        private final Insets padding;
        private Border rendererBorder;

        CellPaddingBorder(Insets padding) {
            this.padding = padding;
        }

        void install(JComponent rendererComponent) {
            Border oldBorder = rendererComponent.getBorder();
            if (!(oldBorder instanceof CellPaddingBorder)) {
                this.rendererBorder = oldBorder;
                rendererComponent.setBorder(this);
            }
        }

        static void uninstall(Object o) {
            if (o instanceof WeakReference) {
                o = ((WeakReference)o).get();
            }
            if (!(o instanceof JComponent)) {
                return;
            }
            JComponent rendererComponent = (JComponent)o;
            Border border = rendererComponent.getBorder();
            if (border instanceof CellPaddingBorder) {
                CellPaddingBorder paddingBorder = (CellPaddingBorder)border;
                rendererComponent.setBorder(paddingBorder.rendererBorder);
                paddingBorder.rendererBorder = null;
            }
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            if (this.rendererBorder != null) {
                Insets insideInsets = this.rendererBorder.getBorderInsets(c);
                insets.top = Math.max(this.padding.top, insideInsets.top);
                insets.left = Math.max(this.padding.left, insideInsets.left);
                insets.bottom = Math.max(this.padding.bottom, insideInsets.bottom);
                insets.right = Math.max(this.padding.right, insideInsets.right);
            } else {
                insets.top = this.padding.top;
                insets.left = this.padding.left;
                insets.bottom = this.padding.bottom;
                insets.right = this.padding.right;
            }
            return insets;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            if (this.rendererBorder != null) {
                this.rendererBorder.paintBorder(c, g, x, y, width, height);
            }
        }
    }

    protected class FlatComboPopup
    extends BasicComboPopup {
        private CellPaddingBorder paddingBorder;

        protected FlatComboPopup(JComboBox combo) {
            super(combo);
            ComponentOrientation o = this.comboBox.getComponentOrientation();
            this.list.setComponentOrientation(o);
            this.scroller.setComponentOrientation(o);
            this.setComponentOrientation(o);
        }

        @Override
        protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
            int displayWidth = FlatComboBoxUI.this.getDisplaySize().width;
            for (Border border : new Border[]{this.scroller.getViewportBorder(), this.scroller.getBorder()}) {
                if (border == null) continue;
                Insets borderInsets = border.getBorderInsets(null);
                displayWidth += borderInsets.left + borderInsets.right;
            }
            JScrollBar verticalScrollBar = this.scroller.getVerticalScrollBar();
            if (verticalScrollBar != null) {
                displayWidth += verticalScrollBar.getPreferredSize().width;
            }
            if (displayWidth > pw) {
                GraphicsConfiguration gc = this.comboBox.getGraphicsConfiguration();
                if (gc != null) {
                    Rectangle screenBounds = gc.getBounds();
                    Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);
                    displayWidth = Math.min(displayWidth, screenBounds.width - screenInsets.left - screenInsets.right);
                } else {
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    displayWidth = Math.min(displayWidth, screenSize.width);
                }
                int diff = displayWidth - pw;
                pw = displayWidth;
                if (!this.comboBox.getComponentOrientation().isLeftToRight()) {
                    px -= diff;
                }
            }
            return super.computePopupBounds(px, py, pw, ph);
        }

        @Override
        protected void configurePopup() {
            super.configurePopup();
            Border border = UIManager.getBorder("PopupMenu.border");
            if (border != null) {
                this.setBorder(border);
            }
        }

        @Override
        protected void configureList() {
            super.configureList();
            this.list.setCellRenderer(new PopupListCellRenderer());
        }

        @Override
        protected PropertyChangeListener createPropertyChangeListener() {
            return new BasicComboPopup.PropertyChangeHandler(){

                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    super.propertyChange(e);
                    if (e.getPropertyName() == "renderer") {
                        FlatComboPopup.this.list.setCellRenderer(new PopupListCellRenderer());
                    }
                }
            };
        }

        private class PopupListCellRenderer
        implements ListCellRenderer {
            private PopupListCellRenderer() {
            }

            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                DefaultListCellRenderer renderer = FlatComboPopup.this.comboBox.getRenderer();
                CellPaddingBorder.uninstall(renderer);
                CellPaddingBorder.uninstall(FlatComboBoxUI.this.lastRendererComponent);
                if (renderer == null) {
                    renderer = new DefaultListCellRenderer();
                }
                Component c = renderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.applyComponentOrientation(FlatComboPopup.this.comboBox.getComponentOrientation());
                if (c instanceof JComponent) {
                    if (FlatComboPopup.this.paddingBorder == null) {
                        FlatComboPopup.this.paddingBorder = new CellPaddingBorder(FlatComboBoxUI.this.padding);
                    }
                    FlatComboPopup.this.paddingBorder.install((JComponent)c);
                }
                FlatComboBoxUI.this.lastRendererComponent = c != renderer ? new WeakReference<Component>(c) : null;
                return c;
            }
        }
    }

    protected class FlatComboBoxButton
    extends FlatArrowButton {
        protected FlatComboBoxButton() {
            this(5, this$0.arrowType, this$0.buttonArrowColor, this$0.buttonDisabledArrowColor, this$0.buttonHoverArrowColor, null, this$0.buttonPressedArrowColor, null);
        }

        protected FlatComboBoxButton(int direction, String type, Color foreground, Color disabledForeground, Color hoverForeground, Color hoverBackground, Color pressedForeground, Color pressedBackground) {
            super(direction, type, foreground, disabledForeground, hoverForeground, hoverBackground, pressedForeground, pressedBackground);
        }

        @Override
        protected boolean isHover() {
            return super.isHover() || !FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.hover;
        }

        @Override
        protected boolean isPressed() {
            return super.isPressed() || !FlatComboBoxUI.this.comboBox.isEditable() && FlatComboBoxUI.this.pressed;
        }
    }
}

