/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.animation.TimeBase;

public class TimeDiscrete
extends TimeBase {
    double secs;

    public TimeDiscrete(double secs) {
        this.secs = secs;
    }

    @Override
    public double evalTime() {
        return this.secs;
    }
}

