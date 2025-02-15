/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MultiDimensionMismatchException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MatrixDimensionMismatchException
extends MultiDimensionMismatchException {
    private static final long serialVersionUID = -8415396756375798143L;

    public MatrixDimensionMismatchException(int wrongRowDim, int wrongColDim, int expectedRowDim, int expectedColDim) {
        super((Localizable)LocalizedFormats.DIMENSIONS_MISMATCH_2x2, new Integer[]{wrongRowDim, wrongColDim}, new Integer[]{expectedRowDim, expectedColDim});
    }

    public int getWrongRowDimension() {
        return this.getWrongDimension(0);
    }

    public int getExpectedRowDimension() {
        return this.getExpectedDimension(0);
    }

    public int getWrongColumnDimension() {
        return this.getWrongDimension(1);
    }

    public int getExpectedColumnDimension() {
        return this.getExpectedDimension(1);
    }
}

