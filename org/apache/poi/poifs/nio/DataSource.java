/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public abstract class DataSource {
    public abstract ByteBuffer read(int var1, long var2) throws IOException;

    public abstract void write(ByteBuffer var1, long var2) throws IOException;

    public abstract long size() throws IOException;

    public abstract void close() throws IOException;

    public abstract void copyTo(OutputStream var1) throws IOException;
}

