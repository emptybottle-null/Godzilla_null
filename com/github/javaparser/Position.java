/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser;

import com.github.javaparser.utils.Utils;
import java.util.Objects;

public class Position
implements Comparable<Position> {
    public final int line;
    public final int column;
    public static final int FIRST_LINE = 1;
    public static final int FIRST_COLUMN = 1;
    public static final Position HOME = new Position(1, 1);
    public static final int ABSOLUTE_BEGIN_LINE = -1;
    public static final int ABSOLUTE_END_LINE = -2;

    public Position(int line, int column) {
        if (line < -2) {
            throw new IllegalArgumentException("Can't position at line " + line);
        }
        if (column < -1) {
            throw new IllegalArgumentException("Can't position at column " + column);
        }
        this.line = line;
        this.column = column;
    }

    @Deprecated
    public static Position pos(int line, int column) {
        return new Position(line, column);
    }

    public Position withColumn(int column) {
        return new Position(this.line, column);
    }

    public Position withLine(int line) {
        return new Position(line, this.column);
    }

    public Position right(int characters) {
        return new Position(this.line, this.column + characters);
    }

    public Position nextLine() {
        return new Position(this.line + 1, 1);
    }

    public boolean valid() {
        return this.line >= 1 && this.column >= 1;
    }

    public boolean invalid() {
        return !this.valid();
    }

    public Position orIfInvalid(Position alternativePosition) {
        Utils.assertNotNull(alternativePosition);
        if (this.valid() || alternativePosition.invalid()) {
            return this;
        }
        return alternativePosition;
    }

    public boolean isAfter(Position otherPosition) {
        Utils.assertNotNull(otherPosition);
        if (otherPosition.line == -1) {
            return true;
        }
        if (this.line > otherPosition.line) {
            return true;
        }
        if (this.line == otherPosition.line) {
            return this.column > otherPosition.column;
        }
        return false;
    }

    public boolean isAfterOrEqual(Position otherPosition) {
        Utils.assertNotNull(otherPosition);
        return this.isAfter(otherPosition) || this.equals(otherPosition);
    }

    public boolean isBefore(Position otherPosition) {
        Utils.assertNotNull(otherPosition);
        if (otherPosition.line == -2) {
            return true;
        }
        if (this.line < otherPosition.line) {
            return true;
        }
        if (this.line == otherPosition.line) {
            return this.column < otherPosition.column;
        }
        return false;
    }

    public boolean isBeforeOrEqual(Position otherPosition) {
        Utils.assertNotNull(otherPosition);
        return this.isBefore(otherPosition) || this.equals(otherPosition);
    }

    @Override
    public int compareTo(Position otherPosition) {
        Utils.assertNotNull(otherPosition);
        if (this.isBefore(otherPosition)) {
            return -1;
        }
        if (this.isAfter(otherPosition)) {
            return 1;
        }
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Position otherPosition = (Position)o;
        return Objects.equals(this.line, otherPosition.line) && Objects.equals(this.column, otherPosition.column);
    }

    public int hashCode() {
        return Objects.hash(this.line, this.column);
    }

    public String toString() {
        return "(line " + this.line + ",col " + this.column + ")";
    }
}

