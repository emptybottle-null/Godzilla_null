/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.linear;

import org.apache.commons.math3.optim.OptimizationData;

public class NonNegativeConstraint
implements OptimizationData {
    private final boolean isRestricted;

    public NonNegativeConstraint(boolean restricted) {
        this.isRestricted = restricted;
    }

    public boolean isRestrictedToNonNegative() {
        return this.isRestricted;
    }
}

