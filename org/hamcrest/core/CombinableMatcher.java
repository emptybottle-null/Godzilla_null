/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import java.util.ArrayList;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CombinableMatcher<T>
extends TypeSafeDiagnosingMatcher<T> {
    private final Matcher<? super T> matcher;

    public CombinableMatcher(Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(T item, Description mismatch) {
        if (!this.matcher.matches(item)) {
            this.matcher.describeMismatch(item, mismatch);
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(this.matcher);
    }

    public CombinableMatcher<T> and(Matcher<? super T> other) {
        return new CombinableMatcher<T>(new AllOf<T>(this.templatedListWith(other)));
    }

    public CombinableMatcher<T> or(Matcher<? super T> other) {
        return new CombinableMatcher<T>(new AnyOf<T>(this.templatedListWith(other)));
    }

    private ArrayList<Matcher<? super T>> templatedListWith(Matcher<? super T> other) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        matchers.add(this.matcher);
        matchers.add(other);
        return matchers;
    }

    @Factory
    public static <LHS> CombinableBothMatcher<LHS> both(Matcher<? super LHS> matcher) {
        return new CombinableBothMatcher<LHS>(matcher);
    }

    @Factory
    public static <LHS> CombinableEitherMatcher<LHS> either(Matcher<? super LHS> matcher) {
        return new CombinableEitherMatcher<LHS>(matcher);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class CombinableEitherMatcher<X> {
        private final Matcher<? super X> first;

        public CombinableEitherMatcher(Matcher<? super X> matcher) {
            this.first = matcher;
        }

        public CombinableMatcher<X> or(Matcher<? super X> other) {
            return new CombinableMatcher<X>(this.first).or(other);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static final class CombinableBothMatcher<X> {
        private final Matcher<? super X> first;

        public CombinableBothMatcher(Matcher<? super X> matcher) {
            this.first = matcher;
        }

        public CombinableMatcher<X> and(Matcher<? super X> other) {
            return new CombinableMatcher<X>(this.first).and(other);
        }
    }
}

