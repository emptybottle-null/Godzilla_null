/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import java.util.List;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface LineChartData
extends ChartData {
    public LineChartSeries addSeries(ChartDataSource<?> var1, ChartDataSource<? extends Number> var2);

    public List<? extends LineChartSeries> getSeries();
}

