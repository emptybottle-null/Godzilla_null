/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class ObjectProtectRecord
extends StandardRecord {
    public static final short sid = 99;
    private short field_1_protect;

    public ObjectProtectRecord() {
    }

    public ObjectProtectRecord(ObjectProtectRecord other) {
        super(other);
        this.field_1_protect = other.field_1_protect;
    }

    public ObjectProtectRecord(RecordInputStream in) {
        this.field_1_protect = in.readShort();
    }

    public void setProtect(boolean protect) {
        this.field_1_protect = (short)(protect ? 1 : 0);
    }

    public boolean getProtect() {
        return this.field_1_protect == 1;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SCENARIOPROTECT]\n");
        buffer.append("    .protect         = ").append(this.getProtect()).append("\n");
        buffer.append("[/SCENARIOPROTECT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_protect);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 99;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ObjectProtectRecord clone() {
        return this.copy();
    }

    @Override
    public ObjectProtectRecord copy() {
        return new ObjectProtectRecord(this);
    }
}

