/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrMatcher;

@Deprecated
public class StrTokenizer
implements ListIterator<String>,
Cloneable {
    private static final StrTokenizer CSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
    private static final StrTokenizer TSV_TOKENIZER_PROTOTYPE;
    private char[] chars;
    private String[] tokens;
    private int tokenPos;
    private StrMatcher delimMatcher = StrMatcher.splitMatcher();
    private StrMatcher quoteMatcher = StrMatcher.noneMatcher();
    private StrMatcher ignoredMatcher = StrMatcher.noneMatcher();
    private StrMatcher trimmerMatcher = StrMatcher.noneMatcher();
    private boolean emptyAsNull;
    private boolean ignoreEmptyTokens = true;

    private static StrTokenizer getCSVClone() {
        return (StrTokenizer)CSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getCSVInstance() {
        return StrTokenizer.getCSVClone();
    }

    public static StrTokenizer getCSVInstance(String input) {
        StrTokenizer tok = StrTokenizer.getCSVClone();
        tok.reset(input);
        return tok;
    }

    public static StrTokenizer getCSVInstance(char[] input) {
        StrTokenizer tok = StrTokenizer.getCSVClone();
        tok.reset(input);
        return tok;
    }

    private static StrTokenizer getTSVClone() {
        return (StrTokenizer)TSV_TOKENIZER_PROTOTYPE.clone();
    }

    public static StrTokenizer getTSVInstance() {
        return StrTokenizer.getTSVClone();
    }

    public static StrTokenizer getTSVInstance(String input) {
        StrTokenizer tok = StrTokenizer.getTSVClone();
        tok.reset(input);
        return tok;
    }

    public static StrTokenizer getTSVInstance(char[] input) {
        StrTokenizer tok = StrTokenizer.getTSVClone();
        tok.reset(input);
        return tok;
    }

    public StrTokenizer() {
        this.chars = null;
    }

    public StrTokenizer(String input) {
        this.chars = (char[])(input != null ? input.toCharArray() : null);
    }

    public StrTokenizer(String input, char delim) {
        this(input);
        this.setDelimiterChar(delim);
    }

    public StrTokenizer(String input, String delim) {
        this(input);
        this.setDelimiterString(delim);
    }

    public StrTokenizer(String input, StrMatcher delim) {
        this(input);
        this.setDelimiterMatcher(delim);
    }

    public StrTokenizer(String input, char delim, char quote) {
        this(input, delim);
        this.setQuoteChar(quote);
    }

    public StrTokenizer(String input, StrMatcher delim, StrMatcher quote) {
        this(input, delim);
        this.setQuoteMatcher(quote);
    }

    public StrTokenizer(char[] input) {
        this.chars = ArrayUtils.clone(input);
    }

    public StrTokenizer(char[] input, char delim) {
        this(input);
        this.setDelimiterChar(delim);
    }

    public StrTokenizer(char[] input, String delim) {
        this(input);
        this.setDelimiterString(delim);
    }

    public StrTokenizer(char[] input, StrMatcher delim) {
        this(input);
        this.setDelimiterMatcher(delim);
    }

    public StrTokenizer(char[] input, char delim, char quote) {
        this(input, delim);
        this.setQuoteChar(quote);
    }

    public StrTokenizer(char[] input, StrMatcher delim, StrMatcher quote) {
        this(input, delim);
        this.setQuoteMatcher(quote);
    }

    public int size() {
        this.checkTokenized();
        return this.tokens.length;
    }

    public String nextToken() {
        if (this.hasNext()) {
            return this.tokens[this.tokenPos++];
        }
        return null;
    }

    public String previousToken() {
        if (this.hasPrevious()) {
            return this.tokens[--this.tokenPos];
        }
        return null;
    }

    public String[] getTokenArray() {
        this.checkTokenized();
        return (String[])this.tokens.clone();
    }

    public List<String> getTokenList() {
        this.checkTokenized();
        ArrayList<String> list = new ArrayList<String>(this.tokens.length);
        list.addAll(Arrays.asList(this.tokens));
        return list;
    }

    public StrTokenizer reset() {
        this.tokenPos = 0;
        this.tokens = null;
        return this;
    }

    public StrTokenizer reset(String input) {
        this.reset();
        this.chars = (char[])(input != null ? input.toCharArray() : null);
        return this;
    }

    public StrTokenizer reset(char[] input) {
        this.reset();
        this.chars = ArrayUtils.clone(input);
        return this;
    }

    @Override
    public boolean hasNext() {
        this.checkTokenized();
        return this.tokenPos < this.tokens.length;
    }

    @Override
    public String next() {
        if (this.hasNext()) {
            return this.tokens[this.tokenPos++];
        }
        throw new NoSuchElementException();
    }

    @Override
    public int nextIndex() {
        return this.tokenPos;
    }

    @Override
    public boolean hasPrevious() {
        this.checkTokenized();
        return this.tokenPos > 0;
    }

    @Override
    public String previous() {
        if (this.hasPrevious()) {
            return this.tokens[--this.tokenPos];
        }
        throw new NoSuchElementException();
    }

    @Override
    public int previousIndex() {
        return this.tokenPos - 1;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is unsupported");
    }

    @Override
    public void set(String obj) {
        throw new UnsupportedOperationException("set() is unsupported");
    }

    @Override
    public void add(String obj) {
        throw new UnsupportedOperationException("add() is unsupported");
    }

    private void checkTokenized() {
        if (this.tokens == null) {
            if (this.chars == null) {
                List<String> split = this.tokenize(null, 0, 0);
                this.tokens = split.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
            } else {
                List<String> split = this.tokenize(this.chars, 0, this.chars.length);
                this.tokens = split.toArray(ArrayUtils.EMPTY_STRING_ARRAY);
            }
        }
    }

    protected List<String> tokenize(char[] srcChars, int offset, int count) {
        if (srcChars == null || count == 0) {
            return Collections.emptyList();
        }
        StrBuilder buf = new StrBuilder();
        ArrayList<String> tokenList = new ArrayList<String>();
        int pos = offset;
        while (pos >= 0 && pos < count) {
            if ((pos = this.readNextToken(srcChars, pos, count, buf, tokenList)) < count) continue;
            this.addToken(tokenList, "");
        }
        return tokenList;
    }

    private void addToken(List<String> list, String tok) {
        if (StringUtils.isEmpty(tok)) {
            if (this.isIgnoreEmptyTokens()) {
                return;
            }
            if (this.isEmptyTokenAsNull()) {
                tok = null;
            }
        }
        list.add(tok);
    }

    private int readNextToken(char[] srcChars, int start, int len, StrBuilder workArea, List<String> tokenList) {
        int removeLen;
        while (start < len && (removeLen = Math.max(this.getIgnoredMatcher().isMatch(srcChars, start, start, len), this.getTrimmerMatcher().isMatch(srcChars, start, start, len))) != 0 && this.getDelimiterMatcher().isMatch(srcChars, start, start, len) <= 0 && this.getQuoteMatcher().isMatch(srcChars, start, start, len) <= 0) {
            start += removeLen;
        }
        if (start >= len) {
            this.addToken(tokenList, "");
            return -1;
        }
        int delimLen = this.getDelimiterMatcher().isMatch(srcChars, start, start, len);
        if (delimLen > 0) {
            this.addToken(tokenList, "");
            return start + delimLen;
        }
        int quoteLen = this.getQuoteMatcher().isMatch(srcChars, start, start, len);
        if (quoteLen > 0) {
            return this.readWithQuotes(srcChars, start + quoteLen, len, workArea, tokenList, start, quoteLen);
        }
        return this.readWithQuotes(srcChars, start, len, workArea, tokenList, 0, 0);
    }

    private int readWithQuotes(char[] srcChars, int start, int len, StrBuilder workArea, List<String> tokenList, int quoteStart, int quoteLen) {
        workArea.clear();
        int pos = start;
        boolean quoting = quoteLen > 0;
        int trimStart = 0;
        while (pos < len) {
            if (quoting) {
                if (this.isQuote(srcChars, pos, len, quoteStart, quoteLen)) {
                    if (this.isQuote(srcChars, pos + quoteLen, len, quoteStart, quoteLen)) {
                        workArea.append(srcChars, pos, quoteLen);
                        pos += quoteLen * 2;
                        trimStart = workArea.size();
                        continue;
                    }
                    quoting = false;
                    pos += quoteLen;
                    continue;
                }
                workArea.append(srcChars[pos++]);
                trimStart = workArea.size();
                continue;
            }
            int delimLen = this.getDelimiterMatcher().isMatch(srcChars, pos, start, len);
            if (delimLen > 0) {
                this.addToken(tokenList, workArea.substring(0, trimStart));
                return pos + delimLen;
            }
            if (quoteLen > 0 && this.isQuote(srcChars, pos, len, quoteStart, quoteLen)) {
                quoting = true;
                pos += quoteLen;
                continue;
            }
            int ignoredLen = this.getIgnoredMatcher().isMatch(srcChars, pos, start, len);
            if (ignoredLen > 0) {
                pos += ignoredLen;
                continue;
            }
            int trimmedLen = this.getTrimmerMatcher().isMatch(srcChars, pos, start, len);
            if (trimmedLen > 0) {
                workArea.append(srcChars, pos, trimmedLen);
                pos += trimmedLen;
                continue;
            }
            workArea.append(srcChars[pos++]);
            trimStart = workArea.size();
        }
        this.addToken(tokenList, workArea.substring(0, trimStart));
        return -1;
    }

    private boolean isQuote(char[] srcChars, int pos, int len, int quoteStart, int quoteLen) {
        for (int i = 0; i < quoteLen; ++i) {
            if (pos + i < len && srcChars[pos + i] == srcChars[quoteStart + i]) continue;
            return false;
        }
        return true;
    }

    public StrMatcher getDelimiterMatcher() {
        return this.delimMatcher;
    }

    public StrTokenizer setDelimiterMatcher(StrMatcher delim) {
        this.delimMatcher = delim == null ? StrMatcher.noneMatcher() : delim;
        return this;
    }

    public StrTokenizer setDelimiterChar(char delim) {
        return this.setDelimiterMatcher(StrMatcher.charMatcher(delim));
    }

    public StrTokenizer setDelimiterString(String delim) {
        return this.setDelimiterMatcher(StrMatcher.stringMatcher(delim));
    }

    public StrMatcher getQuoteMatcher() {
        return this.quoteMatcher;
    }

    public StrTokenizer setQuoteMatcher(StrMatcher quote) {
        if (quote != null) {
            this.quoteMatcher = quote;
        }
        return this;
    }

    public StrTokenizer setQuoteChar(char quote) {
        return this.setQuoteMatcher(StrMatcher.charMatcher(quote));
    }

    public StrMatcher getIgnoredMatcher() {
        return this.ignoredMatcher;
    }

    public StrTokenizer setIgnoredMatcher(StrMatcher ignored) {
        if (ignored != null) {
            this.ignoredMatcher = ignored;
        }
        return this;
    }

    public StrTokenizer setIgnoredChar(char ignored) {
        return this.setIgnoredMatcher(StrMatcher.charMatcher(ignored));
    }

    public StrMatcher getTrimmerMatcher() {
        return this.trimmerMatcher;
    }

    public StrTokenizer setTrimmerMatcher(StrMatcher trimmer) {
        if (trimmer != null) {
            this.trimmerMatcher = trimmer;
        }
        return this;
    }

    public boolean isEmptyTokenAsNull() {
        return this.emptyAsNull;
    }

    public StrTokenizer setEmptyTokenAsNull(boolean emptyAsNull) {
        this.emptyAsNull = emptyAsNull;
        return this;
    }

    public boolean isIgnoreEmptyTokens() {
        return this.ignoreEmptyTokens;
    }

    public StrTokenizer setIgnoreEmptyTokens(boolean ignoreEmptyTokens) {
        this.ignoreEmptyTokens = ignoreEmptyTokens;
        return this;
    }

    public String getContent() {
        if (this.chars == null) {
            return null;
        }
        return new String(this.chars);
    }

    public Object clone() {
        try {
            return this.cloneReset();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    Object cloneReset() throws CloneNotSupportedException {
        StrTokenizer cloned = (StrTokenizer)super.clone();
        if (cloned.chars != null) {
            cloned.chars = (char[])cloned.chars.clone();
        }
        cloned.reset();
        return cloned;
    }

    public String toString() {
        if (this.tokens == null) {
            return "StrTokenizer[not tokenized yet]";
        }
        return "StrTokenizer" + this.getTokenList();
    }

    static {
        CSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.commaMatcher());
        CSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        CSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
        CSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
        CSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        CSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
        TSV_TOKENIZER_PROTOTYPE = new StrTokenizer();
        TSV_TOKENIZER_PROTOTYPE.setDelimiterMatcher(StrMatcher.tabMatcher());
        TSV_TOKENIZER_PROTOTYPE.setQuoteMatcher(StrMatcher.doubleQuoteMatcher());
        TSV_TOKENIZER_PROTOTYPE.setIgnoredMatcher(StrMatcher.noneMatcher());
        TSV_TOKENIZER_PROTOTYPE.setTrimmerMatcher(StrMatcher.trimMatcher());
        TSV_TOKENIZER_PROTOTYPE.setEmptyTokenAsNull(false);
        TSV_TOKENIZER_PROTOTYPE.setIgnoreEmptyTokens(false);
    }
}

