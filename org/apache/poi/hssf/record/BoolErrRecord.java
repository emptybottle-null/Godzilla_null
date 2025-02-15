/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.CellRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;

public final class BoolErrRecord
extends CellRecord {
    public static final short sid = 517;
    private int _value;
    private boolean _isError;

    public BoolErrRecord() {
    }

    public BoolErrRecord(BoolErrRecord other) {
        super(other);
        this._value = other._value;
        this._isError = other._isError;
    }

    public BoolErrRecord(RecordInputStream in) {
        super(in);
        switch (in.remaining()) {
            case 2: {
                this._value = in.readByte();
                break;
            }
            case 3: {
                this._value = in.readUShort();
                break;
            }
            default: {
                throw new RecordFormatException("Unexpected size (" + in.remaining() + ") for BOOLERR record.");
            }
        }
        int flag = in.readUByte();
        switch (flag) {
            case 0: {
                this._isError = false;
                break;
            }
            case 1: {
                this._isError = true;
                break;
            }
            default: {
                throw new RecordFormatException("Unexpected isError flag (" + flag + ") for BOOLERR record.");
            }
        }
    }

    public void setValue(boolean value) {
        this._value = value ? 1 : 0;
        this._isError = false;
    }

    public void setValue(byte value) {
        this.setValue(FormulaError.forInt(value));
    }

    public void setValue(FormulaError value) {
        switch (value) {
            case NULL: 
            case DIV0: 
            case VALUE: 
            case REF: 
            case NAME: 
            case NUM: 
            case NA: {
                this._value = value.getCode();
                this._isError = true;
                return;
            }
        }
        throw new IllegalArgumentException("Error Value can only be 0,7,15,23,29,36 or 42. It cannot be " + value.getCode() + " (" + (Object)((Object)value) + ")");
    }

    public boolean getBooleanValue() {
        return this._value != 0;
    }

    public byte getErrorValue() {
        return (byte)this._value;
    }

    public boolean isBoolean() {
        return !this._isError;
    }

    public boolean isError() {
        return this._isError;
    }

    @Override
    protected String getRecordName() {
        return "BOOLERR";
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        if (this.isBoolean()) {
            sb.append("  .boolVal = ");
            sb.append(this.getBooleanValue());
        } else {
            sb.append("  .errCode = ");
            sb.append(FormulaError.forInt(this.getErrorValue()).getString());
            sb.append(" (").append(HexDump.byteToHex(this.getErrorValue())).append(")");
        }
    }

    @Override
    protected void serializeValue(LittleEndianOutput out) {
        out.writeByte(this._value);
        out.writeByte(this._isError ? 1 : 0);
    }

    @Override
    protected int getValueDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 517;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public BoolErrRecord clone() {
        return this.copy();
    }

    @Override
    public BoolErrRecord copy() {
        return new BoolErrRecord(this);
    }
}

