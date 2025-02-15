/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.CharRange;

public class CharSet
implements Serializable {
    private static final long serialVersionUID = 5947847346149275958L;
    public static final CharSet EMPTY = new CharSet(new String[]{null});
    public static final CharSet ASCII_ALPHA = new CharSet("a-zA-Z");
    public static final CharSet ASCII_ALPHA_LOWER = new CharSet("a-z");
    public static final CharSet ASCII_ALPHA_UPPER = new CharSet("A-Z");
    public static final CharSet ASCII_NUMERIC = new CharSet("0-9");
    protected static final Map<String, CharSet> COMMON = Collections.synchronizedMap(new HashMap());
    private final Set<CharRange> set = Collections.synchronizedSet(new HashSet());

    public static CharSet getInstance(String ... setStrs) {
        CharSet common;
        if (setStrs == null) {
            return null;
        }
        if (setStrs.length == 1 && (common = COMMON.get(setStrs[0])) != null) {
            return common;
        }
        return new CharSet(setStrs);
    }

    protected CharSet(String ... set) {
        for (String s : set) {
            this.add(s);
        }
    }

    protected void add(String str) {
        if (str == null) {
            return;
        }
        int len = str.length();
        int pos = 0;
        while (pos < len) {
            int remainder = len - pos;
            if (remainder >= 4 && str.charAt(pos) == '^' && str.charAt(pos + 2) == '-') {
                this.set.add(CharRange.isNotIn(str.charAt(pos + 1), str.charAt(pos + 3)));
                pos += 4;
                continue;
            }
            if (remainder >= 3 && str.charAt(pos + 1) == '-') {
                this.set.add(CharRange.isIn(str.charAt(pos), str.charAt(pos + 2)));
                pos += 3;
                continue;
            }
            if (remainder >= 2 && str.charAt(pos) == '^') {
                this.set.add(CharRange.isNot(str.charAt(pos + 1)));
                pos += 2;
                continue;
            }
            this.set.add(CharRange.is(str.charAt(pos)));
            ++pos;
        }
    }

    CharRange[] getCharRanges() {
        return this.set.toArray(CharRange.EMPTY_ARRAY);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean contains(char ch) {
        Set<CharRange> set = this.set;
        synchronized (set) {
            for (CharRange range : this.set) {
                if (!range.contains(ch)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharSet)) {
            return false;
        }
        CharSet other = (CharSet)obj;
        return this.set.equals(other.set);
    }

    public int hashCode() {
        return 89 + this.set.hashCode();
    }

    public String toString() {
        return this.set.toString();
    }

    static {
        COMMON.put(null, EMPTY);
        COMMON.put("", EMPTY);
        COMMON.put("a-zA-Z", ASCII_ALPHA);
        COMMON.put("A-Za-z", ASCII_ALPHA);
        COMMON.put("a-z", ASCII_ALPHA_LOWER);
        COMMON.put("A-Z", ASCII_ALPHA_UPPER);
        COMMON.put("0-9", ASCII_NUMERIC);
    }
}

