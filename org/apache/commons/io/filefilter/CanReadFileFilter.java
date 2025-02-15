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
import org.apache.commons.io.filefilter.CanWriteFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class CanReadFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final IOFileFilter CAN_READ = new CanReadFileFilter();
    public static final IOFileFilter CANNOT_READ = CAN_READ.negate();
    public static final IOFileFilter READ_ONLY = CAN_READ.and(CanWriteFileFilter.CANNOT_WRITE);
    private static final long serialVersionUID = 3179904805251622989L;

    protected CanReadFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return file.canRead();
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return CanReadFileFilter.toFileVisitResult(Files.isReadable(file), file);
    }
}

