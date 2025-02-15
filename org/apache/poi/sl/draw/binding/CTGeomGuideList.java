/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTGeomGuide;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_GeomGuideList", propOrder={"gd"})
public class CTGeomGuideList {
    protected List<CTGeomGuide> gd;

    public List<CTGeomGuide> getGd() {
        if (this.gd == null) {
            this.gd = new ArrayList<CTGeomGuide>();
        }
        return this.gd;
    }

    public boolean isSetGd() {
        return this.gd != null && !this.gd.isEmpty();
    }

    public void unsetGd() {
        this.gd = null;
    }
}

