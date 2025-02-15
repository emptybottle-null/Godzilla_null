/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;

public class HiddenFileFilter
extends AbstractFileFilter
implements Serializable {
    public static final IOFileFilter HIDDEN = new HiddenFileFilter();
    private static final long serialVersionUID = 8930842316112759062L;
    public static final IOFileFilter VISIBLE = HIDDEN.negate();

    protected HiddenFileFilter() {
    }

    @Override
    public boolean accept(File file) {
        return file.isHidden();
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        try {
            return HiddenFileFilter.toFileVisitResult(Files.isHidden(file), file);
        } catch (IOException e) {
            return this.handle(e);
        }
    }
}

