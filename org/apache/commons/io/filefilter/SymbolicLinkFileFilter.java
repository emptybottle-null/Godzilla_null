/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class SymbolicLinkFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final SymbolicLinkFileFilter INSTANCE = new SymbolicLinkFileFilter();
    private static final long serialVersionUID = 1L;

    protected SymbolicLinkFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return file.isFile();
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return SymbolicLinkFileFilter.toFileVisitResult(Files.isSymbolicLink(file), file);
    }
}

