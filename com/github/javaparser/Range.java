/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.Position;

public class Range {
    public final Position begin;
    public final Position end;

    public Range(Position begin, Position end) {
        if (begin == null) {
            throw new IllegalArgumentException("begin can't be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("end can't be null");
        }
        if (begin.isBefore(end)) {
            this.begin = begin;
            this.end = end;
        } else {
            this.begin = end;
            this.end = begin;
        }
    }

    public static Range range(Position begin, Position end) {
        return new Range(begin, end);
    }

    public static Range range(int beginLine, int beginColumn, int endLine, int endColumn) {
        return new Range(new Position(beginLine, beginColumn), new Position(endLine, endColumn));
    }

    public Range withBeginColumn(int beginColumn) {
        return Range.range(this.begin.withColumn(beginColumn), this.end);
    }

    public Range withBeginLine(int beginLine) {
        return Range.range(this.begin.withLine(beginLine), this.end);
    }

    public Range withEndColumn(int endColumn) {
        return Range.range(this.begin, this.end.withColumn(endColumn));
    }

    public Range withEndLine(int endLine) {
        return Range.range(this.begin, this.end.withLine(endLine));
    }

    public Range withBegin(Position begin) {
        return Range.range(begin, this.end);
    }

    public Range withEnd(Position end) {
        return Range.range(this.begin, end);
    }

    public boolean contains(Range other) {
        boolean beginResult = this.begin.isBeforeOrEqual(other.begin);
        boolean endResult = this.end.isAfterOrEqual(other.end);
        return beginResult && endResult;
    }

    public boolean contains(Position position) {
        return this.strictlyContains(position) || this.begin.equals(position) || this.end.equals(position);
    }

    public boolean strictlyContains(Range other) {
        boolean beginResult = this.begin.isBefore(other.begin);
        boolean endResult = this.end.isAfter(other.end);
        return beginResult && endResult;
    }

    public boolean strictlyContains(Position position) {
        return position.isAfter(this.begin) && position.isBefore(this.end);
    }

    public boolean overlapsWith(Range other) {
        return this.contains(other.begin) || this.contains(other.end) || other.contains(this.begin) || other.contains(this.end);
    }

    public boolean isBefore(Position position) {
        return this.end.isBefore(position);
    }

    public boolean isAfter(Position position) {
        return this.begin.isAfter(position);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Range range = (Range)o;
        return this.begin.equals(range.begin) && this.end.equals(range.end);
    }

    public int hashCode() {
        return 31 * this.begin.hashCode() + this.end.hashCode();
    }

    public String toString() {
        return this.begin + "-" + this.end;
    }

    public int getLineCount() {
        return this.end.line - this.begin.line + 1;
    }
}

