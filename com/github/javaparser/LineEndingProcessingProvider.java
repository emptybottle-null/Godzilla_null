/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Provider;
import com.github.javaparser.utils.LineSeparator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LineEndingProcessingProvider
implements Provider {
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 2048;
    private final Provider _input;
    private final char[] _data;
    private int _len = 0;
    private int _pos = 0;
    private final Map<LineSeparator, Integer> eolCounts = new HashMap<LineSeparator, Integer>();

    public LineEndingProcessingProvider(Provider input) {
        this(2048, input);
    }

    public LineEndingProcessingProvider(int bufferSize, Provider input) {
        this._input = input;
        this._data = new char[bufferSize];
    }

    @Override
    public void close() throws IOException {
        this._input.close();
    }

    private int fillBuffer() throws IOException {
        this._pos = 0;
        int direct = this._input.read(this._data, 0, this._data.length);
        if (direct != 0) {
            this._len = direct;
        }
        return direct;
    }

    public LineSeparator getDetectedLineEnding() {
        return LineSeparator.getLineEnding(this.eolCounts.getOrDefault((Object)LineSeparator.CR, 0), this.eolCounts.getOrDefault((Object)LineSeparator.LF, 0), this.eolCounts.getOrDefault((Object)LineSeparator.CRLF, 0));
    }

    private boolean isBufferEmpty() {
        return this._pos >= this._len;
    }

    private int nextBufferedChar() throws IOException {
        while (this.isBufferEmpty()) {
            int direct = this.fillBuffer();
            if (direct >= 0) continue;
            return -1;
        }
        return this._data[this._pos++];
    }

    @Override
    public int read(char[] buffer, int offset, int len) throws IOException {
        int pos = offset;
        int stop = offset + len;
        LineSeparator previousLineSeparator = null;
        while (pos < stop) {
            int ch = this.nextBufferedChar();
            if (ch < 0) {
                if (pos != offset) break;
                return -1;
            }
            String str = String.valueOf((char)ch);
            Optional<LineSeparator> lookup = LineSeparator.lookup(str);
            if (lookup.isPresent()) {
                LineSeparator lineSeparator = lookup.get();
                this.eolCounts.putIfAbsent(lineSeparator, 0);
                this.eolCounts.put(lineSeparator, this.eolCounts.get((Object)lineSeparator) + 1);
                if (lineSeparator == LineSeparator.LF && previousLineSeparator == LineSeparator.CR) {
                    this.eolCounts.putIfAbsent(LineSeparator.CRLF, 0);
                    this.eolCounts.put(LineSeparator.CRLF, this.eolCounts.get((Object)LineSeparator.CRLF) + 1);
                }
                previousLineSeparator = lineSeparator;
            } else {
                previousLineSeparator = null;
            }
            buffer[pos++] = (char)ch;
        }
        return pos - offset;
    }
}

