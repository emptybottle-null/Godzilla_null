/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.AxisCrossBetween;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ValueAxis
extends ChartAxis {
    public AxisCrossBetween getCrossBetween();

    public void setCrossBetween(AxisCrossBetween var1);
}

