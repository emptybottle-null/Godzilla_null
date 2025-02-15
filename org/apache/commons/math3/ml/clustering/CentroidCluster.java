/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.clustering;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.Clusterable;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CentroidCluster<T extends Clusterable>
extends Cluster<T> {
    private static final long serialVersionUID = -3075288519071812288L;
    private final Clusterable center;

    public CentroidCluster(Clusterable center) {
        this.center = center;
    }

    public Clusterable getCenter() {
        return this.center;
    }
}

