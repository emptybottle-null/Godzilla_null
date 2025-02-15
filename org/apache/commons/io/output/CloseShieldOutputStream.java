/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.OutputStream;
import org.apache.commons.io.output.ClosedOutputStream;
import org.apache.commons.io.output.ProxyOutputStream;

public class CloseShieldOutputStream
extends ProxyOutputStream {
    public static CloseShieldOutputStream wrap(OutputStream outputStream) {
        return new CloseShieldOutputStream(outputStream);
    }

    @Deprecated
    public CloseShieldOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void close() {
        this.out = ClosedOutputStream.CLOSED_OUTPUT_STREAM;
    }
}

