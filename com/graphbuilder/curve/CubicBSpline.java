/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.BinaryCurveApproximationAlgorithm;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ParametricCurve;

public class CubicBSpline
extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();
    private boolean interpolateEndpoints = false;

    public CubicBSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    protected void eval(double[] p) {
        double t = p[p.length - 1];
        double t2 = t * t;
        double t3 = t2 * t;
        double u = 1.0 - t;
        double u2 = u * u;
        double u3 = u2 * u;
        if (this.sharedData.numPoints == 4) {
            ((SharedData)this.sharedData).b[0] = u2 * u;
            ((SharedData)this.sharedData).b[1] = 3.0 * u2 * t;
            ((SharedData)this.sharedData).b[2] = 3.0 * u * t2;
            ((SharedData)this.sharedData).b[3] = t3;
        } else if (this.sharedData.numPoints == 5) {
            if (this.sharedData.section == 0) {
                ((SharedData)this.sharedData).b[0] = u3;
                ((SharedData)this.sharedData).b[1] = 7.0 * t3 / 4.0 - 9.0 * t2 / 2.0 + 3.0 * t;
                ((SharedData)this.sharedData).b[2] = -t3 + 3.0 * t2 / 2.0;
                ((SharedData)this.sharedData).b[3] = t3 / 4.0;
            } else {
                ((SharedData)this.sharedData).b[0] = u3 / 4.0;
                ((SharedData)this.sharedData).b[1] = -u3 + 3.0 * u2 / 2.0;
                ((SharedData)this.sharedData).b[2] = 7.0 * u3 / 4.0 - 9.0 * u2 / 2.0 + 3.0 * u;
                ((SharedData)this.sharedData).b[3] = t3;
            }
        } else if (this.sharedData.numPoints == 6) {
            if (this.sharedData.section == 0) {
                ((SharedData)this.sharedData).b[0] = u3;
                ((SharedData)this.sharedData).b[1] = 7.0 * t3 / 4.0 - 9.0 * t2 / 2.0 + 3.0 * t;
                ((SharedData)this.sharedData).b[2] = -11.0 * t3 / 12.0 + 3.0 * t2 / 2.0;
                ((SharedData)this.sharedData).b[3] = t3 / 6.0;
            } else if (this.sharedData.section == 1) {
                ((SharedData)this.sharedData).b[0] = u3 / 4.0;
                ((SharedData)this.sharedData).b[1] = 7.0 * t3 / 12.0 - 5.0 * t2 / 4.0 + t / 4.0 + 0.5833333333333334;
                ((SharedData)this.sharedData).b[2] = -7.0 * t3 / 12.0 + t2 / 2.0 + t / 2.0 + 0.16666666666666666;
                ((SharedData)this.sharedData).b[3] = t3 / 4.0;
            } else {
                ((SharedData)this.sharedData).b[0] = u3 / 6.0;
                ((SharedData)this.sharedData).b[1] = -11.0 * u3 / 12.0 + 3.0 * u2 / 2.0;
                ((SharedData)this.sharedData).b[2] = 7.0 * u3 / 4.0 - 9.0 * u2 / 2.0 + 3.0 * u;
                ((SharedData)this.sharedData).b[3] = t3;
            }
        } else if (this.sharedData.section == 0) {
            ((SharedData)this.sharedData).b[0] = u3;
            ((SharedData)this.sharedData).b[1] = 7.0 * t3 / 4.0 - 9.0 * t2 / 2.0 + 3.0 * t;
            ((SharedData)this.sharedData).b[2] = -11.0 * t3 / 12.0 + 3.0 * t2 / 2.0;
            ((SharedData)this.sharedData).b[3] = t3 / 6.0;
        } else if (this.sharedData.section == 1) {
            ((SharedData)this.sharedData).b[0] = u3 / 4.0;
            ((SharedData)this.sharedData).b[1] = 7.0 * t3 / 12.0 - 5.0 * t2 / 4.0 + t / 4.0 + 0.5833333333333334;
            ((SharedData)this.sharedData).b[2] = -t3 / 2.0 + t2 / 2.0 + t / 2.0 + 0.16666666666666666;
            ((SharedData)this.sharedData).b[3] = t3 / 6.0;
        } else if (this.sharedData.section == 2) {
            ((SharedData)this.sharedData).b[0] = u3 / 6.0;
            ((SharedData)this.sharedData).b[1] = t3 / 2.0 - t2 + 0.6666666666666666;
            ((SharedData)this.sharedData).b[2] = (-t3 + t2 + t) / 2.0 + 0.16666666666666666;
            ((SharedData)this.sharedData).b[3] = t3 / 6.0;
        } else if (this.sharedData.section == 3) {
            ((SharedData)this.sharedData).b[0] = u3 / 6.0;
            ((SharedData)this.sharedData).b[1] = -u3 / 2.0 + u2 / 2.0 + u / 2.0 + 0.16666666666666666;
            ((SharedData)this.sharedData).b[2] = 7.0 * u3 / 12.0 - 5.0 * u2 / 4.0 + u / 4.0 + 0.5833333333333334;
            ((SharedData)this.sharedData).b[3] = t3 / 4.0;
        } else {
            ((SharedData)this.sharedData).b[0] = u3 / 6.0;
            ((SharedData)this.sharedData).b[1] = -11.0 * u3 / 12.0 + 3.0 * u2 / 2.0;
            ((SharedData)this.sharedData).b[2] = 7.0 * u3 / 4.0 - 9.0 * u2 / 2.0 + 3.0 * u;
            ((SharedData)this.sharedData).b[3] = t3;
        }
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < p.length - 1; ++j) {
                p[j] = p[j] + this.sharedData.pt[i][j] * this.sharedData.b[i];
            }
        }
    }

    public int getSampleLimit() {
        return 1;
    }

    public void setInterpolateEndpoints(boolean b) {
        this.interpolateEndpoints = b;
    }

    public boolean getInterpolateEndpoints() {
        return this.interpolateEndpoints;
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        int n = this.gi.getGroupSize();
        if (n < 4) {
            throw new IllegalArgumentException("Group iterator size < 4");
        }
        if (this.interpolateEndpoints) {
            this.sharedData.numPoints = n;
            this.sharedData.section = 0;
        } else {
            this.sharedData.numPoints = -1;
            this.sharedData.section = 2;
        }
        this.gi.set(0, 0);
        int index_i = 0;
        int count_j = 0;
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
        int j = 3;
        while (true) {
            BinaryCurveApproximationAlgorithm.genPts(this, 0.0, 1.0, mp);
            if (++j == n) break;
            this.gi.set(index_i, count_j);
            this.gi.next();
            index_i = this.gi.index_i();
            count_j = this.gi.count_j();
            for (int i = 0; i < 4; ++i) {
                ((SharedData)this.sharedData).pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
            }
            if (!this.interpolateEndpoints) continue;
            if (n < 7) {
                this.sharedData.section++;
                continue;
            }
            if (this.sharedData.section != 2) {
                this.sharedData.section++;
            }
            if (this.sharedData.section != 2 || j != n - 2) continue;
            this.sharedData.section++;
        }
    }

    private static class SharedData {
        private int section = 0;
        private int numPoints = 0;
        private double[][] pt = new double[4][];
        private double[] b = new double[4];

        private SharedData() {
        }
    }
}

