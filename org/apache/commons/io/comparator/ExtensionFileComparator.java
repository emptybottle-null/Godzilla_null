/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.AbstractFileComparator;
import org.apache.commons.io.comparator.ReverseFileComparator;

public class ExtensionFileComparator
extends AbstractFileComparator
implements Serializable {
    private static final long serialVersionUID = 1928235200184222815L;
    public static final Comparator<File> EXTENSION_COMPARATOR = new ExtensionFileComparator();
    public static final Comparator<File> EXTENSION_REVERSE = new ReverseFileComparator(EXTENSION_COMPARATOR);
    public static final Comparator<File> EXTENSION_INSENSITIVE_COMPARATOR = new ExtensionFileComparator(IOCase.INSENSITIVE);
    public static final Comparator<File> EXTENSION_INSENSITIVE_REVERSE = new ReverseFileComparator(EXTENSION_INSENSITIVE_COMPARATOR);
    public static final Comparator<File> EXTENSION_SYSTEM_COMPARATOR = new ExtensionFileComparator(IOCase.SYSTEM);
    public static final Comparator<File> EXTENSION_SYSTEM_REVERSE = new ReverseFileComparator(EXTENSION_SYSTEM_COMPARATOR);
    private final IOCase caseSensitivity;

    public ExtensionFileComparator() {
        this.caseSensitivity = IOCase.SENSITIVE;
    }

    public ExtensionFileComparator(IOCase caseSensitivity) {
        this.caseSensitivity = caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }

    @Override
    public int compare(File file1, File file2) {
        String suffix1 = FilenameUtils.getExtension(file1.getName());
        String suffix2 = FilenameUtils.getExtension(file2.getName());
        return this.caseSensitivity.checkCompareTo(suffix1, suffix2);
    }

    @Override
    public String toString() {
        return super.toString() + "[caseSensitivity=" + (Object)((Object)this.caseSensitivity) + "]";
    }
}

