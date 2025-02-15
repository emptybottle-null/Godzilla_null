/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.oned;

import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.FeatureInitializer;
import org.apache.commons.math3.ml.neuralnet.Network;

public class NeuronString
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Network network;
    private final int size;
    private final boolean wrap;
    private final long[] identifiers;

    NeuronString(boolean wrap, double[][] featuresList) {
        this.size = featuresList.length;
        if (this.size < 2) {
            throw new NumberIsTooSmallException(this.size, (Number)2, true);
        }
        this.wrap = wrap;
        int fLen = featuresList[0].length;
        this.network = new Network(0L, fLen);
        this.identifiers = new long[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.identifiers[i] = this.network.createNeuron(featuresList[i]);
        }
        this.createLinks();
    }

    public NeuronString(int num, boolean wrap, FeatureInitializer[] featureInit) {
        if (num < 2) {
            throw new NumberIsTooSmallException(num, (Number)2, true);
        }
        this.size = num;
        this.wrap = wrap;
        this.identifiers = new long[num];
        int fLen = featureInit.length;
        this.network = new Network(0L, fLen);
        for (int i = 0; i < num; ++i) {
            double[] features = new double[fLen];
            for (int fIndex = 0; fIndex < fLen; ++fIndex) {
                features[fIndex] = featureInit[fIndex].value();
            }
            this.identifiers[i] = this.network.createNeuron(features);
        }
        this.createLinks();
    }

    public Network getNetwork() {
        return this.network;
    }

    public int getSize() {
        return this.size;
    }

    public double[] getFeatures(int i) {
        if (i < 0 || i >= this.size) {
            throw new OutOfRangeException(i, (Number)0, this.size - 1);
        }
        return this.network.getNeuron(this.identifiers[i]).getFeatures();
    }

    private void createLinks() {
        int i;
        for (i = 0; i < this.size - 1; ++i) {
            this.network.addLink(this.network.getNeuron(i), this.network.getNeuron(i + 1));
        }
        for (i = this.size - 1; i > 0; --i) {
            this.network.addLink(this.network.getNeuron(i), this.network.getNeuron(i - 1));
        }
        if (this.wrap) {
            this.network.addLink(this.network.getNeuron(0L), this.network.getNeuron(this.size - 1));
            this.network.addLink(this.network.getNeuron(this.size - 1), this.network.getNeuron(0L));
        }
    }

    private void readObject(ObjectInputStream in) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        double[][] featuresList = new double[this.size][];
        for (int i = 0; i < this.size; ++i) {
            featuresList[i] = this.getFeatures(i);
        }
        return new SerializationProxy(this.wrap, featuresList);
    }

    private static class SerializationProxy
    implements Serializable {
        private static final long serialVersionUID = 20130226L;
        private final boolean wrap;
        private final double[][] featuresList;

        SerializationProxy(boolean wrap, double[][] featuresList) {
            this.wrap = wrap;
            this.featuresList = featuresList;
        }

        private Object readResolve() {
            return new NeuronString(this.wrap, this.featuresList);
        }
    }
}

