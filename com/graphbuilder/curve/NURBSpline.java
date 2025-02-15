/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.BSpline;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ValueVector;

public class NURBSpline
extends BSpline {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();
    private ValueVector weightVector = new ValueVector(new double[]{1.0, 1.0, 1.0, 1.0}, 4);
    private boolean useWeightVector = true;

    public NURBSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    protected void eval(double[] p) {
        int i;
        int dim = p.length - 1;
        double t = p[dim];
        double sum2 = 0.0;
        int numPts = this.gi.getGroupSize();
        for (i = 0; i < numPts; ++i) {
            ((SharedData)this.sharedData).nw[i] = this.N(t, i) * this.sharedData.weight[i];
            sum2 += this.sharedData.nw[i];
        }
        if (sum2 == 0.0) {
            sum2 = 1.0;
        }
        for (i = 0; i < dim; ++i) {
            double sum1 = 0.0;
            this.gi.set(0, 0);
            for (int j = 0; j < numPts; ++j) {
                sum1 += this.sharedData.nw[j] * this.cp.getPoint(this.gi.next()).getLocation()[i];
            }
            p[i] = sum1 / sum2;
        }
    }

    public ValueVector getWeightVector() {
        return this.weightVector;
    }

    public void setWeightVector(ValueVector v) {
        if (v == null) {
            throw new IllegalArgumentException("Weight-vector cannot be null.");
        }
        this.weightVector = v;
    }

    public boolean getUseWeightVector() {
        return this.useWeightVector;
    }

    public void setUseWeightVector(boolean b) {
        this.useWeightVector = b;
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        int numPts = this.gi.getGroupSize();
        if (this.sharedData.nw.length < numPts) {
            SharedData.access$102(this.sharedData, new double[2 * numPts]);
            SharedData.access$202(this.sharedData, new double[2 * numPts]);
        }
        if (this.useWeightVector) {
            if (this.weightVector.size() != numPts) {
                throw new IllegalArgumentException("weightVector.size(" + this.weightVector.size() + ") != group iterator size(" + numPts + ")");
            }
            for (int i = 0; i < numPts; ++i) {
                ((SharedData)this.sharedData).weight[i] = this.weightVector.get(i);
                if (!(this.sharedData.weight[i] < 0.0)) continue;
                throw new IllegalArgumentException("Negative weight not allowed");
            }
        } else {
            for (int i = 0; i < numPts; ++i) {
                ((SharedData)this.sharedData).weight[i] = 1.0;
            }
        }
        super.appendTo(mp);
    }

    public void resetMemory() {
        super.resetMemory();
        if (this.sharedData.nw.length > 0) {
            SharedData.access$102(this.sharedData, new double[0]);
            SharedData.access$202(this.sharedData, new double[0]);
        }
    }

    private static class SharedData {
        private double[] nw = new double[0];
        private double[] weight = new double[0];

        private SharedData() {
        }

        static /* synthetic */ double[] access$102(SharedData x0, double[] x1) {
            x0.nw = x1;
            return x1;
        }

        static /* synthetic */ double[] access$202(SharedData x0, double[] x1) {
            x0.weight = x1;
            return x1;
        }
    }
}

