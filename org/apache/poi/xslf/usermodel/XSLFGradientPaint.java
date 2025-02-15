/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.util.Arrays;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFColor;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

@Internal
public class XSLFGradientPaint
implements PaintStyle.GradientPaint {
    private final CTGradientFillProperties gradFill;
    final ColorStyle[] cs;
    final float[] fractions;

    public XSLFGradientPaint(CTGradientFillProperties gradFill, CTSchemeColor phClr, XSLFTheme theme, XSLFSheet sheet) {
        this.gradFill = gradFill;
        CTGradientStop[] gs = gradFill.getGsLst() == null ? new CTGradientStop[]{} : gradFill.getGsLst().getGsArray();
        Arrays.sort(gs, (o1, o2) -> {
            int pos1 = o1.getPos();
            int pos2 = o2.getPos();
            return Integer.compare(pos1, pos2);
        });
        this.cs = new ColorStyle[gs.length];
        this.fractions = new float[gs.length];
        int i = 0;
        for (CTGradientStop cgs : gs) {
            CTSchemeColor phClrCgs = phClr;
            if (phClrCgs == null && cgs.isSetSchemeClr()) {
                phClrCgs = cgs.getSchemeClr();
            }
            this.cs[i] = new XSLFColor(cgs, theme, phClrCgs, sheet).getColorStyle();
            this.fractions[i] = (float)cgs.getPos() / 100000.0f;
            ++i;
        }
    }

    @Override
    public double getGradientAngle() {
        return this.gradFill.isSetLin() ? (double)this.gradFill.getLin().getAng() / 60000.0 : 0.0;
    }

    @Override
    public ColorStyle[] getGradientColors() {
        return this.cs;
    }

    @Override
    public float[] getGradientFractions() {
        return this.fractions;
    }

    @Override
    public boolean isRotatedWithShape() {
        return this.gradFill.getRotWithShape();
    }

    @Override
    public PaintStyle.GradientPaint.GradientType getGradientType() {
        if (this.gradFill.isSetLin()) {
            return PaintStyle.GradientPaint.GradientType.linear;
        }
        if (this.gradFill.isSetPath()) {
            STPathShadeType.Enum ps = this.gradFill.getPath().getPath();
            if (ps == STPathShadeType.CIRCLE) {
                return PaintStyle.GradientPaint.GradientType.circular;
            }
            if (ps == STPathShadeType.SHAPE) {
                return PaintStyle.GradientPaint.GradientType.shape;
            }
            if (ps == STPathShadeType.RECT) {
                return PaintStyle.GradientPaint.GradientType.rectangular;
            }
        }
        return PaintStyle.GradientPaint.GradientType.linear;
    }

    @Override
    public Insets2D getFillToInsets() {
        if (this.gradFill.isSetPath() && this.gradFill.getPath().isSetFillToRect()) {
            double base = 100000.0;
            CTRelativeRect rect = this.gradFill.getPath().getFillToRect();
            return new Insets2D((double)rect.getT() / 100000.0, (double)rect.getL() / 100000.0, (double)rect.getB() / 100000.0, (double)rect.getR() / 100000.0);
        }
        return null;
    }
}

