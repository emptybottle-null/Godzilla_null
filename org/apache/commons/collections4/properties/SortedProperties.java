/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.collections4.iterators.IteratorEnumeration;

public class SortedProperties
extends Properties {
    private static final long serialVersionUID = 1L;

    @Override
    public synchronized Enumeration<Object> keys() {
        Set keySet = this.keySet();
        ArrayList<String> keys = new ArrayList<String>(keySet.size());
        for (Object key : keySet) {
            keys.add(key.toString());
        }
        Collections.sort(keys);
        return new IteratorEnumeration<Object>(keys.iterator());
    }
}

