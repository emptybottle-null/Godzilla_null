/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.StandardLineSeparator;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.output.AppendableWriter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;

public class IOUtils {
    public static final int CR = 13;
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final int EOF = -1;
    public static final int LF = 10;
    @Deprecated
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_SEPARATOR_UNIX = StandardLineSeparator.LF.getString();
    public static final String LINE_SEPARATOR_WINDOWS = StandardLineSeparator.CRLF.getString();
    private static final ThreadLocal<byte[]> SKIP_BYTE_BUFFER = ThreadLocal.withInitial(IOUtils::byteArray);
    private static final ThreadLocal<char[]> SKIP_CHAR_BUFFER = ThreadLocal.withInitial(IOUtils::charArray);

    public static BufferedInputStream buffer(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream);
    }

    public static BufferedInputStream buffer(InputStream inputStream, int size) {
        Objects.requireNonNull(inputStream, "inputStream");
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream, size);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream) {
        Objects.requireNonNull(outputStream, "outputStream");
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream, int size) {
        Objects.requireNonNull(outputStream, "outputStream");
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream, size);
    }

    public static BufferedReader buffer(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

    public static BufferedReader buffer(Reader reader, int size) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, size);
    }

    public static BufferedWriter buffer(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer);
    }

    public static BufferedWriter buffer(Writer writer, int size) {
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer, size);
    }

    public static byte[] byteArray() {
        return IOUtils.byteArray(8192);
    }

    public static byte[] byteArray(int size) {
        return new byte[size];
    }

    private static char[] charArray() {
        return IOUtils.charArray(8192);
    }

    private static char[] charArray(int size) {
        return new char[size];
    }

    public static void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    public static void close(Closeable ... closeables) throws IOException {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                IOUtils.close(closeable);
            }
        }
    }

    public static void close(Closeable closeable, IOConsumer<IOException> consumer) throws IOException {
        block3: {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    if (consumer == null) break block3;
                    consumer.accept(e);
                }
            }
        }
    }

    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection)conn).disconnect();
        }
    }

    public static void closeQuietly(Closeable closeable) {
        IOUtils.closeQuietly(closeable, (Consumer<IOException>)null);
    }

    public static void closeQuietly(Closeable ... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            IOUtils.closeQuietly(closeable);
        }
    }

    public static void closeQuietly(Closeable closeable, Consumer<IOException> consumer) {
        block3: {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    if (consumer == null) break block3;
                    consumer.accept(e);
                }
            }
        }
    }

    public static void closeQuietly(InputStream input) {
        IOUtils.closeQuietly((Closeable)input);
    }

    public static void closeQuietly(OutputStream output) {
        IOUtils.closeQuietly((Closeable)output);
    }

    public static void closeQuietly(Reader reader) {
        IOUtils.closeQuietly((Closeable)reader);
    }

    public static void closeQuietly(Selector selector) {
        IOUtils.closeQuietly((Closeable)selector);
    }

    public static void closeQuietly(ServerSocket serverSocket) {
        IOUtils.closeQuietly((Closeable)serverSocket);
    }

    public static void closeQuietly(Socket socket) {
        IOUtils.closeQuietly((Closeable)socket);
    }

    public static void closeQuietly(Writer writer) {
        IOUtils.closeQuietly((Closeable)writer);
    }

    public static long consume(InputStream input) throws IOException {
        return IOUtils.copyLarge(input, NullOutputStream.NULL_OUTPUT_STREAM, IOUtils.getByteArray());
    }

    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null || input2 == null) {
            return false;
        }
        byte[] array1 = IOUtils.getByteArray();
        byte[] array2 = IOUtils.byteArray();
        block0: while (true) {
            int pos1 = 0;
            int pos2 = 0;
            int index = 0;
            while (true) {
                if (index >= 8192) continue block0;
                if (pos1 == index) {
                    int count1;
                    while ((count1 = input1.read(array1, pos1, 8192 - pos1)) == 0) {
                    }
                    if (count1 == -1) {
                        return pos2 == index && input2.read() == -1;
                    }
                    pos1 += count1;
                }
                if (pos2 == index) {
                    int count2;
                    while ((count2 = input2.read(array2, pos2, 8192 - pos2)) == 0) {
                    }
                    if (count2 == -1) {
                        return pos1 == index && input1.read() == -1;
                    }
                    pos2 += count2;
                }
                if (array1[index] != array2[index]) {
                    return false;
                }
                ++index;
            }
            break;
        }
    }

    public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null || input2 == null) {
            return false;
        }
        char[] array1 = IOUtils.getCharArray();
        char[] array2 = IOUtils.charArray();
        block0: while (true) {
            int pos1 = 0;
            int pos2 = 0;
            int index = 0;
            while (true) {
                if (index >= 8192) continue block0;
                if (pos1 == index) {
                    int count1;
                    while ((count1 = input1.read(array1, pos1, 8192 - pos1)) == 0) {
                    }
                    if (count1 == -1) {
                        return pos2 == index && input2.read() == -1;
                    }
                    pos1 += count1;
                }
                if (pos2 == index) {
                    int count2;
                    while ((count2 = input2.read(array2, pos2, 8192 - pos2)) == 0) {
                    }
                    if (count2 == -1) {
                        return pos1 == index && input1.read() == -1;
                    }
                    pos2 += count2;
                }
                if (array1[index] != array2[index]) {
                    return false;
                }
                ++index;
            }
            break;
        }
    }

    public static boolean contentEqualsIgnoreEOL(Reader reader1, Reader reader2) throws IOException {
        if (reader1 == reader2) {
            return true;
        }
        if (reader1 == null ^ reader2 == null) {
            return false;
        }
        BufferedReader br1 = IOUtils.toBufferedReader(reader1);
        BufferedReader br2 = IOUtils.toBufferedReader(reader2);
        String line1 = br1.readLine();
        String line2 = br2.readLine();
        while (line1 != null && line1.equals(line2)) {
            line1 = br1.readLine();
            line2 = br2.readLine();
        }
        return Objects.equals(line1, line2);
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long count = IOUtils.copyLarge(inputStream, outputStream);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)count;
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
        return IOUtils.copyLarge(inputStream, outputStream, IOUtils.byteArray(bufferSize));
    }

    @Deprecated
    public static void copy(InputStream input, Writer writer) throws IOException {
        IOUtils.copy(input, writer, Charset.defaultCharset());
    }

    public static void copy(InputStream input, Writer writer, Charset inputCharset) throws IOException {
        InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(inputCharset));
        IOUtils.copy((Reader)reader, writer);
    }

    public static void copy(InputStream input, Writer writer, String inputCharsetName) throws IOException {
        IOUtils.copy(input, writer, Charsets.toCharset(inputCharsetName));
    }

    public static long copy(Reader reader, Appendable output) throws IOException {
        return IOUtils.copy(reader, output, CharBuffer.allocate(8192));
    }

    public static long copy(Reader reader, Appendable output, CharBuffer buffer) throws IOException {
        int n;
        long count = 0L;
        while (-1 != (n = reader.read(buffer))) {
            buffer.flip();
            output.append(buffer, 0, n);
            count += (long)n;
        }
        return count;
    }

    @Deprecated
    public static void copy(Reader reader, OutputStream output) throws IOException {
        IOUtils.copy(reader, output, Charset.defaultCharset());
    }

    public static void copy(Reader reader, OutputStream output, Charset outputCharset) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output, Charsets.toCharset(outputCharset));
        IOUtils.copy(reader, (Writer)writer);
        writer.flush();
    }

    public static void copy(Reader reader, OutputStream output, String outputCharsetName) throws IOException {
        IOUtils.copy(reader, output, Charsets.toCharset(outputCharsetName));
    }

    public static int copy(Reader reader, Writer writer) throws IOException {
        long count = IOUtils.copyLarge(reader, writer);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)count;
    }

    public static long copy(URL url, File file) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(Objects.requireNonNull(file, "file").toPath(), new OpenOption[0]);){
            long l = IOUtils.copy(url, outputStream);
            return l;
        }
    }

    public static long copy(URL url, OutputStream outputStream) throws IOException {
        try (InputStream inputStream = Objects.requireNonNull(url, "url").openStream();){
            long l = IOUtils.copyLarge(inputStream, outputStream);
            return l;
        }
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return IOUtils.copy(inputStream, outputStream, 8192);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] buffer) throws IOException {
        int n;
        Objects.requireNonNull(inputStream, "inputStream");
        Objects.requireNonNull(outputStream, "outputStream");
        long count = 0L;
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
            count += (long)n;
        }
        return count;
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) throws IOException {
        return IOUtils.copyLarge(input, output, inputOffset, length, IOUtils.getByteArray());
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte[] buffer) throws IOException {
        int read;
        int bufferLength;
        if (inputOffset > 0L) {
            IOUtils.skipFully(input, inputOffset);
        }
        if (length == 0L) {
            return 0L;
        }
        int bytesToRead = bufferLength = buffer.length;
        if (length > 0L && length < (long)bufferLength) {
            bytesToRead = (int)length;
        }
        long totalRead = 0L;
        while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += (long)read;
            if (length <= 0L) continue;
            bytesToRead = (int)Math.min(length - totalRead, (long)bufferLength);
        }
        return totalRead;
    }

    public static long copyLarge(Reader reader, Writer writer) throws IOException {
        return IOUtils.copyLarge(reader, writer, IOUtils.getCharArray());
    }

    public static long copyLarge(Reader reader, Writer writer, char[] buffer) throws IOException {
        int n;
        long count = 0L;
        while (-1 != (n = reader.read(buffer))) {
            writer.write(buffer, 0, n);
            count += (long)n;
        }
        return count;
    }

    public static long copyLarge(Reader reader, Writer writer, long inputOffset, long length) throws IOException {
        return IOUtils.copyLarge(reader, writer, inputOffset, length, IOUtils.getCharArray());
    }

    public static long copyLarge(Reader reader, Writer writer, long inputOffset, long length, char[] buffer) throws IOException {
        int read;
        if (inputOffset > 0L) {
            IOUtils.skipFully(reader, inputOffset);
        }
        if (length == 0L) {
            return 0L;
        }
        int bytesToRead = buffer.length;
        if (length > 0L && length < (long)buffer.length) {
            bytesToRead = (int)length;
        }
        long totalRead = 0L;
        while (bytesToRead > 0 && -1 != (read = reader.read(buffer, 0, bytesToRead))) {
            writer.write(buffer, 0, read);
            totalRead += (long)read;
            if (length <= 0L) continue;
            bytesToRead = (int)Math.min(length - totalRead, (long)buffer.length);
        }
        return totalRead;
    }

    static byte[] getByteArray() {
        return SKIP_BYTE_BUFFER.get();
    }

    static char[] getCharArray() {
        return SKIP_CHAR_BUFFER.get();
    }

    public static int length(byte[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(char[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(CharSequence csq) {
        return csq == null ? 0 : csq.length();
    }

    public static int length(Object[] array) {
        return array == null ? 0 : array.length;
    }

    public static LineIterator lineIterator(InputStream input, Charset charset) {
        return new LineIterator(new InputStreamReader(input, Charsets.toCharset(charset)));
    }

    public static LineIterator lineIterator(InputStream input, String charsetName) {
        return IOUtils.lineIterator(input, Charsets.toCharset(charsetName));
    }

    public static LineIterator lineIterator(Reader reader) {
        return new LineIterator(reader);
    }

    public static int read(InputStream input, byte[] buffer) throws IOException {
        return IOUtils.read(input, buffer, 0, buffer.length);
    }

    public static int read(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        int location;
        int remaining;
        int count;
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        for (remaining = length; remaining > 0 && -1 != (count = input.read(buffer, offset + (location = length - remaining), remaining)); remaining -= count) {
        }
        return length - remaining;
    }

    public static int read(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int count;
        int length = buffer.remaining();
        while (buffer.remaining() > 0 && -1 != (count = input.read(buffer))) {
        }
        return length - buffer.remaining();
    }

    public static int read(Reader reader, char[] buffer) throws IOException {
        return IOUtils.read(reader, buffer, 0, buffer.length);
    }

    public static int read(Reader reader, char[] buffer, int offset, int length) throws IOException {
        int location;
        int remaining;
        int count;
        if (length < 0) {
            throw new IllegalArgumentException("Length must not be negative: " + length);
        }
        for (remaining = length; remaining > 0 && -1 != (count = reader.read(buffer, offset + (location = length - remaining), remaining)); remaining -= count) {
        }
        return length - remaining;
    }

    public static void readFully(InputStream input, byte[] buffer) throws IOException {
        IOUtils.readFully(input, buffer, 0, buffer.length);
    }

    public static void readFully(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        int actual = IOUtils.read(input, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    public static byte[] readFully(InputStream input, int length) throws IOException {
        byte[] buffer = IOUtils.byteArray(length);
        IOUtils.readFully(input, buffer, 0, buffer.length);
        return buffer;
    }

    public static void readFully(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int expected = buffer.remaining();
        int actual = IOUtils.read(input, buffer);
        if (actual != expected) {
            throw new EOFException("Length to read: " + expected + " actual: " + actual);
        }
    }

    public static void readFully(Reader reader, char[] buffer) throws IOException {
        IOUtils.readFully(reader, buffer, 0, buffer.length);
    }

    public static void readFully(Reader reader, char[] buffer, int offset, int length) throws IOException {
        int actual = IOUtils.read(reader, buffer, offset, length);
        if (actual != length) {
            throw new EOFException("Length to read: " + length + " actual: " + actual);
        }
    }

    @Deprecated
    public static List<String> readLines(InputStream input) throws IOException {
        return IOUtils.readLines(input, Charset.defaultCharset());
    }

    public static List<String> readLines(InputStream input, Charset charset) throws IOException {
        InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(charset));
        return IOUtils.readLines(reader);
    }

    public static List<String> readLines(InputStream input, String charsetName) throws IOException {
        return IOUtils.readLines(input, Charsets.toCharset(charsetName));
    }

    public static List<String> readLines(Reader reader) throws IOException {
        String line;
        BufferedReader bufReader = IOUtils.toBufferedReader(reader);
        ArrayList<String> list = new ArrayList<String>();
        while ((line = bufReader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static byte[] resourceToByteArray(String name) throws IOException {
        return IOUtils.resourceToByteArray(name, null);
    }

    public static byte[] resourceToByteArray(String name, ClassLoader classLoader) throws IOException {
        return IOUtils.toByteArray(IOUtils.resourceToURL(name, classLoader));
    }

    public static String resourceToString(String name, Charset charset) throws IOException {
        return IOUtils.resourceToString(name, charset, null);
    }

    public static String resourceToString(String name, Charset charset, ClassLoader classLoader) throws IOException {
        return IOUtils.toString(IOUtils.resourceToURL(name, classLoader), charset);
    }

    public static URL resourceToURL(String name) throws IOException {
        return IOUtils.resourceToURL(name, null);
    }

    public static URL resourceToURL(String name, ClassLoader classLoader) throws IOException {
        URL resource;
        URL uRL = resource = classLoader == null ? IOUtils.class.getResource(name) : classLoader.getResource(name);
        if (resource == null) {
            throw new IOException("Resource not found: " + name);
        }
        return resource;
    }

    public static long skip(InputStream input, long toSkip) throws IOException {
        byte[] byteArray;
        long remain;
        long n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        for (remain = toSkip; remain > 0L && (n = (long)input.read(byteArray = IOUtils.getByteArray(), 0, (int)Math.min(remain, (long)byteArray.length))) >= 0L; remain -= n) {
        }
        return toSkip - remain;
    }

    public static long skip(ReadableByteChannel input, long toSkip) throws IOException {
        long remain;
        int n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        ByteBuffer skipByteBuffer = ByteBuffer.allocate((int)Math.min(toSkip, 8192L));
        for (remain = toSkip; remain > 0L; remain -= (long)n) {
            skipByteBuffer.position(0);
            skipByteBuffer.limit((int)Math.min(remain, 8192L));
            n = input.read(skipByteBuffer);
            if (n == -1) break;
        }
        return toSkip - remain;
    }

    public static long skip(Reader reader, long toSkip) throws IOException {
        char[] charArray;
        long remain;
        long n;
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        }
        for (remain = toSkip; remain > 0L && (n = (long)reader.read(charArray = IOUtils.getCharArray(), 0, (int)Math.min(remain, (long)charArray.length))) >= 0L; remain -= n) {
        }
        return toSkip - remain;
    }

    public static void skipFully(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = IOUtils.skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip);
        }
        long skipped = IOUtils.skip(input, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static void skipFully(Reader reader, long toSkip) throws IOException {
        long skipped = IOUtils.skip(reader, toSkip);
        if (skipped != toSkip) {
            throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped);
        }
    }

    public static InputStream toBufferedInputStream(InputStream input) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input);
    }

    public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input, size);
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(Reader reader, int size) {
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader, size);
    }

    /*
     * Exception decompiling
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * 
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
         *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
         *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
         *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:538)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
         *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
         *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
         *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
         *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
         *     at org.benf.cfr.reader.Main.main(Main.java:54)
         *     at async.DecompilerRunnable.cfrDecompilation(DecompilerRunnable.java:348)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:309)
         *     at async.DecompilerRunnable.call(DecompilerRunnable.java:31)
         *     at java.util.concurrent.FutureTask.run(FutureTask.java:266)
         *     at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
         *     at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
         *     at java.lang.Thread.run(Thread.java:750)
         */
        throw new IllegalStateException("Decompilation failed");
    }

    public static byte[] toByteArray(InputStream input, int size) throws IOException {
        int offset;
        int read;
        if (size < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size);
        }
        if (size == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] data = IOUtils.byteArray(size);
        for (offset = 0; offset < size && (read = input.read(data, offset, size - offset)) != -1; offset += read) {
        }
        if (offset != size) {
            throw new IOException("Unexpected read size, current: " + offset + ", expected: " + size);
        }
        return data;
    }

    public static byte[] toByteArray(InputStream input, long size) throws IOException {
        if (size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size);
        }
        return IOUtils.toByteArray(input, (int)size);
    }

    @Deprecated
    public static byte[] toByteArray(Reader reader) throws IOException {
        return IOUtils.toByteArray(reader, Charset.defaultCharset());
    }

    public static byte[] toByteArray(Reader reader, Charset charset) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream();){
            IOUtils.copy(reader, (OutputStream)output, charset);
            byte[] byArray = output.toByteArray();
            return byArray;
        }
    }

    public static byte[] toByteArray(Reader reader, String charsetName) throws IOException {
        return IOUtils.toByteArray(reader, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static byte[] toByteArray(String input) {
        return input.getBytes(Charset.defaultCharset());
    }

    public static byte[] toByteArray(URI uri) throws IOException {
        return IOUtils.toByteArray(uri.toURL());
    }

    public static byte[] toByteArray(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        try {
            byte[] byArray = IOUtils.toByteArray(conn);
            return byArray;
        } finally {
            IOUtils.close(conn);
        }
    }

    public static byte[] toByteArray(URLConnection urlConn) throws IOException {
        try (InputStream inputStream = urlConn.getInputStream();){
            byte[] byArray = IOUtils.toByteArray(inputStream);
            return byArray;
        }
    }

    @Deprecated
    public static char[] toCharArray(InputStream inputStream) throws IOException {
        return IOUtils.toCharArray(inputStream, Charset.defaultCharset());
    }

    public static char[] toCharArray(InputStream inputStream, Charset charset) throws IOException {
        CharArrayWriter writer = new CharArrayWriter();
        IOUtils.copy(inputStream, (Writer)writer, charset);
        return writer.toCharArray();
    }

    public static char[] toCharArray(InputStream inputStream, String charsetName) throws IOException {
        return IOUtils.toCharArray(inputStream, Charsets.toCharset(charsetName));
    }

    public static char[] toCharArray(Reader reader) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        IOUtils.copy(reader, (Writer)sw);
        return sw.toCharArray();
    }

    @Deprecated
    public static InputStream toInputStream(CharSequence input) {
        return IOUtils.toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(CharSequence input, Charset charset) {
        return IOUtils.toInputStream(input.toString(), charset);
    }

    public static InputStream toInputStream(CharSequence input, String charsetName) {
        return IOUtils.toInputStream(input, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static InputStream toInputStream(String input) {
        return IOUtils.toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(String input, Charset charset) {
        return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(charset)));
    }

    public static InputStream toInputStream(String input, String charsetName) {
        byte[] bytes = input.getBytes(Charsets.toCharset(charsetName));
        return new ByteArrayInputStream(bytes);
    }

    @Deprecated
    public static String toString(byte[] input) {
        return new String(input, Charset.defaultCharset());
    }

    public static String toString(byte[] input, String charsetName) {
        return new String(input, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static String toString(InputStream input) throws IOException {
        return IOUtils.toString(input, Charset.defaultCharset());
    }

    public static String toString(InputStream input, Charset charset) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter();){
            IOUtils.copy(input, (Writer)sw, charset);
            String string = sw.toString();
            return string;
        }
    }

    public static String toString(InputStream input, String charsetName) throws IOException {
        return IOUtils.toString(input, Charsets.toCharset(charsetName));
    }

    public static String toString(Reader reader) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter();){
            IOUtils.copy(reader, (Writer)sw);
            String string = sw.toString();
            return string;
        }
    }

    @Deprecated
    public static String toString(URI uri) throws IOException {
        return IOUtils.toString(uri, Charset.defaultCharset());
    }

    public static String toString(URI uri, Charset encoding) throws IOException {
        return IOUtils.toString(uri.toURL(), Charsets.toCharset(encoding));
    }

    public static String toString(URI uri, String charsetName) throws IOException {
        return IOUtils.toString(uri, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static String toString(URL url) throws IOException {
        return IOUtils.toString(url, Charset.defaultCharset());
    }

    public static String toString(URL url, Charset encoding) throws IOException {
        try (InputStream inputStream = url.openStream();){
            String string = IOUtils.toString(inputStream, encoding);
            return string;
        }
    }

    public static String toString(URL url, String charsetName) throws IOException {
        return IOUtils.toString(url, Charsets.toCharset(charsetName));
    }

    public static void write(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    @Deprecated
    public static void write(byte[] data, Writer writer) throws IOException {
        IOUtils.write(data, writer, Charset.defaultCharset());
    }

    public static void write(byte[] data, Writer writer, Charset charset) throws IOException {
        if (data != null) {
            writer.write(new String(data, Charsets.toCharset(charset)));
        }
    }

    public static void write(byte[] data, Writer writer, String charsetName) throws IOException {
        IOUtils.write(data, writer, Charsets.toCharset(charsetName));
    }

    @Deprecated
    public static void write(char[] data, OutputStream output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(char[] data, OutputStream output, Charset charset) throws IOException {
        if (data != null) {
            output.write(new String(data).getBytes(Charsets.toCharset(charset)));
        }
    }

    public static void write(char[] data, OutputStream output, String charsetName) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(charsetName));
    }

    public static void write(char[] data, Writer writer) throws IOException {
        if (data != null) {
            writer.write(data);
        }
    }

    @Deprecated
    public static void write(CharSequence data, OutputStream output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(CharSequence data, OutputStream output, Charset charset) throws IOException {
        if (data != null) {
            IOUtils.write(data.toString(), output, charset);
        }
    }

    public static void write(CharSequence data, OutputStream output, String charsetName) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(charsetName));
    }

    public static void write(CharSequence data, Writer writer) throws IOException {
        if (data != null) {
            IOUtils.write(data.toString(), writer);
        }
    }

    @Deprecated
    public static void write(String data, OutputStream output) throws IOException {
        IOUtils.write(data, output, Charset.defaultCharset());
    }

    public static void write(String data, OutputStream output, Charset charset) throws IOException {
        if (data != null) {
            output.write(data.getBytes(Charsets.toCharset(charset)));
        }
    }

    public static void write(String data, OutputStream output, String charsetName) throws IOException {
        IOUtils.write(data, output, Charsets.toCharset(charsetName));
    }

    public static void write(String data, Writer writer) throws IOException {
        if (data != null) {
            writer.write(data);
        }
    }

    @Deprecated
    public static void write(StringBuffer data, OutputStream output) throws IOException {
        IOUtils.write(data, output, (String)null);
    }

    @Deprecated
    public static void write(StringBuffer data, OutputStream output, String charsetName) throws IOException {
        if (data != null) {
            output.write(data.toString().getBytes(Charsets.toCharset(charsetName)));
        }
    }

    @Deprecated
    public static void write(StringBuffer data, Writer writer) throws IOException {
        if (data != null) {
            writer.write(data.toString());
        }
    }

    public static void writeChunked(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            int bytes = data.length;
            int offset = 0;
            while (bytes > 0) {
                int chunk = Math.min(bytes, 8192);
                output.write(data, offset, chunk);
                bytes -= chunk;
                offset += chunk;
            }
        }
    }

    public static void writeChunked(char[] data, Writer writer) throws IOException {
        if (data != null) {
            int bytes = data.length;
            int offset = 0;
            while (bytes > 0) {
                int chunk = Math.min(bytes, 8192);
                writer.write(data, offset, chunk);
                bytes -= chunk;
                offset += chunk;
            }
        }
    }

    @Deprecated
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) throws IOException {
        IOUtils.writeLines(lines, lineEnding, output, Charset.defaultCharset());
    }

    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset charset) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = System.lineSeparator();
        }
        Charset cs = Charsets.toCharset(charset);
        for (Object line : lines) {
            if (line != null) {
                output.write(line.toString().getBytes(cs));
            }
            output.write(lineEnding.getBytes(cs));
        }
    }

    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String charsetName) throws IOException {
        IOUtils.writeLines(lines, lineEnding, output, Charsets.toCharset(charsetName));
    }

    public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
        if (lines == null) {
            return;
        }
        if (lineEnding == null) {
            lineEnding = System.lineSeparator();
        }
        for (Object line : lines) {
            if (line != null) {
                writer.write(line.toString());
            }
            writer.write(lineEnding);
        }
    }

    public static Writer writer(Appendable appendable) {
        Objects.requireNonNull(appendable, "appendable");
        if (appendable instanceof Writer) {
            return (Writer)appendable;
        }
        if (appendable instanceof StringBuilder) {
            return new StringBuilderWriter((StringBuilder)appendable);
        }
        return new AppendableWriter<Appendable>(appendable);
    }

    private static /* synthetic */ OutputStream lambda$toByteArray$1(UnsynchronizedByteArrayOutputStream ubaOutput, ThresholdingOutputStream os) throws IOException {
        return ubaOutput;
    }

    private static /* synthetic */ void lambda$toByteArray$0(ThresholdingOutputStream os) throws IOException {
        throw new IllegalArgumentException(String.format("Cannot read more than %,d into a byte array", Integer.MAX_VALUE));
    }
}

