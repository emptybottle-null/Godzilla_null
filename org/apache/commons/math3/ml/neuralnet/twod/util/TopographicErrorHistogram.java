/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.twod.util;

import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;
import org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization;
import org.apache.commons.math3.util.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TopographicErrorHistogram
implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean relativeCount;

    public TopographicErrorHistogram(boolean relativeCount, DistanceMeasure distance) {
        this.relativeCount = relativeCount;
        this.distance = distance;
    }

    @Override
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        Network net = map.getNetwork();
        LocationFinder finder = new LocationFinder(map);
        int[][] hit = new int[nR][nC];
        double[][] error = new double[nR][nC];
        for (double[] sample : data) {
            Pair<Neuron, Neuron> p = MapUtils.findBestAndSecondBest(sample, map, this.distance);
            Neuron best = p.getFirst();
            LocationFinder.Location loc = finder.getLocation(best);
            int row = loc.getRow();
            int col = loc.getColumn();
            int[] nArray = hit[row];
            int n = col;
            nArray[n] = nArray[n] + 1;
            if (net.getNeighbours(best).contains(p.getSecond())) continue;
            double[] dArray = error[row];
            int n2 = col;
            dArray[n2] = dArray[n2] + 1.0;
        }
        if (this.relativeCount) {
            for (int r = 0; r < nR; ++r) {
                for (int c = 0; c < nC; ++c) {
                    double[] dArray = error[r];
                    int n = c;
                    dArray[n] = dArray[n] / (double)hit[r][c];
                }
            }
        }
        return error;
    }
}

