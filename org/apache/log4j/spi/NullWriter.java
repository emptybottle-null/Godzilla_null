/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import java.io.Writer;

class NullWriter
extends Writer {
    NullWriter() {
    }

    public void close() {
    }

    public void flush() {
    }

    public void write(char[] cbuf, int off, int len) {
    }
}

