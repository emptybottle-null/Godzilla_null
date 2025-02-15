/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.TimeBase;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class TimeCompound
extends TimeBase {
    static final Pattern patPlus = Pattern.compile("\\+");
    final List<TimeBase> componentTimes;
    private AnimationElement parent;

    public TimeCompound(List<TimeBase> timeBases) {
        this.componentTimes = Collections.unmodifiableList(timeBases);
    }

    @Override
    public double evalTime() {
        double agg = 0.0;
        for (TimeBase timeEle : this.componentTimes) {
            double time = timeEle.evalTime();
            agg += time;
        }
        return agg;
    }

    @Override
    public void setParentElement(AnimationElement ele) {
        this.parent = ele;
        for (TimeBase timeEle : this.componentTimes) {
            timeEle.setParentElement(ele);
        }
    }
}

