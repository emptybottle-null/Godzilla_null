/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;

public class XSSFConditionFilterData
implements ConditionFilterData {
    private final CTCfRule _cfRule;

    XSSFConditionFilterData(CTCfRule cfRule) {
        this._cfRule = cfRule;
    }

    @Override
    public boolean getAboveAverage() {
        return this._cfRule.getAboveAverage();
    }

    @Override
    public boolean getBottom() {
        return this._cfRule.getBottom();
    }

    @Override
    public boolean getEqualAverage() {
        return this._cfRule.getEqualAverage();
    }

    @Override
    public boolean getPercent() {
        return this._cfRule.getPercent();
    }

    @Override
    public long getRank() {
        return this._cfRule.getRank();
    }

    @Override
    public int getStdDev() {
        return this._cfRule.getStdDev();
    }
}

