/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Beta
@CanIgnoreReturnValue
public interface PrimitiveSink {
    public PrimitiveSink putByte(byte var1);

    public PrimitiveSink putBytes(byte[] var1);

    public PrimitiveSink putBytes(byte[] var1, int var2, int var3);

    public PrimitiveSink putBytes(ByteBuffer var1);

    public PrimitiveSink putShort(short var1);

    public PrimitiveSink putInt(int var1);

    public PrimitiveSink putLong(long var1);

    public PrimitiveSink putFloat(float var1);

    public PrimitiveSink putDouble(double var1);

    public PrimitiveSink putBoolean(boolean var1);

    public PrimitiveSink putChar(char var1);

    public PrimitiveSink putUnencodedChars(CharSequence var1);

    public PrimitiveSink putString(CharSequence var1, Charset var2);
}

