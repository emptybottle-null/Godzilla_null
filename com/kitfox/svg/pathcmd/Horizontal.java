/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class Horizontal
extends PathCommand {
    public float x = 0.0f;

    public Horizontal() {
    }

    public String toString() {
        return "H " + this.x;
    }

    public Horizontal(boolean isRelative, float x) {
        super(isRelative);
        this.x = x;
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        float offx = this.isRelative ? hist.lastPoint.x : 0.0f;
        float offy = hist.lastPoint.y;
        path.lineTo(this.x + offx, offy);
        hist.setLastPoint(this.x + offx, offy);
        hist.setLastKnot(this.x + offx, offy);
    }

    @Override
    public int getNumKnotsAdded() {
        return 2;
    }
}

