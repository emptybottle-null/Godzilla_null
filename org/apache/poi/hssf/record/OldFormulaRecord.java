/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.FormulaSpecialCachedValue;
import org.apache.poi.hssf.record.OldCellRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;

public final class OldFormulaRecord
extends OldCellRecord {
    public static final short biff2_sid = 6;
    public static final short biff3_sid = 518;
    public static final short biff4_sid = 1030;
    public static final short biff5_sid = 6;
    private FormulaSpecialCachedValue specialCachedValue;
    private double field_4_value;
    private short field_5_options;
    private Formula field_6_parsed_expr;

    public OldFormulaRecord(RecordInputStream ris) {
        super(ris, ris.getSid() == 6);
        if (this.isBiff2()) {
            this.field_4_value = ris.readDouble();
        } else {
            long valueLongBits = ris.readLong();
            this.specialCachedValue = FormulaSpecialCachedValue.create(valueLongBits);
            if (this.specialCachedValue == null) {
                this.field_4_value = Double.longBitsToDouble(valueLongBits);
            }
        }
        this.field_5_options = this.isBiff2() ? (short)ris.readUByte() : ris.readShort();
        short expression_len = ris.readShort();
        int nBytesAvailable = ris.available();
        this.field_6_parsed_expr = Formula.read(expression_len, ris, nBytesAvailable);
    }

    public int getCachedResultType() {
        if (this.specialCachedValue == null) {
            return CellType.NUMERIC.getCode();
        }
        return this.specialCachedValue.getValueType();
    }

    public boolean getCachedBooleanValue() {
        return this.specialCachedValue.getBooleanValue();
    }

    public int getCachedErrorValue() {
        return this.specialCachedValue.getErrorValue();
    }

    public double getValue() {
        return this.field_4_value;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public Ptg[] getParsedExpression() {
        return this.field_6_parsed_expr.getTokens();
    }

    public Formula getFormula() {
        return this.field_6_parsed_expr;
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        sb.append("    .value       = ").append(this.getValue()).append("\n");
    }

    @Override
    protected String getRecordName() {
        return "Old Formula";
    }
}

