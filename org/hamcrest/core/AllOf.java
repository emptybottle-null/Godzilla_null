/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import java.util.ArrayList;
import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AllOf<T>
extends DiagnosingMatcher<T> {
    private final Iterable<Matcher<? super T>> matchers;

    public AllOf(Iterable<Matcher<? super T>> matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Object o, Description mismatch) {
        for (Matcher<T> matcher : this.matchers) {
            if (matcher.matches(o)) continue;
            mismatch.appendDescriptionOf(matcher).appendText(" ");
            matcher.describeMismatch(o, mismatch);
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendList("(", " and ", ")", this.matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Iterable<Matcher<? super T>> matchers) {
        return new AllOf<T>(matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? super T> ... matchers) {
        return AllOf.allOf(Arrays.asList(matchers));
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>(2);
        matchers.add(first);
        matchers.add(second);
        return AllOf.allOf(matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>(3);
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        return AllOf.allOf(matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>(4);
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        matchers.add(fourth);
        return AllOf.allOf(matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>(5);
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        matchers.add(fourth);
        matchers.add(fifth);
        return AllOf.allOf(matchers);
    }

    @Factory
    public static <T> Matcher<T> allOf(Matcher<? super T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth, Matcher<? super T> sixth) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>(6);
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        matchers.add(fourth);
        matchers.add(fifth);
        matchers.add(sixth);
        return AllOf.allOf(matchers);
    }
}

