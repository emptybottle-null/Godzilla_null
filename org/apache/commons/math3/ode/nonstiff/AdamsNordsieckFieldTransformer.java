/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AdamsNordsieckFieldTransformer<T extends RealFieldElement<T>> {
    private static final Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> CACHE = new HashMap();
    private final Field<T> field;
    private final Array2DRowFieldMatrix<T> update;
    private final T[] c1;

    private AdamsNordsieckFieldTransformer(Field<T> field, int n) {
        this.field = field;
        int rows = n - 1;
        FieldMatrix<T> bigP = this.buildP(rows);
        FieldDecompositionSolver<T> pSolver = new FieldLUDecomposition<T>(bigP).getSolver();
        Object[] u = (RealFieldElement[])MathArrays.buildArray(field, rows);
        Arrays.fill(u, field.getOne());
        this.c1 = (RealFieldElement[])pSolver.solve(new ArrayFieldVector((FieldElement[])u, false)).toArray();
        FieldElement[][] shiftedP = (RealFieldElement[][])bigP.getData();
        for (int i = shiftedP.length - 1; i > 0; --i) {
            shiftedP[i] = shiftedP[i - 1];
        }
        shiftedP[0] = (RealFieldElement[])MathArrays.buildArray(field, rows);
        Arrays.fill(shiftedP[0], field.getZero());
        this.update = new Array2DRowFieldMatrix(pSolver.solve(new Array2DRowFieldMatrix(shiftedP, false)).getData());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static <T extends RealFieldElement<T>> AdamsNordsieckFieldTransformer<T> getInstance(Field<T> field, int nSteps) {
        Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> map = CACHE;
        synchronized (map) {
            AdamsNordsieckFieldTransformer<RealFieldElement<Object>> t;
            Map<Field<RealFieldElement<?>>, AdamsNordsieckFieldTransformer<RealFieldElement<?>>> map2 = CACHE.get(nSteps);
            if (map2 == null) {
                map2 = new HashMap();
                CACHE.put(nSteps, map2);
            }
            if ((t = map2.get(field)) == null) {
                t = new AdamsNordsieckFieldTransformer<T>(field, nSteps);
                map2.put(field, t);
            }
            return t;
        }
    }

    private FieldMatrix<T> buildP(int rows) {
        FieldElement[][] pData = (RealFieldElement[][])MathArrays.buildArray(this.field, rows, rows);
        for (int i = 1; i <= pData.length; ++i) {
            RealFieldElement[] pI = pData[i - 1];
            int factor = -i;
            RealFieldElement aj = (RealFieldElement)((RealFieldElement)this.field.getZero()).add(factor);
            for (int j = 1; j <= pI.length; ++j) {
                pI[j - 1] = (RealFieldElement)aj.multiply(j + 1);
                aj = (RealFieldElement)aj.multiply(factor);
            }
        }
        return new Array2DRowFieldMatrix(pData, false);
    }

    public Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(T h, T[] t, T[][] y, T[][] yDot) {
        FieldElement[][] a = (RealFieldElement[][])MathArrays.buildArray(this.field, this.c1.length + 1, this.c1.length + 1);
        FieldElement[][] b = (RealFieldElement[][])MathArrays.buildArray(this.field, this.c1.length + 1, y[0].length);
        T[] y0 = y[0];
        T[] yDot0 = yDot[0];
        for (int i = 1; i < y.length; ++i) {
            RealFieldElement di = (RealFieldElement)t[i].subtract(t[0]);
            RealFieldElement ratio = (RealFieldElement)di.divide(h);
            RealFieldElement dikM1Ohk = (RealFieldElement)h.reciprocal();
            FieldElement[] aI = a[2 * i - 2];
            FieldElement[] aDotI = 2 * i - 1 < a.length ? a[2 * i - 1] : null;
            for (int j = 0; j < aI.length; ++j) {
                dikM1Ohk = dikM1Ohk.multiply(ratio);
                aI[j] = di.multiply(dikM1Ohk);
                if (aDotI == null) continue;
                aDotI[j] = (RealFieldElement)dikM1Ohk.multiply(j + 2);
            }
            T[] yI = y[i];
            T[] yDotI = yDot[i];
            FieldElement[] bI = b[2 * i - 2];
            FieldElement[] bDotI = 2 * i - 1 < b.length ? b[2 * i - 1] : null;
            for (int j = 0; j < yI.length; ++j) {
                bI[j] = (RealFieldElement)((RealFieldElement)yI[j].subtract(y0[j])).subtract(di.multiply(yDot0[j]));
                if (bDotI == null) continue;
                bDotI[j] = (RealFieldElement)yDotI[j].subtract(yDot0[j]);
            }
        }
        FieldLUDecomposition decomposition = new FieldLUDecomposition(new Array2DRowFieldMatrix(a, false));
        FieldMatrix x = decomposition.getSolver().solve(new Array2DRowFieldMatrix(b, false));
        Array2DRowFieldMatrix truncatedX = new Array2DRowFieldMatrix(this.field, x.getRowDimension() - 1, x.getColumnDimension());
        for (int i = 0; i < truncatedX.getRowDimension(); ++i) {
            for (int j = 0; j < truncatedX.getColumnDimension(); ++j) {
                truncatedX.setEntry(i, j, x.getEntry(i, j));
            }
        }
        return truncatedX;
    }

    public Array2DRowFieldMatrix<T> updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix<T> highOrder) {
        return this.update.multiply(highOrder);
    }

    public void updateHighOrderDerivativesPhase2(T[] start, T[] end, Array2DRowFieldMatrix<T> highOrder) {
        RealFieldElement[][] data = (RealFieldElement[][])highOrder.getDataRef();
        for (int i = 0; i < data.length; ++i) {
            RealFieldElement[] dataI = data[i];
            T c1I = this.c1[i];
            for (int j = 0; j < dataI.length; ++j) {
                dataI[j] = (RealFieldElement)dataI[j].add(c1I.multiply(start[j].subtract(end[j])));
            }
        }
    }
}

