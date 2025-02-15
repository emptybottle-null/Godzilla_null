/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AbstractFileFilter;

public class NameFileFilter
extends AbstractFileFilter
implements Serializable {
    private static final long serialVersionUID = 176844364689077340L;
    private final String[] names;
    private final IOCase caseSensitivity;

    public NameFileFilter(List<String> names) {
        this(names, null);
    }

    public NameFileFilter(List<String> names, IOCase caseSensitivity) {
        if (names == null) {
            throw new IllegalArgumentException("The list of names must not be null");
        }
        this.names = names.toArray(EMPTY_STRING_ARRAY);
        this.caseSensitivity = this.toIOCase(caseSensitivity);
    }

    public NameFileFilter(String name) {
        this(name, IOCase.SENSITIVE);
    }

    public NameFileFilter(String ... names) {
        this(names, IOCase.SENSITIVE);
    }

    public NameFileFilter(String name, IOCase caseSensitivity) {
        if (name == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.names = new String[]{name};
        this.caseSensitivity = this.toIOCase(caseSensitivity);
    }

    public NameFileFilter(String[] names, IOCase caseSensitivity) {
        if (names == null) {
            throw new IllegalArgumentException("The array of names must not be null");
        }
        this.names = new String[names.length];
        System.arraycopy(names, 0, this.names, 0, names.length);
        this.caseSensitivity = this.toIOCase(caseSensitivity);
    }

    @Override
    public boolean accept(File file) {
        return this.acceptBaseName(file.getName());
    }

    @Override
    public boolean accept(File dir, String name) {
        return this.acceptBaseName(name);
    }

    @Override
    public FileVisitResult accept(Path file, BasicFileAttributes attributes) {
        return NameFileFilter.toFileVisitResult(this.acceptBaseName(Objects.toString(file.getFileName(), null)), file);
    }

    private boolean acceptBaseName(String baseName) {
        for (String testName : this.names) {
            if (!this.caseSensitivity.checkEquals(baseName, testName)) continue;
            return true;
        }
        return false;
    }

    private IOCase toIOCase(IOCase caseSensitivity) {
        return caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.names != null) {
            for (int i = 0; i < this.names.length; ++i) {
                if (i > 0) {
                    buffer.append(",");
                }
                buffer.append(this.names[i]);
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}

