/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.dev;

import java.util.Iterator;

public interface POIFSViewable {
    public Object[] getViewableArray();

    public Iterator<Object> getViewableIterator();

    public boolean preferArray();

    public String getShortDescription();
}

