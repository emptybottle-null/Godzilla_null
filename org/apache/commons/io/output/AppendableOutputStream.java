/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class AppendableOutputStream<T extends Appendable>
extends OutputStream {
    private final T appendable;

    public AppendableOutputStream(T appendable) {
        this.appendable = appendable;
    }

    @Override
    public void write(int b) throws IOException {
        this.appendable.append((char)b);
    }

    public T getAppendable() {
        return this.appendable;
    }
}

