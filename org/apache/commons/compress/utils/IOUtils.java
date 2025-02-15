/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public final class IOUtils {
    private static final int COPY_BUF_SIZE = 8024;
    private static final int SKIP_BUF_SIZE = 4096;
    private static final byte[] SKIP_BUF = new byte[4096];

    private IOUtils() {
    }

    public static long copy(InputStream input, OutputStream output) throws IOException {
        return IOUtils.copy(input, output, 8024);
    }

    public static long copy(InputStream input, OutputStream output, int buffersize) throws IOException {
        if (buffersize < 1) {
            throw new IllegalArgumentException("buffersize must be bigger than 0");
        }
        byte[] buffer = new byte[buffersize];
        int n = 0;
        long count = 0L;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += (long)n;
        }
        return count;
    }

    public static long skip(InputStream input, long numToSkip) throws IOException {
        int read;
        long skipped;
        long available = numToSkip;
        while (numToSkip > 0L && (skipped = input.skip(numToSkip)) != 0L) {
            numToSkip -= skipped;
        }
        while (numToSkip > 0L && (read = IOUtils.readFully(input, SKIP_BUF, 0, (int)Math.min(numToSkip, 4096L))) >= 1) {
            numToSkip -= (long)read;
        }
        return available - numToSkip;
    }

    public static int readFully(InputStream input, byte[] b) throws IOException {
        return IOUtils.readFully(input, b, 0, b.length);
    }

    public static int readFully(InputStream input, byte[] b, int offset, int len) throws IOException {
        int count;
        if (len < 0 || offset < 0 || len + offset > b.length) {
            throw new IndexOutOfBoundsException();
        }
        int x = 0;
        for (count = 0; count != len && (x = input.read(b, offset + count, len - count)) != -1; count += x) {
        }
        return count;
    }

    public static void readFully(ReadableByteChannel channel, ByteBuffer b) throws IOException {
        int read;
        int readNow;
        int expectedLength = b.remaining();
        for (read = 0; read < expectedLength && (readNow = channel.read(b)) > 0; read += readNow) {
        }
        if (read < expectedLength) {
            throw new EOFException();
        }
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(input, output);
        return output.toByteArray();
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }
}

