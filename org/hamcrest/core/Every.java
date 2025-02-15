/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Every<T>
extends TypeSafeDiagnosingMatcher<Iterable<T>> {
    private final Matcher<? super T> matcher;

    public Every(Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matchesSafely(Iterable<T> collection, Description mismatchDescription) {
        for (T t : collection) {
            if (this.matcher.matches(t)) continue;
            mismatchDescription.appendText("an item ");
            this.matcher.describeMismatch(t, mismatchDescription);
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("every item is ").appendDescriptionOf(this.matcher);
    }

    @Factory
    public static <U> Matcher<Iterable<U>> everyItem(Matcher<U> itemMatcher) {
        return new Every<U>(itemMatcher);
    }
}

