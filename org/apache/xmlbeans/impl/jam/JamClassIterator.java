/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import java.util.Iterator;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JamClassLoader;

public class JamClassIterator
implements Iterator {
    private JamClassLoader mLoader;
    private String[] mClassNames;
    private int mIndex = 0;

    public JamClassIterator(JamClassLoader loader, String[] classes) {
        if (loader == null) {
            throw new IllegalArgumentException("null loader");
        }
        if (classes == null) {
            throw new IllegalArgumentException("null classes");
        }
        this.mLoader = loader;
        this.mClassNames = classes;
    }

    public JClass nextClass() {
        if (!this.hasNext()) {
            throw new IndexOutOfBoundsException();
        }
        ++this.mIndex;
        return this.mLoader.loadClass(this.mClassNames[this.mIndex - 1]);
    }

    @Override
    public boolean hasNext() {
        return this.mIndex < this.mClassNames.length;
    }

    public Object next() {
        return this.nextClass();
    }

    public int getSize() {
        return this.mClassNames.length;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

