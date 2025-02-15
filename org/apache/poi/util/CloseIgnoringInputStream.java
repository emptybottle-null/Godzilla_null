/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.FilterInputStream;
import java.io.InputStream;

public class CloseIgnoringInputStream
extends FilterInputStream {
    public CloseIgnoringInputStream(InputStream in) {
        super(in);
    }

    @Override
    public void close() {
    }
}

