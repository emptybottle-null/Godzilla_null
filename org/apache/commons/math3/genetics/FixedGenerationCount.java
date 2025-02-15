/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;

public class FixedGenerationCount
implements StoppingCondition {
    private int numGenerations = 0;
    private final int maxGenerations;

    public FixedGenerationCount(int maxGenerations) throws NumberIsTooSmallException {
        if (maxGenerations <= 0) {
            throw new NumberIsTooSmallException(maxGenerations, (Number)1, true);
        }
        this.maxGenerations = maxGenerations;
    }

    public boolean isSatisfied(Population population) {
        if (this.numGenerations < this.maxGenerations) {
            ++this.numGenerations;
            return false;
        }
        return true;
    }

    public int getNumGenerations() {
        return this.numGenerations;
    }
}

