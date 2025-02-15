/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.GF2mVector;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.bouncycastle.pqc.math.linearalgebra.LittleEndianConversions;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.RandUtils;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public class GF2Vector
extends Vector {
    private int[] v;

    public GF2Vector(int n) {
        if (n < 0) {
            throw new ArithmeticException("Negative length.");
        }
        this.length = n;
        this.v = new int[n + 31 >> 5];
    }

    public GF2Vector(int n, SecureRandom secureRandom) {
        int n2;
        this.length = n;
        int n3 = n + 31 >> 5;
        this.v = new int[n3];
        for (n2 = n3 - 1; n2 >= 0; --n2) {
            this.v[n2] = secureRandom.nextInt();
        }
        n2 = n & 0x1F;
        if (n2 != 0) {
            int n4 = n3 - 1;
            this.v[n4] = this.v[n4] & (1 << n2) - 1;
        }
    }

    public GF2Vector(int n, int n2, SecureRandom secureRandom) {
        int n3;
        if (n2 > n) {
            throw new ArithmeticException("The hamming weight is greater than the length of vector.");
        }
        this.length = n;
        int n4 = n + 31 >> 5;
        this.v = new int[n4];
        int[] nArray = new int[n];
        for (n3 = 0; n3 < n; ++n3) {
            nArray[n3] = n3;
        }
        n3 = n;
        for (int i = 0; i < n2; ++i) {
            int n5 = RandUtils.nextInt(secureRandom, n3);
            this.setBit(nArray[n5]);
            nArray[n5] = nArray[--n3];
        }
    }

    public GF2Vector(int n, int[] nArray) {
        if (n < 0) {
            throw new ArithmeticException("negative length");
        }
        this.length = n;
        int n2 = n + 31 >> 5;
        if (nArray.length != n2) {
            throw new ArithmeticException("length mismatch");
        }
        this.v = IntUtils.clone(nArray);
        int n3 = n & 0x1F;
        if (n3 != 0) {
            int n4 = n2 - 1;
            this.v[n4] = this.v[n4] & (1 << n3) - 1;
        }
    }

    public GF2Vector(GF2Vector gF2Vector) {
        this.length = gF2Vector.length;
        this.v = IntUtils.clone(gF2Vector.v);
    }

    protected GF2Vector(int[] nArray, int n) {
        this.v = nArray;
        this.length = n;
    }

    public static GF2Vector OS2VP(int n, byte[] byArray) {
        if (n < 0) {
            throw new ArithmeticException("negative length");
        }
        int n2 = n + 7 >> 3;
        if (byArray.length > n2) {
            throw new ArithmeticException("length mismatch");
        }
        return new GF2Vector(n, LittleEndianConversions.toIntArray(byArray));
    }

    public byte[] getEncoded() {
        int n = this.length + 7 >> 3;
        return LittleEndianConversions.toByteArray(this.v, n);
    }

    public int[] getVecArray() {
        return this.v;
    }

    public int getHammingWeight() {
        int n = 0;
        for (int i = 0; i < this.v.length; ++i) {
            int n2 = this.v[i];
            for (int j = 0; j < 32; ++j) {
                int n3 = n2 & 1;
                if (n3 != 0) {
                    ++n;
                }
                n2 >>>= 1;
            }
        }
        return n;
    }

    public boolean isZero() {
        for (int i = this.v.length - 1; i >= 0; --i) {
            if (this.v[i] == 0) continue;
            return false;
        }
        return true;
    }

    public int getBit(int n) {
        if (n >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        int n2 = n >> 5;
        int n3 = n & 0x1F;
        return (this.v[n2] & 1 << n3) >>> n3;
    }

    public void setBit(int n) {
        if (n >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        int n2 = n >> 5;
        this.v[n2] = this.v[n2] | 1 << (n & 0x1F);
    }

    public Vector add(Vector vector) {
        if (!(vector instanceof GF2Vector)) {
            throw new ArithmeticException("vector is not defined over GF(2)");
        }
        GF2Vector gF2Vector = (GF2Vector)vector;
        if (this.length != gF2Vector.length) {
            throw new ArithmeticException("length mismatch");
        }
        int[] nArray = IntUtils.clone(((GF2Vector)vector).v);
        for (int i = nArray.length - 1; i >= 0; --i) {
            int n = i;
            nArray[n] = nArray[n] ^ this.v[i];
        }
        return new GF2Vector(this.length, nArray);
    }

    public Vector multiply(Permutation permutation) {
        int[] nArray = permutation.getVector();
        if (this.length != nArray.length) {
            throw new ArithmeticException("length mismatch");
        }
        GF2Vector gF2Vector = new GF2Vector(this.length);
        for (int i = 0; i < nArray.length; ++i) {
            int n = this.v[nArray[i] >> 5] & 1 << (nArray[i] & 0x1F);
            if (n == 0) continue;
            int n2 = i >> 5;
            gF2Vector.v[n2] = gF2Vector.v[n2] | 1 << (i & 0x1F);
        }
        return gF2Vector;
    }

    public GF2Vector extractVector(int[] nArray) {
        int n = nArray.length;
        if (nArray[n - 1] > this.length) {
            throw new ArithmeticException("invalid index set");
        }
        GF2Vector gF2Vector = new GF2Vector(n);
        for (int i = 0; i < n; ++i) {
            int n2 = this.v[nArray[i] >> 5] & 1 << (nArray[i] & 0x1F);
            if (n2 == 0) continue;
            int n3 = i >> 5;
            gF2Vector.v[n3] = gF2Vector.v[n3] | 1 << (i & 0x1F);
        }
        return gF2Vector;
    }

    public GF2Vector extractLeftVector(int n) {
        if (n > this.length) {
            throw new ArithmeticException("invalid length");
        }
        if (n == this.length) {
            return new GF2Vector(this);
        }
        GF2Vector gF2Vector = new GF2Vector(n);
        int n2 = n >> 5;
        int n3 = n & 0x1F;
        System.arraycopy(this.v, 0, gF2Vector.v, 0, n2);
        if (n3 != 0) {
            gF2Vector.v[n2] = this.v[n2] & (1 << n3) - 1;
        }
        return gF2Vector;
    }

    public GF2Vector extractRightVector(int n) {
        if (n > this.length) {
            throw new ArithmeticException("invalid length");
        }
        if (n == this.length) {
            return new GF2Vector(this);
        }
        GF2Vector gF2Vector = new GF2Vector(n);
        int n2 = this.length - n >> 5;
        int n3 = this.length - n & 0x1F;
        int n4 = n + 31 >> 5;
        int n5 = n2;
        if (n3 != 0) {
            for (int i = 0; i < n4 - 1; ++i) {
                gF2Vector.v[i] = this.v[n5++] >>> n3 | this.v[n5] << 32 - n3;
            }
            gF2Vector.v[n4 - 1] = this.v[n5++] >>> n3;
            if (n5 < this.v.length) {
                int n6 = n4 - 1;
                gF2Vector.v[n6] = gF2Vector.v[n6] | this.v[n5] << 32 - n3;
            }
        } else {
            System.arraycopy(this.v, n2, gF2Vector.v, 0, n4);
        }
        return gF2Vector;
    }

    public GF2mVector toExtensionFieldVector(GF2mField gF2mField) {
        int n = gF2mField.getDegree();
        if (this.length % n != 0) {
            throw new ArithmeticException("conversion is impossible");
        }
        int n2 = this.length / n;
        int[] nArray = new int[n2];
        int n3 = 0;
        for (int i = n2 - 1; i >= 0; --i) {
            for (int j = gF2mField.getDegree() - 1; j >= 0; --j) {
                int n4 = n3 >>> 5;
                int n5 = n3 & 0x1F;
                int n6 = this.v[n4] >>> n5 & 1;
                if (n6 == 1) {
                    int n7 = i;
                    nArray[n7] = nArray[n7] ^ 1 << j;
                }
                ++n3;
            }
        }
        return new GF2mVector(gF2mField, nArray);
    }

    public boolean equals(Object object) {
        if (!(object instanceof GF2Vector)) {
            return false;
        }
        GF2Vector gF2Vector = (GF2Vector)object;
        return this.length == gF2Vector.length && IntUtils.equals(this.v, gF2Vector.v);
    }

    public int hashCode() {
        int n = this.length;
        n = n * 31 + this.v.hashCode();
        return n;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.length; ++i) {
            int n;
            int n2;
            int n3;
            if (i != 0 && (i & 0x1F) == 0) {
                stringBuffer.append(' ');
            }
            if ((n3 = this.v[n2 = i >> 5] & 1 << (n = i & 0x1F)) == 0) {
                stringBuffer.append('0');
                continue;
            }
            stringBuffer.append('1');
        }
        return stringBuffer.toString();
    }
}

