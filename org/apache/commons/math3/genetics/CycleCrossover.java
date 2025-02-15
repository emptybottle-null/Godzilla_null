/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
public class CycleCrossover<T>
implements CrossoverPolicy {
    private final boolean randomStart;

    public CycleCrossover() {
        this(false);
    }

    public CycleCrossover(boolean randomStart) {
        this.randomStart = randomStart;
    }

    public boolean isRandomStart() {
        return this.randomStart;
    }

    @Override
    public ChromosomePair crossover(Chromosome first, Chromosome second) throws DimensionMismatchException, MathIllegalArgumentException {
        if (!(first instanceof AbstractListChromosome) || !(second instanceof AbstractListChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_FIXED_LENGTH_CHROMOSOME, new Object[0]);
        }
        return this.mate((AbstractListChromosome)first, (AbstractListChromosome)second);
    }

    protected ChromosomePair mate(AbstractListChromosome<T> first, AbstractListChromosome<T> second) throws DimensionMismatchException {
        int length = first.getLength();
        if (length != second.getLength()) {
            throw new DimensionMismatchException(second.getLength(), length);
        }
        List<T> parent1Rep = first.getRepresentation();
        List<T> parent2Rep = second.getRepresentation();
        ArrayList<T> child1Rep = new ArrayList<T>(second.getRepresentation());
        ArrayList<T> child2Rep = new ArrayList<T>(first.getRepresentation());
        HashSet visitedIndices = new HashSet(length);
        ArrayList<Integer> indices = new ArrayList<Integer>(length);
        int idx = this.randomStart ? GeneticAlgorithm.getRandomGenerator().nextInt(length) : 0;
        int cycle = 1;
        while (visitedIndices.size() < length) {
            indices.add(idx);
            T item = parent2Rep.get(idx);
            idx = parent1Rep.indexOf(item);
            while (idx != (Integer)indices.get(0)) {
                indices.add(idx);
                item = parent2Rep.get(idx);
                idx = parent1Rep.indexOf(item);
            }
            if (cycle++ % 2 != 0) {
                Iterator i$ = indices.iterator();
                while (i$.hasNext()) {
                    int i = (Integer)i$.next();
                    Object tmp = child1Rep.get(i);
                    child1Rep.set(i, child2Rep.get(i));
                    child2Rep.set(i, tmp);
                }
            }
            visitedIndices.addAll(indices);
            idx = ((Integer)indices.get(0) + 1) % length;
            while (visitedIndices.contains(idx) && visitedIndices.size() < length) {
                if (++idx < length) continue;
                idx = 0;
            }
            indices.clear();
        }
        return new ChromosomePair(first.newFixedLengthChromosome(child1Rep), second.newFixedLengthChromosome(child2Rep));
    }
}

