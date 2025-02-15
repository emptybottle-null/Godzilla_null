/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.file.PathFilter;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;

public interface IOFileFilter
extends FileFilter,
FilenameFilter,
PathFilter {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    @Override
    public boolean accept(File var1);

    @Override
    public boolean accept(File var1, String var2);

    @Override
    default public FileVisitResult accept(Path path, BasicFileAttributes attributes) {
        return AbstractFileFilter.toFileVisitResult(this.accept(path.toFile()), path);
    }

    default public IOFileFilter and(IOFileFilter fileFilter) {
        return new AndFileFilter(this, fileFilter);
    }

    default public IOFileFilter negate() {
        return new NotFileFilter(this);
    }

    default public IOFileFilter or(IOFileFilter fileFilter) {
        return new OrFileFilter(this, fileFilter);
    }
}

