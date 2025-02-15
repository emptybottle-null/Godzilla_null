/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.BarGrouping;
import org.apache.poi.xddf.usermodel.chart.Shape;
import org.apache.poi.xddf.usermodel.chart.XDDFChart;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFErrorBars;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

public class XDDFBar3DChartData
extends XDDFChartData {
    private CTBar3DChart chart;

    @Internal
    protected XDDFBar3DChartData(XDDFChart parent, CTBar3DChart chart, Map<Long, XDDFChartAxis> categories, Map<Long, XDDFValueAxis> values) {
        super(parent);
        this.chart = chart;
        if (chart.getBarDir() == null) {
            chart.addNewBarDir().setVal(BarDirection.BAR.underlying);
        }
        for (CTBarSer series : chart.getSerList()) {
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

    public BarDirection getBarDirection() {
        return BarDirection.valueOf(this.chart.getBarDir().getVal());
    }

    public void setBarDirection(BarDirection direction) {
        this.chart.getBarDir().setVal(direction.underlying);
    }

    public BarGrouping getBarGrouping() {
        if (this.chart.isSetGrouping()) {
            return BarGrouping.valueOf(this.chart.getGrouping().getVal());
        }
        return null;
    }

    public void setBarGrouping(BarGrouping grouping) {
        if (grouping == null) {
            if (this.chart.isSetGrouping()) {
                this.chart.unsetGrouping();
            }
        } else if (this.chart.isSetGrouping()) {
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

    public Integer getGapWidth() {
        if (this.chart.isSetGapWidth()) {
            return this.chart.getGapWidth().getVal();
        }
        return null;
    }

    public void setGapWidth(Integer width) {
        if (width == null) {
            if (this.chart.isSetGapWidth()) {
                this.chart.unsetGapWidth();
            }
        } else if (this.chart.isSetGapWidth()) {
            this.chart.getGapWidth().setVal(width);
        } else {
            this.chart.addNewGapWidth().setVal(width);
        }
    }

    public Shape getShape() {
        if (this.chart.isSetShape()) {
            return Shape.valueOf(this.chart.getShape().getVal());
        }
        return null;
    }

    public void setShape(Shape shape) {
        if (shape == null) {
            if (this.chart.isSetShape()) {
                this.chart.unsetShape();
            }
        } else if (this.chart.isSetShape()) {
            this.chart.getShape().setVal(shape.underlying);
        } else {
            this.chart.addNewShape().setVal(shape.underlying);
        }
    }

    @Override
    public XDDFChartData.Series addSeries(XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
        long index = this.parent.incrementSeriesCount();
        CTBarSer ctSer = this.chart.addNewSer();
        ctSer.addNewTx();
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
        private CTBarSer series;

        protected Series(CTBarSer series, XDDFDataSource<?> category, XDDFNumericalDataSource<? extends Number> values) {
            super(XDDFBar3DChartData.this, category, values);
            this.series = series;
        }

        protected Series(CTBarSer series, CTAxDataSource category, CTNumDataSource values) {
            super(XDDFBar3DChartData.this, XDDFDataSourcesFactory.fromDataSource(category), XDDFDataSourcesFactory.fromDataSource(values));
            this.series = series;
        }

        @Override
        protected CTSerTx getSeriesText() {
            if (this.series.isSetTx()) {
                return this.series.getTx();
            }
            return this.series.addNewTx();
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

        public boolean getInvertIfNegative() {
            if (this.series.isSetInvertIfNegative()) {
                return this.series.getInvertIfNegative().getVal();
            }
            return false;
        }

        public void setInvertIfNegative(boolean invertIfNegative) {
            if (this.series.isSetInvertIfNegative()) {
                this.series.getInvertIfNegative().setVal(invertIfNegative);
            } else {
                this.series.addNewInvertIfNegative().setVal(invertIfNegative);
            }
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

