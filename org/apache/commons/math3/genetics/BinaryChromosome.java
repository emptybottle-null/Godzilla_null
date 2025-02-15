/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BinaryChromosome
extends AbstractListChromosome<Integer> {
    public BinaryChromosome(List<Integer> representation) throws InvalidRepresentationException {
        super(representation);
    }

    public BinaryChromosome(Integer[] representation) throws InvalidRepresentationException {
        super(representation);
    }

    @Override
    protected void checkValidity(List<Integer> chromosomeRepresentation) throws InvalidRepresentationException {
        for (int i : chromosomeRepresentation) {
            if (i >= 0 && i <= 1) continue;
            throw new InvalidRepresentationException(LocalizedFormats.INVALID_BINARY_DIGIT, i);
        }
    }

    public static List<Integer> randomBinaryRepresentation(int length) {
        ArrayList<Integer> rList = new ArrayList<Integer>(length);
        for (int j = 0; j < length; ++j) {
            rList.add(GeneticAlgorithm.getRandomGenerator().nextInt(2));
        }
        return rList;
    }

    @Override
    protected boolean isSame(Chromosome another) {
        if (!(another instanceof BinaryChromosome)) {
            return false;
        }
        BinaryChromosome anotherBc = (BinaryChromosome)another;
        if (this.getLength() != anotherBc.getLength()) {
            return false;
        }
        for (int i = 0; i < this.getRepresentation().size(); ++i) {
            if (((Integer)this.getRepresentation().get(i)).equals(anotherBc.getRepresentation().get(i))) continue;
            return false;
        }
        return true;
    }
}

