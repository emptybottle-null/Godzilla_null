/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import org.apache.poi.sl.draw.binding.CTGeomGuide;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.Guide;

public class AdjustValue
extends Guide {
    public AdjustValue(CTGeomGuide gd) {
        super(gd.getName(), gd.getFmla());
    }

    @Override
    public double evaluate(Context ctx) {
        String name = this.getName();
        Guide adj = ctx.getAdjustValue(name);
        return adj != null ? adj.evaluate(ctx) : super.evaluate(ctx);
    }
}

