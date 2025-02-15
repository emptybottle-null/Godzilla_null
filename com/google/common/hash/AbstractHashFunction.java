/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Immutable
abstract class AbstractHashFunction
implements HashFunction {
    AbstractHashFunction() {
    }

    @Override
    public <T> HashCode hashObject(T instance, Funnel<? super T> funnel) {
        return this.newHasher().putObject(instance, funnel).hash();
    }

    @Override
    public HashCode hashUnencodedChars(CharSequence input) {
        int len = input.length();
        return this.newHasher(len * 2).putUnencodedChars(input).hash();
    }

    @Override
    public HashCode hashString(CharSequence input, Charset charset) {
        return this.newHasher().putString(input, charset).hash();
    }

    @Override
    public HashCode hashInt(int input) {
        return this.newHasher(4).putInt(input).hash();
    }

    @Override
    public HashCode hashLong(long input) {
        return this.newHasher(8).putLong(input).hash();
    }

    @Override
    public HashCode hashBytes(byte[] input) {
        return this.hashBytes(input, 0, input.length);
    }

    @Override
    public HashCode hashBytes(byte[] input, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, input.length);
        return this.newHasher(len).putBytes(input, off, len).hash();
    }

    @Override
    public HashCode hashBytes(ByteBuffer input) {
        return this.newHasher(input.remaining()).putBytes(input).hash();
    }

    @Override
    public Hasher newHasher(int expectedInputSize) {
        Preconditions.checkArgument(expectedInputSize >= 0, "expectedInputSize must be >= 0 but was %s", expectedInputSize);
        return this.newHasher();
    }
}

