/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.BinaryChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.MutationPolicy;

public class BinaryMutation
implements MutationPolicy {
    public Chromosome mutate(Chromosome original) throws MathIllegalArgumentException {
        if (!(original instanceof BinaryChromosome)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INVALID_BINARY_CHROMOSOME, new Object[0]);
        }
        BinaryChromosome origChrom = (BinaryChromosome)original;
        ArrayList newRepr = new ArrayList(origChrom.getRepresentation());
        int geneIndex = GeneticAlgorithm.getRandomGenerator().nextInt(origChrom.getLength());
        newRepr.set(geneIndex, (Integer)origChrom.getRepresentation().get(geneIndex) == 0 ? 1 : 0);
        AbstractListChromosome newChrom = origChrom.newFixedLengthChromosome(newRepr);
        return newChrom;
    }
}

