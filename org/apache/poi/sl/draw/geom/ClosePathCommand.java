/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.PathCommand;

public class ClosePathCommand
implements PathCommand {
    ClosePathCommand() {
    }

    @Override
    public void execute(Path2D.Double path, Context ctx) {
        path.closePath();
    }
}

