/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.util.Removal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;

@Deprecated
@Removal(version="4.2")
class XSSFChartUtil {
    private XSSFChartUtil() {
    }

    public static void buildAxDataSource(CTAxDataSource ctAxDataSource, ChartDataSource<?> dataSource) {
        if (dataSource.isNumeric()) {
            if (dataSource.isReference()) {
                XSSFChartUtil.buildNumRef(ctAxDataSource.addNewNumRef(), dataSource);
            } else {
                XSSFChartUtil.buildNumLit(ctAxDataSource.addNewNumLit(), dataSource);
            }
        } else if (dataSource.isReference()) {
            XSSFChartUtil.buildStrRef(ctAxDataSource.addNewStrRef(), dataSource);
        } else {
            XSSFChartUtil.buildStrLit(ctAxDataSource.addNewStrLit(), dataSource);
        }
    }

    public static void buildNumDataSource(CTNumDataSource ctNumDataSource, ChartDataSource<? extends Number> dataSource) {
        if (dataSource.isReference()) {
            XSSFChartUtil.buildNumRef(ctNumDataSource.addNewNumRef(), dataSource);
        } else {
            XSSFChartUtil.buildNumLit(ctNumDataSource.addNewNumLit(), dataSource);
        }
    }

    private static void buildNumRef(CTNumRef ctNumRef, ChartDataSource<?> dataSource) {
        ctNumRef.setF(dataSource.getFormulaString());
        CTNumData cache = ctNumRef.addNewNumCache();
        XSSFChartUtil.fillNumCache(cache, dataSource);
    }

    private static void buildNumLit(CTNumData ctNumData, ChartDataSource<?> dataSource) {
        XSSFChartUtil.fillNumCache(ctNumData, dataSource);
    }

    private static void buildStrRef(CTStrRef ctStrRef, ChartDataSource<?> dataSource) {
        ctStrRef.setF(dataSource.getFormulaString());
        CTStrData cache = ctStrRef.addNewStrCache();
        XSSFChartUtil.fillStringCache(cache, dataSource);
    }

    private static void buildStrLit(CTStrData ctStrData, ChartDataSource<?> dataSource) {
        XSSFChartUtil.fillStringCache(ctStrData, dataSource);
    }

    private static void fillStringCache(CTStrData cache, ChartDataSource<?> dataSource) {
        int numOfPoints = dataSource.getPointCount();
        cache.addNewPtCount().setVal(numOfPoints);
        for (int i = 0; i < numOfPoints; ++i) {
            Object value = dataSource.getPointAt(i);
            if (value == null) continue;
            CTStrVal ctStrVal = cache.addNewPt();
            ctStrVal.setIdx(i);
            ctStrVal.setV(value.toString());
        }
    }

    private static void fillNumCache(CTNumData cache, ChartDataSource<?> dataSource) {
        int numOfPoints = dataSource.getPointCount();
        cache.addNewPtCount().setVal(numOfPoints);
        for (int i = 0; i < numOfPoints; ++i) {
            Number value = (Number)dataSource.getPointAt(i);
            if (value == null) continue;
            CTNumVal ctNumVal = cache.addNewPt();
            ctNumVal.setIdx(i);
            ctNumVal.setV(value.toString());
        }
    }
}

