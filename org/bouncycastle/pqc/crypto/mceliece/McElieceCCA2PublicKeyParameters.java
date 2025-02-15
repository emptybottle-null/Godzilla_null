/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKeyParameters
extends McElieceCCA2KeyParameters {
    private int n;
    private int t;
    private GF2Matrix matrixG;

    public McElieceCCA2PublicKeyParameters(int n, int n2, GF2Matrix gF2Matrix, String string) {
        super(false, string);
        this.n = n;
        this.t = n2;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public int getN() {
        return this.n;
    }

    public int getT() {
        return this.t;
    }

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }
}

