/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OnePointCrossover<T>
implements CrossoverPolicy {
    @Override
    public ChromosomePair crossover(Chromosome first, Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        if (!(first instanceof AbstractListChromosome) || !(second instanceof AbstractListChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
        }
        return this.crossover((AbstractListChromosome)first, (AbstractListChromosome)second);
    }

    private ChromosomePair crossover(AbstractListChromosome<T> first, AbstractListChromosome<T> second) throws DimensionMismatchException {
        int i;
        int length = first.getLength();
        if (length != second.getLength()) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        List<T> parent1Rep = first.getRepresentation();
        List<T> parent2Rep = second.getRepresentation();
        ArrayList<T> child1Rep = new ArrayList<T>(length);
        ArrayList<T> child2Rep = new ArrayList<T>(length);
        int crossoverIndex = 1 + GeneticAlgorithm.getRandomGenerator().nextInt(length - 2);
        for (i = 0; i < crossoverIndex; ++i) {
            child1Rep.add(parent1Rep.get(i));
            child2Rep.add(parent2Rep.get(i));
        }
        for (i = crossoverIndex; i < length; ++i) {
            child1Rep.add(parent2Rep.get(i));
            child2Rep.add(parent1Rep.get(i));
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}

