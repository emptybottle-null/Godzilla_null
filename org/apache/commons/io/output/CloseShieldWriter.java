/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.Writer;
import org.apache.commons.io.output.ClosedWriter;
import org.apache.commons.io.output.ProxyWriter;

public class CloseShieldWriter
extends ProxyWriter {
    public static CloseShieldWriter wrap(Writer writer) {
        return new CloseShieldWriter(writer);
    }

    @Deprecated
    public CloseShieldWriter(Writer writer) {
        super(writer);
    }

    @Override
    public void close() {
        this.out = ClosedWriter.CLOSED_WRITER;
    }
}

