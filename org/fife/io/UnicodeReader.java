/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.charset.Charset;

public class UnicodeReader
extends Reader {
    private InputStreamReader internalIn = null;
    private String encoding;
    private static final int BOM_SIZE = 4;

    public UnicodeReader(String file) throws IOException {
        this(new File(file));
    }

    public UnicodeReader(File file) throws IOException {
        this(new FileInputStream(file));
    }

    public UnicodeReader(File file, String defaultEncoding) throws IOException {
        this((InputStream)new FileInputStream(file), defaultEncoding);
    }

    public UnicodeReader(File file, Charset defaultCharset) throws IOException {
        this((InputStream)new FileInputStream(file), defaultCharset != null ? defaultCharset.name() : null);
    }

    public UnicodeReader(InputStream in) throws IOException {
        this(in, (String)null);
    }

    public UnicodeReader(InputStream in, String defaultEncoding) throws IOException {
        this.init(in, defaultEncoding);
    }

    public UnicodeReader(InputStream in, Charset defaultCharset) throws IOException {
        this.init(in, defaultCharset.name());
    }

    @Override
    public void close() throws IOException {
        this.internalIn.close();
    }

    public String getEncoding() {
        return this.encoding;
    }

    protected void init(InputStream in, String defaultEncoding) throws IOException {
        int unread;
        PushbackInputStream tempIn = new PushbackInputStream(in, 4);
        byte[] bom = new byte[4];
        int n = tempIn.read(bom, 0, bom.length);
        if (bom[0] == 0 && bom[1] == 0 && bom[2] == -2 && bom[3] == -1) {
            this.encoding = "UTF-32BE";
            unread = n - 4;
        } else if (n == 4 && bom[0] == -1 && bom[1] == -2 && bom[2] == 0 && bom[3] == 0) {
            this.encoding = "UTF-32LE";
            unread = n - 4;
        } else if (bom[0] == -17 && bom[1] == -69 && bom[2] == -65) {
            this.encoding = "UTF-8";
            unread = n - 3;
        } else if (bom[0] == -2 && bom[1] == -1) {
            this.encoding = "UTF-16BE";
            unread = n - 2;
        } else if (bom[0] == -1 && bom[1] == -2) {
            this.encoding = "UTF-16LE";
            unread = n - 2;
        } else {
            this.encoding = defaultEncoding;
            unread = n;
        }
        if (unread > 0) {
            tempIn.unread(bom, n - unread, unread);
        } else if (unread < -1) {
            tempIn.unread(bom, 0, 0);
        }
        if (this.encoding == null) {
            this.internalIn = new InputStreamReader(tempIn);
            this.encoding = this.internalIn.getEncoding();
        } else {
            this.internalIn = new InputStreamReader((InputStream)tempIn, this.encoding);
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return this.internalIn.read(cbuf, off, len);
    }
}

