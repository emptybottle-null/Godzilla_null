/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.math;

public final class PascalsTriangle {
    private static final ThreadLocal<SharedData> SHARED_DATA = new ThreadLocal<SharedData>(){

        @Override
        protected SharedData initialValue() {
            return new SharedData();
        }
    };
    private final SharedData sharedData = SHARED_DATA.get();

    public double nCr(int n, int r) {
        if (n < 0 || r < 0 || r > n) {
            return 0.0;
        }
        if (n >= this.sharedData.pt.length) {
            int i;
            int d = 2 * this.sharedData.pt.length;
            Object pt2 = null;
            pt2 = n > d ? (Object)new double[n + 1][] : (Object)new double[d + 1][];
            for (i = 0; i < this.sharedData.pt.length; ++i) {
                pt2[i] = this.sharedData.pt[i];
            }
            for (i = this.sharedData.pt.length; i < ((double[][])pt2).length; ++i) {
                pt2[i] = new double[i / 2 + 1];
                pt2[i][0] = 1.0;
                for (int j = 1; j < pt2[i].length; ++j) {
                    double x = pt2[i - 1][j - 1];
                    x = j < pt2[i - 1].length ? (x += pt2[i - 1][j]) : 2.0 * x;
                    pt2[i][j] = x;
                }
            }
            SharedData.access$102(this.sharedData, (double[][])pt2);
        }
        if (2 * r > n) {
            r = n - r;
        }
        return this.sharedData.pt[n][r];
    }

    public void reset() {
        SharedData.access$102(this.sharedData, new double[][]{{1.0}});
    }

    private static class SharedData {
        private double[][] pt = new double[][]{{1.0}};

        private SharedData() {
        }

        static /* synthetic */ double[][] access$102(SharedData x0, double[][] x1) {
            x0.pt = x1;
            return x1;
        }
    }
}

