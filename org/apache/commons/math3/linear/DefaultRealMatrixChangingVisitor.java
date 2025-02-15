/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.linear.RealMatrixChangingVisitor;

public class DefaultRealMatrixChangingVisitor
implements RealMatrixChangingVisitor {
    public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
    }

    public double visit(int row, int column, double value) {
        return value;
    }

    public double end() {
        return 0.0;
    }
}

