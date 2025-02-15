/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.CommonMatcher;
import com.google.common.base.Platform;

@GwtCompatible
abstract class CommonPattern {
    CommonPattern() {
    }

    public abstract CommonMatcher matcher(CharSequence var1);

    public abstract String pattern();

    public abstract int flags();

    public abstract String toString();

    public static CommonPattern compile(String pattern) {
        return Platform.compilePattern(pattern);
    }

    public static boolean isPcreLike() {
        return Platform.patternCompilerIsPcreLike();
    }
}

