/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.HeaderFooterBase;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.Removal;

public final class FooterRecord
extends HeaderFooterBase {
    public static final short sid = 21;

    public FooterRecord(String text) {
        super(text);
    }

    public FooterRecord(FooterRecord other) {
        super(other);
    }

    public FooterRecord(RecordInputStream in) {
        super(in);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[FOOTER]\n");
        buffer.append("    .footer = ").append(this.getText()).append("\n");
        buffer.append("[/FOOTER]\n");
        return buffer.toString();
    }

    @Override
    public short getSid() {
        return 21;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public FooterRecord clone() {
        return this.copy();
    }

    @Override
    public FooterRecord copy() {
        return new FooterRecord(this);
    }
}

