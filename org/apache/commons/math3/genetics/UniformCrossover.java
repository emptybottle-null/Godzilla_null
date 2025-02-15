/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.random.RandomGenerator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class UniformCrossover<T>
implements CrossoverPolicy {
    private final double ratio;

    public UniformCrossover(double ratio) throws OutOfRangeException {
        if (ratio < 0.0 || ratio > 1.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.CROSSOVER_RATE, (Number)ratio, 0.0, 1.0);
        }
        this.ratio = ratio;
    }

    public double getRatio() {
        return this.ratio;
    }

    @Override
    public ChromosomePair crossover(Chromosome first, Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        if (!(first instanceof AbstractListChromosome) || !(second instanceof AbstractListChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
        }
        return this.mate((AbstractListChromosome)first, (AbstractListChromosome)second);
    }

    private ChromosomePair mate(AbstractListChromosome<T> first, AbstractListChromosome<T> second) throws DimensionMismatchException {
        int length = first.getLength();
        if (length != second.getLength()) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        List<T> parent1Rep = first.getRepresentation();
        List<T> parent2Rep = second.getRepresentation();
        ArrayList<T> child1Rep = new ArrayList<T>(length);
        ArrayList<T> child2Rep = new ArrayList<T>(length);
        RandomGenerator random = GeneticAlgorithm.getRandomGenerator();
        for (int index = 0; index < length; ++index) {
            if (random.nextDouble() < this.ratio) {
                child1Rep.add(parent2Rep.get(index));
                child2Rep.add(parent1Rep.get(index));
                continue;
            }
            child1Rep.add(parent1Rep.get(index));
            child2Rep.add(parent2Rep.get(index));
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}

