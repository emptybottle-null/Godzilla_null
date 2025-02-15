/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.filter;

import java.io.IOException;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

@FunctionalInterface
public interface TypeFilter {
    public boolean match(MetadataReader var1, MetadataReaderFactory var2) throws IOException;
}

