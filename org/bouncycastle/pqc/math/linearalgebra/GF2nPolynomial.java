/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import org.bouncycastle.pqc.math.linearalgebra.GF2Polynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nField;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBField;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialField;

public class GF2nPolynomial {
    private GF2nElement[] coeff;
    private int size;

    public GF2nPolynomial(int n, GF2nElement gF2nElement) {
        this.size = n;
        this.coeff = new GF2nElement[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.coeff[i] = (GF2nElement)gF2nElement.clone();
        }
    }

    private GF2nPolynomial(int n) {
        this.size = n;
        this.coeff = new GF2nElement[this.size];
    }

    public GF2nPolynomial(GF2nPolynomial gF2nPolynomial) {
        this.coeff = new GF2nElement[gF2nPolynomial.size];
        this.size = gF2nPolynomial.size;
        for (int i = 0; i < this.size; ++i) {
            this.coeff[i] = (GF2nElement)gF2nPolynomial.coeff[i].clone();
        }
    }

    public GF2nPolynomial(GF2Polynomial gF2Polynomial, GF2nField gF2nField) {
        this.size = gF2nField.getDegree() + 1;
        this.coeff = new GF2nElement[this.size];
        if (gF2nField instanceof GF2nONBField) {
            for (int i = 0; i < this.size; ++i) {
                this.coeff[i] = gF2Polynomial.testBit(i) ? GF2nONBElement.ONE((GF2nONBField)gF2nField) : GF2nONBElement.ZERO((GF2nONBField)gF2nField);
            }
        } else if (gF2nField instanceof GF2nPolynomialField) {
            for (int i = 0; i < this.size; ++i) {
                this.coeff[i] = gF2Polynomial.testBit(i) ? GF2nPolynomialElement.ONE((GF2nPolynomialField)gF2nField) : GF2nPolynomialElement.ZERO((GF2nPolynomialField)gF2nField);
            }
        } else {
            throw new IllegalArgumentException("PolynomialGF2n(Bitstring, GF2nField): B1 must be an instance of GF2nONBField or GF2nPolynomialField!");
        }
    }

    public final void assignZeroToElements() {
        for (int i = 0; i < this.size; ++i) {
            this.coeff[i].assignZero();
        }
    }

    public final int size() {
        return this.size;
    }

    public final int getDegree() {
        for (int i = this.size - 1; i >= 0; --i) {
            if (this.coeff[i].isZero()) continue;
            return i;
        }
        return -1;
    }

    public final void enlarge(int n) {
        if (n <= this.size) {
            return;
        }
        GF2nElement[] gF2nElementArray = new GF2nElement[n];
        System.arraycopy(this.coeff, 0, gF2nElementArray, 0, this.size);
        GF2nField gF2nField = this.coeff[0].getField();
        if (this.coeff[0] instanceof GF2nPolynomialElement) {
            for (int i = this.size; i < n; ++i) {
                gF2nElementArray[i] = GF2nPolynomialElement.ZERO((GF2nPolynomialField)gF2nField);
            }
        } else if (this.coeff[0] instanceof GF2nONBElement) {
            for (int i = this.size; i < n; ++i) {
                gF2nElementArray[i] = GF2nONBElement.ZERO((GF2nONBField)gF2nField);
            }
        }
        this.size = n;
        this.coeff = gF2nElementArray;
    }

    public final void shrink() {
        int n;
        for (n = this.size - 1; this.coeff[n].isZero() && n > 0; --n) {
        }
        if (++n < this.size) {
            GF2nElement[] gF2nElementArray = new GF2nElement[n];
            System.arraycopy(this.coeff, 0, gF2nElementArray, 0, n);
            this.coeff = gF2nElementArray;
            this.size = n;
        }
    }

    public final void set(int n, GF2nElement gF2nElement) {
        if (!(gF2nElement instanceof GF2nPolynomialElement) && !(gF2nElement instanceof GF2nONBElement)) {
            throw new IllegalArgumentException("PolynomialGF2n.set f must be an instance of either GF2nPolynomialElement or GF2nONBElement!");
        }
        this.coeff[n] = (GF2nElement)gF2nElement.clone();
    }

    public final GF2nElement at(int n) {
        return this.coeff[n];
    }

    public final boolean isZero() {
        for (int i = 0; i < this.size; ++i) {
            if (this.coeff[i] == null || this.coeff[i].isZero()) continue;
            return false;
        }
        return true;
    }

    public final boolean equals(Object object) {
        if (object == null || !(object instanceof GF2nPolynomial)) {
            return false;
        }
        GF2nPolynomial gF2nPolynomial = (GF2nPolynomial)object;
        if (this.getDegree() != gF2nPolynomial.getDegree()) {
            return false;
        }
        for (int i = 0; i < this.size; ++i) {
            if (this.coeff[i].equals(gF2nPolynomial.coeff[i])) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.getDegree() + this.coeff.hashCode();
    }

    public final GF2nPolynomial add(GF2nPolynomial gF2nPolynomial) throws RuntimeException {
        GF2nPolynomial gF2nPolynomial2;
        if (this.size() >= gF2nPolynomial.size()) {
            int n;
            gF2nPolynomial2 = new GF2nPolynomial(this.size());
            for (n = 0; n < gF2nPolynomial.size(); ++n) {
                gF2nPolynomial2.coeff[n] = (GF2nElement)this.coeff[n].add(gF2nPolynomial.coeff[n]);
            }
            while (n < this.size()) {
                gF2nPolynomial2.coeff[n] = this.coeff[n];
                ++n;
            }
        } else {
            int n;
            gF2nPolynomial2 = new GF2nPolynomial(gF2nPolynomial.size());
            for (n = 0; n < this.size(); ++n) {
                gF2nPolynomial2.coeff[n] = (GF2nElement)this.coeff[n].add(gF2nPolynomial.coeff[n]);
            }
            while (n < gF2nPolynomial.size()) {
                gF2nPolynomial2.coeff[n] = gF2nPolynomial.coeff[n];
                ++n;
            }
        }
        return gF2nPolynomial2;
    }

    public final GF2nPolynomial scalarMultiply(GF2nElement gF2nElement) throws RuntimeException {
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(this.size());
        for (int i = 0; i < this.size(); ++i) {
            gF2nPolynomial.coeff[i] = (GF2nElement)this.coeff[i].multiply(gF2nElement);
        }
        return gF2nPolynomial;
    }

    public final GF2nPolynomial multiply(GF2nPolynomial gF2nPolynomial) throws RuntimeException {
        int n;
        int n2 = this.size();
        if (n2 != (n = gF2nPolynomial.size())) {
            throw new IllegalArgumentException("PolynomialGF2n.multiply: this and b must have the same size!");
        }
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial((n2 << 1) - 1);
        for (int i = 0; i < this.size(); ++i) {
            for (int j = 0; j < gF2nPolynomial.size(); ++j) {
                gF2nPolynomial2.coeff[i + j] = gF2nPolynomial2.coeff[i + j] == null ? (GF2nElement)this.coeff[i].multiply(gF2nPolynomial.coeff[j]) : (GF2nElement)gF2nPolynomial2.coeff[i + j].add(this.coeff[i].multiply(gF2nPolynomial.coeff[j]));
            }
        }
        return gF2nPolynomial2;
    }

    public final GF2nPolynomial multiplyAndReduce(GF2nPolynomial gF2nPolynomial, GF2nPolynomial gF2nPolynomial2) throws RuntimeException, ArithmeticException {
        return this.multiply(gF2nPolynomial).reduce(gF2nPolynomial2);
    }

    public final GF2nPolynomial reduce(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        return this.remainder(gF2nPolynomial);
    }

    public final void shiftThisLeft(int n) {
        block3: {
            int n2;
            GF2nField gF2nField;
            block4: {
                if (n <= 0) break block3;
                int n3 = this.size;
                gF2nField = this.coeff[0].getField();
                this.enlarge(this.size + n);
                for (n2 = n3 - 1; n2 >= 0; --n2) {
                    this.coeff[n2 + n] = this.coeff[n2];
                }
                if (!(this.coeff[0] instanceof GF2nPolynomialElement)) break block4;
                for (n2 = n - 1; n2 >= 0; --n2) {
                    this.coeff[n2] = GF2nPolynomialElement.ZERO((GF2nPolynomialField)gF2nField);
                }
                break block3;
            }
            if (!(this.coeff[0] instanceof GF2nONBElement)) break block3;
            for (n2 = n - 1; n2 >= 0; --n2) {
                this.coeff[n2] = GF2nONBElement.ZERO((GF2nONBField)gF2nField);
            }
        }
    }

    public final GF2nPolynomial shiftLeft(int n) {
        if (n <= 0) {
            return new GF2nPolynomial(this);
        }
        GF2nPolynomial gF2nPolynomial = new GF2nPolynomial(this.size + n, this.coeff[0]);
        gF2nPolynomial.assignZeroToElements();
        for (int i = 0; i < this.size; ++i) {
            gF2nPolynomial.coeff[i + n] = this.coeff[i];
        }
        return gF2nPolynomial;
    }

    public final GF2nPolynomial[] divide(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        GF2nPolynomial[] gF2nPolynomialArray = new GF2nPolynomial[2];
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        gF2nPolynomial2.shrink();
        int n = gF2nPolynomial.getDegree();
        GF2nElement gF2nElement = (GF2nElement)gF2nPolynomial.coeff[n].invert();
        if (gF2nPolynomial2.getDegree() < n) {
            gF2nPolynomialArray[0] = new GF2nPolynomial(this);
            gF2nPolynomialArray[0].assignZeroToElements();
            gF2nPolynomialArray[0].shrink();
            gF2nPolynomialArray[1] = new GF2nPolynomial(this);
            gF2nPolynomialArray[1].shrink();
            return gF2nPolynomialArray;
        }
        gF2nPolynomialArray[0] = new GF2nPolynomial(this);
        gF2nPolynomialArray[0].assignZeroToElements();
        int n2 = gF2nPolynomial2.getDegree() - n;
        while (n2 >= 0) {
            GF2nElement gF2nElement2 = (GF2nElement)gF2nPolynomial2.coeff[gF2nPolynomial2.getDegree()].multiply(gF2nElement);
            GF2nPolynomial gF2nPolynomial3 = gF2nPolynomial.scalarMultiply(gF2nElement2);
            gF2nPolynomial3.shiftThisLeft(n2);
            gF2nPolynomial2 = gF2nPolynomial2.add(gF2nPolynomial3);
            gF2nPolynomial2.shrink();
            gF2nPolynomialArray[0].coeff[n2] = (GF2nElement)gF2nElement2.clone();
            n2 = gF2nPolynomial2.getDegree() - n;
        }
        gF2nPolynomialArray[1] = gF2nPolynomial2;
        gF2nPolynomialArray[0].shrink();
        return gF2nPolynomialArray;
    }

    public final GF2nPolynomial remainder(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        GF2nPolynomial[] gF2nPolynomialArray = new GF2nPolynomial[2];
        gF2nPolynomialArray = this.divide(gF2nPolynomial);
        return gF2nPolynomialArray[1];
    }

    public final GF2nPolynomial quotient(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        GF2nPolynomial[] gF2nPolynomialArray = new GF2nPolynomial[2];
        gF2nPolynomialArray = this.divide(gF2nPolynomial);
        return gF2nPolynomialArray[0];
    }

    public final GF2nPolynomial gcd(GF2nPolynomial gF2nPolynomial) throws RuntimeException, ArithmeticException {
        GF2nPolynomial gF2nPolynomial2 = new GF2nPolynomial(this);
        GF2nPolynomial gF2nPolynomial3 = new GF2nPolynomial(gF2nPolynomial);
        gF2nPolynomial2.shrink();
        gF2nPolynomial3.shrink();
        while (!gF2nPolynomial3.isZero()) {
            GF2nPolynomial gF2nPolynomial4 = gF2nPolynomial2.remainder(gF2nPolynomial3);
            gF2nPolynomial2 = gF2nPolynomial3;
            gF2nPolynomial3 = gF2nPolynomial4;
        }
        GF2nElement gF2nElement = gF2nPolynomial2.coeff[gF2nPolynomial2.getDegree()];
        GF2nPolynomial gF2nPolynomial5 = gF2nPolynomial2.scalarMultiply((GF2nElement)gF2nElement.invert());
        return gF2nPolynomial5;
    }
}

