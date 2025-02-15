/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.net.Utils;

public class QuotedPrintableCodec
implements BinaryEncoder,
BinaryDecoder,
StringEncoder,
StringDecoder {
    private final Charset charset;
    private final boolean strict;
    private static final BitSet PRINTABLE_CHARS;
    private static final byte ESCAPE_CHAR = 61;
    private static final byte TAB = 9;
    private static final byte SPACE = 32;
    private static final byte CR = 13;
    private static final byte LF = 10;
    private static final int SAFE_LENGTH = 73;

    public QuotedPrintableCodec() {
        this(Charsets.UTF_8, false);
    }

    public QuotedPrintableCodec(boolean strict) {
        this(Charsets.UTF_8, strict);
    }

    public QuotedPrintableCodec(Charset charset) {
        this(charset, false);
    }

    public QuotedPrintableCodec(Charset charset, boolean strict) {
        this.charset = charset;
        this.strict = strict;
    }

    public QuotedPrintableCodec(String charsetName) throws IllegalCharsetNameException, IllegalArgumentException, UnsupportedCharsetException {
        this(Charset.forName(charsetName), false);
    }

    private static final int encodeQuotedPrintable(int b, ByteArrayOutputStream buffer) {
        buffer.write(61);
        char hex1 = Utils.hexDigit(b >> 4);
        char hex2 = Utils.hexDigit(b);
        buffer.write(hex1);
        buffer.write(hex2);
        return 3;
    }

    private static int getUnsignedOctet(int index, byte[] bytes) {
        int b = bytes[index];
        if (b < 0) {
            b = 256 + b;
        }
        return b;
    }

    private static int encodeByte(int b, boolean encode, ByteArrayOutputStream buffer) {
        if (encode) {
            return QuotedPrintableCodec.encodeQuotedPrintable(b, buffer);
        }
        buffer.write(b);
        return 1;
    }

    private static boolean isWhitespace(int b) {
        return b == 32 || b == 9;
    }

    public static final byte[] encodeQuotedPrintable(BitSet printable, byte[] bytes) {
        return QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, false);
    }

    /*
     * WARNING - void declaration
     */
    public static final byte[] encodeQuotedPrintable(BitSet printable, byte[] bytes, boolean strict) {
        if (bytes == null) {
            return null;
        }
        if (printable == null) {
            printable = PRINTABLE_CHARS;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        if (strict) {
            void var7_11;
            boolean encode;
            int pos = 1;
            for (int i = 0; i < bytes.length - 3; ++i) {
                int b = QuotedPrintableCodec.getUnsignedOctet(i, bytes);
                if (pos < 73) {
                    pos += QuotedPrintableCodec.encodeByte(b, !printable.get(b), buffer);
                    continue;
                }
                QuotedPrintableCodec.encodeByte(b, !printable.get(b) || QuotedPrintableCodec.isWhitespace(b), buffer);
                buffer.write(61);
                buffer.write(13);
                buffer.write(10);
                pos = 1;
            }
            int b = QuotedPrintableCodec.getUnsignedOctet(bytes.length - 3, bytes);
            boolean bl = encode = !printable.get(b) || QuotedPrintableCodec.isWhitespace(b) && pos > 68;
            if ((pos += QuotedPrintableCodec.encodeByte(b, encode, buffer)) > 71) {
                buffer.write(61);
                buffer.write(13);
                buffer.write(10);
            }
            int n = bytes.length - 2;
            while (var7_11 < bytes.length) {
                b = QuotedPrintableCodec.getUnsignedOctet((int)var7_11, bytes);
                encode = !printable.get(b) || var7_11 > bytes.length - 2 && QuotedPrintableCodec.isWhitespace(b);
                QuotedPrintableCodec.encodeByte(b, encode, buffer);
                ++var7_11;
            }
        } else {
            for (int n : bytes) {
                int b = n;
                if (b < 0) {
                    b = 256 + b;
                }
                if (printable.get(b)) {
                    buffer.write(b);
                    continue;
                }
                QuotedPrintableCodec.encodeQuotedPrintable(b, buffer);
            }
        }
        return buffer.toByteArray();
    }

    public static final byte[] decodeQuotedPrintable(byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; ++i) {
            byte b = bytes[i];
            if (b == 61) {
                try {
                    if (bytes[++i] == 13) continue;
                    int u = Utils.digit16(bytes[i]);
                    int l = Utils.digit16(bytes[++i]);
                    buffer.write((char)((u << 4) + l));
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid quoted-printable encoding", e);
                }
            }
            if (b == 13 || b == 10) continue;
            buffer.write(b);
        }
        return buffer.toByteArray();
    }

    @Override
    public byte[] encode(byte[] bytes) {
        return QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, bytes, this.strict);
    }

    @Override
    public byte[] decode(byte[] bytes) throws DecoderException {
        return QuotedPrintableCodec.decodeQuotedPrintable(bytes);
    }

    @Override
    public String encode(String sourceStr) throws EncoderException {
        return this.encode(sourceStr, this.getCharset());
    }

    public String decode(String sourceStr, Charset sourceCharset) throws DecoderException {
        if (sourceStr == null) {
            return null;
        }
        return new String(this.decode(StringUtils.getBytesUsAscii(sourceStr)), sourceCharset);
    }

    public String decode(String sourceStr, String sourceCharset) throws DecoderException, UnsupportedEncodingException {
        if (sourceStr == null) {
            return null;
        }
        return new String(this.decode(StringUtils.getBytesUsAscii(sourceStr)), sourceCharset);
    }

    @Override
    public String decode(String sourceStr) throws DecoderException {
        return this.decode(sourceStr, this.getCharset());
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.encode((byte[])obj);
        }
        if (obj instanceof String) {
            return this.encode((String)obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be quoted-printable encoded");
    }

    @Override
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.decode((byte[])obj);
        }
        if (obj instanceof String) {
            return this.decode((String)obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be quoted-printable decoded");
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getDefaultCharset() {
        return this.charset.name();
    }

    public String encode(String sourceStr, Charset sourceCharset) {
        if (sourceStr == null) {
            return null;
        }
        return StringUtils.newStringUsAscii(this.encode(sourceStr.getBytes(sourceCharset)));
    }

    public String encode(String sourceStr, String sourceCharset) throws UnsupportedEncodingException {
        if (sourceStr == null) {
            return null;
        }
        return StringUtils.newStringUsAscii(this.encode(sourceStr.getBytes(sourceCharset)));
    }

    static {
        int i;
        PRINTABLE_CHARS = new BitSet(256);
        for (i = 33; i <= 60; ++i) {
            PRINTABLE_CHARS.set(i);
        }
        for (i = 62; i <= 126; ++i) {
            PRINTABLE_CHARS.set(i);
        }
        PRINTABLE_CHARS.set(9);
        PRINTABLE_CHARS.set(32);
    }
}

