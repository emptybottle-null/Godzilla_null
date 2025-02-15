/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.enclosing;

import java.io.Serializable;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class EnclosingBall<S extends Space, P extends Point<S>>
implements Serializable {
    private static final long serialVersionUID = 20140126L;
    private final P center;
    private final double radius;
    private final P[] support;

    public EnclosingBall(P center, double radius, P ... support) {
        this.center = center;
        this.radius = radius;
        this.support = (Point[])support.clone();
    }

    public P getCenter() {
        return this.center;
    }

    public double getRadius() {
        return this.radius;
    }

    public P[] getSupport() {
        return (Point[])this.support.clone();
    }

    public int getSupportSize() {
        return this.support.length;
    }

    public boolean contains(P point) {
        return point.distance(this.center) <= this.radius;
    }

    public boolean contains(P point, double margin) {
        return point.distance(this.center) <= this.radius + margin;
    }
}

