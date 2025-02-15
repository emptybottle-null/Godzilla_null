/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

public interface LittleEndianInput {
    public int available();

    public byte readByte();

    public int readUByte();

    public short readShort();

    public int readUShort();

    public int readInt();

    public long readLong();

    public double readDouble();

    public void readFully(byte[] var1);

    public void readFully(byte[] var1, int var2, int var3);

    public void readPlain(byte[] var1, int var2, int var3);
}

