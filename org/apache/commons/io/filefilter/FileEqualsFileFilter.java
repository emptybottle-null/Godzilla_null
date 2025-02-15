/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class FileEqualsFileFilter
extends AbstractFileFilter {
    private final File file;
    private final Path path;

    public FileEqualsFileFilter(File file) {
        this.file = Objects.requireNonNull(file, "file");
        this.path = file.toPath();
    }

    @Override
    public boolean accept(File file) {
        return Objects.equals(this.file, file);
    }

    @Override
    public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
        return FileEqualsFileFilter.toFileVisitResult(Objects.equals(this.path, path), path);
    }
}

