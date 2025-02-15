/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Is<T>
extends BaseMatcher<T> {
    private final Matcher<T> matcher;

    public Is(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Object arg) {
        return this.matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is ").appendDescriptionOf(this.matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        this.matcher.describeMismatch(item, mismatchDescription);
    }

    @Factory
    public static <T> Matcher<T> is(Matcher<T> matcher) {
        return new Is<T>(matcher);
    }

    @Factory
    public static <T> Matcher<T> is(T value) {
        return Is.is(IsEqual.equalTo(value));
    }

    @Factory
    @Deprecated
    public static <T> Matcher<T> is(Class<T> type) {
        Matcher typeMatcher = IsInstanceOf.instanceOf(type);
        return Is.is(typeMatcher);
    }

    @Factory
    public static <T> Matcher<T> isA(Class<T> type) {
        Matcher typeMatcher = IsInstanceOf.instanceOf(type);
        return Is.is(typeMatcher);
    }
}

