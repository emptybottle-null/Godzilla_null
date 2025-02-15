/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.factories;

import com.jgoodies.common.base.Preconditions;
import com.jgoodies.common.base.Strings;
import com.jgoodies.common.swing.MnemonicUtils;
import com.jgoodies.forms.factories.ComponentFactory;
import com.jgoodies.forms.layout.Sizes;
import com.jgoodies.forms.util.FormUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.accessibility.AccessibleContext;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;

public class DefaultComponentFactory
implements ComponentFactory {
    private static final DefaultComponentFactory INSTANCE = new DefaultComponentFactory();

    public static DefaultComponentFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public JLabel createLabel(String textWithMnemonic) {
        FormsLabel label = new FormsLabel();
        MnemonicUtils.configure(label, textWithMnemonic);
        return label;
    }

    @Override
    public JLabel createReadOnlyLabel(String textWithMnemonic) {
        ReadOnlyLabel label = new ReadOnlyLabel();
        MnemonicUtils.configure(label, textWithMnemonic);
        return label;
    }

    @Override
    public JButton createButton(Action action) {
        return new JButton(action);
    }

    @Override
    public JLabel createTitle(String textWithMnemonic) {
        TitleLabel label = new TitleLabel();
        MnemonicUtils.configure(label, textWithMnemonic);
        label.setVerticalAlignment(0);
        return label;
    }

    @Override
    public JLabel createHeaderLabel(String markedText) {
        return this.createTitle(markedText);
    }

    public JComponent createSeparator(String textWithMnemonic) {
        return this.createSeparator(textWithMnemonic, 2);
    }

    @Override
    public JComponent createSeparator(String textWithMnemonic, int alignment) {
        if (Strings.isBlank(textWithMnemonic)) {
            return new JSeparator();
        }
        JLabel title = this.createTitle(textWithMnemonic);
        title.setHorizontalAlignment(alignment);
        return this.createSeparator(title);
    }

    public JComponent createSeparator(JLabel label) {
        Preconditions.checkNotNull(label, "The label must not be null.");
        int horizontalAlignment = label.getHorizontalAlignment();
        Preconditions.checkArgument(horizontalAlignment == 2 || horizontalAlignment == 0 || horizontalAlignment == 4, "The label's horizontal alignment must be one of: LEFT, CENTER, RIGHT.");
        JPanel panel = new JPanel(new TitledSeparatorLayout(!FormUtils.isLafAqua()));
        panel.setOpaque(false);
        panel.add(label);
        panel.add(new JSeparator());
        if (horizontalAlignment == 0) {
            panel.add(new JSeparator());
        }
        return panel;
    }

    private static final class TitledSeparatorLayout
    implements LayoutManager {
        private final boolean centerSeparators;

        private TitledSeparatorLayout(boolean centerSeparators) {
            this.centerSeparators = centerSeparators;
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return this.preferredLayoutSize(parent);
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            JLabel label = TitledSeparatorLayout.getLabel(parent);
            Dimension labelSize = ((Component)label).getPreferredSize();
            Insets insets = parent.getInsets();
            int width = labelSize.width + insets.left + insets.right;
            int height = labelSize.height + insets.top + insets.bottom;
            return new Dimension(width, height);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void layoutContainer(Container parent) {
            Object object = parent.getTreeLock();
            synchronized (object) {
                Dimension size = parent.getSize();
                Insets insets = parent.getInsets();
                int width = size.width - insets.left - insets.right;
                JLabel label = TitledSeparatorLayout.getLabel(parent);
                Dimension labelSize = label.getPreferredSize();
                int labelWidth = labelSize.width;
                int labelHeight = labelSize.height;
                Component separator1 = parent.getComponent(1);
                int separatorHeight = separator1.getPreferredSize().height;
                FontMetrics metrics = label.getFontMetrics(label.getFont());
                int ascent = metrics.getMaxAscent();
                int hGapDlu = this.centerSeparators ? 3 : 1;
                int hGap = Sizes.dialogUnitXAsPixel(hGapDlu, label);
                int vOffset = this.centerSeparators ? 1 + (labelHeight - separatorHeight) / 2 : ascent - separatorHeight / 2;
                int alignment = label.getHorizontalAlignment();
                int y = insets.top;
                if (alignment == 2) {
                    int x = insets.left;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x += labelWidth;
                    int separatorWidth = size.width - insets.right - (x += hGap);
                    separator1.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
                } else if (alignment == 4) {
                    int x = insets.left + width - labelWidth;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x -= hGap;
                    int separatorWidth = --x - insets.left;
                    separator1.setBounds(insets.left, y + vOffset, separatorWidth, separatorHeight);
                } else {
                    int xOffset = (width - labelWidth - 2 * hGap) / 2;
                    int x = insets.left;
                    separator1.setBounds(x, y + vOffset, xOffset - 1, separatorHeight);
                    x += xOffset;
                    label.setBounds(x += hGap, y, labelWidth, labelHeight);
                    x += labelWidth;
                    Component separator2 = parent.getComponent(2);
                    int separatorWidth = size.width - insets.right - (x += hGap);
                    separator2.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
                }
            }
        }

        private static JLabel getLabel(Container parent) {
            return (JLabel)parent.getComponent(0);
        }
    }

    private static final class TitleLabel
    extends FormsLabel {
        private TitleLabel() {
        }

        @Override
        public void updateUI() {
            super.updateUI();
            Color foreground = TitleLabel.getTitleColor();
            if (foreground != null) {
                this.setForeground(foreground);
            }
            this.setFont(TitleLabel.getTitleFont());
        }

        private static Color getTitleColor() {
            return UIManager.getColor("TitledBorder.titleColor");
        }

        private static Font getTitleFont() {
            return FormUtils.isLafAqua() ? UIManager.getFont("Label.font").deriveFont(1) : UIManager.getFont("TitledBorder.font");
        }
    }

    private static final class ReadOnlyLabel
    extends FormsLabel {
        private static final String[] UIMANAGER_KEYS = new String[]{"Label.disabledForeground", "Label.disabledText", "Label[Disabled].textForeground", "textInactiveText"};

        private ReadOnlyLabel() {
        }

        @Override
        public void updateUI() {
            super.updateUI();
            this.setForeground(ReadOnlyLabel.getDisabledForeground());
        }

        private static Color getDisabledForeground() {
            for (String key : UIMANAGER_KEYS) {
                Color foreground = UIManager.getColor(key);
                if (foreground == null) continue;
                return foreground;
            }
            return null;
        }
    }

    private static class FormsLabel
    extends JLabel {
        private FormsLabel() {
        }

        @Override
        public AccessibleContext getAccessibleContext() {
            if (this.accessibleContext == null) {
                this.accessibleContext = new AccessibleFormsLabel();
            }
            return this.accessibleContext;
        }

        private final class AccessibleFormsLabel
        extends JLabel.AccessibleJLabel {
            private AccessibleFormsLabel() {
                super(FormsLabel.this);
            }

            @Override
            public String getAccessibleName() {
                if (this.accessibleName != null) {
                    return this.accessibleName;
                }
                String text = FormsLabel.this.getText();
                if (text == null) {
                    return super.getAccessibleName();
                }
                return text.endsWith(":") ? text.substring(0, text.length() - 1) : text;
            }
        }
    }
}

