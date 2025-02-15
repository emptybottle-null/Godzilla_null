/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class NullOutputStream
extends OutputStream {
    public static final NullOutputStream NULL_OUTPUT_STREAM = new NullOutputStream();

    @Deprecated
    public NullOutputStream() {
    }

    @Override
    public void write(byte[] b, int off, int len) {
    }

    @Override
    public void write(int b) {
    }

    @Override
    public void write(byte[] b) throws IOException {
    }
}

