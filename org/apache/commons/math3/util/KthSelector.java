/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MedianOf3PivotingStrategy;
import org.apache.commons.math3.util.PivotingStrategyInterface;

public class KthSelector
implements Serializable {
    private static final long serialVersionUID = 20140713L;
    private static final int MIN_SELECT_SIZE = 15;
    private final PivotingStrategyInterface pivotingStrategy;

    public KthSelector() {
        this.pivotingStrategy = new MedianOf3PivotingStrategy();
    }

    public KthSelector(PivotingStrategyInterface pivotingStrategy) throws NullArgumentException {
        MathUtils.checkNotNull(pivotingStrategy);
        this.pivotingStrategy = pivotingStrategy;
    }

    public PivotingStrategyInterface getPivotingStrategy() {
        return this.pivotingStrategy;
    }

    public double select(double[] work, int[] pivotsHeap, int k) {
        boolean usePivotsHeap;
        int begin = 0;
        int end = work.length;
        int node = 0;
        boolean bl = usePivotsHeap = pivotsHeap != null;
        while (end - begin > 15) {
            int pivot;
            if (usePivotsHeap && node < pivotsHeap.length && pivotsHeap[node] >= 0) {
                pivot = pivotsHeap[node];
            } else {
                pivot = this.partition(work, begin, end, this.pivotingStrategy.pivotIndex(work, begin, end));
                if (usePivotsHeap && node < pivotsHeap.length) {
                    pivotsHeap[node] = pivot;
                }
            }
            if (k == pivot) {
                return work[k];
            }
            if (k < pivot) {
                end = pivot;
                node = FastMath.min(2 * node + 1, usePivotsHeap ? pivotsHeap.length : end);
                continue;
            }
            begin = pivot + 1;
            node = FastMath.min(2 * node + 2, usePivotsHeap ? pivotsHeap.length : end);
        }
        Arrays.sort(work, begin, end);
        return work[k];
    }

    private int partition(double[] work, int begin, int end, int pivot) {
        double value = work[pivot];
        work[pivot] = work[begin];
        int i = begin + 1;
        int j = end - 1;
        while (i < j) {
            while (i < j && work[j] > value) {
                --j;
            }
            while (i < j && work[i] < value) {
                ++i;
            }
            if (i >= j) continue;
            double tmp = work[i];
            work[i++] = work[j];
            work[j--] = tmp;
        }
        if (i >= end || work[i] > value) {
            --i;
        }
        work[begin] = work[i];
        work[i] = value;
        return i;
    }
}

