/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.transform.impl;

public interface InterceptFieldCallback {
    public int writeInt(Object var1, String var2, int var3, int var4);

    public char writeChar(Object var1, String var2, char var3, char var4);

    public byte writeByte(Object var1, String var2, byte var3, byte var4);

    public boolean writeBoolean(Object var1, String var2, boolean var3, boolean var4);

    public short writeShort(Object var1, String var2, short var3, short var4);

    public float writeFloat(Object var1, String var2, float var3, float var4);

    public double writeDouble(Object var1, String var2, double var3, double var5);

    public long writeLong(Object var1, String var2, long var3, long var5);

    public Object writeObject(Object var1, String var2, Object var3, Object var4);

    public int readInt(Object var1, String var2, int var3);

    public char readChar(Object var1, String var2, char var3);

    public byte readByte(Object var1, String var2, byte var3);

    public boolean readBoolean(Object var1, String var2, boolean var3);

    public short readShort(Object var1, String var2, short var3);

    public float readFloat(Object var1, String var2, float var3);

    public double readDouble(Object var1, String var2, double var3);

    public long readLong(Object var1, String var2, long var3);

    public Object readObject(Object var1, String var2, Object var3);
}

