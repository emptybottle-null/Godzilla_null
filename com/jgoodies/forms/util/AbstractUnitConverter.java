/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.forms.util;

import com.jgoodies.common.bean.Bean;
import com.jgoodies.forms.util.UnitConverter;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Toolkit;

public abstract class AbstractUnitConverter
extends Bean
implements UnitConverter {
    private static final int DTP_RESOLUTION = 72;
    private static int defaultScreenResolution = -1;

    @Override
    public int inchAsPixel(double in, Component component) {
        return AbstractUnitConverter.inchAsPixel(in, this.getScreenResolution(component));
    }

    @Override
    public int millimeterAsPixel(double mm, Component component) {
        return AbstractUnitConverter.millimeterAsPixel(mm, this.getScreenResolution(component));
    }

    @Override
    public int centimeterAsPixel(double cm, Component component) {
        return AbstractUnitConverter.centimeterAsPixel(cm, this.getScreenResolution(component));
    }

    @Override
    public int pointAsPixel(int pt, Component component) {
        return AbstractUnitConverter.pointAsPixel(pt, this.getScreenResolution(component));
    }

    @Override
    public int dialogUnitXAsPixel(int dluX, Component c) {
        return this.dialogUnitXAsPixel(dluX, this.getDialogBaseUnitsX(c));
    }

    @Override
    public int dialogUnitYAsPixel(int dluY, Component c) {
        return this.dialogUnitYAsPixel(dluY, this.getDialogBaseUnitsY(c));
    }

    protected abstract double getDialogBaseUnitsX(Component var1);

    protected abstract double getDialogBaseUnitsY(Component var1);

    protected static final int inchAsPixel(double in, int dpi) {
        return (int)Math.round((double)dpi * in);
    }

    protected static final int millimeterAsPixel(double mm, int dpi) {
        return (int)Math.round((double)dpi * mm * 10.0 / 254.0);
    }

    protected static final int centimeterAsPixel(double cm, int dpi) {
        return (int)Math.round((double)dpi * cm * 100.0 / 254.0);
    }

    protected static final int pointAsPixel(double pt, int dpi) {
        return (int)Math.round((double)dpi * pt / 72.0);
    }

    protected int dialogUnitXAsPixel(int dluX, double dialogBaseUnitsX) {
        return (int)Math.round((double)dluX * dialogBaseUnitsX / 4.0);
    }

    protected int dialogUnitYAsPixel(int dluY, double dialogBaseUnitsY) {
        return (int)Math.round((double)dluY * dialogBaseUnitsY / 8.0);
    }

    protected double computeAverageCharWidth(FontMetrics metrics, String testString) {
        int width = metrics.stringWidth(testString);
        double average = (double)width / (double)testString.length();
        return average;
    }

    protected int getScreenResolution(Component c) {
        if (c == null) {
            return this.getDefaultScreenResolution();
        }
        Toolkit toolkit = c.getToolkit();
        return toolkit != null ? toolkit.getScreenResolution() : this.getDefaultScreenResolution();
    }

    protected int getDefaultScreenResolution() {
        if (defaultScreenResolution == -1) {
            defaultScreenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        }
        return defaultScreenResolution;
    }
}

