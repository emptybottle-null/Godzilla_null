/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import java.awt.geom.Point2D;

public class Bezier {
    double length;
    double[] coord;

    public Bezier(double sx, double sy, double[] coords, int numCoords) {
        this.setCoords(sx, sy, coords, numCoords);
    }

    public void setCoords(double sx, double sy, double[] coords, int numCoords) {
        this.coord = new double[numCoords * 2 + 2];
        this.coord[0] = sx;
        this.coord[1] = sy;
        for (int i = 0; i < numCoords; ++i) {
            this.coord[i * 2 + 2] = coords[i * 2];
            this.coord[i * 2 + 3] = coords[i * 2 + 1];
        }
        this.calcLength();
    }

    public double getLength() {
        return this.length;
    }

    private void calcLength() {
        this.length = 0.0;
        for (int i = 2; i < this.coord.length; i += 2) {
            this.length += this.lineLength(this.coord[i - 2], this.coord[i - 1], this.coord[i], this.coord[i + 1]);
        }
    }

    private double lineLength(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point2D.Double getFinalPoint(Point2D.Double point) {
        point.x = this.coord[this.coord.length - 2];
        point.y = this.coord[this.coord.length - 1];
        return point;
    }

    public Point2D.Double eval(double param, Point2D.Double point) {
        point.x = 0.0;
        point.y = 0.0;
        int numKnots = this.coord.length / 2;
        for (int i = 0; i < numKnots; ++i) {
            double scale = this.bernstein(numKnots - 1, i, param);
            point.x += this.coord[i * 2] * scale;
            point.y += this.coord[i * 2 + 1] * scale;
        }
        return point;
    }

    private double bernstein(int numKnots, int knotNo, double param) {
        int i;
        double iParam = 1.0 - param;
        switch (numKnots) {
            case 0: {
                return 1.0;
            }
            case 1: {
                switch (knotNo) {
                    case 0: {
                        return iParam;
                    }
                    case 1: {
                        return param;
                    }
                }
                break;
            }
            case 2: {
                switch (knotNo) {
                    case 0: {
                        return iParam * iParam;
                    }
                    case 1: {
                        return 2.0 * iParam * param;
                    }
                    case 2: {
                        return param * param;
                    }
                }
                break;
            }
            case 3: {
                switch (knotNo) {
                    case 0: {
                        return iParam * iParam * iParam;
                    }
                    case 1: {
                        return 3.0 * iParam * iParam * param;
                    }
                    case 2: {
                        return 3.0 * iParam * param * param;
                    }
                    case 3: {
                        return param * param * param;
                    }
                }
            }
        }
        double retVal = 1.0;
        for (i = 0; i < knotNo; ++i) {
            retVal *= param;
        }
        for (i = 0; i < numKnots - knotNo; ++i) {
            retVal *= iParam;
        }
        return retVal *= (double)this.choose(numKnots, knotNo);
    }

    private int choose(int num, int denom) {
        int i;
        int denom2 = num - denom;
        if (denom < denom2) {
            int tmp = denom;
            denom = denom2;
            denom2 = tmp;
        }
        int prod = 1;
        for (i = num; i > denom; --i) {
            prod *= num;
        }
        for (i = 2; i <= denom2; ++i) {
            prod /= i;
        }
        return prod;
    }
}

