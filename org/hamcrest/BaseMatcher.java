/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class BaseMatcher<T>
implements Matcher<T> {
    @Override
    @Deprecated
    public final void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("was ").appendValue(item);
    }

    public String toString() {
        return StringDescription.toString(this);
    }
}

