/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.cont.ContinuableRecord;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;

public final class StringRecord
extends ContinuableRecord {
    public static final short sid = 519;
    private boolean _is16bitUnicode;
    private String _text;

    public StringRecord() {
    }

    public StringRecord(StringRecord other) {
        this._is16bitUnicode = other._is16bitUnicode;
        this._text = other._text;
    }

    public StringRecord(RecordInputStream in) {
        int field_1_string_length = in.readUShort();
        this._is16bitUnicode = in.readByte() != 0;
        this._text = this._is16bitUnicode ? in.readUnicodeLEString(field_1_string_length) : in.readCompressedUnicode(field_1_string_length);
    }

    @Override
    protected void serialize(ContinuableRecordOutput out) {
        out.writeShort(this._text.length());
        out.writeStringData(this._text);
    }

    @Override
    public short getSid() {
        return 519;
    }

    public String getString() {
        return this._text;
    }

    public void setString(String string) {
        this._text = string;
        this._is16bitUnicode = StringUtil.hasMultibyte(string);
    }

    @Override
    public String toString() {
        return "[STRING]\n    .string            = " + this._text + "\n[/STRING]\n";
    }

    @Deprecated
    @Removal(version="5.0.0")
    public StringRecord clone() {
        return this.copy();
    }

    @Override
    public StringRecord copy() {
        return new StringRecord(this);
    }
}

