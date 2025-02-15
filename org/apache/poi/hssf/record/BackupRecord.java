/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class BackupRecord
extends StandardRecord {
    public static final short sid = 64;
    private short field_1_backup;

    public BackupRecord() {
    }

    public BackupRecord(BackupRecord other) {
        super(other);
        this.field_1_backup = other.field_1_backup;
    }

    public BackupRecord(RecordInputStream in) {
        this.field_1_backup = in.readShort();
    }

    public void setBackup(short backup) {
        this.field_1_backup = backup;
    }

    public short getBackup() {
        return this.field_1_backup;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[BACKUP]\n");
        buffer.append("    .backup          = ").append(Integer.toHexString(this.getBackup())).append("\n");
        buffer.append("[/BACKUP]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getBackup());
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 64;
    }

    @Override
    public BackupRecord copy() {
        return new BackupRecord(this);
    }
}

