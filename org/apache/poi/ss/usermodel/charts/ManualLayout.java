/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.LayoutMode;
import org.apache.poi.ss.usermodel.charts.LayoutTarget;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ManualLayout {
    public void setTarget(LayoutTarget var1);

    public LayoutTarget getTarget();

    public void setXMode(LayoutMode var1);

    public LayoutMode getXMode();

    public void setYMode(LayoutMode var1);

    public LayoutMode getYMode();

    public double getX();

    public void setX(double var1);

    public double getY();

    public void setY(double var1);

    public void setWidthMode(LayoutMode var1);

    public LayoutMode getWidthMode();

    public void setHeightMode(LayoutMode var1);

    public LayoutMode getHeightMode();

    public void setWidthRatio(double var1);

    public double getWidthRatio();

    public void setHeightRatio(double var1);

    public double getHeightRatio();
}

