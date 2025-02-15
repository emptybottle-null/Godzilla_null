/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.MultiPath;
import com.graphbuilder.curve.ShapeMultiPath;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

class ShapeMultiPathIterator
implements PathIterator {
    private final int ai0;
    private final int ai1;
    private final AffineTransform at;
    private final ShapeMultiPath smp;
    private final int windingRule;
    private int n = 0;

    ShapeMultiPathIterator(ShapeMultiPath smp, AffineTransform at) {
        this.smp = smp;
        int[] bv = smp.getBasisVectors();
        this.ai0 = bv[0];
        this.ai1 = bv[1];
        this.at = at;
        this.windingRule = smp.getWindingRule();
    }

    public int getWindingRule() {
        return this.windingRule;
    }

    public boolean isDone() {
        return this.n >= this.smp.getNumPoints();
    }

    public void next() {
        ++this.n;
    }

    public int currentSegment(float[] coords) {
        double[] p = this.smp.get(this.n);
        coords[0] = (float)p[this.ai0];
        coords[1] = (float)p[this.ai1];
        if (this.n > 0 && p == this.smp.get(0)) {
            return 4;
        }
        if (this.at != null) {
            this.at.transform(coords, 0, coords, 0, 1);
        }
        if (this.smp.getType(this.n) == MultiPath.MOVE_TO) {
            return 0;
        }
        return 1;
    }

    public int currentSegment(double[] coords) {
        double[] p = this.smp.get(this.n);
        coords[0] = p[this.ai0];
        coords[1] = p[this.ai1];
        if (this.n > 0 && p == this.smp.get(0)) {
            return 4;
        }
        if (this.at != null) {
            this.at.transform(coords, 0, coords, 0, 1);
        }
        if (this.smp.getType(this.n) == MultiPath.MOVE_TO) {
            return 0;
        }
        return 1;
    }
}

