/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.Serializable;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class DelegateFileFilter
extends AbstractFileFilter
implements Serializable {
    private static final long serialVersionUID = -8723373124984771318L;
    private final FileFilter fileFilter;
    private final FilenameFilter filenameFilter;

    public DelegateFileFilter(FileFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("The FileFilter must not be null");
        }
        this.fileFilter = filter;
        this.filenameFilter = null;
    }

    public DelegateFileFilter(FilenameFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("The FilenameFilter must not be null");
        }
        this.filenameFilter = filter;
        this.fileFilter = null;
    }

    @Override
    public boolean accept(File file) {
        if (this.fileFilter != null) {
            return this.fileFilter.accept(file);
        }
        return super.accept(file);
    }

    @Override
    public boolean accept(File dir, String name) {
        if (this.filenameFilter != null) {
            return this.filenameFilter.accept(dir, name);
        }
        return super.accept(dir, name);
    }

    @Override
    public String toString() {
        String delegate = this.fileFilter != null ? this.fileFilter.toString() : this.filenameFilter.toString();
        return super.toString() + "(" + delegate + ")";
    }
}

