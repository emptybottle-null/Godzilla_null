/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Beta
@GwtIncompatible
public final class LittleEndianDataOutputStream
extends FilterOutputStream
implements DataOutput {
    public LittleEndianDataOutputStream(OutputStream out) {
        super(new DataOutputStream(Preconditions.checkNotNull(out)));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v) throws IOException {
        ((DataOutputStream)this.out).writeBoolean(v);
    }

    @Override
    public void writeByte(int v) throws IOException {
        ((DataOutputStream)this.out).writeByte(v);
    }

    @Override
    @Deprecated
    public void writeBytes(String s) throws IOException {
        ((DataOutputStream)this.out).writeBytes(s);
    }

    @Override
    public void writeChar(int v) throws IOException {
        this.writeShort(v);
    }

    @Override
    public void writeChars(String s) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            this.writeChar(s.charAt(i));
        }
    }

    @Override
    public void writeDouble(double v) throws IOException {
        this.writeLong(Double.doubleToLongBits(v));
    }

    @Override
    public void writeFloat(float v) throws IOException {
        this.writeInt(Float.floatToIntBits(v));
    }

    @Override
    public void writeInt(int v) throws IOException {
        this.out.write(0xFF & v);
        this.out.write(0xFF & v >> 8);
        this.out.write(0xFF & v >> 16);
        this.out.write(0xFF & v >> 24);
    }

    @Override
    public void writeLong(long v) throws IOException {
        byte[] bytes = Longs.toByteArray(Long.reverseBytes(v));
        this.write(bytes, 0, bytes.length);
    }

    @Override
    public void writeShort(int v) throws IOException {
        this.out.write(0xFF & v);
        this.out.write(0xFF & v >> 8);
    }

    @Override
    public void writeUTF(String str) throws IOException {
        ((DataOutputStream)this.out).writeUTF(str);
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }
}

