/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class PrintHeadersRecord
extends StandardRecord {
    public static final short sid = 42;
    private short field_1_print_headers;

    public PrintHeadersRecord() {
    }

    public PrintHeadersRecord(PrintHeadersRecord other) {
        super(other);
        this.field_1_print_headers = other.field_1_print_headers;
    }

    public PrintHeadersRecord(RecordInputStream in) {
        this.field_1_print_headers = in.readShort();
    }

    public void setPrintHeaders(boolean p) {
        this.field_1_print_headers = p ? (short)1 : 0;
    }

    public boolean getPrintHeaders() {
        return this.field_1_print_headers == 1;
    }

    @Override
    public String toString() {
        return "[PRINTHEADERS]\n    .printheaders   = " + this.getPrintHeaders() + "\n[/PRINTHEADERS]\n";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_print_headers);
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 42;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public PrintHeadersRecord clone() {
        return this.copy();
    }

    @Override
    public PrintHeadersRecord copy() {
        return new PrintHeadersRecord(this);
    }
}

