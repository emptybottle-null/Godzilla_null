/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.ScalarConstantPtg;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class StringPtg
extends ScalarConstantPtg {
    public static final byte sid = 23;
    private static final char FORMULA_DELIMITER = '\"';
    private final boolean _is16bitUnicode;
    private final String field_3_string;

    public StringPtg(LittleEndianInput in) {
        int nChars = in.readUByte();
        this._is16bitUnicode = (in.readByte() & 1) != 0;
        this.field_3_string = this._is16bitUnicode ? StringUtil.readUnicodeLE(in, nChars) : StringUtil.readCompressedUnicode(in, nChars);
    }

    public StringPtg(String value) {
        if (value.length() > 255) {
            throw new IllegalArgumentException("String literals in formulas can't be bigger than 255 characters ASCII");
        }
        this._is16bitUnicode = StringUtil.hasMultibyte(value);
        this.field_3_string = value;
    }

    public String getValue() {
        return this.field_3_string;
    }

    @Override
    public void write(LittleEndianOutput out) {
        out.writeByte(23 + this.getPtgClass());
        out.writeByte(this.field_3_string.length());
        out.writeByte(this._is16bitUnicode ? 1 : 0);
        if (this._is16bitUnicode) {
            StringUtil.putUnicodeLE(this.field_3_string, out);
        } else {
            StringUtil.putCompressedUnicode(this.field_3_string, out);
        }
    }

    @Override
    public int getSize() {
        return 3 + this.field_3_string.length() * (this._is16bitUnicode ? 2 : 1);
    }

    @Override
    public String toFormulaString() {
        String value = this.field_3_string;
        int len = value.length();
        StringBuilder sb = new StringBuilder(len + 4);
        sb.append('\"');
        for (int i = 0; i < len; ++i) {
            char c = value.charAt(i);
            if (c == '\"') {
                sb.append('\"');
            }
            sb.append(c);
        }
        sb.append('\"');
        return sb.toString();
    }

    @Override
    public StringPtg copy() {
        return this;
    }
}

