/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Color;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.IAdjustableShape;
import org.apache.poi.sl.usermodel.FillStyle;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.Shadow;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface SimpleShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Shape<S, P>,
IAdjustableShape,
PlaceableShape<S, P> {
    public FillStyle getFillStyle();

    public LineDecoration getLineDecoration();

    public StrokeStyle getStrokeStyle();

    public void setStrokeStyle(Object ... var1);

    public CustomGeometry getGeometry();

    public ShapeType getShapeType();

    public void setShapeType(ShapeType var1);

    public Placeholder getPlaceholder();

    public void setPlaceholder(Placeholder var1);

    public PlaceholderDetails getPlaceholderDetails();

    public boolean isPlaceholder();

    public Shadow<S, P> getShadow();

    public Color getFillColor();

    public void setFillColor(Color var1);

    public Hyperlink<S, P> getHyperlink();

    public Hyperlink<S, P> createHyperlink();
}

