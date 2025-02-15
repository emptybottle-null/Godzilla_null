/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="CT_PositiveFixedAngle")
public class CTPositiveFixedAngle {
    @XmlAttribute(name="val", required=true)
    protected int val;

    public int getVal() {
        return this.val;
    }

    public void setVal(int value) {
        this.val = value;
    }

    public boolean isSetVal() {
        return true;
    }
}

