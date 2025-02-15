/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTAdjustHandleList;
import org.apache.poi.sl.draw.binding.CTConnectionSiteList;
import org.apache.poi.sl.draw.binding.CTGeomGuideList;
import org.apache.poi.sl.draw.binding.CTGeomRect;
import org.apache.poi.sl.draw.binding.CTPath2DList;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_CustomGeometry2D", propOrder={"avLst", "gdLst", "ahLst", "cxnLst", "rect", "pathLst"})
public class CTCustomGeometry2D {
    protected CTGeomGuideList avLst;
    protected CTGeomGuideList gdLst;
    protected CTAdjustHandleList ahLst;
    protected CTConnectionSiteList cxnLst;
    protected CTGeomRect rect;
    @XmlElement(required=true)
    protected CTPath2DList pathLst;

    public CTGeomGuideList getAvLst() {
        return this.avLst;
    }

    public void setAvLst(CTGeomGuideList value) {
        this.avLst = value;
    }

    public boolean isSetAvLst() {
        return this.avLst != null;
    }

    public CTGeomGuideList getGdLst() {
        return this.gdLst;
    }

    public void setGdLst(CTGeomGuideList value) {
        this.gdLst = value;
    }

    public boolean isSetGdLst() {
        return this.gdLst != null;
    }

    public CTAdjustHandleList getAhLst() {
        return this.ahLst;
    }

    public void setAhLst(CTAdjustHandleList value) {
        this.ahLst = value;
    }

    public boolean isSetAhLst() {
        return this.ahLst != null;
    }

    public CTConnectionSiteList getCxnLst() {
        return this.cxnLst;
    }

    public void setCxnLst(CTConnectionSiteList value) {
        this.cxnLst = value;
    }

    public boolean isSetCxnLst() {
        return this.cxnLst != null;
    }

    public CTGeomRect getRect() {
        return this.rect;
    }

    public void setRect(CTGeomRect value) {
        this.rect = value;
    }

    public boolean isSetRect() {
        return this.rect != null;
    }

    public CTPath2DList getPathLst() {
        return this.pathLst;
    }

    public void setPathLst(CTPath2DList value) {
        this.pathLst = value;
    }

    public boolean isSetPathLst() {
        return this.pathLst != null;
    }
}

