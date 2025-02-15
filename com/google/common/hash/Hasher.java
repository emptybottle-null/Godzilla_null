/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.PrimitiveSink;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Beta
@CanIgnoreReturnValue
public interface Hasher
extends PrimitiveSink {
    @Override
    public Hasher putByte(byte var1);

    @Override
    public Hasher putBytes(byte[] var1);

    @Override
    public Hasher putBytes(byte[] var1, int var2, int var3);

    @Override
    public Hasher putBytes(ByteBuffer var1);

    @Override
    public Hasher putShort(short var1);

    @Override
    public Hasher putInt(int var1);

    @Override
    public Hasher putLong(long var1);

    @Override
    public Hasher putFloat(float var1);

    @Override
    public Hasher putDouble(double var1);

    @Override
    public Hasher putBoolean(boolean var1);

    @Override
    public Hasher putChar(char var1);

    @Override
    public Hasher putUnencodedChars(CharSequence var1);

    @Override
    public Hasher putString(CharSequence var1, Charset var2);

    public <T> Hasher putObject(T var1, Funnel<? super T> var2);

    public HashCode hash();

    @Deprecated
    public int hashCode();
}

