/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.analysis.function.Sqrt;
import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.NonSquareOperatorException;
import org.apache.commons.math3.linear.RealLinearOperator;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.MathArrays;

public class JacobiPreconditioner
extends RealLinearOperator {
    private final ArrayRealVector diag;

    public JacobiPreconditioner(double[] diag, boolean deep) {
        this.diag = new ArrayRealVector(diag, deep);
    }

    public static JacobiPreconditioner create(RealLinearOperator a) throws NonSquareOperatorException {
        int n = a.getColumnDimension();
        if (a.getRowDimension() != n) {
            throw new NonSquareOperatorException(a.getRowDimension(), n);
        }
        double[] diag = new double[n];
        if (a instanceof AbstractRealMatrix) {
            AbstractRealMatrix m = (AbstractRealMatrix)a;
            for (int i = 0; i < n; ++i) {
                diag[i] = m.getEntry(i, i);
            }
        } else {
            ArrayRealVector x = new ArrayRealVector(n);
            for (int i = 0; i < n; ++i) {
                x.set(0.0);
                x.setEntry(i, 1.0);
                diag[i] = a.operate(x).getEntry(i);
            }
        }
        return new JacobiPreconditioner(diag, false);
    }

    public int getColumnDimension() {
        return this.diag.getDimension();
    }

    public int getRowDimension() {
        return this.diag.getDimension();
    }

    public RealVector operate(RealVector x) {
        return new ArrayRealVector(MathArrays.ebeDivide(x.toArray(), this.diag.toArray()), false);
    }

    public RealLinearOperator sqrt() {
        final ArrayRealVector sqrtDiag = this.diag.map(new Sqrt());
        return new RealLinearOperator(){

            public RealVector operate(RealVector x) {
                return new ArrayRealVector(MathArrays.ebeDivide(x.toArray(), sqrtDiag.toArray()), false);
            }

            public int getRowDimension() {
                return sqrtDiag.getDimension();
            }

            public int getColumnDimension() {
                return sqrtDiag.getDimension();
            }
        };
    }
}

