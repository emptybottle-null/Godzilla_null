/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class ClassRelativeResourceLoader
extends DefaultResourceLoader {
    private final Class<?> clazz;

    public ClassRelativeResourceLoader(Class<?> clazz) {
        Assert.notNull(clazz, "Class must not be null");
        this.clazz = clazz;
        this.setClassLoader(clazz.getClassLoader());
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassRelativeContextResource(path, this.clazz);
    }

    private static class ClassRelativeContextResource
    extends ClassPathResource
    implements ContextResource {
        private final Class<?> clazz;

        public ClassRelativeContextResource(String path, Class<?> clazz) {
            super(path, clazz);
            this.clazz = clazz;
        }

        @Override
        public String getPathWithinContext() {
            return this.getPath();
        }

        @Override
        public Resource createRelative(String relativePath) {
            String pathToUse = StringUtils.applyRelativePath(this.getPath(), relativePath);
            return new ClassRelativeContextResource(pathToUse, this.clazz);
        }
    }
}

