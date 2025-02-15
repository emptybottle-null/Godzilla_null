/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel;

import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFUserException;
import org.apache.poi.hssf.record.Record;

public abstract class AbortableHSSFListener
implements HSSFListener {
    @Override
    public void processRecord(Record record) {
    }

    public abstract short abortableProcessRecord(Record var1) throws HSSFUserException;
}

