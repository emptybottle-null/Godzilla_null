/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.comparator.AbstractFileComparator;
import org.apache.commons.io.comparator.ReverseFileComparator;

public class DirectoryFileComparator
extends AbstractFileComparator
implements Serializable {
    private static final int TYPE_FILE = 2;
    private static final int TYPE_DIRECTORY = 1;
    private static final long serialVersionUID = 296132640160964395L;
    public static final Comparator<File> DIRECTORY_COMPARATOR = new DirectoryFileComparator();
    public static final Comparator<File> DIRECTORY_REVERSE = new ReverseFileComparator(DIRECTORY_COMPARATOR);

    @Override
    public int compare(File file1, File file2) {
        return this.getType(file1) - this.getType(file2);
    }

    private int getType(File file) {
        return file.isDirectory() ? 1 : 2;
    }
}

