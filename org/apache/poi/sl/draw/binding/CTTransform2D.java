/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTPoint2D;
import org.apache.poi.sl.draw.binding.CTPositiveSize2D;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_Transform2D", propOrder={"off", "ext"})
public class CTTransform2D {
    protected CTPoint2D off;
    protected CTPositiveSize2D ext;
    @XmlAttribute(name="rot")
    protected Integer rot;
    @XmlAttribute(name="flipH")
    protected Boolean flipH;
    @XmlAttribute(name="flipV")
    protected Boolean flipV;

    public CTPoint2D getOff() {
        return this.off;
    }

    public void setOff(CTPoint2D value) {
        this.off = value;
    }

    public boolean isSetOff() {
        return this.off != null;
    }

    public CTPositiveSize2D getExt() {
        return this.ext;
    }

    public void setExt(CTPositiveSize2D value) {
        this.ext = value;
    }

    public boolean isSetExt() {
        return this.ext != null;
    }

    public int getRot() {
        if (this.rot == null) {
            return 0;
        }
        return this.rot;
    }

    public void setRot(int value) {
        this.rot = value;
    }

    public boolean isSetRot() {
        return this.rot != null;
    }

    public void unsetRot() {
        this.rot = null;
    }

    public boolean isFlipH() {
        if (this.flipH == null) {
            return false;
        }
        return this.flipH;
    }

    public void setFlipH(boolean value) {
        this.flipH = value;
    }

    public boolean isSetFlipH() {
        return this.flipH != null;
    }

    public void unsetFlipH() {
        this.flipH = null;
    }

    public boolean isFlipV() {
        if (this.flipV == null) {
            return false;
        }
        return this.flipV;
    }

    public void setFlipV(boolean value) {
        this.flipV = value;
    }

    public boolean isSetFlipV() {
        return this.flipV != null;
    }

    public void unsetFlipV() {
        this.flipV = null;
    }
}

