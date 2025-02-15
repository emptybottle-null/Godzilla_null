/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.InputStream;

public class ClosedInputStream
extends InputStream {
    public static final ClosedInputStream CLOSED_INPUT_STREAM = new ClosedInputStream();

    @Override
    public int read() {
        return -1;
    }
}

