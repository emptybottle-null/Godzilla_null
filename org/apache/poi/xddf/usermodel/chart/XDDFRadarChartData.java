/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.RadarStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFRadarChartData
extends XDDFChartData {
    private CTRadarChart chart;

    @Internal
    protected XDDFRadarChartData(XDDFChart parent, CTRadarChart chart, Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        super(parent);
        this.chart = chart;
        for (CTRadarSer series : chart.getSerList()) {
            this.series.add(new Series(series, series.getCat(), series.getVal()));
        }
        this.defineAxes(categories, values);
    }

    private void defineAxes(Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        if (this.chart.sizeOfAxIdArray() == 0) {
            for (Long id : categories.keySet()) {
                this.chart.addNewAxId().setVal(id);
            }
            for (Long id : values.keySet()) {
                this.chart.addNewAxId().setVal(id);
            }
        }
        this.defineAxes(this.chart.getAxIdArray(), categories, values);
    }

    @Override
    @Internal
    protected void removeCTSeries(int n) {
        this.chart.removeSer(n);
    }

    @Override
    public void setVaryColors(Boolean varyColors) {
        if (varyColors == null) {
            if (this.chart.isSetVaryColors()) {
                this.chart.unsetVaryColors();
            }
        } else if (this.chart.isSetVaryColors()) {
            this.chart.getVaryColors().setVal(varyColors);
        } else {
            this.chart.addNewVaryColors().setVal(varyColors);
        }
    }

    public RadarStyle getStyle() {
        return RadarStyle.valueOf(this.chart.getRadarStyle().getVal());
    }

    public void setStyle(RadarStyle style) {
        CTRadarStyle radarStyle = this.chart.getRadarStyle();
        if (radarStyle == null) {
            radarStyle = this.chart.addNewRadarStyle();
        }
        radarStyle.setVal(style.underlying);
    }

    @Override
    public XDDFChartData.Series addSeries(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
        long index = this.parent.incrementSeriesCount();
        CTRadarSer ctSer = this.chart.addNewSer();
        ctSer.addNewCat();
        ctSer.addNewVal();
        ctSer.addNewIdx().setVal(index);
        ctSer.addNewOrder().setVal(index);
        Series added = new Series(ctSer, category, values);
        this.series.add(added);
        return added;
    }

    public class Series
    extends XDDFChartData.Series {
        private CTRadarSer series;

        protected Series(CTRadarSer series, XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            super(category, values);
            this.series = series;
        }

        protected Series(CTRadarSer series, CTAxDataSource category, CTNumDataSource values) {
            super(XDDFDataSourcesFactory.fromDataSource(category), XDDFDataSourcesFactory.fromDataSource(values));
            this.series = series;
        }

        @Override
        protected CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
        }

        @Override
        public void setShowLeaderLines(boolean showLeaderLines) {
            if (!this.series.isSetDLbls()) {
                this.series.addNewDLbls();
            }
            if (this.series.getDLbls().isSetShowLeaderLines()) {
                this.series.getDLbls().getShowLeaderLines().setVal(showLeaderLines);
            } else {
                this.series.getDLbls().addNewShowLeaderLines().setVal(showLeaderLines);
            }
        }

        @Override
        public XDDFShapeProperties getShapeProperties() {
            if (this.series.isSetSpPr()) {
                return new XDDFShapeProperties(this.series.getSpPr());
            }
            return null;
        }

        @Override
        public void setShapeProperties(XDDFShapeProperties properties) {
            if (properties == null) {
                if (this.series.isSetSpPr()) {
                    this.series.unsetSpPr();
                }
            } else if (this.series.isSetSpPr()) {
                this.series.setSpPr(properties.getXmlObject());
            } else {
                this.series.addNewSpPr().set(properties.getXmlObject());
            }
        }

        @Override
        protected CTAxDataSource getAxDS() {
            return this.series.getCat();
        }

        @Override
        protected CTNumDataSource getNumDS() {
            return this.series.getVal();
        }

        @Override
        protected void setIndex(long val) {
            this.series.getIdx().setVal(val);
        }

        @Override
        protected void setOrder(long val) {
            this.series.getOrder().setVal(val);
        }
    }
}

