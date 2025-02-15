/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatOptionPaneErrorIcon
extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneErrorIcon() {
        super("OptionPane.icon.errorColor", "Actions.Red");
    }

    @Override
    protected Shape createOutside() {
        return new Ellipse2D.Float(2.0f, 2.0f, 28.0f, 28.0f);
    }

    @Override
    protected Shape createInside() {
        Path2D.Float inside = new Path2D.Float(0);
        inside.append(new Rectangle2D.Float(14.0f, 7.0f, 4.0f, 11.0f), false);
        inside.append(new Rectangle2D.Float(14.0f, 21.0f, 4.0f, 4.0f), false);
        return inside;
    }
}

