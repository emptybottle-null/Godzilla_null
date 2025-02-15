/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.hull;

import java.io.Serializable;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.partitioning.Region;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface ConvexHull<S extends Space, P extends Point<S>>
extends Serializable {
    public P[] getVertices();

    public Region<S> createRegion() throws InsufficientDataException;
}

