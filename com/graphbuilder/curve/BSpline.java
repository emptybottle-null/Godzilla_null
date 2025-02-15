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

public class BSpline
extends ParametricCurve {
    public static final int UNIFORM_CLAMPED = 0;
    public static final int UNIFORM_UNCLAMPED = 1;
    public static final int NON_UNIFORM = 2;
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();
    private ValueVector knotVector = new ValueVector(new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0}, 8);
    private double t_min = 0.0;
    private double t_max = 1.0;
    private int sampleLimit = 1;
    private int degree = 4;
    private int knotVectorType = 0;
    private boolean useDefaultInterval = true;

    public BSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    protected void eval(double[] p) {
        int dim = p.length - 1;
        double t = p[dim];
        int numPts = this.gi.getGroupSize();
        this.gi.set(0, 0);
        for (int i = 0; i < numPts; ++i) {
            double w = this.N(t, i);
            double[] loc = this.cp.getPoint(this.gi.next()).getLocation();
            for (int j = 0; j < dim; ++j) {
                int n = j;
                p[n] = p[n] + loc[j] * w;
            }
        }
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

    public int getSampleLimit() {
        return this.sampleLimit;
    }

    public void setSampleLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Sample-limit >= 0 required.");
        }
        this.sampleLimit = limit;
    }

    public int getDegree() {
        return this.degree - 1;
    }

    public void setDegree(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("Degree > 0 required.");
        }
        this.degree = d + 1;
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

    public boolean getUseDefaultInterval() {
        return this.useDefaultInterval;
    }

    public void setUseDefaultInterval(boolean b) {
        this.useDefaultInterval = b;
    }

    public int getKnotVectorType() {
        return this.knotVectorType;
    }

    public void setKnotVectorType(int type) {
        if (type < 0 || type > 2) {
            throw new IllegalArgumentException("Unknown knot-vector type.");
        }
        this.knotVectorType = type;
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        int numPts = this.gi.getGroupSize();
        int f = numPts - this.degree;
        if (f < 0) {
            throw new IllegalArgumentException("group iterator size - degree < 0");
        }
        int x = numPts + this.degree;
        if (this.sharedData.knot.length < x) {
            SharedData.access$102(this.sharedData, new double[2 * x]);
        }
        double t1 = this.t_min;
        double t2 = this.t_max;
        if (this.knotVectorType == 2) {
            if (this.knotVector.size() != x) {
                throw new IllegalArgumentException("knotVector.size(" + this.knotVector.size() + ") != " + x);
            }
            ((SharedData)this.sharedData).knot[0] = this.knotVector.get(0);
            for (int i = 1; i < x; ++i) {
                ((SharedData)this.sharedData).knot[i] = this.knotVector.get(i);
                if (!(this.sharedData.knot[i] < this.sharedData.knot[i - 1])) continue;
                throw new IllegalArgumentException("Knot not in sorted order! (knot[" + i + "] < knot[" + i + "-1])");
            }
        } else if (this.knotVectorType == 1) {
            double grad = 1.0 / (double)(x - 1);
            for (int i = 0; i < x; ++i) {
                ((SharedData)this.sharedData).knot[i] = (double)i * grad;
            }
            if (this.useDefaultInterval) {
                t1 = (double)(this.degree - 1) * grad;
                t2 = 1.0 - (double)(this.degree - 1) * grad;
            }
        } else if (this.knotVectorType == 0) {
            int i;
            double grad = 1.0 / (double)(f + 1);
            for (int i2 = 0; i2 < this.degree; ++i2) {
                ((SharedData)this.sharedData).knot[i2] = 0.0;
            }
            int j = this.degree;
            for (i = 1; i <= f; ++i) {
                ((SharedData)this.sharedData).knot[j++] = (double)i * grad;
            }
            for (i = j; i < x; ++i) {
                ((SharedData)this.sharedData).knot[i] = 1.0;
            }
            if (this.useDefaultInterval) {
                t1 = 0.0;
                t2 = 1.0;
            }
        }
        if (this.sharedData.a.length < this.degree) {
            SharedData.access$202(this.sharedData, new int[2 * this.degree]);
            SharedData.access$302(this.sharedData, new int[2 * this.degree]);
        }
        double[] p = new double[mp.getDimension() + 1];
        p[mp.getDimension()] = t1;
        this.eval(p);
        if (this.connect) {
            mp.lineTo(p);
        } else {
            mp.moveTo(p);
        }
        BinaryCurveApproximationAlgorithm.genPts(this, t1, t2, mp);
    }

    protected double N(double t, int i) {
        double d = 0.0;
        block0: for (int j = 0; j < this.degree; ++j) {
            int k;
            double t1 = this.sharedData.knot[i + j];
            double t2 = this.sharedData.knot[i + j + 1];
            if (!(t >= t1) || !(t <= t2) || t1 == t2) continue;
            int dm2 = this.degree - 2;
            for (k = this.degree - j - 1; k >= 0; --k) {
                ((SharedData)this.sharedData).a[k] = 0;
            }
            if (j > 0) {
                for (k = 0; k < j; ++k) {
                    ((SharedData)this.sharedData).c[k] = k;
                }
                ((SharedData)this.sharedData).c[j] = Integer.MAX_VALUE;
            } else {
                ((SharedData)this.sharedData).c[0] = dm2;
                ((SharedData)this.sharedData).c[1] = this.degree;
            }
            int z = 0;
            while (true) {
                if (this.sharedData.c[z] < this.sharedData.c[z + 1] - 1) {
                    double e = 1.0;
                    int bc = 0;
                    int y = dm2 - j;
                    int p = j - 1;
                    int m = dm2;
                    int n = this.degree;
                    while (m >= 0) {
                        int w;
                        if (p >= 0 && this.sharedData.c[p] == m) {
                            w = i + bc;
                            double kd = this.sharedData.knot[w + n];
                            e *= (kd - t) / (kd - this.sharedData.knot[w + 1]);
                            ++bc;
                            --p;
                        } else {
                            w = i + this.sharedData.a[y];
                            double kw = this.sharedData.knot[w];
                            e *= (t - kw) / (this.sharedData.knot[w + n - 1] - kw);
                            --y;
                        }
                        --m;
                        --n;
                    }
                    if (j > 0) {
                        int g = 0;
                        boolean reset = false;
                        while (true) {
                            int[] nArray = this.sharedData.a;
                            int n2 = ++g;
                            nArray[n2] = nArray[n2] + 1;
                            if (this.sharedData.a[g] <= j) break;
                            reset = true;
                        }
                        if (reset) {
                            for (int h = g - 1; h >= 0; --h) {
                                ((SharedData)this.sharedData).a[h] = this.sharedData.a[g];
                            }
                        }
                    }
                    d += e;
                    int[] nArray = this.sharedData.c;
                    int n3 = z;
                    nArray[n3] = nArray[n3] + 1;
                    if (this.sharedData.c[z] > dm2) break block0;
                    for (int k2 = 0; k2 < z; ++k2) {
                        ((SharedData)this.sharedData).c[k2] = k2;
                    }
                    z = 0;
                    continue;
                }
                ++z;
            }
        }
        return d;
    }

    public void resetMemory() {
        if (this.sharedData.a.length > 0) {
            SharedData.access$202(this.sharedData, new int[0]);
            SharedData.access$302(this.sharedData, new int[0]);
        }
        if (this.sharedData.knot.length > 0) {
            SharedData.access$102(this.sharedData, new double[0]);
        }
    }

    private static class SharedData {
        private int[] a = new int[0];
        private int[] c = new int[0];
        private double[] knot = new double[0];

        private SharedData() {
        }

        static /* synthetic */ double[] access$102(SharedData x0, double[] x1) {
            x0.knot = x1;
            return x1;
        }

        static /* synthetic */ int[] access$202(SharedData x0, int[] x1) {
            x0.a = x1;
            return x1;
        }

        static /* synthetic */ int[] access$302(SharedData x0, int[] x1) {
            x0.c = x1;
            return x1;
        }
    }
}

