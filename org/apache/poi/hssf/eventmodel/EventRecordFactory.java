/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventmodel;

import java.io.InputStream;
import java.util.Arrays;
import org.apache.poi.hssf.eventmodel.ERFListener;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.RecordFormatException;

public final class EventRecordFactory {
    private final ERFListener _listener;
    private final short[] _sids;

    public EventRecordFactory(ERFListener listener, short[] sids) {
        this._listener = listener;
        if (sids == null) {
            this._sids = null;
        } else {
            this._sids = (short[])sids.clone();
            Arrays.sort(this._sids);
        }
    }

    private boolean isSidIncluded(short sid) {
        if (this._sids == null) {
            return true;
        }
        return Arrays.binarySearch(this._sids, sid) >= 0;
    }

    private boolean processRecord(Record record) {
        if (!this.isSidIncluded(record.getSid())) {
            return true;
        }
        return this._listener.processRecord(record);
    }

    public void processRecords(InputStream in) throws RecordFormatException {
        Record last_record = null;
        RecordInputStream recStream = new RecordInputStream(in);
        while (recStream.hasNextRecord()) {
            recStream.nextRecord();
            Record[] recs = RecordFactory.createRecord(recStream);
            if (recs.length > 1) {
                for (Record rec : recs) {
                    if (last_record != null && !this.processRecord(last_record)) {
                        return;
                    }
                    last_record = rec;
                }
                continue;
            }
            Record record = recs[0];
            if (record == null) continue;
            if (last_record != null && !this.processRecord(last_record)) {
                return;
            }
            last_record = record;
        }
        if (last_record != null) {
            this.processRecord(last_record);
        }
    }
}

