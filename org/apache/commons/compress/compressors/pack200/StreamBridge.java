/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

abstract class StreamBridge
extends FilterOutputStream {
    private InputStream input;
    private final Object inputLock = new Object();

    protected StreamBridge(OutputStream out) {
        super(out);
    }

    protected StreamBridge() {
        this(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    InputStream getInput() throws IOException {
        Object object = this.inputLock;
        synchronized (object) {
            if (this.input == null) {
                this.input = this.getInputView();
            }
        }
        return this.input;
    }

    abstract InputStream getInputView() throws IOException;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void stop() throws IOException {
        this.close();
        Object object = this.inputLock;
        synchronized (object) {
            if (this.input != null) {
                this.input.close();
                this.input = null;
            }
        }
    }
}

