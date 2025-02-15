/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public class GF2mVector
extends Vector {
    private GF2mField field;
    private int[] vector;

    public GF2mVector(GF2mField gF2mField, byte[] byArray) {
        int n;
        this.field = new GF2mField(gF2mField);
        int n2 = 1;
        for (n = 8; gF2mField.getDegree() > n; n += 8) {
            ++n2;
        }
        if (byArray.length % n2 != 0) {
            throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
        }
        this.length = byArray.length / n2;
        this.vector = new int[this.length];
        n2 = 0;
        for (int i = 0; i < this.vector.length; ++i) {
            for (int j = 0; j < n; j += 8) {
                int n3 = i;
                this.vector[n3] = this.vector[n3] | (byArray[n2++] & 0xFF) << j;
            }
            if (gF2mField.isElementOfThisField(this.vector[i])) continue;
            throw new IllegalArgumentException("Byte array is not an encoded vector over the given finite field.");
        }
    }

    public GF2mVector(GF2mField gF2mField, int[] nArray) {
        this.field = gF2mField;
        this.length = nArray.length;
        for (int i = nArray.length - 1; i >= 0; --i) {
            if (gF2mField.isElementOfThisField(nArray[i])) continue;
            throw new ArithmeticException("Element array is not specified over the given finite field.");
        }
        this.vector = IntUtils.clone(nArray);
    }

    public GF2mVector(GF2mVector gF2mVector) {
        this.field = new GF2mField(gF2mVector.field);
        this.length = gF2mVector.length;
        this.vector = IntUtils.clone(gF2mVector.vector);
    }

    public GF2mField getField() {
        return this.field;
    }

    public int[] getIntArrayForm() {
        return IntUtils.clone(this.vector);
    }

    public byte[] getEncoded() {
        int n;
        int n2 = 1;
        for (n = 8; this.field.getDegree() > n; n += 8) {
            ++n2;
        }
        byte[] byArray = new byte[this.vector.length * n2];
        n2 = 0;
        for (int i = 0; i < this.vector.length; ++i) {
            for (int j = 0; j < n; j += 8) {
                byArray[n2++] = (byte)(this.vector[i] >>> j);
            }
        }
        return byArray;
    }

    public boolean isZero() {
        for (int i = this.vector.length - 1; i >= 0; --i) {
            if (this.vector[i] == 0) continue;
            return false;
        }
        return true;
    }

    public Vector add(Vector vector) {
        throw new RuntimeException("not implemented");
    }

    public Vector multiply(Permutation permutation) {
        int[] nArray = permutation.getVector();
        if (this.length != nArray.length) {
            throw new ArithmeticException("permutation size and vector size mismatch");
        }
        int[] nArray2 = new int[this.length];
        for (int i = 0; i < nArray.length; ++i) {
            nArray2[i] = this.vector[nArray[i]];
        }
        return new GF2mVector(this.field, nArray2);
    }

    public boolean equals(Object object) {
        if (!(object instanceof GF2mVector)) {
            return false;
        }
        GF2mVector gF2mVector = (GF2mVector)object;
        if (!this.field.equals(gF2mVector.field)) {
            return false;
        }
        return IntUtils.equals(this.vector, gF2mVector.vector);
    }

    public int hashCode() {
        int n = this.field.hashCode();
        n = n * 31 + this.vector.hashCode();
        return n;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.vector.length; ++i) {
            for (int j = 0; j < this.field.getDegree(); ++j) {
                int n = j & 0x1F;
                int n2 = 1 << n;
                int n3 = this.vector[i] & n2;
                if (n3 != 0) {
                    stringBuffer.append('1');
                    continue;
                }
                stringBuffer.append('0');
            }
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }
}

