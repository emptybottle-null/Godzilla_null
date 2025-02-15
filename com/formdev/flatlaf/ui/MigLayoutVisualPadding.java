/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.util.function.Function;
import javax.swing.JComponent;

public class MigLayoutVisualPadding {
    public static String VISUAL_PADDING_PROPERTY = "visualPadding";
    private static final FlatMigInsets ZERO = new FlatMigInsets(0, 0, 0, 0);
    private static final boolean migLayoutAvailable;

    public static void install(JComponent c, Insets insets) {
        if (!migLayoutAvailable) {
            return;
        }
        MigLayoutVisualPadding.setVisualPadding(c, insets);
    }

    public static void install(JComponent c) {
        if (!migLayoutAvailable) {
            return;
        }
        MigLayoutVisualPadding.install(c, c2 -> {
            FlatBorder border = FlatUIUtils.getOutsideFlatBorder(c2);
            if (border != null) {
                int focusWidth = border.getFocusWidth((Component)c2);
                return new Insets(focusWidth, focusWidth, focusWidth, focusWidth);
            }
            return null;
        }, "border");
    }

    public static void install(JComponent c, Function<JComponent, Insets> getPaddingFunction, String ... propertyNames) {
        if (!migLayoutAvailable) {
            return;
        }
        MigLayoutVisualPadding.setVisualPadding(c, getPaddingFunction.apply(c));
        c.addPropertyChangeListener(e -> {
            String propertyName = e.getPropertyName();
            for (String name : propertyNames) {
                if (name != propertyName) continue;
                MigLayoutVisualPadding.setVisualPadding(c, (Insets)getPaddingFunction.apply(c));
                break;
            }
        });
    }

    private static void setVisualPadding(JComponent c, Insets visualPadding) {
        Object oldPadding = c.getClientProperty(VISUAL_PADDING_PROPERTY);
        if (oldPadding == null || oldPadding instanceof FlatMigInsets) {
            FlatMigInsets flatVisualPadding = visualPadding != null ? new FlatMigInsets(UIScale.scale2(visualPadding.top), UIScale.scale2(visualPadding.left), UIScale.scale2(visualPadding.bottom), UIScale.scale2(visualPadding.right)) : ZERO;
            c.putClientProperty(VISUAL_PADDING_PROPERTY, flatVisualPadding);
        }
    }

    public static void uninstall(JComponent c) {
        if (!migLayoutAvailable) {
            return;
        }
        for (PropertyChangeListener l : c.getPropertyChangeListeners()) {
            if (!(l instanceof FlatMigListener)) continue;
            c.removePropertyChangeListener(l);
            break;
        }
        if (c.getClientProperty(VISUAL_PADDING_PROPERTY) instanceof FlatMigInsets) {
            c.putClientProperty(VISUAL_PADDING_PROPERTY, null);
        }
    }

    static {
        boolean available = false;
        try {
            Class.forName("net.miginfocom.swing.MigLayout");
            available = true;
        } catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        migLayoutAvailable = available;
    }

    private static interface FlatMigListener
    extends PropertyChangeListener {
    }

    private static class FlatMigInsets
    extends Insets {
        FlatMigInsets(int top, int left, int bottom, int right) {
            super(top, left, bottom, right);
        }
    }
}

