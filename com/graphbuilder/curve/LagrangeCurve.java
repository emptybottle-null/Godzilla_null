/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.BinaryCurveApproximationAlgorithm;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ParametricCurve;
import com.graphbuilder.curve.ValueVector;

public class LagrangeCurve
extends ParametricCurve {
    private ValueVector knotVector = new ValueVector(new double[]{0.0, 0.3333333333333333, 0.6666666666666666, 1.0}, 4);
    private int baseIndex = 1;
    private int baseLength = 1;
    private boolean interpolateFirst = false;
    private boolean interpolateLast = false;
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();

    public LagrangeCurve(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    public int getBaseIndex() {
        return this.baseIndex;
    }

    public void setBaseIndex(int b) {
        if (b < 0) {
            throw new IllegalArgumentException("base index >= 0 required.");
        }
        this.baseIndex = b;
    }

    public int getBaseLength() {
        return this.baseLength;
    }

    public void setBaseLength(int b) {
        if (b <= 0) {
            throw new IllegalArgumentException("base length > 0 required.");
        }
        this.baseLength = b;
    }

    public boolean getInterpolateFirst() {
        return this.interpolateFirst;
    }

    public boolean getInterpolateLast() {
        return this.interpolateLast;
    }

    public void setInterpolateFirst(boolean b) {
        this.interpolateFirst = b;
    }

    public void setInterpolateLast(boolean b) {
        this.interpolateLast = b;
    }

    public ValueVector getKnotVector() {
        return this.knotVector;
    }

    public void setKnotVector(ValueVector v) {
        if (v == null) {
            throw new IllegalArgumentException("Knot-vector cannot be null.");
        }
        this.knotVector = v;
    }

    public int getSampleLimit() {
        return 1;
    }

    protected void eval(double[] p) {
        double t = p[p.length - 1];
        int n = this.knotVector.size();
        for (int i = 0; i < n; ++i) {
            double[] q = this.sharedData.pt[i];
            double L = this.L(t, i);
            for (int j = 0; j < p.length - 1; ++j) {
                int n2 = j;
                p[n2] = p[n2] + q[j] * L;
            }
        }
    }

    private double L(double t, int i) {
        double d = 1.0;
        int n = this.knotVector.size();
        for (int j = 0; j < n; ++j) {
            double e = this.knotVector.get(i) - this.knotVector.get(j);
            if (e == 0.0) continue;
            d *= (t - this.knotVector.get(j)) / e;
        }
        return d;
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        if (this.baseIndex + this.baseLength >= this.knotVector.size()) {
            throw new IllegalArgumentException("baseIndex + baseLength >= knotVector.size");
        }
        if (this.sharedData.pt.length < this.knotVector.size()) {
            SharedData.access$102(this.sharedData, new double[2 * this.knotVector.size()][]);
        }
        this.gi.set(0, 0);
        boolean b = false;
        if (this.baseIndex != 0 && this.interpolateFirst) {
            for (int i = 0; i < this.knotVector.size(); ++i) {
                if (!this.gi.hasNext()) {
                    throw new IllegalArgumentException("Group iterator ended early");
                }
                ((SharedData)this.sharedData).pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
            }
            b = this.doBCAA(mp, this.knotVector.get(0), this.knotVector.get(this.baseIndex), b);
        }
        this.gi.set(0, 0);
        int last_i = 0;
        int last_j = 0;
        while (true) {
            int j;
            int temp_i = this.gi.index_i();
            int temp_j = this.gi.count_j();
            int index_i = 0;
            int count_j = 0;
            int i = 0;
            for (j = 0; j < this.knotVector.size(); ++j) {
                if (i == this.baseLength) {
                    index_i = this.gi.index_i();
                    count_j = this.gi.count_j();
                }
                if (!this.gi.hasNext()) break;
                ((SharedData)this.sharedData).pt[j] = this.cp.getPoint(this.gi.next()).getLocation();
                ++i;
            }
            if (j < this.knotVector.size()) break;
            this.gi.set(index_i, count_j);
            last_i = temp_i;
            last_j = temp_j;
            b = this.doBCAA(mp, this.knotVector.get(this.baseIndex), this.knotVector.get(this.baseIndex + this.baseLength), b);
        }
        if (this.baseIndex + this.baseLength < this.knotVector.size() - 1 && this.interpolateLast) {
            this.gi.set(last_i, last_j);
            for (int i = 0; i < this.knotVector.size(); ++i) {
                if (!this.gi.hasNext()) {
                    System.out.println("not enough points to interpolate last");
                    return;
                }
                ((SharedData)this.sharedData).pt[i] = this.cp.getPoint(this.gi.next()).getLocation();
            }
            this.doBCAA(mp, this.knotVector.get(this.baseIndex + this.baseLength), this.knotVector.get(this.knotVector.size() - 1), b);
        }
    }

    private boolean doBCAA(MultiPath mp, double t1, double t2, boolean b) {
        if (t2 < t1) {
            double temp = t1;
            t1 = t2;
            t2 = temp;
        }
        if (!b) {
            b = true;
            double[] d = new double[mp.getDimension() + 1];
            d[mp.getDimension()] = t1;
            this.eval(d);
            if (this.connect) {
                mp.lineTo(d);
            } else {
                mp.moveTo(d);
            }
        }
        BinaryCurveApproximationAlgorithm.genPts(this, t1, t2, mp);
        return b;
    }

    public void resetMemory() {
        if (this.sharedData.pt.length > 0) {
            SharedData.access$102(this.sharedData, new double[0][]);
        }
    }

    private static class SharedData {
        private double[][] pt = new double[0][];

        private SharedData() {
        }

        static /* synthetic */ double[][] access$102(SharedData x0, double[][] x1) {
            x0.pt = x1;
            return x1;
        }
    }
}

