/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.Curve;
import com.graphbuilder.curve.Point;
import com.graphbuilder.struc.Bag;

public class ControlPath {
    private Bag curveBag = new Bag();
    private Bag pointBag = new Bag();

    public void addCurve(Curve c) {
        if (c == null) {
            throw new IllegalArgumentException("Curve cannot be null.");
        }
        this.curveBag.add(c);
    }

    public void addPoint(Point p) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        this.pointBag.add(p);
    }

    public void insertCurve(Curve c, int index) {
        if (c == null) {
            throw new IllegalArgumentException("Curve cannot be null.");
        }
        this.curveBag.insert(c, index);
    }

    public void insertPoint(Point p, int index) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        this.pointBag.insert(p, index);
    }

    public Curve setCurve(Curve c, int index) {
        if (c == null) {
            throw new IllegalArgumentException("Curve cannot be null.");
        }
        return (Curve)this.curveBag.set(c, index);
    }

    public Point setPoint(Point p, int index) {
        if (p == null) {
            throw new IllegalArgumentException("Point cannot be null.");
        }
        return (Point)this.pointBag.set(p, index);
    }

    public Curve getCurve(int index) {
        return (Curve)this.curveBag.get(index);
    }

    public Point getPoint(int index) {
        return (Point)this.pointBag.get(index);
    }

    public int numCurves() {
        return this.curveBag.size();
    }

    public int numPoints() {
        return this.pointBag.size();
    }

    public void removeCurve(Curve c) {
        this.curveBag.remove(c);
    }

    public void removePoint(Point p) {
        this.pointBag.remove(p);
    }

    public void removeCurve(int index) {
        this.curveBag.remove(index);
    }

    public void removePoint(int index) {
        this.pointBag.remove(index);
    }

    public void ensureCurveCapacity(int capacity) {
        this.curveBag.ensureCapacity(capacity);
    }

    public void ensurePointCapacity(int capacity) {
        this.pointBag.ensureCapacity(capacity);
    }

    public void trimCurveArray() {
        this.curveBag.trimArray();
    }

    public void trimPointArray() {
        this.pointBag.trimArray();
    }
}

