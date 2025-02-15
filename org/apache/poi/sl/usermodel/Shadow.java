/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface Shadow<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> {
    public SimpleShape<S, P> getShadowParent();

    public double getDistance();

    public double getAngle();

    public double getBlur();

    public PaintStyle.SolidPaint getFillStyle();
}

