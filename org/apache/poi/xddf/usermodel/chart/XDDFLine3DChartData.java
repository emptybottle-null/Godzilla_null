/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.Grouping;
import org.apache.poi.xddf.usermodel.chart.MarkerStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFErrorBars;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFLine3DChartData
extends XDDFChartData {
    private CTLine3DChart chart;

    @Internal
    protected XDDFLine3DChartData(XDDFChart parent, CTLine3DChart chart, Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        super(parent);
        this.chart = chart;
        for (CTLineSer series : chart.getSerList()) {
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

    public Grouping getGrouping() {
        return Grouping.valueOf(this.chart.getGrouping().getVal());
    }

    public void setGrouping(Grouping grouping) {
        if (this.chart.getGrouping() != null) {
            this.chart.getGrouping().setVal(grouping.underlying);
        } else {
            this.chart.addNewGrouping().setVal(grouping.underlying);
        }
    }

    public Integer getGapDepth() {
        if (this.chart.isSetGapDepth()) {
            return this.chart.getGapDepth().getVal();
        }
        return null;
    }

    public void setGapDepth(Integer depth) {
        if (depth == null) {
            if (this.chart.isSetGapDepth()) {
                this.chart.unsetGapDepth();
            }
        } else if (this.chart.isSetGapDepth()) {
            this.chart.getGapDepth().setVal(depth);
        } else {
            this.chart.addNewGapDepth().setVal(depth);
        }
    }

    @Override
    public XDDFChartData.Series addSeries(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
        long index = this.parent.incrementSeriesCount();
        CTLineSer ctSer = this.chart.addNewSer();
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
        private CTLineSer series;

        protected Series(CTLineSer series, XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            super(category, values);
            this.series = series;
        }

        protected Series(CTLineSer series, CTAxDataSource category, CTNumDataSource values) {
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

        public Boolean isSmooth() {
            if (this.series.isSetSmooth()) {
                return this.series.getSmooth().getVal();
            }
            return null;
        }

        public void setSmooth(Boolean smooth) {
            if (smooth == null) {
                if (this.series.isSetSmooth()) {
                    this.series.unsetSmooth();
                }
            } else if (this.series.isSetSmooth()) {
                this.series.getSmooth().setVal(smooth);
            } else {
                this.series.addNewSmooth().setVal(smooth);
            }
        }

        public void setMarkerSize(short size) {
            if (size < 2 || 72 < size) {
                throw new IllegalArgumentException("Minimum inclusive: 2; Maximum inclusive: 72");
            }
            CTMarker marker = this.getMarker();
            if (marker.isSetSize()) {
                marker.getSize().setVal(size);
            } else {
                marker.addNewSize().setVal(size);
            }
        }

        public void setMarkerStyle(MarkerStyle style) {
            CTMarker marker = this.getMarker();
            if (marker.isSetSymbol()) {
                marker.getSymbol().setVal(style.underlying);
            } else {
                marker.addNewSymbol().setVal(style.underlying);
            }
        }

        private CTMarker getMarker() {
            if (this.series.isSetMarker()) {
                return this.series.getMarker();
            }
            return this.series.addNewMarker();
        }

        public boolean hasErrorBars() {
            return this.series.isSetErrBars();
        }

        public XDDFErrorBars getErrorBars() {
            if (this.series.isSetErrBars()) {
                return new XDDFErrorBars(this.series.getErrBars());
            }
            return null;
        }

        public void setErrorBars(XDDFErrorBars bars) {
            if (bars == null) {
                if (this.series.isSetErrBars()) {
                    this.series.unsetErrBars();
                }
            } else if (this.series.isSetErrBars()) {
                this.series.getErrBars().set(bars.getXmlObject());
            } else {
                this.series.addNewErrBars().set(bars.getXmlObject());
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

