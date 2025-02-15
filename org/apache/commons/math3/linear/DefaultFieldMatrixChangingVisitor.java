/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.linear.FieldMatrixChangingVisitor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DefaultFieldMatrixChangingVisitor<T extends FieldElement<T>>
implements FieldMatrixChangingVisitor<T> {
    private final T zero;

    public DefaultFieldMatrixChangingVisitor(T zero) {
        this.zero = zero;
    }

    @Override
    public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
    }

    @Override
    public T visit(int row, int column, T value) {
        return value;
    }

    @Override
    public T end() {
        return this.zero;
    }
}

