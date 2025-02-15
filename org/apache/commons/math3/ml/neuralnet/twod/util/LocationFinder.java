/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.twod.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.twod.NeuronSquareMesh2D;

public class LocationFinder {
    private final Map<Long, Location> locations = new HashMap<Long, Location>();

    public LocationFinder(NeuronSquareMesh2D map) {
        int nR = map.getNumberOfRows();
        int nC = map.getNumberOfColumns();
        for (int r = 0; r < nR; ++r) {
            for (int c = 0; c < nC; ++c) {
                Long id = map.getNeuron(r, c).getIdentifier();
                if (this.locations.get(id) != null) {
                    throw new MathIllegalStateException();
                }
                this.locations.put(id, new Location(r, c));
            }
        }
    }

    public Location getLocation(Neuron n) {
        return this.locations.get(n.getIdentifier());
    }

    public static class Location {
        private final int row;
        private final int column;

        public Location(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }
    }
}

