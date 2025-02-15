/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public enum StepNormalizerBounds {
    NEITHER(false, false),
    FIRST(true, false),
    LAST(false, true),
    BOTH(true, true);

    private final boolean first;
    private final boolean last;

    private StepNormalizerBounds(boolean first, boolean last) {
        this.first = first;
        this.last = last;
    }

    public boolean firstIncluded() {
        return this.first;
    }

    public boolean lastIncluded() {
        return this.last;
    }
}

