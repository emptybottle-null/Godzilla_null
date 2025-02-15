/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.HeaderFooterBase;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.Removal;

public final class HeaderRecord
extends HeaderFooterBase {
    public static final short sid = 20;

    public HeaderRecord(String text) {
        super(text);
    }

    public HeaderRecord(HeaderRecord other) {
        super(other);
    }

    public HeaderRecord(RecordInputStream in) {
        super(in);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[HEADER]\n");
        buffer.append("    .header = ").append(this.getText()).append("\n");
        buffer.append("[/HEADER]\n");
        return buffer.toString();
    }

    @Override
    public short getSid() {
        return 20;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public HeaderRecord clone() {
        return this.copy();
    }

    @Override
    public HeaderRecord copy() {
        return new HeaderRecord(this);
    }
}

