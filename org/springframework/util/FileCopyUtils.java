/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

public abstract class FileCopyUtils {
    public static final int BUFFER_SIZE = 4096;

    public static int copy(File in, File out) throws IOException {
        Assert.notNull((Object)in, "No input File specified");
        Assert.notNull((Object)out, "No output File specified");
        return FileCopyUtils.copy(Files.newInputStream(in.toPath(), new OpenOption[0]), Files.newOutputStream(out.toPath(), new OpenOption[0]));
    }

    public static void copy(byte[] in, File out) throws IOException {
        Assert.notNull((Object)in, "No input byte array specified");
        Assert.notNull((Object)out, "No output File specified");
        FileCopyUtils.copy(new ByteArrayInputStream(in), Files.newOutputStream(out.toPath(), new OpenOption[0]));
    }

    public static byte[] copyToByteArray(File in) throws IOException {
        Assert.notNull((Object)in, "No input File specified");
        return FileCopyUtils.copyToByteArray(Files.newInputStream(in.toPath(), new OpenOption[0]));
    }

    public static int copy(InputStream in, OutputStream out) throws IOException {
        Assert.notNull((Object)in, "No InputStream specified");
        Assert.notNull((Object)out, "No OutputStream specified");
        try {
            int n = StreamUtils.copy(in, out);
            return n;
        } finally {
            FileCopyUtils.close(in);
            FileCopyUtils.close(out);
        }
    }

    public static void copy(byte[] in, OutputStream out) throws IOException {
        Assert.notNull((Object)in, "No input byte array specified");
        Assert.notNull((Object)out, "No OutputStream specified");
        try {
            out.write(in);
        } finally {
            FileCopyUtils.close(out);
        }
    }

    public static byte[] copyToByteArray(@Nullable InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        FileCopyUtils.copy(in, (OutputStream)out);
        return out.toByteArray();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static int copy(Reader in, Writer out) throws IOException {
        Assert.notNull((Object)in, "No Reader specified");
        Assert.notNull((Object)out, "No Writer specified");
        try {
            int charsRead;
            int charCount = 0;
            char[] buffer = new char[4096];
            while ((charsRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, charsRead);
                charCount += charsRead;
            }
            out.flush();
            int n = charCount;
            return n;
        } finally {
            FileCopyUtils.close(in);
            FileCopyUtils.close(out);
        }
    }

    public static void copy(String in, Writer out) throws IOException {
        Assert.notNull((Object)in, "No input String specified");
        Assert.notNull((Object)out, "No Writer specified");
        try {
            out.write(in);
        } finally {
            FileCopyUtils.close(out);
        }
    }

    public static String copyToString(@Nullable Reader in) throws IOException {
        if (in == null) {
            return "";
        }
        StringWriter out = new StringWriter(4096);
        FileCopyUtils.copy(in, (Writer)out);
        return out.toString();
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException iOException) {
            // empty catch block
        }
    }
}

