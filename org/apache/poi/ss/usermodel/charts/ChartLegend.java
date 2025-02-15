/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.ManuallyPositionable;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartLegend
extends ManuallyPositionable {
    public LegendPosition getPosition();

    public void setPosition(LegendPosition var1);

    public boolean isOverlay();

    public void setOverlay(boolean var1);
}

