/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import org.springframework.core.io.ContextResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class FileSystemResourceLoader
extends DefaultResourceLoader {
    @Override
    protected Resource getResourceByPath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return new FileSystemContextResource(path);
    }

    private static class FileSystemContextResource
    extends FileSystemResource
    implements ContextResource {
        public FileSystemContextResource(String path) {
            super(path);
        }

        @Override
        public String getPathWithinContext() {
            return this.getPath();
        }
    }
}

