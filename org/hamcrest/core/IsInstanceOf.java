/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsInstanceOf
extends DiagnosingMatcher<Object> {
    private final Class<?> expectedClass;
    private final Class<?> matchableClass;

    public IsInstanceOf(Class<?> expectedClass) {
        this.expectedClass = expectedClass;
        this.matchableClass = IsInstanceOf.matchableClass(expectedClass);
    }

    private static Class<?> matchableClass(Class<?> expectedClass) {
        if (Boolean.TYPE.equals(expectedClass)) {
            return Boolean.class;
        }
        if (Byte.TYPE.equals(expectedClass)) {
            return Byte.class;
        }
        if (Character.TYPE.equals(expectedClass)) {
            return Character.class;
        }
        if (Double.TYPE.equals(expectedClass)) {
            return Double.class;
        }
        if (Float.TYPE.equals(expectedClass)) {
            return Float.class;
        }
        if (Integer.TYPE.equals(expectedClass)) {
            return Integer.class;
        }
        if (Long.TYPE.equals(expectedClass)) {
            return Long.class;
        }
        if (Short.TYPE.equals(expectedClass)) {
            return Short.class;
        }
        return expectedClass;
    }

    @Override
    protected boolean matches(Object item, Description mismatch) {
        if (null == item) {
            mismatch.appendText("null");
            return false;
        }
        if (!this.matchableClass.isInstance(item)) {
            mismatch.appendValue(item).appendText(" is a " + item.getClass().getName());
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an instance of ").appendText(this.expectedClass.getName());
    }

    @Factory
    public static <T> Matcher<T> instanceOf(Class<?> type) {
        return new IsInstanceOf(type);
    }

    @Factory
    public static <T> Matcher<T> any(Class<T> type) {
        return new IsInstanceOf(type);
    }
}

