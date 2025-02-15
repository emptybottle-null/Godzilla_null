/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEState;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class FieldODEStateAndDerivative<T extends RealFieldElement<T>>
extends FieldODEState<T> {
    private final T[] derivative;
    private final T[][] secondaryDerivative;

    public FieldODEStateAndDerivative(T time, T[] state, T[] derivative) {
        this((RealFieldElement)time, (RealFieldElement[])state, (RealFieldElement[])derivative, null, null);
    }

    public FieldODEStateAndDerivative(T time, T[] state, T[] derivative, T[][] secondaryState, T[][] secondaryDerivative) {
        super(time, state, secondaryState);
        this.derivative = (RealFieldElement[])derivative.clone();
        this.secondaryDerivative = this.copy(time.getField(), (RealFieldElement[][])secondaryDerivative);
    }

    public T[] getDerivative() {
        return (RealFieldElement[])this.derivative.clone();
    }

    public T[] getSecondaryDerivative(int index) {
        return index == 0 ? (RealFieldElement[])this.derivative.clone() : (RealFieldElement[])this.secondaryDerivative[index - 1].clone();
    }
}

