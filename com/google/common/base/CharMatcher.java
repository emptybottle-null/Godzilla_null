/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Platform;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.SmallCharMatcher;
import java.util.Arrays;
import java.util.BitSet;

@GwtCompatible(emulated=true)
public abstract class CharMatcher
implements Predicate<Character> {
    private static final int DISTINCT_CHARS = 65536;

    public static CharMatcher any() {
        return Any.INSTANCE;
    }

    public static CharMatcher none() {
        return None.INSTANCE;
    }

    public static CharMatcher whitespace() {
        return Whitespace.INSTANCE;
    }

    public static CharMatcher breakingWhitespace() {
        return BreakingWhitespace.INSTANCE;
    }

    public static CharMatcher ascii() {
        return Ascii.INSTANCE;
    }

    @Deprecated
    public static CharMatcher digit() {
        return Digit.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaDigit() {
        return JavaDigit.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetter() {
        return JavaLetter.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLetterOrDigit() {
        return JavaLetterOrDigit.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaUpperCase() {
        return JavaUpperCase.INSTANCE;
    }

    @Deprecated
    public static CharMatcher javaLowerCase() {
        return JavaLowerCase.INSTANCE;
    }

    public static CharMatcher javaIsoControl() {
        return JavaIsoControl.INSTANCE;
    }

    @Deprecated
    public static CharMatcher invisible() {
        return Invisible.INSTANCE;
    }

    @Deprecated
    public static CharMatcher singleWidth() {
        return SingleWidth.INSTANCE;
    }

    public static CharMatcher is(char match) {
        return new Is(match);
    }

    public static CharMatcher isNot(char match) {
        return new IsNot(match);
    }

    public static CharMatcher anyOf(CharSequence sequence) {
        switch (sequence.length()) {
            case 0: {
                return CharMatcher.none();
            }
            case 1: {
                return CharMatcher.is(sequence.charAt(0));
            }
            case 2: {
                return CharMatcher.isEither(sequence.charAt(0), sequence.charAt(1));
            }
        }
        return new AnyOf(sequence);
    }

    public static CharMatcher noneOf(CharSequence sequence) {
        return CharMatcher.anyOf(sequence).negate();
    }

    public static CharMatcher inRange(char startInclusive, char endInclusive) {
        return new InRange(startInclusive, endInclusive);
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        return predicate instanceof CharMatcher ? (CharMatcher)predicate : new ForPredicate(predicate);
    }

    protected CharMatcher() {
    }

    public abstract boolean matches(char var1);

    public CharMatcher negate() {
        return new Negated(this);
    }

    public CharMatcher and(CharMatcher other) {
        return new And(this, other);
    }

    public CharMatcher or(CharMatcher other) {
        return new Or(this, other);
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    @GwtIncompatible
    CharMatcher precomputedInternal() {
        BitSet table = new BitSet();
        this.setBits(table);
        int totalCharacters = table.cardinality();
        if (totalCharacters * 2 <= 65536) {
            return CharMatcher.precomputedPositive(totalCharacters, table, this.toString());
        }
        table.flip(0, 65536);
        int negatedCharacters = 65536 - totalCharacters;
        String suffix = ".negate()";
        final String description = this.toString();
        String negatedDescription = description.endsWith(suffix) ? description.substring(0, description.length() - suffix.length()) : description + suffix;
        return new NegatedFastMatcher(CharMatcher.precomputedPositive(negatedCharacters, table, negatedDescription)){

            @Override
            public String toString() {
                return description;
            }
        };
    }

    @GwtIncompatible
    private static CharMatcher precomputedPositive(int totalCharacters, BitSet table, String description) {
        switch (totalCharacters) {
            case 0: {
                return CharMatcher.none();
            }
            case 1: {
                return CharMatcher.is((char)table.nextSetBit(0));
            }
            case 2: {
                char c1 = (char)table.nextSetBit(0);
                char c2 = (char)table.nextSetBit(c1 + '\u0001');
                return CharMatcher.isEither(c1, c2);
            }
        }
        return CharMatcher.isSmall(totalCharacters, table.length()) ? SmallCharMatcher.from(table, description) : new BitSetMatcher(table, description);
    }

    @GwtIncompatible
    private static boolean isSmall(int totalCharacters, int tableLength) {
        return totalCharacters <= 1023 && tableLength > totalCharacters * 4 * 16;
    }

    @GwtIncompatible
    void setBits(BitSet table) {
        for (int c = 65535; c >= 0; --c) {
            if (!this.matches((char)c)) continue;
            table.set(c);
        }
    }

    public boolean matchesAnyOf(CharSequence sequence) {
        return !this.matchesNoneOf(sequence);
    }

    public boolean matchesAllOf(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; --i) {
            if (this.matches(sequence.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence sequence) {
        return this.indexIn(sequence) == -1;
    }

    public int indexIn(CharSequence sequence) {
        return this.indexIn(sequence, 0);
    }

    public int indexIn(CharSequence sequence, int start) {
        int length = sequence.length();
        Preconditions.checkPositionIndex(start, length);
        for (int i = start; i < length; ++i) {
            if (!this.matches(sequence.charAt(i))) continue;
            return i;
        }
        return -1;
    }

    public int lastIndexIn(CharSequence sequence) {
        for (int i = sequence.length() - 1; i >= 0; --i) {
            if (!this.matches(sequence.charAt(i))) continue;
            return i;
        }
        return -1;
    }

    public int countIn(CharSequence sequence) {
        int count = 0;
        for (int i = 0; i < sequence.length(); ++i) {
            if (!this.matches(sequence.charAt(i))) continue;
            ++count;
        }
        return count;
    }

    public String removeFrom(CharSequence sequence) {
        String string = sequence.toString();
        int pos = this.indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        int spread = 1;
        block0: while (true) {
            ++pos;
            while (pos != chars.length) {
                if (!this.matches(chars[pos])) {
                    chars[pos - spread] = chars[pos];
                    ++pos;
                    continue;
                }
                ++spread;
                continue block0;
            }
            break;
        }
        return new String(chars, 0, pos - spread);
    }

    public String retainFrom(CharSequence sequence) {
        return this.negate().removeFrom(sequence);
    }

    public String replaceFrom(CharSequence sequence, char replacement) {
        String string = sequence.toString();
        int pos = this.indexIn(string);
        if (pos == -1) {
            return string;
        }
        char[] chars = string.toCharArray();
        chars[pos] = replacement;
        for (int i = pos + 1; i < chars.length; ++i) {
            if (!this.matches(chars[i])) continue;
            chars[i] = replacement;
        }
        return new String(chars);
    }

    public String replaceFrom(CharSequence sequence, CharSequence replacement) {
        int replacementLen = replacement.length();
        if (replacementLen == 0) {
            return this.removeFrom(sequence);
        }
        if (replacementLen == 1) {
            return this.replaceFrom(sequence, replacement.charAt(0));
        }
        String string = sequence.toString();
        int pos = this.indexIn(string);
        if (pos == -1) {
            return string;
        }
        int len = string.length();
        StringBuilder buf = new StringBuilder(len * 3 / 2 + 16);
        int oldpos = 0;
        do {
            buf.append(string, oldpos, pos);
            buf.append(replacement);
        } while ((pos = this.indexIn(string, oldpos = pos + 1)) != -1);
        buf.append(string, oldpos, len);
        return buf.toString();
    }

    public String trimFrom(CharSequence sequence) {
        int last;
        int first;
        int len = sequence.length();
        for (first = 0; first < len && this.matches(sequence.charAt(first)); ++first) {
        }
        for (last = len - 1; last > first && this.matches(sequence.charAt(last)); --last) {
        }
        return sequence.subSequence(first, last + 1).toString();
    }

    public String trimLeadingFrom(CharSequence sequence) {
        int len = sequence.length();
        for (int first = 0; first < len; ++first) {
            if (this.matches(sequence.charAt(first))) continue;
            return sequence.subSequence(first, len).toString();
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence sequence) {
        int len = sequence.length();
        for (int last = len - 1; last >= 0; --last) {
            if (this.matches(sequence.charAt(last))) continue;
            return sequence.subSequence(0, last + 1).toString();
        }
        return "";
    }

    public String collapseFrom(CharSequence sequence, char replacement) {
        int len = sequence.length();
        for (int i = 0; i < len; ++i) {
            char c = sequence.charAt(i);
            if (!this.matches(c)) continue;
            if (!(c != replacement || i != len - 1 && this.matches(sequence.charAt(i + 1)))) {
                ++i;
                continue;
            }
            StringBuilder builder = new StringBuilder(len).append(sequence, 0, i).append(replacement);
            return this.finishCollapseFrom(sequence, i + 1, len, replacement, builder, true);
        }
        return sequence.toString();
    }

    public String trimAndCollapseFrom(CharSequence sequence, char replacement) {
        int first;
        int len = sequence.length();
        int last = len - 1;
        for (first = 0; first < len && this.matches(sequence.charAt(first)); ++first) {
        }
        while (last > first && this.matches(sequence.charAt(last))) {
            --last;
        }
        return first == 0 && last == len - 1 ? this.collapseFrom(sequence, replacement) : this.finishCollapseFrom(sequence, first, last + 1, replacement, new StringBuilder(last + 1 - first), false);
    }

    private String finishCollapseFrom(CharSequence sequence, int start, int end, char replacement, StringBuilder builder, boolean inMatchingGroup) {
        for (int i = start; i < end; ++i) {
            char c = sequence.charAt(i);
            if (this.matches(c)) {
                if (inMatchingGroup) continue;
                builder.append(replacement);
                inMatchingGroup = true;
                continue;
            }
            builder.append(c);
            inMatchingGroup = false;
        }
        return builder.toString();
    }

    @Override
    @Deprecated
    public boolean apply(Character character) {
        return this.matches(character.charValue());
    }

    public String toString() {
        return super.toString();
    }

    private static String showCharacter(char c) {
        String hex = "0123456789ABCDEF";
        char[] tmp = new char[]{'\\', 'u', '\u0000', '\u0000', '\u0000', '\u0000'};
        for (int i = 0; i < 4; ++i) {
            tmp[5 - i] = hex.charAt(c & 0xF);
            c = (char)(c >> 4);
        }
        return String.copyValueOf(tmp);
    }

    private static IsEither isEither(char c1, char c2) {
        return new IsEither(c1, c2);
    }

    private static final class ForPredicate
    extends CharMatcher {
        private final Predicate<? super Character> predicate;

        ForPredicate(Predicate<? super Character> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate);
        }

        @Override
        public boolean matches(char c) {
            return this.predicate.apply(Character.valueOf(c));
        }

        @Override
        public boolean apply(Character character) {
            return this.predicate.apply(Preconditions.checkNotNull(character));
        }

        @Override
        public String toString() {
            return "CharMatcher.forPredicate(" + this.predicate + ")";
        }
    }

    private static final class InRange
    extends FastMatcher {
        private final char startInclusive;
        private final char endInclusive;

        InRange(char startInclusive, char endInclusive) {
            Preconditions.checkArgument(endInclusive >= startInclusive);
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
        }

        @Override
        public boolean matches(char c) {
            return this.startInclusive <= c && c <= this.endInclusive;
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            table.set((int)this.startInclusive, this.endInclusive + '\u0001');
        }

        @Override
        public String toString() {
            return "CharMatcher.inRange('" + CharMatcher.showCharacter(this.startInclusive) + "', '" + CharMatcher.showCharacter(this.endInclusive) + "')";
        }
    }

    private static final class AnyOf
    extends CharMatcher {
        private final char[] chars;

        public AnyOf(CharSequence chars) {
            this.chars = chars.toString().toCharArray();
            Arrays.sort(this.chars);
        }

        @Override
        public boolean matches(char c) {
            return Arrays.binarySearch(this.chars, c) >= 0;
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            for (char c : this.chars) {
                table.set(c);
            }
        }

        @Override
        public String toString() {
            StringBuilder description = new StringBuilder("CharMatcher.anyOf(\"");
            for (char c : this.chars) {
                description.append(CharMatcher.showCharacter(c));
            }
            description.append("\")");
            return description.toString();
        }
    }

    private static final class IsEither
    extends FastMatcher {
        private final char match1;
        private final char match2;

        IsEither(char match1, char match2) {
            this.match1 = match1;
            this.match2 = match2;
        }

        @Override
        public boolean matches(char c) {
            return c == this.match1 || c == this.match2;
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            table.set(this.match1);
            table.set(this.match2);
        }

        @Override
        public String toString() {
            return "CharMatcher.anyOf(\"" + CharMatcher.showCharacter(this.match1) + CharMatcher.showCharacter(this.match2) + "\")";
        }
    }

    private static final class IsNot
    extends FastMatcher {
        private final char match;

        IsNot(char match) {
            this.match = match;
        }

        @Override
        public boolean matches(char c) {
            return c != this.match;
        }

        @Override
        public CharMatcher and(CharMatcher other) {
            return other.matches(this.match) ? super.and(other) : other;
        }

        @Override
        public CharMatcher or(CharMatcher other) {
            return other.matches(this.match) ? IsNot.any() : this;
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            table.set(0, this.match);
            table.set(this.match + '\u0001', 65536);
        }

        @Override
        public CharMatcher negate() {
            return IsNot.is(this.match);
        }

        @Override
        public String toString() {
            return "CharMatcher.isNot('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    private static final class Is
    extends FastMatcher {
        private final char match;

        Is(char match) {
            this.match = match;
        }

        @Override
        public boolean matches(char c) {
            return c == this.match;
        }

        @Override
        public String replaceFrom(CharSequence sequence, char replacement) {
            return sequence.toString().replace(this.match, replacement);
        }

        @Override
        public CharMatcher and(CharMatcher other) {
            return other.matches(this.match) ? this : Is.none();
        }

        @Override
        public CharMatcher or(CharMatcher other) {
            return other.matches(this.match) ? other : super.or(other);
        }

        @Override
        public CharMatcher negate() {
            return Is.isNot(this.match);
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            table.set(this.match);
        }

        @Override
        public String toString() {
            return "CharMatcher.is('" + CharMatcher.showCharacter(this.match) + "')";
        }
    }

    private static final class Or
    extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        Or(CharMatcher a, CharMatcher b) {
            this.first = Preconditions.checkNotNull(a);
            this.second = Preconditions.checkNotNull(b);
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            this.first.setBits(table);
            this.second.setBits(table);
        }

        @Override
        public boolean matches(char c) {
            return this.first.matches(c) || this.second.matches(c);
        }

        @Override
        public String toString() {
            return "CharMatcher.or(" + this.first + ", " + this.second + ")";
        }
    }

    private static final class And
    extends CharMatcher {
        final CharMatcher first;
        final CharMatcher second;

        And(CharMatcher a, CharMatcher b) {
            this.first = Preconditions.checkNotNull(a);
            this.second = Preconditions.checkNotNull(b);
        }

        @Override
        public boolean matches(char c) {
            return this.first.matches(c) && this.second.matches(c);
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            BitSet tmp1 = new BitSet();
            this.first.setBits(tmp1);
            BitSet tmp2 = new BitSet();
            this.second.setBits(tmp2);
            tmp1.and(tmp2);
            table.or(tmp1);
        }

        @Override
        public String toString() {
            return "CharMatcher.and(" + this.first + ", " + this.second + ")";
        }
    }

    private static class Negated
    extends CharMatcher {
        final CharMatcher original;

        Negated(CharMatcher original) {
            this.original = Preconditions.checkNotNull(original);
        }

        @Override
        public boolean matches(char c) {
            return !this.original.matches(c);
        }

        @Override
        public boolean matchesAllOf(CharSequence sequence) {
            return this.original.matchesNoneOf(sequence);
        }

        @Override
        public boolean matchesNoneOf(CharSequence sequence) {
            return this.original.matchesAllOf(sequence);
        }

        @Override
        public int countIn(CharSequence sequence) {
            return sequence.length() - this.original.countIn(sequence);
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            BitSet tmp = new BitSet();
            this.original.setBits(tmp);
            tmp.flip(0, 65536);
            table.or(tmp);
        }

        @Override
        public CharMatcher negate() {
            return this.original;
        }

        @Override
        public String toString() {
            return this.original + ".negate()";
        }
    }

    private static final class SingleWidth
    extends RangesMatcher {
        static final SingleWidth INSTANCE = new SingleWidth();

        private SingleWidth() {
            super("CharMatcher.singleWidth()", "\u0000\u05be\u05d0\u05f3\u0600\u0750\u0e00\u1e00\u2100\ufb50\ufe70\uff61".toCharArray(), "\u04f9\u05be\u05ea\u05f4\u06ff\u077f\u0e7f\u20af\u213a\ufdff\ufeff\uffdc".toCharArray());
        }
    }

    private static final class Invisible
    extends RangesMatcher {
        private static final String RANGE_STARTS = "\u0000\u007f\u00ad\u0600\u061c\u06dd\u070f\u08e2\u1680\u180e\u2000\u2028\u205f\u2066\u3000\ud800\ufeff\ufff9";
        private static final String RANGE_ENDS = " \u00a0\u00ad\u0605\u061c\u06dd\u070f\u08e2\u1680\u180e\u200f\u202f\u2064\u206f\u3000\uf8ff\ufeff\ufffb";
        static final Invisible INSTANCE = new Invisible();

        private Invisible() {
            super("CharMatcher.invisible()", RANGE_STARTS.toCharArray(), RANGE_ENDS.toCharArray());
        }
    }

    private static final class JavaIsoControl
    extends NamedFastMatcher {
        static final JavaIsoControl INSTANCE = new JavaIsoControl();

        private JavaIsoControl() {
            super("CharMatcher.javaIsoControl()");
        }

        @Override
        public boolean matches(char c) {
            return c <= '\u001f' || c >= '\u007f' && c <= '\u009f';
        }
    }

    private static final class JavaLowerCase
    extends CharMatcher {
        static final JavaLowerCase INSTANCE = new JavaLowerCase();

        private JavaLowerCase() {
        }

        @Override
        public boolean matches(char c) {
            return Character.isLowerCase(c);
        }

        @Override
        public String toString() {
            return "CharMatcher.javaLowerCase()";
        }
    }

    private static final class JavaUpperCase
    extends CharMatcher {
        static final JavaUpperCase INSTANCE = new JavaUpperCase();

        private JavaUpperCase() {
        }

        @Override
        public boolean matches(char c) {
            return Character.isUpperCase(c);
        }

        @Override
        public String toString() {
            return "CharMatcher.javaUpperCase()";
        }
    }

    private static final class JavaLetterOrDigit
    extends CharMatcher {
        static final JavaLetterOrDigit INSTANCE = new JavaLetterOrDigit();

        private JavaLetterOrDigit() {
        }

        @Override
        public boolean matches(char c) {
            return Character.isLetterOrDigit(c);
        }

        @Override
        public String toString() {
            return "CharMatcher.javaLetterOrDigit()";
        }
    }

    private static final class JavaLetter
    extends CharMatcher {
        static final JavaLetter INSTANCE = new JavaLetter();

        private JavaLetter() {
        }

        @Override
        public boolean matches(char c) {
            return Character.isLetter(c);
        }

        @Override
        public String toString() {
            return "CharMatcher.javaLetter()";
        }
    }

    private static final class JavaDigit
    extends CharMatcher {
        static final JavaDigit INSTANCE = new JavaDigit();

        private JavaDigit() {
        }

        @Override
        public boolean matches(char c) {
            return Character.isDigit(c);
        }

        @Override
        public String toString() {
            return "CharMatcher.javaDigit()";
        }
    }

    private static final class Digit
    extends RangesMatcher {
        private static final String ZEROES = "0\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0de6\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1a80\u1a90\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\ua9d0\ua9f0\uaa50\uabf0\uff10";
        static final Digit INSTANCE = new Digit();

        private static char[] zeroes() {
            return ZEROES.toCharArray();
        }

        private static char[] nines() {
            char[] nines = new char[ZEROES.length()];
            for (int i = 0; i < ZEROES.length(); ++i) {
                nines[i] = (char)(ZEROES.charAt(i) + 9);
            }
            return nines;
        }

        private Digit() {
            super("CharMatcher.digit()", Digit.zeroes(), Digit.nines());
        }
    }

    private static class RangesMatcher
    extends CharMatcher {
        private final String description;
        private final char[] rangeStarts;
        private final char[] rangeEnds;

        RangesMatcher(String description, char[] rangeStarts, char[] rangeEnds) {
            this.description = description;
            this.rangeStarts = rangeStarts;
            this.rangeEnds = rangeEnds;
            Preconditions.checkArgument(rangeStarts.length == rangeEnds.length);
            for (int i = 0; i < rangeStarts.length; ++i) {
                Preconditions.checkArgument(rangeStarts[i] <= rangeEnds[i]);
                if (i + 1 >= rangeStarts.length) continue;
                Preconditions.checkArgument(rangeEnds[i] < rangeStarts[i + 1]);
            }
        }

        @Override
        public boolean matches(char c) {
            int index = Arrays.binarySearch(this.rangeStarts, c);
            if (index >= 0) {
                return true;
            }
            return (index = ~index - 1) >= 0 && c <= this.rangeEnds[index];
        }

        @Override
        public String toString() {
            return this.description;
        }
    }

    private static final class Ascii
    extends NamedFastMatcher {
        static final Ascii INSTANCE = new Ascii();

        Ascii() {
            super("CharMatcher.ascii()");
        }

        @Override
        public boolean matches(char c) {
            return c <= '\u007f';
        }
    }

    private static final class BreakingWhitespace
    extends CharMatcher {
        static final CharMatcher INSTANCE = new BreakingWhitespace();

        private BreakingWhitespace() {
        }

        @Override
        public boolean matches(char c) {
            switch (c) {
                case '\t': 
                case '\n': 
                case '\u000b': 
                case '\f': 
                case '\r': 
                case ' ': 
                case '\u0085': 
                case '\u1680': 
                case '\u2028': 
                case '\u2029': 
                case '\u205f': 
                case '\u3000': {
                    return true;
                }
                case '\u2007': {
                    return false;
                }
            }
            return c >= '\u2000' && c <= '\u200a';
        }

        @Override
        public String toString() {
            return "CharMatcher.breakingWhitespace()";
        }
    }

    @VisibleForTesting
    static final class Whitespace
    extends NamedFastMatcher {
        static final String TABLE = "\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001\u202f\u00a0\f\u2009\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000";
        static final int MULTIPLIER = 1682554634;
        static final int SHIFT = Integer.numberOfLeadingZeros("\u2002\u3000\r\u0085\u200a\u2005\u2000\u3000\u2029\u000b\u3000\u2008\u2003\u205f\u3000\u1680\t \u2006\u2001\u202f\u00a0\f\u2009\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000".length() - 1);
        static final Whitespace INSTANCE = new Whitespace();

        Whitespace() {
            super("CharMatcher.whitespace()");
        }

        @Override
        public boolean matches(char c) {
            return TABLE.charAt(1682554634 * c >>> SHIFT) == c;
        }

        @Override
        @GwtIncompatible
        void setBits(BitSet table) {
            for (int i = 0; i < TABLE.length(); ++i) {
                table.set(TABLE.charAt(i));
            }
        }
    }

    private static final class None
    extends NamedFastMatcher {
        static final None INSTANCE = new None();

        private None() {
            super("CharMatcher.none()");
        }

        @Override
        public boolean matches(char c) {
            return false;
        }

        @Override
        public int indexIn(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return -1;
        }

        @Override
        public int indexIn(CharSequence sequence, int start) {
            int length = sequence.length();
            Preconditions.checkPositionIndex(start, length);
            return -1;
        }

        @Override
        public int lastIndexIn(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return -1;
        }

        @Override
        public boolean matchesAllOf(CharSequence sequence) {
            return sequence.length() == 0;
        }

        @Override
        public boolean matchesNoneOf(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return true;
        }

        @Override
        public String removeFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override
        public String replaceFrom(CharSequence sequence, char replacement) {
            return sequence.toString();
        }

        @Override
        public String replaceFrom(CharSequence sequence, CharSequence replacement) {
            Preconditions.checkNotNull(replacement);
            return sequence.toString();
        }

        @Override
        public String collapseFrom(CharSequence sequence, char replacement) {
            return sequence.toString();
        }

        @Override
        public String trimFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override
        public String trimLeadingFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override
        public String trimTrailingFrom(CharSequence sequence) {
            return sequence.toString();
        }

        @Override
        public int countIn(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return 0;
        }

        @Override
        public CharMatcher and(CharMatcher other) {
            Preconditions.checkNotNull(other);
            return this;
        }

        @Override
        public CharMatcher or(CharMatcher other) {
            return Preconditions.checkNotNull(other);
        }

        @Override
        public CharMatcher negate() {
            return None.any();
        }
    }

    private static final class Any
    extends NamedFastMatcher {
        static final Any INSTANCE = new Any();

        private Any() {
            super("CharMatcher.any()");
        }

        @Override
        public boolean matches(char c) {
            return true;
        }

        @Override
        public int indexIn(CharSequence sequence) {
            return sequence.length() == 0 ? -1 : 0;
        }

        @Override
        public int indexIn(CharSequence sequence, int start) {
            int length = sequence.length();
            Preconditions.checkPositionIndex(start, length);
            return start == length ? -1 : start;
        }

        @Override
        public int lastIndexIn(CharSequence sequence) {
            return sequence.length() - 1;
        }

        @Override
        public boolean matchesAllOf(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return true;
        }

        @Override
        public boolean matchesNoneOf(CharSequence sequence) {
            return sequence.length() == 0;
        }

        @Override
        public String removeFrom(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return "";
        }

        @Override
        public String replaceFrom(CharSequence sequence, char replacement) {
            char[] array = new char[sequence.length()];
            Arrays.fill(array, replacement);
            return new String(array);
        }

        @Override
        public String replaceFrom(CharSequence sequence, CharSequence replacement) {
            StringBuilder result = new StringBuilder(sequence.length() * replacement.length());
            for (int i = 0; i < sequence.length(); ++i) {
                result.append(replacement);
            }
            return result.toString();
        }

        @Override
        public String collapseFrom(CharSequence sequence, char replacement) {
            return sequence.length() == 0 ? "" : String.valueOf(replacement);
        }

        @Override
        public String trimFrom(CharSequence sequence) {
            Preconditions.checkNotNull(sequence);
            return "";
        }

        @Override
        public int countIn(CharSequence sequence) {
            return sequence.length();
        }

        @Override
        public CharMatcher and(CharMatcher other) {
            return Preconditions.checkNotNull(other);
        }

        @Override
        public CharMatcher or(CharMatcher other) {
            Preconditions.checkNotNull(other);
            return this;
        }

        @Override
        public CharMatcher negate() {
            return Any.none();
        }
    }

    @GwtIncompatible
    private static final class BitSetMatcher
    extends NamedFastMatcher {
        private final BitSet table;

        private BitSetMatcher(BitSet table, String description) {
            super(description);
            if (table.length() + 64 < table.size()) {
                table = (BitSet)table.clone();
            }
            this.table = table;
        }

        @Override
        public boolean matches(char c) {
            return this.table.get(c);
        }

        @Override
        void setBits(BitSet bitSet) {
            bitSet.or(this.table);
        }
    }

    static class NegatedFastMatcher
    extends Negated {
        NegatedFastMatcher(CharMatcher original) {
            super(original);
        }

        @Override
        public final CharMatcher precomputed() {
            return this;
        }
    }

    static abstract class NamedFastMatcher
    extends FastMatcher {
        private final String description;

        NamedFastMatcher(String description) {
            this.description = Preconditions.checkNotNull(description);
        }

        @Override
        public final String toString() {
            return this.description;
        }
    }

    static abstract class FastMatcher
    extends CharMatcher {
        FastMatcher() {
        }

        @Override
        public final CharMatcher precomputed() {
            return this;
        }

        @Override
        public CharMatcher negate() {
            return new NegatedFastMatcher(this);
        }
    }
}

