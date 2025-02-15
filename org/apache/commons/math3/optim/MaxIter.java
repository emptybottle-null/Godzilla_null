/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optim.OptimizationData;

public class MaxIter
implements OptimizationData {
    private final int maxIter;

    public MaxIter(int max) {
        if (max <= 0) {
            throw new NotStrictlyPositiveException(max);
        }
        this.maxIter = max;
    }

    public int getMaxIter() {
        return this.maxIter;
    }

    public static MaxIter unlimited() {
        return new MaxIter(Integer.MAX_VALUE);
    }
}

