/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.RealFieldElement;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface FieldButcherArrayProvider<T extends RealFieldElement<T>> {
    public T[] getC();

    public T[][] getA();

    public T[] getB();
}

