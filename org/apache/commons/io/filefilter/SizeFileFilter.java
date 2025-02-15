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

public class SizeFileFilter
extends AbstractFileFilter
implements Serializable {
    private static final long serialVersionUID = 7388077430788600069L;
    private final boolean acceptLarger;
    private final long size;

    public SizeFileFilter(long size) {
        this(size, true);
    }

    public SizeFileFilter(long size, boolean acceptLarger) {
        if (size < 0L) {
            throw new IllegalArgumentException("The size must be non-negative");
        }
        this.size = size;
        this.acceptLarger = acceptLarger;
    }

    @Override
    public boolean accept(File file) {
        return this.accept(file.length());
    }

    private boolean accept(long length) {
        return this.acceptLarger != length < this.size;
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        try {
            return SizeFileFilter.toFileVisitResult(this.accept(Files.size(file)), file);
        } catch (IOException e) {
            return this.handle(e);
        }
    }

    @Override
    public String toString() {
        String condition = this.acceptLarger ? ">=" : "<";
        return super.toString() + "(" + condition + this.size + ")";
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return SizeFileFilter.toFileVisitResult(this.accept(Files.size(file)), file);
    }
}

