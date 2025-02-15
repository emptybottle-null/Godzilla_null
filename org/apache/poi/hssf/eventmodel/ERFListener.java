/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventmodel;

import org.apache.poi.hssf.record.Record;

public interface ERFListener {
    public boolean processRecord(Record var1);
}

