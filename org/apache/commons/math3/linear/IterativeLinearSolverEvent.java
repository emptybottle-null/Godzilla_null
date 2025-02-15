/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.IterationEvent;

public abstract class IterativeLinearSolverEvent
extends IterationEvent {
    private static final long serialVersionUID = 20120129L;

    public IterativeLinearSolverEvent(Object source, int iterations) {
        super(source, iterations);
    }

    public abstract RealVector getRightHandSideVector();

    public abstract double getNormOfResidual();

    public RealVector getResidual() {
        throw new MathUnsupportedOperationException();
    }

    public abstract RealVector getSolution();

    public boolean providesResidual() {
        return false;
    }
}

