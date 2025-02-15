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

public class FileFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final IOFileFilter INSTANCE;
    @Deprecated
    public static final IOFileFilter FILE;
    private static final long serialVersionUID = 5345244090827540862L;

    protected FileFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return file.isFile();
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return FileFileFilter.toFileVisitResult(Files.isRegularFile(file, new LinkOption[0]), file);
    }

    static {
        FILE = INSTANCE = new FileFileFilter();
    }
}

