/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSerializationListener;

public class NullEscherSerializationListener
implements EscherSerializationListener {
    @Override
    public void beforeRecordSerialize(int offset, short recordId, EscherRecord record) {
    }

    @Override
    public void afterRecordSerialize(int offset, short recordId, int size, EscherRecord record) {
    }
}

