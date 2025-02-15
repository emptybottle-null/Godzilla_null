/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.SubCharBuffer;
import com.jediterm.terminal.util.Pair;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SubstringFinder {
    private final String myPattern;
    private final int myPatternHash;
    private int myCurrentHash;
    private int myCurrentLength;
    private final ArrayList<TextToken> myTokens = Lists.newArrayList();
    private int myFirstIndex;
    private int myPower = 0;
    private final FindResult myResult = new FindResult();
    private boolean myIgnoreCase;

    public SubstringFinder(String pattern, boolean ignoreCase) {
        this.myIgnoreCase = ignoreCase;
        this.myPattern = ignoreCase ? pattern.toLowerCase() : pattern;
        this.myPatternHash = this.myPattern.hashCode();
    }

    public void nextChar(int x, int y, CharBuffer characters, int index) {
        if (this.myTokens.size() == 0 || this.myTokens.get((int)(this.myTokens.size() - 1)).buf != characters) {
            this.myTokens.add(new TextToken(x, y, characters));
        }
        if (this.myCurrentLength == this.myPattern.length()) {
            this.myCurrentHash -= this.hashCodeForChar(this.myTokens.get((int)0).buf.charAt(this.myFirstIndex));
            if (this.myFirstIndex + 1 == this.myTokens.get((int)0).buf.length()) {
                this.myFirstIndex = 0;
                this.myTokens.remove(0);
            } else {
                ++this.myFirstIndex;
            }
        } else {
            ++this.myCurrentLength;
            this.myPower = this.myPower == 0 ? 1 : (this.myPower *= 31);
        }
        this.myCurrentHash = 31 * this.myCurrentHash + this.charHash(characters.charAt(index));
        if (this.myCurrentLength == this.myPattern.length() && this.myCurrentHash == this.myPatternHash) {
            FindResult.FindItem item = new FindResult.FindItem(this.myTokens, this.myFirstIndex, index, -1);
            String itemText = item.getText();
            boolean matched = this.myPattern.equals(this.myIgnoreCase ? itemText.toLowerCase() : itemText);
            if (matched && this.accept(item)) {
                this.myResult.patternMatched(this.myTokens, this.myFirstIndex, index);
                this.myCurrentHash = 0;
                this.myCurrentLength = 0;
                this.myPower = 0;
                this.myTokens.clear();
                if (index + 1 < characters.length()) {
                    this.myFirstIndex = index + 1;
                    this.myTokens.add(new TextToken(x, y, characters));
                } else {
                    this.myFirstIndex = 0;
                }
            }
        }
    }

    public boolean accept(@NotNull FindResult.FindItem item) {
        if (item == null) {
            SubstringFinder.$$$reportNull$$$0(0);
        }
        return true;
    }

    private int charHash(char c) {
        return this.myIgnoreCase ? Character.toLowerCase(c) : c;
    }

    private int hashCodeForChar(char charAt) {
        return this.myPower * this.charHash(charAt);
    }

    public FindResult getResult() {
        return this.myResult;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jediterm/terminal/SubstringFinder", "accept"));
    }

    private static class TextToken {
        final CharBuffer buf;
        final int x;
        final int y;

        private TextToken(int x, int y, CharBuffer buf) {
            this.x = x;
            this.y = y;
            this.buf = buf;
        }
    }

    public static class FindResult {
        private final List<FindItem> items = Lists.newArrayList();
        private final Map<CharBuffer, List<Pair<Integer, Integer>>> ranges = Maps.newHashMap();
        private int currentFindItem = 0;

        public List<Pair<Integer, Integer>> getRanges(CharBuffer characters) {
            if (characters instanceof SubCharBuffer) {
                SubCharBuffer subCharBuffer = (SubCharBuffer)characters;
                List<Pair<Integer, Integer>> pairs = this.ranges.get(subCharBuffer.getParent());
                if (pairs != null) {
                    ArrayList<Pair<Integer, Integer>> filtered = new ArrayList<Pair<Integer, Integer>>();
                    for (Pair<Integer, Integer> pair : pairs) {
                        Pair<Integer, Integer> intersected = this.intersect(pair, subCharBuffer.getOffset(), subCharBuffer.getOffset() + subCharBuffer.length());
                        if (intersected == null) continue;
                        filtered.add(Pair.create((Integer)intersected.first - subCharBuffer.getOffset(), (Integer)intersected.second - subCharBuffer.getOffset()));
                    }
                    return filtered;
                }
                return null;
            }
            return this.ranges.get(characters);
        }

        @Nullable
        private Pair<Integer, Integer> intersect(@NotNull Pair<Integer, Integer> interval, int a, int b) {
            int end;
            int start;
            if (interval == null) {
                FindResult.$$$reportNull$$$0(0);
            }
            return (start = Math.max((Integer)interval.first, a)) < (end = Math.min((Integer)interval.second, b)) ? Pair.create(start, end) : null;
        }

        public void patternMatched(ArrayList<TextToken> tokens, int firstIndex, int lastIndex) {
            Pair<Integer, Integer> range;
            if (tokens.size() > 1) {
                range = Pair.create(firstIndex, tokens.get((int)0).buf.length());
                this.put(tokens.get((int)0).buf, range);
            } else {
                range = Pair.create(firstIndex, lastIndex + 1);
                this.put(tokens.get((int)0).buf, range);
            }
            for (int i = 1; i < tokens.size() - 1; ++i) {
                this.put(tokens.get((int)i).buf, Pair.create(0, tokens.get((int)i).buf.length()));
            }
            if (tokens.size() > 1) {
                Pair<Integer, Integer> range2 = Pair.create(0, lastIndex + 1);
                this.put(tokens.get((int)(tokens.size() - 1)).buf, range2);
            }
            this.items.add(new FindItem(tokens, firstIndex, lastIndex, this.items.size() + 1));
        }

        private void put(CharBuffer characters, Pair<Integer, Integer> range) {
            if (this.ranges.containsKey(characters)) {
                this.ranges.get(characters).add(range);
            } else {
                this.ranges.put(characters, Lists.newArrayList(range));
            }
        }

        public List<FindItem> getItems() {
            return this.items;
        }

        public FindItem prevFindItem() {
            if (this.items.isEmpty()) {
                return null;
            }
            ++this.currentFindItem;
            this.currentFindItem %= this.items.size();
            return this.items.get(this.currentFindItem);
        }

        public FindItem nextFindItem() {
            if (this.items.isEmpty()) {
                return null;
            }
            --this.currentFindItem;
            this.currentFindItem = (this.currentFindItem + this.items.size()) % this.items.size();
            return this.items.get(this.currentFindItem);
        }

        private static /* synthetic */ void $$$reportNull$$$0(int n) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "interval", "com/jediterm/terminal/SubstringFinder$FindResult", "intersect"));
        }

        public static class FindItem {
            final ArrayList<TextToken> tokens;
            final int firstIndex;
            final int lastIndex;
            final int index;

            private FindItem(ArrayList<TextToken> tokens, int firstIndex, int lastIndex, int index) {
                this.tokens = Lists.newArrayList(tokens);
                this.firstIndex = firstIndex;
                this.lastIndex = lastIndex;
                this.index = index;
            }

            @NotNull
            public String getText() {
                Pair<Integer, Integer> range;
                StringBuilder b = new StringBuilder();
                if (this.tokens.size() > 1) {
                    range = Pair.create(this.firstIndex, this.tokens.get((int)0).buf.length());
                    b.append(this.tokens.get((int)0).buf.subBuffer(range));
                } else {
                    range = Pair.create(this.firstIndex, this.lastIndex + 1);
                    b.append(this.tokens.get((int)0).buf.subBuffer(range));
                }
                for (int i = 1; i < this.tokens.size() - 1; ++i) {
                    b.append(this.tokens.get((int)i).buf);
                }
                if (this.tokens.size() > 1) {
                    Pair<Integer, Integer> range2 = Pair.create(0, this.lastIndex + 1);
                    b.append(this.tokens.get((int)(this.tokens.size() - 1)).buf.subBuffer(range2));
                }
                String string = b.toString();
                if (string == null) {
                    FindItem.$$$reportNull$$$0(0);
                }
                return string;
            }

            public String toString() {
                return this.getText();
            }

            public int getIndex() {
                return this.index;
            }

            public Point getStart() {
                return new Point(this.tokens.get((int)0).x + this.firstIndex, this.tokens.get((int)0).y);
            }

            public Point getEnd() {
                return new Point(this.tokens.get((int)(this.tokens.size() - 1)).x + this.lastIndex, this.tokens.get((int)(this.tokens.size() - 1)).y);
            }

            private static /* synthetic */ void $$$reportNull$$$0(int n) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jediterm/terminal/SubstringFinder$FindResult$FindItem", "getText"));
            }
        }
    }
}

