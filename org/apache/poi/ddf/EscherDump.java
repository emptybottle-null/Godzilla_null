/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.io.PrintStream;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherRecord;

public final class EscherDump {
    public void dump(byte[] data, int offset, int size, PrintStream out) {
        int bytesRead;
        DefaultEscherRecordFactory recordFactory = new DefaultEscherRecordFactory();
        for (int pos = offset; pos < offset + size; pos += bytesRead) {
            EscherRecord r = recordFactory.createRecord(data, pos);
            bytesRead = r.fillFields(data, pos, recordFactory);
            out.println(r);
        }
    }

    public void dump(int recordSize, byte[] data, PrintStream out) {
        this.dump(data, 0, recordSize, out);
    }
}

