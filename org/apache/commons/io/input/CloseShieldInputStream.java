/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.InputStream;
import org.apache.commons.io.input.ClosedInputStream;
import org.apache.commons.io.input.ProxyInputStream;

public class CloseShieldInputStream
extends ProxyInputStream {
    public static CloseShieldInputStream wrap(InputStream inputStream) {
        return new CloseShieldInputStream(inputStream);
    }

    @Deprecated
    public CloseShieldInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public void close() {
        this.in = ClosedInputStream.CLOSED_INPUT_STREAM;
    }
}

