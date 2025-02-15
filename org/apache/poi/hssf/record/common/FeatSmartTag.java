/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.common;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.common.SharedFeature;
import org.apache.poi.util.LittleEndianOutput;

public final class FeatSmartTag
implements SharedFeature {
    private byte[] data;

    public FeatSmartTag() {
        this.data = new byte[0];
    }

    public FeatSmartTag(FeatSmartTag other) {
        this.data = other.data == null ? null : (byte[])other.data.clone();
    }

    public FeatSmartTag(RecordInputStream in) {
        this.data = in.readRemainder();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(" [FEATURE SMART TAGS]\n");
        buffer.append(" [/FEATURE SMART TAGS]\n");
        return buffer.toString();
    }

    @Override
    public int getDataSize() {
        return this.data.length;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.write(this.data);
    }

    @Override
    public FeatSmartTag copy() {
        return new FeatSmartTag(this);
    }
}

