/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTGeomGuideList;
import org.apache.poi.sl.draw.binding.STTextShapeType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_PresetTextShape", propOrder={"avLst"})
public class CTPresetTextShape {
    protected CTGeomGuideList avLst;
    @XmlAttribute(name="prst", required=true)
    protected STTextShapeType prst;

    public CTGeomGuideList getAvLst() {
        return this.avLst;
    }

    public void setAvLst(CTGeomGuideList value) {
        this.avLst = value;
    }

    public boolean isSetAvLst() {
        return this.avLst != null;
    }

    public STTextShapeType getPrst() {
        return this.prst;
    }

    public void setPrst(STTextShapeType value) {
        this.prst = value;
    }

    public boolean isSetPrst() {
        return this.prst != null;
    }
}

