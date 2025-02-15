/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.genetics.Chromosome;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Population
extends Iterable<Chromosome> {
    public int getPopulationSize();

    public int getPopulationLimit();

    public Population nextGeneration();

    public void addChromosome(Chromosome var1) throws NumberIsTooLargeException;

    public Chromosome getFittestChromosome();
}

