/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.PermutationChromosome;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class RandomKey<T>
extends AbstractListChromosome<Double>
implements PermutationChromosome<T> {
    private final List<Double> sortedRepresentation;
    private final List<Integer> baseSeqPermutation;

    public RandomKey(List<Double> representation) throws InvalidRepresentationException {
        super(representation);
        ArrayList sortedRepr = new ArrayList(this.getRepresentation());
        Collections.sort(sortedRepr);
        this.sortedRepresentation = Collections.unmodifiableList(sortedRepr);
        this.baseSeqPermutation = Collections.unmodifiableList(RandomKey.decodeGeneric(RandomKey.baseSequence(this.getLength()), this.getRepresentation(), this.sortedRepresentation));
    }

    public RandomKey(Double[] representation) throws InvalidRepresentationException {
        this(Arrays.asList(representation));
    }

    @Override
    public List<T> decode(List<T> sequence) {
        return RandomKey.decodeGeneric(sequence, this.getRepresentation(), this.sortedRepresentation);
    }

    private static <S> List<S> decodeGeneric(List<S> sequence, List<Double> representation, List<Double> sortedRepr) throws DimensionMismatchException {
        int l = sequence.size();
        if (representation.size() != l) {
            throw new DimensionMismatchException(representation.size(), l);
        }
        if (sortedRepr.size() != l) {
            throw new DimensionMismatchException(sortedRepr.size(), l);
        }
        ArrayList<Double> reprCopy = new ArrayList<Double>(representation);
        ArrayList<S> res = new ArrayList<S>(l);
        for (int i = 0; i < l; ++i) {
            int index = reprCopy.indexOf(sortedRepr.get(i));
            res.add(sequence.get(index));
            reprCopy.set(index, null);
        }
        return res;
    }

    @Override
    protected boolean isSame(Chromosome another) {
        if (!(another instanceof RandomKey)) {
            return false;
        }
        RandomKey anotherRk = (RandomKey)another;
        if (this.getLength() != anotherRk.getLength()) {
            return false;
        }
        List<Integer> thisPerm = this.baseSeqPermutation;
        List<Integer> anotherPerm = anotherRk.baseSeqPermutation;
        for (int i = 0; i < this.getLength(); ++i) {
            if (thisPerm.get(i) == anotherPerm.get(i)) continue;
            return false;
        }
        return true;
    }

    @Override
    protected void checkValidity(List<Double> chromosomeRepresentation) throws InvalidRepresentationException {
        for (double val : chromosomeRepresentation) {
            if (!(val < 0.0) && !(val > 1.0)) continue;
            throw new InvalidRepresentationException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, val, 0, 1);
        }
    }

    public static final List<Double> randomPermutation(int l) {
        ArrayList<Double> repr = new ArrayList<Double>(l);
        for (int i = 0; i < l; ++i) {
            repr.add(GeneticAlgorithm.getRandomGenerator().nextDouble());
        }
        return repr;
    }

    public static final List<Double> identityPermutation(int l) {
        ArrayList<Double> repr = new ArrayList<Double>(l);
        for (int i = 0; i < l; ++i) {
            repr.add((double)i / (double)l);
        }
        return repr;
    }

    public static <S> List<Double> comparatorPermutation(List<S> data, Comparator<S> comparator) {
        ArrayList<S> sortedData = new ArrayList<S>(data);
        Collections.sort(sortedData, comparator);
        return RandomKey.inducedPermutation(data, sortedData);
    }

    public static <S> List<Double> inducedPermutation(List<S> originalData, List<S> permutedData) throws DimensionMismatchException, MathIllegalArgumentException {
        if (originalData.size() != permutedData.size()) {
            throw new DimensionMismatchException(permutedData.size(), originalData.size());
        }
        int l = originalData.size();
        ArrayList<S> origDataCopy = new ArrayList<S>(originalData);
        Double[] res = new Double[l];
        for (int i = 0; i < l; ++i) {
            int index = origDataCopy.indexOf(permutedData.get(i));
            if (index == -1) {
                throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA, new Object[0]);
            }
            res[index] = (double)i / (double)l;
            origDataCopy.set(index, null);
        }
        return Arrays.asList(res);
    }

    @Override
    public String toString() {
        return String.format("(f=%s pi=(%s))", this.getFitness(), this.baseSeqPermutation);
    }

    private static List<Integer> baseSequence(int l) {
        ArrayList<Integer> baseSequence = new ArrayList<Integer>(l);
        for (int i = 0; i < l; ++i) {
            baseSequence.add(i);
        }
        return baseSequence;
    }
}

