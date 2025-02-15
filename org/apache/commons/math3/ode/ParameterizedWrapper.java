/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.ParameterizedODE;
import org.apache.commons.math3.ode.UnknownParameterException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class ParameterizedWrapper
implements ParameterizedODE {
    private final FirstOrderDifferentialEquations fode;

    ParameterizedWrapper(FirstOrderDifferentialEquations ode) {
        this.fode = ode;
    }

    public int getDimension() {
        return this.fode.getDimension();
    }

    public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
        this.fode.computeDerivatives(t, y, yDot);
    }

    @Override
    public Collection<String> getParametersNames() {
        return new ArrayList<String>();
    }

    @Override
    public boolean isSupported(String name) {
        return false;
    }

    @Override
    public double getParameter(String name) throws UnknownParameterException {
        if (!this.isSupported(name)) {
            throw new UnknownParameterException(name);
        }
        return Double.NaN;
    }

    @Override
    public void setParameter(String name, double value) {
    }
}

