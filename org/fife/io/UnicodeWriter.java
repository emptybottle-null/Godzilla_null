/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class UnicodeWriter
extends Writer {
    public static final String PROPERTY_WRITE_UTF8_BOM = "UnicodeWriter.writeUtf8BOM";
    private OutputStreamWriter internalOut;
    private static final byte[] UTF8_BOM = new byte[]{-17, -69, -65};
    private static final byte[] UTF16LE_BOM = new byte[]{-1, -2};
    private static final byte[] UTF16BE_BOM = new byte[]{-2, -1};
    private static final byte[] UTF32LE_BOM = new byte[]{-1, -2, 0, 0};
    private static final byte[] UTF32BE_BOM = new byte[]{0, 0, -2, -1};

    public UnicodeWriter(String fileName, Charset charset) throws IOException {
        this((OutputStream)new FileOutputStream(fileName), charset.name());
    }

    public UnicodeWriter(String fileName, String encoding) throws IOException {
        this((OutputStream)new FileOutputStream(fileName), encoding);
    }

    public UnicodeWriter(File file, Charset charset) throws IOException {
        this((OutputStream)new FileOutputStream(file), charset.name());
    }

    public UnicodeWriter(File file, String encoding) throws IOException {
        this((OutputStream)new FileOutputStream(file), encoding);
    }

    public UnicodeWriter(OutputStream out, Charset charset) throws IOException {
        this.init(out, charset.name());
    }

    public UnicodeWriter(OutputStream out, String encoding) throws IOException {
        this.init(out, encoding);
    }

    @Override
    public void close() throws IOException {
        this.internalOut.close();
    }

    @Override
    public void flush() throws IOException {
        this.internalOut.flush();
    }

    public String getEncoding() {
        return this.internalOut.getEncoding();
    }

    public static boolean getWriteUtf8BOM() {
        return Boolean.getBoolean(PROPERTY_WRITE_UTF8_BOM);
    }

    private void init(OutputStream out, String encoding) throws IOException {
        this.internalOut = new OutputStreamWriter(out, encoding);
        if ("UTF-8".equals(encoding)) {
            if (UnicodeWriter.getWriteUtf8BOM()) {
                out.write(UTF8_BOM, 0, UTF8_BOM.length);
            }
        } else if ("UTF-16LE".equals(encoding)) {
            out.write(UTF16LE_BOM, 0, UTF16LE_BOM.length);
        } else if ("UTF-16BE".equals(encoding)) {
            out.write(UTF16BE_BOM, 0, UTF16BE_BOM.length);
        } else if ("UTF-32LE".equals(encoding)) {
            out.write(UTF32LE_BOM, 0, UTF32LE_BOM.length);
        } else if ("UTF-32".equals(encoding) || "UTF-32BE".equals(encoding)) {
            out.write(UTF32BE_BOM, 0, UTF32BE_BOM.length);
        }
    }

    public static void setWriteUtf8BOM(boolean write) {
        System.setProperty(PROPERTY_WRITE_UTF8_BOM, Boolean.toString(write));
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.internalOut.write(cbuf, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        this.internalOut.write(c);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        this.internalOut.write(str, off, len);
    }
}

