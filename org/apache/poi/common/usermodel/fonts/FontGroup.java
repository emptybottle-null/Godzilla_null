/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.common.usermodel.fonts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public enum FontGroup {
    LATIN,
    EAST_ASIAN,
    SYMBOL,
    COMPLEX_SCRIPT;

    private static NavigableMap<Integer, Range> UCS_RANGES;

    public static List<FontGroupRange> getFontGroupRanges(String runText) {
        int charCount;
        ArrayList<FontGroupRange> ttrList = new ArrayList<FontGroupRange>();
        if (runText == null || runText.isEmpty()) {
            return ttrList;
        }
        FontGroupRange ttrLast = null;
        int rlen = runText.length();
        for (int i = 0; i < rlen; i += charCount) {
            int cp = runText.codePointAt(i);
            charCount = Character.charCount(cp);
            FontGroup tt = ttrLast != null && " \n\r".indexOf(cp) > -1 ? ttrLast.fontGroup : FontGroup.lookup(cp);
            if (ttrLast == null || ttrLast.fontGroup != tt) {
                ttrLast = new FontGroupRange();
                ttrLast.fontGroup = tt;
                ttrList.add(ttrLast);
            }
            FontGroupRange fontGroupRange = ttrLast;
            fontGroupRange.len = fontGroupRange.len + charCount;
        }
        return ttrList;
    }

    public static FontGroup getFontGroupFirst(String runText) {
        return runText == null || runText.isEmpty() ? LATIN : FontGroup.lookup(runText.codePointAt(0));
    }

    private static FontGroup lookup(int codepoint) {
        Map.Entry<Integer, Range> entry = UCS_RANGES.floorEntry(codepoint);
        Range range = entry != null ? entry.getValue() : null;
        return range != null && codepoint <= range.upper ? range.fontGroup : EAST_ASIAN;
    }

    static {
        UCS_RANGES = new TreeMap<Integer, Range>();
        UCS_RANGES.put(0, new Range(127, LATIN));
        UCS_RANGES.put(128, new Range(166, LATIN));
        UCS_RANGES.put(169, new Range(175, LATIN));
        UCS_RANGES.put(178, new Range(179, LATIN));
        UCS_RANGES.put(181, new Range(214, LATIN));
        UCS_RANGES.put(216, new Range(246, LATIN));
        UCS_RANGES.put(248, new Range(1423, LATIN));
        UCS_RANGES.put(1424, new Range(1871, COMPLEX_SCRIPT));
        UCS_RANGES.put(1920, new Range(1983, COMPLEX_SCRIPT));
        UCS_RANGES.put(2304, new Range(4255, COMPLEX_SCRIPT));
        UCS_RANGES.put(4256, new Range(4351, LATIN));
        UCS_RANGES.put(4608, new Range(4991, LATIN));
        UCS_RANGES.put(5024, new Range(6015, LATIN));
        UCS_RANGES.put(7424, new Range(7551, LATIN));
        UCS_RANGES.put(7680, new Range(8191, LATIN));
        UCS_RANGES.put(6016, new Range(6319, COMPLEX_SCRIPT));
        UCS_RANGES.put(8192, new Range(8203, LATIN));
        UCS_RANGES.put(8204, new Range(8207, COMPLEX_SCRIPT));
        UCS_RANGES.put(8208, new Range(8233, LATIN));
        UCS_RANGES.put(8234, new Range(8239, COMPLEX_SCRIPT));
        UCS_RANGES.put(8240, new Range(8262, LATIN));
        UCS_RANGES.put(8266, new Range(9311, LATIN));
        UCS_RANGES.put(9840, new Range(9841, COMPLEX_SCRIPT));
        UCS_RANGES.put(10176, new Range(11263, LATIN));
        UCS_RANGES.put(12441, new Range(12442, EAST_ASIAN));
        UCS_RANGES.put(55349, new Range(55349, LATIN));
        UCS_RANGES.put(61440, new Range(61695, SYMBOL));
        UCS_RANGES.put(64256, new Range(64279, LATIN));
        UCS_RANGES.put(64285, new Range(64335, COMPLEX_SCRIPT));
        UCS_RANGES.put(65104, new Range(65135, LATIN));
    }

    private static class Range {
        int upper;
        FontGroup fontGroup;

        Range(int upper, FontGroup fontGroup) {
            this.upper = upper;
            this.fontGroup = fontGroup;
        }
    }

    public static class FontGroupRange {
        private int len;
        private FontGroup fontGroup;

        public int getLength() {
            return this.len;
        }

        public FontGroup getFontGroup() {
            return this.fontGroup;
        }
    }
}

