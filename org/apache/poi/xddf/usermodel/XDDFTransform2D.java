/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.Angles;
import org.apache.poi.xddf.usermodel.XDDFPoint2D;
import org.apache.poi.xddf.usermodel.XDDFPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

public class XDDFTransform2D {
    private CTTransform2D transform;

    protected XDDFTransform2D(CTTransform2D transform) {
        this.transform = transform;
    }

    @Internal
    protected CTTransform2D getXmlObject() {
        return this.transform;
    }

    public Boolean getFlipHorizontal() {
        if (this.transform.isSetFlipH()) {
            return this.transform.getFlipH();
        }
        return null;
    }

    public void setFlipHorizontal(Boolean flip) {
        if (flip == null) {
            if (this.transform.isSetFlipH()) {
                this.transform.unsetFlipH();
            }
        } else {
            this.transform.setFlipH(flip);
        }
    }

    public Boolean getFlipVertical() {
        if (this.transform.isSetFlipV()) {
            return this.transform.getFlipV();
        }
        return null;
    }

    public void setFlipVertical(Boolean flip) {
        if (flip == null) {
            if (this.transform.isSetFlipV()) {
                this.transform.unsetFlipV();
            }
        } else {
            this.transform.setFlipV(flip);
        }
    }

    public XDDFPositiveSize2D getExtension() {
        if (this.transform.isSetExt()) {
            return new XDDFPositiveSize2D(this.transform.getExt());
        }
        return null;
    }

    public void setExtension(XDDFPositiveSize2D extension) {
        if (extension == null) {
            if (this.transform.isSetExt()) {
                this.transform.unsetExt();
            }
            return;
        }
        CTPositiveSize2D xformExt = this.transform.isSetExt() ? this.transform.getExt() : this.transform.addNewExt();
        xformExt.setCx(extension.getX());
        xformExt.setCy(extension.getY());
    }

    public XDDFPoint2D getOffset() {
        if (this.transform.isSetOff()) {
            return new XDDFPoint2D(this.transform.getOff());
        }
        return null;
    }

    public void setOffset(XDDFPoint2D offset) {
        if (offset == null) {
            if (this.transform.isSetOff()) {
                this.transform.unsetOff();
            }
            return;
        }
        CTPoint2D xformOff = this.transform.isSetOff() ? this.transform.getOff() : this.transform.addNewOff();
        xformOff.setX(offset.getX());
        xformOff.setY(offset.getY());
    }

    public Double getRotation() {
        if (this.transform.isSetRot()) {
            return Angles.attributeToDegrees(this.transform.getRot());
        }
        return null;
    }

    public void setRotation(Double rotation) {
        if (rotation == null) {
            if (this.transform.isSetRot()) {
                this.transform.unsetRot();
            }
        } else {
            this.transform.setRot(Angles.degreesToAttribute(rotation));
        }
    }
}

