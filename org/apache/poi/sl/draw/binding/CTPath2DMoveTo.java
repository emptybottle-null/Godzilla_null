/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTAdjPoint2D;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_Path2DMoveTo", propOrder={"pt"})
public class CTPath2DMoveTo {
    @XmlElement(required=true)
    protected CTAdjPoint2D pt;

    public CTAdjPoint2D getPt() {
        return this.pt;
    }

    public void setPt(CTAdjPoint2D value) {
        this.pt = value;
    }

    public boolean isSetPt() {
        return this.pt != null;
    }
}

