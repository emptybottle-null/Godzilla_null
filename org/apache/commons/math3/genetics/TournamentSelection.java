/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.ListPopulation;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.SelectionPolicy;

public class TournamentSelection
implements SelectionPolicy {
    private int arity;

    public TournamentSelection(int arity) {
        this.arity = arity;
    }

    public ChromosomePair select(Population population) throws MathIllegalArgumentException {
        return new ChromosomePair(this.tournament((ListPopulation)population), this.tournament((ListPopulation)population));
    }

    private Chromosome tournament(ListPopulation population) throws MathIllegalArgumentException {
        if (population.getPopulationSize() < this.arity) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_LARGE_TOURNAMENT_ARITY, this.arity, population.getPopulationSize());
        }
        ListPopulation tournamentPopulation = new ListPopulation(this.arity){

            public Population nextGeneration() {
                return null;
            }
        };
        ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>(population.getChromosomes());
        for (int i = 0; i < this.arity; ++i) {
            int rind = GeneticAlgorithm.getRandomGenerator().nextInt(chromosomes.size());
            tournamentPopulation.addChromosome((Chromosome)chromosomes.get(rind));
            chromosomes.remove(rind);
        }
        return tournamentPopulation.getFittestChromosome();
    }

    public int getArity() {
        return this.arity;
    }

    public void setArity(int arity) {
        this.arity = arity;
    }
}

