/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import java.io.IOException;

public interface IDataWriter {
    public void writeByte(int var1) throws IOException;

    public void writeByte(int var1, int var2) throws IOException;

    public void writeWord(int var1) throws IOException;

    public void writeDoubleWord(int var1) throws IOException;

    public void writeLong(long var1) throws IOException;

    public void writeBytes(byte[] var1) throws IOException;

    public void writeUtf(String var1) throws IOException;

    public void writeUtf(String var1, int var2) throws IOException;

    public int getPosition();
}

