/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import java.util.ArrayList;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsEqual;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class IsCollectionContaining<T>
extends TypeSafeDiagnosingMatcher<Iterable<? super T>> {
    private final Matcher<? super T> elementMatcher;

    public IsCollectionContaining(Matcher<? super T> elementMatcher) {
        this.elementMatcher = elementMatcher;
    }

    @Override
    protected boolean matchesSafely(Iterable<? super T> collection, Description mismatchDescription) {
        boolean isPastFirst = false;
        for (T item : collection) {
            if (this.elementMatcher.matches(item)) {
                return true;
            }
            if (isPastFirst) {
                mismatchDescription.appendText(", ");
            }
            this.elementMatcher.describeMismatch(item, mismatchDescription);
            isPastFirst = true;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
    }

    @Factory
    public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> itemMatcher) {
        return new IsCollectionContaining<T>(itemMatcher);
    }

    @Factory
    public static <T> Matcher<Iterable<? super T>> hasItem(T item) {
        return new IsCollectionContaining<T>(IsEqual.equalTo(item));
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T> ... itemMatchers) {
        ArrayList all = new ArrayList(itemMatchers.length);
        for (Matcher<? super T> elementMatcher : itemMatchers) {
            all.add(new IsCollectionContaining<T>(elementMatcher));
        }
        return AllOf.allOf(all);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItems(T ... items) {
        ArrayList all = new ArrayList(items.length);
        for (T element : items) {
            all.add(IsCollectionContaining.hasItem(element));
        }
        return AllOf.allOf(all);
    }
}

