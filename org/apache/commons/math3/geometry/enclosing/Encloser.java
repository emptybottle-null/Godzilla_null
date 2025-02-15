/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.enclosing;

import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.enclosing.EnclosingBall;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Encloser<S extends Space, P extends Point<S>> {
    public EnclosingBall<S, P> enclose(Iterable<P> var1);
}

