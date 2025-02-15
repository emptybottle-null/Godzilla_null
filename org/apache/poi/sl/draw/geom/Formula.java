/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import org.apache.poi.sl.draw.geom.Context;

public interface Formula {
    public static final double OOXML_DEGREE = 60000.0;

    public double evaluate(Context var1);
}

