/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class PasswordRecord
extends StandardRecord {
    public static final short sid = 19;
    private int field_1_password;

    public PasswordRecord(int password) {
        this.field_1_password = password;
    }

    public PasswordRecord(PasswordRecord other) {
        super(other);
        this.field_1_password = other.field_1_password;
    }

    public PasswordRecord(RecordInputStream in) {
        this.field_1_password = in.readShort();
    }

    public void setPassword(int password) {
        this.field_1_password = password;
    }

    public int getPassword() {
        return this.field_1_password;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[PASSWORD]\n");
        buffer.append("    .password = ").append(HexDump.shortToHex(this.field_1_password)).append("\n");
        buffer.append("[/PASSWORD]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_password);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 19;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public PasswordRecord clone() {
        return this.copy();
    }

    @Override
    public PasswordRecord copy() {
        return new PasswordRecord(this);
    }
}

