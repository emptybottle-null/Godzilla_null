/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ListPopulation;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ElitisticListPopulation
extends ListPopulation {
    private double elitismRate = 0.9;

    public ElitisticListPopulation(List<Chromosome> chromosomes, int populationLimit, double elitismRate) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException, OutOfRangeException {
        super(chromosomes, populationLimit);
        this.setElitismRate(elitismRate);
    }

    public ElitisticListPopulation(int populationLimit, double elitismRate) throws NotPositiveException, OutOfRangeException {
        super(populationLimit);
        this.setElitismRate(elitismRate);
    }

    @Override
    public Population nextGeneration() {
        int boundIndex;
        ElitisticListPopulation nextGeneration = new ElitisticListPopulation(this.getPopulationLimit(), this.getElitismRate());
        List<Chromosome> oldChromosomes = this.getChromosomeList();
        Collections.sort(oldChromosomes);
        for (int i = boundIndex = (int)FastMath.ceil((1.0 - this.getElitismRate()) * (double)oldChromosomes.size()); i < oldChromosomes.size(); ++i) {
            nextGeneration.addChromosome(oldChromosomes.get(i));
        }
        return nextGeneration;
    }

    public void setElitismRate(double elitismRate) throws OutOfRangeException {
        if (elitismRate < 0.0 || elitismRate > 1.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.ELITISM_RATE, (Number)elitismRate, 0, 1);
        }
        this.elitismRate = elitismRate;
    }

    public double getElitismRate() {
        return this.elitismRate;
    }
}

