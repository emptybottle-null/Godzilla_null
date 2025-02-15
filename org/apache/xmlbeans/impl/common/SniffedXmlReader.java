/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import org.apache.xmlbeans.impl.common.SniffedXmlInputStream;

public class SniffedXmlReader
extends BufferedReader {
    public static int MAX_SNIFFED_CHARS = 192;
    private static Charset dummy1 = Charset.forName("UTF-8");
    private static Charset dummy2 = Charset.forName("UTF-16");
    private static Charset dummy3 = Charset.forName("UTF-16BE");
    private static Charset dummy4 = Charset.forName("UTF-16LE");
    private static Charset dummy5 = Charset.forName("ISO-8859-1");
    private static Charset dummy6 = Charset.forName("US-ASCII");
    private static Charset dummy7 = Charset.forName("Cp1252");
    private String _encoding = this.sniffForXmlDecl();

    public SniffedXmlReader(Reader reader) throws IOException {
        super(reader);
    }

    private int readAsMuchAsPossible(char[] buf, int startAt, int len) throws IOException {
        int total;
        int count;
        for (total = 0; total < len && (count = this.read(buf, startAt + total, len - total)) >= 0; total += count) {
        }
        return total;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String sniffForXmlDecl() throws IOException {
        this.mark(MAX_SNIFFED_CHARS);
        try {
            char[] buf = new char[MAX_SNIFFED_CHARS];
            int limit = this.readAsMuchAsPossible(buf, 0, MAX_SNIFFED_CHARS);
            String string = SniffedXmlInputStream.extractXmlDeclEncoding(buf, 0, limit);
            return string;
        } finally {
            this.reset();
        }
    }

    public String getXmlEncoding() {
        return this._encoding;
    }
}

