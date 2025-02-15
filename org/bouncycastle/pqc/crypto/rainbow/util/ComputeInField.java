/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow.util;

import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

public class ComputeInField {
    private short[][] A;
    short[] x;

    public short[] solveEquation(short[][] sArray, short[] sArray2) {
        if (sArray.length != sArray2.length) {
            return null;
        }
        try {
            int n;
            this.A = new short[sArray.length][sArray.length + 1];
            this.x = new short[sArray.length];
            for (n = 0; n < sArray.length; ++n) {
                for (int i = 0; i < sArray[0].length; ++i) {
                    this.A[n][i] = sArray[n][i];
                }
            }
            for (n = 0; n < sArray2.length; ++n) {
                this.A[n][sArray2.length] = GF2Field.addElem(sArray2[n], this.A[n][sArray2.length]);
            }
            this.computeZerosUnder(false);
            this.substitute();
            return this.x;
        } catch (RuntimeException runtimeException) {
            return null;
        }
    }

    public short[][] inverse(short[][] sArray) {
        try {
            int n;
            int n2;
            this.A = new short[sArray.length][2 * sArray.length];
            if (sArray.length != sArray[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            for (n2 = 0; n2 < sArray.length; ++n2) {
                for (n = 0; n < sArray.length; ++n) {
                    this.A[n2][n] = sArray[n2][n];
                }
                for (n = sArray.length; n < 2 * sArray.length; ++n) {
                    this.A[n2][n] = 0;
                }
                this.A[n2][n2 + this.A.length] = 1;
            }
            this.computeZerosUnder(true);
            for (n2 = 0; n2 < this.A.length; ++n2) {
                short s = GF2Field.invElem(this.A[n2][n2]);
                for (n = n2; n < 2 * this.A.length; ++n) {
                    this.A[n2][n] = GF2Field.multElem(this.A[n2][n], s);
                }
            }
            this.computeZerosAbove();
            short[][] sArray2 = new short[this.A.length][this.A.length];
            for (n2 = 0; n2 < this.A.length; ++n2) {
                for (n = this.A.length; n < 2 * this.A.length; ++n) {
                    sArray2[n2][n - this.A.length] = this.A[n2][n];
                }
            }
            return sArray2;
        } catch (RuntimeException runtimeException) {
            return null;
        }
    }

    private void computeZerosUnder(boolean bl) throws RuntimeException {
        short s = 0;
        int n = bl ? 2 * this.A.length : this.A.length + 1;
        for (int i = 0; i < this.A.length - 1; ++i) {
            for (int j = i + 1; j < this.A.length; ++j) {
                short s2 = this.A[j][i];
                short s3 = GF2Field.invElem(this.A[i][i]);
                if (s3 == 0) {
                    throw new IllegalStateException("Matrix not invertible! We have to choose another one!");
                }
                for (int k = i; k < n; ++k) {
                    s = GF2Field.multElem(this.A[i][k], s3);
                    s = GF2Field.multElem(s2, s);
                    this.A[j][k] = GF2Field.addElem(this.A[j][k], s);
                }
            }
        }
    }

    private void computeZerosAbove() throws RuntimeException {
        short s = 0;
        for (int i = this.A.length - 1; i > 0; --i) {
            for (int j = i - 1; j >= 0; --j) {
                short s2 = this.A[j][i];
                short s3 = GF2Field.invElem(this.A[i][i]);
                if (s3 == 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                for (int k = i; k < 2 * this.A.length; ++k) {
                    s = GF2Field.multElem(this.A[i][k], s3);
                    s = GF2Field.multElem(s2, s);
                    this.A[j][k] = GF2Field.addElem(this.A[j][k], s);
                }
            }
        }
    }

    private void substitute() throws IllegalStateException {
        short s = GF2Field.invElem(this.A[this.A.length - 1][this.A.length - 1]);
        if (s == 0) {
            throw new IllegalStateException("The equation system is not solvable");
        }
        this.x[this.A.length - 1] = GF2Field.multElem(this.A[this.A.length - 1][this.A.length], s);
        for (int i = this.A.length - 2; i >= 0; --i) {
            short s2 = this.A[i][this.A.length];
            for (int j = this.A.length - 1; j > i; --j) {
                s = GF2Field.multElem(this.A[i][j], this.x[j]);
                s2 = GF2Field.addElem(s2, s);
            }
            s = GF2Field.invElem(this.A[i][i]);
            if (s == 0) {
                throw new IllegalStateException("Not solvable equation system");
            }
            this.x[i] = GF2Field.multElem(s2, s);
        }
    }

    public short[][] multiplyMatrix(short[][] sArray, short[][] sArray2) throws RuntimeException {
        if (sArray[0].length != sArray2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short s = 0;
        this.A = new short[sArray.length][sArray2[0].length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray2.length; ++j) {
                for (int k = 0; k < sArray2[0].length; ++k) {
                    s = GF2Field.multElem(sArray[i][j], sArray2[j][k]);
                    this.A[i][k] = GF2Field.addElem(this.A[i][k], s);
                }
            }
        }
        return this.A;
    }

    public short[] multiplyMatrix(short[][] sArray, short[] sArray2) throws RuntimeException {
        if (sArray[0].length != sArray2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short s = 0;
        short[] sArray3 = new short[sArray.length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray2.length; ++j) {
                s = GF2Field.multElem(sArray[i][j], sArray2[j]);
                sArray3[i] = GF2Field.addElem(sArray3[i], s);
            }
        }
        return sArray3;
    }

    public short[] addVect(short[] sArray, short[] sArray2) {
        if (sArray.length != sArray2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] sArray3 = new short[sArray.length];
        for (int i = 0; i < sArray3.length; ++i) {
            sArray3[i] = GF2Field.addElem(sArray[i], sArray2[i]);
        }
        return sArray3;
    }

    public short[][] multVects(short[] sArray, short[] sArray2) {
        if (sArray.length != sArray2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[][] sArray3 = new short[sArray.length][sArray2.length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray2.length; ++j) {
                sArray3[i][j] = GF2Field.multElem(sArray[i], sArray2[j]);
            }
        }
        return sArray3;
    }

    public short[] multVect(short s, short[] sArray) {
        short[] sArray2 = new short[sArray.length];
        for (int i = 0; i < sArray2.length; ++i) {
            sArray2[i] = GF2Field.multElem(s, sArray[i]);
        }
        return sArray2;
    }

    public short[][] multMatrix(short s, short[][] sArray) {
        short[][] sArray2 = new short[sArray.length][sArray[0].length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray[0].length; ++j) {
                sArray2[i][j] = GF2Field.multElem(s, sArray[i][j]);
            }
        }
        return sArray2;
    }

    public short[][] addSquareMatrix(short[][] sArray, short[][] sArray2) {
        if (sArray.length != sArray2.length || sArray[0].length != sArray2[0].length) {
            throw new RuntimeException("Addition is not possible!");
        }
        short[][] sArray3 = new short[sArray.length][sArray.length];
        for (int i = 0; i < sArray.length; ++i) {
            for (int j = 0; j < sArray2.length; ++j) {
                sArray3[i][j] = GF2Field.addElem(sArray[i][j], sArray2[i][j]);
            }
        }
        return sArray3;
    }
}

