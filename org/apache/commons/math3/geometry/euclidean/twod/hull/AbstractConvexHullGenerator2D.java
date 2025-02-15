/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.Collection;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHull2D;
import org.apache.commons.math3.geometry.euclidean.twod.hull.ConvexHullGenerator2D;
import org.apache.commons.math3.util.MathUtils;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
abstract class AbstractConvexHullGenerator2D
implements ConvexHullGenerator2D {
    private static final double DEFAULT_TOLERANCE = 1.0E-10;
    private final double tolerance;
    private final boolean includeCollinearPoints;

    protected AbstractConvexHullGenerator2D(boolean includeCollinearPoints) {
        this(includeCollinearPoints, 1.0E-10);
    }

    protected AbstractConvexHullGenerator2D(boolean includeCollinearPoints, double tolerance) {
        this.includeCollinearPoints = includeCollinearPoints;
        this.tolerance = tolerance;
    }

    public double getTolerance() {
        return this.tolerance;
    }

    public boolean isIncludeCollinearPoints() {
        return this.includeCollinearPoints;
    }

    @Override
    public ConvexHull2D generate(Collection<Vector2D> points) throws NullArgumentException, ConvergenceException {
        MathUtils.checkNotNull(points);
        Collection<Vector2D> hullVertices = null;
        hullVertices = points.size() < 2 ? points : this.findHullVertices(points);
        try {
            return new ConvexHull2D(hullVertices.toArray(new Vector2D[hullVertices.size()]), this.tolerance);
        } catch (MathIllegalArgumentException e) {
            throw new ConvergenceException();
        }
    }

    protected abstract Collection<Vector2D> findHullVertices(Collection<Vector2D> var1);
}

