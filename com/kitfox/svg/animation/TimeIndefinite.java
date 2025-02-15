/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.animation.TimeBase;

public class TimeIndefinite
extends TimeBase {
    @Override
    public double evalTime() {
        return Double.POSITIVE_INFINITY;
    }
}

