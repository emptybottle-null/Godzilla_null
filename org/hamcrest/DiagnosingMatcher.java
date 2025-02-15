/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class DiagnosingMatcher<T>
extends BaseMatcher<T> {
    @Override
    public final boolean matches(Object item) {
        return this.matches(item, Description.NONE);
    }

    @Override
    public final void describeMismatch(Object item, Description mismatchDescription) {
        this.matches(item, mismatchDescription);
    }

    protected abstract boolean matches(Object var1, Description var2);
}

