/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatOptionPaneWarningIcon
extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneWarningIcon() {
        super("OptionPane.icon.warningColor", "Actions.Yellow");
    }

    @Override
    protected Shape createOutside() {
        return FlatUIUtils.createPath(16.0, 2.0, 31.0, 28.0, 1.0, 28.0);
    }

    @Override
    protected Shape createInside() {
        Path2D.Float inside = new Path2D.Float(0);
        inside.append(new Rectangle2D.Float(14.0f, 10.0f, 4.0f, 8.0f), false);
        inside.append(new Rectangle2D.Float(14.0f, 21.0f, 4.0f, 4.0f), false);
        return inside;
    }
}

