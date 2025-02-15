/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.CellRecord;
import org.apache.poi.hssf.record.FormulaSpecialCachedValue;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class FormulaRecord
extends CellRecord {
    public static final short sid = 6;
    private static final int FIXED_SIZE = 14;
    private static final BitField alwaysCalc = BitFieldFactory.getInstance(1);
    private static final BitField calcOnLoad = BitFieldFactory.getInstance(2);
    private static final BitField sharedFormula = BitFieldFactory.getInstance(8);
    private double field_4_value;
    private short field_5_options;
    private int field_6_zero;
    private Formula field_8_parsed_expr;
    private FormulaSpecialCachedValue specialCachedValue;

    public FormulaRecord() {
        this.field_8_parsed_expr = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    public FormulaRecord(FormulaRecord other) {
        super(other);
        this.field_4_value = other.field_4_value;
        this.field_5_options = other.field_5_options;
        this.field_6_zero = other.field_6_zero;
        this.field_8_parsed_expr = other.field_8_parsed_expr == null ? null : new Formula(other.field_8_parsed_expr);
        this.specialCachedValue = other.specialCachedValue == null ? null : new FormulaSpecialCachedValue(other.specialCachedValue);
    }

    public FormulaRecord(RecordInputStream ris) {
        super(ris);
        long valueLongBits = ris.readLong();
        this.field_5_options = ris.readShort();
        this.specialCachedValue = FormulaSpecialCachedValue.create(valueLongBits);
        if (this.specialCachedValue == null) {
            this.field_4_value = Double.longBitsToDouble(valueLongBits);
        }
        this.field_6_zero = ris.readInt();
        short field_7_expression_len = ris.readShort();
        int nBytesAvailable = ris.available();
        this.field_8_parsed_expr = Formula.read(field_7_expression_len, ris, nBytesAvailable);
    }

    public void setValue(double value) {
        this.field_4_value = value;
        this.specialCachedValue = null;
    }

    public void setCachedResultTypeEmptyString() {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedEmptyValue();
    }

    public void setCachedResultTypeString() {
        this.specialCachedValue = FormulaSpecialCachedValue.createForString();
    }

    public void setCachedResultErrorCode(int errorCode) {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedErrorCode(errorCode);
    }

    public void setCachedResultBoolean(boolean value) {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedBoolean(value);
    }

    public boolean hasCachedResultString() {
        return this.specialCachedValue != null && this.specialCachedValue.getTypeCode() == 0;
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

    public void setOptions(short options) {
        this.field_5_options = options;
    }

    public double getValue() {
        return this.field_4_value;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public boolean isSharedFormula() {
        return sharedFormula.isSet(this.field_5_options);
    }

    public void setSharedFormula(boolean flag) {
        this.field_5_options = sharedFormula.setShortBoolean(this.field_5_options, flag);
    }

    public boolean isAlwaysCalc() {
        return alwaysCalc.isSet(this.field_5_options);
    }

    public void setAlwaysCalc(boolean flag) {
        this.field_5_options = alwaysCalc.setShortBoolean(this.field_5_options, flag);
    }

    public boolean isCalcOnLoad() {
        return calcOnLoad.isSet(this.field_5_options);
    }

    public void setCalcOnLoad(boolean flag) {
        this.field_5_options = calcOnLoad.setShortBoolean(this.field_5_options, flag);
    }

    public Ptg[] getParsedExpression() {
        return this.field_8_parsed_expr.getTokens();
    }

    public Formula getFormula() {
        return this.field_8_parsed_expr;
    }

    public void setParsedExpression(Ptg[] ptgs) {
        this.field_8_parsed_expr = Formula.create(ptgs);
    }

    @Override
    public short getSid() {
        return 6;
    }

    @Override
    protected int getValueDataSize() {
        return 14 + this.field_8_parsed_expr.getEncodedSize();
    }

    @Override
    protected void serializeValue(LittleEndianOutput out) {
        if (this.specialCachedValue == null) {
            out.writeDouble(this.field_4_value);
        } else {
            this.specialCachedValue.serialize(out);
        }
        out.writeShort(this.getOptions());
        out.writeInt(this.field_6_zero);
        this.field_8_parsed_expr.serialize(out);
    }

    @Override
    protected String getRecordName() {
        return "FORMULA";
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        sb.append("  .value\t = ");
        if (this.specialCachedValue == null) {
            sb.append(this.field_4_value).append("\n");
        } else {
            sb.append(this.specialCachedValue.formatDebugString()).append("\n");
        }
        sb.append("  .options   = ").append(HexDump.shortToHex(this.getOptions())).append("\n");
        sb.append("    .alwaysCalc= ").append(this.isAlwaysCalc()).append("\n");
        sb.append("    .calcOnLoad= ").append(this.isCalcOnLoad()).append("\n");
        sb.append("    .shared    = ").append(this.isSharedFormula()).append("\n");
        sb.append("  .zero      = ").append(HexDump.intToHex(this.field_6_zero)).append("\n");
        Ptg[] ptgs = this.field_8_parsed_expr.getTokens();
        for (int k = 0; k < ptgs.length; ++k) {
            if (k > 0) {
                sb.append("\n");
            }
            sb.append("    Ptg[").append(k).append("]=");
            Ptg ptg = ptgs[k];
            sb.append(ptg).append(ptg.getRVAType());
        }
    }

    @Deprecated
    @Removal(version="5.0.0")
    public FormulaRecord clone() {
        return this.copy();
    }

    @Override
    public FormulaRecord copy() {
        return new FormulaRecord(this);
    }
}

