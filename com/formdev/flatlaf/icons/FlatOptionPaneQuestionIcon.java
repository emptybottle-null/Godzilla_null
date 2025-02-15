/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatOptionPaneAbstractIcon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatOptionPaneQuestionIcon
extends FlatOptionPaneAbstractIcon {
    public FlatOptionPaneQuestionIcon() {
        super("OptionPane.icon.questionColor", "Actions.Blue");
    }

    @Override
    protected Shape createOutside() {
        return new Ellipse2D.Float(2.0f, 2.0f, 28.0f, 28.0f);
    }

    @Override
    protected Shape createInside() {
        Path2D.Float q = new Path2D.Float();
        ((Path2D)q).moveTo(14.0, 20.0);
        ((Path2D)q).lineTo(18.0, 20.0);
        ((Path2D)q).curveTo(18.0, 16.0, 23.0, 16.0, 23.0, 12.0);
        ((Path2D)q).curveTo(23.0, 8.0, 20.0, 6.0, 16.0, 6.0);
        ((Path2D)q).curveTo(12.0, 6.0, 9.0, 8.0, 9.0, 12.0);
        ((Path2D)q).curveTo(9.0, 12.0, 13.0, 12.0, 13.0, 12.0);
        ((Path2D)q).curveTo(13.0, 10.0, 14.0, 9.0, 16.0, 9.0);
        ((Path2D)q).curveTo(18.0, 9.0, 19.0, 10.0, 19.0, 12.0);
        ((Path2D)q).curveTo(19.0, 15.0, 14.0, 15.0, 14.0, 20.0);
        q.closePath();
        Path2D.Float inside = new Path2D.Float(0);
        inside.append(new Rectangle2D.Float(14.0f, 22.0f, 4.0f, 4.0f), false);
        inside.append(q, false);
        return inside;
    }
}

