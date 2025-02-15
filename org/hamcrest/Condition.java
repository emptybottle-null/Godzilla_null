/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Condition<T> {
    public static final NotMatched<Object> NOT_MATCHED = new NotMatched();

    private Condition() {
    }

    public abstract boolean matching(Matcher<T> var1, String var2);

    public abstract <U> Condition<U> and(Step<? super T, U> var1);

    public final boolean matching(Matcher<T> match) {
        return this.matching(match, "");
    }

    public final <U> Condition<U> then(Step<? super T, U> mapping) {
        return this.and(mapping);
    }

    public static <T> Condition<T> notMatched() {
        return NOT_MATCHED;
    }

    public static <T> Condition<T> matched(T theValue, Description mismatch) {
        return new Matched(theValue, mismatch);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static final class NotMatched<T>
    extends Condition<T> {
        private NotMatched() {
        }

        @Override
        public boolean matching(Matcher<T> match, String message) {
            return false;
        }

        @Override
        public <U> Condition<U> and(Step<? super T, U> mapping) {
            return NotMatched.notMatched();
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private static final class Matched<T>
    extends Condition<T> {
        private final T theValue;
        private final Description mismatch;

        private Matched(T theValue, Description mismatch) {
            this.theValue = theValue;
            this.mismatch = mismatch;
        }

        @Override
        public boolean matching(Matcher<T> matcher, String message) {
            if (matcher.matches(this.theValue)) {
                return true;
            }
            this.mismatch.appendText(message);
            matcher.describeMismatch(this.theValue, this.mismatch);
            return false;
        }

        @Override
        public <U> Condition<U> and(Step<? super T, U> next) {
            return next.apply(this.theValue, this.mismatch);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static interface Step<I, O> {
        public Condition<O> apply(I var1, Description var2);
    }
}

