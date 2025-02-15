/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

import java.io.Serializable;
import org.apache.xmlbeans.impl.regex.Token;

final class RangeToken
extends Token
implements Serializable {
    int[] ranges;
    boolean sorted;
    boolean compacted;
    RangeToken icaseCache = null;
    int[] map = null;
    int nonMapIndex;
    private static final int MAPSIZE = 256;

    RangeToken(int type) {
        super(type);
        this.setSorted(false);
    }

    @Override
    protected void addRange(int start, int end) {
        int r2;
        int r1;
        this.icaseCache = null;
        if (start <= end) {
            r1 = start;
            r2 = end;
        } else {
            r1 = end;
            r2 = start;
        }
        int pos = 0;
        if (this.ranges == null) {
            this.ranges = new int[2];
            this.ranges[0] = r1;
            this.ranges[1] = r2;
            this.setSorted(true);
        } else {
            pos = this.ranges.length;
            if (this.ranges[pos - 1] + 1 == r1) {
                this.ranges[pos - 1] = r2;
                return;
            }
            int[] temp = new int[pos + 2];
            System.arraycopy(this.ranges, 0, temp, 0, pos);
            this.ranges = temp;
            if (this.ranges[pos - 1] >= r1) {
                this.setSorted(false);
            }
            this.ranges[pos++] = r1;
            this.ranges[pos] = r2;
            if (!this.sorted) {
                this.sortRanges();
            }
        }
    }

    private final boolean isSorted() {
        return this.sorted;
    }

    private final void setSorted(boolean sort) {
        this.sorted = sort;
        if (!sort) {
            this.compacted = false;
        }
    }

    private final boolean isCompacted() {
        return this.compacted;
    }

    private final void setCompacted() {
        this.compacted = true;
    }

    @Override
    protected void sortRanges() {
        if (this.isSorted()) {
            return;
        }
        if (this.ranges == null) {
            return;
        }
        for (int i = this.ranges.length - 4; i >= 0; i -= 2) {
            for (int j = 0; j <= i; j += 2) {
                if (this.ranges[j] <= this.ranges[j + 2] && (this.ranges[j] != this.ranges[j + 2] || this.ranges[j + 1] <= this.ranges[j + 3])) continue;
                int tmp = this.ranges[j + 2];
                this.ranges[j + 2] = this.ranges[j];
                this.ranges[j] = tmp;
                tmp = this.ranges[j + 3];
                this.ranges[j + 3] = this.ranges[j + 1];
                this.ranges[j + 1] = tmp;
            }
        }
        this.setSorted(true);
    }

    @Override
    protected void compactRanges() {
        boolean DEBUG = false;
        if (this.ranges == null || this.ranges.length <= 2) {
            return;
        }
        if (this.isCompacted()) {
            return;
        }
        int base = 0;
        int target = 0;
        while (target < this.ranges.length) {
            if (base != target) {
                this.ranges[base] = this.ranges[target++];
                this.ranges[base + 1] = this.ranges[target++];
            } else {
                target += 2;
            }
            int baseend = this.ranges[base + 1];
            while (target < this.ranges.length && baseend + 1 >= this.ranges[target]) {
                if (baseend + 1 == this.ranges[target]) {
                    if (DEBUG) {
                        System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target] + ", " + this.ranges[target + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[target + 1] + "]");
                    }
                    this.ranges[base + 1] = this.ranges[target + 1];
                    baseend = this.ranges[base + 1];
                    target += 2;
                    continue;
                }
                if (baseend >= this.ranges[target + 1]) {
                    if (DEBUG) {
                        System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target] + ", " + this.ranges[target + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[base + 1] + "]");
                    }
                    target += 2;
                    continue;
                }
                if (baseend < this.ranges[target + 1]) {
                    if (DEBUG) {
                        System.err.println("Token#compactRanges(): Compaction: [" + this.ranges[base] + ", " + this.ranges[base + 1] + "], [" + this.ranges[target] + ", " + this.ranges[target + 1] + "] -> [" + this.ranges[base] + ", " + this.ranges[target + 1] + "]");
                    }
                    this.ranges[base + 1] = this.ranges[target + 1];
                    baseend = this.ranges[base + 1];
                    target += 2;
                    continue;
                }
                throw new RuntimeException("Token#compactRanges(): Internel Error: [" + this.ranges[base] + "," + this.ranges[base + 1] + "] [" + this.ranges[target] + "," + this.ranges[target + 1] + "]");
            }
            base += 2;
        }
        if (base != this.ranges.length) {
            int[] result = new int[base];
            System.arraycopy(this.ranges, 0, result, 0, base);
            this.ranges = result;
        }
        this.setCompacted();
    }

    @Override
    protected void mergeRanges(Token token) {
        RangeToken tok = (RangeToken)token;
        this.sortRanges();
        tok.sortRanges();
        if (tok.ranges == null) {
            return;
        }
        this.icaseCache = null;
        this.setSorted(true);
        if (this.ranges == null) {
            this.ranges = new int[tok.ranges.length];
            System.arraycopy(tok.ranges, 0, this.ranges, 0, tok.ranges.length);
            return;
        }
        int[] result = new int[this.ranges.length + tok.ranges.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < this.ranges.length || j < tok.ranges.length) {
            if (i >= this.ranges.length) {
                result[k++] = tok.ranges[j++];
                result[k++] = tok.ranges[j++];
                continue;
            }
            if (j >= tok.ranges.length) {
                result[k++] = this.ranges[i++];
                result[k++] = this.ranges[i++];
                continue;
            }
            if (tok.ranges[j] < this.ranges[i] || tok.ranges[j] == this.ranges[i] && tok.ranges[j + 1] < this.ranges[i + 1]) {
                result[k++] = tok.ranges[j++];
                result[k++] = tok.ranges[j++];
                continue;
            }
            result[k++] = this.ranges[i++];
            result[k++] = this.ranges[i++];
        }
        this.ranges = result;
    }

    @Override
    protected void subtractRanges(Token token) {
        if (token.type == 5) {
            this.intersectRanges(token);
            return;
        }
        RangeToken tok = (RangeToken)token;
        if (tok.ranges == null || this.ranges == null) {
            return;
        }
        this.icaseCache = null;
        this.sortRanges();
        this.compactRanges();
        tok.sortRanges();
        tok.compactRanges();
        int[] result = new int[this.ranges.length + tok.ranges.length];
        int wp = 0;
        int src = 0;
        int sub = 0;
        while (src < this.ranges.length && sub < tok.ranges.length) {
            int srcbegin = this.ranges[src];
            int srcend = this.ranges[src + 1];
            int subbegin = tok.ranges[sub];
            int subend = tok.ranges[sub + 1];
            if (srcend < subbegin) {
                result[wp++] = this.ranges[src++];
                result[wp++] = this.ranges[src++];
                continue;
            }
            if (srcend >= subbegin && srcbegin <= subend) {
                if (subbegin <= srcbegin && srcend <= subend) {
                    src += 2;
                    continue;
                }
                if (subbegin <= srcbegin) {
                    this.ranges[src] = subend + 1;
                    sub += 2;
                    continue;
                }
                if (srcend <= subend) {
                    result[wp++] = srcbegin;
                    result[wp++] = subbegin - 1;
                    src += 2;
                    continue;
                }
                result[wp++] = srcbegin;
                result[wp++] = subbegin - 1;
                this.ranges[src] = subend + 1;
                sub += 2;
                continue;
            }
            if (subend < srcbegin) {
                sub += 2;
                continue;
            }
            throw new RuntimeException("Token#subtractRanges(): Internal Error: [" + this.ranges[src] + "," + this.ranges[src + 1] + "] - [" + tok.ranges[sub] + "," + tok.ranges[sub + 1] + "]");
        }
        while (src < this.ranges.length) {
            result[wp++] = this.ranges[src++];
            result[wp++] = this.ranges[src++];
        }
        this.ranges = new int[wp];
        System.arraycopy(result, 0, this.ranges, 0, wp);
    }

    @Override
    protected void intersectRanges(Token token) {
        RangeToken tok = (RangeToken)token;
        if (tok.ranges == null || this.ranges == null) {
            return;
        }
        this.icaseCache = null;
        this.sortRanges();
        this.compactRanges();
        tok.sortRanges();
        tok.compactRanges();
        int[] result = new int[this.ranges.length + tok.ranges.length];
        int wp = 0;
        int src1 = 0;
        int src2 = 0;
        while (src1 < this.ranges.length && src2 < tok.ranges.length) {
            int src1begin = this.ranges[src1];
            int src1end = this.ranges[src1 + 1];
            int src2begin = tok.ranges[src2];
            int src2end = tok.ranges[src2 + 1];
            if (src1end < src2begin) {
                src1 += 2;
                continue;
            }
            if (src1end >= src2begin && src1begin <= src2end) {
                if (src2begin <= src2begin && src1end <= src2end) {
                    result[wp++] = src1begin;
                    result[wp++] = src1end;
                    src1 += 2;
                    continue;
                }
                if (src2begin <= src1begin) {
                    result[wp++] = src1begin;
                    result[wp++] = src2end;
                    this.ranges[src1] = src2end + 1;
                    src2 += 2;
                    continue;
                }
                if (src1end <= src2end) {
                    result[wp++] = src2begin;
                    result[wp++] = src1end;
                    src1 += 2;
                    continue;
                }
                result[wp++] = src2begin;
                result[wp++] = src2end;
                this.ranges[src1] = src2end + 1;
                continue;
            }
            if (src2end < src1begin) {
                src2 += 2;
                continue;
            }
            throw new RuntimeException("Token#intersectRanges(): Internal Error: [" + this.ranges[src1] + "," + this.ranges[src1 + 1] + "] & [" + tok.ranges[src2] + "," + tok.ranges[src2 + 1] + "]");
        }
        while (src1 < this.ranges.length) {
            result[wp++] = this.ranges[src1++];
            result[wp++] = this.ranges[src1++];
        }
        this.ranges = new int[wp];
        System.arraycopy(result, 0, this.ranges, 0, wp);
    }

    static Token complementRanges(Token token) {
        int last;
        if (token.type != 4 && token.type != 5) {
            throw new IllegalArgumentException("Token#complementRanges(): must be RANGE: " + token.type);
        }
        RangeToken tok = (RangeToken)token;
        tok.sortRanges();
        tok.compactRanges();
        int len = tok.ranges.length + 2;
        if (tok.ranges[0] == 0) {
            len -= 2;
        }
        if ((last = tok.ranges[tok.ranges.length - 1]) == 0x10FFFF) {
            len -= 2;
        }
        RangeToken ret = Token.createRange();
        ret.ranges = new int[len];
        int wp = 0;
        if (tok.ranges[0] > 0) {
            ret.ranges[wp++] = 0;
            ret.ranges[wp++] = tok.ranges[0] - 1;
        }
        for (int i = 1; i < tok.ranges.length - 2; i += 2) {
            ret.ranges[wp++] = tok.ranges[i] + 1;
            ret.ranges[wp++] = tok.ranges[i + 1] - 1;
        }
        if (last != 0x10FFFF) {
            ret.ranges[wp++] = last + 1;
            ret.ranges[wp] = 0x10FFFF;
        }
        ret.setCompacted();
        return ret;
    }

    synchronized RangeToken getCaseInsensitiveToken() {
        if (this.icaseCache != null) {
            return this.icaseCache;
        }
        RangeToken uppers = this.type == 4 ? Token.createRange() : Token.createNRange();
        for (int i = 0; i < this.ranges.length; i += 2) {
            for (int ch = this.ranges[i]; ch <= this.ranges[i + 1]; ++ch) {
                if (ch > 65535) {
                    uppers.addRange(ch, ch);
                    continue;
                }
                char uch = Character.toUpperCase((char)ch);
                uppers.addRange(uch, uch);
            }
        }
        RangeToken lowers = this.type == 4 ? Token.createRange() : Token.createNRange();
        for (int i = 0; i < uppers.ranges.length; i += 2) {
            for (int ch = uppers.ranges[i]; ch <= uppers.ranges[i + 1]; ++ch) {
                if (ch > 65535) {
                    lowers.addRange(ch, ch);
                    continue;
                }
                char uch = Character.toUpperCase((char)ch);
                lowers.addRange(uch, uch);
            }
        }
        lowers.mergeRanges(uppers);
        lowers.mergeRanges(this);
        lowers.compactRanges();
        this.icaseCache = lowers;
        return lowers;
    }

    void dumpRanges() {
        System.err.print("RANGE: ");
        if (this.ranges == null) {
            System.err.println(" NULL");
        }
        for (int i = 0; i < this.ranges.length; i += 2) {
            System.err.print("[" + this.ranges[i] + "," + this.ranges[i + 1] + "] ");
        }
        System.err.println("");
    }

    @Override
    boolean match(int ch) {
        boolean ret;
        if (this.map == null) {
            this.createMap();
        }
        if (this.type == 4) {
            if (ch < 256) {
                return (this.map[ch / 32] & 1 << (ch & 0x1F)) != 0;
            }
            ret = false;
            for (int i = this.nonMapIndex; i < this.ranges.length; i += 2) {
                if (this.ranges[i] > ch || ch > this.ranges[i + 1]) continue;
                return true;
            }
        } else {
            if (ch < 256) {
                return (this.map[ch / 32] & 1 << (ch & 0x1F)) == 0;
            }
            ret = true;
            for (int i = this.nonMapIndex; i < this.ranges.length; i += 2) {
                if (this.ranges[i] > ch || ch > this.ranges[i + 1]) continue;
                return false;
            }
        }
        return ret;
    }

    private void createMap() {
        int i;
        int asize = 8;
        int[] localmap = new int[asize];
        int localnonMapIndex = this.ranges.length;
        for (i = 0; i < asize; ++i) {
            localmap[i] = 0;
        }
        for (i = 0; i < this.ranges.length; i += 2) {
            int s = this.ranges[i];
            int e = this.ranges[i + 1];
            if (s < 256) {
                for (int j = s; j <= e && j < 256; ++j) {
                    int n = j / 32;
                    localmap[n] = localmap[n] | 1 << (j & 0x1F);
                }
            } else {
                localnonMapIndex = i;
                break;
            }
            if (e < 256) continue;
            localnonMapIndex = i;
            break;
        }
        this.nonMapIndex = localnonMapIndex;
        this.map = localmap;
    }

    @Override
    public String toString(int options) {
        String ret;
        if (this.type == 4) {
            if (this == Token.token_dot) {
                ret = ".";
            } else if (this == Token.token_0to9) {
                ret = "\\d";
            } else if (this == Token.token_wordchars) {
                ret = "\\w";
            } else if (this == Token.token_spaces) {
                ret = "\\s";
            } else {
                StringBuffer sb = new StringBuffer();
                sb.append("[");
                for (int i = 0; i < this.ranges.length; i += 2) {
                    if ((options & 0x400) != 0 && i > 0) {
                        sb.append(",");
                    }
                    if (this.ranges[i] == this.ranges[i + 1]) {
                        sb.append(RangeToken.escapeCharInCharClass(this.ranges[i]));
                        continue;
                    }
                    sb.append(RangeToken.escapeCharInCharClass(this.ranges[i]));
                    sb.append('-');
                    sb.append(RangeToken.escapeCharInCharClass(this.ranges[i + 1]));
                }
                sb.append("]");
                ret = sb.toString();
            }
        } else if (this == Token.token_not_0to9) {
            ret = "\\D";
        } else if (this == Token.token_not_wordchars) {
            ret = "\\W";
        } else if (this == Token.token_not_spaces) {
            ret = "\\S";
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("[^");
            for (int i = 0; i < this.ranges.length; i += 2) {
                if ((options & 0x400) != 0 && i > 0) {
                    sb.append(",");
                }
                if (this.ranges[i] == this.ranges[i + 1]) {
                    sb.append(RangeToken.escapeCharInCharClass(this.ranges[i]));
                    continue;
                }
                sb.append(RangeToken.escapeCharInCharClass(this.ranges[i]));
                sb.append('-');
                sb.append(RangeToken.escapeCharInCharClass(this.ranges[i + 1]));
            }
            sb.append("]");
            ret = sb.toString();
        }
        return ret;
    }

    private static String escapeCharInCharClass(int ch) {
        String ret;
        switch (ch) {
            case 44: 
            case 45: 
            case 91: 
            case 92: 
            case 93: 
            case 94: {
                ret = "\\" + (char)ch;
                break;
            }
            case 12: {
                ret = "\\f";
                break;
            }
            case 10: {
                ret = "\\n";
                break;
            }
            case 13: {
                ret = "\\r";
                break;
            }
            case 9: {
                ret = "\\t";
                break;
            }
            case 27: {
                ret = "\\e";
                break;
            }
            default: {
                if (ch < 32) {
                    String pre = "0" + Integer.toHexString(ch);
                    ret = "\\x" + pre.substring(pre.length() - 2, pre.length());
                    break;
                }
                if (ch >= 65536) {
                    String pre = "0" + Integer.toHexString(ch);
                    ret = "\\v" + pre.substring(pre.length() - 6, pre.length());
                    break;
                }
                ret = "" + (char)ch;
            }
        }
        return ret;
    }
}

