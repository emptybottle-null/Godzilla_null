/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.charts.XSSFManualLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

@Deprecated
@Removal(version="4.2")
public final class XSSFChartLegend
implements ChartLegend {
    private CTLegend legend;

    public XSSFChartLegend(XSSFChart chart) {
        CTChart ctChart = chart.getCTChart();
        this.legend = ctChart.isSetLegend() ? ctChart.getLegend() : ctChart.addNewLegend();
        this.setDefaults();
    }

    private void setDefaults() {
        if (!this.legend.isSetOverlay()) {
            this.legend.addNewOverlay();
        }
        this.legend.getOverlay().setVal(false);
    }

    @Internal
    public CTLegend getCTLegend() {
        return this.legend;
    }

    @Override
    public void setPosition(LegendPosition position) {
        if (!this.legend.isSetLegendPos()) {
            this.legend.addNewLegendPos();
        }
        this.legend.getLegendPos().setVal(this.fromLegendPosition(position));
    }

    @Override
    public LegendPosition getPosition() {
        if (this.legend.isSetLegendPos()) {
            return this.toLegendPosition(this.legend.getLegendPos());
        }
        return LegendPosition.RIGHT;
    }

    @Override
    public XSSFManualLayout getManualLayout() {
        if (!this.legend.isSetLayout()) {
            this.legend.addNewLayout();
        }
        return new XSSFManualLayout(this.legend.getLayout());
    }

    @Override
    public boolean isOverlay() {
        return this.legend.getOverlay().getVal();
    }

    @Override
    public void setOverlay(boolean value) {
        this.legend.getOverlay().setVal(value);
    }

    private STLegendPos.Enum fromLegendPosition(LegendPosition position) {
        switch (position) {
            case BOTTOM: {
                return STLegendPos.B;
            }
            case LEFT: {
                return STLegendPos.L;
            }
            case RIGHT: {
                return STLegendPos.R;
            }
            case TOP: {
                return STLegendPos.T;
            }
            case TOP_RIGHT: {
                return STLegendPos.TR;
            }
        }
        throw new IllegalArgumentException();
    }

    private LegendPosition toLegendPosition(CTLegendPos ctLegendPos) {
        switch (ctLegendPos.getVal().intValue()) {
            case 1: {
                return LegendPosition.BOTTOM;
            }
            case 3: {
                return LegendPosition.LEFT;
            }
            case 4: {
                return LegendPosition.RIGHT;
            }
            case 5: {
                return LegendPosition.TOP;
            }
            case 2: {
                return LegendPosition.TOP_RIGHT;
            }
        }
        throw new IllegalArgumentException();
    }
}

