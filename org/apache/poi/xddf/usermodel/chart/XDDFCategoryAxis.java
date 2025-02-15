/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisLabelAlignment;
import org.apache.poi.xddf.usermodel.chart.AxisOrientation;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickLabelPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFTitle;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class XDDFCategoryAxis
extends XDDFChartAxis {
    private CTCatAx ctCatAx;

    public XDDFCategoryAxis(CTPlotArea plotArea, AxisPosition position) {
        this.initializeAxis(plotArea, position);
    }

    public XDDFCategoryAxis(CTCatAx ctCatAx) {
        this.ctCatAx = ctCatAx;
    }

    @Override
    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines majorGridlines = this.ctCatAx.isSetMajorGridlines() ? this.ctCatAx.getMajorGridlines() : this.ctCatAx.addNewMajorGridlines();
        return new XDDFShapeProperties(this.getOrAddLinesProperties(majorGridlines));
    }

    @Override
    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines minorGridlines = this.ctCatAx.isSetMinorGridlines() ? this.ctCatAx.getMinorGridlines() : this.ctCatAx.addNewMinorGridlines();
        return new XDDFShapeProperties(this.getOrAddLinesProperties(minorGridlines));
    }

    @Override
    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties = this.ctCatAx.isSetSpPr() ? this.ctCatAx.getSpPr() : this.ctCatAx.addNewSpPr();
        return new XDDFShapeProperties(properties);
    }

    @Override
    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text = this.ctCatAx.isSetTxPr() ? this.ctCatAx.getTxPr() : this.ctCatAx.addNewTxPr();
        return new XDDFRunProperties(this.getOrAddTextProperties(text));
    }

    @Override
    public void setTitle(String text) {
        if (!this.ctCatAx.isSetTitle()) {
            this.ctCatAx.addNewTitle();
        }
        XDDFTitle title = new XDDFTitle(null, this.ctCatAx.getTitle());
        title.setOverlay(false);
        title.setText(text);
    }

    @Override
    public boolean isSetMinorUnit() {
        return false;
    }

    @Override
    public void setMinorUnit(double minor) {
    }

    @Override
    public double getMinorUnit() {
        return Double.NaN;
    }

    @Override
    public boolean isSetMajorUnit() {
        return false;
    }

    @Override
    public void setMajorUnit(double major) {
    }

    @Override
    public double getMajorUnit() {
        return Double.NaN;
    }

    @Override
    public void crossAxis(XDDFChartAxis axis) {
        this.ctCatAx.getCrossAx().setVal(axis.getId());
    }

    @Override
    protected CTUnsignedInt getCTAxId() {
        return this.ctCatAx.getAxId();
    }

    @Override
    protected CTAxPos getCTAxPos() {
        return this.ctCatAx.getAxPos();
    }

    @Override
    public boolean hasNumberFormat() {
        return this.ctCatAx.isSetNumFmt();
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
        CTCrosses crosses = this.ctCatAx.getCrosses();
        if (crosses == null) {
            return this.ctCatAx.addNewCrosses();
        }
        return crosses;
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
    protected CTTickLblPos getCTTickLblPos() {
        return this.ctCatAx.getTickLblPos();
    }

    public AxisLabelAlignment getLabelAlignment() {
        return AxisLabelAlignment.valueOf(this.ctCatAx.getLblAlgn().getVal());
    }

    public void setLabelAlignment(AxisLabelAlignment labelAlignment) {
        this.ctCatAx.getLblAlgn().setVal(labelAlignment.underlying);
    }

    private void initializeAxis(CTPlotArea plotArea, AxisPosition position) {
        long id = this.getNextAxId(plotArea);
        this.ctCatAx = plotArea.addNewCatAx();
        this.ctCatAx.addNewAxId().setVal(id);
        this.ctCatAx.addNewAuto().setVal(false);
        this.ctCatAx.addNewAxPos();
        this.ctCatAx.addNewScaling();
        this.ctCatAx.addNewCrosses();
        this.ctCatAx.addNewCrossAx();
        this.ctCatAx.addNewTickLblPos();
        this.ctCatAx.addNewDelete();
        this.ctCatAx.addNewMajorTickMark();
        this.ctCatAx.addNewMinorTickMark();
        this.ctCatAx.addNewNumFmt().setSourceLinked(true);
        this.ctCatAx.getNumFmt().setFormatCode("");
        this.setPosition(position);
        this.setOrientation(AxisOrientation.MIN_MAX);
        this.setCrosses(AxisCrosses.AUTO_ZERO);
        this.setVisible(true);
        this.setMajorTickMark(AxisTickMark.CROSS);
        this.setMinorTickMark(AxisTickMark.NONE);
        this.setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}

