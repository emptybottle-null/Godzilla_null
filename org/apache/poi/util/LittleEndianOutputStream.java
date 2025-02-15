/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.util.LittleEndianOutput;

public final class LittleEndianOutputStream
extends FilterOutputStream
implements LittleEndianOutput {
    public LittleEndianOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void writeByte(int v) {
        try {
            this.out.write(v);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeDouble(double v) {
        this.writeLong(Double.doubleToLongBits(v));
    }

    @Override
    public void writeInt(int v) {
        int b3 = v >>> 24 & 0xFF;
        int b2 = v >>> 16 & 0xFF;
        int b1 = v >>> 8 & 0xFF;
        int b0 = v & 0xFF;
        try {
            this.out.write(b0);
            this.out.write(b1);
            this.out.write(b2);
            this.out.write(b3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeLong(long v) {
        this.writeInt((int)v);
        this.writeInt((int)(v >> 32));
    }

    @Override
    public void writeShort(int v) {
        int b1 = v >>> 8 & 0xFF;
        int b0 = v & 0xFF;
        try {
            this.out.write(b0);
            this.out.write(b1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(byte[] b) {
        try {
            super.write(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) {
        try {
            super.write(b, off, len);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeUInt(long value) {
        try {
            this.out.write((byte)(value & 0xFFL));
            this.out.write((byte)(value >>> 8 & 0xFFL));
            this.out.write((byte)(value >>> 16 & 0xFFL));
            this.out.write((byte)(value >>> 24 & 0xFFL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putUShort(int value) {
        try {
            this.out.write((byte)(value & 0xFF));
            this.out.write((byte)(value >>> 8 & 0xFF));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

