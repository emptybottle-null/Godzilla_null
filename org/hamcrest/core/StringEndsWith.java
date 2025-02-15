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
public class StringEndsWith
extends SubstringMatcher {
    public StringEndsWith(String substring) {
        super(substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.endsWith(this.substring);
    }

    @Override
    protected String relationship() {
        return "ending with";
    }

    @Factory
    public static Matcher<String> endsWith(String suffix) {
        return new StringEndsWith(suffix);
    }
}

