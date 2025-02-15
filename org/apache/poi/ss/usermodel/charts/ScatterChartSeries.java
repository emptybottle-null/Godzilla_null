/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ChartSeries;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ScatterChartSeries
extends ChartSeries {
    public ChartDataSource<?> getXValues();

    public ChartDataSource<? extends Number> getYValues();
}

