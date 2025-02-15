/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Color;
import java.util.List;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TabStop;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;

public interface TextParagraph<S extends Shape<S, P>, P extends TextParagraph<S, P, T>, T extends TextRun>
extends Iterable<T> {
    public Double getSpaceBefore();

    public void setSpaceBefore(Double var1);

    public Double getSpaceAfter();

    public void setSpaceAfter(Double var1);

    public Double getLeftMargin();

    public void setLeftMargin(Double var1);

    public Double getRightMargin();

    public void setRightMargin(Double var1);

    public Double getIndent();

    public void setIndent(Double var1);

    public int getIndentLevel();

    public void setIndentLevel(int var1);

    public Double getLineSpacing();

    public void setLineSpacing(Double var1);

    public String getDefaultFontFamily();

    public Double getDefaultFontSize();

    public TextAlign getTextAlign();

    public void setTextAlign(TextAlign var1);

    public FontAlign getFontAlign();

    public BulletStyle getBulletStyle();

    public void setBulletStyle(Object ... var1);

    public Double getDefaultTabSize();

    public TextShape<S, P> getParentShape();

    public List<T> getTextRuns();

    public boolean isHeaderOrFooter();

    public List<? extends TabStop> getTabStops();

    public void addTabStops(double var1, TabStop.TabStopType var3);

    public void clearTabStops();

    public static interface BulletStyle {
        public String getBulletCharacter();

        public String getBulletFont();

        public Double getBulletFontSize();

        public void setBulletFontColor(Color var1);

        public void setBulletFontColor(PaintStyle var1);

        public PaintStyle getBulletFontColor();

        public AutoNumberingScheme getAutoNumberingScheme();

        public Integer getAutoNumberingStartAt();
    }

    public static enum FontAlign {
        AUTO,
        TOP,
        CENTER,
        BASELINE,
        BOTTOM;

    }

    public static enum TextAlign {
        LEFT,
        CENTER,
        RIGHT,
        JUSTIFY,
        JUSTIFY_LOW,
        DIST,
        THAI_DIST;

    }
}

