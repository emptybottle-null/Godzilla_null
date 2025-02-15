/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MapUtils {
    private MapUtils() {
    }

    public static Neuron findBest(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        Neuron best = null;
        double min = Double.POSITIVE_INFINITY;
        for (Neuron n : neurons) {
            double d = distance.compute(n.getFeatures(), features);
            if (!(d < min)) continue;
            min = d;
            best = n;
        }
        return best;
    }

    public static Pair<Neuron, Neuron> findBestAndSecondBest(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        Neuron[] best = new Neuron[]{null, null};
        double[] min = new double[]{Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        for (Neuron n : neurons) {
            double d = distance.compute(n.getFeatures(), features);
            if (d < min[0]) {
                min[1] = min[0];
                best[1] = best[0];
                min[0] = d;
                best[0] = n;
                continue;
            }
            if (!(d < min[1])) continue;
            min[1] = d;
            best[1] = n;
        }
        return new Pair<Neuron, Neuron>(best[0], best[1]);
    }

    public static Neuron[] sort(double[] features, Iterable<Neuron> neurons, DistanceMeasure distance) {
        ArrayList<PairNeuronDouble> list = new ArrayList<PairNeuronDouble>();
        for (Neuron n : neurons) {
            double d = distance.compute(n.getFeatures(), features);
            list.add(new PairNeuronDouble(n, d));
        }
        Collections.sort(list, PairNeuronDouble.COMPARATOR);
        int len = list.size();
        Neuron[] sorted = new Neuron[len];
        for (int i = 0; i < len; ++i) {
            sorted[i] = ((PairNeuronDouble)list.get(i)).getNeuron();
        }
        return sorted;
    }

    public static double[][] computeU(NeuronSquareMesh2D map, DistanceMeasure distance) {
        int numRows = map.getNumberOfRows();
        int numCols = map.getNumberOfColumns();
        double[][] uMatrix = new double[numRows][numCols];
        Network net = map.getNetwork();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                Neuron neuron = map.getNeuron(i, j);
                Collection<Neuron> neighbours = net.getNeighbours(neuron);
                double[] features = neuron.getFeatures();
                double d = 0.0;
                int count = 0;
                for (Neuron n : neighbours) {
                    ++count;
                    d += distance.compute(features, n.getFeatures());
                }
                uMatrix[i][j] = d / (double)count;
            }
        }
        return uMatrix;
    }

    public static int[][] computeHitHistogram(Iterable<double[]> data, NeuronSquareMesh2D map, DistanceMeasure distance) {
        HashMap<Neuron, Integer> hit = new HashMap<Neuron, Integer>();
        Network net = map.getNetwork();
        for (double[] f : data) {
            Neuron best = MapUtils.findBest(f, net, distance);
            Integer count = (Integer)hit.get(best);
            if (count == null) {
                hit.put(best, 1);
                continue;
            }
            hit.put(best, count + 1);
        }
        int numRows = map.getNumberOfRows();
        int numCols = map.getNumberOfColumns();
        int[][] histo = new int[numRows][numCols];
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                Neuron neuron = map.getNeuron(i, j);
                Integer count = (Integer)hit.get(neuron);
                histo[i][j] = count == null ? 0 : count;
            }
        }
        return histo;
    }

    public static double computeQuantizationError(Iterable<double[]> data, Iterable<Neuron> neurons, DistanceMeasure distance) {
        double d = 0.0;
        int count = 0;
        for (double[] f : data) {
            ++count;
            d += distance.compute(f, MapUtils.findBest(f, neurons, distance).getFeatures());
        }
        if (count == 0) {
            throw new NoDataException();
        }
        return d / (double)count;
    }

    public static double computeTopographicError(Iterable<double[]> data, Network net, DistanceMeasure distance) {
        int notAdjacentCount = 0;
        int count = 0;
        for (double[] f : data) {
            ++count;
            Pair<Neuron, Neuron> p = MapUtils.findBestAndSecondBest(f, net, distance);
            if (net.getNeighbours(p.getFirst()).contains(p.getSecond())) continue;
            ++notAdjacentCount;
        }
        if (count == 0) {
            throw new NoDataException();
        }
        return (double)notAdjacentCount / (double)count;
    }

    private static class PairNeuronDouble {
        static final Comparator<PairNeuronDouble> COMPARATOR = new Comparator<PairNeuronDouble>(){

            @Override
            public int compare(PairNeuronDouble o1, PairNeuronDouble o2) {
                return Double.compare(o1.value, o2.value);
            }
        };
        private final Neuron neuron;
        private final double value;

        PairNeuronDouble(Neuron neuron, double value) {
            this.neuron = neuron;
            this.value = value;
        }

        public Neuron getNeuron() {
            return this.neuron;
        }
    }
}

