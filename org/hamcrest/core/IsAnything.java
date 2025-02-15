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
public class IsAnything<T>
extends BaseMatcher<T> {
    private final String message;

    public IsAnything() {
        this("ANYTHING");
    }

    public IsAnything(String message) {
        this.message = message;
    }

    @Override
    public boolean matches(Object o) {
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(this.message);
    }

    @Factory
    public static Matcher<Object> anything() {
        return new IsAnything<Object>();
    }

    @Factory
    public static Matcher<Object> anything(String description) {
        return new IsAnything<Object>(description);
    }
}

