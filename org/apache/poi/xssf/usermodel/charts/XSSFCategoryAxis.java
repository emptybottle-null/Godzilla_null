/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisOrientation;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.AxisTickMark;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.charts.XSSFChartAxis;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

@Deprecated
@Removal(version="4.2")
public class XSSFCategoryAxis
extends XSSFChartAxis {
    private CTCatAx ctCatAx;

    public XSSFCategoryAxis(XSSFChart chart, long id, AxisPosition pos) {
        super(chart);
        this.createAxis(id, pos);
    }

    public XSSFCategoryAxis(XSSFChart chart, CTCatAx ctCatAx) {
        super(chart);
        this.ctCatAx = ctCatAx;
    }

    @Override
    public long getId() {
        return this.ctCatAx.getAxId().getVal();
    }

    @Override
    @Internal
    public CTShapeProperties getLine() {
        return this.ctCatAx.getSpPr();
    }

    @Override
    protected CTAxPos getCTAxPos() {
        return this.ctCatAx.getAxPos();
    }

    @Override
    protected CTNumFmt getCTNumFmt() {
        if (this.ctCatAx.isSetNumFmt()) {
            return this.ctCatAx.getNumFmt();
        }
        return this.ctCatAx.addNewNumFmt();
    }

    @Override
    protected CTScaling getCTScaling() {
        return this.ctCatAx.getScaling();
    }

    @Override
    protected CTCrosses getCTCrosses() {
        return this.ctCatAx.getCrosses();
    }

    @Override
    protected CTBoolean getDelete() {
        return this.ctCatAx.getDelete();
    }

    @Override
    protected CTTickMark getMajorCTTickMark() {
        return this.ctCatAx.getMajorTickMark();
    }

    @Override
    protected CTTickMark getMinorCTTickMark() {
        return this.ctCatAx.getMinorTickMark();
    }

    @Override
    @Internal
    public CTChartLines getMajorGridLines() {
        return this.ctCatAx.getMajorGridlines();
    }

    @Override
    public void crossAxis(ChartAxis axis) {
        this.ctCatAx.getCrossAx().setVal(axis.getId());
    }

    private void createAxis(long id, AxisPosition pos) {
        this.ctCatAx = this.chart.getCTChart().getPlotArea().addNewCatAx();
        this.ctCatAx.addNewAxId().setVal(id);
        this.ctCatAx.addNewAxPos();
        this.ctCatAx.addNewScaling();
        this.ctCatAx.addNewCrosses();
        this.ctCatAx.addNewCrossAx();
        this.ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);
        this.ctCatAx.addNewDelete();
        this.ctCatAx.addNewMajorTickMark();
        this.ctCatAx.addNewMinorTickMark();
        this.setPosition(pos);
        this.setOrientation(AxisOrientation.MIN_MAX);
        this.setCrosses(AxisCrosses.AUTO_ZERO);
        this.setVisible(true);
        this.setMajorTickMark(AxisTickMark.CROSS);
        this.setMinorTickMark(AxisTickMark.NONE);
    }

    @Override
    public boolean hasNumberFormat() {
        return this.ctCatAx.isSetNumFmt();
    }
}

