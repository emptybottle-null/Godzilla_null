/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FirstOrderFieldDifferentialEquations<T extends RealFieldElement<T>> {
    public int getDimension();

    public void init(T var1, T[] var2, T var3);

    public T[] computeDerivatives(T var1, T[] var2);
}

