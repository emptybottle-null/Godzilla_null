/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.SelectionPolicy;
import org.apache.commons.math3.genetics.StoppingCondition;
import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

public class GeneticAlgorithm {
    private static RandomGenerator randomGenerator = new JDKRandomGenerator();
    private final CrossoverPolicy crossoverPolicy;
    private final double crossoverRate;
    private final MutationPolicy mutationPolicy;
    private final double mutationRate;
    private final SelectionPolicy selectionPolicy;
    private int generationsEvolved = 0;

    public GeneticAlgorithm(CrossoverPolicy crossoverPolicy, double crossoverRate, MutationPolicy mutationPolicy, double mutationRate, SelectionPolicy selectionPolicy) throws OutOfRangeException {
        if (crossoverRate < 0.0 || crossoverRate > 1.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.CROSSOVER_RATE, (Number)crossoverRate, 0, 1);
        }
        if (mutationRate < 0.0 || mutationRate > 1.0) {
            throw new OutOfRangeException((Localizable)LocalizedFormats.MUTATION_RATE, (Number)mutationRate, 0, 1);
        }
        this.crossoverPolicy = crossoverPolicy;
        this.crossoverRate = crossoverRate;
        this.mutationPolicy = mutationPolicy;
        this.mutationRate = mutationRate;
        this.selectionPolicy = selectionPolicy;
    }

    public static synchronized void setRandomGenerator(RandomGenerator random) {
        randomGenerator = random;
    }

    public static synchronized RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public Population evolve(Population initial, StoppingCondition condition) {
        Population current = initial;
        this.generationsEvolved = 0;
        while (!condition.isSatisfied(current)) {
            current = this.nextGeneration(current);
            ++this.generationsEvolved;
        }
        return current;
    }

    public Population nextGeneration(Population current) {
        Population nextGeneration = current.nextGeneration();
        RandomGenerator randGen = GeneticAlgorithm.getRandomGenerator();
        while (nextGeneration.getPopulationSize() < nextGeneration.getPopulationLimit()) {
            ChromosomePair pair = this.getSelectionPolicy().select(current);
            if (randGen.nextDouble() < this.getCrossoverRate()) {
                pair = this.getCrossoverPolicy().crossover(pair.getFirst(), pair.getSecond());
            }
            if (randGen.nextDouble() < this.getMutationRate()) {
                pair = new ChromosomePair(this.getMutationPolicy().mutate(pair.getFirst()), this.getMutationPolicy().mutate(pair.getSecond()));
            }
            nextGeneration.addChromosome(pair.getFirst());
            if (nextGeneration.getPopulationSize() >= nextGeneration.getPopulationLimit()) continue;
            nextGeneration.addChromosome(pair.getSecond());
        }
        return nextGeneration;
    }

    public CrossoverPolicy getCrossoverPolicy() {
        return this.crossoverPolicy;
    }

    public double getCrossoverRate() {
        return this.crossoverRate;
    }

    public MutationPolicy getMutationPolicy() {
        return this.mutationPolicy;
    }

    public double getMutationRate() {
        return this.mutationRate;
    }

    public SelectionPolicy getSelectionPolicy() {
        return this.selectionPolicy;
    }

    public int getGenerationsEvolved() {
        return this.generationsEvolved;
    }
}

