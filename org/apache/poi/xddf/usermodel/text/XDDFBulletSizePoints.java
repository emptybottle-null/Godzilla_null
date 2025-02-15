/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFBulletSize;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;

public class XDDFBulletSizePoints
implements XDDFBulletSize {
    private CTTextBulletSizePoint points;

    public XDDFBulletSizePoints(double value) {
        this(CTTextBulletSizePoint.Factory.newInstance());
        this.setPoints(value);
    }

    @Internal
    protected XDDFBulletSizePoints(CTTextBulletSizePoint points) {
        this.points = points;
    }

    @Internal
    protected CTTextBulletSizePoint getXmlObject() {
        return this.points;
    }

    public double getPoints() {
        return (double)this.points.getVal() * 0.01;
    }

    public void setPoints(double value) {
        this.points.setVal(Math.toIntExact(Math.round(100.0 * value)));
    }
}

