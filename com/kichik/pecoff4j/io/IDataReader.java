/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import java.io.IOException;

public interface IDataReader
extends AutoCloseable {
    public int readByte() throws IOException;

    public int readWord() throws IOException;

    public int readDoubleWord() throws IOException;

    public long readLong() throws IOException;

    public int getPosition();

    public boolean hasMore() throws IOException;

    public void jumpTo(int var1) throws IOException;

    public void skipBytes(int var1) throws IOException;

    @Override
    public void close() throws IOException;

    public void read(byte[] var1) throws IOException;

    public String readUtf(int var1) throws IOException;

    public String readUtf() throws IOException;

    public String readUnicode() throws IOException;

    public String readUnicode(int var1) throws IOException;

    public byte[] readAll() throws IOException;
}

