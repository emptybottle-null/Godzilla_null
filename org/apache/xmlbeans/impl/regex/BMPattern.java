/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.text.CharacterIterator;

public class BMPattern {
    char[] pattern;
    int[] shiftTable;
    boolean ignoreCase;

    public BMPattern(String pat, boolean ignoreCase) {
        this(pat, 256, ignoreCase);
    }

    public BMPattern(String pat, int tableSize, boolean ignoreCase) {
        int i;
        this.pattern = pat.toCharArray();
        this.shiftTable = new int[tableSize];
        this.ignoreCase = ignoreCase;
        int length = this.pattern.length;
        for (i = 0; i < this.shiftTable.length; ++i) {
            this.shiftTable[i] = length;
        }
        for (i = 0; i < length; ++i) {
            int diff = length - i - 1;
            char ch = this.pattern[i];
            int index = ch % this.shiftTable.length;
            if (diff < this.shiftTable[index]) {
                this.shiftTable[index] = diff;
            }
            if (!this.ignoreCase) continue;
            index = (ch = Character.toUpperCase(ch)) % this.shiftTable.length;
            if (diff < this.shiftTable[index]) {
                this.shiftTable[index] = diff;
            }
            if (diff >= this.shiftTable[index = (ch = Character.toLowerCase(ch)) % this.shiftTable.length]) continue;
            this.shiftTable[index] = diff;
        }
    }

    public int matches(CharacterIterator iterator, int start, int limit) {
        if (this.ignoreCase) {
            return this.matchesIgnoreCase(iterator, start, limit);
        }
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            while ((ch = iterator.setIndex(--index)) == this.pattern[--pindex]) {
                if (pindex == 0) {
                    return index;
                }
                if (pindex > 0) continue;
            }
            if ((index += this.shiftTable[ch % this.shiftTable.length] + 1) >= nindex) continue;
            index = nindex;
        }
        return -1;
    }

    public int matches(String str, int start, int limit) {
        if (this.ignoreCase) {
            return this.matchesIgnoreCase(str, start, limit);
        }
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            while ((ch = str.charAt(--index)) == this.pattern[--pindex]) {
                if (pindex == 0) {
                    return index;
                }
                if (pindex > 0) continue;
            }
            if ((index += this.shiftTable[ch % this.shiftTable.length] + 1) >= nindex) continue;
            index = nindex;
        }
        return -1;
    }

    public int matches(char[] chars, int start, int limit) {
        if (this.ignoreCase) {
            return this.matchesIgnoreCase(chars, start, limit);
        }
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch;
            int pindex = plength;
            int nindex = index + 1;
            while ((ch = chars[--index]) == this.pattern[--pindex]) {
                if (pindex == 0) {
                    return index;
                }
                if (pindex > 0) continue;
            }
            if ((index += this.shiftTable[ch % this.shiftTable.length] + 1) >= nindex) continue;
            index = nindex;
        }
        return -1;
    }

    int matchesIgnoreCase(CharacterIterator iterator, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch2;
            char ch;
            char ch1;
            int pindex = plength;
            int nindex = index + 1;
            while ((ch1 = (ch = iterator.setIndex(--index))) == (ch2 = this.pattern[--pindex]) || (ch1 = Character.toUpperCase(ch1)) == (ch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) {
                if (pindex == 0) {
                    return index;
                }
                if (pindex > 0) continue;
            }
            if ((index += this.shiftTable[ch % this.shiftTable.length] + 1) >= nindex) continue;
            index = nindex;
        }
        return -1;
    }

    int matchesIgnoreCase(String text, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch2;
            char ch;
            char ch1;
            int pindex = plength;
            int nindex = index + 1;
            while ((ch1 = (ch = text.charAt(--index))) == (ch2 = this.pattern[--pindex]) || (ch1 = Character.toUpperCase(ch1)) == (ch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) {
                if (pindex == 0) {
                    return index;
                }
                if (pindex > 0) continue;
            }
            if ((index += this.shiftTable[ch % this.shiftTable.length] + 1) >= nindex) continue;
            index = nindex;
        }
        return -1;
    }

    int matchesIgnoreCase(char[] chars, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0) {
            return start;
        }
        int index = start + plength;
        while (index <= limit) {
            char ch2;
            char ch;
            char ch1;
            int pindex = plength;
            int nindex = index + 1;
            while ((ch1 = (ch = chars[--index])) == (ch2 = this.pattern[--pindex]) || (ch1 = Character.toUpperCase(ch1)) == (ch2 = Character.toUpperCase(ch2)) || Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) {
                if (pindex == 0) {
                    return index;
                }
                if (pindex > 0) continue;
            }
            if ((index += this.shiftTable[ch % this.shiftTable.length] + 1) >= nindex) continue;
            index = nindex;
        }
        return -1;
    }
}

