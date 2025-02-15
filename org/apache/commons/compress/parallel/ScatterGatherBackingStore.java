/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.parallel;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public interface ScatterGatherBackingStore
extends Closeable {
    public InputStream getInputStream() throws IOException;

    public void writeOut(byte[] var1, int var2, int var3) throws IOException;

    public void closeForWriting() throws IOException;
}

