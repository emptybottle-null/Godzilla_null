/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.Escaper;
import com.google.common.escape.Platform;

@Beta
@GwtCompatible
public abstract class CharEscaper
extends Escaper {
    private static final int DEST_PAD_MULTIPLIER = 2;

    protected CharEscaper() {
    }

    @Override
    public String escape(String string) {
        Preconditions.checkNotNull(string);
        int length = string.length();
        for (int index = 0; index < length; ++index) {
            if (this.escape(string.charAt(index)) == null) continue;
            return this.escapeSlow(string, index);
        }
        return string;
    }

    protected abstract char[] escape(char var1);

    protected final String escapeSlow(String s, int index) {
        int slen = s.length();
        char[] dest = Platform.charBufferFromThreadLocal();
        int destSize = dest.length;
        int destIndex = 0;
        int lastEscape = 0;
        while (index < slen) {
            char[] r = this.escape(s.charAt(index));
            if (r != null) {
                int charsSkipped = index - lastEscape;
                int rlen = r.length;
                int sizeNeeded = destIndex + charsSkipped + rlen;
                if (destSize < sizeNeeded) {
                    destSize = sizeNeeded + 2 * (slen - index);
                    dest = CharEscaper.growBuffer(dest, destIndex, destSize);
                }
                if (charsSkipped > 0) {
                    s.getChars(lastEscape, index, dest, destIndex);
                    destIndex += charsSkipped;
                }
                if (rlen > 0) {
                    System.arraycopy(r, 0, dest, destIndex, rlen);
                    destIndex += rlen;
                }
                lastEscape = index + 1;
            }
            ++index;
        }
        int charsLeft = slen - lastEscape;
        if (charsLeft > 0) {
            int sizeNeeded = destIndex + charsLeft;
            if (destSize < sizeNeeded) {
                dest = CharEscaper.growBuffer(dest, destIndex, sizeNeeded);
            }
            s.getChars(lastEscape, slen, dest, destIndex);
            destIndex = sizeNeeded;
        }
        return new String(dest, 0, destIndex);
    }

    private static char[] growBuffer(char[] dest, int index, int size) {
        if (size < 0) {
            throw new AssertionError((Object)"Cannot increase internal buffer any further");
        }
        char[] copy = new char[size];
        if (index > 0) {
            System.arraycopy(dest, 0, copy, 0, index);
        }
        return copy;
    }
}

