/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface GroupShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Shape<S, P>,
ShapeContainer<S, P>,
PlaceableShape<S, P> {
    public Rectangle2D getInteriorAnchor();

    public void setInteriorAnchor(Rectangle2D var1);
}

