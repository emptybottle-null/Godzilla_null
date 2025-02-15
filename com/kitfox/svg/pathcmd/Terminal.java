/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.geom.GeneralPath;

public class Terminal
extends PathCommand {
    public String toString() {
        return "Z";
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        path.closePath();
        hist.setLastPoint(hist.startPoint.x, hist.startPoint.y);
        hist.setLastKnot(hist.startPoint.x, hist.startPoint.y);
    }

    @Override
    public int getNumKnotsAdded() {
        return 0;
    }
}

