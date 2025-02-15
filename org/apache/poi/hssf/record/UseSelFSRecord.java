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

public final class UseSelFSRecord
extends StandardRecord {
    public static final short sid = 352;
    private static final BitField useNaturalLanguageFormulasFlag = BitFieldFactory.getInstance(1);
    private int _options;

    private UseSelFSRecord(UseSelFSRecord other) {
        super(other);
        this._options = other._options;
    }

    private UseSelFSRecord(int options) {
        this._options = options;
    }

    public UseSelFSRecord(RecordInputStream in) {
        this(in.readUShort());
    }

    public UseSelFSRecord(boolean b) {
        this(0);
        this._options = useNaturalLanguageFormulasFlag.setBoolean(this._options, b);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[USESELFS]\n");
        buffer.append("    .options = ").append(HexDump.shortToHex(this._options)).append("\n");
        buffer.append("[/USESELFS]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this._options);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 352;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public UseSelFSRecord clone() {
        return this.copy();
    }

    @Override
    public UseSelFSRecord copy() {
        return new UseSelFSRecord(this);
    }
}

