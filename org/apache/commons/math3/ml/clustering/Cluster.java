/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.ml.clustering.Clusterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Cluster<T extends Clusterable>
implements Serializable {
    private static final long serialVersionUID = -3442297081515880464L;
    private final List<T> points = new ArrayList<T>();

    public void addPoint(T point) {
        this.points.add(point);
    }

    public List<T> getPoints() {
        return this.points;
    }
}

