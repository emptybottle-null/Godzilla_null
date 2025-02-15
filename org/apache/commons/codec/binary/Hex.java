/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.binary;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

public class Hex
implements BinaryEncoder,
BinaryDecoder {
    public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final Charset charset;

    public static byte[] decodeHex(String data) throws DecoderException {
        return Hex.decodeHex(data.toCharArray());
    }

    public static byte[] decodeHex(char[] data) throws DecoderException {
        int len = data.length;
        if ((len & 1) != 0) {
            throw new DecoderException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        int i = 0;
        int j = 0;
        while (j < len) {
            int f = Hex.toDigit(data[j], j) << 4;
            f |= Hex.toDigit(data[++j], j);
            ++j;
            out[i] = (byte)(f & 0xFF);
            ++i;
        }
        return out;
    }

    public static char[] encodeHex(byte[] data) {
        return Hex.encodeHex(data, true);
    }

    public static char[] encodeHex(ByteBuffer data) {
        return Hex.encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return Hex.encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    public static char[] encodeHex(ByteBuffer data, boolean toLowerCase) {
        return Hex.encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        int j = 0;
        for (int i = 0; i < l; ++i) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0xF & data[i]];
        }
        return out;
    }

    protected static char[] encodeHex(ByteBuffer byteBuffer, char[] toDigits) {
        return Hex.encodeHex(Hex.toByteArray(byteBuffer), toDigits);
    }

    public static String encodeHexString(byte[] data) {
        return new String(Hex.encodeHex(data));
    }

    public static String encodeHexString(byte[] data, boolean toLowerCase) {
        return new String(Hex.encodeHex(data, toLowerCase));
    }

    public static String encodeHexString(ByteBuffer data) {
        return new String(Hex.encodeHex(data));
    }

    public static String encodeHexString(ByteBuffer data, boolean toLowerCase) {
        return new String(Hex.encodeHex(data, toLowerCase));
    }

    private static byte[] toByteArray(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return byteBuffer.array();
        }
        byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);
        return byteArray;
    }

    protected static int toDigit(char ch, int index) throws DecoderException {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new DecoderException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public Hex() {
        this.charset = DEFAULT_CHARSET;
    }

    public Hex(Charset charset) {
        this.charset = charset;
    }

    public Hex(String charsetName) {
        this(Charset.forName(charsetName));
    }

    @Override
    public byte[] decode(byte[] array) throws DecoderException {
        return Hex.decodeHex(new String(array, this.getCharset()).toCharArray());
    }

    public byte[] decode(ByteBuffer buffer) throws DecoderException {
        return Hex.decodeHex(new String(Hex.toByteArray(buffer), this.getCharset()).toCharArray());
    }

    @Override
    public Object decode(Object object) throws DecoderException {
        if (object instanceof String) {
            return this.decode(((String)object).toCharArray());
        }
        if (object instanceof byte[]) {
            return this.decode((byte[])object);
        }
        if (object instanceof ByteBuffer) {
            return this.decode((ByteBuffer)object);
        }
        try {
            return Hex.decodeHex((char[])object);
        } catch (ClassCastException e) {
            throw new DecoderException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] encode(byte[] array) {
        return Hex.encodeHexString(array).getBytes(this.getCharset());
    }

    public byte[] encode(ByteBuffer array) {
        return Hex.encodeHexString(array).getBytes(this.getCharset());
    }

    @Override
    public Object encode(Object object) throws EncoderException {
        byte[] byteArray;
        if (object instanceof String) {
            byteArray = ((String)object).getBytes(this.getCharset());
        } else if (object instanceof ByteBuffer) {
            byteArray = Hex.toByteArray((ByteBuffer)object);
        } else {
            try {
                byteArray = (byte[])object;
            } catch (ClassCastException e) {
                throw new EncoderException(e.getMessage(), e);
            }
        }
        return Hex.encodeHex(byteArray);
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getCharsetName() {
        return this.charset.name();
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }
}

