/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class TrueFileFilter
implements IOFileFilter,
Serializable {
    private static final String TO_STRING = Boolean.TRUE.toString();
    private static final long serialVersionUID = 8782512160909720199L;
    public static final IOFileFilter TRUE;
    public static final IOFileFilter INSTANCE;

    protected TrueFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return true;
    }

    @Override
    public boolean accept(File dir, String name) {
        return true;
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public IOFileFilter negate() {
        return FalseFileFilter.INSTANCE;
    }

    @Override
    public IOFileFilter or(IOFileFilter fileFilter) {
        return INSTANCE;
    }

    @Override
    public IOFileFilter and(IOFileFilter fileFilter) {
        return fileFilter;
    }

    public String toString() {
        return TO_STRING;
    }

    static {
        INSTANCE = TRUE = new TrueFileFilter();
    }
}

