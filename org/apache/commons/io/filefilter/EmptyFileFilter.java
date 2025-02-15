/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class EmptyFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final IOFileFilter EMPTY = new EmptyFileFilter();
    public static final IOFileFilter NOT_EMPTY = EMPTY.negate();
    private static final long serialVersionUID = 3631422087512832211L;

    protected EmptyFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            Object[] files = file.listFiles();
            return IOUtils.length(files) == 0;
        }
        return file.length() == 0L;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        try {
            boolean bl;
            if (Files.isDirectory(file, new LinkOption[0])) {
                try (Stream<Path> stream = Files.list(file);){
                    FileVisitResult fileVisitResult = EmptyFileFilter.toFileVisitResult(!stream.findFirst().isPresent(), file);
                    return fileVisitResult;
                }
            }
            if (Files.size(file) == 0L) {
                bl = true;
                return EmptyFileFilter.toFileVisitResult(bl, file);
            }
            bl = false;
            return EmptyFileFilter.toFileVisitResult(bl, file);
        } catch (IOException e) {
            return this.handle(e);
        }
    }
}

