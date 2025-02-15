/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry;

import java.text.NumberFormat;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Vector<S extends Space>
extends Point<S> {
    public Vector<S> getZero();

    public double getNorm1();

    public double getNorm();

    public double getNormSq();

    public double getNormInf();

    public Vector<S> add(Vector<S> var1);

    public Vector<S> add(double var1, Vector<S> var3);

    public Vector<S> subtract(Vector<S> var1);

    public Vector<S> subtract(double var1, Vector<S> var3);

    public Vector<S> negate();

    public Vector<S> normalize() throws MathArithmeticException;

    public Vector<S> scalarMultiply(double var1);

    public boolean isInfinite();

    public double distance1(Vector<S> var1);

    @Override
    public double distance(Vector<S> var1);

    public double distanceInf(Vector<S> var1);

    public double distanceSq(Vector<S> var1);

    public double dotProduct(Vector<S> var1);

    public String toString(NumberFormat var1);
}

