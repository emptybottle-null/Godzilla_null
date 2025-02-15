/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;
import org.apache.poi.sl.draw.binding.CTAdjPoint2D;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.PathCommand;

public class MoveToCommand
implements PathCommand {
    private String arg1;
    private String arg2;

    MoveToCommand(CTAdjPoint2D pt) {
        this.arg1 = pt.getX();
        this.arg2 = pt.getY();
    }

    MoveToCommand(String s1, String s2) {
        this.arg1 = s1;
        this.arg2 = s2;
    }

    @Override
    public void execute(Path2D.Double path, Context ctx) {
        double x = ctx.getValue(this.arg1);
        double y = ctx.getValue(this.arg2);
        path.moveTo(x, y);
    }
}

