/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.layout;

import com.jgoodies.forms.layout.BoundedSize;
import com.jgoodies.forms.layout.ConstantSize;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.Size;
import com.jgoodies.forms.util.DefaultUnitConverter;
import com.jgoodies.forms.util.UnitConverter;
import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public final class Sizes {
    public static final ConstantSize ZERO = Sizes.pixel(0);
    public static final ConstantSize DLUX1 = Sizes.dluX(1);
    public static final ConstantSize DLUX2 = Sizes.dluX(2);
    public static final ConstantSize DLUX3 = Sizes.dluX(3);
    public static final ConstantSize DLUX4 = Sizes.dluX(4);
    public static final ConstantSize DLUX5 = Sizes.dluX(5);
    public static final ConstantSize DLUX6 = Sizes.dluX(6);
    public static final ConstantSize DLUX7 = Sizes.dluX(7);
    public static final ConstantSize DLUX8 = Sizes.dluX(8);
    public static final ConstantSize DLUX9 = Sizes.dluX(9);
    public static final ConstantSize DLUX11 = Sizes.dluX(11);
    public static final ConstantSize DLUX14 = Sizes.dluX(14);
    public static final ConstantSize DLUX21 = Sizes.dluX(21);
    public static final ConstantSize DLUY1 = Sizes.dluY(1);
    public static final ConstantSize DLUY2 = Sizes.dluY(2);
    public static final ConstantSize DLUY3 = Sizes.dluY(3);
    public static final ConstantSize DLUY4 = Sizes.dluY(4);
    public static final ConstantSize DLUY5 = Sizes.dluY(5);
    public static final ConstantSize DLUY6 = Sizes.dluY(6);
    public static final ConstantSize DLUY7 = Sizes.dluY(7);
    public static final ConstantSize DLUY8 = Sizes.dluY(8);
    public static final ConstantSize DLUY9 = Sizes.dluY(9);
    public static final ConstantSize DLUY11 = Sizes.dluY(11);
    public static final ConstantSize DLUY14 = Sizes.dluY(14);
    public static final ConstantSize DLUY21 = Sizes.dluY(21);
    public static final ComponentSize MINIMUM = new ComponentSize("minimum");
    public static final ComponentSize PREFERRED = new ComponentSize("preferred");
    public static final ComponentSize DEFAULT = new ComponentSize("default");
    private static final ComponentSize[] VALUES = new ComponentSize[]{MINIMUM, PREFERRED, DEFAULT};
    private static UnitConverter unitConverter;
    private static ConstantSize.Unit defaultUnit;

    private Sizes() {
    }

    public static ConstantSize constant(String encodedValueAndUnit, boolean horizontal) {
        String lowerCase = encodedValueAndUnit.toLowerCase(Locale.ENGLISH);
        String trimmed = lowerCase.trim();
        return ConstantSize.valueOf(trimmed, horizontal);
    }

    public static ConstantSize dluX(int value) {
        return ConstantSize.dluX(value);
    }

    public static ConstantSize dluY(int value) {
        return ConstantSize.dluY(value);
    }

    public static ConstantSize pixel(int value) {
        return new ConstantSize(value, ConstantSize.PIXEL);
    }

    public static Size bounded(Size basis, Size lowerBound, Size upperBound) {
        return new BoundedSize(basis, lowerBound, upperBound);
    }

    public static int inchAsPixel(double in, Component component) {
        return in == 0.0 ? 0 : Sizes.getUnitConverter().inchAsPixel(in, component);
    }

    public static int millimeterAsPixel(double mm, Component component) {
        return mm == 0.0 ? 0 : Sizes.getUnitConverter().millimeterAsPixel(mm, component);
    }

    public static int centimeterAsPixel(double cm, Component component) {
        return cm == 0.0 ? 0 : Sizes.getUnitConverter().centimeterAsPixel(cm, component);
    }

    public static int pointAsPixel(int pt, Component component) {
        return pt == 0 ? 0 : Sizes.getUnitConverter().pointAsPixel(pt, component);
    }

    public static int dialogUnitXAsPixel(int dluX, Component component) {
        return dluX == 0 ? 0 : Sizes.getUnitConverter().dialogUnitXAsPixel(dluX, component);
    }

    public static int dialogUnitYAsPixel(int dluY, Component component) {
        return dluY == 0 ? 0 : Sizes.getUnitConverter().dialogUnitYAsPixel(dluY, component);
    }

    public static UnitConverter getUnitConverter() {
        if (unitConverter == null) {
            unitConverter = DefaultUnitConverter.getInstance();
        }
        return unitConverter;
    }

    public static void setUnitConverter(UnitConverter newUnitConverter) {
        unitConverter = newUnitConverter;
    }

    public static ConstantSize.Unit getDefaultUnit() {
        return defaultUnit;
    }

    public static void setDefaultUnit(ConstantSize.Unit unit) {
        if (unit == ConstantSize.DLUX || unit == ConstantSize.DLUY) {
            throw new IllegalArgumentException("The unit must not be DLUX or DLUY. To use DLU as default unit, invoke this method with null.");
        }
        defaultUnit = unit;
    }

    static {
        defaultUnit = ConstantSize.PIXEL;
    }

    static final class ComponentSize
    implements Size,
    Serializable {
        private final transient String name;
        private static int nextOrdinal = 0;
        private final int ordinal = nextOrdinal++;

        private ComponentSize(String name) {
            this.name = name;
        }

        static ComponentSize valueOf(String str) {
            if (str.equals("m") || str.equals("min")) {
                return MINIMUM;
            }
            if (str.equals("p") || str.equals("pref")) {
                return PREFERRED;
            }
            if (str.equals("d") || str.equals("default")) {
                return DEFAULT;
            }
            return null;
        }

        @Override
        public int maximumSize(Container container, List components, FormLayout.Measure minMeasure, FormLayout.Measure prefMeasure, FormLayout.Measure defaultMeasure) {
            FormLayout.Measure measure = this == MINIMUM ? minMeasure : (this == PREFERRED ? prefMeasure : defaultMeasure);
            int maximum = 0;
            for (Component c : components) {
                maximum = Math.max(maximum, measure.sizeOf(c));
            }
            return maximum;
        }

        @Override
        public boolean compressible() {
            return this == DEFAULT;
        }

        public String toString() {
            return this.encode();
        }

        @Override
        public String encode() {
            return this.name.substring(0, 1);
        }

        private Object readResolve() {
            return VALUES[this.ordinal];
        }
    }
}

