/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTOfficeArtExtension;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_OfficeArtExtensionList", propOrder={"ext"})
public class CTOfficeArtExtensionList {
    protected List<CTOfficeArtExtension> ext;

    public List<CTOfficeArtExtension> getExt() {
        if (this.ext == null) {
            this.ext = new ArrayList<CTOfficeArtExtension>();
        }
        return this.ext;
    }

    public boolean isSetExt() {
        return this.ext != null && !this.ext.isEmpty();
    }

    public void unsetExt() {
        this.ext = null;
    }
}

