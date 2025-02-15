/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import java.awt.geom.GeneralPath;

public abstract class PathCommand {
    public boolean isRelative = false;

    public PathCommand() {
    }

    public PathCommand(boolean isRelative) {
        this.isRelative = isRelative;
    }

    public abstract void appendPath(GeneralPath var1, BuildHistory var2);

    public abstract int getNumKnotsAdded();
}

