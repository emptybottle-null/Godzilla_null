/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

public interface LittleEndianOutput {
    public void writeByte(int var1);

    public void writeShort(int var1);

    public void writeInt(int var1);

    public void writeLong(long var1);

    public void writeDouble(double var1);

    public void write(byte[] var1);

    public void write(byte[] var1, int var2, int var3);
}

