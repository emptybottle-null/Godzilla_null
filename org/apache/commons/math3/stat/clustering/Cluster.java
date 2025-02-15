/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.clustering.Clusterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Deprecated
public class Cluster<T extends Clusterable<T>>
implements Serializable {
    private static final long serialVersionUID = -3442297081515880464L;
    private final List<T> points;
    private final T center;

    public Cluster(T center) {
        this.center = center;
        this.points = new ArrayList<T>();
    }

    public void addPoint(T point) {
        this.points.add(point);
    }

    public List<T> getPoints() {
        return this.points;
    }

    public T getCenter() {
        return this.center;
    }
}

