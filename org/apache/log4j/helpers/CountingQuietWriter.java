/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.io.IOException;
import java.io.Writer;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;

public class CountingQuietWriter
extends QuietWriter {
    protected long count;

    public CountingQuietWriter(Writer writer, ErrorHandler eh) {
        super(writer, eh);
    }

    public void write(String string) {
        try {
            this.out.write(string);
            this.count += (long)string.length();
        } catch (IOException e) {
            this.errorHandler.error("Write failure.", e, 1);
        }
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

