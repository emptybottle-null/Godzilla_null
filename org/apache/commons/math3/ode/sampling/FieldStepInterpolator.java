/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldStepInterpolator<T extends RealFieldElement<T>> {
    public FieldODEStateAndDerivative<T> getPreviousState();

    public FieldODEStateAndDerivative<T> getCurrentState();

    public FieldODEStateAndDerivative<T> getInterpolatedState(T var1);

    public boolean isForward();
}

