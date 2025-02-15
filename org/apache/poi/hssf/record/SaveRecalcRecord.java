/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class SaveRecalcRecord
extends StandardRecord {
    public static final short sid = 95;
    private short field_1_recalc;

    public SaveRecalcRecord() {
    }

    public SaveRecalcRecord(SaveRecalcRecord other) {
        super(other);
        this.field_1_recalc = other.field_1_recalc;
    }

    public SaveRecalcRecord(RecordInputStream in) {
        this.field_1_recalc = in.readShort();
    }

    public void setRecalc(boolean recalc) {
        this.field_1_recalc = (short)(recalc ? 1 : 0);
    }

    public boolean getRecalc() {
        return this.field_1_recalc == 1;
    }

    @Override
    public String toString() {
        return "[SAVERECALC]\n    .recalc         = " + this.getRecalc() + "\n[/SAVERECALC]\n";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_recalc);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 95;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public SaveRecalcRecord clone() {
        return this.copy();
    }

    @Override
    public SaveRecalcRecord copy() {
        return new SaveRecalcRecord(this);
    }
}

