/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;

public interface ColorScaleFormatting {
    public int getNumControlPoints();

    public void setNumControlPoints(int var1);

    public Color[] getColors();

    public void setColors(Color[] var1);

    public ConditionalFormattingThreshold[] getThresholds();

    public void setThresholds(ConditionalFormattingThreshold[] var1);

    public ConditionalFormattingThreshold createThreshold();
}

