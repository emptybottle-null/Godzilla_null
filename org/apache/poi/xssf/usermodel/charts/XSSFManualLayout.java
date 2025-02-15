/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.LayoutMode;
import org.apache.poi.ss.usermodel.charts.LayoutTarget;
import org.apache.poi.ss.usermodel.charts.ManualLayout;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutMode;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutTarget;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutMode;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

@Deprecated
@Removal(version="4.2")
public final class XSSFManualLayout
implements ManualLayout {
    private CTManualLayout layout;
    private static final LayoutMode defaultLayoutMode = LayoutMode.EDGE;
    private static final LayoutTarget defaultLayoutTarget = LayoutTarget.INNER;

    public XSSFManualLayout(CTLayout ctLayout) {
        this.initLayout(ctLayout);
    }

    public XSSFManualLayout(XSSFChart chart) {
        CTPlotArea ctPlotArea = chart.getCTChart().getPlotArea();
        CTLayout ctLayout = ctPlotArea.isSetLayout() ? ctPlotArea.getLayout() : ctPlotArea.addNewLayout();
        this.initLayout(ctLayout);
    }

    @Internal
    public CTManualLayout getCTManualLayout() {
        return this.layout;
    }

    @Override
    public void setWidthRatio(double ratio) {
        if (!this.layout.isSetW()) {
            this.layout.addNewW();
        }
        this.layout.getW().setVal(ratio);
    }

    @Override
    public double getWidthRatio() {
        if (!this.layout.isSetW()) {
            return 0.0;
        }
        return this.layout.getW().getVal();
    }

    @Override
    public void setHeightRatio(double ratio) {
        if (!this.layout.isSetH()) {
            this.layout.addNewH();
        }
        this.layout.getH().setVal(ratio);
    }

    @Override
    public double getHeightRatio() {
        if (!this.layout.isSetH()) {
            return 0.0;
        }
        return this.layout.getH().getVal();
    }

    @Override
    public LayoutTarget getTarget() {
        if (!this.layout.isSetLayoutTarget()) {
            return defaultLayoutTarget;
        }
        return this.toLayoutTarget(this.layout.getLayoutTarget());
    }

    @Override
    public void setTarget(LayoutTarget target) {
        if (!this.layout.isSetLayoutTarget()) {
            this.layout.addNewLayoutTarget();
        }
        this.layout.getLayoutTarget().setVal(this.fromLayoutTarget(target));
    }

    @Override
    public LayoutMode getXMode() {
        if (!this.layout.isSetXMode()) {
            return defaultLayoutMode;
        }
        return this.toLayoutMode(this.layout.getXMode());
    }

    @Override
    public void setXMode(LayoutMode mode) {
        if (!this.layout.isSetXMode()) {
            this.layout.addNewXMode();
        }
        this.layout.getXMode().setVal(this.fromLayoutMode(mode));
    }

    @Override
    public LayoutMode getYMode() {
        if (!this.layout.isSetYMode()) {
            return defaultLayoutMode;
        }
        return this.toLayoutMode(this.layout.getYMode());
    }

    @Override
    public void setYMode(LayoutMode mode) {
        if (!this.layout.isSetYMode()) {
            this.layout.addNewYMode();
        }
        this.layout.getYMode().setVal(this.fromLayoutMode(mode));
    }

    @Override
    public double getX() {
        if (!this.layout.isSetX()) {
            return 0.0;
        }
        return this.layout.getX().getVal();
    }

    @Override
    public void setX(double x) {
        if (!this.layout.isSetX()) {
            this.layout.addNewX();
        }
        this.layout.getX().setVal(x);
    }

    @Override
    public double getY() {
        if (!this.layout.isSetY()) {
            return 0.0;
        }
        return this.layout.getY().getVal();
    }

    @Override
    public void setY(double y) {
        if (!this.layout.isSetY()) {
            this.layout.addNewY();
        }
        this.layout.getY().setVal(y);
    }

    @Override
    public LayoutMode getWidthMode() {
        if (!this.layout.isSetWMode()) {
            return defaultLayoutMode;
        }
        return this.toLayoutMode(this.layout.getWMode());
    }

    @Override
    public void setWidthMode(LayoutMode mode) {
        if (!this.layout.isSetWMode()) {
            this.layout.addNewWMode();
        }
        this.layout.getWMode().setVal(this.fromLayoutMode(mode));
    }

    @Override
    public LayoutMode getHeightMode() {
        if (!this.layout.isSetHMode()) {
            return defaultLayoutMode;
        }
        return this.toLayoutMode(this.layout.getHMode());
    }

    @Override
    public void setHeightMode(LayoutMode mode) {
        if (!this.layout.isSetHMode()) {
            this.layout.addNewHMode();
        }
        this.layout.getHMode().setVal(this.fromLayoutMode(mode));
    }

    private void initLayout(CTLayout ctLayout) {
        this.layout = ctLayout.isSetManualLayout() ? ctLayout.getManualLayout() : ctLayout.addNewManualLayout();
    }

    private STLayoutMode.Enum fromLayoutMode(LayoutMode mode) {
        switch (mode) {
            case EDGE: {
                return STLayoutMode.EDGE;
            }
            case FACTOR: {
                return STLayoutMode.FACTOR;
            }
        }
        throw new IllegalArgumentException();
    }

    private LayoutMode toLayoutMode(CTLayoutMode ctLayoutMode) {
        switch (ctLayoutMode.getVal().intValue()) {
            case 1: {
                return LayoutMode.EDGE;
            }
            case 2: {
                return LayoutMode.FACTOR;
            }
        }
        throw new IllegalArgumentException();
    }

    private STLayoutTarget.Enum fromLayoutTarget(LayoutTarget target) {
        switch (target) {
            case INNER: {
                return STLayoutTarget.INNER;
            }
            case OUTER: {
                return STLayoutTarget.OUTER;
            }
        }
        throw new IllegalArgumentException();
    }

    private LayoutTarget toLayoutTarget(CTLayoutTarget ctLayoutTarget) {
        switch (ctLayoutTarget.getVal().intValue()) {
            case 1: {
                return LayoutTarget.INNER;
            }
            case 2: {
                return LayoutTarget.OUTER;
            }
        }
        throw new IllegalArgumentException();
    }
}

