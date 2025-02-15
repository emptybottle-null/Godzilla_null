/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ByteUtils {
    private ByteUtils() {
    }

    public static long fromLittleEndian(byte[] bytes) {
        return ByteUtils.fromLittleEndian(bytes, 0, bytes.length);
    }

    public static long fromLittleEndian(byte[] bytes, int off, int length) {
        ByteUtils.checkReadLength(length);
        long l = 0L;
        for (int i = 0; i < length; ++i) {
            l |= ((long)bytes[off + i] & 0xFFL) << 8 * i;
        }
        return l;
    }

    public static long fromLittleEndian(InputStream in, int length) throws IOException {
        ByteUtils.checkReadLength(length);
        long l = 0L;
        for (int i = 0; i < length; ++i) {
            long b = in.read();
            if (b == -1L) {
                throw new IOException("Premature end of data");
            }
            l |= b << i * 8;
        }
        return l;
    }

    public static long fromLittleEndian(ByteSupplier supplier, int length) throws IOException {
        ByteUtils.checkReadLength(length);
        long l = 0L;
        for (int i = 0; i < length; ++i) {
            long b = supplier.getAsByte();
            if (b == -1L) {
                throw new IOException("Premature end of data");
            }
            l |= b << i * 8;
        }
        return l;
    }

    public static long fromLittleEndian(DataInput in, int length) throws IOException {
        ByteUtils.checkReadLength(length);
        long l = 0L;
        for (int i = 0; i < length; ++i) {
            long b = in.readUnsignedByte();
            l |= b << i * 8;
        }
        return l;
    }

    public static void toLittleEndian(byte[] b, long value, int off, int length) {
        long num = value;
        for (int i = 0; i < length; ++i) {
            b[off + i] = (byte)(num & 0xFFL);
            num >>= 8;
        }
    }

    public static void toLittleEndian(OutputStream out, long value, int length) throws IOException {
        long num = value;
        for (int i = 0; i < length; ++i) {
            out.write((int)(num & 0xFFL));
            num >>= 8;
        }
    }

    public static void toLittleEndian(ByteConsumer consumer, long value, int length) throws IOException {
        long num = value;
        for (int i = 0; i < length; ++i) {
            consumer.accept((int)(num & 0xFFL));
            num >>= 8;
        }
    }

    public static void toLittleEndian(DataOutput out, long value, int length) throws IOException {
        long num = value;
        for (int i = 0; i < length; ++i) {
            out.write((int)(num & 0xFFL));
            num >>= 8;
        }
    }

    private static final void checkReadLength(int length) {
        if (length > 8) {
            throw new IllegalArgumentException("Can't read more than eight bytes into a long value");
        }
    }

    public static class OutputStreamByteConsumer
    implements ByteConsumer {
        private final OutputStream os;

        public OutputStreamByteConsumer(OutputStream os) {
            this.os = os;
        }

        @Override
        public void accept(int b) throws IOException {
            this.os.write(b);
        }
    }

    public static class InputStreamByteSupplier
    implements ByteSupplier {
        private final InputStream is;

        public InputStreamByteSupplier(InputStream is) {
            this.is = is;
        }

        @Override
        public int getAsByte() throws IOException {
            return this.is.read();
        }
    }

    public static interface ByteConsumer {
        public void accept(int var1) throws IOException;
    }

    public static interface ByteSupplier {
        public int getAsByte() throws IOException;
    }
}

