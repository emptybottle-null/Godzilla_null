/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class DemuxInputStream
extends InputStream {
    private final InheritableThreadLocal<InputStream> inputStreamLocal = new InheritableThreadLocal();

    public InputStream bindStream(InputStream input) {
        InputStream oldValue = (InputStream)this.inputStreamLocal.get();
        this.inputStreamLocal.set(input);
        return oldValue;
    }

    @Override
    public void close() throws IOException {
        IOUtils.close((Closeable)this.inputStreamLocal.get());
    }

    @Override
    public int read() throws IOException {
        InputStream inputStream = (InputStream)this.inputStreamLocal.get();
        if (null != inputStream) {
            return inputStream.read();
        }
        return -1;
    }
}

