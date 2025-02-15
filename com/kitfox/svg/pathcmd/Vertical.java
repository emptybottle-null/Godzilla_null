/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class Vertical
extends PathCommand {
    public float y = 0.0f;

    public Vertical() {
    }

    public String toString() {
        return "V " + this.y;
    }

    public Vertical(boolean isRelative, float y) {
        super(isRelative);
        this.y = y;
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        float offx = hist.lastPoint.x;
        float offy = this.isRelative ? hist.lastPoint.y : 0.0f;
        path.lineTo(offx, this.y + offy);
        hist.setLastPoint(offx, this.y + offy);
        hist.setLastKnot(offx, this.y + offy);
    }

    @Override
    public int getNumKnotsAdded() {
        return 2;
    }
}

