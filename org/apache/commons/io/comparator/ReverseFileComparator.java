/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.comparator.AbstractFileComparator;

class ReverseFileComparator
extends AbstractFileComparator
implements Serializable {
    private static final long serialVersionUID = -4808255005272229056L;
    private final Comparator<File> delegate;

    public ReverseFileComparator(Comparator<File> delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("Delegate comparator is missing");
        }
        this.delegate = delegate;
    }

    @Override
    public int compare(File file1, File file2) {
        return this.delegate.compare(file2, file1);
    }

    @Override
    public String toString() {
        return super.toString() + "[" + this.delegate.toString() + "]";
    }
}

