/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;

public class XDDFRelativeRectangle {
    private CTRelativeRect rect;

    public XDDFRelativeRectangle() {
        this(CTRelativeRect.Factory.newInstance());
    }

    protected XDDFRelativeRectangle(CTRelativeRect rectangle) {
        this.rect = rectangle;
    }

    @Internal
    protected CTRelativeRect getXmlObject() {
        return this.rect;
    }

    public Integer getBottom() {
        if (this.rect.isSetB()) {
            return this.rect.getB();
        }
        return null;
    }

    public void setBottom(Integer bottom) {
        if (bottom == null) {
            if (this.rect.isSetB()) {
                this.rect.unsetB();
            }
        } else {
            this.rect.setB(bottom);
        }
    }

    public Integer getLeft() {
        if (this.rect.isSetL()) {
            return this.rect.getL();
        }
        return null;
    }

    public void setLeft(Integer left) {
        if (left == null) {
            if (this.rect.isSetL()) {
                this.rect.unsetL();
            }
        } else {
            this.rect.setL(left);
        }
    }

    public Integer getRight() {
        if (this.rect.isSetR()) {
            return this.rect.getR();
        }
        return null;
    }

    public void setRight(Integer right) {
        if (right == null) {
            if (this.rect.isSetR()) {
                this.rect.unsetR();
            }
        } else {
            this.rect.setR(right);
        }
    }

    public Integer getTop() {
        if (this.rect.isSetT()) {
            return this.rect.getT();
        }
        return null;
    }

    public void setTop(Integer top) {
        if (top == null) {
            if (this.rect.isSetT()) {
                this.rect.unsetT();
            }
        } else {
            this.rect.setT(top);
        }
    }
}

