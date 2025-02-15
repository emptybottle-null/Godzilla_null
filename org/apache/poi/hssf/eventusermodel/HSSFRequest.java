/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.eventusermodel.AbortableHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFUserException;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;

public class HSSFRequest {
    private final Map<Short, List<HSSFListener>> _records = new HashMap<Short, List<HSSFListener>>(50);

    public void addListener(HSSFListener lsnr, short sid) {
        List list = this._records.computeIfAbsent(sid, k -> new ArrayList(1));
        list.add(lsnr);
    }

    public void addListenerForAllRecords(HSSFListener lsnr) {
        short[] rectypes;
        for (short rectype : rectypes = RecordFactory.getAllKnownRecordSIDs()) {
            this.addListener(lsnr, rectype);
        }
    }

    protected short processRecord(Record rec) throws HSSFUserException {
        List<HSSFListener> listeners = this._records.get(rec.getSid());
        short userCode = 0;
        if (listeners != null) {
            for (int k = 0; k < listeners.size(); ++k) {
                HSSFListener listener;
                HSSFListener listenObj = listeners.get(k);
                if (listenObj instanceof AbortableHSSFListener) {
                    listener = (AbortableHSSFListener)listenObj;
                    userCode = ((AbortableHSSFListener)listener).abortableProcessRecord(rec);
                    if (userCode == 0) continue;
                    break;
                }
                listener = listenObj;
                listener.processRecord(rec);
            }
        }
        return userCode;
    }
}

