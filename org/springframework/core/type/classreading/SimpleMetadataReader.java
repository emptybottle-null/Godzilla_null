/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.asm.ClassReader;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleAnnotationMetadataReadingVisitor;
import org.springframework.lang.Nullable;

final class SimpleMetadataReader
implements MetadataReader {
    private static final int PARSING_OPTIONS = 7;
    private final Resource resource;
    private final AnnotationMetadata annotationMetadata;

    SimpleMetadataReader(Resource resource, @Nullable ClassLoader classLoader) throws IOException {
        SimpleAnnotationMetadataReadingVisitor visitor = new SimpleAnnotationMetadataReadingVisitor(classLoader);
        SimpleMetadataReader.getClassReader(resource).accept(visitor, 7);
        this.resource = resource;
        this.annotationMetadata = visitor.getMetadata();
    }

    private static ClassReader getClassReader(Resource resource) throws IOException {
        Throwable throwable = null;
        try (InputStream is = resource.getInputStream();){
            ClassReader classReader = new ClassReader(is);
            return classReader;
        } catch (IllegalArgumentException ex) {
            try {
                throw new NestedIOException("ASM ClassReader failed to parse class file - probably due to a new Java class file version that isn't supported yet: " + resource, ex);
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
        }
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.annotationMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}

