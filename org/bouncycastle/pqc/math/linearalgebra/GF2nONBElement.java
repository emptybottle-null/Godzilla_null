/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.pqc.math.linearalgebra.GF2nElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBField;
import org.bouncycastle.pqc.math.linearalgebra.GFElement;

public class GF2nONBElement
extends GF2nElement {
    private static final long[] mBitmask = new long[]{1L, 2L, 4L, 8L, 16L, 32L, 64L, 128L, 256L, 512L, 1024L, 2048L, 4096L, 8192L, 16384L, 32768L, 65536L, 131072L, 262144L, 524288L, 0x100000L, 0x200000L, 0x400000L, 0x800000L, 0x1000000L, 0x2000000L, 0x4000000L, 0x8000000L, 0x10000000L, 0x20000000L, 0x40000000L, 0x80000000L, 0x100000000L, 0x200000000L, 0x400000000L, 0x800000000L, 0x1000000000L, 0x2000000000L, 0x4000000000L, 0x8000000000L, 0x10000000000L, 0x20000000000L, 0x40000000000L, 0x80000000000L, 0x100000000000L, 0x200000000000L, 0x400000000000L, 0x800000000000L, 0x1000000000000L, 0x2000000000000L, 0x4000000000000L, 0x8000000000000L, 0x10000000000000L, 0x20000000000000L, 0x40000000000000L, 0x80000000000000L, 0x100000000000000L, 0x200000000000000L, 0x400000000000000L, 0x800000000000000L, 0x1000000000000000L, 0x2000000000000000L, 0x4000000000000000L, Long.MIN_VALUE};
    private static final long[] mMaxmask = new long[]{1L, 3L, 7L, 15L, 31L, 63L, 127L, 255L, 511L, 1023L, 2047L, 4095L, 8191L, 16383L, 32767L, 65535L, 131071L, 262143L, 524287L, 1048575L, 0x1FFFFFL, 0x3FFFFFL, 0x7FFFFFL, 0xFFFFFFL, 0x1FFFFFFL, 0x3FFFFFFL, 0x7FFFFFFL, 0xFFFFFFFL, 0x1FFFFFFFL, 0x3FFFFFFFL, Integer.MAX_VALUE, 0xFFFFFFFFL, 0x1FFFFFFFFL, 0x3FFFFFFFFL, 0x7FFFFFFFFL, 0xFFFFFFFFFL, 0x1FFFFFFFFFL, 0x3FFFFFFFFFL, 0x7FFFFFFFFFL, 0xFFFFFFFFFFL, 0x1FFFFFFFFFFL, 0x3FFFFFFFFFFL, 0x7FFFFFFFFFFL, 0xFFFFFFFFFFFL, 0x1FFFFFFFFFFFL, 0x3FFFFFFFFFFFL, 0x7FFFFFFFFFFFL, 0xFFFFFFFFFFFFL, 0x1FFFFFFFFFFFFL, 0x3FFFFFFFFFFFFL, 0x7FFFFFFFFFFFFL, 0xFFFFFFFFFFFFFL, 0x1FFFFFFFFFFFFFL, 0x3FFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFL, 0x1FFFFFFFFFFFFFFL, 0x3FFFFFFFFFFFFFFL, 0x7FFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFL, 0x1FFFFFFFFFFFFFFFL, 0x3FFFFFFFFFFFFFFFL, Long.MAX_VALUE, -1L};
    private static final int[] mIBY64 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    private static final int MAXLONG = 64;
    private int mLength;
    private int mBit;
    private long[] mPol;

    public GF2nONBElement(GF2nONBField gF2nONBField, SecureRandom secureRandom) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        if (this.mLength > 1) {
            for (int i = 0; i < this.mLength - 1; ++i) {
                this.mPol[i] = secureRandom.nextLong();
            }
            long l = secureRandom.nextLong();
            this.mPol[this.mLength - 1] = l >>> 64 - this.mBit;
        } else {
            this.mPol[0] = secureRandom.nextLong();
            this.mPol[0] = this.mPol[0] >>> 64 - this.mBit;
        }
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, byte[] byArray) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        this.assign(byArray);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, BigInteger bigInteger) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        this.assign(bigInteger);
    }

    private GF2nONBElement(GF2nONBField gF2nONBField, long[] lArray) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = lArray;
    }

    public GF2nONBElement(GF2nONBElement gF2nONBElement) {
        this.mField = gF2nONBElement.mField;
        this.mDegree = this.mField.getDegree();
        this.mLength = ((GF2nONBField)this.mField).getONBLength();
        this.mBit = ((GF2nONBField)this.mField).getONBBit();
        this.mPol = new long[this.mLength];
        this.assign(gF2nONBElement.getElement());
    }

    public Object clone() {
        return new GF2nONBElement(this);
    }

    public static GF2nONBElement ZERO(GF2nONBField gF2nONBField) {
        long[] lArray = new long[gF2nONBField.getONBLength()];
        return new GF2nONBElement(gF2nONBField, lArray);
    }

    public static GF2nONBElement ONE(GF2nONBField gF2nONBField) {
        int n = gF2nONBField.getONBLength();
        long[] lArray = new long[n];
        for (int i = 0; i < n - 1; ++i) {
            lArray[i] = -1L;
        }
        lArray[n - 1] = mMaxmask[gF2nONBField.getONBBit() - 1];
        return new GF2nONBElement(gF2nONBField, lArray);
    }

    void assignZero() {
        this.mPol = new long[this.mLength];
    }

    void assignOne() {
        for (int i = 0; i < this.mLength - 1; ++i) {
            this.mPol[i] = -1L;
        }
        this.mPol[this.mLength - 1] = mMaxmask[this.mBit - 1];
    }

    private void assign(BigInteger bigInteger) {
        this.assign(bigInteger.toByteArray());
    }

    private void assign(long[] lArray) {
        System.arraycopy(lArray, 0, this.mPol, 0, this.mLength);
    }

    private void assign(byte[] byArray) {
        this.mPol = new long[this.mLength];
        for (int i = 0; i < byArray.length; ++i) {
            int n = i >>> 3;
            this.mPol[n] = this.mPol[n] | ((long)byArray[byArray.length - 1 - i] & 0xFFL) << ((i & 7) << 3);
        }
    }

    public boolean isZero() {
        boolean bl = true;
        for (int i = 0; i < this.mLength && bl; ++i) {
            bl = bl && (this.mPol[i] & 0xFFFFFFFFFFFFFFFFL) == 0L;
        }
        return bl;
    }

    public boolean isOne() {
        boolean bl = true;
        for (int i = 0; i < this.mLength - 1 && bl; ++i) {
            bl = bl && (this.mPol[i] & 0xFFFFFFFFFFFFFFFFL) == -1L;
        }
        if (bl) {
            bl = bl && (this.mPol[this.mLength - 1] & mMaxmask[this.mBit - 1]) == mMaxmask[this.mBit - 1];
        }
        return bl;
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof GF2nONBElement)) {
            return false;
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement)object;
        for (int i = 0; i < this.mLength; ++i) {
            if (this.mPol[i] == gF2nONBElement.mPol[i]) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mPol.hashCode();
    }

    public boolean testRightmostBit() {
        return (this.mPol[this.mLength - 1] & mBitmask[this.mBit - 1]) != 0L;
    }

    boolean testBit(int n) {
        if (n < 0 || n > this.mDegree) {
            return false;
        }
        long l = this.mPol[n >>> 6] & mBitmask[n & 0x3F];
        return l != 0L;
    }

    private long[] getElement() {
        long[] lArray = new long[this.mPol.length];
        System.arraycopy(this.mPol, 0, lArray, 0, this.mPol.length);
        return lArray;
    }

    private long[] getElementReverseOrder() {
        long[] lArray = new long[this.mPol.length];
        for (int i = 0; i < this.mDegree; ++i) {
            if (!this.testBit(this.mDegree - i - 1)) continue;
            int n = i >>> 6;
            lArray[n] = lArray[n] | mBitmask[i & 0x3F];
        }
        return lArray;
    }

    void reverseOrder() {
        this.mPol = this.getElementReverseOrder();
    }

    public GFElement add(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.addToThis(gFElement);
        return gF2nONBElement;
    }

    public void addToThis(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException();
        }
        if (!this.mField.equals(((GF2nONBElement)gFElement).mField)) {
            throw new RuntimeException();
        }
        for (int i = 0; i < this.mLength; ++i) {
            int n = i;
            this.mPol[n] = this.mPol[n] ^ ((GF2nONBElement)gFElement).mPol[i];
        }
    }

    public GF2nElement increase() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.increaseThis();
        return gF2nONBElement;
    }

    public void increaseThis() {
        this.addToThis(GF2nONBElement.ONE((GF2nONBField)this.mField));
    }

    public GFElement multiply(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.multiplyThisBy(gFElement);
        return gF2nONBElement;
    }

    public void multiplyThisBy(GFElement gFElement) throws RuntimeException {
        if (!(gFElement instanceof GF2nONBElement)) {
            throw new RuntimeException("The elements have different representation: not yet implemented");
        }
        if (!this.mField.equals(((GF2nONBElement)gFElement).mField)) {
            throw new RuntimeException();
        }
        if (this.equals(gFElement)) {
            this.squareThis();
        } else {
            long[] lArray = this.mPol;
            long[] lArray2 = ((GF2nONBElement)gFElement).mPol;
            long[] lArray3 = new long[this.mLength];
            int[][] nArray = ((GF2nONBField)this.mField).mMult;
            int n = this.mLength - 1;
            int n2 = this.mBit - 1;
            boolean bl = false;
            long l = mBitmask[63];
            long l2 = mBitmask[n2];
            for (int i = 0; i < this.mDegree; ++i) {
                boolean bl2;
                int n3;
                int n4;
                int n5;
                bl = false;
                for (n5 = 0; n5 < this.mDegree; ++n5) {
                    n4 = mIBY64[n5];
                    n3 = n5 & 0x3F;
                    int n6 = mIBY64[nArray[n5][0]];
                    int n7 = nArray[n5][0] & 0x3F;
                    if ((lArray[n4] & mBitmask[n3]) == 0L) continue;
                    if ((lArray2[n6] & mBitmask[n7]) != 0L) {
                        bl ^= true;
                    }
                    if (nArray[n5][1] == -1 || (lArray2[n6 = mIBY64[nArray[n5][1]]] & mBitmask[n7 = nArray[n5][1] & 0x3F]) == 0L) continue;
                    bl ^= true;
                }
                n4 = mIBY64[i];
                n3 = i & 0x3F;
                if (bl) {
                    int n8 = n4;
                    lArray3[n8] = lArray3[n8] ^ mBitmask[n3];
                }
                if (this.mLength > 1) {
                    boolean bl3;
                    bl2 = (lArray[n] & 1L) == 1L;
                    for (n5 = n - 1; n5 >= 0; --n5) {
                        bl3 = (lArray[n5] & 1L) != 0L;
                        lArray[n5] = lArray[n5] >>> 1;
                        if (bl2) {
                            int n9 = n5;
                            lArray[n9] = lArray[n9] ^ l;
                        }
                        bl2 = bl3;
                    }
                    lArray[n] = lArray[n] >>> 1;
                    if (bl2) {
                        int n10 = n;
                        lArray[n10] = lArray[n10] ^ l2;
                    }
                    bl2 = (lArray2[n] & 1L) == 1L;
                    for (n5 = n - 1; n5 >= 0; --n5) {
                        bl3 = (lArray2[n5] & 1L) != 0L;
                        lArray2[n5] = lArray2[n5] >>> 1;
                        if (bl2) {
                            int n11 = n5;
                            lArray2[n11] = lArray2[n11] ^ l;
                        }
                        bl2 = bl3;
                    }
                    lArray2[n] = lArray2[n] >>> 1;
                    if (!bl2) continue;
                    int n12 = n;
                    lArray2[n12] = lArray2[n12] ^ l2;
                    continue;
                }
                bl2 = (lArray[0] & 1L) == 1L;
                lArray[0] = lArray[0] >>> 1;
                if (bl2) {
                    lArray[0] = lArray[0] ^ l2;
                }
                bl2 = (lArray2[0] & 1L) == 1L;
                lArray2[0] = lArray2[0] >>> 1;
                if (!bl2) continue;
                lArray2[0] = lArray2[0] ^ l2;
            }
            this.assign(lArray3);
        }
    }

    public GF2nElement square() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareThis();
        return gF2nONBElement;
    }

    public void squareThis() {
        boolean bl;
        long[] lArray = this.getElement();
        int n = this.mLength - 1;
        int n2 = this.mBit - 1;
        long l = mBitmask[63];
        boolean bl2 = (lArray[n] & mBitmask[n2]) != 0L;
        for (int i = 0; i < n; ++i) {
            bl = (lArray[i] & l) != 0L;
            lArray[i] = lArray[i] << 1;
            if (bl2) {
                int n3 = i;
                lArray[n3] = lArray[n3] ^ 1L;
            }
            bl2 = bl;
        }
        bl = (lArray[n] & mBitmask[n2]) != 0L;
        lArray[n] = lArray[n] << 1;
        if (bl2) {
            int n4 = n;
            lArray[n4] = lArray[n4] ^ 1L;
        }
        if (bl) {
            int n5 = n;
            lArray[n5] = lArray[n5] ^ mBitmask[n2 + 1];
        }
        this.assign(lArray);
    }

    public GFElement invert() throws ArithmeticException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.invertThis();
        return gF2nONBElement;
    }

    public void invertThis() throws ArithmeticException {
        if (this.isZero()) {
            throw new ArithmeticException();
        }
        boolean bl = false;
        for (int i = 31; !bl && i >= 0; --i) {
            if (((long)(this.mDegree - 1) & mBitmask[i]) == 0L) continue;
            bl = true;
        }
        GF2nElement gF2nElement = GF2nONBElement.ZERO((GF2nONBField)this.mField);
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        int n = 1;
        for (int i = ++i - 1; i >= 0; --i) {
            gF2nElement = (GF2nElement)((GF2nElement)gF2nONBElement).clone();
            for (int j = 1; j <= n; ++j) {
                gF2nElement.squareThis();
            }
            gF2nONBElement.multiplyThisBy(gF2nElement);
            n <<= 1;
            if (((long)(this.mDegree - 1) & mBitmask[i]) == 0L) continue;
            ((GF2nElement)gF2nONBElement).squareThis();
            gF2nONBElement.multiplyThisBy(this);
            ++n;
        }
        ((GF2nElement)gF2nONBElement).squareThis();
    }

    public GF2nElement squareRoot() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareRootThis();
        return gF2nONBElement;
    }

    public void squareRootThis() {
        long[] lArray = this.getElement();
        int n = this.mLength - 1;
        int n2 = this.mBit - 1;
        long l = mBitmask[63];
        boolean bl = (lArray[0] & 1L) != 0L;
        for (int i = n; i >= 0; --i) {
            boolean bl2 = (lArray[i] & 1L) != 0L;
            lArray[i] = lArray[i] >>> 1;
            if (bl) {
                if (i == n) {
                    int n3 = i;
                    lArray[n3] = lArray[n3] ^ mBitmask[n2];
                } else {
                    int n4 = i;
                    lArray[n4] = lArray[n4] ^ l;
                }
            }
            bl = bl2;
        }
        this.assign(lArray);
    }

    public int trace() {
        int n;
        int n2;
        int n3 = 0;
        int n4 = this.mLength - 1;
        for (n2 = 0; n2 < n4; ++n2) {
            for (n = 0; n < 64; ++n) {
                if ((this.mPol[n2] & mBitmask[n]) == 0L) continue;
                n3 ^= 1;
            }
        }
        n2 = this.mBit;
        for (n = 0; n < n2; ++n) {
            if ((this.mPol[n4] & mBitmask[n]) == 0L) continue;
            n3 ^= 1;
        }
        return n3;
    }

    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        int n;
        if (this.trace() == 1) {
            throw new RuntimeException();
        }
        long l = mBitmask[63];
        long l2 = 0L;
        long l3 = 1L;
        long[] lArray = new long[this.mLength];
        long l4 = 0L;
        int n2 = 1;
        for (n = 0; n < this.mLength - 1; ++n) {
            for (n2 = 1; n2 < 64; ++n2) {
                if ((mBitmask[n2] & this.mPol[n]) != l2 && (l4 & mBitmask[n2 - 1]) != l2 || (this.mPol[n] & mBitmask[n2]) == l2 && (l4 & mBitmask[n2 - 1]) == l2) continue;
                l4 ^= mBitmask[n2];
            }
            lArray[n] = l4;
            l4 = (l & l4) != l2 && (l3 & this.mPol[n + 1]) == l3 || (l & l4) == l2 && (l3 & this.mPol[n + 1]) == l2 ? l2 : l3;
        }
        n = this.mDegree & 0x3F;
        long l5 = this.mPol[this.mLength - 1];
        for (n2 = 1; n2 < n; ++n2) {
            if ((mBitmask[n2] & l5) != l2 && (mBitmask[n2 - 1] & l4) != l2 || (mBitmask[n2] & l5) == l2 && (mBitmask[n2 - 1] & l4) == l2) continue;
            l4 ^= mBitmask[n2];
        }
        lArray[this.mLength - 1] = l4;
        return new GF2nONBElement((GF2nONBField)this.mField, lArray);
    }

    public String toString() {
        return this.toString(16);
    }

    public String toString(int n) {
        String string;
        block5: {
            long[] lArray;
            block4: {
                int n2;
                string = "";
                lArray = this.getElement();
                int n3 = this.mBit;
                if (n != 2) break block4;
                for (n2 = n3 - 1; n2 >= 0; --n2) {
                    string = (lArray[lArray.length - 1] & 1L << n2) == 0L ? string + "0" : string + "1";
                }
                for (n2 = lArray.length - 2; n2 >= 0; --n2) {
                    for (int i = 63; i >= 0; --i) {
                        string = (lArray[n2] & mBitmask[i]) == 0L ? string + "0" : string + "1";
                    }
                }
                break block5;
            }
            if (n != 16) break block5;
            char[] cArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int i = lArray.length - 1; i >= 0; --i) {
                string = string + cArray[(int)(lArray[i] >>> 60) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 56) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 52) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 48) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 44) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 40) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 36) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 32) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 28) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 24) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 20) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 16) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 12) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 8) & 0xF];
                string = string + cArray[(int)(lArray[i] >>> 4) & 0xF];
                string = string + cArray[(int)lArray[i] & 0xF];
                string = string + " ";
            }
        }
        return string;
    }

    public BigInteger toFlexiBigInt() {
        return new BigInteger(1, this.toByteArray());
    }

    public byte[] toByteArray() {
        int n = (this.mDegree - 1 >> 3) + 1;
        byte[] byArray = new byte[n];
        for (int i = 0; i < n; ++i) {
            byArray[n - i - 1] = (byte)((this.mPol[i >>> 3] & 255L << ((i & 7) << 3)) >>> ((i & 7) << 3));
        }
        return byArray;
    }
}

