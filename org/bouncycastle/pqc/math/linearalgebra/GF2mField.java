/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.pqc.math.linearalgebra.LittleEndianConversions;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;
import org.bouncycastle.pqc.math.linearalgebra.RandUtils;

public class GF2mField {
    private int degree = 0;
    private int polynomial;

    public GF2mField(int n) {
        if (n >= 32) {
            throw new IllegalArgumentException(" Error: the degree of field is too large ");
        }
        if (n < 1) {
            throw new IllegalArgumentException(" Error: the degree of field is non-positive ");
        }
        this.degree = n;
        this.polynomial = PolynomialRingGF2.getIrreduciblePolynomial(n);
    }

    public GF2mField(int n, int n2) {
        if (n != PolynomialRingGF2.degree(n2)) {
            throw new IllegalArgumentException(" Error: the degree is not correct");
        }
        if (!PolynomialRingGF2.isIrreducible(n2)) {
            throw new IllegalArgumentException(" Error: given polynomial is reducible");
        }
        this.degree = n;
        this.polynomial = n2;
    }

    public GF2mField(byte[] byArray) {
        if (byArray.length != 4) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        this.polynomial = LittleEndianConversions.OS2IP(byArray);
        if (!PolynomialRingGF2.isIrreducible(this.polynomial)) {
            throw new IllegalArgumentException("byte array is not an encoded finite field");
        }
        this.degree = PolynomialRingGF2.degree(this.polynomial);
    }

    public GF2mField(GF2mField gF2mField) {
        this.degree = gF2mField.degree;
        this.polynomial = gF2mField.polynomial;
    }

    public int getDegree() {
        return this.degree;
    }

    public int getPolynomial() {
        return this.polynomial;
    }

    public byte[] getEncoded() {
        return LittleEndianConversions.I2OSP(this.polynomial);
    }

    public int add(int n, int n2) {
        return n ^ n2;
    }

    public int mult(int n, int n2) {
        return PolynomialRingGF2.modMultiply(n, n2, this.polynomial);
    }

    public int exp(int n, int n2) {
        if (n2 == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int n3 = 1;
        if (n2 < 0) {
            n = this.inverse(n);
            n2 = -n2;
        }
        while (n2 != 0) {
            if ((n2 & 1) == 1) {
                n3 = this.mult(n3, n);
            }
            n = this.mult(n, n);
            n2 >>>= 1;
        }
        return n3;
    }

    public int inverse(int n) {
        int n2 = (1 << this.degree) - 2;
        return this.exp(n, n2);
    }

    public int sqRoot(int n) {
        for (int i = 1; i < this.degree; ++i) {
            n = this.mult(n, n);
        }
        return n;
    }

    public int getRandomElement(SecureRandom secureRandom) {
        int n = RandUtils.nextInt(secureRandom, 1 << this.degree);
        return n;
    }

    public int getRandomNonZeroElement() {
        return this.getRandomNonZeroElement(new SecureRandom());
    }

    public int getRandomNonZeroElement(SecureRandom secureRandom) {
        int n;
        int n2 = 0x100000;
        int n3 = RandUtils.nextInt(secureRandom, 1 << this.degree);
        for (n = 0; n3 == 0 && n < n2; ++n) {
            n3 = RandUtils.nextInt(secureRandom, 1 << this.degree);
        }
        if (n == n2) {
            n3 = 1;
        }
        return n3;
    }

    public boolean isElementOfThisField(int n) {
        if (this.degree == 31) {
            return n >= 0;
        }
        return n >= 0 && n < 1 << this.degree;
    }

    public String elementToStr(int n) {
        String string = "";
        for (int i = 0; i < this.degree; ++i) {
            string = ((byte)n & 1) == 0 ? "0" + string : "1" + string;
            n >>>= 1;
        }
        return string;
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof GF2mField)) {
            return false;
        }
        GF2mField gF2mField = (GF2mField)object;
        return this.degree == gF2mField.degree && this.polynomial == gF2mField.polynomial;
    }

    public int hashCode() {
        return this.polynomial;
    }

    public String toString() {
        String string = "Finite Field GF(2^" + this.degree + ") = " + "GF(2)[X]/<" + GF2mField.polyToString(this.polynomial) + "> ";
        return string;
    }

    private static String polyToString(int n) {
        String string = "";
        if (n == 0) {
            string = "0";
        } else {
            byte by = (byte)(n & 1);
            if (by == 1) {
                string = "1";
            }
            n >>>= 1;
            int n2 = 1;
            while (n != 0) {
                by = (byte)(n & 1);
                if (by == 1) {
                    string = string + "+x^" + n2;
                }
                n >>>= 1;
                ++n2;
            }
        }
        return string;
    }
}

