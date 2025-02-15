/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

abstract class AbstractFileComparator
implements Comparator<File> {
    AbstractFileComparator() {
    }

    public File[] sort(File ... files) {
        if (files != null) {
            Arrays.sort(files, this);
        }
        return files;
    }

    public List<File> sort(List<File> files) {
        if (files != null) {
            files.sort(this);
        }
        return files;
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}

