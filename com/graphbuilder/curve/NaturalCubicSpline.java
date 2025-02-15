/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.BinaryCurveApproximationAlgorithm;
import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ParametricCurve;

public class NaturalCubicSpline
extends ParametricCurve {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();
    private boolean closed = false;

    public NaturalCubicSpline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    protected void eval(double[] p) {
        int n = p.length - 1;
        double t = p[n];
        double t2 = t * t;
        double t3 = t2 * t;
        int j = 0;
        for (int i = 0; i < n; ++i) {
            p[i] = this.sharedData.data[j++][this.sharedData.ci] + this.sharedData.data[j++][this.sharedData.ci] * t + this.sharedData.data[j++][this.sharedData.ci] * t2 + this.sharedData.data[j++][this.sharedData.ci] * t3;
        }
    }

    private void precalc(int n, int dim, boolean closed) {
        --n;
        double[] a = this.sharedData.data[4 * dim];
        double[] b = this.sharedData.data[4 * dim + 1];
        double[] c = this.sharedData.data[4 * dim + 2];
        int k = 0;
        if (closed) {
            double[] d = this.sharedData.data[4 * dim + 3];
            for (int j = 0; j < dim; ++j) {
                int i;
                double e = 0.25;
                a[1] = 0.25;
                d[1] = 0.25;
                b[0] = e * 3.0 * (this.sharedData.pt[1][j] - this.sharedData.pt[n][j]);
                double h = 4.0;
                double f = 3.0 * (this.sharedData.pt[0][j] - this.sharedData.pt[n - 1][j]);
                double g = 1.0;
                for (i = 1; i < n; ++i) {
                    a[i + 1] = e = 1.0 / (4.0 - a[i]);
                    d[i + 1] = -e * d[i];
                    b[i] = e * (3.0 * (this.sharedData.pt[i + 1][j] - this.sharedData.pt[i - 1][j]) - b[i - 1]);
                    h -= g * d[i];
                    f -= g * b[i - 1];
                    g = -a[i] * g;
                }
                b[n] = f - (g + 1.0) * b[n - 1];
                c[n] = b[n] / (h -= (g + 1.0) * (a[n] + d[n]));
                c[n - 1] = b[n - 1] - (a[n] + d[n]) * c[n];
                for (i = n - 2; i >= 0; --i) {
                    c[i] = b[i] - a[i + 1] * c[i + 1] - d[i + 1] * c[n];
                }
                double[] w = this.sharedData.data[k++];
                double[] x = this.sharedData.data[k++];
                double[] y = this.sharedData.data[k++];
                double[] z = this.sharedData.data[k++];
                for (int i2 = 0; i2 < n; ++i2) {
                    w[i2] = this.sharedData.pt[i2][j];
                    x[i2] = c[i2];
                    y[i2] = 3.0 * (this.sharedData.pt[i2 + 1][j] - this.sharedData.pt[i2][j]) - 2.0 * c[i2] - c[i2 + 1];
                    z[i2] = 2.0 * (this.sharedData.pt[i2][j] - this.sharedData.pt[i2 + 1][j]) + c[i2] + c[i2 + 1];
                }
                w[n] = this.sharedData.pt[n][j];
                x[n] = c[n];
                y[n] = 3.0 * (this.sharedData.pt[0][j] - this.sharedData.pt[n][j]) - 2.0 * c[n] - c[0];
                z[n] = 2.0 * (this.sharedData.pt[n][j] - this.sharedData.pt[0][j]) + c[n] + c[0];
            }
        } else {
            for (int j = 0; j < dim; ++j) {
                int i;
                a[0] = 0.5;
                for (i = 1; i < n; ++i) {
                    a[i] = 1.0 / (4.0 - a[i - 1]);
                }
                a[n] = 1.0 / (2.0 - a[n - 1]);
                b[0] = a[0] * (3.0 * (this.sharedData.pt[1][j] - this.sharedData.pt[0][j]));
                for (i = 1; i < n; ++i) {
                    b[i] = a[i] * (3.0 * (this.sharedData.pt[i + 1][j] - this.sharedData.pt[i - 1][j]) - b[i - 1]);
                }
                b[n] = a[n] * (3.0 * (this.sharedData.pt[n][j] - this.sharedData.pt[n - 1][j]) - b[n - 1]);
                c[n] = b[n];
                for (i = n - 1; i >= 0; --i) {
                    c[i] = b[i] - a[i] * c[i + 1];
                }
                double[] w = this.sharedData.data[k++];
                double[] x = this.sharedData.data[k++];
                double[] y = this.sharedData.data[k++];
                double[] z = this.sharedData.data[k++];
                for (int i3 = 0; i3 < n; ++i3) {
                    w[i3] = this.sharedData.pt[i3][j];
                    x[i3] = c[i3];
                    y[i3] = 3.0 * (this.sharedData.pt[i3 + 1][j] - this.sharedData.pt[i3][j]) - 2.0 * c[i3] - c[i3 + 1];
                    z[i3] = 2.0 * (this.sharedData.pt[i3][j] - this.sharedData.pt[i3 + 1][j]) + c[i3] + c[i3 + 1];
                }
                w[n] = this.sharedData.pt[n][j];
                x[n] = 0.0;
                y[n] = 0.0;
                z[n] = 0.0;
            }
        }
    }

    public void setClosed(boolean b) {
        this.closed = b;
    }

    public boolean getClosed() {
        return this.closed;
    }

    public int getSampleLimit() {
        return 1;
    }

    public void appendTo(MultiPath mp) {
        int i;
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        int n = this.gi.getGroupSize();
        if (n < 2) {
            throw new IllegalArgumentException("Group iterator size < 2");
        }
        int dim = mp.getDimension();
        int x = 3 + 4 * dim + 1;
        if (this.sharedData.data.length < x) {
            double[][] temp = new double[x][];
            for (i = 0; i < this.sharedData.data.length; ++i) {
                temp[i] = this.sharedData.data[i];
            }
            SharedData.access$102(this.sharedData, temp);
        }
        if (this.sharedData.pt.length < n) {
            int m = 2 * n;
            SharedData.access$302(this.sharedData, new double[m][]);
            for (i = 0; i < this.sharedData.data.length; ++i) {
                ((SharedData)this.sharedData).data[i] = new double[m];
            }
        }
        this.gi.set(0, 0);
        for (int i2 = 0; i2 < n; ++i2) {
            ((SharedData)this.sharedData).pt[i2] = this.cp.getPoint(this.gi.next()).getLocation();
        }
        this.precalc(n, dim, this.closed);
        this.sharedData.ci = 0;
        double[] p = new double[dim + 1];
        this.eval(p);
        if (this.connect) {
            mp.lineTo(p);
        } else {
            mp.moveTo(p);
        }
        for (i = 0; i < n; ++i) {
            this.sharedData.ci = i;
            BinaryCurveApproximationAlgorithm.genPts(this, 0.0, 1.0, mp);
        }
    }

    public void resetMemory() {
        if (this.sharedData.pt.length > 0) {
            SharedData.access$302(this.sharedData, new double[0][]);
        }
        if (this.sharedData.data.length > 0) {
            SharedData.access$102(this.sharedData, new double[0][]);
        }
    }

    private static class SharedData {
        private double[][] pt = new double[0][];
        private double[][] data = new double[0][];
        private int ci = 0;

        private SharedData() {
        }

        static /* synthetic */ double[][] access$102(SharedData x0, double[][] x1) {
            x0.data = x1;
            return x1;
        }

        static /* synthetic */ double[][] access$302(SharedData x0, double[][] x1) {
            x0.pt = x1;
            return x1;
        }
    }
}

