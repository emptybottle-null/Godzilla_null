/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class CustomMatcher<T>
extends BaseMatcher<T> {
    private final String fixedDescription;

    public CustomMatcher(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description should be non null!");
        }
        this.fixedDescription = description;
    }

    @Override
    public final void describeTo(Description description) {
        description.appendText(this.fixedDescription);
    }
}

