/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;

class SimpleBigDecimal {
    private static final long serialVersionUID = 1L;
    private final BigInteger bigInt;
    private final int scale;

    public static SimpleBigDecimal getInstance(BigInteger bigInteger, int n) {
        return new SimpleBigDecimal(bigInteger.shiftLeft(n), n);
    }

    public SimpleBigDecimal(BigInteger bigInteger, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        this.bigInt = bigInteger;
        this.scale = n;
    }

    private void checkScale(SimpleBigDecimal simpleBigDecimal) {
        if (this.scale != simpleBigDecimal.scale) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    public SimpleBigDecimal adjustScale(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        if (n == this.scale) {
            return this;
        }
        return new SimpleBigDecimal(this.bigInt.shiftLeft(n - this.scale), n);
    }

    public SimpleBigDecimal add(SimpleBigDecimal simpleBigDecimal) {
        this.checkScale(simpleBigDecimal);
        return new SimpleBigDecimal(this.bigInt.add(simpleBigDecimal.bigInt), this.scale);
    }

    public SimpleBigDecimal add(BigInteger bigInteger) {
        return new SimpleBigDecimal(this.bigInt.add(bigInteger.shiftLeft(this.scale)), this.scale);
    }

    public SimpleBigDecimal negate() {
        return new SimpleBigDecimal(this.bigInt.negate(), this.scale);
    }

    public SimpleBigDecimal subtract(SimpleBigDecimal simpleBigDecimal) {
        return this.add(simpleBigDecimal.negate());
    }

    public SimpleBigDecimal subtract(BigInteger bigInteger) {
        return new SimpleBigDecimal(this.bigInt.subtract(bigInteger.shiftLeft(this.scale)), this.scale);
    }

    public SimpleBigDecimal multiply(SimpleBigDecimal simpleBigDecimal) {
        this.checkScale(simpleBigDecimal);
        return new SimpleBigDecimal(this.bigInt.multiply(simpleBigDecimal.bigInt), this.scale + this.scale);
    }

    public SimpleBigDecimal multiply(BigInteger bigInteger) {
        return new SimpleBigDecimal(this.bigInt.multiply(bigInteger), this.scale);
    }

    public SimpleBigDecimal divide(SimpleBigDecimal simpleBigDecimal) {
        this.checkScale(simpleBigDecimal);
        BigInteger bigInteger = this.bigInt.shiftLeft(this.scale);
        return new SimpleBigDecimal(bigInteger.divide(simpleBigDecimal.bigInt), this.scale);
    }

    public SimpleBigDecimal divide(BigInteger bigInteger) {
        return new SimpleBigDecimal(this.bigInt.divide(bigInteger), this.scale);
    }

    public SimpleBigDecimal shiftLeft(int n) {
        return new SimpleBigDecimal(this.bigInt.shiftLeft(n), this.scale);
    }

    public int compareTo(SimpleBigDecimal simpleBigDecimal) {
        this.checkScale(simpleBigDecimal);
        return this.bigInt.compareTo(simpleBigDecimal.bigInt);
    }

    public int compareTo(BigInteger bigInteger) {
        return this.bigInt.compareTo(bigInteger.shiftLeft(this.scale));
    }

    public BigInteger floor() {
        return this.bigInt.shiftRight(this.scale);
    }

    public BigInteger round() {
        SimpleBigDecimal simpleBigDecimal = new SimpleBigDecimal(ECConstants.ONE, 1);
        return this.add(simpleBigDecimal.adjustScale(this.scale)).floor();
    }

    public int intValue() {
        return this.floor().intValue();
    }

    public long longValue() {
        return this.floor().longValue();
    }

    public int getScale() {
        return this.scale;
    }

    public String toString() {
        int n;
        if (this.scale == 0) {
            return this.bigInt.toString();
        }
        BigInteger bigInteger = this.floor();
        BigInteger bigInteger2 = this.bigInt.subtract(bigInteger.shiftLeft(this.scale));
        if (this.bigInt.signum() == -1) {
            bigInteger2 = ECConstants.ONE.shiftLeft(this.scale).subtract(bigInteger2);
        }
        if (bigInteger.signum() == -1 && !bigInteger2.equals(ECConstants.ZERO)) {
            bigInteger = bigInteger.add(ECConstants.ONE);
        }
        String string = bigInteger.toString();
        char[] cArray = new char[this.scale];
        String string2 = bigInteger2.toString(2);
        int n2 = string2.length();
        int n3 = this.scale - n2;
        for (n = 0; n < n3; ++n) {
            cArray[n] = 48;
        }
        for (n = 0; n < n2; ++n) {
            cArray[n3 + n] = string2.charAt(n);
        }
        String string3 = new String(cArray);
        StringBuffer stringBuffer = new StringBuffer(string);
        stringBuffer.append(".");
        stringBuffer.append(string3);
        return stringBuffer.toString();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SimpleBigDecimal)) {
            return false;
        }
        SimpleBigDecimal simpleBigDecimal = (SimpleBigDecimal)object;
        return this.bigInt.equals(simpleBigDecimal.bigInt) && this.scale == simpleBigDecimal.scale;
    }

    public int hashCode() {
        return this.bigInt.hashCode() ^ this.scale;
    }
}

