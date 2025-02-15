/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode.events;

import java.util.Arrays;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.events.FilterType;
import org.apache.commons.math3.ode.events.Transformer;

public class EventFilter
implements EventHandler {
    private static final int HISTORY_SIZE = 100;
    private final EventHandler rawHandler;
    private final FilterType filter;
    private final Transformer[] transformers;
    private final double[] updates;
    private boolean forward;
    private double extremeT;

    public EventFilter(EventHandler rawHandler, FilterType filter) {
        this.rawHandler = rawHandler;
        this.filter = filter;
        this.transformers = new Transformer[100];
        this.updates = new double[100];
    }

    public void init(double t0, double[] y0, double t) {
        this.rawHandler.init(t0, y0, t);
        this.forward = t >= t0;
        this.extremeT = this.forward ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        Arrays.fill((Object[])this.transformers, (Object)Transformer.UNINITIALIZED);
        Arrays.fill(this.updates, this.extremeT);
    }

    public double g(double t, double[] y) {
        double rawG = this.rawHandler.g(t, y);
        if (this.forward) {
            int last = this.transformers.length - 1;
            if (this.extremeT < t) {
                Transformer previous = this.transformers[last];
                Transformer next = this.filter.selectTransformer(previous, rawG, this.forward);
                if (next != previous) {
                    System.arraycopy(this.updates, 1, this.updates, 0, last);
                    System.arraycopy(this.transformers, 1, this.transformers, 0, last);
                    this.updates[last] = this.extremeT;
                    this.transformers[last] = next;
                }
                this.extremeT = t;
                return next.transformed(rawG);
            }
            for (int i = last; i > 0; --i) {
                if (!(this.updates[i] <= t)) continue;
                return this.transformers[i].transformed(rawG);
            }
            return this.transformers[0].transformed(rawG);
        }
        if (t < this.extremeT) {
            Transformer previous = this.transformers[0];
            Transformer next = this.filter.selectTransformer(previous, rawG, this.forward);
            if (next != previous) {
                System.arraycopy(this.updates, 0, this.updates, 1, this.updates.length - 1);
                System.arraycopy(this.transformers, 0, this.transformers, 1, this.transformers.length - 1);
                this.updates[0] = this.extremeT;
                this.transformers[0] = next;
            }
            this.extremeT = t;
            return next.transformed(rawG);
        }
        for (int i = 0; i < this.updates.length - 1; ++i) {
            if (!(t <= this.updates[i])) continue;
            return this.transformers[i].transformed(rawG);
        }
        return this.transformers[this.updates.length - 1].transformed(rawG);
    }

    public EventHandler.Action eventOccurred(double t, double[] y, boolean increasing) {
        return this.rawHandler.eventOccurred(t, y, this.filter.getTriggeredIncreasing());
    }

    public void resetState(double t, double[] y) {
        this.rawHandler.resetState(t, y);
    }
}

