/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.KthSelector;

public class Median
extends Percentile
implements Serializable {
    private static final long serialVersionUID = -3961477041290915687L;
    private static final double FIXED_QUANTILE_50 = 50.0;

    public Median() {
        super(50.0);
    }

    public Median(Median original) throws NullArgumentException {
        super(original);
    }

    private Median(Percentile.EstimationType estimationType, NaNStrategy nanStrategy, KthSelector kthSelector) throws MathIllegalArgumentException {
        super(50.0, estimationType, nanStrategy, kthSelector);
    }

    public Median withEstimationType(Percentile.EstimationType newEstimationType) {
        return new Median(newEstimationType, this.getNaNStrategy(), this.getKthSelector());
    }

    public Median withNaNStrategy(NaNStrategy newNaNStrategy) {
        return new Median(this.getEstimationType(), newNaNStrategy, this.getKthSelector());
    }

    public Median withKthSelector(KthSelector newKthSelector) {
        return new Median(this.getEstimationType(), this.getNaNStrategy(), newKthSelector);
    }
}

