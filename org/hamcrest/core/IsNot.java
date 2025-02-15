/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsNot<T>
extends BaseMatcher<T> {
    private final Matcher<T> matcher;

    public IsNot(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Object arg) {
        return !this.matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not ").appendDescriptionOf(this.matcher);
    }

    @Factory
    public static <T> Matcher<T> not(Matcher<T> matcher) {
        return new IsNot<T>(matcher);
    }

    @Factory
    public static <T> Matcher<T> not(T value) {
        return IsNot.not(IsEqual.equalTo(value));
    }
}

