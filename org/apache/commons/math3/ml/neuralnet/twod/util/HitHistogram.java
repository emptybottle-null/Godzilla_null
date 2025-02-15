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
public class HitHistogram
implements MapDataVisualization {
    private final DistanceMeasure distance;
    private final boolean normalizeCount;

    public HitHistogram(boolean normalizeCount, DistanceMeasure distance) {
        this.normalizeCount = normalizeCount;
        this.distance = distance;
    }

    @Override
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        LocationFinder finder = new LocationFinder(map);
        int numSamples = 0;
        double[][] hit = new double[nR][nC];
        for (double[] sample : data) {
            Neuron best = MapUtils.findBest(sample, map, this.distance);
            LocationFinder.Location loc = finder.getLocation(best);
            int row = loc.getRow();
            int col = loc.getColumn();
            double[] dArray = hit[row];
            int n = col;
            dArray[n] = dArray[n] + 1.0;
            ++numSamples;
        }
        if (this.normalizeCount) {
            for (int r = 0; r < nR; ++r) {
                int c = 0;
                while (c < nC) {
                    double[] dArray = hit[r];
                    int n = c++;
                    dArray[n] = dArray[n] / (double)numSamples;
                }
            }
        }
        return hit;
    }
}

