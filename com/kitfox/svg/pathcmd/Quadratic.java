/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class Quadratic
extends PathCommand {
    public float kx = 0.0f;
    public float ky = 0.0f;
    public float x = 0.0f;
    public float y = 0.0f;

    public Quadratic() {
    }

    public String toString() {
        return "Q " + this.kx + " " + this.ky + " " + this.x + " " + this.y;
    }

    public Quadratic(boolean isRelative, float kx, float ky, float x, float y) {
        super(isRelative);
        this.kx = kx;
        this.ky = ky;
        this.x = x;
        this.y = y;
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        float offx = this.isRelative ? hist.lastPoint.x : 0.0f;
        float offy = this.isRelative ? hist.lastPoint.y : 0.0f;
        path.quadTo(this.kx + offx, this.ky + offy, this.x + offx, this.y + offy);
        hist.setLastPoint(this.x + offx, this.y + offy);
        hist.setLastKnot(this.kx + offx, this.ky + offy);
    }

    @Override
    public int getNumKnotsAdded() {
        return 4;
    }
}

