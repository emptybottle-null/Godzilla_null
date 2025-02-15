/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class PatternFilenameFilter
implements FilenameFilter {
    private final Pattern pattern;

    public PatternFilenameFilter(String patternStr) {
        this(Pattern.compile(patternStr));
    }

    public PatternFilenameFilter(Pattern pattern) {
        this.pattern = Preconditions.checkNotNull(pattern);
    }

    @Override
    public boolean accept(@Nullable File dir, String fileName) {
        return this.pattern.matcher(fileName).matches();
    }
}

