/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.springframework.lang.Nullable;

public interface PropertyResolver {
    public boolean containsProperty(String var1);

    @Nullable
    public String getProperty(String var1);

    public String getProperty(String var1, String var2);

    @Nullable
    public <T> T getProperty(String var1, Class<T> var2);

    public <T> T getProperty(String var1, Class<T> var2, T var3);

    public String getRequiredProperty(String var1) throws IllegalStateException;

    public <T> T getRequiredProperty(String var1, Class<T> var2) throws IllegalStateException;

    public String resolvePlaceholders(String var1);

    public String resolveRequiredPlaceholders(String var1) throws IllegalArgumentException;
}

