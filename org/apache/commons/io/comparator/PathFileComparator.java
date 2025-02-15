/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.AbstractFileComparator;
import org.apache.commons.io.comparator.ReverseFileComparator;

public class PathFileComparator
extends AbstractFileComparator
implements Serializable {
    private static final long serialVersionUID = 6527501707585768673L;
    public static final Comparator<File> PATH_COMPARATOR = new PathFileComparator();
    public static final Comparator<File> PATH_REVERSE = new ReverseFileComparator(PATH_COMPARATOR);
    public static final Comparator<File> PATH_INSENSITIVE_COMPARATOR = new PathFileComparator(IOCase.INSENSITIVE);
    public static final Comparator<File> PATH_INSENSITIVE_REVERSE = new ReverseFileComparator(PATH_INSENSITIVE_COMPARATOR);
    public static final Comparator<File> PATH_SYSTEM_COMPARATOR = new PathFileComparator(IOCase.SYSTEM);
    public static final Comparator<File> PATH_SYSTEM_REVERSE = new ReverseFileComparator(PATH_SYSTEM_COMPARATOR);
    private final IOCase caseSensitivity;

    public PathFileComparator() {
        this.caseSensitivity = IOCase.SENSITIVE;
    }

    public PathFileComparator(IOCase caseSensitivity) {
        this.caseSensitivity = caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }

    @Override
    public int compare(File file1, File file2) {
        return this.caseSensitivity.checkCompareTo(file1.getPath(), file2.getPath());
    }

    @Override
    public String toString() {
        return super.toString() + "[caseSensitivity=" + (Object)((Object)this.caseSensitivity) + "]";
    }
}

