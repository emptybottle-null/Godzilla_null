/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianConsts;

public class LittleEndian
implements LittleEndianConsts {
    public static byte[] getByteArray(byte[] data, int offset, int size) {
        byte[] copy = new byte[size];
        System.arraycopy(data, offset, copy, 0, size);
        return copy;
    }

    public static byte[] getByteArray(byte[] data, int offset, int size, int maxSize) {
        byte[] copy = IOUtils.safelyAllocate(size, maxSize);
        System.arraycopy(data, offset, copy, 0, size);
        return copy;
    }

    public static double getDouble(byte[] data) {
        return Double.longBitsToDouble(LittleEndian.getLong(data, 0));
    }

    public static double getDouble(byte[] data, int offset) {
        return Double.longBitsToDouble(LittleEndian.getLong(data, offset));
    }

    public static float getFloat(byte[] data) {
        return LittleEndian.getFloat(data, 0);
    }

    public static float getFloat(byte[] data, int offset) {
        return Float.intBitsToFloat(LittleEndian.getInt(data, offset));
    }

    public static int getInt(byte[] data) {
        return LittleEndian.getInt(data, 0);
    }

    public static int getInt(byte[] data, int offset) {
        int i = offset;
        int b0 = data[i++] & 0xFF;
        int b1 = data[i++] & 0xFF;
        int b2 = data[i++] & 0xFF;
        int b3 = data[i++] & 0xFF;
        return (b3 << 24) + (b2 << 16) + (b1 << 8) + (b0 << 0);
    }

    public static long getLong(byte[] data) {
        return LittleEndian.getLong(data, 0);
    }

    public static long getLong(byte[] data, int offset) {
        long result = 0xFF & data[offset + 7];
        for (int j = offset + 8 - 1; j >= offset; --j) {
            result <<= 8;
            result |= (long)(0xFF & data[j]);
        }
        return result;
    }

    public static short getShort(byte[] data) {
        return LittleEndian.getShort(data, 0);
    }

    public static short getShort(byte[] data, int offset) {
        int b0 = data[offset] & 0xFF;
        int b1 = data[offset + 1] & 0xFF;
        return (short)((b1 << 8) + (b0 << 0));
    }

    public static short[] getShortArray(byte[] data, int offset, int size) {
        short[] result = new short[size / 2];
        for (int i = 0; i < result.length; ++i) {
            result[i] = LittleEndian.getShort(data, offset + i * 2);
        }
        return result;
    }

    public static short getUByte(byte[] data) {
        return (short)(data[0] & 0xFF);
    }

    public static short getUByte(byte[] data, int offset) {
        return (short)(data[offset] & 0xFF);
    }

    public static long getUInt(byte[] data) {
        return LittleEndian.getUInt(data, 0);
    }

    public static long getUInt(byte[] data, int offset) {
        long retNum = LittleEndian.getInt(data, offset);
        return retNum & 0xFFFFFFFFL;
    }

    public static int getUShort(byte[] data) {
        return LittleEndian.getUShort(data, 0);
    }

    public static int getUShort(byte[] data, int offset) {
        int b0 = data[offset] & 0xFF;
        int b1 = data[offset + 1] & 0xFF;
        return (b1 << 8) + (b0 << 0);
    }

    public static void putByte(byte[] data, int offset, int value) {
        data[offset] = (byte)value;
    }

    public static void putDouble(byte[] data, int offset, double value) {
        LittleEndian.putLong(data, offset, Double.doubleToLongBits(value));
    }

    public static void putDouble(double value, OutputStream outputStream) throws IOException {
        LittleEndian.putLong(Double.doubleToLongBits(value), outputStream);
    }

    public static void putFloat(byte[] data, int offset, float value) {
        LittleEndian.putInt(data, offset, Float.floatToIntBits(value));
    }

    public static void putFloat(float value, OutputStream outputStream) throws IOException {
        LittleEndian.putInt(Float.floatToIntBits(value), outputStream);
    }

    public static void putInt(byte[] data, int offset, int value) {
        int i = offset;
        data[i++] = (byte)(value >>> 0 & 0xFF);
        data[i++] = (byte)(value >>> 8 & 0xFF);
        data[i++] = (byte)(value >>> 16 & 0xFF);
        data[i++] = (byte)(value >>> 24 & 0xFF);
    }

    public static void putInt(int value, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(value >>> 0 & 0xFF));
        outputStream.write((byte)(value >>> 8 & 0xFF));
        outputStream.write((byte)(value >>> 16 & 0xFF));
        outputStream.write((byte)(value >>> 24 & 0xFF));
    }

    public static void putLong(byte[] data, int offset, long value) {
        data[offset + 0] = (byte)(value >>> 0 & 0xFFL);
        data[offset + 1] = (byte)(value >>> 8 & 0xFFL);
        data[offset + 2] = (byte)(value >>> 16 & 0xFFL);
        data[offset + 3] = (byte)(value >>> 24 & 0xFFL);
        data[offset + 4] = (byte)(value >>> 32 & 0xFFL);
        data[offset + 5] = (byte)(value >>> 40 & 0xFFL);
        data[offset + 6] = (byte)(value >>> 48 & 0xFFL);
        data[offset + 7] = (byte)(value >>> 56 & 0xFFL);
    }

    public static void putLong(long value, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(value >>> 0 & 0xFFL));
        outputStream.write((byte)(value >>> 8 & 0xFFL));
        outputStream.write((byte)(value >>> 16 & 0xFFL));
        outputStream.write((byte)(value >>> 24 & 0xFFL));
        outputStream.write((byte)(value >>> 32 & 0xFFL));
        outputStream.write((byte)(value >>> 40 & 0xFFL));
        outputStream.write((byte)(value >>> 48 & 0xFFL));
        outputStream.write((byte)(value >>> 56 & 0xFFL));
    }

    public static void putShort(byte[] data, int offset, short value) {
        int i = offset;
        data[i++] = (byte)(value >>> 0 & 0xFF);
        data[i++] = (byte)(value >>> 8 & 0xFF);
    }

    public static void putShort(OutputStream outputStream, short value) throws IOException {
        outputStream.write((byte)(value >>> 0 & 0xFF));
        outputStream.write((byte)(value >>> 8 & 0xFF));
    }

    public static void putShortArray(byte[] data, int startOffset, short[] value) {
        int offset = startOffset;
        for (short s : value) {
            LittleEndian.putShort(data, offset, s);
            offset += 2;
        }
    }

    public static void putUByte(byte[] data, int offset, short value) {
        data[offset] = (byte)(value & 0xFF);
    }

    public static void putUInt(byte[] data, int offset, long value) {
        int i = offset;
        data[i++] = (byte)(value >>> 0 & 0xFFL);
        data[i++] = (byte)(value >>> 8 & 0xFFL);
        data[i++] = (byte)(value >>> 16 & 0xFFL);
        data[i++] = (byte)(value >>> 24 & 0xFFL);
    }

    public static void putUInt(long value, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(value >>> 0 & 0xFFL));
        outputStream.write((byte)(value >>> 8 & 0xFFL));
        outputStream.write((byte)(value >>> 16 & 0xFFL));
        outputStream.write((byte)(value >>> 24 & 0xFFL));
    }

    public static void putUShort(byte[] data, int offset, int value) {
        int i = offset;
        data[i++] = (byte)(value >>> 0 & 0xFF);
        data[i++] = (byte)(value >>> 8 & 0xFF);
    }

    public static void putUShort(int value, OutputStream outputStream) throws IOException {
        outputStream.write((byte)(value >>> 0 & 0xFF));
        outputStream.write((byte)(value >>> 8 & 0xFF));
    }

    public static int readInt(InputStream stream) throws IOException, BufferUnderrunException {
        int ch4;
        int ch3;
        int ch2;
        int ch1 = stream.read();
        if ((ch1 | (ch2 = stream.read()) | (ch3 = stream.read()) | (ch4 = stream.read())) < 0) {
            throw new BufferUnderrunException();
        }
        return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
    }

    public static long readUInt(InputStream stream) throws IOException, BufferUnderrunException {
        long retNum = LittleEndian.readInt(stream);
        return retNum & 0xFFFFFFFFL;
    }

    public static long readLong(InputStream stream) throws IOException, BufferUnderrunException {
        int ch8;
        int ch7;
        int ch6;
        int ch5;
        int ch4;
        int ch3;
        int ch2;
        int ch1 = stream.read();
        if ((ch1 | (ch2 = stream.read()) | (ch3 = stream.read()) | (ch4 = stream.read()) | (ch5 = stream.read()) | (ch6 = stream.read()) | (ch7 = stream.read()) | (ch8 = stream.read())) < 0) {
            throw new BufferUnderrunException();
        }
        return ((long)ch8 << 56) + ((long)ch7 << 48) + ((long)ch6 << 40) + ((long)ch5 << 32) + ((long)ch4 << 24) + (long)(ch3 << 16) + (long)(ch2 << 8) + (long)(ch1 << 0);
    }

    public static short readShort(InputStream stream) throws IOException, BufferUnderrunException {
        return (short)LittleEndian.readUShort(stream);
    }

    public static int readUShort(InputStream stream) throws IOException, BufferUnderrunException {
        int ch2;
        int ch1 = stream.read();
        if ((ch1 | (ch2 = stream.read())) < 0) {
            throw new BufferUnderrunException();
        }
        return (ch2 << 8) + (ch1 << 0);
    }

    public static int ubyteToInt(byte b) {
        return b & 0xFF;
    }

    private LittleEndian() {
    }

    public static final class BufferUnderrunException
    extends IOException {
        private static final long serialVersionUID = 8736973884877006145L;

        BufferUnderrunException() {
            super("buffer underrun");
        }
    }
}

