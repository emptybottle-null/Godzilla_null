/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.TimeBase;

public class TimeSum
extends TimeBase {
    TimeBase t1;
    TimeBase t2;
    boolean add;

    public TimeSum(TimeBase t1, TimeBase t2, boolean add) {
        this.t1 = t1;
        this.t2 = t2;
        this.add = add;
    }

    @Override
    public double evalTime() {
        return this.add ? this.t1.evalTime() + this.t2.evalTime() : this.t1.evalTime() - this.t2.evalTime();
    }

    @Override
    public void setParentElement(AnimationElement ele) {
        this.t1.setParentElement(ele);
        this.t2.setParentElement(ele);
    }
}

