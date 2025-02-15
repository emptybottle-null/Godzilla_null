/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.util.CharUtils;
import com.jediterm.terminal.util.Pair;
import java.util.Arrays;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class CharBuffer
implements Iterable<Character>,
CharSequence {
    public static final CharBuffer EMPTY = new CharBuffer(new char[0], 0, 0);
    private final char[] myBuf;
    private final int myStart;
    private final int myLength;

    public CharBuffer(@NotNull char[] buf, int start, int length) {
        if (buf == null) {
            CharBuffer.$$$reportNull$$$0(0);
        }
        if (start + length > buf.length) {
            throw new IllegalArgumentException(String.format("Out ouf bounds %d+%d>%d", start, length, buf.length));
        }
        this.myBuf = buf;
        this.myStart = start;
        this.myLength = length;
        if (this.myLength < 0) {
            throw new IllegalStateException("Length can't be negative: " + this.myLength);
        }
        if (this.myStart < 0) {
            throw new IllegalStateException("Start position can't be negative: " + this.myStart);
        }
        if (this.myStart + this.myLength > this.myBuf.length) {
            throw new IllegalStateException(String.format("Interval is out of array bounds: %d+%d>%d", this.myStart, this.myLength, this.myBuf.length));
        }
    }

    public CharBuffer(char c, int count) {
        this(new char[count], 0, count);
        assert (!CharUtils.isDoubleWidthCharacter(c, false));
        Arrays.fill(this.myBuf, c);
    }

    public CharBuffer(@NotNull String str) {
        if (str == null) {
            CharBuffer.$$$reportNull$$$0(1);
        }
        this(str.toCharArray(), 0, str.length());
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>(){
            private int myCurPosition;
            {
                this.myCurPosition = CharBuffer.this.myStart;
            }

            @Override
            public boolean hasNext() {
                return this.myCurPosition < CharBuffer.this.myBuf.length && this.myCurPosition < CharBuffer.this.myStart + CharBuffer.this.myLength;
            }

            @Override
            public Character next() {
                return Character.valueOf(CharBuffer.this.myBuf[this.myCurPosition]);
            }

            @Override
            public void remove() {
                throw new IllegalStateException("Can't remove from buffer");
            }
        };
    }

    public char[] getBuf() {
        return this.myBuf;
    }

    public int getStart() {
        return this.myStart;
    }

    public CharBuffer subBuffer(int start, int length) {
        return new CharBuffer(this.myBuf, this.getStart() + start, length);
    }

    public CharBuffer subBuffer(Pair<Integer, Integer> range) {
        return new CharBuffer(this.myBuf, this.getStart() + (Integer)range.first, (Integer)range.second - (Integer)range.first);
    }

    public boolean isNul() {
        return this.myLength > 0 && this.myBuf[0] == '\u0000';
    }

    public void unNullify() {
        Arrays.fill(this.myBuf, ' ');
    }

    @Override
    public int length() {
        return this.myLength;
    }

    @Override
    public char charAt(int index) {
        return this.myBuf[this.myStart + index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new CharBuffer(this.myBuf, this.myStart + start, end - start);
    }

    @Override
    public String toString() {
        return new String(this.myBuf, this.myStart, this.myLength);
    }

    public CharBuffer clone() {
        char[] newBuf = Arrays.copyOfRange(this.myBuf, this.myStart, this.myStart + this.myLength);
        return new CharBuffer(newBuf, 0, this.myLength);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2 = new Object[3];
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[0] = "buf";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[0] = "str";
                break;
            }
        }
        objectArray[1] = "com/jediterm/terminal/model/CharBuffer";
        objectArray[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

