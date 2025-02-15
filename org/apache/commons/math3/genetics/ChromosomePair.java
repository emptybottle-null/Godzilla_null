/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.genetics.Chromosome;

public class ChromosomePair {
    private final Chromosome first;
    private final Chromosome second;

    public ChromosomePair(Chromosome c1, Chromosome c2) {
        this.first = c1;
        this.second = c2;
    }

    public Chromosome getFirst() {
        return this.first;
    }

    public Chromosome getSecond() {
        return this.second;
    }

    public String toString() {
        return String.format("(%s,%s)", this.getFirst(), this.getSecond());
    }
}

