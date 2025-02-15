/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.graphbuilder.curve;

import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.Curve;
import com.graphbuilder.curve.GroupIterator;
import com.graphbuilder.curve.MultiPath;

public class Polyline
extends Curve {
    public Polyline(ControlPath cp, GroupIterator gi) {
        super(cp, gi);
    }

    public void appendTo(MultiPath mp) {
        if (!this.gi.isInRange(0, this.cp.numPoints())) {
            throw new IllegalArgumentException("Group iterator not in range");
        }
        this.gi.set(0, 0);
        if (this.connect) {
            mp.lineTo(this.cp.getPoint(this.gi.next()).getLocation());
        } else {
            mp.moveTo(this.cp.getPoint(this.gi.next()).getLocation());
        }
        while (this.gi.hasNext()) {
            mp.lineTo(this.cp.getPoint(this.gi.next()).getLocation());
        }
    }
}

