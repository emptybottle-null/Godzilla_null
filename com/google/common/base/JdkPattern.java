/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.CommonMatcher;
import com.google.common.base.CommonPattern;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@GwtIncompatible
final class JdkPattern
extends CommonPattern
implements Serializable {
    private final Pattern pattern;
    private static final long serialVersionUID = 0L;

    JdkPattern(Pattern pattern) {
        this.pattern = Preconditions.checkNotNull(pattern);
    }

    @Override
    public CommonMatcher matcher(CharSequence t) {
        return new JdkMatcher(this.pattern.matcher(t));
    }

    @Override
    public String pattern() {
        return this.pattern.pattern();
    }

    @Override
    public int flags() {
        return this.pattern.flags();
    }

    @Override
    public String toString() {
        return this.pattern.toString();
    }

    private static final class JdkMatcher
    extends CommonMatcher {
        final Matcher matcher;

        JdkMatcher(Matcher matcher) {
            this.matcher = Preconditions.checkNotNull(matcher);
        }

        @Override
        public boolean matches() {
            return this.matcher.matches();
        }

        @Override
        public boolean find() {
            return this.matcher.find();
        }

        @Override
        public boolean find(int index) {
            return this.matcher.find(index);
        }

        @Override
        public String replaceAll(String replacement) {
            return this.matcher.replaceAll(replacement);
        }

        @Override
        public int end() {
            return this.matcher.end();
        }

        @Override
        public int start() {
            return this.matcher.start();
        }
    }
}

