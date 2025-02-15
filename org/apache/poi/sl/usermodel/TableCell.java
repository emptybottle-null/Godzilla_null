/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Color;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;

public interface TableCell<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends TextShape<S, P> {
    public StrokeStyle getBorderStyle(BorderEdge var1);

    public void setBorderStyle(BorderEdge var1, StrokeStyle var2);

    public void setBorderWidth(BorderEdge var1, double var2);

    public void setBorderColor(BorderEdge var1, Color var2);

    public void setBorderCompound(BorderEdge var1, StrokeStyle.LineCompound var2);

    public void setBorderDash(BorderEdge var1, StrokeStyle.LineDash var2);

    public void removeBorder(BorderEdge var1);

    public int getGridSpan();

    public int getRowSpan();

    public boolean isMerged();

    public static enum BorderEdge {
        bottom,
        left,
        top,
        right;

    }
}

