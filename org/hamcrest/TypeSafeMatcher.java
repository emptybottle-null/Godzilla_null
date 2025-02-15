/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.internal.ReflectiveTypeFinder;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class TypeSafeMatcher<T>
extends BaseMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 1, 0);
    private final Class<?> expectedType;

    protected TypeSafeMatcher() {
        this(TYPE_FINDER);
    }

    protected TypeSafeMatcher(Class<?> expectedType) {
        this.expectedType = expectedType;
    }

    protected TypeSafeMatcher(ReflectiveTypeFinder typeFinder) {
        this.expectedType = typeFinder.findExpectedType(this.getClass());
    }

    protected abstract boolean matchesSafely(T var1);

    protected void describeMismatchSafely(T item, Description mismatchDescription) {
        super.describeMismatch(item, mismatchDescription);
    }

    @Override
    public final boolean matches(Object item) {
        return item != null && this.expectedType.isInstance(item) && this.matchesSafely(item);
    }

    @Override
    public final void describeMismatch(Object item, Description description) {
        if (item == null) {
            super.describeMismatch(item, description);
        } else if (!this.expectedType.isInstance(item)) {
            description.appendText("was a ").appendText(item.getClass().getName()).appendText(" (").appendValue(item).appendText(")");
        } else {
            this.describeMismatchSafely(item, description);
        }
    }
}

