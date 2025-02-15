/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EndianUtils {
    public static short swapShort(short value) {
        return (short)(((value >> 0 & 0xFF) << 8) + ((value >> 8 & 0xFF) << 0));
    }

    public static int swapInteger(int value) {
        return ((value >> 0 & 0xFF) << 24) + ((value >> 8 & 0xFF) << 16) + ((value >> 16 & 0xFF) << 8) + ((value >> 24 & 0xFF) << 0);
    }

    public static long swapLong(long value) {
        return ((value >> 0 & 0xFFL) << 56) + ((value >> 8 & 0xFFL) << 48) + ((value >> 16 & 0xFFL) << 40) + ((value >> 24 & 0xFFL) << 32) + ((value >> 32 & 0xFFL) << 24) + ((value >> 40 & 0xFFL) << 16) + ((value >> 48 & 0xFFL) << 8) + ((value >> 56 & 0xFFL) << 0);
    }

    public static float swapFloat(float value) {
        return Float.intBitsToFloat(EndianUtils.swapInteger(Float.floatToIntBits(value)));
    }

    public static double swapDouble(double value) {
        return Double.longBitsToDouble(EndianUtils.swapLong(Double.doubleToLongBits(value)));
    }

    public static void writeSwappedShort(byte[] data, int offset, short value) {
        data[offset + 0] = (byte)(value >> 0 & 0xFF);
        data[offset + 1] = (byte)(value >> 8 & 0xFF);
    }

    public static short readSwappedShort(byte[] data, int offset) {
        return (short)(((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8));
    }

    public static int readSwappedUnsignedShort(byte[] data, int offset) {
        return ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8);
    }

    public static void writeSwappedInteger(byte[] data, int offset, int value) {
        data[offset + 0] = (byte)(value >> 0 & 0xFF);
        data[offset + 1] = (byte)(value >> 8 & 0xFF);
        data[offset + 2] = (byte)(value >> 16 & 0xFF);
        data[offset + 3] = (byte)(value >> 24 & 0xFF);
    }

    public static int readSwappedInteger(byte[] data, int offset) {
        return ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16) + ((data[offset + 3] & 0xFF) << 24);
    }

    public static long readSwappedUnsignedInteger(byte[] data, int offset) {
        long low = ((data[offset + 0] & 0xFF) << 0) + ((data[offset + 1] & 0xFF) << 8) + ((data[offset + 2] & 0xFF) << 16);
        long high = data[offset + 3] & 0xFF;
        return (high << 24) + (0xFFFFFFFFL & low);
    }

    public static void writeSwappedLong(byte[] data, int offset, long value) {
        data[offset + 0] = (byte)(value >> 0 & 0xFFL);
        data[offset + 1] = (byte)(value >> 8 & 0xFFL);
        data[offset + 2] = (byte)(value >> 16 & 0xFFL);
        data[offset + 3] = (byte)(value >> 24 & 0xFFL);
        data[offset + 4] = (byte)(value >> 32 & 0xFFL);
        data[offset + 5] = (byte)(value >> 40 & 0xFFL);
        data[offset + 6] = (byte)(value >> 48 & 0xFFL);
        data[offset + 7] = (byte)(value >> 56 & 0xFFL);
    }

    public static long readSwappedLong(byte[] data, int offset) {
        long low = EndianUtils.readSwappedInteger(data, offset);
        long high = EndianUtils.readSwappedInteger(data, offset + 4);
        return (high << 32) + (0xFFFFFFFFL & low);
    }

    public static void writeSwappedFloat(byte[] data, int offset, float value) {
        EndianUtils.writeSwappedInteger(data, offset, Float.floatToIntBits(value));
    }

    public static float readSwappedFloat(byte[] data, int offset) {
        return Float.intBitsToFloat(EndianUtils.readSwappedInteger(data, offset));
    }

    public static void writeSwappedDouble(byte[] data, int offset, double value) {
        EndianUtils.writeSwappedLong(data, offset, Double.doubleToLongBits(value));
    }

    public static double readSwappedDouble(byte[] data, int offset) {
        return Double.longBitsToDouble(EndianUtils.readSwappedLong(data, offset));
    }

    public static void writeSwappedShort(OutputStream output, short value) throws IOException {
        output.write((byte)(value >> 0 & 0xFF));
        output.write((byte)(value >> 8 & 0xFF));
    }

    public static short readSwappedShort(InputStream input) throws IOException {
        return (short)(((EndianUtils.read(input) & 0xFF) << 0) + ((EndianUtils.read(input) & 0xFF) << 8));
    }

    public static int readSwappedUnsignedShort(InputStream input) throws IOException {
        int value1 = EndianUtils.read(input);
        int value2 = EndianUtils.read(input);
        return ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8);
    }

    public static void writeSwappedInteger(OutputStream output, int value) throws IOException {
        output.write((byte)(value >> 0 & 0xFF));
        output.write((byte)(value >> 8 & 0xFF));
        output.write((byte)(value >> 16 & 0xFF));
        output.write((byte)(value >> 24 & 0xFF));
    }

    public static int readSwappedInteger(InputStream input) throws IOException {
        int value1 = EndianUtils.read(input);
        int value2 = EndianUtils.read(input);
        int value3 = EndianUtils.read(input);
        int value4 = EndianUtils.read(input);
        return ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8) + ((value3 & 0xFF) << 16) + ((value4 & 0xFF) << 24);
    }

    public static long readSwappedUnsignedInteger(InputStream input) throws IOException {
        int value1 = EndianUtils.read(input);
        int value2 = EndianUtils.read(input);
        int value3 = EndianUtils.read(input);
        int value4 = EndianUtils.read(input);
        long low = ((value1 & 0xFF) << 0) + ((value2 & 0xFF) << 8) + ((value3 & 0xFF) << 16);
        long high = value4 & 0xFF;
        return (high << 24) + (0xFFFFFFFFL & low);
    }

    public static void writeSwappedLong(OutputStream output, long value) throws IOException {
        output.write((byte)(value >> 0 & 0xFFL));
        output.write((byte)(value >> 8 & 0xFFL));
        output.write((byte)(value >> 16 & 0xFFL));
        output.write((byte)(value >> 24 & 0xFFL));
        output.write((byte)(value >> 32 & 0xFFL));
        output.write((byte)(value >> 40 & 0xFFL));
        output.write((byte)(value >> 48 & 0xFFL));
        output.write((byte)(value >> 56 & 0xFFL));
    }

    public static long readSwappedLong(InputStream input) throws IOException {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; ++i) {
            bytes[i] = (byte)EndianUtils.read(input);
        }
        return EndianUtils.readSwappedLong(bytes, 0);
    }

    public static void writeSwappedFloat(OutputStream output, float value) throws IOException {
        EndianUtils.writeSwappedInteger(output, Float.floatToIntBits(value));
    }

    public static float readSwappedFloat(InputStream input) throws IOException {
        return Float.intBitsToFloat(EndianUtils.readSwappedInteger(input));
    }

    public static void writeSwappedDouble(OutputStream output, double value) throws IOException {
        EndianUtils.writeSwappedLong(output, Double.doubleToLongBits(value));
    }

    public static double readSwappedDouble(InputStream input) throws IOException {
        return Double.longBitsToDouble(EndianUtils.readSwappedLong(input));
    }

    private static int read(InputStream input) throws IOException {
        int value = input.read();
        if (-1 == value) {
            throw new EOFException("Unexpected EOF reached");
        }
        return value;
    }
}

