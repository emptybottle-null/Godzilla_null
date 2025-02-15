/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_RelativeRect")
public class CTRelativeRect {
    @XmlAttribute(name="l")
    protected Integer l;
    @XmlAttribute(name="t")
    protected Integer t;
    @XmlAttribute(name="r")
    protected Integer r;
    @XmlAttribute(name="b")
    protected Integer b;

    public int getL() {
        if (this.l == null) {
            return 0;
        }
        return this.l;
    }

    public void setL(int value) {
        this.l = value;
    }

    public boolean isSetL() {
        return this.l != null;
    }

    public void unsetL() {
        this.l = null;
    }

    public int getT() {
        if (this.t == null) {
            return 0;
        }
        return this.t;
    }

    public void setT(int value) {
        this.t = value;
    }

    public boolean isSetT() {
        return this.t != null;
    }

    public void unsetT() {
        this.t = null;
    }

    public int getR() {
        if (this.r == null) {
            return 0;
        }
        return this.r;
    }

    public void setR(int value) {
        this.r = value;
    }

    public boolean isSetR() {
        return this.r != null;
    }

    public void unsetR() {
        this.r = null;
    }

    public int getB() {
        if (this.b == null) {
            return 0;
        }
        return this.b;
    }

    public void setB(int value) {
        this.b = value;
    }

    public boolean isSetB() {
        return this.b != null;
    }

    public void unsetB() {
        this.b = null;
    }
}

