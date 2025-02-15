/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_GeomRect")
public class CTGeomRect {
    @XmlAttribute(name="l", required=true)
    protected String l;
    @XmlAttribute(name="t", required=true)
    protected String t;
    @XmlAttribute(name="r", required=true)
    protected String r;
    @XmlAttribute(name="b", required=true)
    protected String b;

    public String getL() {
        return this.l;
    }

    public void setL(String value) {
        this.l = value;
    }

    public boolean isSetL() {
        return this.l != null;
    }

    public String getT() {
        return this.t;
    }

    public void setT(String value) {
        this.t = value;
    }

    public boolean isSetT() {
        return this.t != null;
    }

    public String getR() {
        return this.r;
    }

    public void setR(String value) {
        this.r = value;
    }

    public boolean isSetR() {
        return this.r != null;
    }

    public String getB() {
        return this.b;
    }

    public void setB(String value) {
        this.b = value;
    }

    public boolean isSetB() {
        return this.b != null;
    }
}

