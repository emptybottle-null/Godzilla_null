/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

public class GOST3410ValidationParameters {
    private int x0;
    private int c;
    private long x0L;
    private long cL;

    public GOST3410ValidationParameters(int n, int n2) {
        this.x0 = n;
        this.c = n2;
    }

    public GOST3410ValidationParameters(long l, long l2) {
        this.x0L = l;
        this.cL = l2;
    }

    public int getC() {
        return this.c;
    }

    public int getX0() {
        return this.x0;
    }

    public long getCL() {
        return this.cL;
    }

    public long getX0L() {
        return this.x0L;
    }

    public boolean equals(Object object) {
        if (!(object instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters)object;
        if (gOST3410ValidationParameters.c != this.c) {
            return false;
        }
        if (gOST3410ValidationParameters.x0 != this.x0) {
            return false;
        }
        if (gOST3410ValidationParameters.cL != this.cL) {
            return false;
        }
        return gOST3410ValidationParameters.x0L == this.x0L;
    }

    public int hashCode() {
        return this.x0 ^ this.c ^ (int)this.x0L ^ (int)(this.x0L >> 32) ^ (int)this.cL ^ (int)(this.cL >> 32);
    }
}

