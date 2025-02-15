/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.util.Animator;

public class CubicBezierEasing
implements Animator.Interpolator {
    public static final CubicBezierEasing STANDARD_EASING = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    public static final CubicBezierEasing EASE = new CubicBezierEasing(0.25f, 0.1f, 0.25f, 1.0f);
    public static final CubicBezierEasing EASE_IN = new CubicBezierEasing(0.42f, 0.0f, 1.0f, 1.0f);
    public static final CubicBezierEasing EASE_IN_OUT = new CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f);
    public static final CubicBezierEasing EASE_OUT = new CubicBezierEasing(0.0f, 0.0f, 0.58f, 1.0f);
    private final float x1;
    private final float y1;
    private final float x2;
    private final float y2;

    public CubicBezierEasing(float x1, float y1, float x2, float y2) {
        if (x1 < 0.0f || x1 > 1.0f || y1 < 0.0f || y1 > 1.0f || x2 < 0.0f || x2 > 1.0f || y2 < 0.0f || y2 > 1.0f) {
            throw new IllegalArgumentException("control points must be in range [0, 1]");
        }
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public float interpolate(float fraction) {
        if (fraction <= 0.0f || fraction >= 1.0f) {
            return fraction;
        }
        float low = 0.0f;
        float high = 1.0f;
        float mid;
        float estimate;
        while (!(Math.abs(fraction - (estimate = CubicBezierEasing.cubicBezier(mid = (low + high) / 2.0f, this.x1, this.x2))) < 5.0E-4f)) {
            if (estimate < fraction) {
                low = mid;
                continue;
            }
            high = mid;
        }
        return CubicBezierEasing.cubicBezier(mid, this.y1, this.y2);
    }

    private static float cubicBezier(float t, float xy1, float xy2) {
        float invT = 1.0f - t;
        float b1 = 3.0f * t * (invT * invT);
        float b2 = 3.0f * (t * t) * invT;
        float b3 = t * t * t;
        return b1 * xy1 + b2 * xy2 + b3;
    }
}

