/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface Shape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>> {
    public ShapeContainer<S, P> getParent();

    public Sheet<S, P> getSheet();

    public Rectangle2D getAnchor();

    public String getShapeName();

    public void draw(Graphics2D var1, Rectangle2D var2);

    public int getShapeId();
}

