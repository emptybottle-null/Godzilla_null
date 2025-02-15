/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisOrientation;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.AxisTickMark;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartAxis {
    public long getId();

    public AxisPosition getPosition();

    public void setPosition(AxisPosition var1);

    public String getNumberFormat();

    public void setNumberFormat(String var1);

    public boolean isSetLogBase();

    public void setLogBase(double var1);

    public double getLogBase();

    public boolean isSetMinimum();

    public double getMinimum();

    public void setMinimum(double var1);

    public boolean isSetMaximum();

    public double getMaximum();

    public void setMaximum(double var1);

    public AxisOrientation getOrientation();

    public void setOrientation(AxisOrientation var1);

    public void setCrosses(AxisCrosses var1);

    public AxisCrosses getCrosses();

    public void crossAxis(ChartAxis var1);

    public boolean isVisible();

    public void setVisible(boolean var1);

    public AxisTickMark getMajorTickMark();

    public void setMajorTickMark(AxisTickMark var1);

    public AxisTickMark getMinorTickMark();

    public void setMinorTickMark(AxisTickMark var1);

    public boolean hasNumberFormat();
}

