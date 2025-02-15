/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel;

import org.springframework.core.SpringProperties;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.lang.Nullable;

public class SpelParserConfiguration {
    public static final String SPRING_EXPRESSION_COMPILER_MODE_PROPERTY_NAME = "spring.expression.compiler.mode";
    private static final SpelCompilerMode defaultCompilerMode;
    private final SpelCompilerMode compilerMode;
    @Nullable
    private final ClassLoader compilerClassLoader;
    private final boolean autoGrowNullReferences;
    private final boolean autoGrowCollections;
    private final int maximumAutoGrowSize;

    public SpelParserConfiguration() {
        this(null, null, false, false, Integer.MAX_VALUE);
    }

    public SpelParserConfiguration(@Nullable SpelCompilerMode compilerMode, @Nullable ClassLoader compilerClassLoader) {
        this(compilerMode, compilerClassLoader, false, false, Integer.MAX_VALUE);
    }

    public SpelParserConfiguration(boolean autoGrowNullReferences, boolean autoGrowCollections) {
        this(null, null, autoGrowNullReferences, autoGrowCollections, Integer.MAX_VALUE);
    }

    public SpelParserConfiguration(boolean autoGrowNullReferences, boolean autoGrowCollections, int maximumAutoGrowSize) {
        this(null, null, autoGrowNullReferences, autoGrowCollections, maximumAutoGrowSize);
    }

    public SpelParserConfiguration(@Nullable SpelCompilerMode compilerMode, @Nullable ClassLoader compilerClassLoader, boolean autoGrowNullReferences, boolean autoGrowCollections, int maximumAutoGrowSize) {
        this.compilerMode = compilerMode != null ? compilerMode : defaultCompilerMode;
        this.compilerClassLoader = compilerClassLoader;
        this.autoGrowNullReferences = autoGrowNullReferences;
        this.autoGrowCollections = autoGrowCollections;
        this.maximumAutoGrowSize = maximumAutoGrowSize;
    }

    public SpelCompilerMode getCompilerMode() {
        return this.compilerMode;
    }

    @Nullable
    public ClassLoader getCompilerClassLoader() {
        return this.compilerClassLoader;
    }

    public boolean isAutoGrowNullReferences() {
        return this.autoGrowNullReferences;
    }

    public boolean isAutoGrowCollections() {
        return this.autoGrowCollections;
    }

    public int getMaximumAutoGrowSize() {
        return this.maximumAutoGrowSize;
    }

    static {
        String compilerMode = SpringProperties.getProperty(SPRING_EXPRESSION_COMPILER_MODE_PROPERTY_NAME);
        defaultCompilerMode = compilerMode != null ? SpelCompilerMode.valueOf(compilerMode.toUpperCase()) : SpelCompilerMode.OFF;
    }
}

