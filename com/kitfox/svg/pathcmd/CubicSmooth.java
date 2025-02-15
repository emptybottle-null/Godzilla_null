/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class CubicSmooth
extends PathCommand {
    public float x = 0.0f;
    public float y = 0.0f;
    public float k2x = 0.0f;
    public float k2y = 0.0f;

    public CubicSmooth() {
    }

    public CubicSmooth(boolean isRelative, float k2x, float k2y, float x, float y) {
        super(isRelative);
        this.k2x = k2x;
        this.k2y = k2y;
        this.x = x;
        this.y = y;
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        float offx = this.isRelative ? hist.lastPoint.x : 0.0f;
        float offy = this.isRelative ? hist.lastPoint.y : 0.0f;
        float oldKx = hist.lastKnot.x;
        float oldKy = hist.lastKnot.y;
        float oldX = hist.lastPoint.x;
        float oldY = hist.lastPoint.y;
        float k1x = oldX * 2.0f - oldKx;
        float k1y = oldY * 2.0f - oldKy;
        path.curveTo(k1x, k1y, this.k2x + offx, this.k2y + offy, this.x + offx, this.y + offy);
        hist.setLastPoint(this.x + offx, this.y + offy);
        hist.setLastKnot(this.k2x + offx, this.k2y + offy);
    }

    @Override
    public int getNumKnotsAdded() {
        return 6;
    }

    public String toString() {
        return "S " + this.k2x + " " + this.k2y + " " + this.x + " " + this.y;
    }
}

