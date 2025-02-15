/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;

public interface DecompositionSolver {
    public RealVector solve(RealVector var1) throws SingularMatrixException;

    public RealMatrix solve(RealMatrix var1) throws SingularMatrixException;

    public boolean isNonSingular();

    public RealMatrix getInverse() throws SingularMatrixException;
}

