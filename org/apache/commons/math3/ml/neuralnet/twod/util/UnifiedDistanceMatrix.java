/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.util.Collection;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;
import org.apache.commons.math3.ml.neuralnet.twod.util.MapVisualization;

public class UnifiedDistanceMatrix
implements MapVisualization {
    private final boolean individualDistances;
    private final DistanceMeasure distance;

    public UnifiedDistanceMatrix(boolean individualDistances, DistanceMeasure distance) {
        this.individualDistances = individualDistances;
        this.distance = distance;
    }

    public double[][] computeImage(NeuronSquareMesh2D map) {
        if (this.individualDistances) {
            return this.individualDistances(map);
        }
        return this.averageDistances(map);
    }

    private double[][] individualDistances(NeuronSquareMesh2D map) {
        Object current;
        int jR;
        int j;
        int iR;
        int i;
        int numRows = map.getNumberOfRows();
        int numCols = map.getNumberOfColumns();
        double[][] uMatrix = new double[numRows * 2 + 1][numCols * 2 + 1];
        for (i = 0; i < numRows; ++i) {
            iR = 2 * i + 1;
            for (j = 0; j < numCols; ++j) {
                jR = 2 * j + 1;
                current = map.getNeuron(i, j).getFeatures();
                Neuron neighbour = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                if (neighbour != null) {
                    uMatrix[iR][jR + 1] = this.distance.compute((double[])current, neighbour.getFeatures());
                }
                if ((neighbour = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN)) == null) continue;
                uMatrix[iR + 1][jR] = this.distance.compute((double[])current, neighbour.getFeatures());
            }
        }
        for (i = 0; i < numRows; ++i) {
            iR = 2 * i + 1;
            for (j = 0; j < numCols; ++j) {
                jR = 2 * j + 1;
                current = map.getNeuron(i, j);
                Neuron right = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.CENTER);
                Neuron bottom = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.CENTER, NeuronSquareMesh2D.VerticalDirection.DOWN);
                Neuron bottomRight = map.getNeuron(i, j, NeuronSquareMesh2D.HorizontalDirection.RIGHT, NeuronSquareMesh2D.VerticalDirection.DOWN);
                double current2BottomRight = bottomRight == null ? 0.0 : this.distance.compute(((Neuron)current).getFeatures(), bottomRight.getFeatures());
                double right2Bottom = right == null || bottom == null ? 0.0 : this.distance.compute(right.getFeatures(), bottom.getFeatures());
                uMatrix[iR + 1][jR + 1] = 0.5 * (current2BottomRight + right2Bottom);
            }
        }
        int lastRow = uMatrix.length - 1;
        uMatrix[0] = uMatrix[lastRow];
        int lastCol = uMatrix[0].length - 1;
        for (int r = 0; r < lastRow; ++r) {
            uMatrix[r][0] = uMatrix[r][lastCol];
        }
        return uMatrix;
    }

    private double[][] averageDistances(NeuronSquareMesh2D map) {
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
                    d += this.distance.compute(features, n.getFeatures());
                }
                uMatrix[i][j] = d / (double)count;
            }
        }
        return uMatrix;
    }
}

