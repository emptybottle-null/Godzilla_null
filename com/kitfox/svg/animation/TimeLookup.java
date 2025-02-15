/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.TimeBase;

public class TimeLookup
extends TimeBase {
    private AnimationElement parent;
    String node;
    String event;
    String paramList;

    public TimeLookup(AnimationElement parent, String node, String event, String paramList) {
        this.parent = parent;
        this.node = node;
        this.event = event;
        this.paramList = paramList;
    }

    @Override
    public double evalTime() {
        return 0.0;
    }

    @Override
    public void setParentElement(AnimationElement ele) {
        this.parent = ele;
    }
}

