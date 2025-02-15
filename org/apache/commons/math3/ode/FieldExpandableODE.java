/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldSecondaryEquations;
import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;
import org.apache.commons.math3.util.MathArrays;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldExpandableODE<T extends RealFieldElement<T>> {
    private final FirstOrderFieldDifferentialEquations<T> primary;
    private List<FieldSecondaryEquations<T>> components;
    private FieldEquationsMapper<T> mapper;

    public FieldExpandableODE(FirstOrderFieldDifferentialEquations<T> primary) {
        this.primary = primary;
        this.components = new ArrayList<FieldSecondaryEquations<T>>();
        this.mapper = new FieldEquationsMapper(null, primary.getDimension());
    }

    public FieldEquationsMapper<T> getMapper() {
        return this.mapper;
    }

    public int addSecondaryEquations(FieldSecondaryEquations<T> secondary) {
        this.components.add(secondary);
        this.mapper = new FieldEquationsMapper<T>(this.mapper, secondary.getDimension());
        return this.components.size();
    }

    public void init(T t0, T[] y0, T finalTime) {
        int index = 0;
        RealFieldElement[] primary0 = this.mapper.extractEquationData(index, (RealFieldElement[])y0);
        this.primary.init((RealFieldElement)t0, primary0, (RealFieldElement)finalTime);
        while (++index < this.mapper.getNumberOfEquations()) {
            RealFieldElement[] secondary0 = this.mapper.extractEquationData(index, (RealFieldElement[])y0);
            this.components.get(index - 1).init((RealFieldElement)t0, primary0, secondary0, (RealFieldElement)finalTime);
        }
    }

    public T[] computeDerivatives(T t, T[] y) throws MaxCountExceededException, DimensionMismatchException {
        RealFieldElement[] yDot = (RealFieldElement[])MathArrays.buildArray(t.getField(), this.mapper.getTotalDimension());
        int index = 0;
        RealFieldElement[] primaryState = this.mapper.extractEquationData(index, (RealFieldElement[])y);
        RealFieldElement[] primaryStateDot = this.primary.computeDerivatives((RealFieldElement)t, primaryState);
        this.mapper.insertEquationData(index, primaryStateDot, yDot);
        while (++index < this.mapper.getNumberOfEquations()) {
            RealFieldElement[] componentState = this.mapper.extractEquationData(index, (RealFieldElement[])y);
            RealFieldElement[] componentStateDot = this.components.get(index - 1).computeDerivatives((RealFieldElement)t, primaryState, primaryStateDot, componentState);
            this.mapper.insertEquationData(index, componentStateDot, yDot);
        }
        return yDot;
    }
}

