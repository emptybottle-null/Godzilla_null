/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.encoders;

public interface Translator {
    public int getEncodedBlockSize();

    public int encode(byte[] var1, int var2, int var3, byte[] var4, int var5);

    public int getDecodedBlockSize();

    public int decode(byte[] var1, int var2, int var3, byte[] var4, int var5);
}

