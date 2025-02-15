/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.crypto.mceliece.McElieceKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;

public class McEliecePublicKeyParameters
extends McElieceKeyParameters {
    private int n;
    private int t;
    private GF2Matrix g;

    public McEliecePublicKeyParameters(int n, int n2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.n = n;
        this.t = n2;
        this.g = new GF2Matrix(gF2Matrix);
    }

    public int getN() {
        return this.n;
    }

    public int getT() {
        return this.t;
    }

    public GF2Matrix getG() {
        return this.g;
    }

    public int getK() {
        return this.g.getNumRows();
    }
}

