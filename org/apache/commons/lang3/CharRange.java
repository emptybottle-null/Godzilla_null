/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.Validate;

final class CharRange
implements Iterable<Character>,
Serializable {
    private static final long serialVersionUID = 8270183163158333422L;
    private final char start;
    private final char end;
    private final boolean negated;
    private transient String iToString;
    static final CharRange[] EMPTY_ARRAY = new CharRange[0];

    private CharRange(char start, char end, boolean negated) {
        if (start > end) {
            char temp = start;
            start = end;
            end = temp;
        }
        this.start = start;
        this.end = end;
        this.negated = negated;
    }

    public static CharRange is(char ch) {
        return new CharRange(ch, ch, false);
    }

    public static CharRange isNot(char ch) {
        return new CharRange(ch, ch, true);
    }

    public static CharRange isIn(char start, char end) {
        return new CharRange(start, end, false);
    }

    public static CharRange isNotIn(char start, char end) {
        return new CharRange(start, end, true);
    }

    public char getStart() {
        return this.start;
    }

    public char getEnd() {
        return this.end;
    }

    public boolean isNegated() {
        return this.negated;
    }

    public boolean contains(char ch) {
        return (ch >= this.start && ch <= this.end) != this.negated;
    }

    public boolean contains(CharRange range) {
        Validate.notNull(range, "range", new Object[0]);
        if (this.negated) {
            if (range.negated) {
                return this.start >= range.start && this.end <= range.end;
            }
            return range.end < this.start || range.start > this.end;
        }
        if (range.negated) {
            return this.start == '\u0000' && this.end == '\uffff';
        }
        return this.start <= range.start && this.end >= range.end;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharRange)) {
            return false;
        }
        CharRange other = (CharRange)obj;
        return this.start == other.start && this.end == other.end && this.negated == other.negated;
    }

    public int hashCode() {
        return 83 + this.start + 7 * this.end + (this.negated ? 1 : 0);
    }

    public String toString() {
        if (this.iToString == null) {
            StringBuilder buf = new StringBuilder(4);
            if (this.isNegated()) {
                buf.append('^');
            }
            buf.append(this.start);
            if (this.start != this.end) {
                buf.append('-');
                buf.append(this.end);
            }
            this.iToString = buf.toString();
        }
        return this.iToString;
    }

    @Override
    public Iterator<Character> iterator() {
        return new CharacterIterator(this);
    }

    private static class CharacterIterator
    implements Iterator<Character> {
        private char current;
        private final CharRange range;
        private boolean hasNext;

        private CharacterIterator(CharRange r) {
            this.range = r;
            this.hasNext = true;
            if (this.range.negated) {
                if (this.range.start == '\u0000') {
                    if (this.range.end == '\uffff') {
                        this.hasNext = false;
                    } else {
                        this.current = (char)(this.range.end + '\u0001');
                    }
                } else {
                    this.current = '\u0000';
                }
            } else {
                this.current = this.range.start;
            }
        }

        private void prepareNext() {
            if (this.range.negated) {
                if (this.current == '\uffff') {
                    this.hasNext = false;
                } else if (this.current + '\u0001' == this.range.start) {
                    if (this.range.end == '\uffff') {
                        this.hasNext = false;
                    } else {
                        this.current = (char)(this.range.end + '\u0001');
                    }
                } else {
                    this.current = (char)(this.current + '\u0001');
                }
            } else if (this.current < this.range.end) {
                this.current = (char)(this.current + '\u0001');
            } else {
                this.hasNext = false;
            }
        }

        @Override
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override
        public Character next() {
            if (!this.hasNext) {
                throw new NoSuchElementException();
            }
            char cur = this.current;
            this.prepareNext();
            return Character.valueOf(cur);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

