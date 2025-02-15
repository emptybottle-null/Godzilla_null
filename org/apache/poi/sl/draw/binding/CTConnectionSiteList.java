/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTConnectionSite;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_ConnectionSiteList", propOrder={"cxn"})
public class CTConnectionSiteList {
    protected List<CTConnectionSite> cxn;

    public List<CTConnectionSite> getCxn() {
        if (this.cxn == null) {
            this.cxn = new ArrayList<CTConnectionSite>();
        }
        return this.cxn;
    }

    public boolean isSetCxn() {
        return this.cxn != null && !this.cxn.isEmpty();
    }

    public void unsetCxn() {
        this.cxn = null;
    }
}

