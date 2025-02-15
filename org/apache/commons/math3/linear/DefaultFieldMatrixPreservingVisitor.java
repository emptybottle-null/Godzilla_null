/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DefaultFieldMatrixPreservingVisitor<T extends FieldElement<T>>
implements FieldMatrixPreservingVisitor<T> {
    private final T zero;

    public DefaultFieldMatrixPreservingVisitor(T zero) {
        this.zero = zero;
    }

    @Override
    public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
    }

    @Override
    public void visit(int row, int column, T value) {
    }

    @Override
    public T end() {
        return this.zero;
    }
}

