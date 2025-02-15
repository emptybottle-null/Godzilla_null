/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsSame<T>
extends BaseMatcher<T> {
    private final T object;

    public IsSame(T object) {
        this.object = object;
    }

    @Override
    public boolean matches(Object arg) {
        return arg == this.object;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("sameInstance(").appendValue(this.object).appendText(")");
    }

    @Factory
    public static <T> Matcher<T> sameInstance(T target) {
        return new IsSame<T>(target);
    }

    @Factory
    public static <T> Matcher<T> theInstance(T target) {
        return new IsSame<T>(target);
    }
}

