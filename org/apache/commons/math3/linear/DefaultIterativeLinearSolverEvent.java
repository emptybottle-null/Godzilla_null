/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.linear.IterativeLinearSolverEvent;
import org.apache.commons.math3.linear.RealVector;

public class DefaultIterativeLinearSolverEvent
extends IterativeLinearSolverEvent {
    private static final long serialVersionUID = 20120129L;
    private final RealVector b;
    private final RealVector r;
    private final double rnorm;
    private final RealVector x;

    public DefaultIterativeLinearSolverEvent(Object source, int iterations, RealVector x, RealVector b, RealVector r, double rnorm) {
        super(source, iterations);
        this.x = x;
        this.b = b;
        this.r = r;
        this.rnorm = rnorm;
    }

    public DefaultIterativeLinearSolverEvent(Object source, int iterations, RealVector x, RealVector b, double rnorm) {
        super(source, iterations);
        this.x = x;
        this.b = b;
        this.r = null;
        this.rnorm = rnorm;
    }

    public double getNormOfResidual() {
        return this.rnorm;
    }

    public RealVector getResidual() {
        if (this.r != null) {
            return this.r;
        }
        throw new MathUnsupportedOperationException();
    }

    public RealVector getRightHandSideVector() {
        return this.b;
    }

    public RealVector getSolution() {
        return this.x;
    }

    public boolean providesResidual() {
        return this.r != null;
    }
}

