/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.genetics.Fitness;
import org.apache.commons.math3.genetics.Population;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Chromosome
implements Comparable<Chromosome>,
Fitness {
    private static final double NO_FITNESS = Double.NEGATIVE_INFINITY;
    private double fitness = Double.NEGATIVE_INFINITY;

    public double getFitness() {
        if (this.fitness == Double.NEGATIVE_INFINITY) {
            this.fitness = this.fitness();
        }
        return this.fitness;
    }

    @Override
    public int compareTo(Chromosome another) {
        return Double.compare(this.getFitness(), another.getFitness());
    }

    protected boolean isSame(Chromosome another) {
        return false;
    }

    protected Chromosome findSameChromosome(Population population) {
        for (Chromosome anotherChr : population) {
            if (!this.isSame(anotherChr)) continue;
            return anotherChr;
        }
        return null;
    }

    public void searchForFitnessUpdate(Population population) {
        Chromosome sameChromosome = this.findSameChromosome(population);
        if (sameChromosome != null) {
            this.fitness = sameChromosome.getFitness();
        }
    }
}

