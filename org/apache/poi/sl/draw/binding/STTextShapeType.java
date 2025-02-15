/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.binding;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="ST_TextShapeType")
@XmlEnum
public enum STTextShapeType {
    TEXT_NO_SHAPE("textNoShape"),
    TEXT_PLAIN("textPlain"),
    TEXT_STOP("textStop"),
    TEXT_TRIANGLE("textTriangle"),
    TEXT_TRIANGLE_INVERTED("textTriangleInverted"),
    TEXT_CHEVRON("textChevron"),
    TEXT_CHEVRON_INVERTED("textChevronInverted"),
    TEXT_RING_INSIDE("textRingInside"),
    TEXT_RING_OUTSIDE("textRingOutside"),
    TEXT_ARCH_UP("textArchUp"),
    TEXT_ARCH_DOWN("textArchDown"),
    TEXT_CIRCLE("textCircle"),
    TEXT_BUTTON("textButton"),
    TEXT_ARCH_UP_POUR("textArchUpPour"),
    TEXT_ARCH_DOWN_POUR("textArchDownPour"),
    TEXT_CIRCLE_POUR("textCirclePour"),
    TEXT_BUTTON_POUR("textButtonPour"),
    TEXT_CURVE_UP("textCurveUp"),
    TEXT_CURVE_DOWN("textCurveDown"),
    TEXT_CAN_UP("textCanUp"),
    TEXT_CAN_DOWN("textCanDown"),
    TEXT_WAVE_1("textWave1"),
    TEXT_WAVE_2("textWave2"),
    TEXT_DOUBLE_WAVE_1("textDoubleWave1"),
    TEXT_WAVE_4("textWave4"),
    TEXT_INFLATE("textInflate"),
    TEXT_DEFLATE("textDeflate"),
    TEXT_INFLATE_BOTTOM("textInflateBottom"),
    TEXT_DEFLATE_BOTTOM("textDeflateBottom"),
    TEXT_INFLATE_TOP("textInflateTop"),
    TEXT_DEFLATE_TOP("textDeflateTop"),
    TEXT_DEFLATE_INFLATE("textDeflateInflate"),
    TEXT_DEFLATE_INFLATE_DEFLATE("textDeflateInflateDeflate"),
    TEXT_FADE_RIGHT("textFadeRight"),
    TEXT_FADE_LEFT("textFadeLeft"),
    TEXT_FADE_UP("textFadeUp"),
    TEXT_FADE_DOWN("textFadeDown"),
    TEXT_SLANT_UP("textSlantUp"),
    TEXT_SLANT_DOWN("textSlantDown"),
    TEXT_CASCADE_UP("textCascadeUp"),
    TEXT_CASCADE_DOWN("textCascadeDown");

    private final String value;

    private STTextShapeType(String v) {
        this.value = v;
    }

    public String value() {
        return this.value;
    }

    public static STTextShapeType fromValue(String v) {
        for (STTextShapeType c : STTextShapeType.values()) {
            if (!c.value.equals(v)) continue;
            return c;
        }
        throw new IllegalArgumentException(v);
    }
}

