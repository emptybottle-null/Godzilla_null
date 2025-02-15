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
import org.apache.poi.sl.draw.binding.CTHslColor;
import org.apache.poi.sl.draw.binding.CTPresetColor;
import org.apache.poi.sl.draw.binding.CTSRgbColor;
import org.apache.poi.sl.draw.binding.CTScRgbColor;
import org.apache.poi.sl.draw.binding.CTSchemeColor;
import org.apache.poi.sl.draw.binding.CTSystemColor;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_ColorMRU", propOrder={"egColorChoice"})
public class CTColorMRU {
    @XmlElements(value={@XmlElement(name="scrgbClr", type=CTScRgbColor.class), @XmlElement(name="srgbClr", type=CTSRgbColor.class), @XmlElement(name="hslClr", type=CTHslColor.class), @XmlElement(name="sysClr", type=CTSystemColor.class), @XmlElement(name="schemeClr", type=CTSchemeColor.class), @XmlElement(name="prstClr", type=CTPresetColor.class)})
    protected List<Object> egColorChoice;

    public List<Object> getEGColorChoice() {
        if (this.egColorChoice == null) {
            this.egColorChoice = new ArrayList<Object>();
        }
        return this.egColorChoice;
    }

    public boolean isSetEGColorChoice() {
        return this.egColorChoice != null && !this.egColorChoice.isEmpty();
    }

    public void unsetEGColorChoice() {
        this.egColorChoice = null;
    }
}

