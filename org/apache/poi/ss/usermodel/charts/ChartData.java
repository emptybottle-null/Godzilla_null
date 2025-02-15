/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartData {
    public void fillChart(Chart var1, ChartAxis ... var2);
}

