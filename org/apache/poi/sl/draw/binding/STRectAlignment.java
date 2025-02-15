/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="ST_RectAlignment")
@XmlEnum
public enum STRectAlignment {
    TL("tl"),
    T("t"),
    TR("tr"),
    L("l"),
    CTR("ctr"),
    R("r"),
    BL("bl"),
    B("b"),
    BR("br");

    private final String value;

    private STRectAlignment(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static STRectAlignment fromValue(String v) {
        for (STRectAlignment c : STRectAlignment.values()) {
            if (!c.value.equals(v)) continue;
            return c;
        }
        throw new IllegalArgumentException(v);
    }
}

