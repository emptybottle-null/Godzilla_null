/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;

public interface DataBarFormatting {
    public boolean isLeftToRight();

    public void setLeftToRight(boolean var1);

    public boolean isIconOnly();

    public void setIconOnly(boolean var1);

    public int getWidthMin();

    public void setWidthMin(int var1);

    public int getWidthMax();

    public void setWidthMax(int var1);

    public Color getColor();

    public void setColor(Color var1);

    public ConditionalFormattingThreshold getMinThreshold();

    public ConditionalFormattingThreshold getMaxThreshold();
}

