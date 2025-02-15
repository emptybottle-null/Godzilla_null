/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.tar;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

public class TarUtils {
    private static final int BYTE_MASK = 255;
    static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(null);
    static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding(){

        @Override
        public boolean canEncode(String name) {
            return true;
        }

        @Override
        public ByteBuffer encode(String name) {
            int length = name.length();
            byte[] buf = new byte[length];
            for (int i = 0; i < length; ++i) {
                buf[i] = (byte)name.charAt(i);
            }
            return ByteBuffer.wrap(buf);
        }

        @Override
        public String decode(byte[] buffer) {
            int length = buffer.length;
            StringBuilder result = new StringBuilder(length);
            for (byte b : buffer) {
                if (b == 0) break;
                result.append((char)(b & 0xFF));
            }
            return result.toString();
        }
    };

    private TarUtils() {
    }

    public static long parseOctal(byte[] buffer, int offset, int length) {
        int start;
        long result = 0L;
        int end = offset + length;
        if (length < 2) {
            throw new IllegalArgumentException("Length " + length + " must be at least 2");
        }
        if (buffer[start] == 0) {
            return 0L;
        }
        for (start = offset; start < end && buffer[start] == 32; ++start) {
        }
        byte trailer = buffer[end - 1];
        while (start < end && (trailer == 0 || trailer == 32)) {
            trailer = buffer[--end - 1];
        }
        while (start < end) {
            byte currentByte = buffer[start];
            if (currentByte < 48 || currentByte > 55) {
                throw new IllegalArgumentException(TarUtils.exceptionMessage(buffer, offset, length, start, currentByte));
            }
            result = (result << 3) + (long)(currentByte - 48);
            ++start;
        }
        return result;
    }

    public static long parseOctalOrBinary(byte[] buffer, int offset, int length) {
        boolean negative;
        if ((buffer[offset] & 0x80) == 0) {
            return TarUtils.parseOctal(buffer, offset, length);
        }
        boolean bl = negative = buffer[offset] == -1;
        if (length < 9) {
            return TarUtils.parseBinaryLong(buffer, offset, length, negative);
        }
        return TarUtils.parseBinaryBigInteger(buffer, offset, length, negative);
    }

    private static long parseBinaryLong(byte[] buffer, int offset, int length, boolean negative) {
        if (length >= 9) {
            throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number exceeds maximum signed long value");
        }
        long val = 0L;
        for (int i = 1; i < length; ++i) {
            val = (val << 8) + (long)(buffer[offset + i] & 0xFF);
        }
        if (negative) {
            --val;
            val ^= (long)Math.pow(2.0, (double)(length - 1) * 8.0) - 1L;
        }
        return negative ? -val : val;
    }

    private static long parseBinaryBigInteger(byte[] buffer, int offset, int length, boolean negative) {
        byte[] remainder = new byte[length - 1];
        System.arraycopy(buffer, offset + 1, remainder, 0, length - 1);
        BigInteger val = new BigInteger(remainder);
        if (negative) {
            val = val.add(BigInteger.valueOf(-1L)).not();
        }
        if (val.bitLength() > 63) {
            throw new IllegalArgumentException("At offset " + offset + ", " + length + " byte binary number exceeds maximum signed long value");
        }
        return negative ? -val.longValue() : val.longValue();
    }

    public static boolean parseBoolean(byte[] buffer, int offset) {
        return buffer[offset] == 1;
    }

    private static String exceptionMessage(byte[] buffer, int offset, int length, int current, byte currentByte) {
        String string = new String(buffer, offset, length);
        string = string.replaceAll("\u0000", "{NUL}");
        return "Invalid byte " + currentByte + " at offset " + (current - offset) + " in '" + string + "' len=" + length;
    }

    public static String parseName(byte[] buffer, int offset, int length) {
        try {
            return TarUtils.parseName(buffer, offset, length, DEFAULT_ENCODING);
        } catch (IOException ex) {
            try {
                return TarUtils.parseName(buffer, offset, length, FALLBACK_ENCODING);
            } catch (IOException ex2) {
                throw new RuntimeException(ex2);
            }
        }
    }

    public static String parseName(byte[] buffer, int offset, int length, ZipEncoding encoding) throws IOException {
        int len = 0;
        int i = offset;
        while (len < length && buffer[i] != 0) {
            ++len;
            ++i;
        }
        if (len > 0) {
            byte[] b = new byte[len];
            System.arraycopy(buffer, offset, b, 0, len);
            return encoding.decode(b);
        }
        return "";
    }

    public static int formatNameBytes(String name, byte[] buf, int offset, int length) {
        try {
            return TarUtils.formatNameBytes(name, buf, offset, length, DEFAULT_ENCODING);
        } catch (IOException ex) {
            try {
                return TarUtils.formatNameBytes(name, buf, offset, length, FALLBACK_ENCODING);
            } catch (IOException ex2) {
                throw new RuntimeException(ex2);
            }
        }
    }

    public static int formatNameBytes(String name, byte[] buf, int offset, int length, ZipEncoding encoding) throws IOException {
        int len = name.length();
        ByteBuffer b = encoding.encode(name);
        while (b.limit() > length && len > 0) {
            b = encoding.encode(name.substring(0, --len));
        }
        int limit = b.limit() - b.position();
        System.arraycopy(b.array(), b.arrayOffset(), buf, offset, limit);
        for (int i = limit; i < length; ++i) {
            buf[offset + i] = 0;
        }
        return offset + length;
    }

    public static void formatUnsignedOctalString(long value, byte[] buffer, int offset, int length) {
        int remaining = length;
        --remaining;
        if (value == 0L) {
            buffer[offset + remaining--] = 48;
        } else {
            long val;
            for (val = value; remaining >= 0 && val != 0L; val >>>= 3, --remaining) {
                buffer[offset + remaining] = (byte)(48 + (byte)(val & 7L));
            }
            if (val != 0L) {
                throw new IllegalArgumentException(value + "=" + Long.toOctalString(value) + " will not fit in octal number buffer of length " + length);
            }
        }
        while (remaining >= 0) {
            buffer[offset + remaining] = 48;
            --remaining;
        }
    }

    public static int formatOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 2;
        TarUtils.formatUnsignedOctalString(value, buf, offset, idx);
        buf[offset + idx++] = 32;
        buf[offset + idx] = 0;
        return offset + length;
    }

    public static int formatLongOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 1;
        TarUtils.formatUnsignedOctalString(value, buf, offset, idx);
        buf[offset + idx] = 32;
        return offset + length;
    }

    public static int formatLongOctalOrBinaryBytes(long value, byte[] buf, int offset, int length) {
        boolean negative;
        long maxAsOctalChar = length == 8 ? 0x1FFFFFL : 0x1FFFFFFFFL;
        boolean bl = negative = value < 0L;
        if (!negative && value <= maxAsOctalChar) {
            return TarUtils.formatLongOctalBytes(value, buf, offset, length);
        }
        if (length < 9) {
            TarUtils.formatLongBinary(value, buf, offset, length, negative);
        } else {
            TarUtils.formatBigIntegerBinary(value, buf, offset, length, negative);
        }
        buf[offset] = (byte)(negative ? 255 : 128);
        return offset + length;
    }

    private static void formatLongBinary(long value, byte[] buf, int offset, int length, boolean negative) {
        int bits = (length - 1) * 8;
        long max = 1L << bits;
        long val = Math.abs(value);
        if (val < 0L || val >= max) {
            throw new IllegalArgumentException("Value " + value + " is too large for " + length + " byte field.");
        }
        if (negative) {
            val ^= max - 1L;
            ++val;
            val |= 255L << bits;
        }
        for (int i = offset + length - 1; i >= offset; --i) {
            buf[i] = (byte)val;
            val >>= 8;
        }
    }

    private static void formatBigIntegerBinary(long value, byte[] buf, int offset, int length, boolean negative) {
        BigInteger val = BigInteger.valueOf(value);
        byte[] b = val.toByteArray();
        int len = b.length;
        if (len > length - 1) {
            throw new IllegalArgumentException("Value " + value + " is too large for " + length + " byte field.");
        }
        int off = offset + length - len;
        System.arraycopy(b, 0, buf, off, len);
        byte fill = (byte)(negative ? 255 : 0);
        for (int i = offset + 1; i < off; ++i) {
            buf[i] = fill;
        }
    }

    public static int formatCheckSumOctalBytes(long value, byte[] buf, int offset, int length) {
        int idx = length - 2;
        TarUtils.formatUnsignedOctalString(value, buf, offset, idx);
        buf[offset + idx++] = 0;
        buf[offset + idx] = 32;
        return offset + length;
    }

    public static long computeCheckSum(byte[] buf) {
        long sum = 0L;
        for (byte element : buf) {
            sum += (long)(0xFF & element);
        }
        return sum;
    }

    public static boolean verifyCheckSum(byte[] header) {
        long storedSum = TarUtils.parseOctal(header, 148, 8);
        long unsignedSum = 0L;
        long signedSum = 0L;
        for (int i = 0; i < header.length; ++i) {
            int b = header[i];
            if (148 <= i && i < 156) {
                b = 32;
            }
            unsignedSum += (long)(0xFF & b);
            signedSum += (long)b;
        }
        return storedSum == unsignedSum || storedSum == signedSum;
    }
}

