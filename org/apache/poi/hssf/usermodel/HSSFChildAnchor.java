/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.usermodel.HSSFAnchor;

public final class HSSFChildAnchor
extends HSSFAnchor {
    private EscherChildAnchorRecord _escherChildAnchor;

    public HSSFChildAnchor(EscherChildAnchorRecord escherChildAnchorRecord) {
        this._escherChildAnchor = escherChildAnchorRecord;
    }

    public HSSFChildAnchor() {
        this._escherChildAnchor = new EscherChildAnchorRecord();
    }

    public HSSFChildAnchor(int dx1, int dy1, int dx2, int dy2) {
        super(Math.min(dx1, dx2), Math.min(dy1, dy2), Math.max(dx1, dx2), Math.max(dy1, dy2));
        if (dx1 > dx2) {
            this._isHorizontallyFlipped = true;
        }
        if (dy1 > dy2) {
            this._isVerticallyFlipped = true;
        }
    }

    @Override
    public int getDx1() {
        return this._escherChildAnchor.getDx1();
    }

    @Override
    public void setDx1(int dx1) {
        this._escherChildAnchor.setDx1(dx1);
    }

    @Override
    public int getDy1() {
        return this._escherChildAnchor.getDy1();
    }

    @Override
    public void setDy1(int dy1) {
        this._escherChildAnchor.setDy1(dy1);
    }

    @Override
    public int getDy2() {
        return this._escherChildAnchor.getDy2();
    }

    @Override
    public void setDy2(int dy2) {
        this._escherChildAnchor.setDy2(dy2);
    }

    @Override
    public int getDx2() {
        return this._escherChildAnchor.getDx2();
    }

    @Override
    public void setDx2(int dx2) {
        this._escherChildAnchor.setDx2(dx2);
    }

    public void setAnchor(int dx1, int dy1, int dx2, int dy2) {
        this.setDx1(Math.min(dx1, dx2));
        this.setDy1(Math.min(dy1, dy2));
        this.setDx2(Math.max(dx1, dx2));
        this.setDy2(Math.max(dy1, dy2));
    }

    @Override
    public boolean isHorizontallyFlipped() {
        return this._isHorizontallyFlipped;
    }

    @Override
    public boolean isVerticallyFlipped() {
        return this._isVerticallyFlipped;
    }

    @Override
    protected EscherRecord getEscherAnchor() {
        return this._escherChildAnchor;
    }

    @Override
    protected void createEscherAnchor() {
        this._escherChildAnchor = new EscherChildAnchorRecord();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        HSSFChildAnchor anchor = (HSSFChildAnchor)obj;
        return anchor.getDx1() == this.getDx1() && anchor.getDx2() == this.getDx2() && anchor.getDy1() == this.getDy1() && anchor.getDy2() == this.getDy2();
    }

    public int hashCode() {
        assert (false) : "hashCode not designed";
        return 42;
    }
}

