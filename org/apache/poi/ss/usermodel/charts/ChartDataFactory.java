/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.ScatterChartData;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartDataFactory {
    public ScatterChartData createScatterChartData();

    public LineChartData createLineChartData();
}

