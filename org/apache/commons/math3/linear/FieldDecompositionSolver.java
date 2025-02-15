/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.FieldVector;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldDecompositionSolver<T extends FieldElement<T>> {
    public FieldVector<T> solve(FieldVector<T> var1);

    public FieldMatrix<T> solve(FieldMatrix<T> var1);

    public boolean isNonSingular();

    public FieldMatrix<T> getInverse();
}

