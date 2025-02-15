/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.core;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class StringContains
extends SubstringMatcher {
    public StringContains(String substring) {
        super(substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.indexOf(this.substring) >= 0;
    }

    @Override
    protected String relationship() {
        return "containing";
    }

    @Factory
    public static Matcher<String> containsString(String substring) {
        return new StringContains(substring);
    }
}

