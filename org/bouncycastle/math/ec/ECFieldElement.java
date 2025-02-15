/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.LongArray;
import org.bouncycastle.math.raw.Mod;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public abstract class ECFieldElement
implements ECConstants {
    public abstract BigInteger toBigInteger();

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement add(ECFieldElement var1);

    public abstract ECFieldElement addOne();

    public abstract ECFieldElement subtract(ECFieldElement var1);

    public abstract ECFieldElement multiply(ECFieldElement var1);

    public abstract ECFieldElement divide(ECFieldElement var1);

    public abstract ECFieldElement negate();

    public abstract ECFieldElement square();

    public abstract ECFieldElement invert();

    public abstract ECFieldElement sqrt();

    public int bitLength() {
        return this.toBigInteger().bitLength();
    }

    public boolean isOne() {
        return this.bitLength() == 1;
    }

    public boolean isZero() {
        return 0 == this.toBigInteger().signum();
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return this.multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return this.square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int n) {
        ECFieldElement eCFieldElement = this;
        for (int i = 0; i < n; ++i) {
            eCFieldElement = eCFieldElement.square();
        }
        return eCFieldElement;
    }

    public boolean testBitZero() {
        return this.toBigInteger().testBit(0);
    }

    public String toString() {
        return this.toBigInteger().toString(16);
    }

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((this.getFieldSize() + 7) / 8, this.toBigInteger());
    }

    public static class F2m
    extends ECFieldElement {
        public static final int GNB = 1;
        public static final int TPB = 2;
        public static final int PPB = 3;
        private int representation;
        private int m;
        private int[] ks;
        private LongArray x;

        public F2m(int n, int n2, int n3, int n4, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > n) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (n3 == 0 && n4 == 0) {
                this.representation = 2;
                this.ks = new int[]{n2};
            } else {
                if (n3 >= n4) {
                    throw new IllegalArgumentException("k2 must be smaller than k3");
                }
                if (n3 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.representation = 3;
                this.ks = new int[]{n2, n3, n4};
            }
            this.m = n;
            this.x = new LongArray(bigInteger);
        }

        public F2m(int n, int n2, BigInteger bigInteger) {
            this(n, n2, 0, 0, bigInteger);
        }

        private F2m(int n, int[] nArray, LongArray longArray) {
            this.m = n;
            this.representation = nArray.length == 1 ? 2 : 3;
            this.ks = nArray;
            this.x = longArray;
        }

        public int bitLength() {
            return this.x.degree();
        }

        public boolean isOne() {
            return this.x.isOne();
        }

        public boolean isZero() {
            return this.x.isZero();
        }

        public boolean testBitZero() {
            return this.x.testBitZero();
        }

        public BigInteger toBigInteger() {
            return this.x.toBigInteger();
        }

        public String getFieldName() {
            return "F2m";
        }

        public int getFieldSize() {
            return this.m;
        }

        public static void checkFieldElements(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            if (!(eCFieldElement instanceof F2m) || !(eCFieldElement2 instanceof F2m)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            F2m f2m = (F2m)eCFieldElement;
            F2m f2m2 = (F2m)eCFieldElement2;
            if (f2m.representation != f2m2.representation) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            }
            if (f2m.m != f2m2.m || !Arrays.areEqual(f2m.ks, f2m2.ks)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray)this.x.clone();
            F2m f2m = (F2m)eCFieldElement;
            longArray.addShiftedByWords(f2m.x, 0);
            return new F2m(this.m, this.ks, longArray);
        }

        public ECFieldElement addOne() {
            return new F2m(this.m, this.ks, this.x.addOne());
        }

        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return this.add(eCFieldElement);
        }

        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new F2m(this.m, this.ks, this.x.modMultiply(((F2m)eCFieldElement).x, this.m, this.ks));
        }

        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return this.multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.x;
            LongArray longArray2 = ((F2m)eCFieldElement).x;
            LongArray longArray3 = ((F2m)eCFieldElement2).x;
            LongArray longArray4 = ((F2m)eCFieldElement3).x;
            LongArray longArray5 = longArray.multiply(longArray2, this.m, this.ks);
            LongArray longArray6 = longArray3.multiply(longArray4, this.m, this.ks);
            if (longArray5 == longArray || longArray5 == longArray2) {
                longArray5 = (LongArray)longArray5.clone();
            }
            longArray5.addShiftedByWords(longArray6, 0);
            longArray5.reduce(this.m, this.ks);
            return new F2m(this.m, this.ks, longArray5);
        }

        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            ECFieldElement eCFieldElement2 = eCFieldElement.invert();
            return this.multiply(eCFieldElement2);
        }

        public ECFieldElement negate() {
            return this;
        }

        public ECFieldElement square() {
            return new F2m(this.m, this.ks, this.x.modSquare(this.m, this.ks));
        }

        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return this.squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.x;
            LongArray longArray2 = ((F2m)eCFieldElement).x;
            LongArray longArray3 = ((F2m)eCFieldElement2).x;
            LongArray longArray4 = longArray.square(this.m, this.ks);
            LongArray longArray5 = longArray2.multiply(longArray3, this.m, this.ks);
            if (longArray4 == longArray) {
                longArray4 = (LongArray)longArray4.clone();
            }
            longArray4.addShiftedByWords(longArray5, 0);
            longArray4.reduce(this.m, this.ks);
            return new F2m(this.m, this.ks, longArray4);
        }

        public ECFieldElement squarePow(int n) {
            return n < 1 ? this : new F2m(this.m, this.ks, this.x.modSquareN(n, this.m, this.ks));
        }

        public ECFieldElement invert() {
            return new F2m(this.m, this.ks, this.x.modInverse(this.m, this.ks));
        }

        public ECFieldElement sqrt() {
            return this.x.isZero() || this.x.isOne() ? this : this.squarePow(this.m - 1);
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int getM() {
            return this.m;
        }

        public int getK1() {
            return this.ks[0];
        }

        public int getK2() {
            return this.ks.length >= 2 ? this.ks[1] : 0;
        }

        public int getK3() {
            return this.ks.length >= 3 ? this.ks[2] : 0;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m)object;
            return this.m == f2m.m && this.representation == f2m.representation && Arrays.areEqual(this.ks, f2m.ks) && this.x.equals(f2m.x);
        }

        public int hashCode() {
            return this.x.hashCode() ^ this.m ^ Arrays.hashCode(this.ks);
        }
    }

    public static class Fp
    extends ECFieldElement {
        BigInteger q;
        BigInteger r;
        BigInteger x;

        static BigInteger calculateResidue(BigInteger bigInteger) {
            BigInteger bigInteger2;
            int n = bigInteger.bitLength();
            if (n >= 96 && (bigInteger2 = bigInteger.shiftRight(n - 64)).longValue() == -1L) {
                return ONE.shiftLeft(n).subtract(bigInteger);
            }
            return null;
        }

        public Fp(BigInteger bigInteger, BigInteger bigInteger2) {
            this(bigInteger, Fp.calculateResidue(bigInteger), bigInteger2);
        }

        Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.q = bigInteger;
            this.r = bigInteger2;
            this.x = bigInteger3;
        }

        public BigInteger toBigInteger() {
            return this.x;
        }

        public String getFieldName() {
            return "Fp";
        }

        public int getFieldSize() {
            return this.q.bitLength();
        }

        public BigInteger getQ() {
            return this.q;
        }

        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new Fp(this.q, this.r, this.modAdd(this.x, eCFieldElement.toBigInteger()));
        }

        public ECFieldElement addOne() {
            BigInteger bigInteger = this.x.add(ECConstants.ONE);
            if (bigInteger.compareTo(this.q) == 0) {
                bigInteger = ECConstants.ZERO;
            }
            return new Fp(this.q, this.r, bigInteger);
        }

        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new Fp(this.q, this.r, this.modSubtract(this.x, eCFieldElement.toBigInteger()));
        }

        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new Fp(this.q, this.r, this.modMult(this.x, eCFieldElement.toBigInteger()));
        }

        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            BigInteger bigInteger5 = bigInteger.multiply(bigInteger2);
            BigInteger bigInteger6 = bigInteger3.multiply(bigInteger4);
            return new Fp(this.q, this.r, this.modReduce(bigInteger5.subtract(bigInteger6)));
        }

        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            BigInteger bigInteger5 = bigInteger.multiply(bigInteger2);
            BigInteger bigInteger6 = bigInteger3.multiply(bigInteger4);
            return new Fp(this.q, this.r, this.modReduce(bigInteger5.add(bigInteger6)));
        }

        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new Fp(this.q, this.r, this.modMult(this.x, this.modInverse(eCFieldElement.toBigInteger())));
        }

        public ECFieldElement negate() {
            return this.x.signum() == 0 ? this : new Fp(this.q, this.r, this.q.subtract(this.x));
        }

        public ECFieldElement square() {
            return new Fp(this.q, this.r, this.modMult(this.x, this.x));
        }

        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = bigInteger.multiply(bigInteger);
            BigInteger bigInteger5 = bigInteger2.multiply(bigInteger3);
            return new Fp(this.q, this.r, this.modReduce(bigInteger4.subtract(bigInteger5)));
        }

        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = bigInteger.multiply(bigInteger);
            BigInteger bigInteger5 = bigInteger2.multiply(bigInteger3);
            return new Fp(this.q, this.r, this.modReduce(bigInteger4.add(bigInteger5)));
        }

        public ECFieldElement invert() {
            return new Fp(this.q, this.r, this.modInverse(this.x));
        }

        public ECFieldElement sqrt() {
            if (this.isZero() || this.isOne()) {
                return this;
            }
            if (!this.q.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.q.testBit(1)) {
                BigInteger bigInteger = this.q.shiftRight(2).add(ECConstants.ONE);
                return this.checkSqrt(new Fp(this.q, this.r, this.x.modPow(bigInteger, this.q)));
            }
            if (this.q.testBit(2)) {
                BigInteger bigInteger = this.x.modPow(this.q.shiftRight(3), this.q);
                BigInteger bigInteger2 = this.modMult(bigInteger, this.x);
                BigInteger bigInteger3 = this.modMult(bigInteger2, bigInteger);
                if (bigInteger3.equals(ECConstants.ONE)) {
                    return this.checkSqrt(new Fp(this.q, this.r, bigInteger2));
                }
                BigInteger bigInteger4 = ECConstants.TWO.modPow(this.q.shiftRight(2), this.q);
                BigInteger bigInteger5 = this.modMult(bigInteger2, bigInteger4);
                return this.checkSqrt(new Fp(this.q, this.r, bigInteger5));
            }
            BigInteger bigInteger = this.q.shiftRight(1);
            if (!this.x.modPow(bigInteger, this.q).equals(ECConstants.ONE)) {
                return null;
            }
            BigInteger bigInteger6 = this.x;
            BigInteger bigInteger7 = this.modDouble(this.modDouble(bigInteger6));
            BigInteger bigInteger8 = bigInteger.add(ECConstants.ONE);
            BigInteger bigInteger9 = this.q.subtract(ECConstants.ONE);
            Random random = new Random();
            while (true) {
                BigInteger bigInteger10;
                if ((bigInteger10 = new BigInteger(this.q.bitLength(), random)).compareTo(this.q) >= 0 || !this.modReduce(bigInteger10.multiply(bigInteger10).subtract(bigInteger7)).modPow(bigInteger, this.q).equals(bigInteger9)) {
                    continue;
                }
                BigInteger[] bigIntegerArray = this.lucasSequence(bigInteger10, bigInteger6, bigInteger8);
                BigInteger bigInteger11 = bigIntegerArray[0];
                BigInteger bigInteger12 = bigIntegerArray[1];
                if (this.modMult(bigInteger12, bigInteger12).equals(bigInteger7)) {
                    return new Fp(this.q, this.r, this.modHalfAbs(bigInteger12));
                }
                if (!bigInteger11.equals(ECConstants.ONE) && !bigInteger11.equals(bigInteger9)) break;
            }
            return null;
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            return eCFieldElement.square().equals(this) ? eCFieldElement : null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int n;
            int n2 = bigInteger3.bitLength();
            int n3 = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.ONE;
            BigInteger bigInteger5 = ECConstants.TWO;
            BigInteger bigInteger6 = bigInteger;
            BigInteger bigInteger7 = ECConstants.ONE;
            BigInteger bigInteger8 = ECConstants.ONE;
            for (n = n2 - 1; n >= n3 + 1; --n) {
                bigInteger7 = this.modMult(bigInteger7, bigInteger8);
                if (bigInteger3.testBit(n)) {
                    bigInteger8 = this.modMult(bigInteger7, bigInteger2);
                    bigInteger4 = this.modMult(bigInteger4, bigInteger6);
                    bigInteger5 = this.modReduce(bigInteger6.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger7)));
                    bigInteger6 = this.modReduce(bigInteger6.multiply(bigInteger6).subtract(bigInteger8.shiftLeft(1)));
                    continue;
                }
                bigInteger8 = bigInteger7;
                bigInteger4 = this.modReduce(bigInteger4.multiply(bigInteger5).subtract(bigInteger7));
                bigInteger6 = this.modReduce(bigInteger6.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger7)));
                bigInteger5 = this.modReduce(bigInteger5.multiply(bigInteger5).subtract(bigInteger7.shiftLeft(1)));
            }
            bigInteger7 = this.modMult(bigInteger7, bigInteger8);
            bigInteger8 = this.modMult(bigInteger7, bigInteger2);
            bigInteger4 = this.modReduce(bigInteger4.multiply(bigInteger5).subtract(bigInteger7));
            bigInteger5 = this.modReduce(bigInteger6.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger7)));
            bigInteger7 = this.modMult(bigInteger7, bigInteger8);
            for (n = 1; n <= n3; ++n) {
                bigInteger4 = this.modMult(bigInteger4, bigInteger5);
                bigInteger5 = this.modReduce(bigInteger5.multiply(bigInteger5).subtract(bigInteger7.shiftLeft(1)));
                bigInteger7 = this.modMult(bigInteger7, bigInteger7);
            }
            return new BigInteger[]{bigInteger4, bigInteger5};
        }

        protected BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigInteger3 = bigInteger.add(bigInteger2);
            if (bigInteger3.compareTo(this.q) >= 0) {
                bigInteger3 = bigInteger3.subtract(this.q);
            }
            return bigInteger3;
        }

        protected BigInteger modDouble(BigInteger bigInteger) {
            BigInteger bigInteger2 = bigInteger.shiftLeft(1);
            if (bigInteger2.compareTo(this.q) >= 0) {
                bigInteger2 = bigInteger2.subtract(this.q);
            }
            return bigInteger2;
        }

        protected BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modInverse(BigInteger bigInteger) {
            int n = this.getFieldSize();
            int n2 = n + 31 >> 5;
            int[] nArray = Nat.fromBigInteger(n, this.q);
            int[] nArray2 = Nat.fromBigInteger(n, bigInteger);
            int[] nArray3 = Nat.create(n2);
            Mod.invert(nArray, nArray2, nArray3);
            return Nat.toBigInteger(n2, nArray3);
        }

        protected BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return this.modReduce(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger modReduce(BigInteger bigInteger) {
            if (this.r != null) {
                boolean bl;
                boolean bl2 = bl = bigInteger.signum() < 0;
                if (bl) {
                    bigInteger = bigInteger.abs();
                }
                int n = this.q.bitLength();
                boolean bl3 = this.r.equals(ECConstants.ONE);
                while (bigInteger.bitLength() > n + 1) {
                    BigInteger bigInteger2 = bigInteger.shiftRight(n);
                    BigInteger bigInteger3 = bigInteger.subtract(bigInteger2.shiftLeft(n));
                    if (!bl3) {
                        bigInteger2 = bigInteger2.multiply(this.r);
                    }
                    bigInteger = bigInteger2.add(bigInteger3);
                }
                while (bigInteger.compareTo(this.q) >= 0) {
                    bigInteger = bigInteger.subtract(this.q);
                }
                if (bl && bigInteger.signum() != 0) {
                    bigInteger = this.q.subtract(bigInteger);
                }
            } else {
                bigInteger = bigInteger.mod(this.q);
            }
            return bigInteger;
        }

        protected BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigInteger3 = bigInteger.subtract(bigInteger2);
            if (bigInteger3.signum() < 0) {
                bigInteger3 = bigInteger3.add(this.q);
            }
            return bigInteger3;
        }

        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof Fp)) {
                return false;
            }
            Fp fp = (Fp)object;
            return this.q.equals(fp.q) && this.x.equals(fp.x);
        }

        public int hashCode() {
            return this.q.hashCode() ^ this.x.hashCode();
        }
    }
}

