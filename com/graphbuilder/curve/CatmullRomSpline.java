/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.BinaryCurveApproximationAlgorithm;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ParametricCurve;

public class CatmullRomSpline
extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();

    public CatmullRomSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    protected void eval(double[] p) {
        double t = p[p.length - 1];
        double t2 = t * t;
        double t3 = t2 * t;
        for (int i = 0; i < p.length - 1; ++i) {
            p[i] = 0.5 * ((this.sharedData.pt[3][i] - this.sharedData.pt[0][i] + 3.0 * (this.sharedData.pt[1][i] - this.sharedData.pt[2][i])) * t3 + (2.0 * (this.sharedData.pt[0][i] + 2.0 * this.sharedData.pt[2][i]) - 5.0 * this.sharedData.pt[1][i] - this.sharedData.pt[3][i]) * t2 + (this.sharedData.pt[2][i] - this.sharedData.pt[0][i]) * t) + this.sharedData.pt[1][i];
        }
    }

    public int getSampleLimit() {
        return 1;
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        if (this.gi.getGroupSize() < 4) {
            throw new IllegalArgumentException("Group iterator size < 4");
        }
        this.gi.set(0, 0);
        for (int i = 0; i < 4; ++i) {
            ((SharedData)this.sharedData).pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
        }
        double[] d = new double[mp.getDimension() + 1];
        this.eval(d);
        if (this.connect) {
            mp.lineTo(d);
        } else {
            mp.moveTo(d);
        }
        this.gi.set(0, 0);
        while (true) {
            int index_i = this.gi.index_i();
            int count_j = this.gi.count_j();
            for (int i = 0; i < 4; ++i) {
                if (!this.gi.hasNext()) {
                    return;
                }
                ((SharedData)this.sharedData).pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
            }
            this.gi.set(index_i, count_j);
            this.gi.next();
            BinaryCurveApproximationAlgorithm.genPts(this, 0.0, 1.0, mp);
        }
    }

    private static class SharedData {
        private double[][] pt = new double[4][];

        private SharedData() {
        }
    }
}

