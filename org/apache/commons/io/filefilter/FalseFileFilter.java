/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FalseFileFilter
implements IOFileFilter,
Serializable {
    private static final String TO_STRING = Boolean.FALSE.toString();
    public static final IOFileFilter FALSE;
    public static final IOFileFilter INSTANCE;
    private static final long serialVersionUID = 6210271677940926200L;

    protected FalseFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return false;
    }

    @Override
    public boolean accept(File dir, String name) {
        return false;
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return FileVisitResult.TERMINATE;
    }

    @Override
    public IOFileFilter negate() {
        return TrueFileFilter.INSTANCE;
    }

    public String toString() {
        return TO_STRING;
    }

    @Override
    public IOFileFilter and(IOFileFilter fileFilter) {
        return INSTANCE;
    }

    @Override
    public IOFileFilter or(IOFileFilter fileFilter) {
        return fileFilter;
    }

    static {
        INSTANCE = FALSE = new FalseFileFilter();
    }
}

