/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public final class TabIdRecord
extends StandardRecord {
    public static final short sid = 317;
    private static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public short[] _tabids;

    public TabIdRecord() {
        this._tabids = EMPTY_SHORT_ARRAY;
    }

    public TabIdRecord(TabIdRecord other) {
        super(other);
        this._tabids = other._tabids == null ? null : (short[])other._tabids.clone();
    }

    public TabIdRecord(RecordInputStream in) {
        int nTabs = in.remaining() / 2;
        this._tabids = new short[nTabs];
        for (int i = 0; i < this._tabids.length; ++i) {
            this._tabids[i] = in.readShort();
        }
    }

    public void setTabIdArray(short[] array) {
        this._tabids = (short[])array.clone();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[TABID]\n");
        buffer.append("    .elements        = ").append(this._tabids.length).append("\n");
        for (int i = 0; i < this._tabids.length; ++i) {
            buffer.append("    .element_").append(i).append(" = ").append(this._tabids[i]).append("\n");
        }
        buffer.append("[/TABID]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        for (short tabid : this._tabids) {
            out.writeShort(tabid);
        }
    }

    @Override
    protected int getDataSize() {
        return this._tabids.length * 2;
    }

    @Override
    public short getSid() {
        return 317;
    }

    @Override
    public TabIdRecord copy() {
        return new TabIdRecord(this);
    }
}

