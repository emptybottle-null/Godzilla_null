/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.charts.AbstractXSSFChartSeries;
import org.apache.poi.xssf.usermodel.charts.XSSFChartUtil;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

@Deprecated
@Removal(version="4.2")
public class XSSFLineChartData
implements LineChartData {
    private List<Series> series = new ArrayList<Series>();

    @Override
    public LineChartSeries addSeries(ChartDataSource<?> categoryAxisData, ChartDataSource<? extends Number> values) {
        if (!values.isNumeric()) {
            throw new IllegalArgumentException("Value data source must be numeric.");
        }
        int numOfSeries = this.series.size();
        Series newSeries = new Series(numOfSeries, numOfSeries, categoryAxisData, values);
        this.series.add(newSeries);
        return newSeries;
    }

    @Override
    public List<? extends LineChartSeries> getSeries() {
        return this.series;
    }

    @Override
    public void fillChart(Chart chart, ChartAxis ... axis) {
        if (!(chart instanceof XSSFChart)) {
            throw new IllegalArgumentException("Chart must be instance of XSSFChart");
        }
        XSSFChart xssfChart = (XSSFChart)chart;
        CTPlotArea plotArea = xssfChart.getCTChart().getPlotArea();
        CTLineChart lineChart = plotArea.addNewLineChart();
        lineChart.addNewVaryColors().setVal(false);
        for (Series s : this.series) {
            s.addToChart(lineChart);
        }
        for (ChartAxis ax : axis) {
            lineChart.addNewAxId().setVal(ax.getId());
        }
    }

    static class Series
    extends AbstractXSSFChartSeries
    implements LineChartSeries {
        private int id;
        private int order;
        private ChartDataSource<?> categories;
        private ChartDataSource<? extends Number> values;

        protected Series(int id, int order, ChartDataSource<?> categories, ChartDataSource<? extends Number> values) {
            this.id = id;
            this.order = order;
            this.categories = categories;
            this.values = values;
        }

        @Override
        public ChartDataSource<?> getCategoryAxisData() {
            return this.categories;
        }

        @Override
        public ChartDataSource<? extends Number> getValues() {
            return this.values;
        }

        protected void addToChart(CTLineChart ctLineChart) {
            CTLineSer ctLineSer = ctLineChart.addNewSer();
            ctLineSer.addNewIdx().setVal(this.id);
            ctLineSer.addNewOrder().setVal(this.order);
            ctLineSer.addNewMarker().addNewSymbol().setVal(STMarkerStyle.NONE);
            CTAxDataSource catDS = ctLineSer.addNewCat();
            XSSFChartUtil.buildAxDataSource(catDS, this.categories);
            CTNumDataSource valueDS = ctLineSer.addNewVal();
            XSSFChartUtil.buildNumDataSource(valueDS, this.values);
            if (this.isTitleSet()) {
                ctLineSer.setTx(this.getCTSerTx());
            }
        }
    }
}

