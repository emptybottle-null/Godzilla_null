/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.ColorFunctions;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;

public class DerivedColor
extends ColorUIResource {
    private final ColorFunctions.ColorFunction[] functions;
    private boolean hasBaseOfDefaultColor;
    private int baseOfDefaultColorRGB;

    public DerivedColor(Color defaultColor, ColorFunctions.ColorFunction ... functions2) {
        super(defaultColor != null ? defaultColor : Color.red);
        this.functions = functions2;
    }

    public Color derive(Color baseColor) {
        if (this.hasBaseOfDefaultColor && this.baseOfDefaultColorRGB == baseColor.getRGB() || baseColor == this) {
            return this;
        }
        Color result = ColorFunctions.applyFunctions(baseColor, this.functions);
        if (!this.hasBaseOfDefaultColor && result.getRGB() == this.getRGB()) {
            this.hasBaseOfDefaultColor = true;
            this.baseOfDefaultColorRGB = baseColor.getRGB();
        }
        return result;
    }

    public ColorFunctions.ColorFunction[] getFunctions() {
        return this.functions;
    }
}

