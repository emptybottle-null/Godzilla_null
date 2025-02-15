/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.HSLColor;
import java.awt.Color;

public class ColorFunctions {
    public static Color applyFunctions(Color color, ColorFunction ... functions2) {
        float[] hsl = HSLColor.fromRGB(color);
        float alpha = (float)color.getAlpha() / 255.0f;
        float[] hsla = new float[]{hsl[0], hsl[1], hsl[2], alpha * 100.0f};
        for (ColorFunction function : functions2) {
            function.apply(hsla);
        }
        return HSLColor.toRGB(hsla[0], hsla[1], hsla[2], hsla[3] / 100.0f);
    }

    public static float clamp(float value) {
        return value < 0.0f ? 0.0f : (value > 100.0f ? 100.0f : value);
    }

    public static Color mix(Color color1, Color color2, float weight) {
        if (weight >= 1.0f) {
            return color1;
        }
        if (weight <= 0.0f) {
            return color2;
        }
        int r1 = color1.getRed();
        int g1 = color1.getGreen();
        int b1 = color1.getBlue();
        int a1 = color1.getAlpha();
        int r2 = color2.getRed();
        int g2 = color2.getGreen();
        int b2 = color2.getBlue();
        int a2 = color2.getAlpha();
        return new Color(Math.round((float)r2 + (float)(r1 - r2) * weight), Math.round((float)g2 + (float)(g1 - g2) * weight), Math.round((float)b2 + (float)(b1 - b2) * weight), Math.round((float)a2 + (float)(a1 - a2) * weight));
    }

    public static class Fade
    implements ColorFunction {
        public final float amount;

        public Fade(float amount) {
            this.amount = amount;
        }

        @Override
        public void apply(float[] hsla) {
            hsla[3] = ColorFunctions.clamp(this.amount);
        }
    }

    public static class HSLIncreaseDecrease
    implements ColorFunction {
        public final int hslIndex;
        public final boolean increase;
        public final float amount;
        public final boolean relative;
        public final boolean autoInverse;

        public HSLIncreaseDecrease(int hslIndex, boolean increase, float amount, boolean relative, boolean autoInverse) {
            this.hslIndex = hslIndex;
            this.increase = increase;
            this.amount = amount;
            this.relative = relative;
            this.autoInverse = autoInverse;
        }

        @Override
        public void apply(float[] hsla) {
            float amount2;
            float f = amount2 = this.increase ? this.amount : -this.amount;
            if (this.hslIndex == 0) {
                hsla[0] = (hsla[0] + amount2) % 360.0f;
                return;
            }
            amount2 = this.autoInverse && this.shouldInverse(hsla) ? -amount2 : amount2;
            hsla[this.hslIndex] = ColorFunctions.clamp(this.relative ? hsla[this.hslIndex] * ((100.0f + amount2) / 100.0f) : hsla[this.hslIndex] + amount2);
        }

        protected boolean shouldInverse(float[] hsla) {
            return this.increase ? hsla[this.hslIndex] > 65.0f : hsla[this.hslIndex] < 35.0f;
        }
    }

    public static interface ColorFunction {
        public void apply(float[] var1);
    }
}

