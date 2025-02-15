/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class LineTo
extends PathCommand {
    public float x = 0.0f;
    public float y = 0.0f;

    public LineTo() {
    }

    public LineTo(boolean isRelative, float x, float y) {
        super(isRelative);
        this.x = x;
        this.y = y;
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        float offx = this.isRelative ? hist.lastPoint.x : 0.0f;
        float offy = this.isRelative ? hist.lastPoint.y : 0.0f;
        path.lineTo(this.x + offx, this.y + offy);
        hist.setLastPoint(this.x + offx, this.y + offy);
        hist.setLastKnot(this.x + offx, this.y + offy);
    }

    @Override
    public int getNumKnotsAdded() {
        return 2;
    }

    public String toString() {
        return "L " + this.x + " " + this.y;
    }
}

