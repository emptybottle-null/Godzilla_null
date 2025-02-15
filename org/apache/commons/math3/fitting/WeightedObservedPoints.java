/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.fitting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class WeightedObservedPoints
implements Serializable {
    private static final long serialVersionUID = 20130813L;
    private final List<WeightedObservedPoint> observations = new ArrayList<WeightedObservedPoint>();

    public void add(double x, double y) {
        this.add(1.0, x, y);
    }

    public void add(double weight, double x, double y) {
        this.observations.add(new WeightedObservedPoint(weight, x, y));
    }

    public void add(WeightedObservedPoint observed) {
        this.observations.add(observed);
    }

    public List<WeightedObservedPoint> toList() {
        return new ArrayList<WeightedObservedPoint>(this.observations);
    }

    public void clear() {
        this.observations.clear();
    }
}

