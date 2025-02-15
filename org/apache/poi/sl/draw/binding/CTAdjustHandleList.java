/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTPolarAdjustHandle;
import org.apache.poi.sl.draw.binding.CTXYAdjustHandle;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_AdjustHandleList", propOrder={"ahXYOrAhPolar"})
public class CTAdjustHandleList {
    @XmlElements(value={@XmlElement(name="ahXY", type=CTXYAdjustHandle.class), @XmlElement(name="ahPolar", type=CTPolarAdjustHandle.class)})
    protected List<Object> ahXYOrAhPolar;

    public List<Object> getAhXYOrAhPolar() {
        if (this.ahXYOrAhPolar == null) {
            this.ahXYOrAhPolar = new ArrayList<Object>();
        }
        return this.ahXYOrAhPolar;
    }

    public boolean isSetAhXYOrAhPolar() {
        return this.ahXYOrAhPolar != null && !this.ahXYOrAhPolar.isEmpty();
    }

    public void unsetAhXYOrAhPolar() {
        this.ahXYOrAhPolar = null;
    }
}

