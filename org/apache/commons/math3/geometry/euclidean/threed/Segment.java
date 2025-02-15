/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Segment {
    private final Vector3D start;
    private final Vector3D end;
    private final Line line;

    public Segment(Vector3D start, Vector3D end, Line line) {
        this.start = start;
        this.end = end;
        this.line = line;
    }

    public Vector3D getStart() {
        return this.start;
    }

    public Vector3D getEnd() {
        return this.end;
    }

    public Line getLine() {
        return this.line;
    }
}

