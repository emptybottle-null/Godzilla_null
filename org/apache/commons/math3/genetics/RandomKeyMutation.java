/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.RandomKey;

public class RandomKeyMutation
implements MutationPolicy {
    public Chromosome mutate(Chromosome original) throws MathIllegalArgumentException {
        if (!(original instanceof RandomKey)) {
            throw new MathIllegalArgumentException(LocalizedFormats.RANDOMKEY_MUTATION_WRONG_CLASS, original.getClass().getSimpleName());
        }
        RandomKey originalRk = (RandomKey)original;
        List repr = originalRk.getRepresentation();
        int rInd = GeneticAlgorithm.getRandomGenerator().nextInt(repr.size());
        ArrayList newRepr = new ArrayList(repr);
        newRepr.set(rInd, GeneticAlgorithm.getRandomGenerator().nextDouble());
        return originalRk.newFixedLengthChromosome(newRepr);
    }
}

