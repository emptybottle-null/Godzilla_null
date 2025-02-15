/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.poi.sl.draw.binding.CTHslColor;
import org.apache.poi.sl.draw.binding.CTPresetColor;
import org.apache.poi.sl.draw.binding.CTSRgbColor;
import org.apache.poi.sl.draw.binding.CTScRgbColor;
import org.apache.poi.sl.draw.binding.CTSchemeColor;
import org.apache.poi.sl.draw.binding.CTSystemColor;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_Color", propOrder={"scrgbClr", "srgbClr", "hslClr", "sysClr", "schemeClr", "prstClr"})
public class CTColor {
    protected CTScRgbColor scrgbClr;
    protected CTSRgbColor srgbClr;
    protected CTHslColor hslClr;
    protected CTSystemColor sysClr;
    protected CTSchemeColor schemeClr;
    protected CTPresetColor prstClr;

    public CTScRgbColor getScrgbClr() {
        return this.scrgbClr;
    }

    public void setScrgbClr(CTScRgbColor value) {
        this.scrgbClr = value;
    }

    public boolean isSetScrgbClr() {
        return this.scrgbClr != null;
    }

    public CTSRgbColor getSrgbClr() {
        return this.srgbClr;
    }

    public void setSrgbClr(CTSRgbColor value) {
        this.srgbClr = value;
    }

    public boolean isSetSrgbClr() {
        return this.srgbClr != null;
    }

    public CTHslColor getHslClr() {
        return this.hslClr;
    }

    public void setHslClr(CTHslColor value) {
        this.hslClr = value;
    }

    public boolean isSetHslClr() {
        return this.hslClr != null;
    }

    public CTSystemColor getSysClr() {
        return this.sysClr;
    }

    public void setSysClr(CTSystemColor value) {
        this.sysClr = value;
    }

    public boolean isSetSysClr() {
        return this.sysClr != null;
    }

    public CTSchemeColor getSchemeClr() {
        return this.schemeClr;
    }

    public void setSchemeClr(CTSchemeColor value) {
        this.schemeClr = value;
    }

    public boolean isSetSchemeClr() {
        return this.schemeClr != null;
    }

    public CTPresetColor getPrstClr() {
        return this.prstClr;
    }

    public void setPrstClr(CTPresetColor value) {
        this.prstClr = value;
    }

    public boolean isSetPrstClr() {
        return this.prstClr != null;
    }
}

