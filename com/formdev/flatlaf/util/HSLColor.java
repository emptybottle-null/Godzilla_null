/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import java.awt.Color;

public class HSLColor {
    private final Color rgb;
    private final float[] hsl;
    private final float alpha;

    public HSLColor(Color rgb) {
        this.rgb = rgb;
        this.hsl = HSLColor.fromRGB(rgb);
        this.alpha = (float)rgb.getAlpha() / 255.0f;
    }

    public HSLColor(float h, float s, float l) {
        this(h, s, l, 1.0f);
    }

    public HSLColor(float h, float s, float l, float alpha) {
        this.hsl = new float[]{h, s, l};
        this.alpha = alpha;
        this.rgb = HSLColor.toRGB(this.hsl, alpha);
    }

    public HSLColor(float[] hsl) {
        this(hsl, 1.0f);
    }

    public HSLColor(float[] hsl, float alpha) {
        this.hsl = hsl;
        this.alpha = alpha;
        this.rgb = HSLColor.toRGB(hsl, alpha);
    }

    public Color adjustHue(float degrees) {
        return HSLColor.toRGB(degrees, this.hsl[1], this.hsl[2], this.alpha);
    }

    public Color adjustLuminance(float percent) {
        return HSLColor.toRGB(this.hsl[0], this.hsl[1], percent, this.alpha);
    }

    public Color adjustSaturation(float percent) {
        return HSLColor.toRGB(this.hsl[0], percent, this.hsl[2], this.alpha);
    }

    public Color adjustShade(float percent) {
        float multiplier = (100.0f - percent) / 100.0f;
        float l = Math.max(0.0f, this.hsl[2] * multiplier);
        return HSLColor.toRGB(this.hsl[0], this.hsl[1], l, this.alpha);
    }

    public Color adjustTone(float percent) {
        float multiplier = (100.0f + percent) / 100.0f;
        float l = Math.min(100.0f, this.hsl[2] * multiplier);
        return HSLColor.toRGB(this.hsl[0], this.hsl[1], l, this.alpha);
    }

    public float getAlpha() {
        return this.alpha;
    }

    public Color getComplementary() {
        float hue = (this.hsl[0] + 180.0f) % 360.0f;
        return HSLColor.toRGB(hue, this.hsl[1], this.hsl[2]);
    }

    public float getHue() {
        return this.hsl[0];
    }

    public float[] getHSL() {
        return this.hsl;
    }

    public float getLuminance() {
        return this.hsl[2];
    }

    public Color getRGB() {
        return this.rgb;
    }

    public float getSaturation() {
        return this.hsl[1];
    }

    public String toString() {
        String toString = "HSLColor[h=" + this.hsl[0] + ",s=" + this.hsl[1] + ",l=" + this.hsl[2] + ",alpha=" + this.alpha + "]";
        return toString;
    }

    public static float[] fromRGB(Color color) {
        float[] rgb = color.getRGBColorComponents(null);
        float r = rgb[0];
        float g = rgb[1];
        float b = rgb[2];
        float min = Math.min(r, Math.min(g, b));
        float max = Math.max(r, Math.max(g, b));
        float h = 0.0f;
        if (max == min) {
            h = 0.0f;
        } else if (max == r) {
            h = (60.0f * (g - b) / (max - min) + 360.0f) % 360.0f;
        } else if (max == g) {
            h = 60.0f * (b - r) / (max - min) + 120.0f;
        } else if (max == b) {
            h = 60.0f * (r - g) / (max - min) + 240.0f;
        }
        float l = (max + min) / 2.0f;
        float s = 0.0f;
        s = max == min ? 0.0f : (l <= 0.5f ? (max - min) / (max + min) : (max - min) / (2.0f - max - min));
        return new float[]{h, s * 100.0f, l * 100.0f};
    }

    public static Color toRGB(float[] hsl) {
        return HSLColor.toRGB(hsl, 1.0f);
    }

    public static Color toRGB(float[] hsl, float alpha) {
        return HSLColor.toRGB(hsl[0], hsl[1], hsl[2], alpha);
    }

    public static Color toRGB(float h, float s, float l) {
        return HSLColor.toRGB(h, s, l, 1.0f);
    }

    public static Color toRGB(float h, float s, float l, float alpha) {
        if (s < 0.0f || s > 100.0f) {
            String message = "Color parameter outside of expected range - Saturation";
            throw new IllegalArgumentException(message);
        }
        if (l < 0.0f || l > 100.0f) {
            String message = "Color parameter outside of expected range - Luminance";
            throw new IllegalArgumentException(message);
        }
        if (alpha < 0.0f || alpha > 1.0f) {
            String message = "Color parameter outside of expected range - Alpha";
            throw new IllegalArgumentException(message);
        }
        h %= 360.0f;
        float q = 0.0f;
        q = (double)l < 0.5 ? l * (1.0f + s) : (l /= 100.0f) + (s /= 100.0f) - s * l;
        float p = 2.0f * l - q;
        float r = Math.max(0.0f, HSLColor.HueToRGB(p, q, (h /= 360.0f) + 0.33333334f));
        float g = Math.max(0.0f, HSLColor.HueToRGB(p, q, h));
        float b = Math.max(0.0f, HSLColor.HueToRGB(p, q, h - 0.33333334f));
        r = Math.min(r, 1.0f);
        g = Math.min(g, 1.0f);
        b = Math.min(b, 1.0f);
        return new Color(r, g, b, alpha);
    }

    private static float HueToRGB(float p, float q, float h) {
        if (h < 0.0f) {
            h += 1.0f;
        }
        if (h > 1.0f) {
            h -= 1.0f;
        }
        if (6.0f * h < 1.0f) {
            return p + (q - p) * 6.0f * h;
        }
        if (2.0f * h < 1.0f) {
            return q;
        }
        if (3.0f * h < 2.0f) {
            return p + (q - p) * 6.0f * (0.6666667f - h);
        }
        return p;
    }
}

