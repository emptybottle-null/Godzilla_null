/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class ScenarioProtectRecord
extends StandardRecord {
    public static final short sid = 221;
    private short field_1_protect;

    public ScenarioProtectRecord() {
    }

    public ScenarioProtectRecord(ScenarioProtectRecord other) {
        super(other);
        this.field_1_protect = other.field_1_protect;
    }

    public ScenarioProtectRecord(RecordInputStream in) {
        this.field_1_protect = in.readShort();
    }

    public void setProtect(boolean protect) {
        this.field_1_protect = protect ? (short)1 : 0;
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
        return 221;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ScenarioProtectRecord clone() {
        return this.copy();
    }

    @Override
    public ScenarioProtectRecord copy() {
        return new ScenarioProtectRecord(this);
    }
}

