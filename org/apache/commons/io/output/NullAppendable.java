/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.IOException;

public class NullAppendable
implements Appendable {
    public static final NullAppendable INSTANCE = new NullAppendable();

    private NullAppendable() {
    }

    @Override
    public Appendable append(char c) throws IOException {
        return this;
    }

    @Override
    public Appendable append(CharSequence csq) throws IOException {
        return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        return this;
    }
}

