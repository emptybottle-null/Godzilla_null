/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.google.common.io.Closer;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.stream.Stream;

@GwtIncompatible
public abstract class CharSink {
    protected CharSink() {
    }

    public abstract Writer openStream() throws IOException;

    public Writer openBufferedStream() throws IOException {
        Writer writer = this.openStream();
        return writer instanceof BufferedWriter ? (BufferedWriter)writer : new BufferedWriter(writer);
    }

    public void write(CharSequence charSequence) throws IOException {
        Preconditions.checkNotNull(charSequence);
        try (Closer closer = Closer.create();){
            Writer out = closer.register(this.openStream());
            out.append(charSequence);
            out.flush();
        }
    }

    public void writeLines(Iterable<? extends CharSequence> lines) throws IOException {
        this.writeLines(lines, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> lines, String lineSeparator) throws IOException {
        this.writeLines(lines.iterator(), lineSeparator);
    }

    @Beta
    public void writeLines(Stream<? extends CharSequence> lines) throws IOException {
        this.writeLines(lines, System.getProperty("line.separator"));
    }

    @Beta
    public void writeLines(Stream<? extends CharSequence> lines, String lineSeparator) throws IOException {
        this.writeLines(lines.iterator(), lineSeparator);
    }

    private void writeLines(Iterator<? extends CharSequence> lines, String lineSeparator) throws IOException {
        Preconditions.checkNotNull(lineSeparator);
        try (Writer out = this.openBufferedStream();){
            while (lines.hasNext()) {
                out.append(lines.next()).append(lineSeparator);
            }
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(Readable readable) throws IOException {
        Preconditions.checkNotNull(readable);
        try (Closer closer = Closer.create();){
            Writer out = closer.register(this.openStream());
            long written = CharStreams.copy(readable, out);
            out.flush();
            long l = written;
            return l;
        }
    }
}

