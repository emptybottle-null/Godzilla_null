/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisOrientation;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickLabelPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFTitle;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class XDDFDateAxis
extends XDDFChartAxis {
    private CTDateAx ctDateAx;

    public XDDFDateAxis(CTPlotArea plotArea, AxisPosition position) {
        this.initializeAxis(plotArea, position);
    }

    public XDDFDateAxis(CTDateAx ctDateAx) {
        this.ctDateAx = ctDateAx;
    }

    @Override
    public XDDFShapeProperties getOrAddMajorGridProperties() {
        CTChartLines majorGridlines = this.ctDateAx.isSetMajorGridlines() ? this.ctDateAx.getMajorGridlines() : this.ctDateAx.addNewMajorGridlines();
        return new XDDFShapeProperties(this.getOrAddLinesProperties(majorGridlines));
    }

    @Override
    public XDDFShapeProperties getOrAddMinorGridProperties() {
        CTChartLines minorGridlines = this.ctDateAx.isSetMinorGridlines() ? this.ctDateAx.getMinorGridlines() : this.ctDateAx.addNewMinorGridlines();
        return new XDDFShapeProperties(this.getOrAddLinesProperties(minorGridlines));
    }

    @Override
    public XDDFShapeProperties getOrAddShapeProperties() {
        CTShapeProperties properties = this.ctDateAx.isSetSpPr() ? this.ctDateAx.getSpPr() : this.ctDateAx.addNewSpPr();
        return new XDDFShapeProperties(properties);
    }

    @Override
    public XDDFRunProperties getOrAddTextProperties() {
        CTTextBody text = this.ctDateAx.isSetTxPr() ? this.ctDateAx.getTxPr() : this.ctDateAx.addNewTxPr();
        return new XDDFRunProperties(this.getOrAddTextProperties(text));
    }

    @Override
    public void setTitle(String text) {
        if (!this.ctDateAx.isSetTitle()) {
            this.ctDateAx.addNewTitle();
        }
        XDDFTitle title = new XDDFTitle(null, this.ctDateAx.getTitle());
        title.setOverlay(false);
        title.setText(text);
    }

    @Override
    public boolean isSetMinorUnit() {
        return this.ctDateAx.isSetMinorUnit();
    }

    @Override
    public void setMinorUnit(double minor) {
        if (Double.isNaN(minor)) {
            if (this.ctDateAx.isSetMinorUnit()) {
                this.ctDateAx.unsetMinorUnit();
            }
        } else if (this.ctDateAx.isSetMinorUnit()) {
            this.ctDateAx.getMinorUnit().setVal(minor);
        } else {
            this.ctDateAx.addNewMinorUnit().setVal(minor);
        }
    }

    @Override
    public double getMinorUnit() {
        if (this.ctDateAx.isSetMinorUnit()) {
            return this.ctDateAx.getMinorUnit().getVal();
        }
        return Double.NaN;
    }

    @Override
    public boolean isSetMajorUnit() {
        return this.ctDateAx.isSetMajorUnit();
    }

    @Override
    public void setMajorUnit(double major) {
        if (Double.isNaN(major)) {
            if (this.ctDateAx.isSetMajorUnit()) {
                this.ctDateAx.unsetMajorUnit();
            }
        } else if (this.ctDateAx.isSetMajorUnit()) {
            this.ctDateAx.getMajorUnit().setVal(major);
        } else {
            this.ctDateAx.addNewMajorUnit().setVal(major);
        }
    }

    @Override
    public double getMajorUnit() {
        if (this.ctDateAx.isSetMajorUnit()) {
            return this.ctDateAx.getMajorUnit().getVal();
        }
        return Double.NaN;
    }

    @Override
    public void crossAxis(XDDFChartAxis axis) {
        this.ctDateAx.getCrossAx().setVal(axis.getId());
    }

    @Override
    protected CTUnsignedInt getCTAxId() {
        return this.ctDateAx.getAxId();
    }

    @Override
    protected CTAxPos getCTAxPos() {
        return this.ctDateAx.getAxPos();
    }

    @Override
    public boolean hasNumberFormat() {
        return this.ctDateAx.isSetNumFmt();
    }

    @Override
    protected CTNumFmt getCTNumFmt() {
        if (this.ctDateAx.isSetNumFmt()) {
            return this.ctDateAx.getNumFmt();
        }
        return this.ctDateAx.addNewNumFmt();
    }

    @Override
    protected CTScaling getCTScaling() {
        return this.ctDateAx.getScaling();
    }

    @Override
    protected CTCrosses getCTCrosses() {
        CTCrosses crosses = this.ctDateAx.getCrosses();
        if (crosses == null) {
            return this.ctDateAx.addNewCrosses();
        }
        return crosses;
    }

    @Override
    protected CTBoolean getDelete() {
        return this.ctDateAx.getDelete();
    }

    @Override
    protected CTTickMark getMajorCTTickMark() {
        return this.ctDateAx.getMajorTickMark();
    }

    @Override
    protected CTTickMark getMinorCTTickMark() {
        return this.ctDateAx.getMinorTickMark();
    }

    @Override
    protected CTTickLblPos getCTTickLblPos() {
        return this.ctDateAx.getTickLblPos();
    }

    private void initializeAxis(CTPlotArea plotArea, AxisPosition position) {
        long id = this.getNextAxId(plotArea);
        this.ctDateAx = plotArea.addNewDateAx();
        this.ctDateAx.addNewAxId().setVal(id);
        this.ctDateAx.addNewAuto().setVal(false);
        this.ctDateAx.addNewAxPos();
        this.ctDateAx.addNewScaling();
        this.ctDateAx.addNewCrosses();
        this.ctDateAx.addNewCrossAx();
        this.ctDateAx.addNewTickLblPos();
        this.ctDateAx.addNewDelete();
        this.ctDateAx.addNewMajorTickMark();
        this.ctDateAx.addNewMinorTickMark();
        this.ctDateAx.addNewNumFmt().setSourceLinked(true);
        this.ctDateAx.getNumFmt().setFormatCode("");
        this.setPosition(position);
        this.setOrientation(AxisOrientation.MIN_MAX);
        this.setCrosses(AxisCrosses.AUTO_ZERO);
        this.setVisible(true);
        this.setMajorTickMark(AxisTickMark.CROSS);
        this.setMinorTickMark(AxisTickMark.NONE);
        this.setTickLabelPosition(AxisTickLabelPosition.NEXT_TO);
    }
}

