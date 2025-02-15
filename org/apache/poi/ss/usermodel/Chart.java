/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.util.List;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartAxisFactory;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.apache.poi.ss.usermodel.charts.ChartDataFactory;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.ManuallyPositionable;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface Chart
extends ManuallyPositionable {
    public ChartDataFactory getChartDataFactory();

    public ChartAxisFactory getChartAxisFactory();

    public ChartLegend getOrCreateLegend();

    public void deleteLegend();

    public List<? extends ChartAxis> getAxis();

    public void plot(ChartData var1, ChartAxis ... var2);
}

