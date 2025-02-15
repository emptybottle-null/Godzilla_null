/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class BoundaryProjection<S extends Space> {
    private final Point<S> original;
    private final Point<S> projected;
    private final double offset;

    public BoundaryProjection(Point<S> original, Point<S> projected, double offset) {
        this.original = original;
        this.projected = projected;
        this.offset = offset;
    }

    public Point<S> getOriginal() {
        return this.original;
    }

    public Point<S> getProjected() {
        return this.projected;
    }

    public double getOffset() {
        return this.offset;
    }
}

