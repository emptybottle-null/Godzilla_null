/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.IOException;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;

public interface PropertySourceFactory {
    public PropertySource<?> createPropertySource(@Nullable String var1, EncodedResource var2) throws IOException;
}

