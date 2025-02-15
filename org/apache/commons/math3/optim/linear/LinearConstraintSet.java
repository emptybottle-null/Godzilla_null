/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.linear.LinearConstraint;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LinearConstraintSet
implements OptimizationData {
    private final Set<LinearConstraint> linearConstraints = new LinkedHashSet<LinearConstraint>();

    public LinearConstraintSet(LinearConstraint ... constraints) {
        for (LinearConstraint c : constraints) {
            this.linearConstraints.add(c);
        }
    }

    public LinearConstraintSet(Collection<LinearConstraint> constraints) {
        this.linearConstraints.addAll(constraints);
    }

    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableSet(this.linearConstraints);
    }
}

