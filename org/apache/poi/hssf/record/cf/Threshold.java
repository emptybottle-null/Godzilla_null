/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import java.util.Arrays;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public abstract class Threshold {
    private byte type;
    private Formula formula;
    private Double value;

    protected Threshold() {
        this.type = (byte)ConditionalFormattingThreshold.RangeType.NUMBER.id;
        this.formula = Formula.create(null);
        this.value = 0.0;
    }

    protected Threshold(Threshold other) {
        this.type = other.type;
        this.formula = other.formula.copy();
        this.value = other.value;
    }

    protected Threshold(LittleEndianInput in) {
        this.type = in.readByte();
        short formulaLen = in.readShort();
        this.formula = formulaLen > 0 ? Formula.read(formulaLen, in) : Formula.create(null);
        if (formulaLen == 0 && this.type != ConditionalFormattingThreshold.RangeType.MIN.id && this.type != ConditionalFormattingThreshold.RangeType.MAX.id) {
            this.value = in.readDouble();
        }
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
        if (type == ConditionalFormattingThreshold.RangeType.MIN.id || type == ConditionalFormattingThreshold.RangeType.MAX.id || type == ConditionalFormattingThreshold.RangeType.FORMULA.id) {
            this.value = null;
        } else if (this.value == null) {
            this.value = 0.0;
        }
    }

    public void setType(int type) {
        this.type = (byte)type;
    }

    protected Formula getFormula() {
        return this.formula;
    }

    public Ptg[] getParsedExpression() {
        return this.formula.getTokens();
    }

    public void setParsedExpression(Ptg[] ptgs) {
        this.formula = Formula.create(ptgs);
        if (ptgs.length > 0) {
            this.value = null;
        }
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getDataLength() {
        int len = 1 + this.formula.getEncodedSize();
        if (this.value != null) {
            len += 8;
        }
        return len;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("    [CF Threshold]\n");
        buffer.append("          .type    = ").append(Integer.toHexString(this.type)).append("\n");
        buffer.append("          .formula = ").append(Arrays.toString(this.formula.getTokens())).append("\n");
        buffer.append("          .value   = ").append(this.value).append("\n");
        buffer.append("    [/CF Threshold]\n");
        return buffer.toString();
    }

    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.type);
        if (this.formula.getTokens().length == 0) {
            out.writeShort(0);
        } else {
            this.formula.serialize(out);
        }
        if (this.value != null) {
            out.writeDouble(this.value);
        }
    }

    public abstract Threshold copy();
}

