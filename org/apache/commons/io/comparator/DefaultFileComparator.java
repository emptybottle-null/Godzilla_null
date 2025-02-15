/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.comparator.AbstractFileComparator;
import org.apache.commons.io.comparator.ReverseFileComparator;

public class DefaultFileComparator
extends AbstractFileComparator
implements Serializable {
    private static final long serialVersionUID = 3260141861365313518L;
    public static final Comparator<File> DEFAULT_COMPARATOR = new DefaultFileComparator();
    public static final Comparator<File> DEFAULT_REVERSE = new ReverseFileComparator(DEFAULT_COMPARATOR);

    @Override
    public int compare(File file1, File file2) {
        return file1.compareTo(file2);
    }
}

