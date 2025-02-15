/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class SniffedXmlInputStream
extends BufferedInputStream {
    public static int MAX_SNIFFED_BYTES = 192;
    private static Charset dummy1 = Charset.forName("UTF-8");
    private static Charset dummy2 = Charset.forName("UTF-16");
    private static Charset dummy3 = Charset.forName("UTF-16BE");
    private static Charset dummy4 = Charset.forName("UTF-16LE");
    private static Charset dummy5 = Charset.forName("ISO-8859-1");
    private static Charset dummy6 = Charset.forName("US-ASCII");
    private static Charset dummy7 = Charset.forName("Cp1252");
    private String _encoding = this.sniffFourBytes();
    private static char[] WHITESPACE = new char[]{' ', '\r', '\t', '\n'};
    private static char[] NOTNAME = new char[]{'=', ' ', '\r', '\t', '\n', '?', '>', '<', '\'', '\"'};

    public SniffedXmlInputStream(InputStream stream) throws IOException {
        super(stream);
        String encoding;
        if (this._encoding != null && this._encoding.equals("IBM037") && (encoding = this.sniffForXmlDecl(this._encoding)) != null) {
            this._encoding = encoding;
        }
        if (this._encoding == null) {
            this._encoding = this.sniffForXmlDecl("UTF-8");
        }
        if (this._encoding == null) {
            this._encoding = "UTF-8";
        }
    }

    private int readAsMuchAsPossible(byte[] buf, int startAt, int len) throws IOException {
        int total;
        int count;
        for (total = 0; total < len && (count = this.read(buf, startAt + total, len - total)) >= 0; total += count) {
        }
        return total;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String sniffFourBytes() throws IOException {
        this.mark(4);
        boolean skip = false;
        try {
            byte[] buf = new byte[4];
            if (this.readAsMuchAsPossible(buf, 0, 4) < 4) {
                String string = null;
                return string;
            }
            long result = 0xFF000000 & buf[0] << 24 | 0xFF0000 & buf[1] << 16 | 0xFF00 & buf[2] << 8 | 0xFF & buf[3];
            if (result == 65279L) {
                String string = "UCS-4";
                return string;
            }
            if (result == -131072L) {
                String string = "UCS-4";
                return string;
            }
            if (result == 60L) {
                String string = "UCS-4BE";
                return string;
            }
            if (result == 0x3C000000L) {
                String string = "UCS-4LE";
                return string;
            }
            if (result == 3932223L) {
                String string = "UTF-16BE";
                return string;
            }
            if (result == 1006649088L) {
                String string = "UTF-16LE";
                return string;
            }
            if (result == 1010792557L) {
                String string = null;
                return string;
            }
            if (result == 1282385812L) {
                String string = "IBM037";
                return string;
            }
            if ((result & 0xFFFFFFFFFFFF0000L) == -16842752L) {
                String string = "UTF-16";
                return string;
            }
            if ((result & 0xFFFFFFFFFFFF0000L) == -131072L) {
                String string = "UTF-16";
                return string;
            }
            if ((result & 0xFFFFFFFFFFFFFF00L) == -272908544L) {
                String string = "UTF-8";
                return string;
            }
            String string = null;
            return string;
        } finally {
            this.reset();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String sniffForXmlDecl(String encoding) throws IOException {
        this.mark(MAX_SNIFFED_BYTES);
        try {
            int limit;
            int count;
            byte[] bytebuf = new byte[MAX_SNIFFED_BYTES];
            int bytelimit = this.readAsMuchAsPossible(bytebuf, 0, MAX_SNIFFED_BYTES);
            Charset charset = Charset.forName(encoding);
            InputStreamReader reader = new InputStreamReader((InputStream)new ByteArrayInputStream(bytebuf, 0, bytelimit), charset);
            char[] buf = new char[bytelimit];
            for (limit = 0; limit < bytelimit && (count = ((Reader)reader).read(buf, limit, bytelimit - limit)) >= 0; limit += count) {
            }
            String string = SniffedXmlInputStream.extractXmlDeclEncoding(buf, 0, limit);
            return string;
        } finally {
            this.reset();
        }
    }

    public String getXmlEncoding() {
        return this._encoding;
    }

    static String extractXmlDeclEncoding(char[] buf, int offset, int size) {
        int limit = offset + size;
        int xmlpi = SniffedXmlInputStream.firstIndexOf("<?xml", buf, offset, limit);
        if (xmlpi >= 0) {
            int i = xmlpi + 5;
            ScannedAttribute attr = new ScannedAttribute();
            while (i < limit) {
                if ((i = SniffedXmlInputStream.scanAttribute(buf, i, limit, attr)) < 0) {
                    return null;
                }
                if (!attr.name.equals("encoding")) continue;
                return attr.value;
            }
        }
        return null;
    }

    private static int firstIndexOf(String s, char[] buf, int startAt, int limit) {
        assert (s.length() > 0);
        char[] lookFor = s.toCharArray();
        char firstchar = lookFor[0];
        limit -= lookFor.length;
        while (startAt < limit) {
            block5: {
                if (buf[startAt] == firstchar) {
                    for (int i = 1; i < lookFor.length; ++i) {
                        if (buf[startAt + i] == lookFor[i]) {
                            continue;
                        }
                        break block5;
                    }
                    return startAt;
                }
            }
            ++startAt;
        }
        return -1;
    }

    private static int nextNonmatchingByte(char[] lookFor, char[] buf, int startAt, int limit) {
        while (startAt < limit) {
            block3: {
                char thischar = buf[startAt];
                for (int i = 0; i < lookFor.length; ++i) {
                    if (thischar != lookFor[i]) {
                        continue;
                    }
                    break block3;
                }
                return startAt;
            }
            ++startAt;
        }
        return -1;
    }

    private static int nextMatchingByte(char[] lookFor, char[] buf, int startAt, int limit) {
        while (startAt < limit) {
            char thischar = buf[startAt];
            for (int i = 0; i < lookFor.length; ++i) {
                if (thischar != lookFor[i]) continue;
                return startAt;
            }
            ++startAt;
        }
        return -1;
    }

    private static int nextMatchingByte(char lookFor, char[] buf, int startAt, int limit) {
        while (startAt < limit) {
            if (buf[startAt] == lookFor) {
                return startAt;
            }
            ++startAt;
        }
        return -1;
    }

    private static int scanAttribute(char[] buf, int startAt, int limit, ScannedAttribute attr) {
        int nameStart = SniffedXmlInputStream.nextNonmatchingByte(WHITESPACE, buf, startAt, limit);
        if (nameStart < 0) {
            return -1;
        }
        int nameEnd = SniffedXmlInputStream.nextMatchingByte(NOTNAME, buf, nameStart, limit);
        if (nameEnd < 0) {
            return -1;
        }
        int equals = SniffedXmlInputStream.nextNonmatchingByte(WHITESPACE, buf, nameEnd, limit);
        if (equals < 0) {
            return -1;
        }
        if (buf[equals] != '=') {
            return -1;
        }
        int valQuote = SniffedXmlInputStream.nextNonmatchingByte(WHITESPACE, buf, equals + 1, limit);
        if (buf[valQuote] != '\'' && buf[valQuote] != '\"') {
            return -1;
        }
        int valEndquote = SniffedXmlInputStream.nextMatchingByte(buf[valQuote], buf, valQuote + 1, limit);
        if (valEndquote < 0) {
            return -1;
        }
        attr.name = new String(buf, nameStart, nameEnd - nameStart);
        attr.value = new String(buf, valQuote + 1, valEndquote - valQuote - 1);
        return valEndquote + 1;
    }

    private static class ScannedAttribute {
        public String name;
        public String value;

        private ScannedAttribute() {
        }
    }
}

