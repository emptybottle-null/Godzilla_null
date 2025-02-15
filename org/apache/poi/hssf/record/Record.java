/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.ByteArrayInputStream;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RecordInputStream;

public abstract class Record
extends RecordBase
implements Duplicatable {
    protected Record() {
    }

    protected Record(Record other) {
    }

    public final byte[] serialize() {
        byte[] retval = new byte[this.getRecordSize()];
        this.serialize(0, retval);
        return retval;
    }

    public String toString() {
        return super.toString();
    }

    public abstract short getSid();

    public Record cloneViaReserialise() {
        byte[] b = this.serialize();
        RecordInputStream rinp = new RecordInputStream(new ByteArrayInputStream(b));
        rinp.nextRecord();
        Record[] r = RecordFactory.createRecord(rinp);
        if (r.length != 1) {
            throw new IllegalStateException("Re-serialised a record to clone it, but got " + r.length + " records back!");
        }
        return r[0];
    }

    @Override
    public abstract Record copy();
}

