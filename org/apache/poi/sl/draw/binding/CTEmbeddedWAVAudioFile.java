/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_EmbeddedWAVAudioFile")
public class CTEmbeddedWAVAudioFile {
    @XmlAttribute(name="embed", namespace="http://schemas.openxmlformats.org/officeDocument/2006/relationships", required=true)
    protected String embed;
    @XmlAttribute(name="name")
    protected String name;
    @XmlAttribute(name="builtIn")
    protected Boolean builtIn;

    public String getEmbed() {
        return this.embed;
    }

    public void setEmbed(String value) {
        this.embed = value;
    }

    public boolean isSetEmbed() {
        return this.embed != null;
    }

    public String getName() {
        if (this.name == null) {
            return "";
        }
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return this.name != null;
    }

    public boolean isBuiltIn() {
        if (this.builtIn == null) {
            return false;
        }
        return this.builtIn;
    }

    public void setBuiltIn(boolean value) {
        this.builtIn = value;
    }

    public boolean isSetBuiltIn() {
        return this.builtIn != null;
    }

    public void unsetBuiltIn() {
        this.builtIn = null;
    }
}

