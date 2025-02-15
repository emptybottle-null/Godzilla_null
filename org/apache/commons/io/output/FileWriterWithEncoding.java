/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileWriterWithEncoding
extends Writer {
    private final Writer out;

    public FileWriterWithEncoding(String fileName, String charsetName) throws IOException {
        this(new File(fileName), charsetName, false);
    }

    public FileWriterWithEncoding(String fileName, String charsetName, boolean append) throws IOException {
        this(new File(fileName), charsetName, append);
    }

    public FileWriterWithEncoding(String fileName, Charset charset) throws IOException {
        this(new File(fileName), charset, false);
    }

    public FileWriterWithEncoding(String fileName, Charset charset, boolean append) throws IOException {
        this(new File(fileName), charset, append);
    }

    public FileWriterWithEncoding(String fileName, CharsetEncoder encoding) throws IOException {
        this(new File(fileName), encoding, false);
    }

    public FileWriterWithEncoding(String fileName, CharsetEncoder charsetEncoder, boolean append) throws IOException {
        this(new File(fileName), charsetEncoder, append);
    }

    public FileWriterWithEncoding(File file, String charsetName) throws IOException {
        this(file, charsetName, false);
    }

    public FileWriterWithEncoding(File file, String charsetName, boolean append) throws IOException {
        this.out = FileWriterWithEncoding.initWriter(file, charsetName, append);
    }

    public FileWriterWithEncoding(File file, Charset charset) throws IOException {
        this(file, charset, false);
    }

    public FileWriterWithEncoding(File file, Charset encoding, boolean append) throws IOException {
        this.out = FileWriterWithEncoding.initWriter(file, encoding, append);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder) throws IOException {
        this(file, charsetEncoder, false);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder, boolean append) throws IOException {
        this.out = FileWriterWithEncoding.initWriter(file, charsetEncoder, append);
    }

    private static Writer initWriter(File file, Object encoding, boolean append) throws IOException {
        Objects.requireNonNull(file, "file");
        Objects.requireNonNull(encoding, "encoding");
        OutputStream stream = null;
        boolean fileExistedAlready = file.exists();
        try {
            stream = Files.newOutputStream(file.toPath(), append ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
            if (encoding instanceof Charset) {
                return new OutputStreamWriter(stream, (Charset)encoding);
            }
            if (encoding instanceof CharsetEncoder) {
                return new OutputStreamWriter(stream, (CharsetEncoder)encoding);
            }
            return new OutputStreamWriter(stream, (String)encoding);
        } catch (IOException | RuntimeException ex) {
            try {
                IOUtils.close(stream);
            } catch (IOException e) {
                ex.addSuppressed(e);
            }
            if (!fileExistedAlready) {
                FileUtils.deleteQuietly(file);
            }
            throw ex;
        }
    }

    @Override
    public void write(int idx) throws IOException {
        this.out.write(idx);
    }

    @Override
    public void write(char[] chr) throws IOException {
        this.out.write(chr);
    }

    @Override
    public void write(char[] chr, int st, int end) throws IOException {
        this.out.write(chr, st, end);
    }

    @Override
    public void write(String str) throws IOException {
        this.out.write(str);
    }

    @Override
    public void write(String str, int st, int end) throws IOException {
        this.out.write(str, st, end);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }
}

