/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.pqc.math.linearalgebra.GF2Polynomial;
import org.bouncycastle.pqc.math.linearalgebra.GF2nElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nONBField;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialElement;
import org.bouncycastle.pqc.math.linearalgebra.GF2nPolynomialField;

public abstract class GF2nField {
    protected final SecureRandom random;
    protected int mDegree;
    protected GF2Polynomial fieldPolynomial;
    protected Vector fields;
    protected Vector matrices;

    protected GF2nField(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public final int getDegree() {
        return this.mDegree;
    }

    public final GF2Polynomial getFieldPolynomial() {
        if (this.fieldPolynomial == null) {
            this.computeFieldPolynomial();
        }
        return new GF2Polynomial(this.fieldPolynomial);
    }

    public final boolean equals(Object object) {
        if (object == null || !(object instanceof GF2nField)) {
            return false;
        }
        GF2nField gF2nField = (GF2nField)object;
        if (gF2nField.mDegree != this.mDegree) {
            return false;
        }
        if (!this.fieldPolynomial.equals(gF2nField.fieldPolynomial)) {
            return false;
        }
        if (this instanceof GF2nPolynomialField && !(gF2nField instanceof GF2nPolynomialField)) {
            return false;
        }
        return !(this instanceof GF2nONBField) || gF2nField instanceof GF2nONBField;
    }

    public int hashCode() {
        return this.mDegree + this.fieldPolynomial.hashCode();
    }

    protected abstract GF2nElement getRandomRoot(GF2Polynomial var1);

    protected abstract void computeCOBMatrix(GF2nField var1);

    protected abstract void computeFieldPolynomial();

    protected final GF2Polynomial[] invertMatrix(GF2Polynomial[] gF2PolynomialArray) {
        int n;
        int n2;
        GF2Polynomial[] gF2PolynomialArray2 = new GF2Polynomial[gF2PolynomialArray.length];
        GF2Polynomial[] gF2PolynomialArray3 = new GF2Polynomial[gF2PolynomialArray.length];
        for (n2 = 0; n2 < this.mDegree; ++n2) {
            try {
                gF2PolynomialArray2[n2] = new GF2Polynomial(gF2PolynomialArray[n2]);
                gF2PolynomialArray3[n2] = new GF2Polynomial(this.mDegree);
                gF2PolynomialArray3[n2].setBit(this.mDegree - 1 - n2);
                continue;
            } catch (RuntimeException runtimeException) {
                runtimeException.printStackTrace();
            }
        }
        for (n2 = 0; n2 < this.mDegree - 1; ++n2) {
            for (n = n2; n < this.mDegree && !gF2PolynomialArray2[n].testBit(this.mDegree - 1 - n2); ++n) {
            }
            if (n >= this.mDegree) {
                throw new RuntimeException("GF2nField.invertMatrix: Matrix cannot be inverted!");
            }
            if (n2 != n) {
                GF2Polynomial gF2Polynomial = gF2PolynomialArray2[n2];
                gF2PolynomialArray2[n2] = gF2PolynomialArray2[n];
                gF2PolynomialArray2[n] = gF2Polynomial;
                gF2Polynomial = gF2PolynomialArray3[n2];
                gF2PolynomialArray3[n2] = gF2PolynomialArray3[n];
                gF2PolynomialArray3[n] = gF2Polynomial;
            }
            for (n = n2 + 1; n < this.mDegree; ++n) {
                if (!gF2PolynomialArray2[n].testBit(this.mDegree - 1 - n2)) continue;
                gF2PolynomialArray2[n].addToThis(gF2PolynomialArray2[n2]);
                gF2PolynomialArray3[n].addToThis(gF2PolynomialArray3[n2]);
            }
        }
        for (n2 = this.mDegree - 1; n2 > 0; --n2) {
            for (n = n2 - 1; n >= 0; --n) {
                if (!gF2PolynomialArray2[n].testBit(this.mDegree - 1 - n2)) continue;
                gF2PolynomialArray2[n].addToThis(gF2PolynomialArray2[n2]);
                gF2PolynomialArray3[n].addToThis(gF2PolynomialArray3[n2]);
            }
        }
        return gF2PolynomialArray3;
    }

    public final GF2nElement convert(GF2nElement gF2nElement, GF2nField gF2nField) throws RuntimeException {
        if (gF2nField == this) {
            return (GF2nElement)gF2nElement.clone();
        }
        if (this.fieldPolynomial.equals(gF2nField.fieldPolynomial)) {
            return (GF2nElement)gF2nElement.clone();
        }
        if (this.mDegree != gF2nField.mDegree) {
            throw new RuntimeException("GF2nField.convert: B1 has a different degree and thus cannot be coverted to!");
        }
        int n = this.fields.indexOf(gF2nField);
        if (n == -1) {
            this.computeCOBMatrix(gF2nField);
            n = this.fields.indexOf(gF2nField);
        }
        GF2Polynomial[] gF2PolynomialArray = (GF2Polynomial[])this.matrices.elementAt(n);
        GF2nElement gF2nElement2 = (GF2nElement)gF2nElement.clone();
        if (gF2nElement2 instanceof GF2nONBElement) {
            ((GF2nONBElement)gF2nElement2).reverseOrder();
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.mDegree, gF2nElement2.toFlexiBigInt());
        gF2Polynomial.expandN(this.mDegree);
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.mDegree);
        for (n = 0; n < this.mDegree; ++n) {
            if (!gF2Polynomial.vectorMult(gF2PolynomialArray[n])) continue;
            gF2Polynomial2.setBit(this.mDegree - 1 - n);
        }
        if (gF2nField instanceof GF2nPolynomialField) {
            return new GF2nPolynomialElement((GF2nPolynomialField)gF2nField, gF2Polynomial2);
        }
        if (gF2nField instanceof GF2nONBField) {
            GF2nONBElement gF2nONBElement = new GF2nONBElement((GF2nONBField)gF2nField, gF2Polynomial2.toFlexiBigInt());
            gF2nONBElement.reverseOrder();
            return gF2nONBElement;
        }
        throw new RuntimeException("GF2nField.convert: B1 must be an instance of GF2nPolynomialField or GF2nONBField!");
    }
}

