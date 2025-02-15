/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OrderedCrossover<T>
implements CrossoverPolicy {
    @Override
    public ChromosomePair crossover(Chromosome first, Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        if (!(first instanceof AbstractListChromosome) || !(second instanceof AbstractListChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
        }
        return this.mate((AbstractListChromosome)first, (AbstractListChromosome)second);
    }

    protected ChromosomePair mate(AbstractListChromosome<T> first, AbstractListChromosome<T> second) throws DimensionMismatchException {
        int b;
        int length = first.getLength();
        if (length != second.getLength()) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        List<T> parent1Rep = first.getRepresentation();
        List<T> parent2Rep = second.getRepresentation();
        ArrayList<T> child1 = new ArrayList<T>(length);
        ArrayList<T> child2 = new ArrayList<T>(length);
        HashSet<T> child1Set = new HashSet<T>(length);
        HashSet<T> child2Set = new HashSet<T>(length);
        RandomGenerator random = GeneticAlgorithm.getRandomGenerator();
        int a = random.nextInt(length);
        while (a == (b = random.nextInt(length))) {
        }
        int lb = FastMath.min(a, b);
        int ub = FastMath.max(a, b);
        child1.addAll(parent1Rep.subList(lb, ub + 1));
        child1Set.addAll(child1);
        child2.addAll(parent2Rep.subList(lb, ub + 1));
        child2Set.addAll(child2);
        for (int i = 1; i <= length; ++i) {
            int idx = (ub + i) % length;
            T item1 = parent1Rep.get(idx);
            T item2 = parent2Rep.get(idx);
            if (!child1Set.contains(item2)) {
                child1.add(item2);
                child1Set.add(item2);
            }
            if (child2Set.contains(item1)) continue;
            child2.add(item1);
            child2Set.add(item1);
        }
        Collections.rotate(child1, lb);
        Collections.rotate(child2, lb);
        return new ChromosomePair(first.newFixedLengthChromosome(child1), second.newFixedLengthChromosome(child2));
    }
}

