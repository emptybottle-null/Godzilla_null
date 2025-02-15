/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section.geometry;

import java.awt.geom.Path2D;
import org.apache.poi.xdgf.usermodel.XDGFShape;

public interface GeometryRow {
    public void setupMaster(GeometryRow var1);

    public void addToPath(Path2D.Double var1, XDGFShape var2);
}

