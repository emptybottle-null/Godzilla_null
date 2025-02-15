/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.geom.Geom;

public class MultiPath {
    public static final Object MOVE_TO = new Object();
    public static final Object LINE_TO = new Object();
    private double[][] point = new double[2][0];
    private Object[] type = new Object[this.point.length];
    private int size = 0;
    private double flatness = 1.0;
    private final int dimension;

    public MultiPath(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("dimension > 0 required");
        }
        this.dimension = dimension;
    }

    public int getDimension() {
        return this.dimension;
    }

    public double getFlatness() {
        return this.flatness;
    }

    public void setFlatness(double f) {
        if (f <= 0.0) {
            throw new IllegalArgumentException("flatness > 0 required");
        }
        this.flatness = f;
    }

    public double[] get(int index) {
        return this.point[index];
    }

    public void set(int index, double[] p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        if (p.length < this.dimension) {
            throw new IllegalArgumentException("p.length >= dimension required");
        }
        if (this.point[index] == null) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        this.point[index] = p;
    }

    public Object getType(int index) {
        if (this.type[index] == null) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return this.type[index];
    }

    public void setType(int index, Object type) {
        if (type != MOVE_TO && type != LINE_TO) {
            throw new IllegalArgumentException("unknown type");
        }
        if (this.type[index] == null) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        if (index == 0 && type != MOVE_TO) {
            throw new IllegalArgumentException("type[0] must always be MOVE_TO");
        }
        this.type[index] = type;
    }

    public int getNumPoints() {
        return this.size;
    }

    public void setNumPoints(int n) {
        if (n != 0 && this.point[n - 1] == null) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        this.size = n;
    }

    public int getCapacity() {
        return this.point.length;
    }

    public void ensureCapacity(int capacity) {
        if (this.point.length < capacity) {
            int x = 2 * this.point.length;
            if (x < capacity) {
                x = capacity;
            }
            double[][] p2 = new double[x][];
            for (int i = 0; i < this.size; ++i) {
                p2[i] = this.point[i];
            }
            Object[] t2 = new Object[x];
            for (int i = 0; i < this.size; ++i) {
                t2[i] = this.type[i];
            }
            this.point = p2;
            this.type = t2;
        }
    }

    public void trimArray() {
        if (this.size < this.point.length) {
            double[][] p2 = new double[this.size][];
            for (int i = 0; i < this.size; ++i) {
                p2[i] = this.point[i];
            }
            Object[] t2 = new Object[this.size];
            for (int i = 0; i < this.size; ++i) {
                t2[i] = this.type[i];
            }
            this.point = p2;
            this.type = t2;
        }
    }

    public void lineTo(double[] p) {
        this.append(p, LINE_TO);
    }

    public void moveTo(double[] p) {
        this.append(p, MOVE_TO);
    }

    private void append(double[] p, Object t) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        if (p.length < this.dimension) {
            throw new IllegalArgumentException("p.length >= dimension required");
        }
        if (this.size == 0) {
            t = MOVE_TO;
        }
        this.ensureCapacity(this.size + 1);
        this.point[this.size] = p;
        this.type[this.size] = t;
        ++this.size;
    }

    public double getDistSq(double[] p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        if (p.length < this.dimension) {
            throw new IllegalArgumentException("p.length >= dimension required");
        }
        int n = this.getNumPoints();
        if (n == 0) {
            return Double.MAX_VALUE;
        }
        double dist = Double.MAX_VALUE;
        double[] b = this.get(0);
        double[] c = new double[this.dimension + 1];
        for (int i = 1; i < n; ++i) {
            double d;
            double[] a = this.get(i);
            if (this.getType(i) != LINE_TO || !((d = Geom.ptSegDistSq(a, b, p, c, this.dimension)) < dist)) continue;
            dist = d;
        }
        return dist;
    }
}

