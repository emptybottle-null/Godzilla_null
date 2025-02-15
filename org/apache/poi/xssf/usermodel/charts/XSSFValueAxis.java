/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.AxisCrossBetween;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisOrientation;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.AxisTickMark;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.charts.XSSFChartAxis;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrossBetween;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

@Deprecated
@Removal(version="4.2")
public class XSSFValueAxis
extends XSSFChartAxis
implements ValueAxis {
    private CTValAx ctValAx;

    public XSSFValueAxis(XSSFChart chart, long id, AxisPosition pos) {
        super(chart);
        this.createAxis(id, pos);
    }

    public XSSFValueAxis(XSSFChart chart, CTValAx ctValAx) {
        super(chart);
        this.ctValAx = ctValAx;
    }

    @Override
    public long getId() {
        return this.ctValAx.getAxId().getVal();
    }

    @Override
    @Internal
    public CTShapeProperties getLine() {
        return this.ctValAx.getSpPr();
    }

    @Override
    public void setCrossBetween(AxisCrossBetween crossBetween) {
        this.ctValAx.getCrossBetween().setVal(XSSFValueAxis.fromCrossBetween(crossBetween));
    }

    @Override
    public AxisCrossBetween getCrossBetween() {
        return XSSFValueAxis.toCrossBetween(this.ctValAx.getCrossBetween().getVal());
    }

    @Override
    protected CTAxPos getCTAxPos() {
        return this.ctValAx.getAxPos();
    }

    @Override
    protected CTNumFmt getCTNumFmt() {
        if (this.ctValAx.isSetNumFmt()) {
            return this.ctValAx.getNumFmt();
        }
        return this.ctValAx.addNewNumFmt();
    }

    @Override
    protected CTScaling getCTScaling() {
        return this.ctValAx.getScaling();
    }

    @Override
    protected CTCrosses getCTCrosses() {
        return this.ctValAx.getCrosses();
    }

    @Override
    protected CTBoolean getDelete() {
        return this.ctValAx.getDelete();
    }

    @Override
    protected CTTickMark getMajorCTTickMark() {
        return this.ctValAx.getMajorTickMark();
    }

    @Override
    protected CTTickMark getMinorCTTickMark() {
        return this.ctValAx.getMinorTickMark();
    }

    @Override
    @Internal
    public CTChartLines getMajorGridLines() {
        return this.ctValAx.getMajorGridlines();
    }

    @Override
    public void crossAxis(ChartAxis axis) {
        this.ctValAx.getCrossAx().setVal(axis.getId());
    }

    private void createAxis(long id, AxisPosition pos) {
        this.ctValAx = this.chart.getCTChart().getPlotArea().addNewValAx();
        this.ctValAx.addNewAxId().setVal(id);
        this.ctValAx.addNewAxPos();
        this.ctValAx.addNewScaling();
        this.ctValAx.addNewCrossBetween();
        this.ctValAx.addNewCrosses();
        this.ctValAx.addNewCrossAx();
        this.ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        this.ctValAx.addNewDelete();
        this.ctValAx.addNewMajorTickMark();
        this.ctValAx.addNewMinorTickMark();
        this.setPosition(pos);
        this.setOrientation(AxisOrientation.MIN_MAX);
        this.setCrossBetween(AxisCrossBetween.MIDPOINT_CATEGORY);
        this.setCrosses(AxisCrosses.AUTO_ZERO);
        this.setVisible(true);
        this.setMajorTickMark(AxisTickMark.CROSS);
        this.setMinorTickMark(AxisTickMark.NONE);
    }

    private static STCrossBetween.Enum fromCrossBetween(AxisCrossBetween crossBetween) {
        switch (crossBetween) {
            case BETWEEN: {
                return STCrossBetween.BETWEEN;
            }
            case MIDPOINT_CATEGORY: {
                return STCrossBetween.MID_CAT;
            }
        }
        throw new IllegalArgumentException();
    }

    private static AxisCrossBetween toCrossBetween(STCrossBetween.Enum ctCrossBetween) {
        switch (ctCrossBetween.intValue()) {
            case 1: {
                return AxisCrossBetween.BETWEEN;
            }
            case 2: {
                return AxisCrossBetween.MIDPOINT_CATEGORY;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean hasNumberFormat() {
        return this.ctValAx.isSetNumFmt();
    }
}

