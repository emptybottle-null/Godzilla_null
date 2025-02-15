/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import java.util.List;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ScatterChartSeries;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ScatterChartData
extends ChartData {
    public ScatterChartSeries addSerie(ChartDataSource<?> var1, ChartDataSource<? extends Number> var2);

    public List<? extends ScatterChartSeries> getSeries();
}

