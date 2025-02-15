/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfvoType;

public class XSSFConditionalFormattingThreshold
implements ConditionalFormattingThreshold {
    private CTCfvo cfvo;

    protected XSSFConditionalFormattingThreshold(CTCfvo cfvo) {
        this.cfvo = cfvo;
    }

    protected CTCfvo getCTCfvo() {
        return this.cfvo;
    }

    @Override
    public ConditionalFormattingThreshold.RangeType getRangeType() {
        return ConditionalFormattingThreshold.RangeType.byName(this.cfvo.getType().toString());
    }

    @Override
    public void setRangeType(ConditionalFormattingThreshold.RangeType type) {
        STCfvoType.Enum xtype = STCfvoType.Enum.forString(type.name);
        this.cfvo.setType(xtype);
    }

    @Override
    public String getFormula() {
        if (this.cfvo.getType() == STCfvoType.FORMULA) {
            return this.cfvo.getVal();
        }
        return null;
    }

    @Override
    public void setFormula(String formula) {
        this.cfvo.setVal(formula);
    }

    @Override
    public Double getValue() {
        if (this.cfvo.getType() == STCfvoType.FORMULA || this.cfvo.getType() == STCfvoType.MIN || this.cfvo.getType() == STCfvoType.MAX) {
            return null;
        }
        if (this.cfvo.isSetVal()) {
            return Double.parseDouble(this.cfvo.getVal());
        }
        return null;
    }

    @Override
    public void setValue(Double value) {
        if (value == null) {
            this.cfvo.unsetVal();
        } else {
            this.cfvo.setVal(value.toString());
        }
    }
}

