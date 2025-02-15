/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="ST_BlackWhiteMode")
@XmlEnum
public enum STBlackWhiteMode {
    CLR("clr"),
    AUTO("auto"),
    GRAY("gray"),
    LT_GRAY("ltGray"),
    INV_GRAY("invGray"),
    GRAY_WHITE("grayWhite"),
    BLACK_GRAY("blackGray"),
    BLACK_WHITE("blackWhite"),
    BLACK("black"),
    WHITE("white"),
    HIDDEN("hidden");

    private final String value;

    private STBlackWhiteMode(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static STBlackWhiteMode fromValue(String v) {
        for (STBlackWhiteMode c : STBlackWhiteMode.values()) {
            if (!c.value.equals(v)) continue;
            return c;
        }
        throw new IllegalArgumentException(v);
    }
}

