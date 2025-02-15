/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class IterationRecord
extends StandardRecord {
    public static final short sid = 17;
    private static final BitField iterationOn = BitFieldFactory.getInstance(1);
    private int _flags;

    public IterationRecord(IterationRecord other) {
        super(other);
        this._flags = other._flags;
    }

    public IterationRecord(boolean iterateOn) {
        this._flags = iterationOn.setBoolean(0, iterateOn);
    }

    public IterationRecord(RecordInputStream in) {
        this._flags = in.readShort();
    }

    public void setIteration(boolean iterate) {
        this._flags = iterationOn.setBoolean(this._flags, iterate);
    }

    public boolean getIteration() {
        return iterationOn.isSet(this._flags);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[ITERATION]\n");
        buffer.append("    .flags      = ").append(HexDump.shortToHex(this._flags)).append("\n");
        buffer.append("[/ITERATION]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._flags);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 17;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public IterationRecord clone() {
        return this.copy();
    }

    @Override
    public IterationRecord copy() {
        return new IterationRecord(this);
    }
}

