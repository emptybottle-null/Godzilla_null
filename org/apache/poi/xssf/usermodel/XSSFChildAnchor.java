/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFAnchor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

public final class XSSFChildAnchor
extends XSSFAnchor {
    private CTTransform2D t2d;

    public XSSFChildAnchor(int x, int y, int cx, int cy) {
        this.t2d = CTTransform2D.Factory.newInstance();
        CTPoint2D off = this.t2d.addNewOff();
        CTPositiveSize2D ext = this.t2d.addNewExt();
        off.setX(x);
        off.setY(y);
        ext.setCx(Math.abs(cx - x));
        ext.setCy(Math.abs(cy - y));
        if (x > cx) {
            this.t2d.setFlipH(true);
        }
        if (y > cy) {
            this.t2d.setFlipV(true);
        }
    }

    public XSSFChildAnchor(CTTransform2D t2d) {
        this.t2d = t2d;
    }

    @Internal
    public CTTransform2D getCTTransform2D() {
        return this.t2d;
    }

    @Override
    public int getDx1() {
        return (int)this.t2d.getOff().getX();
    }

    @Override
    public void setDx1(int dx1) {
        this.t2d.getOff().setX(dx1);
    }

    @Override
    public int getDy1() {
        return (int)this.t2d.getOff().getY();
    }

    @Override
    public void setDy1(int dy1) {
        this.t2d.getOff().setY(dy1);
    }

    @Override
    public int getDy2() {
        return (int)((long)this.getDy1() + this.t2d.getExt().getCy());
    }

    @Override
    public void setDy2(int dy2) {
        this.t2d.getExt().setCy(dy2 - this.getDy1());
    }

    @Override
    public int getDx2() {
        return (int)((long)this.getDx1() + this.t2d.getExt().getCx());
    }

    @Override
    public void setDx2(int dx2) {
        this.t2d.getExt().setCx(dx2 - this.getDx1());
    }
}

