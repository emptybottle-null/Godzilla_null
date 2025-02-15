/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.HasShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisOrientation;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickLabelPosition;
import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public abstract class XDDFChartAxis
implements HasShapeProperties {
    private static final double MIN_LOG_BASE = 2.0;
    private static final double MAX_LOG_BASE = 1000.0;

    protected abstract CTUnsignedInt getCTAxId();

    protected abstract CTAxPos getCTAxPos();

    protected abstract CTNumFmt getCTNumFmt();

    protected abstract CTScaling getCTScaling();

    protected abstract CTCrosses getCTCrosses();

    protected abstract CTBoolean getDelete();

    protected abstract CTTickMark getMajorCTTickMark();

    protected abstract CTTickMark getMinorCTTickMark();

    protected abstract CTTickLblPos getCTTickLblPos();

    public abstract XDDFShapeProperties getOrAddMajorGridProperties();

    public abstract XDDFShapeProperties getOrAddMinorGridProperties();

    public abstract XDDFRunProperties getOrAddTextProperties();

    public abstract void setTitle(String var1);

    public abstract boolean isSetMinorUnit();

    public abstract void setMinorUnit(double var1);

    public abstract double getMinorUnit();

    public abstract boolean isSetMajorUnit();

    public abstract void setMajorUnit(double var1);

    public abstract double getMajorUnit();

    public long getId() {
        return this.getCTAxId().getVal();
    }

    public AxisPosition getPosition() {
        return AxisPosition.valueOf(this.getCTAxPos().getVal());
    }

    public void setPosition(AxisPosition position) {
        this.getCTAxPos().setVal(position.underlying);
    }

    public abstract boolean hasNumberFormat();

    public void setNumberFormat(String format) {
        this.getCTNumFmt().setFormatCode(format);
        this.getCTNumFmt().setSourceLinked(true);
    }

    public String getNumberFormat() {
        return this.getCTNumFmt().getFormatCode();
    }

    public boolean isSetLogBase() {
        return this.getCTScaling().isSetLogBase();
    }

    public void setLogBase(double logBase) {
        if (logBase < 2.0 || 1000.0 < logBase) {
            throw new IllegalArgumentException("Axis log base must be between 2 and 1000 (inclusive), got: " + logBase);
        }
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetLogBase()) {
            scaling.getLogBase().setVal(logBase);
        } else {
            scaling.addNewLogBase().setVal(logBase);
        }
    }

    public double getLogBase() {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetLogBase()) {
            return scaling.getLogBase().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMinimum() {
        return this.getCTScaling().isSetMin();
    }

    public void setMinimum(double min) {
        CTScaling scaling = this.getCTScaling();
        if (Double.isNaN(min)) {
            if (scaling.isSetMin()) {
                scaling.unsetMin();
            }
        } else if (scaling.isSetMin()) {
            scaling.getMin().setVal(min);
        } else {
            scaling.addNewMin().setVal(min);
        }
    }

    public double getMinimum() {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetMin()) {
            return scaling.getMin().getVal();
        }
        return Double.NaN;
    }

    public boolean isSetMaximum() {
        return this.getCTScaling().isSetMax();
    }

    public void setMaximum(double max) {
        CTScaling scaling = this.getCTScaling();
        if (Double.isNaN(max)) {
            if (scaling.isSetMax()) {
                scaling.unsetMax();
            }
        } else if (scaling.isSetMax()) {
            scaling.getMax().setVal(max);
        } else {
            scaling.addNewMax().setVal(max);
        }
    }

    public double getMaximum() {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetMax()) {
            return scaling.getMax().getVal();
        }
        return Double.NaN;
    }

    public AxisOrientation getOrientation() {
        return AxisOrientation.valueOf(this.getCTScaling().getOrientation().getVal());
    }

    public void setOrientation(AxisOrientation orientation) {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetOrientation()) {
            scaling.getOrientation().setVal(orientation.underlying);
        } else {
            scaling.addNewOrientation().setVal(orientation.underlying);
        }
    }

    public AxisCrosses getCrosses() {
        return AxisCrosses.valueOf(this.getCTCrosses().getVal());
    }

    public void setCrosses(AxisCrosses crosses) {
        this.getCTCrosses().setVal(crosses.underlying);
    }

    public abstract void crossAxis(XDDFChartAxis var1);

    public boolean isVisible() {
        return !this.getDelete().getVal();
    }

    public void setVisible(boolean value) {
        this.getDelete().setVal(!value);
    }

    public AxisTickMark getMajorTickMark() {
        return AxisTickMark.valueOf(this.getMajorCTTickMark().getVal());
    }

    public void setMajorTickMark(AxisTickMark tickMark) {
        this.getMajorCTTickMark().setVal(tickMark.underlying);
    }

    public AxisTickMark getMinorTickMark() {
        return AxisTickMark.valueOf(this.getMinorCTTickMark().getVal());
    }

    public void setMinorTickMark(AxisTickMark tickMark) {
        this.getMinorCTTickMark().setVal(tickMark.underlying);
    }

    public AxisTickLabelPosition getTickLabelPosition() {
        return AxisTickLabelPosition.valueOf(this.getCTTickLblPos().getVal());
    }

    public void setTickLabelPosition(AxisTickLabelPosition labelPosition) {
        this.getCTTickLblPos().setVal(labelPosition.underlying);
    }

    protected CTTextCharacterProperties getOrAddTextProperties(CTTextBody body) {
        if (body.getBodyPr() == null) {
            body.addNewBodyPr();
        }
        CTTextParagraph paragraph = body.sizeOfPArray() > 0 ? body.getPArray(0) : body.addNewP();
        CTTextParagraphProperties paraprops = paragraph.isSetPPr() ? paragraph.getPPr() : paragraph.addNewPPr();
        CTTextCharacterProperties properties = paraprops.isSetDefRPr() ? paraprops.getDefRPr() : paraprops.addNewDefRPr();
        return properties;
    }

    protected CTShapeProperties getOrAddLinesProperties(CTChartLines gridlines) {
        CTShapeProperties properties = gridlines.isSetSpPr() ? gridlines.getSpPr() : gridlines.addNewSpPr();
        return properties;
    }

    protected long getNextAxId(CTPlotArea plotArea) {
        long totalAxisCount = plotArea.sizeOfValAxArray() + plotArea.sizeOfCatAxArray() + plotArea.sizeOfDateAxArray() + plotArea.sizeOfSerAxArray();
        return totalAxisCount;
    }
}

