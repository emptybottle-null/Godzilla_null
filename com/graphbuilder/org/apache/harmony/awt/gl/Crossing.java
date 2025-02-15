/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.org.apache.harmony.awt.gl;

import java.awt.Shape;
import java.awt.geom.PathIterator;

public class Crossing {
    static final double DELTA = 1.0E-5;
    static final double ROOT_DELTA = 1.0E-10;
    public static final int CROSSING = 255;
    static final int UNKNOWN = 254;

    public static int solveQuad(double[] eqn, double[] res) {
        double a = eqn[2];
        double b = eqn[1];
        double c = eqn[0];
        int rc = 0;
        if (a == 0.0) {
            if (b == 0.0) {
                return -1;
            }
            res[rc++] = -c / b;
        } else {
            double d = b * b - 4.0 * a * c;
            if (d < 0.0) {
                return 0;
            }
            d = Math.sqrt(d);
            res[rc++] = (-b + d) / (a * 2.0);
            if (d != 0.0) {
                res[rc++] = (-b - d) / (a * 2.0);
            }
        }
        return Crossing.fixRoots(res, rc);
    }

    public static int solveCubic(double[] eqn, double[] res) {
        double d = eqn[3];
        if (d == 0.0) {
            return Crossing.solveQuad(eqn, res);
        }
        double a = eqn[2] / d;
        double b = eqn[1] / d;
        double c = eqn[0] / d;
        int rc = 0;
        double Q = (a * a - 3.0 * b) / 9.0;
        double R = (2.0 * a * a * a - 9.0 * a * b + 27.0 * c) / 54.0;
        double Q3 = Q * Q * Q;
        double R2 = R * R;
        double n = -a / 3.0;
        if (R2 < Q3) {
            double t = Math.acos(R / Math.sqrt(Q3)) / 3.0;
            double p = 2.0943951023931953;
            double m = -2.0 * Math.sqrt(Q);
            res[rc++] = m * Math.cos(t) + n;
            res[rc++] = m * Math.cos(t + p) + n;
            res[rc++] = m * Math.cos(t - p) + n;
        } else {
            double A2 = Math.pow(Math.abs(R) + Math.sqrt(R2 - Q3), 0.3333333333333333);
            if (R > 0.0) {
                A2 = -A2;
            }
            if (-1.0E-10 < A2 && A2 < 1.0E-10) {
                res[rc++] = n;
            } else {
                double B = Q / A2;
                res[rc++] = A2 + B + n;
                double delta = R2 - Q3;
                if (-1.0E-10 < delta && delta < 1.0E-10) {
                    res[rc++] = -(A2 + B) / 2.0 + n;
                }
            }
        }
        return Crossing.fixRoots(res, rc);
    }

    static int fixRoots(double[] res, int rc) {
        int tc = 0;
        block0: for (int i = 0; i < rc; ++i) {
            for (int j = i + 1; j < rc; ++j) {
                if (Crossing.isZero(res[i] - res[j])) continue block0;
            }
            res[tc++] = res[i];
        }
        return tc;
    }

    public static int crossLine(double x1, double y1, double x2, double y2, double x, double y) {
        if (x < x1 && x < x2 || x > x1 && x > x2 || y > y1 && y > y2 || x1 == x2) {
            return 0;
        }
        if (!(y < y1 && y < y2 || !((y2 - y1) * (x - x1) / (x2 - x1) <= y - y1))) {
            return 0;
        }
        if (x == x1) {
            return x1 < x2 ? 0 : -1;
        }
        if (x == x2) {
            return x1 < x2 ? 1 : 0;
        }
        return x1 < x2 ? 1 : -1;
    }

    public static int crossQuad(double x1, double y1, double cx, double cy, double x2, double y2, double x, double y) {
        if (x < x1 && x < cx && x < x2 || x > x1 && x > cx && x > x2 || y > y1 && y > cy && y > y2 || x1 == cx && cx == x2) {
            return 0;
        }
        if (y < y1 && y < cy && y < y2 && x != x1 && x != x2) {
            if (x1 < x2) {
                return x1 < x && x < x2 ? 1 : 0;
            }
            return x2 < x && x < x1 ? -1 : 0;
        }
        QuadCurve c = new QuadCurve(x1, y1, cx, cy, x2, y2);
        double px = x - x1;
        double py = y - y1;
        double[] res = new double[3];
        int rc = c.solvePoint(res, px);
        return c.cross(res, rc, py, py);
    }

    public static int crossCubic(double x1, double y1, double cx1, double cy1, double cx2, double cy2, double x2, double y2, double x, double y) {
        if (x < x1 && x < cx1 && x < cx2 && x < x2 || x > x1 && x > cx1 && x > cx2 && x > x2 || y > y1 && y > cy1 && y > cy2 && y > y2 || x1 == cx1 && cx1 == cx2 && cx2 == x2) {
            return 0;
        }
        if (y < y1 && y < cy1 && y < cy2 && y < y2 && x != x1 && x != x2) {
            if (x1 < x2) {
                return x1 < x && x < x2 ? 1 : 0;
            }
            return x2 < x && x < x1 ? -1 : 0;
        }
        CubicCurve c = new CubicCurve(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
        double px = x - x1;
        double py = y - y1;
        double[] res = new double[3];
        int rc = c.solvePoint(res, px);
        return c.cross(res, rc, py, py);
    }

    public static int crossPath(PathIterator p, double x, double y) {
        int cross = 0;
        double cy = 0.0;
        double cx = 0.0;
        double my = 0.0;
        double mx = 0.0;
        double[] coords = new double[6];
        while (!p.isDone()) {
            switch (p.currentSegment(coords)) {
                case 0: {
                    if (cx != mx || cy != my) {
                        cross += Crossing.crossLine(cx, cy, mx, my, x, y);
                    }
                    mx = cx = coords[0];
                    my = cy = coords[1];
                    break;
                }
                case 1: {
                    double d = cx;
                    double d2 = cy;
                    cx = coords[0];
                    cy = coords[1];
                    cross += Crossing.crossLine(d, d2, cx, cy, x, y);
                    break;
                }
                case 2: {
                    double d = cx;
                    double d3 = cy;
                    cx = coords[2];
                    cy = coords[3];
                    cross += Crossing.crossQuad(d, d3, coords[0], coords[1], cx, cy, x, y);
                    break;
                }
                case 3: {
                    double d = cx;
                    double d4 = cy;
                    cx = coords[4];
                    cy = coords[5];
                    cross += Crossing.crossCubic(d, d4, coords[0], coords[1], coords[2], coords[3], cx, cy, x, y);
                    break;
                }
                case 4: {
                    if (cy == my && cx == mx) break;
                    double d = cx;
                    double d5 = cy;
                    cx = mx;
                    cy = my;
                    cross += Crossing.crossLine(d, d5, cx, cy, x, y);
                }
            }
            if (x == cx && y == cy) {
                cross = 0;
                cy = my;
                break;
            }
            p.next();
        }
        if (cy != my) {
            cross += Crossing.crossLine(cx, cy, mx, my, x, y);
        }
        return cross;
    }

    public static int crossShape(Shape s, double x, double y) {
        if (!s.getBounds2D().contains(x, y)) {
            return 0;
        }
        return Crossing.crossPath(s.getPathIterator(null), x, y);
    }

    public static boolean isZero(double val) {
        return -1.0E-5 < val && val < 1.0E-5;
    }

    static void sortBound(double[] bound, int bc) {
        for (int i = 0; i < bc - 4; i += 4) {
            int k = i;
            for (int j = i + 4; j < bc; j += 4) {
                if (!(bound[k] > bound[j])) continue;
                k = j;
            }
            if (k == i) continue;
            double tmp = bound[i];
            bound[i] = bound[k];
            bound[k] = tmp;
            tmp = bound[i + 1];
            bound[i + 1] = bound[k + 1];
            bound[k + 1] = tmp;
            tmp = bound[i + 2];
            bound[i + 2] = bound[k + 2];
            bound[k + 2] = tmp;
            tmp = bound[i + 3];
            bound[i + 3] = bound[k + 3];
            bound[k + 3] = tmp;
        }
    }

    static int crossBound(double[] bound, int bc, double py1, double py2) {
        if (bc == 0) {
            return 0;
        }
        int up = 0;
        int down = 0;
        for (int i = 2; i < bc; i += 4) {
            if (bound[i] < py1) {
                ++up;
                continue;
            }
            if (bound[i] > py2) {
                ++down;
                continue;
            }
            return 255;
        }
        if (down == 0) {
            return 0;
        }
        if (up != 0) {
            Crossing.sortBound(bound, bc);
            boolean sign = bound[2] > py2;
            for (int i = 6; i < bc; i += 4) {
                boolean sign2;
                boolean bl = sign2 = bound[i] > py2;
                if (sign != sign2 && bound[i + 1] != bound[i - 3]) {
                    return 255;
                }
                sign = sign2;
            }
        }
        return 254;
    }

    public static int intersectLine(double x1, double y1, double x2, double y2, double rx1, double ry1, double rx2, double ry2) {
        if (rx2 < x1 && rx2 < x2 || rx1 > x1 && rx1 > x2 || ry1 > y1 && ry1 > y2) {
            return 0;
        }
        if (!(ry2 < y1) || !(ry2 < y2)) {
            double bx2;
            double bx1;
            if (x1 == x2) {
                return 255;
            }
            if (x1 < x2) {
                bx1 = x1 < rx1 ? rx1 : x1;
                bx2 = x2 < rx2 ? x2 : rx2;
            } else {
                bx1 = x2 < rx1 ? rx1 : x2;
                bx2 = x1 < rx2 ? x1 : rx2;
            }
            double k = (y2 - y1) / (x2 - x1);
            double by1 = k * (bx1 - x1) + y1;
            double by2 = k * (bx2 - x1) + y1;
            if (by1 < ry1 && by2 < ry1) {
                return 0;
            }
            if (!(by1 > ry2) || !(by2 > ry2)) {
                return 255;
            }
        }
        if (x1 == x2) {
            return 0;
        }
        if (rx1 == x1) {
            return x1 < x2 ? 0 : -1;
        }
        if (rx1 == x2) {
            return x1 < x2 ? 1 : 0;
        }
        if (x1 < x2) {
            return x1 < rx1 && rx1 < x2 ? 1 : 0;
        }
        return x2 < rx1 && rx1 < x1 ? -1 : 0;
    }

    public static int intersectQuad(double x1, double y1, double cx, double cy, double x2, double y2, double rx1, double ry1, double rx2, double ry2) {
        int cross;
        if (rx2 < x1 && rx2 < cx && rx2 < x2 || rx1 > x1 && rx1 > cx && rx1 > x2 || ry1 > y1 && ry1 > cy && ry1 > y2) {
            return 0;
        }
        if (ry2 < y1 && ry2 < cy && ry2 < y2 && rx1 != x1 && rx1 != x2) {
            if (x1 < x2) {
                return x1 < rx1 && rx1 < x2 ? 1 : 0;
            }
            return x2 < rx1 && rx1 < x1 ? -1 : 0;
        }
        QuadCurve c = new QuadCurve(x1, y1, cx, cy, x2, y2);
        double px1 = rx1 - x1;
        double py1 = ry1 - y1;
        double px2 = rx2 - x1;
        double py2 = ry2 - y1;
        double[] res1 = new double[3];
        double[] res2 = new double[3];
        int rc1 = c.solvePoint(res1, px1);
        int rc2 = c.solvePoint(res2, px2);
        if (rc1 == 0 && rc2 == 0) {
            return 0;
        }
        double minX = px1 - 1.0E-5;
        double maxX = px2 + 1.0E-5;
        double[] bound = new double[28];
        int bc = 0;
        bc = c.addBound(bound, bc, res1, rc1, minX, maxX, false, 0);
        bc = c.addBound(bound, bc, res2, rc2, minX, maxX, false, 1);
        rc2 = c.solveExtrem(res2);
        bc = c.addBound(bound, bc, res2, rc2, minX, maxX, true, 2);
        if (rx1 < x1 && x1 < rx2) {
            bound[bc++] = 0.0;
            bound[bc++] = 0.0;
            bound[bc++] = 0.0;
            bound[bc++] = 4.0;
        }
        if (rx1 < x2 && x2 < rx2) {
            bound[bc++] = 1.0;
            bound[bc++] = c.ax;
            bound[bc++] = c.ay;
            bound[bc++] = 5.0;
        }
        if ((cross = Crossing.crossBound(bound, bc, py1, py2)) != 254) {
            return cross;
        }
        return c.cross(res1, rc1, py1, py2);
    }

    public static int intersectCubic(double x1, double y1, double cx1, double cy1, double cx2, double cy2, double x2, double y2, double rx1, double ry1, double rx2, double ry2) {
        int cross;
        if (rx2 < x1 && rx2 < cx1 && rx2 < cx2 && rx2 < x2 || rx1 > x1 && rx1 > cx1 && rx1 > cx2 && rx1 > x2 || ry1 > y1 && ry1 > cy1 && ry1 > cy2 && ry1 > y2) {
            return 0;
        }
        if (ry2 < y1 && ry2 < cy1 && ry2 < cy2 && ry2 < y2 && rx1 != x1 && rx1 != x2) {
            if (x1 < x2) {
                return x1 < rx1 && rx1 < x2 ? 1 : 0;
            }
            return x2 < rx1 && rx1 < x1 ? -1 : 0;
        }
        CubicCurve c = new CubicCurve(x1, y1, cx1, cy1, cx2, cy2, x2, y2);
        double px1 = rx1 - x1;
        double py1 = ry1 - y1;
        double px2 = rx2 - x1;
        double py2 = ry2 - y1;
        double[] res1 = new double[3];
        double[] res2 = new double[3];
        int rc1 = c.solvePoint(res1, px1);
        int rc2 = c.solvePoint(res2, px2);
        if (rc1 == 0 && rc2 == 0) {
            return 0;
        }
        double minX = px1 - 1.0E-5;
        double maxX = px2 + 1.0E-5;
        double[] bound = new double[40];
        int bc = 0;
        bc = c.addBound(bound, bc, res1, rc1, minX, maxX, false, 0);
        bc = c.addBound(bound, bc, res2, rc2, minX, maxX, false, 1);
        rc2 = c.solveExtremX(res2);
        bc = c.addBound(bound, bc, res2, rc2, minX, maxX, true, 2);
        rc2 = c.solveExtremY(res2);
        bc = c.addBound(bound, bc, res2, rc2, minX, maxX, true, 4);
        if (rx1 < x1 && x1 < rx2) {
            bound[bc++] = 0.0;
            bound[bc++] = 0.0;
            bound[bc++] = 0.0;
            bound[bc++] = 6.0;
        }
        if (rx1 < x2 && x2 < rx2) {
            bound[bc++] = 1.0;
            bound[bc++] = c.ax;
            bound[bc++] = c.ay;
            bound[bc++] = 7.0;
        }
        if ((cross = Crossing.crossBound(bound, bc, py1, py2)) != 254) {
            return cross;
        }
        return c.cross(res1, rc1, py1, py2);
    }

    public static int intersectPath(PathIterator p, double x, double y, double w, double h) {
        int count;
        int cross = 0;
        double cy = 0.0;
        double cx = 0.0;
        double my = 0.0;
        double mx = 0.0;
        double[] coords = new double[6];
        double rx1 = x;
        double ry1 = y;
        double rx2 = x + w;
        double ry2 = y + h;
        while (!p.isDone()) {
            count = 0;
            switch (p.currentSegment(coords)) {
                case 0: {
                    if (cx != mx || cy != my) {
                        count = Crossing.intersectLine(cx, cy, mx, my, rx1, ry1, rx2, ry2);
                    }
                    mx = cx = coords[0];
                    my = cy = coords[1];
                    break;
                }
                case 1: {
                    double d = cx;
                    double d2 = cy;
                    cx = coords[0];
                    cy = coords[1];
                    count = Crossing.intersectLine(d, d2, cx, cy, rx1, ry1, rx2, ry2);
                    break;
                }
                case 2: {
                    double d = cx;
                    double d3 = cy;
                    cx = coords[2];
                    cy = coords[3];
                    count = Crossing.intersectQuad(d, d3, coords[0], coords[1], cx, cy, rx1, ry1, rx2, ry2);
                    break;
                }
                case 3: {
                    double d = cx;
                    double d4 = cy;
                    cx = coords[4];
                    cy = coords[5];
                    count = Crossing.intersectCubic(d, d4, coords[0], coords[1], coords[2], coords[3], cx, cy, rx1, ry1, rx2, ry2);
                    break;
                }
                case 4: {
                    if (cy != my || cx != mx) {
                        count = Crossing.intersectLine(cx, cy, mx, my, rx1, ry1, rx2, ry2);
                    }
                    cx = mx;
                    cy = my;
                }
            }
            if (count == 255) {
                return 255;
            }
            cross += count;
            p.next();
        }
        if (cy != my) {
            count = Crossing.intersectLine(cx, cy, mx, my, rx1, ry1, rx2, ry2);
            if (count == 255) {
                return 255;
            }
            cross += count;
        }
        return cross;
    }

    public static int intersectShape(Shape s, double x, double y, double w, double h) {
        if (!s.getBounds2D().intersects(x, y, w, h)) {
            return 0;
        }
        return Crossing.intersectPath(s.getPathIterator(null), x, y, w, h);
    }

    public static boolean isInsideNonZero(int cross) {
        return cross != 0;
    }

    public static boolean isInsideEvenOdd(int cross) {
        return (cross & 1) != 0;
    }

    public static class CubicCurve {
        double ax;
        double ay;
        double bx;
        double by;
        double cx;
        double cy;
        double Ax;
        double Ay;
        double Bx;
        double By;
        double Cx;
        double Cy;
        double Ax3;
        double Bx2;

        public CubicCurve(double x1, double y1, double cx1, double cy1, double cx2, double cy2, double x2, double y2) {
            this.ax = x2 - x1;
            this.ay = y2 - y1;
            this.bx = cx1 - x1;
            this.by = cy1 - y1;
            this.cx = cx2 - x1;
            this.cy = cy2 - y1;
            this.Cx = this.bx + this.bx + this.bx;
            this.Bx = this.cx + this.cx + this.cx - this.Cx - this.Cx;
            this.Ax = this.ax - this.Bx - this.Cx;
            this.Cy = this.by + this.by + this.by;
            this.By = this.cy + this.cy + this.cy - this.Cy - this.Cy;
            this.Ay = this.ay - this.By - this.Cy;
            this.Ax3 = this.Ax + this.Ax + this.Ax;
            this.Bx2 = this.Bx + this.Bx;
        }

        int cross(double[] res, int rc, double py1, double py2) {
            int cross = 0;
            for (int i = 0; i < rc; ++i) {
                double t = res[i];
                if (t < -1.0E-5 || t > 1.00001) continue;
                if (t < 1.0E-5) {
                    if (!(py1 < 0.0)) continue;
                    double d = this.bx != 0.0 ? this.bx : (this.cx != this.bx ? this.cx - this.bx : this.ax - this.cx);
                    if (!(d < 0.0)) continue;
                    --cross;
                    continue;
                }
                if (t > 0.99999) {
                    if (!(py1 < this.ay)) continue;
                    double d = this.ax != this.cx ? this.ax - this.cx : (this.cx != this.bx ? this.cx - this.bx : this.bx);
                    if (!(d > 0.0)) continue;
                    ++cross;
                    continue;
                }
                double ry = t * (t * (t * this.Ay + this.By) + this.Cy);
                if (!(ry > py2)) continue;
                double rxt = t * (t * this.Ax3 + this.Bx2) + this.Cx;
                if (rxt > -1.0E-5 && rxt < 1.0E-5) {
                    rxt = t * (this.Ax3 + this.Ax3) + this.Bx2;
                    if (rxt < -1.0E-5 || rxt > 1.0E-5) continue;
                    rxt = this.ax;
                }
                cross += rxt > 0.0 ? 1 : -1;
            }
            return cross;
        }

        int solvePoint(double[] res, double px) {
            double[] eqn = new double[]{-px, this.Cx, this.Bx, this.Ax};
            return Crossing.solveCubic(eqn, res);
        }

        int solveExtremX(double[] res) {
            double[] eqn = new double[]{this.Cx, this.Bx2, this.Ax3};
            return Crossing.solveQuad(eqn, res);
        }

        int solveExtremY(double[] res) {
            double[] eqn = new double[]{this.Cy, this.By + this.By, this.Ay + this.Ay + this.Ay};
            return Crossing.solveQuad(eqn, res);
        }

        int addBound(double[] bound, int bc, double[] res, int rc, double minX, double maxX, boolean changeId, int id) {
            for (int i = 0; i < rc; ++i) {
                double rx;
                double t = res[i];
                if (!(t > -1.0E-5) || !(t < 1.00001) || !(minX <= (rx = t * (t * (t * this.Ax + this.Bx) + this.Cx))) || !(rx <= maxX)) continue;
                bound[bc++] = t;
                bound[bc++] = rx;
                bound[bc++] = t * (t * (t * this.Ay + this.By) + this.Cy);
                bound[bc++] = id;
                if (!changeId) continue;
                ++id;
            }
            return bc;
        }
    }

    public static class QuadCurve {
        double ax;
        double ay;
        double bx;
        double by;
        double Ax;
        double Ay;
        double Bx;
        double By;

        public QuadCurve(double x1, double y1, double cx, double cy, double x2, double y2) {
            this.ax = x2 - x1;
            this.ay = y2 - y1;
            this.bx = cx - x1;
            this.by = cy - y1;
            this.Bx = this.bx + this.bx;
            this.Ax = this.ax - this.Bx;
            this.By = this.by + this.by;
            this.Ay = this.ay - this.By;
        }

        int cross(double[] res, int rc, double py1, double py2) {
            int cross = 0;
            for (int i = 0; i < rc; ++i) {
                double rxt;
                double t = res[i];
                if (t < -1.0E-5 || t > 1.00001) continue;
                if (t < 1.0E-5) {
                    if (!(py1 < 0.0)) continue;
                    double d = this.bx != 0.0 ? this.bx : this.ax - this.bx;
                    if (!(d < 0.0)) continue;
                    --cross;
                    continue;
                }
                if (t > 0.99999) {
                    if (!(py1 < this.ay)) continue;
                    double d = this.ax != this.bx ? this.ax - this.bx : this.bx;
                    if (!(d > 0.0)) continue;
                    ++cross;
                    continue;
                }
                double ry = t * (t * this.Ay + this.By);
                if (!(ry > py2) || (rxt = t * this.Ax + this.bx) > -1.0E-5 && rxt < 1.0E-5) continue;
                cross += rxt > 0.0 ? 1 : -1;
            }
            return cross;
        }

        int solvePoint(double[] res, double px) {
            double[] eqn = new double[]{-px, this.Bx, this.Ax};
            return Crossing.solveQuad(eqn, res);
        }

        int solveExtrem(double[] res) {
            int rc = 0;
            if (this.Ax != 0.0) {
                res[rc++] = -this.Bx / (this.Ax + this.Ax);
            }
            if (this.Ay != 0.0) {
                res[rc++] = -this.By / (this.Ay + this.Ay);
            }
            return rc;
        }

        int addBound(double[] bound, int bc, double[] res, int rc, double minX, double maxX, boolean changeId, int id) {
            for (int i = 0; i < rc; ++i) {
                double rx;
                double t = res[i];
                if (!(t > -1.0E-5) || !(t < 1.00001) || !(minX <= (rx = t * (t * this.Ax + this.Bx))) || !(rx <= maxX)) continue;
                bound[bc++] = t;
                bound[bc++] = rx;
                bound[bc++] = t * (t * this.Ay + this.By);
                bound[bc++] = id;
                if (!changeId) continue;
                ++id;
            }
            return bc;
        }
    }
}

