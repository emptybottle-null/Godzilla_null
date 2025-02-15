/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class AgeFileFilter
extends AbstractFileFilter
implements Serializable {
    private static final long serialVersionUID = -2132740084016138541L;
    private final boolean acceptOlder;
    private final long cutoffMillis;

    public AgeFileFilter(Date cutoffDate) {
        this(cutoffDate, true);
    }

    public AgeFileFilter(Date cutoffDate, boolean acceptOlder) {
        this(cutoffDate.getTime(), acceptOlder);
    }

    public AgeFileFilter(File cutoffReference) {
        this(cutoffReference, true);
    }

    public AgeFileFilter(File cutoffReference, boolean acceptOlder) {
        this(FileUtils.lastModifiedUnchecked(cutoffReference), acceptOlder);
    }

    public AgeFileFilter(long cutoffMillis) {
        this(cutoffMillis, true);
    }

    public AgeFileFilter(long cutoffMillis, boolean acceptOlder) {
        this.acceptOlder = acceptOlder;
        this.cutoffMillis = cutoffMillis;
    }

    @Override
    public boolean accept(File file) {
        boolean newer = FileUtils.isFileNewer(file, this.cutoffMillis);
        return this.acceptOlder != newer;
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        boolean newer;
        try {
            newer = PathUtils.isNewer(file, this.cutoffMillis, new LinkOption[0]);
        } catch (IOException e) {
            return this.handle(e);
        }
        return AgeFileFilter.toFileVisitResult(this.acceptOlder != newer, file);
    }

    @Override
    public String toString() {
        String condition = this.acceptOlder ? "<=" : ">";
        return super.toString() + "(" + condition + this.cutoffMillis + ")";
    }
}

