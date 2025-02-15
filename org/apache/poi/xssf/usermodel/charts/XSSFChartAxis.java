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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickMark;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

@Deprecated
@Removal(version="4.2")
public abstract class XSSFChartAxis
implements ChartAxis {
    protected XSSFChart chart;
    private static final double MIN_LOG_BASE = 2.0;
    private static final double MAX_LOG_BASE = 1000.0;

    protected XSSFChartAxis(XSSFChart chart) {
        this.chart = chart;
    }

    @Override
    public AxisPosition getPosition() {
        return XSSFChartAxis.toAxisPosition(this.getCTAxPos());
    }

    @Override
    public void setPosition(AxisPosition position) {
        this.getCTAxPos().setVal(XSSFChartAxis.fromAxisPosition(position));
    }

    @Override
    public void setNumberFormat(String format) {
        this.getCTNumFmt().setFormatCode(format);
        this.getCTNumFmt().setSourceLinked(true);
    }

    @Override
    public String getNumberFormat() {
        return this.getCTNumFmt().getFormatCode();
    }

    @Override
    public boolean isSetLogBase() {
        return this.getCTScaling().isSetLogBase();
    }

    @Override
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

    @Override
    public double getLogBase() {
        CTLogBase logBase = this.getCTScaling().getLogBase();
        if (logBase != null) {
            return logBase.getVal();
        }
        return 0.0;
    }

    @Override
    public boolean isSetMinimum() {
        return this.getCTScaling().isSetMin();
    }

    @Override
    public void setMinimum(double min) {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetMin()) {
            scaling.getMin().setVal(min);
        } else {
            scaling.addNewMin().setVal(min);
        }
    }

    @Override
    public double getMinimum() {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetMin()) {
            return scaling.getMin().getVal();
        }
        return 0.0;
    }

    @Override
    public boolean isSetMaximum() {
        return this.getCTScaling().isSetMax();
    }

    @Override
    public void setMaximum(double max) {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetMax()) {
            scaling.getMax().setVal(max);
        } else {
            scaling.addNewMax().setVal(max);
        }
    }

    @Override
    public double getMaximum() {
        CTScaling scaling = this.getCTScaling();
        if (scaling.isSetMax()) {
            return scaling.getMax().getVal();
        }
        return 0.0;
    }

    @Override
    public AxisOrientation getOrientation() {
        return XSSFChartAxis.toAxisOrientation(this.getCTScaling().getOrientation());
    }

    @Override
    public void setOrientation(AxisOrientation orientation) {
        CTScaling scaling = this.getCTScaling();
        STOrientation.Enum stOrientation = XSSFChartAxis.fromAxisOrientation(orientation);
        if (scaling.isSetOrientation()) {
            scaling.getOrientation().setVal(stOrientation);
        } else {
            this.getCTScaling().addNewOrientation().setVal(stOrientation);
        }
    }

    @Override
    public AxisCrosses getCrosses() {
        return XSSFChartAxis.toAxisCrosses(this.getCTCrosses());
    }

    @Override
    public void setCrosses(AxisCrosses crosses) {
        this.getCTCrosses().setVal(XSSFChartAxis.fromAxisCrosses(crosses));
    }

    @Override
    public boolean isVisible() {
        return !this.getDelete().getVal();
    }

    @Override
    public void setVisible(boolean value) {
        this.getDelete().setVal(!value);
    }

    @Override
    public AxisTickMark getMajorTickMark() {
        return XSSFChartAxis.toAxisTickMark(this.getMajorCTTickMark());
    }

    @Override
    public void setMajorTickMark(AxisTickMark tickMark) {
        this.getMajorCTTickMark().setVal(XSSFChartAxis.fromAxisTickMark(tickMark));
    }

    @Override
    public AxisTickMark getMinorTickMark() {
        return XSSFChartAxis.toAxisTickMark(this.getMinorCTTickMark());
    }

    @Override
    public void setMinorTickMark(AxisTickMark tickMark) {
        this.getMinorCTTickMark().setVal(XSSFChartAxis.fromAxisTickMark(tickMark));
    }

    protected abstract CTAxPos getCTAxPos();

    protected abstract CTNumFmt getCTNumFmt();

    protected abstract CTScaling getCTScaling();

    protected abstract CTCrosses getCTCrosses();

    protected abstract CTBoolean getDelete();

    protected abstract CTTickMark getMajorCTTickMark();

    protected abstract CTTickMark getMinorCTTickMark();

    @Internal
    public abstract CTChartLines getMajorGridLines();

    @Internal
    public abstract CTShapeProperties getLine();

    private static STOrientation.Enum fromAxisOrientation(AxisOrientation orientation) {
        switch (orientation) {
            case MIN_MAX: {
                return STOrientation.MIN_MAX;
            }
            case MAX_MIN: {
                return STOrientation.MAX_MIN;
            }
        }
        throw new IllegalArgumentException();
    }

    private static AxisOrientation toAxisOrientation(CTOrientation ctOrientation) {
        switch (ctOrientation.getVal().intValue()) {
            case 2: {
                return AxisOrientation.MIN_MAX;
            }
            case 1: {
                return AxisOrientation.MAX_MIN;
            }
        }
        throw new IllegalArgumentException();
    }

    private static STCrosses.Enum fromAxisCrosses(AxisCrosses crosses) {
        switch (crosses) {
            case AUTO_ZERO: {
                return STCrosses.AUTO_ZERO;
            }
            case MIN: {
                return STCrosses.MIN;
            }
            case MAX: {
                return STCrosses.MAX;
            }
        }
        throw new IllegalArgumentException();
    }

    private static AxisCrosses toAxisCrosses(CTCrosses ctCrosses) {
        switch (ctCrosses.getVal().intValue()) {
            case 1: {
                return AxisCrosses.AUTO_ZERO;
            }
            case 2: {
                return AxisCrosses.MAX;
            }
            case 3: {
                return AxisCrosses.MIN;
            }
        }
        throw new IllegalArgumentException();
    }

    private static STAxPos.Enum fromAxisPosition(AxisPosition position) {
        switch (position) {
            case BOTTOM: {
                return STAxPos.B;
            }
            case LEFT: {
                return STAxPos.L;
            }
            case RIGHT: {
                return STAxPos.R;
            }
            case TOP: {
                return STAxPos.T;
            }
        }
        throw new IllegalArgumentException();
    }

    private static AxisPosition toAxisPosition(CTAxPos ctAxPos) {
        switch (ctAxPos.getVal().intValue()) {
            case 1: {
                return AxisPosition.BOTTOM;
            }
            case 2: {
                return AxisPosition.LEFT;
            }
            case 3: {
                return AxisPosition.RIGHT;
            }
            case 4: {
                return AxisPosition.TOP;
            }
        }
        return AxisPosition.BOTTOM;
    }

    private static STTickMark.Enum fromAxisTickMark(AxisTickMark tickMark) {
        switch (tickMark) {
            case NONE: {
                return STTickMark.NONE;
            }
            case IN: {
                return STTickMark.IN;
            }
            case OUT: {
                return STTickMark.OUT;
            }
            case CROSS: {
                return STTickMark.CROSS;
            }
        }
        throw new IllegalArgumentException("Unknown AxisTickMark: " + (Object)((Object)tickMark));
    }

    private static AxisTickMark toAxisTickMark(CTTickMark ctTickMark) {
        switch (ctTickMark.getVal().intValue()) {
            case 3: {
                return AxisTickMark.NONE;
            }
            case 2: {
                return AxisTickMark.IN;
            }
            case 4: {
                return AxisTickMark.OUT;
            }
            case 1: {
                return AxisTickMark.CROSS;
            }
        }
        return AxisTickMark.CROSS;
    }
}

