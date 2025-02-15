/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;

public interface MetadataReader {
    public Resource getResource();

    public ClassMetadata getClassMetadata();

    public AnnotationMetadata getAnnotationMetadata();
}

