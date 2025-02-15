/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.geom;

import com.graphbuilder.geom.Point2d;

public final class Geom {
    public static final Object PARALLEL = new Object();
    public static final Object INTERSECT = new Object();

    private Geom() {
    }

    public static double getAngle(double originX, double originY, double x, double y) {
        double adj = x - originX;
        double opp = y - originY;
        double rad = 0.0;
        if (adj == 0.0) {
            if (opp == 0.0) {
                return 0.0;
            }
            rad = 1.5707963267948966;
        } else {
            rad = Math.atan(opp / adj);
            if (rad < 0.0) {
                rad = -rad;
            }
        }
        if (x >= originX) {
            if (y < originY) {
                rad = Math.PI * 2 - rad;
            }
        } else {
            rad = y < originY ? Math.PI + rad : Math.PI - rad;
        }
        return rad;
    }

    public static double getAngle(Point2d origin, Point2d p) {
        return Geom.getAngle(origin.getX(), origin.getY(), p.getX(), p.getY());
    }

    public static double ptLineDistSq(double x1, double y1, double x2, double y2, double x, double y, double[] result) {
        double run = x2 - x1;
        double rise = y2 - y1;
        double t = 0.0;
        double f = run * run + rise * rise;
        if (f != 0.0) {
            t = (run * (x - x1) + rise * (y - y1)) / f;
        }
        double nx = x1 + t * run;
        double ny = y1 + t * rise;
        if (result != null) {
            result[0] = nx;
            result[1] = ny;
            result[2] = t;
        }
        double dx = x - nx;
        double dy = y - ny;
        return dx * dx + dy * dy;
    }

    public static double ptSegDistSq(double x1, double y1, double x2, double y2, double x, double y, double[] result) {
        double run = x2 - x1;
        double rise = y2 - y1;
        double t = 0.0;
        double f = run * run + rise * rise;
        if (f != 0.0) {
            t = (run * (x - x1) + rise * (y - y1)) / f;
        }
        if (t < 0.0) {
            t = 0.0;
        } else if (t > 1.0) {
            t = 1.0;
        }
        double nx = x1 + t * run;
        double ny = y1 + t * rise;
        if (result != null) {
            result[0] = nx;
            result[1] = ny;
            result[2] = t;
        }
        double dx = x - nx;
        double dy = y - ny;
        return dx * dx + dy * dy;
    }

    public static double ptLineDistSq(double[] a, double[] b, double[] c, double[] d, int n) {
        int i;
        for (int i2 = 0; i2 < n; ++i2) {
            d[i2] = b[i2] - a[i2];
        }
        double f = 0.0;
        for (int i3 = 0; i3 < n; ++i3) {
            f += d[i3] * d[i3];
        }
        double t = 0.0;
        if (f != 0.0) {
            double g = 0.0;
            for (i = 0; i < n; ++i) {
                g += d[i] * (c[i] - a[i]);
            }
            t = g / f;
        }
        for (int i4 = 0; i4 < n; ++i4) {
            d[i4] = a[i4] + t * d[i4];
        }
        d[n] = t;
        double distSq = 0.0;
        for (i = 0; i < n; ++i) {
            double h = c[i] - d[i];
            distSq += h * h;
        }
        return distSq;
    }

    public static double ptSegDistSq(double[] a, double[] b, double[] c, double[] d, int n) {
        int i;
        for (int i2 = 0; i2 < n; ++i2) {
            d[i2] = b[i2] - a[i2];
        }
        double f = 0.0;
        for (int i3 = 0; i3 < n; ++i3) {
            f += d[i3] * d[i3];
        }
        double t = 0.0;
        if (f != 0.0) {
            double g = 0.0;
            for (i = 0; i < n; ++i) {
                g += d[i] * (c[i] - a[i]);
            }
            t = g / f;
        }
        if (t < 0.0) {
            t = 0.0;
        } else if (t > 1.0) {
            t = 1.0;
        }
        for (int i4 = 0; i4 < n; ++i4) {
            d[i4] = a[i4] + t * d[i4];
        }
        d[n] = t;
        double distSq = 0.0;
        for (i = 0; i < n; ++i) {
            double h = c[i] - d[i];
            distSq += h * h;
        }
        return distSq;
    }

    public static Object getLineLineIntersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double[] result) {
        double bx = x2 - x1;
        double dy = y4 - y3;
        double by = y2 - y1;
        double dx = x4 - x3;
        double b_dot_d_perp = bx * dy - by * dx;
        if (b_dot_d_perp == 0.0) {
            return PARALLEL;
        }
        double cx = x3 - x1;
        double cy = y3 - y1;
        double t = (cx * dy - cy * dx) / b_dot_d_perp;
        if (result != null) {
            result[0] = x1 + t * bx;
            result[1] = y1 + t * by;
            result[2] = t;
        }
        return INTERSECT;
    }

    public static Object getLineSegIntersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double[] result) {
        double bx = x2 - x1;
        double dy = y4 - y3;
        double by = y2 - y1;
        double dx = x4 - x3;
        double b_dot_d_perp = bx * dy - by * dx;
        if (b_dot_d_perp == 0.0) {
            return PARALLEL;
        }
        double cx = x3 - x1;
        double cy = y3 - y1;
        double u = (cx * by - cy * bx) / b_dot_d_perp;
        if (u < 0.0 || u > 1.0) {
            return null;
        }
        if (result != null) {
            result[0] = x3 + u * dx;
            result[1] = y3 + u * dy;
            result[2] = u;
        }
        return INTERSECT;
    }

    public static Object getSegSegIntersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double[] result) {
        double bx = x2 - x1;
        double dy = y4 - y3;
        double by = y2 - y1;
        double dx = x4 - x3;
        double b_dot_d_perp = bx * dy - by * dx;
        if (b_dot_d_perp == 0.0) {
            return PARALLEL;
        }
        double cx = x3 - x1;
        double cy = y3 - y1;
        double t = (cx * dy - cy * dx) / b_dot_d_perp;
        if (t < 0.0 || t > 1.0) {
            return null;
        }
        double u = (cx * by - cy * bx) / b_dot_d_perp;
        if (u < 0.0 || u > 1.0) {
            return null;
        }
        if (result != null) {
            result[0] = x1 + t * bx;
            result[1] = y1 + t * by;
            result[2] = t;
        }
        return INTERSECT;
    }

    public static boolean getCircle(double x1, double y1, double x2, double y2, double x3, double y3, double[] result) {
        double ax = x2 - x1;
        double cy = y1 - y3;
        double ay = y2 - y1;
        double cx = x1 - x3;
        double aPerpDOTc = ax * cy - ay * cx;
        if (aPerpDOTc == 0.0) {
            return false;
        }
        double bx = x3 - x2;
        double by = y3 - y2;
        double bDOTc = bx * cx + by * cy;
        double qo = bDOTc / aPerpDOTc;
        double sx = x1 + (ax - qo * ay) / 2.0;
        double sy = y1 + (ay + qo * ax) / 2.0;
        double dx = x1 - sx;
        double dy = y1 - sy;
        double rSquared = dx * dx + dy * dy;
        if (result != null) {
            result[0] = sx;
            result[1] = sy;
            result[2] = rSquared;
        }
        return true;
    }

    public static double getTriangleAreaSq(double x1, double y1, double x2, double y2, double x3, double y3) {
        double t;
        double ax = x1 - x2;
        double ay = y1 - y2;
        double bx = x2 - x3;
        double by = y2 - y3;
        double cx = x3 - x1;
        double cy = y3 - y1;
        double a = (ax * ax + ay * ay) / 2.0;
        double b = (bx * bx + by * by) / 2.0;
        double c = (cx * cx + cy * cy) / 2.0;
        if (b < a) {
            t = a;
            a = b;
            b = t;
        }
        if (c < a) {
            t = a;
            a = c;
            c = t;
        }
        double d = (a + (b - c)) / 2.0;
        return a * b - d * d;
    }

    public static double getTriangleAreaSq(double a, double b, double c) {
        double t;
        if (a < 0.0) {
            throw new IllegalArgumentException("a >= 0 required");
        }
        if (b < 0.0) {
            throw new IllegalArgumentException("b >= 0 required");
        }
        if (c < 0.0) {
            throw new IllegalArgumentException("c >= 0 required");
        }
        if (a > b + c) {
            throw new IllegalArgumentException("a <= b + c required");
        }
        if (b > a + c) {
            throw new IllegalArgumentException("b <= a + c required");
        }
        if (c > a + b) {
            throw new IllegalArgumentException("c <= a + b required");
        }
        if (a < c) {
            t = c;
            c = a;
            a = t;
        }
        if (b < c) {
            t = c;
            c = b;
            b = t;
        }
        if (a < b) {
            t = b;
            b = a;
            a = t;
        }
        return (a + (b + c)) * (c - (a - b)) * (c + (a - b)) * (a + (b - c)) / 16.0;
    }
}

