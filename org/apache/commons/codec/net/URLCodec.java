/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.net;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.net.Utils;

public class URLCodec
implements BinaryEncoder,
BinaryDecoder,
StringEncoder,
StringDecoder {
    @Deprecated
    protected volatile String charset;
    protected static final byte ESCAPE_CHAR = 37;
    @Deprecated
    protected static final BitSet WWW_FORM_URL;
    private static final BitSet WWW_FORM_URL_SAFE;

    public URLCodec() {
        this("UTF-8");
    }

    public URLCodec(String charset) {
        this.charset = charset;
    }

    public static final byte[] encodeUrl(BitSet urlsafe, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        if (urlsafe == null) {
            urlsafe = WWW_FORM_URL_SAFE;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        for (int n : bytes) {
            int b = n;
            if (b < 0) {
                b = 256 + b;
            }
            if (urlsafe.get(b)) {
                if (b == 32) {
                    b = 43;
                }
                buffer.write(b);
                continue;
            }
            buffer.write(37);
            char hex1 = Utils.hexDigit(b >> 4);
            char hex2 = Utils.hexDigit(b);
            buffer.write(hex1);
            buffer.write(hex2);
        }
        return buffer.toByteArray();
    }

    public static final byte[] decodeUrl(byte[] bytes) throws DecoderException {
        if (bytes == null) {
            return null;
        }
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; ++i) {
            byte b = bytes[i];
            if (b == 43) {
                buffer.write(32);
                continue;
            }
            if (b == 37) {
                try {
                    int u = Utils.digit16(bytes[++i]);
                    int l = Utils.digit16(bytes[++i]);
                    buffer.write((char)((u << 4) + l));
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DecoderException("Invalid URL encoding: ", e);
                }
            }
            buffer.write(b);
        }
        return buffer.toByteArray();
    }

    @Override
    public byte[] encode(byte[] bytes) {
        return URLCodec.encodeUrl(WWW_FORM_URL_SAFE, bytes);
    }

    @Override
    public byte[] decode(byte[] bytes) throws DecoderException {
        return URLCodec.decodeUrl(bytes);
    }

    public String encode(String str, String charsetName) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return StringUtils.newStringUsAscii(this.encode(str.getBytes(charsetName)));
    }

    @Override
    public String encode(String str) throws EncoderException {
        if (str == null) {
            return null;
        }
        try {
            return this.encode(str, this.getDefaultCharset());
        } catch (UnsupportedEncodingException e) {
            throw new EncoderException(e.getMessage(), e);
        }
    }

    public String decode(String str, String charsetName) throws DecoderException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(this.decode(StringUtils.getBytesUsAscii(str)), charsetName);
    }

    @Override
    public String decode(String str) throws DecoderException {
        if (str == null) {
            return null;
        }
        try {
            return this.decode(str, this.getDefaultCharset());
        } catch (UnsupportedEncodingException e) {
            throw new DecoderException(e.getMessage(), e);
        }
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
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be URL encoded");
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
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be URL decoded");
    }

    public String getDefaultCharset() {
        return this.charset;
    }

    @Deprecated
    public String getEncoding() {
        return this.charset;
    }

    static {
        int i;
        WWW_FORM_URL_SAFE = new BitSet(256);
        for (i = 97; i <= 122; ++i) {
            WWW_FORM_URL_SAFE.set(i);
        }
        for (i = 65; i <= 90; ++i) {
            WWW_FORM_URL_SAFE.set(i);
        }
        for (i = 48; i <= 57; ++i) {
            WWW_FORM_URL_SAFE.set(i);
        }
        WWW_FORM_URL_SAFE.set(45);
        WWW_FORM_URL_SAFE.set(95);
        WWW_FORM_URL_SAFE.set(46);
        WWW_FORM_URL_SAFE.set(42);
        WWW_FORM_URL_SAFE.set(32);
        WWW_FORM_URL = (BitSet)WWW_FORM_URL_SAFE.clone();
    }
}

