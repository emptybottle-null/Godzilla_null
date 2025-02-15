/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.binary;

import java.util.Arrays;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

public abstract class BaseNCodec
implements BinaryEncoder,
BinaryDecoder {
    static final int EOF = -1;
    public static final int MIME_CHUNK_SIZE = 76;
    public static final int PEM_CHUNK_SIZE = 64;
    private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
    private static final int DEFAULT_BUFFER_SIZE = 8192;
    protected static final int MASK_8BITS = 255;
    protected static final byte PAD_DEFAULT = 61;
    @Deprecated
    protected final byte PAD = (byte)61;
    protected final byte pad;
    private final int unencodedBlockSize;
    private final int encodedBlockSize;
    protected final int lineLength;
    private final int chunkSeparatorLength;

    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength) {
        this(unencodedBlockSize, encodedBlockSize, lineLength, chunkSeparatorLength, 61);
    }

    protected BaseNCodec(int unencodedBlockSize, int encodedBlockSize, int lineLength, int chunkSeparatorLength, byte pad) {
        this.unencodedBlockSize = unencodedBlockSize;
        this.encodedBlockSize = encodedBlockSize;
        boolean useChunking = lineLength > 0 && chunkSeparatorLength > 0;
        this.lineLength = useChunking ? lineLength / encodedBlockSize * encodedBlockSize : 0;
        this.chunkSeparatorLength = chunkSeparatorLength;
        this.pad = pad;
    }

    boolean hasData(Context context) {
        return context.buffer != null;
    }

    int available(Context context) {
        return context.buffer != null ? context.pos - context.readPos : 0;
    }

    protected int getDefaultBufferSize() {
        return 8192;
    }

    private byte[] resizeBuffer(Context context) {
        if (context.buffer == null) {
            context.buffer = new byte[this.getDefaultBufferSize()];
            context.pos = 0;
            context.readPos = 0;
        } else {
            byte[] b = new byte[context.buffer.length * 2];
            System.arraycopy(context.buffer, 0, b, 0, context.buffer.length);
            context.buffer = b;
        }
        return context.buffer;
    }

    protected byte[] ensureBufferSize(int size, Context context) {
        if (context.buffer == null || context.buffer.length < context.pos + size) {
            return this.resizeBuffer(context);
        }
        return context.buffer;
    }

    int readResults(byte[] b, int bPos, int bAvail, Context context) {
        if (context.buffer != null) {
            int len = Math.min(this.available(context), bAvail);
            System.arraycopy(context.buffer, context.readPos, b, bPos, len);
            context.readPos += len;
            if (context.readPos >= context.pos) {
                context.buffer = null;
            }
            return len;
        }
        return context.eof ? -1 : 0;
    }

    protected static boolean isWhiteSpace(byte byteToCheck) {
        switch (byteToCheck) {
            case 9: 
            case 10: 
            case 13: 
            case 32: {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof byte[])) {
            throw new EncoderException("Parameter supplied to Base-N encode is not a byte[]");
        }
        return this.encode((byte[])obj);
    }

    public String encodeToString(byte[] pArray) {
        return StringUtils.newStringUtf8(this.encode(pArray));
    }

    public String encodeAsString(byte[] pArray) {
        return StringUtils.newStringUtf8(this.encode(pArray));
    }

    @Override
    public Object decode(Object obj) throws DecoderException {
        if (obj instanceof byte[]) {
            return this.decode((byte[])obj);
        }
        if (obj instanceof String) {
            return this.decode((String)obj);
        }
        throw new DecoderException("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] decode(String pArray) {
        return this.decode(StringUtils.getBytesUtf8(pArray));
    }

    @Override
    public byte[] decode(byte[] pArray) {
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        Context context = new Context();
        this.decode(pArray, 0, pArray.length, context);
        this.decode(pArray, 0, -1, context);
        byte[] result = new byte[context.pos];
        this.readResults(result, 0, result.length, context);
        return result;
    }

    @Override
    public byte[] encode(byte[] pArray) {
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        return this.encode(pArray, 0, pArray.length);
    }

    public byte[] encode(byte[] pArray, int offset, int length) {
        if (pArray == null || pArray.length == 0) {
            return pArray;
        }
        Context context = new Context();
        this.encode(pArray, offset, length, context);
        this.encode(pArray, offset, -1, context);
        byte[] buf = new byte[context.pos - context.readPos];
        this.readResults(buf, 0, buf.length, context);
        return buf;
    }

    abstract void encode(byte[] var1, int var2, int var3, Context var4);

    abstract void decode(byte[] var1, int var2, int var3, Context var4);

    protected abstract boolean isInAlphabet(byte var1);

    public boolean isInAlphabet(byte[] arrayOctet, boolean allowWSPad) {
        for (byte octet : arrayOctet) {
            if (this.isInAlphabet(octet) || allowWSPad && (octet == this.pad || BaseNCodec.isWhiteSpace(octet))) continue;
            return false;
        }
        return true;
    }

    public boolean isInAlphabet(String basen) {
        return this.isInAlphabet(StringUtils.getBytesUtf8(basen), true);
    }

    protected boolean containsAlphabetOrPad(byte[] arrayOctet) {
        if (arrayOctet == null) {
            return false;
        }
        for (byte element : arrayOctet) {
            if (this.pad != element && !this.isInAlphabet(element)) continue;
            return true;
        }
        return false;
    }

    public long getEncodedLength(byte[] pArray) {
        long len = (long)((pArray.length + this.unencodedBlockSize - 1) / this.unencodedBlockSize) * (long)this.encodedBlockSize;
        if (this.lineLength > 0) {
            len += (len + (long)this.lineLength - 1L) / (long)this.lineLength * (long)this.chunkSeparatorLength;
        }
        return len;
    }

    static class Context {
        int ibitWorkArea;
        long lbitWorkArea;
        byte[] buffer;
        int pos;
        int readPos;
        boolean eof;
        int currentLinePos;
        int modulus;

        Context() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", this.getClass().getSimpleName(), Arrays.toString(this.buffer), this.currentLinePos, this.eof, this.ibitWorkArea, this.lbitWorkArea, this.modulus, this.pos, this.readPos);
        }
    }
}

