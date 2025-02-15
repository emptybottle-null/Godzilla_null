/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="ST_PathFillMode")
@XmlEnum
public enum STPathFillMode {
    NONE("none"),
    NORM("norm"),
    LIGHTEN("lighten"),
    LIGHTEN_LESS("lightenLess"),
    DARKEN("darken"),
    DARKEN_LESS("darkenLess");

    private final String value;

    private STPathFillMode(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static STPathFillMode fromValue(String v) {
        for (STPathFillMode c : STPathFillMode.values()) {
            if (!c.value.equals(v)) continue;
            return c;
        }
        throw new IllegalArgumentException(v);
    }
}

