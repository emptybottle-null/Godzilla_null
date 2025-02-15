/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.twod;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.Neuron;
import org.apache.commons.math3.ml.neuralnet.SquareNeighbourhood;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class NeuronSquareMesh2D
implements Iterable<Neuron>,
Serializable {
    private static final long serialVersionUID = 1L;
    private final Network network;
    private final int numberOfRows;
    private final int numberOfColumns;
    private final boolean wrapRows;
    private final boolean wrapColumns;
    private final SquareNeighbourhood neighbourhood;
    private final long[][] identifiers;

    NeuronSquareMesh2D(boolean wrapRowDim, boolean wrapColDim, SquareNeighbourhood neighbourhoodType, double[][][] featuresList) {
        this.numberOfRows = featuresList.length;
        this.numberOfColumns = featuresList[0].length;
        if (this.numberOfRows < 2) {
            throw new NumberIsTooSmallException(this.numberOfRows, (Number)2, true);
        }
        if (this.numberOfColumns < 2) {
            throw new NumberIsTooSmallException(this.numberOfColumns, (Number)2, true);
        }
        this.wrapRows = wrapRowDim;
        this.wrapColumns = wrapColDim;
        this.neighbourhood = neighbourhoodType;
        int fLen = featuresList[0][0].length;
        this.network = new Network(0L, fLen);
        this.identifiers = new long[this.numberOfRows][this.numberOfColumns];
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                this.identifiers[i][j] = this.network.createNeuron(featuresList[i][j]);
            }
        }
        this.createLinks();
    }

    public NeuronSquareMesh2D(int numRows, boolean wrapRowDim, int numCols, boolean wrapColDim, SquareNeighbourhood neighbourhoodType, FeatureInitializer[] featureInit) {
        if (numRows < 2) {
            throw new NumberIsTooSmallException(numRows, (Number)2, true);
        }
        if (numCols < 2) {
            throw new NumberIsTooSmallException(numCols, (Number)2, true);
        }
        this.numberOfRows = numRows;
        this.wrapRows = wrapRowDim;
        this.numberOfColumns = numCols;
        this.wrapColumns = wrapColDim;
        this.neighbourhood = neighbourhoodType;
        this.identifiers = new long[this.numberOfRows][this.numberOfColumns];
        int fLen = featureInit.length;
        this.network = new Network(0L, fLen);
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                double[] features = new double[fLen];
                for (int fIndex = 0; fIndex < fLen; ++fIndex) {
                    features[fIndex] = featureInit[fIndex].value();
                }
                this.identifiers[i][j] = this.network.createNeuron(features);
            }
        }
        this.createLinks();
    }

    private NeuronSquareMesh2D(boolean wrapRowDim, boolean wrapColDim, SquareNeighbourhood neighbourhoodType, Network net, long[][] idGrid) {
        this.numberOfRows = idGrid.length;
        this.numberOfColumns = idGrid[0].length;
        this.wrapRows = wrapRowDim;
        this.wrapColumns = wrapColDim;
        this.neighbourhood = neighbourhoodType;
        this.network = net;
        this.identifiers = idGrid;
    }

    public synchronized NeuronSquareMesh2D copy() {
        long[][] idGrid = new long[this.numberOfRows][this.numberOfColumns];
        for (int r = 0; r < this.numberOfRows; ++r) {
            for (int c = 0; c < this.numberOfColumns; ++c) {
                idGrid[r][c] = this.identifiers[r][c];
            }
        }
        return new NeuronSquareMesh2D(this.wrapRows, this.wrapColumns, this.neighbourhood, this.network.copy(), idGrid);
    }

    @Override
    public Iterator<Neuron> iterator() {
        return this.network.iterator();
    }

    public Network getNetwork() {
        return this.network;
    }

    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    public Neuron getNeuron(int i, int j) {
        if (i < 0 || i >= this.numberOfRows) {
            throw new OutOfRangeException(i, (Number)0, this.numberOfRows - 1);
        }
        if (j < 0 || j >= this.numberOfColumns) {
            throw new OutOfRangeException(j, (Number)0, this.numberOfColumns - 1);
        }
        return this.network.getNeuron(this.identifiers[i][j]);
    }

    public Neuron getNeuron(int row, int col, HorizontalDirection alongRowDir, VerticalDirection alongColDir) {
        int[] location = this.getLocation(row, col, alongRowDir, alongColDir);
        return location == null ? null : this.getNeuron(location[0], location[1]);
    }

    private int[] getLocation(int row, int col, HorizontalDirection alongRowDir, VerticalDirection alongColDir) {
        int rowOffset;
        int colOffset;
        switch (alongRowDir) {
            case LEFT: {
                colOffset = -1;
                break;
            }
            case RIGHT: {
                colOffset = 1;
                break;
            }
            case CENTER: {
                colOffset = 0;
                break;
            }
            default: {
                throw new MathInternalError();
            }
        }
        int colIndex = col + colOffset;
        if (this.wrapColumns) {
            colIndex = colIndex < 0 ? (colIndex += this.numberOfColumns) : (colIndex %= this.numberOfColumns);
        }
        switch (alongColDir) {
            case UP: {
                rowOffset = -1;
                break;
            }
            case DOWN: {
                rowOffset = 1;
                break;
            }
            case CENTER: {
                rowOffset = 0;
                break;
            }
            default: {
                throw new MathInternalError();
            }
        }
        int rowIndex = row + rowOffset;
        if (this.wrapRows) {
            rowIndex = rowIndex < 0 ? (rowIndex += this.numberOfRows) : (rowIndex %= this.numberOfRows);
        }
        if (rowIndex < 0 || rowIndex >= this.numberOfRows || colIndex < 0 || colIndex >= this.numberOfColumns) {
            return null;
        }
        return new int[]{rowIndex, colIndex};
    }

    /*
     * Unable to fully structure code
     */
    private void createLinks() {
        linkEnd = new ArrayList<Long>();
        iLast = this.numberOfRows - 1;
        jLast = this.numberOfColumns - 1;
        for (i = 0; i < this.numberOfRows; ++i) {
            for (j = 0; j < this.numberOfColumns; ++j) {
                linkEnd.clear();
                switch (1.$SwitchMap$org$apache$commons$math3$ml$neuralnet$SquareNeighbourhood[this.neighbourhood.ordinal()]) {
                    case 1: {
                        if (i > 0) {
                            if (j > 0) {
                                linkEnd.add(this.identifiers[i - 1][j - 1]);
                            }
                            if (j < jLast) {
                                linkEnd.add(this.identifiers[i - 1][j + 1]);
                            }
                        }
                        if (i < iLast) {
                            if (j > 0) {
                                linkEnd.add(this.identifiers[i + 1][j - 1]);
                            }
                            if (j < jLast) {
                                linkEnd.add(this.identifiers[i + 1][j + 1]);
                            }
                        }
                        if (this.wrapRows) {
                            if (i == 0) {
                                if (j > 0) {
                                    linkEnd.add(this.identifiers[iLast][j - 1]);
                                }
                                if (j < jLast) {
                                    linkEnd.add(this.identifiers[iLast][j + 1]);
                                }
                            } else if (i == iLast) {
                                if (j > 0) {
                                    linkEnd.add(this.identifiers[0][j - 1]);
                                }
                                if (j < jLast) {
                                    linkEnd.add(this.identifiers[0][j + 1]);
                                }
                            }
                        }
                        if (this.wrapColumns) {
                            if (j == 0) {
                                if (i > 0) {
                                    linkEnd.add(this.identifiers[i - 1][jLast]);
                                }
                                if (i < iLast) {
                                    linkEnd.add(this.identifiers[i + 1][jLast]);
                                }
                            } else if (j == jLast) {
                                if (i > 0) {
                                    linkEnd.add(this.identifiers[i - 1][0]);
                                }
                                if (i < iLast) {
                                    linkEnd.add(this.identifiers[i + 1][0]);
                                }
                            }
                        }
                        if (!this.wrapRows || !this.wrapColumns) ** GOTO lbl71
                        if (i != 0 || j != 0) ** GOTO lbl60
                        linkEnd.add(this.identifiers[iLast][jLast]);
                        ** GOTO lbl71
lbl60:
                        // 1 sources

                        if (i != 0 || j != jLast) ** GOTO lbl64
                        linkEnd.add(this.identifiers[iLast][0]);
                        ** GOTO lbl71
lbl64:
                        // 1 sources

                        if (i != iLast || j != 0) ** GOTO lbl68
                        linkEnd.add(this.identifiers[0][jLast]);
                        ** GOTO lbl71
lbl68:
                        // 1 sources

                        if (i == iLast && j == jLast) {
                            linkEnd.add(this.identifiers[0][0]);
                        }
                    }
lbl71:
                    // 8 sources

                    case 2: {
                        if (i > 0) {
                            linkEnd.add(this.identifiers[i - 1][j]);
                        }
                        if (i < iLast) {
                            linkEnd.add(this.identifiers[i + 1][j]);
                        }
                        if (this.wrapRows) {
                            if (i == 0) {
                                linkEnd.add(this.identifiers[iLast][j]);
                            } else if (i == iLast) {
                                linkEnd.add(this.identifiers[0][j]);
                            }
                        }
                        if (j > 0) {
                            linkEnd.add(this.identifiers[i][j - 1]);
                        }
                        if (j < jLast) {
                            linkEnd.add(this.identifiers[i][j + 1]);
                        }
                        if (!this.wrapColumns) break;
                        if (j == 0) {
                            linkEnd.add(this.identifiers[i][jLast]);
                            break;
                        }
                        if (j != jLast) break;
                        linkEnd.add(this.identifiers[i][0]);
                        break;
                    }
                    default: {
                        throw new MathInternalError();
                    }
                }
                aNeuron = this.network.getNeuron(this.identifiers[i][j]);
                i$ = linkEnd.iterator();
                while (i$.hasNext()) {
                    b = (Long)i$.next();
                    bNeuron = this.network.getNeuron(b);
                    this.network.addLink(aNeuron, bNeuron);
                }
            }
        }
    }

    private void readObject(ObjectInputStream in) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        double[][][] featuresList = new double[this.numberOfRows][this.numberOfColumns][];
        for (int i = 0; i < this.numberOfRows; ++i) {
            for (int j = 0; j < this.numberOfColumns; ++j) {
                featuresList[i][j] = this.getNeuron(i, j).getFeatures();
            }
        }
        return new SerializationProxy(this.wrapRows, this.wrapColumns, this.neighbourhood, featuresList);
    }

    private static class SerializationProxy
    implements Serializable {
        private static final long serialVersionUID = 20130226L;
        private final boolean wrapRows;
        private final boolean wrapColumns;
        private final SquareNeighbourhood neighbourhood;
        private final double[][][] featuresList;

        SerializationProxy(boolean wrapRows, boolean wrapColumns, SquareNeighbourhood neighbourhood, double[][][] featuresList) {
            this.wrapRows = wrapRows;
            this.wrapColumns = wrapColumns;
            this.neighbourhood = neighbourhood;
            this.featuresList = featuresList;
        }

        private Object readResolve() {
            return new NeuronSquareMesh2D(this.wrapRows, this.wrapColumns, this.neighbourhood, this.featuresList);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum VerticalDirection {
        UP,
        CENTER,
        DOWN;

    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static enum HorizontalDirection {
        RIGHT,
        CENTER,
        LEFT;

    }
}

