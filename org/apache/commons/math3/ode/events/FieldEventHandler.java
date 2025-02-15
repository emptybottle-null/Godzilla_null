/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.events.Action;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldEventHandler<T extends RealFieldElement<T>> {
    public void init(FieldODEStateAndDerivative<T> var1, T var2);

    public T g(FieldODEStateAndDerivative<T> var1);

    public Action eventOccurred(FieldODEStateAndDerivative<T> var1, boolean var2);

    public FieldODEState<T> resetState(FieldODEStateAndDerivative<T> var1);
}

