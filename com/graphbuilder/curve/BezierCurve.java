/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.BinaryCurveApproximationAlgorithm;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ParametricCurve;
import com.graphbuilder.math.PascalsTriangle;

public class BezierCurve
extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();
    private final PascalsTriangle pascalsTriangle = new PascalsTriangle();
    private double t_min = 0.0;
    private double t_max = 1.0;
    private int sampleLimit = 1;

    public BezierCurve(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    public void eval(double[] p) {
        int i;
        double t = p[p.length - 1];
        int numPts = this.gi.getGroupSize();
        if (numPts > this.sharedData.a.length) {
            SharedData.access$102(this.sharedData, new double[2 * numPts]);
        }
        ((SharedData)this.sharedData).a[numPts - 1] = 1.0;
        double b = 1.0;
        double one_minus_t = 1.0 - t;
        for (i = numPts - 2; i >= 0; --i) {
            ((SharedData)this.sharedData).a[i] = this.sharedData.a[i + 1] * one_minus_t;
        }
        this.gi.set(0, 0);
        for (i = 0; i < numPts; ++i) {
            double pt = this.pascalsTriangle.nCr(numPts - 1, i);
            if (!Double.isInfinite(pt) && !Double.isNaN(pt)) {
                double gravity = this.sharedData.a[i] * b * pt;
                double[] d = this.cp.getPoint(this.gi.next()).getLocation();
                for (int j = 0; j < p.length - 1; ++j) {
                    p[j] = p[j] + d[j] * gravity;
                }
            }
            b *= t;
        }
    }

    public int getSampleLimit() {
        return this.sampleLimit;
    }

    public void setSampleLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Sample-limit >= 0 required.");
        }
        this.sampleLimit = limit;
    }

    public void setInterval(double t_min, double t_max) {
        if (t_min > t_max) {
            throw new IllegalArgumentException("t_min <= t_max required.");
        }
        this.t_min = t_min;
        this.t_max = t_max;
    }

    public double t_min() {
        return this.t_min;
    }

    public double t_max() {
        return this.t_max;
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("group iterator not in range");
        }
        int n = mp.getDimension();
        double[] d = new double[n + 1];
        d[n] = this.t_min;
        this.eval(d);
        if (this.connect) {
            mp.lineTo(d);
        } else {
            mp.moveTo(d);
        }
        BinaryCurveApproximationAlgorithm.genPts(this, this.t_min, this.t_max, mp);
    }

    public void resetMemory() {
        if (this.sharedData.a.length > 0) {
            SharedData.access$102(this.sharedData, new double[0]);
        }
    }

    private static class SharedData {
        private double[] a = new double[0];

        private SharedData() {
        }

        static /* synthetic */ double[] access$102(SharedData x0, double[] x1) {
            x0.a = x1;
            return x1;
        }
    }
}

