/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.Writer;
import org.apache.commons.logging.Log;
import org.springframework.util.Assert;

public class CommonsLogWriter
extends Writer {
    private final Log logger;
    private final StringBuilder buffer = new StringBuilder();

    public CommonsLogWriter(Log logger) {
        Assert.notNull((Object)logger, "Logger must not be null");
        this.logger = logger;
    }

    public void write(char ch) {
        if (ch == '\n' && this.buffer.length() > 0) {
            this.logger.debug(this.buffer.toString());
            this.buffer.setLength(0);
        } else {
            this.buffer.append(ch);
        }
    }

    @Override
    public void write(char[] buffer, int offset, int length) {
        for (int i = 0; i < length; ++i) {
            this.write(buffer[offset + i]);
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() {
    }
}

