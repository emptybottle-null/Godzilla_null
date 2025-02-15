/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface PlaceableShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> {
    public ShapeContainer<S, P> getParent();

    public Sheet<S, P> getSheet();

    public Rectangle2D getAnchor();

    public void setAnchor(Rectangle2D var1);

    public double getRotation();

    public void setRotation(double var1);

    public void setFlipHorizontal(boolean var1);

    public void setFlipVertical(boolean var1);

    public boolean getFlipHorizontal();

    public boolean getFlipVertical();
}

