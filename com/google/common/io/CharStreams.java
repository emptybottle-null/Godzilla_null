/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.AppendableWriter;
import com.google.common.io.LineProcessor;
import com.google.common.io.LineReader;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@GwtIncompatible
public final class CharStreams {
    private static final int DEFAULT_BUF_SIZE = 2048;

    static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    private CharStreams() {
    }

    @CanIgnoreReturnValue
    public static long copy(Readable from, Appendable to) throws IOException {
        if (from instanceof Reader) {
            if (to instanceof StringBuilder) {
                return CharStreams.copyReaderToBuilder((Reader)from, (StringBuilder)to);
            }
            return CharStreams.copyReaderToWriter((Reader)from, CharStreams.asWriter(to));
        }
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        long total = 0L;
        CharBuffer buf = CharStreams.createBuffer();
        while (from.read(buf) != -1) {
            buf.flip();
            to.append(buf);
            total += (long)buf.remaining();
            buf.clear();
        }
        return total;
    }

    @CanIgnoreReturnValue
    static long copyReaderToBuilder(Reader from, StringBuilder to) throws IOException {
        int nRead;
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[2048];
        long total = 0L;
        while ((nRead = from.read(buf)) != -1) {
            to.append(buf, 0, nRead);
            total += (long)nRead;
        }
        return total;
    }

    @CanIgnoreReturnValue
    static long copyReaderToWriter(Reader from, Writer to) throws IOException {
        int nRead;
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[2048];
        long total = 0L;
        while ((nRead = from.read(buf)) != -1) {
            to.write(buf, 0, nRead);
            total += (long)nRead;
        }
        return total;
    }

    public static String toString(Readable r) throws IOException {
        return CharStreams.toStringBuilder(r).toString();
    }

    private static StringBuilder toStringBuilder(Readable r) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (r instanceof Reader) {
            CharStreams.copyReaderToBuilder((Reader)r, sb);
        } else {
            CharStreams.copy(r, sb);
        }
        return sb;
    }

    @Beta
    public static List<String> readLines(Readable r) throws IOException {
        String line;
        ArrayList<String> result = new ArrayList<String>();
        LineReader lineReader = new LineReader(r);
        while ((line = lineReader.readLine()) != null) {
            result.add(line);
        }
        return result;
    }

    @Beta
    @CanIgnoreReturnValue
    public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
        String line;
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(processor);
        LineReader lineReader = new LineReader(readable);
        while ((line = lineReader.readLine()) != null && processor.processLine(line)) {
        }
        return processor.getResult();
    }

    @Beta
    @CanIgnoreReturnValue
    public static long exhaust(Readable readable) throws IOException {
        long read;
        long total = 0L;
        CharBuffer buf = CharStreams.createBuffer();
        while ((read = (long)readable.read(buf)) != -1L) {
            total += read;
            buf.clear();
        }
        return total;
    }

    @Beta
    public static void skipFully(Reader reader, long n) throws IOException {
        Preconditions.checkNotNull(reader);
        while (n > 0L) {
            long amt = reader.skip(n);
            if (amt == 0L) {
                throw new EOFException();
            }
            n -= amt;
        }
    }

    @Beta
    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    @Beta
    public static Writer asWriter(Appendable target) {
        if (target instanceof Writer) {
            return (Writer)target;
        }
        return new AppendableWriter(target);
    }

    private static final class NullWriter
    extends Writer {
        private static final NullWriter INSTANCE = new NullWriter();

        private NullWriter() {
        }

        @Override
        public void write(int c) {
        }

        @Override
        public void write(char[] cbuf) {
            Preconditions.checkNotNull(cbuf);
        }

        @Override
        public void write(char[] cbuf, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
        }

        @Override
        public void write(String str) {
            Preconditions.checkNotNull(str);
        }

        @Override
        public void write(String str, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, str.length());
        }

        @Override
        public Writer append(CharSequence csq) {
            Preconditions.checkNotNull(csq);
            return this;
        }

        @Override
        public Writer append(CharSequence csq, int start, int end) {
            Preconditions.checkPositionIndexes(start, end, csq.length());
            return this;
        }

        @Override
        public Writer append(char c) {
            return this;
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }
    }
}

