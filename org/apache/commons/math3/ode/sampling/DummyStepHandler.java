/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

public class DummyStepHandler
implements StepHandler {
    private DummyStepHandler() {
    }

    public static DummyStepHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init(double t0, double[] y0, double t) {
    }

    public void handleStep(StepInterpolator interpolator, boolean isLast) {
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final DummyStepHandler INSTANCE = new DummyStepHandler();

        private LazyHolder() {
        }
    }
}

