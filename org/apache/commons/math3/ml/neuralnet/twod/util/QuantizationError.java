/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.twod.util;

import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;
import org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class QuantizationError
implements MapDataVisualization {
    private final DistanceMeasure distance;

    public QuantizationError(DistanceMeasure distance) {
        this.distance = distance;
    }

    @Override
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        LocationFinder finder = new LocationFinder(map);
        int[][] hit = new int[nR][nC];
        double[][] error = new double[nR][nC];
        for (double[] sample : data) {
            Neuron best = MapUtils.findBest(sample, map, this.distance);
            LocationFinder.Location loc = finder.getLocation(best);
            int row = loc.getRow();
            int col = loc.getColumn();
            int[] nArray = hit[row];
            int n = col;
            nArray[n] = nArray[n] + 1;
            double[] dArray = error[row];
            int n2 = col;
            dArray[n2] = dArray[n2] + this.distance.compute(sample, best.getFeatures());
        }
        for (int r = 0; r < nR; ++r) {
            for (int c = 0; c < nC; ++c) {
                int count = hit[r][c];
                if (count == 0) continue;
                double[] dArray = error[r];
                int n = c;
                dArray[n] = dArray[n] / (double)count;
            }
        }
        return error;
    }
}

