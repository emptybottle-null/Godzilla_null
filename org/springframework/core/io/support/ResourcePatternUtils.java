/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.ResourceUtils;

public abstract class ResourcePatternUtils {
    public static boolean isUrl(@Nullable String resourceLocation) {
        return resourceLocation != null && (resourceLocation.startsWith("classpath*:") || ResourceUtils.isUrl(resourceLocation));
    }

    public static ResourcePatternResolver getResourcePatternResolver(@Nullable ResourceLoader resourceLoader) {
        if (resourceLoader instanceof ResourcePatternResolver) {
            return (ResourcePatternResolver)resourceLoader;
        }
        if (resourceLoader != null) {
            return new PathMatchingResourcePatternResolver(resourceLoader);
        }
        return new PathMatchingResourcePatternResolver();
    }
}

