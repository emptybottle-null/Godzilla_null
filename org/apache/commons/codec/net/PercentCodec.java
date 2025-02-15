/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.net;

import java.nio.ByteBuffer;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.Utils;

public class PercentCodec
implements BinaryEncoder,
BinaryDecoder {
    private static final byte ESCAPE_CHAR = 37;
    private final BitSet alwaysEncodeChars = new BitSet();
    private final boolean plusForSpace;
    private int alwaysEncodeCharsMin = Integer.MAX_VALUE;
    private int alwaysEncodeCharsMax = Integer.MIN_VALUE;

    public PercentCodec() {
        this.plusForSpace = false;
        this.insertAlwaysEncodeChar((byte)37);
    }

    public PercentCodec(byte[] alwaysEncodeChars, boolean plusForSpace) {
        this.plusForSpace = plusForSpace;
        this.insertAlwaysEncodeChars(alwaysEncodeChars);
    }

    private void insertAlwaysEncodeChars(byte[] alwaysEncodeCharsArray) {
        if (alwaysEncodeCharsArray != null) {
            for (byte b : alwaysEncodeCharsArray) {
                this.insertAlwaysEncodeChar(b);
            }
        }
        this.insertAlwaysEncodeChar((byte)37);
    }

    private void insertAlwaysEncodeChar(byte b) {
        this.alwaysEncodeChars.set(b);
        if (b < this.alwaysEncodeCharsMin) {
            this.alwaysEncodeCharsMin = b;
        }
        if (b > this.alwaysEncodeCharsMax) {
            this.alwaysEncodeCharsMax = b;
        }
    }

    @Override
    public byte[] encode(byte[] bytes) throws EncoderException {
        boolean willEncode;
        if (bytes == null) {
            return null;
        }
        int expectedEncodingBytes = this.expectedEncodingBytes(bytes);
        boolean bl = willEncode = expectedEncodingBytes != bytes.length;
        if (willEncode || this.plusForSpace && this.containsSpace(bytes)) {
            return this.doEncode(bytes, expectedEncodingBytes, willEncode);
        }
        return bytes;
    }

    private byte[] doEncode(byte[] bytes, int expectedLength, boolean willEncode) {
        ByteBuffer buffer = ByteBuffer.allocate(expectedLength);
        for (byte b : bytes) {
            if (willEncode && this.canEncode(b)) {
                byte bb = b;
                if (bb < 0) {
                    bb = (byte)(256 + bb);
                }
                char hex1 = Utils.hexDigit(bb >> 4);
                char hex2 = Utils.hexDigit(bb);
                buffer.put((byte)37);
                buffer.put((byte)hex1);
                buffer.put((byte)hex2);
                continue;
            }
            if (this.plusForSpace && b == 32) {
                buffer.put((byte)43);
                continue;
            }
            buffer.put(b);
        }
        return buffer.array();
    }

    private int expectedEncodingBytes(byte[] bytes) {
        int byteCount = 0;
        for (byte b : bytes) {
            byteCount += this.canEncode(b) ? 3 : 1;
        }
        return byteCount;
    }

    private boolean containsSpace(byte[] bytes) {
        for (byte b : bytes) {
            if (b != 32) continue;
            return true;
        }
        return false;
    }

    private boolean canEncode(byte c) {
        return !this.isAsciiChar(c) || this.inAlwaysEncodeCharsRange(c) && this.alwaysEncodeChars.get(c);
    }

    private boolean inAlwaysEncodeCharsRange(byte c) {
        return c >= this.alwaysEncodeCharsMin && c <= this.alwaysEncodeCharsMax;
    }

    private boolean isAsciiChar(byte c) {
        return c >= 0;
    }

    @Override
    public byte[] decode(byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(this.expectedDecodingBytes(bytes));
        for (int i = 0; i < bytes.length; ++i) {
            byte b = bytes[i];
            if (b == 37) {
                try {
                    int u = Utils.digit16(bytes[++i]);
                    int l = Utils.digit16(bytes[++i]);
                    buffer.put((byte)((u << 4) + l));
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid percent decoding: ", e);
                }
            }
            if (this.plusForSpace && b == 43) {
                buffer.put((byte)32);
                continue;
            }
            buffer.put(b);
        }
        return buffer.array();
    }

    private int expectedDecodingBytes(byte[] bytes) {
        int byteCount = 0;
        int i = 0;
        while (i < bytes.length) {
            byte b;
            i += (b = bytes[i]) == 37 ? 3 : 1;
            ++byteCount;
        }
        return byteCount;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.encode((byte[])obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent encoded");
    }

    @Override
    public Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            return this.decode((byte[])obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be Percent decoded");
    }
}

