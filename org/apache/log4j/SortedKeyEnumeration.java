/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class SortedKeyEnumeration
implements Enumeration {
    private Enumeration e;

    public SortedKeyEnumeration(Hashtable ht) {
        Enumeration f = ht.keys();
        Vector<String> keys = new Vector<String>(ht.size());
        int last = 0;
        while (f.hasMoreElements()) {
            String s;
            int i;
            String key = (String)f.nextElement();
            for (i = 0; i < last && key.compareTo(s = (String)keys.get(i)) > 0; ++i) {
            }
            keys.add(i, key);
            ++last;
        }
        this.e = keys.elements();
    }

    public boolean hasMoreElements() {
        return this.e.hasMoreElements();
    }

    public Object nextElement() {
        return this.e.nextElement();
    }
}

