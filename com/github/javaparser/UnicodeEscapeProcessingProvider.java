/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Position;
import com.github.javaparser.Provider;
import com.github.javaparser.Range;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnicodeEscapeProcessingProvider
implements Provider {
    private static final char LF = '\n';
    private static final char CR = '\r';
    private static final char BACKSLASH = '\\';
    private static final int EOF = -1;
    private char[] _data;
    private int _len = 0;
    private int _pos = 0;
    private boolean _backslashSeen;
    private final LineCounter _inputLine = new LineCounter();
    private final LineCounter _outputLine = new LineCounter();
    private final PositionMappingBuilder _mappingBuilder = new PositionMappingBuilder(this._outputLine, this._inputLine);
    private Provider _input;

    public UnicodeEscapeProcessingProvider(Provider input) {
        this(2048, input);
    }

    public UnicodeEscapeProcessingProvider(int bufferSize, Provider input) {
        this._input = input;
        this._data = new char[bufferSize];
    }

    public LineCounter getInputCounter() {
        return this._inputLine;
    }

    public LineCounter getOutputCounter() {
        return this._outputLine;
    }

    @Override
    public int read(char[] buffer, int offset, int len) throws IOException {
        int pos = offset;
        int stop = offset + len;
        while (pos < stop) {
            int ch = this._outputLine.process(this.nextOutputChar());
            if (ch < 0) {
                if (pos != offset) break;
                return -1;
            }
            this._mappingBuilder.update();
            buffer[pos++] = (char)ch;
        }
        return pos - offset;
    }

    @Override
    public void close() throws IOException {
        this._input.close();
    }

    private int nextOutputChar() throws IOException {
        int next = this.nextInputChar();
        switch (next) {
            case -1: {
                return -1;
            }
            case 92: {
                if (this._backslashSeen) {
                    return this.clearBackSlashSeen(next);
                }
                return this.backSlashSeen();
            }
        }
        return this.clearBackSlashSeen(next);
    }

    private int clearBackSlashSeen(int next) {
        this._backslashSeen = false;
        return next;
    }

    private int backSlashSeen() throws IOException {
        this._backslashSeen = true;
        int next = this.nextInputChar();
        switch (next) {
            case -1: {
                return 92;
            }
            case 117: {
                return this.unicodeStartSeen();
            }
        }
        this.pushBack(next);
        return 92;
    }

    private int unicodeStartSeen() throws IOException {
        int next;
        int uCnt = 1;
        block4: while (true) {
            next = this.nextInputChar();
            switch (next) {
                case -1: {
                    this.pushBackUs(uCnt);
                    return 92;
                }
                case 117: {
                    ++uCnt;
                    continue block4;
                }
            }
            break;
        }
        return this.readDigits(uCnt, next);
    }

    private int readDigits(int uCnt, int next3) throws IOException {
        int digit3 = UnicodeEscapeProcessingProvider.digit(next3);
        if (digit3 < 0) {
            this.pushBack(next3);
            this.pushBackUs(uCnt);
            return 92;
        }
        int next2 = this.nextInputChar();
        int digit2 = UnicodeEscapeProcessingProvider.digit(next2);
        if (digit2 < 0) {
            this.pushBack(next2);
            this.pushBack(next3);
            this.pushBackUs(uCnt);
            return 92;
        }
        int next1 = this.nextInputChar();
        int digit1 = UnicodeEscapeProcessingProvider.digit(next1);
        if (digit1 < 0) {
            this.pushBack(next1);
            this.pushBack(next2);
            this.pushBack(next3);
            this.pushBackUs(uCnt);
            return 92;
        }
        int next0 = this.nextInputChar();
        int digit0 = UnicodeEscapeProcessingProvider.digit(next0);
        if (digit0 < 0) {
            this.pushBack(next0);
            this.pushBack(next1);
            this.pushBack(next2);
            this.pushBack(next3);
            this.pushBackUs(uCnt);
            return 92;
        }
        int ch = digit3 << 12 | digit2 << 8 | digit1 << 4 | digit0;
        return this.clearBackSlashSeen(ch);
    }

    private void pushBackUs(int cnt) {
        for (int n = 0; n < cnt; ++n) {
            this.pushBack(117);
        }
    }

    private static int digit(int ch) {
        if (ch >= 48 && ch <= 57) {
            return ch - 48;
        }
        if (ch >= 65 && ch <= 70) {
            return 10 + ch - 65;
        }
        if (ch >= 97 && ch <= 102) {
            return 10 + ch - 97;
        }
        return -1;
    }

    private int nextInputChar() throws IOException {
        int result = this.nextBufferedChar();
        return this._inputLine.process(result);
    }

    private int nextBufferedChar() throws IOException {
        while (this.isBufferEmpty()) {
            int direct = this.fillBuffer();
            if (direct >= 0) continue;
            return -1;
        }
        return this._data[this._pos++];
    }

    private boolean isBufferEmpty() {
        return this._pos >= this._len;
    }

    private int fillBuffer() throws IOException {
        this._pos = 0;
        int direct = this._input.read(this._data, 0, this._data.length);
        if (direct != 0) {
            this._len = direct;
        }
        return direct;
    }

    private void pushBack(int ch) {
        if (ch < 0) {
            return;
        }
        if (this.isBufferEmpty()) {
            this._pos = this._data.length;
            this._len = this._data.length;
        } else if (this._pos == 0) {
            if (this._len == this._data.length) {
                char[] newData = new char[this._data.length + 1024];
                this._len = newData.length;
                this._pos = newData.length - this._data.length;
                System.arraycopy(this._data, 0, newData, this._pos, this._data.length);
                this._data = newData;
            } else {
                int cnt = this._len - this._pos;
                this._pos = this._data.length - this._len;
                this._len = this._data.length;
                System.arraycopy(this._data, 0, this._data, this._pos, cnt);
            }
        }
        this._data[--this._pos] = (char)ch;
    }

    public PositionMapping getPositionMapping() {
        return this._mappingBuilder.getMapping();
    }

    public static final class LineCounter {
        private boolean _crSeen;
        private int _line = 1;
        private int _column = 1;

        public int getLine() {
            return this._line;
        }

        public int getColumn() {
            return this._column;
        }

        public Position getPosition() {
            return new Position(this.getLine(), this.getColumn());
        }

        public int process(int ch) {
            switch (ch) {
                case -1: {
                    break;
                }
                case 13: {
                    this.incLine();
                    this._crSeen = true;
                    break;
                }
                case 10: {
                    if (this._crSeen) {
                        this._crSeen = false;
                        break;
                    }
                    this.incLine();
                    break;
                }
                default: {
                    this._crSeen = false;
                    ++this._column;
                }
            }
            return ch;
        }

        private void incLine() {
            ++this._line;
            this._column = 1;
        }
    }

    private static final class PositionMappingBuilder {
        private LineCounter _left;
        private LineCounter _right;
        private final PositionMapping _mapping = new PositionMapping();
        private int _lineDelta = 0;
        private int _columnDelta = 0;

        public PositionMappingBuilder(LineCounter left, LineCounter right) {
            this._left = left;
            this._right = right;
            this.update();
        }

        public PositionMapping getMapping() {
            return this._mapping;
        }

        public void update() {
            int lineDelta = this._right.getLine() - this._left.getLine();
            int columnDelta = this._right.getColumn() - this._left.getColumn();
            if (lineDelta != this._lineDelta || columnDelta != this._columnDelta) {
                this._mapping.add(this._left.getLine(), this._left.getColumn(), lineDelta, columnDelta);
                this._lineDelta = lineDelta;
                this._columnDelta = columnDelta;
            }
        }
    }

    public static final class PositionMapping {
        private final List<DeltaInfo> _deltas = new ArrayList<DeltaInfo>();

        public boolean isEmpty() {
            return this._deltas.isEmpty();
        }

        void add(int line, int column, int lineDelta, int columnDelta) {
            this._deltas.add(new DeltaInfo(line, column, lineDelta, columnDelta));
        }

        public PositionUpdate lookup(Position position) {
            int result = Collections.binarySearch(this._deltas, position);
            if (result >= 0) {
                return this._deltas.get(result);
            }
            int insertIndex = -result - 1;
            if (insertIndex == 0) {
                return PositionUpdate.NONE;
            }
            return this._deltas.get(insertIndex - 1);
        }

        public Position transform(Position pos) {
            return this.lookup(pos).transform(pos);
        }

        public Range transform(Range range) {
            Position begin = this.transform(range.begin);
            Position end = this.transform(range.end);
            if (begin == range.begin && end == range.end) {
                return range;
            }
            return new Range(begin, end);
        }

        private static final class DeltaInfo
        extends Position
        implements PositionUpdate {
            private final int _lineDelta;
            private final int _columnDelta;

            public DeltaInfo(int line, int column, int lineDelta, int columnDelta) {
                super(line, column);
                this._lineDelta = lineDelta;
                this._columnDelta = columnDelta;
            }

            @Override
            public int transformLine(int sourceLine) {
                return sourceLine + this._lineDelta;
            }

            @Override
            public int transformColumn(int sourceColumn) {
                return sourceColumn + this._columnDelta;
            }

            @Override
            public String toString() {
                return "(" + this.line + ", " + this.column + ": " + this._lineDelta + ", " + this._columnDelta + ")";
            }
        }

        public static interface PositionUpdate {
            public static final PositionUpdate NONE = new PositionUpdate(){

                @Override
                public int transformLine(int line) {
                    return line;
                }

                @Override
                public int transformColumn(int column) {
                    return column;
                }

                @Override
                public Position transform(Position pos) {
                    return pos;
                }
            };

            public int transformLine(int var1);

            public int transformColumn(int var1);

            default public Position transform(Position pos) {
                int line = pos.line;
                int column = pos.column;
                int transformedLine = this.transformLine(line);
                int transformedColumn = this.transformColumn(column);
                return new Position(transformedLine, transformedColumn);
            }
        }
    }
}

