/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.google.common.io.LineBuffer;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class LineReader {
    private final Readable readable;
    private final @Nullable Reader reader;
    private final CharBuffer cbuf = CharStreams.createBuffer();
    private final char[] buf = this.cbuf.array();
    private final Queue<String> lines = new LinkedList<String>();
    private final LineBuffer lineBuf = new LineBuffer(){

        @Override
        protected void handleLine(String line, String end) {
            LineReader.this.lines.add(line);
        }
    };

    public LineReader(Readable readable) {
        this.readable = Preconditions.checkNotNull(readable);
        this.reader = readable instanceof Reader ? (Reader)readable : null;
    }

    @CanIgnoreReturnValue
    public String readLine() throws IOException {
        while (this.lines.peek() == null) {
            int read;
            this.cbuf.clear();
            int n = read = this.reader != null ? this.reader.read(this.buf, 0, this.buf.length) : this.readable.read(this.cbuf);
            if (read == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, read);
        }
        return this.lines.poll();
    }
}

