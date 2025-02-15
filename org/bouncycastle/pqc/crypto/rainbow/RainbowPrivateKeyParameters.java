/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters;

public class RainbowPrivateKeyParameters
extends RainbowKeyParameters {
    private short[][] A1inv;
    private short[] b1;
    private short[][] A2inv;
    private short[] b2;
    private int[] vi;
    private Layer[] layers;

    public RainbowPrivateKeyParameters(short[][] sArray, short[] sArray2, short[][] sArray3, short[] sArray4, int[] nArray, Layer[] layerArray) {
        super(true, nArray[nArray.length - 1] - nArray[0]);
        this.A1inv = sArray;
        this.b1 = sArray2;
        this.A2inv = sArray3;
        this.b2 = sArray4;
        this.vi = nArray;
        this.layers = layerArray;
    }

    public short[] getB1() {
        return this.b1;
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[] getB2() {
        return this.b2;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.vi;
    }
}

