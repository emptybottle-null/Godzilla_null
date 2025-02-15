/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.io.Serializable;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldEquationsMapper<T extends RealFieldElement<T>>
implements Serializable {
    private static final long serialVersionUID = 20151114L;
    private final int[] start;

    FieldEquationsMapper(FieldEquationsMapper<T> mapper, int dimension) {
        int index = mapper == null ? 0 : mapper.getNumberOfEquations();
        this.start = new int[index + 2];
        if (mapper == null) {
            this.start[0] = 0;
        } else {
            System.arraycopy(mapper.start, 0, this.start, 0, index + 1);
        }
        this.start[index + 1] = this.start[index] + dimension;
    }

    public int getNumberOfEquations() {
        return this.start.length - 1;
    }

    public int getTotalDimension() {
        return this.start[this.start.length - 1];
    }

    public T[] mapState(FieldODEState<T> state) {
        RealFieldElement[] y = (RealFieldElement[])MathArrays.buildArray(state.getTime().getField(), this.getTotalDimension());
        int index = 0;
        this.insertEquationData(index, state.getState(), y);
        while (++index < this.getNumberOfEquations()) {
            this.insertEquationData(index, state.getSecondaryState(index), y);
        }
        return y;
    }

    public T[] mapDerivative(FieldODEStateAndDerivative<T> state) {
        RealFieldElement[] yDot = (RealFieldElement[])MathArrays.buildArray(state.getTime().getField(), this.getTotalDimension());
        int index = 0;
        this.insertEquationData(index, state.getDerivative(), yDot);
        while (++index < this.getNumberOfEquations()) {
            this.insertEquationData(index, state.getSecondaryDerivative(index), yDot);
        }
        return yDot;
    }

    public FieldODEStateAndDerivative<T> mapStateAndDerivative(T t, T[] y, T[] yDot) throws DimensionMismatchException {
        if (y.length != this.getTotalDimension()) {
            throw new DimensionMismatchException(y.length, this.getTotalDimension());
        }
        if (yDot.length != this.getTotalDimension()) {
            throw new DimensionMismatchException(yDot.length, this.getTotalDimension());
        }
        int n = this.getNumberOfEquations();
        int index = 0;
        RealFieldElement[] state = this.extractEquationData(index, (RealFieldElement[])y);
        RealFieldElement[] derivative = this.extractEquationData(index, (RealFieldElement[])yDot);
        if (n < 2) {
            return new FieldODEStateAndDerivative(t, state, derivative);
        }
        RealFieldElement[][] secondaryState = (RealFieldElement[][])MathArrays.buildArray(t.getField(), n - 1, -1);
        RealFieldElement[][] secondaryDerivative = (RealFieldElement[][])MathArrays.buildArray(t.getField(), n - 1, -1);
        while (++index < this.getNumberOfEquations()) {
            secondaryState[index - 1] = this.extractEquationData(index, (RealFieldElement[])y);
            secondaryDerivative[index - 1] = this.extractEquationData(index, (RealFieldElement[])yDot);
        }
        return new FieldODEStateAndDerivative(t, state, derivative, secondaryState, secondaryDerivative);
    }

    public T[] extractEquationData(int index, T[] complete) throws MathIllegalArgumentException, DimensionMismatchException {
        this.checkIndex(index);
        int begin = this.start[index];
        int end = this.start[index + 1];
        if (complete.length < end) {
            throw new DimensionMismatchException(complete.length, end);
        }
        int dimension = end - begin;
        RealFieldElement[] equationData = (RealFieldElement[])MathArrays.buildArray(complete[0].getField(), dimension);
        System.arraycopy(complete, begin, equationData, 0, dimension);
        return equationData;
    }

    public void insertEquationData(int index, T[] equationData, T[] complete) throws DimensionMismatchException {
        this.checkIndex(index);
        int begin = this.start[index];
        int end = this.start[index + 1];
        int dimension = end - begin;
        if (complete.length < end) {
            throw new DimensionMismatchException(complete.length, end);
        }
        if (equationData.length != dimension) {
            throw new DimensionMismatchException(equationData.length, dimension);
        }
        System.arraycopy(equationData, 0, complete, begin, dimension);
    }

    private void checkIndex(int index) throws MathIllegalArgumentException {
        if (index < 0 || index > this.start.length - 2) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, index, 0, this.start.length - 2);
        }
    }
}

