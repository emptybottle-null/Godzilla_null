/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry;

import java.io.Serializable;
import org.apache.commons.math3.geometry.Space;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Point<S extends Space>
extends Serializable {
    public Space getSpace();

    public boolean isNaN();

    public double distance(Point<S> var1);
}

