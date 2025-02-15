/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldFixedStepHandler<T extends RealFieldElement<T>> {
    public void init(FieldODEStateAndDerivative<T> var1, T var2);

    public void handleStep(FieldODEStateAndDerivative<T> var1, boolean var2);
}

