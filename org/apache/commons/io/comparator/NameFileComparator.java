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

public class NameFileComparator
extends AbstractFileComparator
implements Serializable {
    private static final long serialVersionUID = 8397947749814525798L;
    public static final Comparator<File> NAME_COMPARATOR = new NameFileComparator();
    public static final Comparator<File> NAME_REVERSE = new ReverseFileComparator(NAME_COMPARATOR);
    public static final Comparator<File> NAME_INSENSITIVE_COMPARATOR = new NameFileComparator(IOCase.INSENSITIVE);
    public static final Comparator<File> NAME_INSENSITIVE_REVERSE = new ReverseFileComparator(NAME_INSENSITIVE_COMPARATOR);
    public static final Comparator<File> NAME_SYSTEM_COMPARATOR = new NameFileComparator(IOCase.SYSTEM);
    public static final Comparator<File> NAME_SYSTEM_REVERSE = new ReverseFileComparator(NAME_SYSTEM_COMPARATOR);
    private final IOCase caseSensitivity;

    public NameFileComparator() {
        this.caseSensitivity = IOCase.SENSITIVE;
    }

    public NameFileComparator(IOCase caseSensitivity) {
        this.caseSensitivity = caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }

    @Override
    public int compare(File file1, File file2) {
        return this.caseSensitivity.checkCompareTo(file1.getName(), file2.getName());
    }

    @Override
    public String toString() {
        return super.toString() + "[caseSensitivity=" + (Object)((Object)this.caseSensitivity) + "]";
    }
}

