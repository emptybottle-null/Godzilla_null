/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartAxisFactory {
    public ValueAxis createValueAxis(AxisPosition var1);

    public ChartAxis createCategoryAxis(AxisPosition var1);

    public ChartAxis createDateAxis(AxisPosition var1);
}

