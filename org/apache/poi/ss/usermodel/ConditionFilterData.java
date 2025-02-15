/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

public interface ConditionFilterData {
    public boolean getAboveAverage();

    public boolean getBottom();

    public boolean getEqualAverage();

    public boolean getPercent();

    public long getRank();

    public int getStdDev();
}

