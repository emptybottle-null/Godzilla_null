/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_GeomGuide")
public class CTGeomGuide {
    @XmlAttribute(name="name", required=true)
    @XmlJavaTypeAdapter(value=CollapsedStringAdapter.class)
    protected String name;
    @XmlAttribute(name="fmla", required=true)
    protected String fmla;

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return this.name != null;
    }

    public String getFmla() {
        return this.fmla;
    }

    public void setFmla(String value) {
        this.fmla = value;
    }

    public boolean isSetFmla() {
        return this.fmla != null;
    }
}

