/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteProcessor;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

@GwtIncompatible
public final class ByteStreams {
    private static final int BUFFER_SIZE = 8192;
    private static final int ZERO_COPY_CHUNK_SIZE = 524288;
    private static final int MAX_ARRAY_LEN = 0x7FFFFFF7;
    private static final int TO_BYTE_ARRAY_DEQUE_SIZE = 20;
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream(){

        @Override
        public void write(int b) {
        }

        @Override
        public void write(byte[] b) {
            Preconditions.checkNotNull(b);
        }

        @Override
        public void write(byte[] b, int off, int len) {
            Preconditions.checkNotNull(b);
        }

        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }
    };

    static byte[] createBuffer() {
        return new byte[8192];
    }

    private ByteStreams() {
    }

    @CanIgnoreReturnValue
    public static long copy(InputStream from, OutputStream to) throws IOException {
        int r;
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        byte[] buf = ByteStreams.createBuffer();
        long total = 0L;
        while ((r = from.read(buf)) != -1) {
            to.write(buf, 0, r);
            total += (long)r;
        }
        return total;
    }

    @CanIgnoreReturnValue
    public static long copy(ReadableByteChannel from, WritableByteChannel to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        if (from instanceof FileChannel) {
            long copied;
            long oldPosition;
            FileChannel sourceChannel = (FileChannel)from;
            long position = oldPosition = sourceChannel.position();
            do {
                copied = sourceChannel.transferTo(position, 524288L, to);
                sourceChannel.position(position += copied);
            } while (copied > 0L || position < sourceChannel.size());
            return position - oldPosition;
        }
        ByteBuffer buf = ByteBuffer.wrap(ByteStreams.createBuffer());
        long total = 0L;
        while (from.read(buf) != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                total += (long)to.write(buf);
            }
            buf.clear();
        }
        return total;
    }

    private static byte[] toByteArrayInternal(InputStream in, Deque<byte[]> bufs, int totalLen) throws IOException {
        int bufSize = 8192;
        while (totalLen < 0x7FFFFFF7) {
            byte[] buf = new byte[Math.min(bufSize, 0x7FFFFFF7 - totalLen)];
            bufs.add(buf);
            int off = 0;
            while (off < buf.length) {
                int r = in.read(buf, off, buf.length - off);
                if (r == -1) {
                    return ByteStreams.combineBuffers(bufs, totalLen);
                }
                off += r;
                totalLen += r;
            }
            bufSize = IntMath.saturatedMultiply(bufSize, 2);
        }
        if (in.read() == -1) {
            return ByteStreams.combineBuffers(bufs, 0x7FFFFFF7);
        }
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }

    private static byte[] combineBuffers(Deque<byte[]> bufs, int totalLen) {
        int bytesToCopy;
        byte[] result = new byte[totalLen];
        for (int remaining = totalLen; remaining > 0; remaining -= bytesToCopy) {
            byte[] buf = bufs.removeFirst();
            bytesToCopy = Math.min(remaining, buf.length);
            int resultOffset = totalLen - remaining;
            System.arraycopy(buf, 0, result, resultOffset, bytesToCopy);
        }
        return result;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        Preconditions.checkNotNull(in);
        return ByteStreams.toByteArrayInternal(in, new ArrayDeque<byte[]>(20), 0);
    }

    static byte[] toByteArray(InputStream in, long expectedSize) throws IOException {
        int read;
        Preconditions.checkArgument(expectedSize >= 0L, "expectedSize (%s) must be non-negative", expectedSize);
        if (expectedSize > 0x7FFFFFF7L) {
            throw new OutOfMemoryError(expectedSize + " bytes is too large to fit in a byte array");
        }
        byte[] bytes = new byte[(int)expectedSize];
        for (int remaining = (int)expectedSize; remaining > 0; remaining -= read) {
            int off = (int)expectedSize - remaining;
            read = in.read(bytes, off, remaining);
            if (read != -1) continue;
            return Arrays.copyOf(bytes, off);
        }
        int b = in.read();
        if (b == -1) {
            return bytes;
        }
        ArrayDeque<byte[]> bufs = new ArrayDeque<byte[]>(22);
        bufs.add(bytes);
        bufs.add(new byte[]{(byte)b});
        return ByteStreams.toByteArrayInternal(in, bufs, bytes.length + 1);
    }

    @CanIgnoreReturnValue
    @Beta
    public static long exhaust(InputStream in) throws IOException {
        long read;
        long total = 0L;
        byte[] buf = ByteStreams.createBuffer();
        while ((read = (long)in.read(buf)) != -1L) {
            total += read;
        }
        return total;
    }

    @Beta
    public static ByteArrayDataInput newDataInput(byte[] bytes) {
        return ByteStreams.newDataInput(new ByteArrayInputStream(bytes));
    }

    @Beta
    public static ByteArrayDataInput newDataInput(byte[] bytes, int start) {
        Preconditions.checkPositionIndex(start, bytes.length);
        return ByteStreams.newDataInput(new ByteArrayInputStream(bytes, start, bytes.length - start));
    }

    @Beta
    public static ByteArrayDataInput newDataInput(ByteArrayInputStream byteArrayInputStream) {
        return new ByteArrayDataInputStream(Preconditions.checkNotNull(byteArrayInputStream));
    }

    @Beta
    public static ByteArrayDataOutput newDataOutput() {
        return ByteStreams.newDataOutput(new ByteArrayOutputStream());
    }

    @Beta
    public static ByteArrayDataOutput newDataOutput(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(String.format("Invalid size: %s", size));
        }
        return ByteStreams.newDataOutput(new ByteArrayOutputStream(size));
    }

    @Beta
    public static ByteArrayDataOutput newDataOutput(ByteArrayOutputStream byteArrayOutputSteam) {
        return new ByteArrayDataOutputStream(Preconditions.checkNotNull(byteArrayOutputSteam));
    }

    @Beta
    public static OutputStream nullOutputStream() {
        return NULL_OUTPUT_STREAM;
    }

    @Beta
    public static InputStream limit(InputStream in, long limit) {
        return new LimitedInputStream(in, limit);
    }

    @Beta
    public static void readFully(InputStream in, byte[] b) throws IOException {
        ByteStreams.readFully(in, b, 0, b.length);
    }

    @Beta
    public static void readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        int read = ByteStreams.read(in, b, off, len);
        if (read != len) {
            throw new EOFException("reached end of stream after reading " + read + " bytes; " + len + " bytes expected");
        }
    }

    @Beta
    public static void skipFully(InputStream in, long n) throws IOException {
        long skipped = ByteStreams.skipUpTo(in, n);
        if (skipped < n) {
            throw new EOFException("reached end of stream after skipping " + skipped + " bytes; " + n + " bytes expected");
        }
    }

    static long skipUpTo(InputStream in, long n) throws IOException {
        int skip;
        long remaining;
        long totalSkipped;
        long skipped;
        byte[] buf = ByteStreams.createBuffer();
        for (totalSkipped = 0L; totalSkipped < n && ((skipped = ByteStreams.skipSafely(in, remaining = n - totalSkipped)) != 0L || (skipped = (long)in.read(buf, 0, skip = (int)Math.min(remaining, (long)buf.length))) != -1L); totalSkipped += skipped) {
        }
        return totalSkipped;
    }

    private static long skipSafely(InputStream in, long n) throws IOException {
        int available = in.available();
        return available == 0 ? 0L : in.skip(Math.min((long)available, n));
    }

    @Beta
    @CanIgnoreReturnValue
    public static <T> T readBytes(InputStream input, ByteProcessor<T> processor) throws IOException {
        int read;
        Preconditions.checkNotNull(input);
        Preconditions.checkNotNull(processor);
        byte[] buf = ByteStreams.createBuffer();
        while ((read = input.read(buf)) != -1 && processor.processBytes(buf, 0, read)) {
        }
        return processor.getResult();
    }

    @Beta
    @CanIgnoreReturnValue
    public static int read(InputStream in, byte[] b, int off, int len) throws IOException {
        int total;
        int result;
        Preconditions.checkNotNull(in);
        Preconditions.checkNotNull(b);
        if (len < 0) {
            throw new IndexOutOfBoundsException("len is negative");
        }
        for (total = 0; total < len && (result = in.read(b, off + total, len - total)) != -1; total += result) {
        }
        return total;
    }

    private static final class LimitedInputStream
    extends FilterInputStream {
        private long left;
        private long mark = -1L;

        LimitedInputStream(InputStream in, long limit) {
            super(in);
            Preconditions.checkNotNull(in);
            Preconditions.checkArgument(limit >= 0L, "limit must be non-negative");
            this.left = limit;
        }

        @Override
        public int available() throws IOException {
            return (int)Math.min((long)this.in.available(), this.left);
        }

        @Override
        public synchronized void mark(int readLimit) {
            this.in.mark(readLimit);
            this.mark = this.left;
        }

        @Override
        public int read() throws IOException {
            if (this.left == 0L) {
                return -1;
            }
            int result = this.in.read();
            if (result != -1) {
                --this.left;
            }
            return result;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (this.left == 0L) {
                return -1;
            }
            int result = this.in.read(b, off, len = (int)Math.min((long)len, this.left));
            if (result != -1) {
                this.left -= (long)result;
            }
            return result;
        }

        @Override
        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1L) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.left = this.mark;
        }

        @Override
        public long skip(long n) throws IOException {
            n = Math.min(n, this.left);
            long skipped = this.in.skip(n);
            this.left -= skipped;
            return skipped;
        }
    }

    private static class ByteArrayDataOutputStream
    implements ByteArrayDataOutput {
        final DataOutput output;
        final ByteArrayOutputStream byteArrayOutputSteam;

        ByteArrayDataOutputStream(ByteArrayOutputStream byteArrayOutputSteam) {
            this.byteArrayOutputSteam = byteArrayOutputSteam;
            this.output = new DataOutputStream(byteArrayOutputSteam);
        }

        @Override
        public void write(int b) {
            try {
                this.output.write(b);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void write(byte[] b) {
            try {
                this.output.write(b);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void write(byte[] b, int off, int len) {
            try {
                this.output.write(b, off, len);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeBoolean(boolean v) {
            try {
                this.output.writeBoolean(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeByte(int v) {
            try {
                this.output.writeByte(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeBytes(String s) {
            try {
                this.output.writeBytes(s);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeChar(int v) {
            try {
                this.output.writeChar(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeChars(String s) {
            try {
                this.output.writeChars(s);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeDouble(double v) {
            try {
                this.output.writeDouble(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeFloat(float v) {
            try {
                this.output.writeFloat(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeInt(int v) {
            try {
                this.output.writeInt(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeLong(long v) {
            try {
                this.output.writeLong(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeShort(int v) {
            try {
                this.output.writeShort(v);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public void writeUTF(String s) {
            try {
                this.output.writeUTF(s);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public byte[] toByteArray() {
            return this.byteArrayOutputSteam.toByteArray();
        }
    }

    private static class ByteArrayDataInputStream
    implements ByteArrayDataInput {
        final DataInput input;

        ByteArrayDataInputStream(ByteArrayInputStream byteArrayInputStream) {
            this.input = new DataInputStream(byteArrayInputStream);
        }

        @Override
        public void readFully(byte[] b) {
            try {
                this.input.readFully(b);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public void readFully(byte[] b, int off, int len) {
            try {
                this.input.readFully(b, off, len);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public int skipBytes(int n) {
            try {
                return this.input.skipBytes(n);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public boolean readBoolean() {
            try {
                return this.input.readBoolean();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public byte readByte() {
            try {
                return this.input.readByte();
            } catch (EOFException e) {
                throw new IllegalStateException(e);
            } catch (IOException impossible) {
                throw new AssertionError((Object)impossible);
            }
        }

        @Override
        public int readUnsignedByte() {
            try {
                return this.input.readUnsignedByte();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public short readShort() {
            try {
                return this.input.readShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public int readUnsignedShort() {
            try {
                return this.input.readUnsignedShort();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public char readChar() {
            try {
                return this.input.readChar();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public int readInt() {
            try {
                return this.input.readInt();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public long readLong() {
            try {
                return this.input.readLong();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public float readFloat() {
            try {
                return this.input.readFloat();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public double readDouble() {
            try {
                return this.input.readDouble();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public String readLine() {
            try {
                return this.input.readLine();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override
        public String readUTF() {
            try {
                return this.input.readUTF();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

