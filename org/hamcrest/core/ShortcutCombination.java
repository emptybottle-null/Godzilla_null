/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
abstract class ShortcutCombination<T>
extends BaseMatcher<T> {
    private final Iterable<Matcher<? super T>> matchers;

    public ShortcutCombination(Iterable<Matcher<? super T>> matchers) {
        this.matchers = matchers;
    }

    @Override
    public abstract boolean matches(Object var1);

    @Override
    public abstract void describeTo(Description var1);

    protected boolean matches(Object o, boolean shortcut) {
        for (Matcher<T> matcher : this.matchers) {
            if (matcher.matches(o) != shortcut) continue;
            return shortcut;
        }
        return !shortcut;
    }

    public void describeTo(Description description, String operator2) {
        description.appendList("(", " " + operator2 + " ", ")", this.matchers);
    }
}

