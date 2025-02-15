/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.AbstractIterator;
import com.google.common.base.CharMatcher;
import com.google.common.base.CommonMatcher;
import com.google.common.base.CommonPattern;
import com.google.common.base.JdkPattern;
import com.google.common.base.Joiner;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@GwtCompatible(emulated=true)
public final class Splitter {
    private final CharMatcher trimmer;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final int limit;

    private Splitter(Strategy strategy) {
        this(strategy, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    private Splitter(Strategy strategy, boolean omitEmptyStrings, CharMatcher trimmer, int limit) {
        this.strategy = strategy;
        this.omitEmptyStrings = omitEmptyStrings;
        this.trimmer = trimmer;
        this.limit = limit;
    }

    public static Splitter on(char separator) {
        return Splitter.on(CharMatcher.is(separator));
    }

    public static Splitter on(final CharMatcher separatorMatcher) {
        Preconditions.checkNotNull(separatorMatcher);
        return new Splitter(new Strategy(){

            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit){

                    @Override
                    int separatorStart(int start) {
                        return separatorMatcher.indexIn(this.toSplit, start);
                    }

                    @Override
                    int separatorEnd(int separatorPosition) {
                        return separatorPosition + 1;
                    }
                };
            }
        });
    }

    public static Splitter on(final String separator) {
        Preconditions.checkArgument(separator.length() != 0, "The separator may not be the empty string.");
        if (separator.length() == 1) {
            return Splitter.on(separator.charAt(0));
        }
        return new Splitter(new Strategy(){

            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit){

                    @Override
                    public int separatorStart(int start) {
                        int separatorLength = separator.length();
                        int last = this.toSplit.length() - separatorLength;
                        block0: for (int p = start; p <= last; ++p) {
                            for (int i = 0; i < separatorLength; ++i) {
                                if (this.toSplit.charAt(i + p) != separator.charAt(i)) continue block0;
                            }
                            return p;
                        }
                        return -1;
                    }

                    @Override
                    public int separatorEnd(int separatorPosition) {
                        return separatorPosition + separator.length();
                    }
                };
            }
        });
    }

    @GwtIncompatible
    public static Splitter on(Pattern separatorPattern) {
        return Splitter.on(new JdkPattern(separatorPattern));
    }

    private static Splitter on(final CommonPattern separatorPattern) {
        Preconditions.checkArgument(!separatorPattern.matcher("").matches(), "The pattern may not match the empty string: %s", (Object)separatorPattern);
        return new Splitter(new Strategy(){

            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                final CommonMatcher matcher = separatorPattern.matcher(toSplit);
                return new SplittingIterator(splitter, toSplit){

                    @Override
                    public int separatorStart(int start) {
                        return matcher.find(start) ? matcher.start() : -1;
                    }

                    @Override
                    public int separatorEnd(int separatorPosition) {
                        return matcher.end();
                    }
                };
            }
        });
    }

    @GwtIncompatible
    public static Splitter onPattern(String separatorPattern) {
        return Splitter.on(Platform.compilePattern(separatorPattern));
    }

    public static Splitter fixedLength(final int length) {
        Preconditions.checkArgument(length > 0, "The length may not be less than 1");
        return new Splitter(new Strategy(){

            public SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
                return new SplittingIterator(splitter, toSplit){

                    @Override
                    public int separatorStart(int start) {
                        int nextChunkStart = start + length;
                        return nextChunkStart < this.toSplit.length() ? nextChunkStart : -1;
                    }

                    @Override
                    public int separatorEnd(int separatorPosition) {
                        return separatorPosition;
                    }
                };
            }
        });
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Splitter limit(int limit) {
        Preconditions.checkArgument(limit > 0, "must be greater than zero: %s", limit);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, limit);
    }

    public Splitter trimResults() {
        return this.trimResults(CharMatcher.whitespace());
    }

    public Splitter trimResults(CharMatcher trimmer) {
        Preconditions.checkNotNull(trimmer);
        return new Splitter(this.strategy, this.omitEmptyStrings, trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence sequence) {
        Preconditions.checkNotNull(sequence);
        return new Iterable<String>(){

            @Override
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(sequence);
            }

            public String toString() {
                return Joiner.on(", ").appendTo(new StringBuilder().append('['), (Iterable<?>)this).append(']').toString();
            }
        };
    }

    private Iterator<String> splittingIterator(CharSequence sequence) {
        return this.strategy.iterator(this, sequence);
    }

    public List<String> splitToList(CharSequence sequence) {
        Preconditions.checkNotNull(sequence);
        Iterator<String> iterator = this.splittingIterator(sequence);
        ArrayList<String> result = new ArrayList<String>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return Collections.unmodifiableList(result);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(String separator) {
        return this.withKeyValueSeparator(Splitter.on(separator));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(char separator) {
        return this.withKeyValueSeparator(Splitter.on(separator));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(Splitter keyValueSplitter) {
        return new MapSplitter(this, keyValueSplitter);
    }

    private static abstract class SplittingIterator
    extends AbstractIterator<String> {
        final CharSequence toSplit;
        final CharMatcher trimmer;
        final boolean omitEmptyStrings;
        int offset = 0;
        int limit;

        abstract int separatorStart(int var1);

        abstract int separatorEnd(int var1);

        protected SplittingIterator(Splitter splitter, CharSequence toSplit) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = toSplit;
        }

        @Override
        protected String computeNext() {
            int nextStart = this.offset;
            while (this.offset != -1) {
                int end;
                int start = nextStart;
                int separatorPosition = this.separatorStart(this.offset);
                if (separatorPosition == -1) {
                    end = this.toSplit.length();
                    this.offset = -1;
                } else {
                    end = separatorPosition;
                    this.offset = this.separatorEnd(separatorPosition);
                }
                if (this.offset == nextStart) {
                    ++this.offset;
                    if (this.offset <= this.toSplit.length()) continue;
                    this.offset = -1;
                    continue;
                }
                while (start < end && this.trimmer.matches(this.toSplit.charAt(start))) {
                    ++start;
                }
                while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                    --end;
                }
                if (this.omitEmptyStrings && start == end) {
                    nextStart = this.offset;
                    continue;
                }
                if (this.limit == 1) {
                    this.offset = -1;
                    for (end = this.toSplit.length(); end > start && this.trimmer.matches(this.toSplit.charAt(end - 1)); --end) {
                    }
                } else {
                    --this.limit;
                }
                return this.toSplit.subSequence(start, end).toString();
            }
            return (String)this.endOfData();
        }
    }

    private static interface Strategy {
        public Iterator<String> iterator(Splitter var1, CharSequence var2);
    }

    @Beta
    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter outerSplitter;
        private final Splitter entrySplitter;

        private MapSplitter(Splitter outerSplitter, Splitter entrySplitter) {
            this.outerSplitter = outerSplitter;
            this.entrySplitter = Preconditions.checkNotNull(entrySplitter);
        }

        public Map<String, String> split(CharSequence sequence) {
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            for (String entry : this.outerSplitter.split(sequence)) {
                Iterator entryFields = this.entrySplitter.splittingIterator(entry);
                Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, (Object)entry);
                String key = (String)entryFields.next();
                Preconditions.checkArgument(!map.containsKey(key), "Duplicate key [%s] found.", (Object)key);
                Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, (Object)entry);
                String value = (String)entryFields.next();
                map.put(key, value);
                Preconditions.checkArgument(!entryFields.hasNext(), INVALID_ENTRY_MESSAGE, (Object)entry);
            }
            return Collections.unmodifiableMap(map);
        }
    }
}

