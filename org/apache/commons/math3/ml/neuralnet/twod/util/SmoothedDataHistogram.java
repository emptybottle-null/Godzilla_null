/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.twod.util;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.MapUtils;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.LocationFinder;
import org.apache.commons.math3.ml.neuralnet.twod.util.MapDataVisualization;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SmoothedDataHistogram
implements MapDataVisualization {
    private final int smoothingBins;
    private final DistanceMeasure distance;
    private final double membershipNormalization;

    public SmoothedDataHistogram(int smoothingBins, DistanceMeasure distance) {
        this.smoothingBins = smoothingBins;
        this.distance = distance;
        double sum = 0.0;
        for (int i = 0; i < smoothingBins; ++i) {
            sum += (double)(smoothingBins - i);
        }
        this.membershipNormalization = 1.0 / sum;
    }

    @Override
    public double[][] computeImage(NeuronSquareMesh2D map, Iterable<double[]> data) {
        int nC;
        int nR = map.getNumberOfRows();
        int mapSize = nR * (nC = map.getNumberOfColumns());
        if (mapSize < this.smoothingBins) {
            throw new NumberIsTooSmallException(mapSize, (Number)this.smoothingBins, true);
        }
        LocationFinder finder = new LocationFinder(map);
        double[][] histo = new double[nR][nC];
        for (double[] sample : data) {
            Neuron[] sorted = MapUtils.sort(sample, map.getNetwork(), this.distance);
            for (int i = 0; i < this.smoothingBins; ++i) {
                LocationFinder.Location loc = finder.getLocation(sorted[i]);
                int row = loc.getRow();
                int col = loc.getColumn();
                double[] dArray = histo[row];
                int n = col;
                dArray[n] = dArray[n] + (double)(this.smoothingBins - i) * this.membershipNormalization;
            }
        }
        return histo;
    }
}

