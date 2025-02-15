/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package net.miginfocom.layout;

import java.io.Serializable;
import net.miginfocom.layout.LayoutUtil;

public class AnimSpec
implements Serializable {
    public static final AnimSpec DEF = new AnimSpec(0, 0, 0.2f, 0.2f);
    private final int prio;
    private final int durMillis;
    private final float easeIn;
    private final float easeOut;

    public AnimSpec(int prio, int durMillis, float easeIn, float easeOut) {
        this.prio = prio;
        this.durMillis = durMillis;
        this.easeIn = LayoutUtil.clamp(easeIn, 0.0f, 1.0f);
        this.easeOut = LayoutUtil.clamp(easeOut, 0.0f, 1.0f);
    }

    public int getPriority() {
        return this.prio;
    }

    public int getDurationMillis(int defMillis) {
        return this.durMillis > 0 ? this.durMillis : defMillis;
    }

    public int getDurationMillis() {
        return this.durMillis;
    }

    public float getEaseIn() {
        return this.easeIn;
    }

    public float getEaseOut() {
        return this.easeOut;
    }
}

