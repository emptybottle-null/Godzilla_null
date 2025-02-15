/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Streams;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSequenceReader;
import com.google.common.io.CharSink;
import com.google.common.io.CharStreams;
import com.google.common.io.Closer;
import com.google.common.io.LineProcessor;
import com.google.common.io.MultiReader;
import com.google.common.io.ReaderInputStream;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.MustBeClosed;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public abstract class CharSource {
    protected CharSource() {
    }

    @Beta
    public ByteSource asByteSource(Charset charset) {
        return new AsByteSource(charset);
    }

    public abstract Reader openStream() throws IOException;

    public BufferedReader openBufferedStream() throws IOException {
        Reader reader = this.openStream();
        return reader instanceof BufferedReader ? (BufferedReader)reader : new BufferedReader(reader);
    }

    @Beta
    @MustBeClosed
    public Stream<String> lines() throws IOException {
        BufferedReader reader = this.openBufferedStream();
        return (Stream)reader.lines().onClose(() -> {
            try {
                reader.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }

    @Beta
    public Optional<Long> lengthIfKnown() {
        return Optional.absent();
    }

    @Beta
    public long length() throws IOException {
        Optional<Long> lengthIfKnown = this.lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get();
        }
        try (Closer closer = Closer.create();){
            Reader reader = closer.register(this.openStream());
            long l = this.countBySkipping(reader);
            return l;
        }
    }

    private long countBySkipping(Reader reader) throws IOException {
        long read;
        long count = 0L;
        while ((read = reader.skip(Long.MAX_VALUE)) != 0L) {
            count += read;
        }
        return count;
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws IOException {
        Preconditions.checkNotNull(appendable);
        try (Closer closer = Closer.create();){
            Reader reader = closer.register(this.openStream());
            long l = CharStreams.copy(reader, appendable);
            return l;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(CharSink sink) throws IOException {
        Preconditions.checkNotNull(sink);
        try (Closer closer = Closer.create();){
            Reader reader = closer.register(this.openStream());
            Writer writer = closer.register(sink.openStream());
            long l = CharStreams.copy(reader, writer);
            return l;
        }
    }

    public String read() throws IOException {
        try (Closer closer = Closer.create();){
            Reader reader = closer.register(this.openStream());
            String string = CharStreams.toString(reader);
            return string;
        }
    }

    public @Nullable String readFirstLine() throws IOException {
        try (Closer closer = Closer.create();){
            BufferedReader reader = closer.register(this.openBufferedStream());
            String string = reader.readLine();
            return string;
        }
    }

    public ImmutableList<String> readLines() throws IOException {
        try (Closer closer = Closer.create();){
            String line;
            BufferedReader reader = closer.register(this.openBufferedStream());
            ArrayList<String> result = Lists.newArrayList();
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            ImmutableList<String> immutableList = ImmutableList.copyOf(result);
            return immutableList;
        }
    }

    @Beta
    @CanIgnoreReturnValue
    public <T> T readLines(LineProcessor<T> processor) throws IOException {
        Preconditions.checkNotNull(processor);
        try (Closer closer = Closer.create();){
            Reader reader = closer.register(this.openStream());
            T t = CharStreams.readLines(reader, processor);
            return t;
        }
    }

    @Beta
    public void forEachLine(Consumer<? super String> action) throws IOException {
        try (Stream<String> lines = this.lines();){
            lines.forEachOrdered(action);
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    public boolean isEmpty() throws IOException {
        Optional<Long> lengthIfKnown = this.lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get() == 0L;
        }
        try (Closer closer = Closer.create();){
            Reader reader = closer.register(this.openStream());
            boolean bl = reader.read() == -1;
            return bl;
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> sources) {
        return new ConcatenatedCharSource(sources);
    }

    public static CharSource concat(Iterator<? extends CharSource> sources) {
        return CharSource.concat(ImmutableList.copyOf(sources));
    }

    public static CharSource concat(CharSource ... sources) {
        return CharSource.concat(ImmutableList.copyOf(sources));
    }

    public static CharSource wrap(CharSequence charSequence) {
        return charSequence instanceof String ? new StringCharSource((String)charSequence) : new CharSequenceCharSource(charSequence);
    }

    public static CharSource empty() {
        return EmptyCharSource.INSTANCE;
    }

    private static final class ConcatenatedCharSource
    extends CharSource {
        private final Iterable<? extends CharSource> sources;

        ConcatenatedCharSource(Iterable<? extends CharSource> sources) {
            this.sources = Preconditions.checkNotNull(sources);
        }

        @Override
        public Reader openStream() throws IOException {
            return new MultiReader(this.sources.iterator());
        }

        @Override
        public boolean isEmpty() throws IOException {
            for (CharSource charSource : this.sources) {
                if (charSource.isEmpty()) continue;
                return false;
            }
            return true;
        }

        @Override
        public Optional<Long> lengthIfKnown() {
            long result = 0L;
            for (CharSource charSource : this.sources) {
                Optional<Long> lengthIfKnown = charSource.lengthIfKnown();
                if (!lengthIfKnown.isPresent()) {
                    return Optional.absent();
                }
                result += lengthIfKnown.get().longValue();
            }
            return Optional.of(result);
        }

        @Override
        public long length() throws IOException {
            long result = 0L;
            for (CharSource charSource : this.sources) {
                result += charSource.length();
            }
            return result;
        }

        public String toString() {
            return "CharSource.concat(" + this.sources + ")";
        }
    }

    private static final class EmptyCharSource
    extends StringCharSource {
        private static final EmptyCharSource INSTANCE = new EmptyCharSource();

        private EmptyCharSource() {
            super("");
        }

        @Override
        public String toString() {
            return "CharSource.empty()";
        }
    }

    private static class StringCharSource
    extends CharSequenceCharSource {
        protected StringCharSource(String seq) {
            super(seq);
        }

        @Override
        public Reader openStream() {
            return new StringReader((String)this.seq);
        }

        @Override
        public long copyTo(Appendable appendable) throws IOException {
            appendable.append(this.seq);
            return this.seq.length();
        }

        @Override
        public long copyTo(CharSink sink) throws IOException {
            Preconditions.checkNotNull(sink);
            try (Closer closer = Closer.create();){
                Writer writer = closer.register(sink.openStream());
                writer.write((String)this.seq);
                long l = this.seq.length();
                return l;
            }
        }
    }

    private static class CharSequenceCharSource
    extends CharSource {
        private static final Splitter LINE_SPLITTER = Splitter.onPattern("\r\n|\n|\r");
        protected final CharSequence seq;

        protected CharSequenceCharSource(CharSequence seq) {
            this.seq = Preconditions.checkNotNull(seq);
        }

        @Override
        public Reader openStream() {
            return new CharSequenceReader(this.seq);
        }

        @Override
        public String read() {
            return this.seq.toString();
        }

        @Override
        public boolean isEmpty() {
            return this.seq.length() == 0;
        }

        @Override
        public long length() {
            return this.seq.length();
        }

        @Override
        public Optional<Long> lengthIfKnown() {
            return Optional.of(Long.valueOf(this.seq.length()));
        }

        private Iterator<String> linesIterator() {
            return new AbstractIterator<String>(){
                Iterator<String> lines;
                {
                    this.lines = LINE_SPLITTER.split(seq).iterator();
                }

                @Override
                protected String computeNext() {
                    if (this.lines.hasNext()) {
                        String next = this.lines.next();
                        if (this.lines.hasNext() || !next.isEmpty()) {
                            return next;
                        }
                    }
                    return (String)this.endOfData();
                }
            };
        }

        @Override
        public Stream<String> lines() {
            return Streams.stream(this.linesIterator());
        }

        @Override
        public String readFirstLine() {
            Iterator<String> lines = this.linesIterator();
            return lines.hasNext() ? lines.next() : null;
        }

        @Override
        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(this.linesIterator());
        }

        @Override
        public <T> T readLines(LineProcessor<T> processor) throws IOException {
            Iterator<String> lines = this.linesIterator();
            while (lines.hasNext() && processor.processLine(lines.next())) {
            }
            return processor.getResult();
        }

        public String toString() {
            return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
        }
    }

    private final class AsByteSource
    extends ByteSource {
        final Charset charset;

        AsByteSource(Charset charset) {
            this.charset = Preconditions.checkNotNull(charset);
        }

        @Override
        public CharSource asCharSource(Charset charset) {
            if (charset.equals(this.charset)) {
                return CharSource.this;
            }
            return super.asCharSource(charset);
        }

        @Override
        public InputStream openStream() throws IOException {
            return new ReaderInputStream(CharSource.this.openStream(), this.charset, 8192);
        }

        public String toString() {
            return CharSource.this.toString() + ".asByteSource(" + this.charset + ")";
        }
    }
}

