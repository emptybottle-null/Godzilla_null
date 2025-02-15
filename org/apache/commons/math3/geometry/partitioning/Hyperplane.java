/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Hyperplane<S extends Space> {
    public Hyperplane<S> copySelf();

    public double getOffset(Point<S> var1);

    public Point<S> project(Point<S> var1);

    public double getTolerance();

    public boolean sameOrientationAs(Hyperplane<S> var1);

    public SubHyperplane<S> wholeHyperplane();

    public Region<S> wholeSpace();
}

