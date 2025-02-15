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
import org.apache.commons.io.filefilter.IOFileFilter;

public class CanWriteFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final IOFileFilter CAN_WRITE = new CanWriteFileFilter();
    public static final IOFileFilter CANNOT_WRITE = CAN_WRITE.negate();
    private static final long serialVersionUID = 5132005214688990379L;

    protected CanWriteFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return file.canWrite();
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return CanWriteFileFilter.toFileVisitResult(Files.isWritable(file), file);
    }
}

