/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Transform<S extends Space, T extends Space> {
    public Point<S> apply(Point<S> var1);

    public Hyperplane<S> apply(Hyperplane<S> var1);

    public SubHyperplane<T> apply(SubHyperplane<T> var1, Hyperplane<S> var2, Hyperplane<S> var3);
}

