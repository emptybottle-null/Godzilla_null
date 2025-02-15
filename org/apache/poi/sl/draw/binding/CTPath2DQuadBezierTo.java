/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTAdjPoint2D;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_Path2DQuadBezierTo", propOrder={"pt"})
public class CTPath2DQuadBezierTo {
    @XmlElement(required=true)
    protected List<CTAdjPoint2D> pt;

    public List<CTAdjPoint2D> getPt() {
        if (this.pt == null) {
            this.pt = new ArrayList<CTAdjPoint2D>();
        }
        return this.pt;
    }

    public boolean isSetPt() {
        return this.pt != null && !this.pt.isEmpty();
    }

    public void unsetPt() {
        this.pt = null;
    }
}

