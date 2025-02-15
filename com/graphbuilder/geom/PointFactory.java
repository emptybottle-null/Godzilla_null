/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.geom;

import com.graphbuilder.geom.Point2d;

public class PointFactory {
    public static Point2d create(double x, double y) {
        return new Point2D(x, y);
    }

    static class Point2D
    implements Point2d {
        double[] pts;

        public Point2D(double x, double y) {
            this.pts = new double[]{x, y};
        }

        public double getX() {
            return this.pts[0];
        }

        public double getY() {
            return this.pts[1];
        }

        public void setLocation(double[] p) {
            this.pts[0] = p[0];
            this.pts[1] = p[1];
        }

        public void setLocation(double x, double y) {
            this.pts[0] = x;
            this.pts[1] = y;
        }

        public double[] getLocation() {
            return this.pts;
        }
    }
}

