/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class QuadraticSmooth
extends PathCommand {
    public float x = 0.0f;
    public float y = 0.0f;

    public QuadraticSmooth() {
    }

    public String toString() {
        return "T " + this.x + " " + this.y;
    }

    public QuadraticSmooth(boolean isRelative, float x, float y) {
        super(isRelative);
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
        float kx = oldX * 2.0f - oldKx;
        float ky = oldY * 2.0f - oldKy;
        path.quadTo(kx, ky, this.x + offx, this.y + offy);
        hist.setLastPoint(this.x + offx, this.y + offy);
        hist.setLastKnot(kx, ky);
    }

    @Override
    public int getNumKnotsAdded() {
        return 4;
    }
}

