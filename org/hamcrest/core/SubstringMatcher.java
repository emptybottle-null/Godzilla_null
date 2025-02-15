/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class SubstringMatcher
extends TypeSafeMatcher<String> {
    protected final String substring;

    protected SubstringMatcher(String substring) {
        this.substring = substring;
    }

    @Override
    public boolean matchesSafely(String item) {
        return this.evalSubstringOf(item);
    }

    @Override
    public void describeMismatchSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("was \"").appendText(item).appendText("\"");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string ").appendText(this.relationship()).appendText(" ").appendValue(this.substring);
    }

    protected abstract boolean evalSubstringOf(String var1);

    protected abstract String relationship();
}

