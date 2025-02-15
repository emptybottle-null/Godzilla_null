/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;

public class XDDFSpacingPoints
extends XDDFSpacing {
    private CTTextSpacingPoint points;

    public XDDFSpacingPoints(double value) {
        this(CTTextSpacing.Factory.newInstance(), CTTextSpacingPoint.Factory.newInstance());
        if (this.spacing.isSetSpcPct()) {
            this.spacing.unsetSpcPct();
        }
        this.spacing.setSpcPts(this.points);
        this.setPoints(value);
    }

    @Internal
    protected XDDFSpacingPoints(CTTextSpacing parent, CTTextSpacingPoint points) {
        super(parent);
        this.points = points;
    }

    @Override
    public XDDFSpacing.Kind getType() {
        return XDDFSpacing.Kind.POINTS;
    }

    public double getPoints() {
        return (double)this.points.getVal() * 0.01;
    }

    public void setPoints(double value) {
        this.points.setVal((int)(100.0 * value));
    }
}

