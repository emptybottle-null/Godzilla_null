/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.ChartDataFactory;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.charts.XSSFLineChartData;
import org.apache.poi.xssf.usermodel.charts.XSSFScatterChartData;

@Deprecated
@Removal(version="4.2")
public class XSSFChartDataFactory
implements ChartDataFactory {
    private static XSSFChartDataFactory instance;

    private XSSFChartDataFactory() {
    }

    @Override
    public XSSFScatterChartData createScatterChartData() {
        return new XSSFScatterChartData();
    }

    @Override
    public XSSFLineChartData createLineChartData() {
        return new XSSFLineChartData();
    }

    public static XSSFChartDataFactory getInstance() {
        if (instance == null) {
            instance = new XSSFChartDataFactory();
        }
        return instance;
    }
}

