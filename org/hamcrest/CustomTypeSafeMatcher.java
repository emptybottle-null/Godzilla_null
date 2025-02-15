/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class CustomTypeSafeMatcher<T>
extends TypeSafeMatcher<T> {
    private final String fixedDescription;

    public CustomTypeSafeMatcher(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description must be non null!");
        }
        this.fixedDescription = description;
    }

    @Override
    public final void describeTo(Description description) {
        description.appendText(this.fixedDescription);
    }
}

