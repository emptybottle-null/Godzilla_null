/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import java.util.ArrayList;
import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.ShortcutCombination;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class AnyOf<T>
extends ShortcutCombination<T> {
    public AnyOf(Iterable<Matcher<? super T>> matchers) {
        super(matchers);
    }

    @Override
    public boolean matches(Object o) {
        return this.matches(o, true);
    }

    @Override
    public void describeTo(Description description) {
        this.describeTo(description, "or");
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Iterable<Matcher<? super T>> matchers) {
        return new AnyOf<T>(matchers);
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Matcher<? super T> ... matchers) {
        return AnyOf.anyOf(Arrays.asList(matchers));
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Matcher<T> first, Matcher<? super T> second) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        matchers.add(first);
        matchers.add(second);
        return AnyOf.anyOf(matchers);
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Matcher<T> first, Matcher<? super T> second, Matcher<? super T> third) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        return AnyOf.anyOf(matchers);
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Matcher<T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        matchers.add(fourth);
        return AnyOf.anyOf(matchers);
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Matcher<T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        matchers.add(fourth);
        matchers.add(fifth);
        return AnyOf.anyOf(matchers);
    }

    @Factory
    public static <T> AnyOf<T> anyOf(Matcher<T> first, Matcher<? super T> second, Matcher<? super T> third, Matcher<? super T> fourth, Matcher<? super T> fifth, Matcher<? super T> sixth) {
        ArrayList<Matcher<T>> matchers = new ArrayList<Matcher<T>>();
        matchers.add(first);
        matchers.add(second);
        matchers.add(third);
        matchers.add(fourth);
        matchers.add(fifth);
        matchers.add(sixth);
        return AnyOf.anyOf(matchers);
    }
}

