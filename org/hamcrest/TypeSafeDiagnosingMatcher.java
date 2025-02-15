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
public abstract class TypeSafeDiagnosingMatcher<T>
extends BaseMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 2, 0);
    private final Class<?> expectedType;

    protected abstract boolean matchesSafely(T var1, Description var2);

    protected TypeSafeDiagnosingMatcher(Class<?> expectedType) {
        this.expectedType = expectedType;
    }

    protected TypeSafeDiagnosingMatcher(ReflectiveTypeFinder typeFinder) {
        this.expectedType = typeFinder.findExpectedType(this.getClass());
    }

    protected TypeSafeDiagnosingMatcher() {
        this(TYPE_FINDER);
    }

    @Override
    public final boolean matches(Object item) {
        return item != null && this.expectedType.isInstance(item) && this.matchesSafely(item, new Description.NullDescription());
    }

    @Override
    public final void describeMismatch(Object item, Description mismatchDescription) {
        if (item == null || !this.expectedType.isInstance(item)) {
            super.describeMismatch(item, mismatchDescription);
        } else {
            this.matchesSafely(item, mismatchDescription);
        }
    }
}

