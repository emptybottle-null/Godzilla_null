/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_Ratio")
public class CTRatio {
    @XmlAttribute(name="n", required=true)
    protected long n;
    @XmlAttribute(name="d", required=true)
    protected long d;

    public long getN() {
        return this.n;
    }

    public void setN(long value) {
        this.n = value;
    }

    public boolean isSetN() {
        return true;
    }

    public long getD() {
        return this.d;
    }

    public void setD(long value) {
        this.d = value;
    }

    public boolean isSetD() {
        return true;
    }
}

