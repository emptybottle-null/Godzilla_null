/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class DirectoryFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final IOFileFilter DIRECTORY;
    public static final IOFileFilter INSTANCE;
    private static final long serialVersionUID = -5148237843784525732L;

    protected DirectoryFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return file.isDirectory();
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return DirectoryFileFilter.toFileVisitResult(Files.isDirectory(file, new LinkOption[0]), file);
    }

    static {
        INSTANCE = DIRECTORY = new DirectoryFileFilter();
    }
}

