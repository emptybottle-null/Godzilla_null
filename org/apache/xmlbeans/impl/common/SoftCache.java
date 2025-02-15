/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftCache {
    private HashMap map = new HashMap();

    public Object get(Object key) {
        SoftReference softRef = (SoftReference)this.map.get(key);
        if (softRef == null) {
            return null;
        }
        return softRef.get();
    }

    public Object put(Object key, Object value) {
        SoftReference<Object> softRef = this.map.put(key, new SoftReference<Object>(value));
        if (softRef == null) {
            return null;
        }
        Object oldValue = softRef.get();
        softRef.clear();
        return oldValue;
    }

    public Object remove(Object key) {
        SoftReference softRef = (SoftReference)this.map.remove(key);
        if (softRef == null) {
            return null;
        }
        Object oldValue = softRef.get();
        softRef.clear();
        return oldValue;
    }
}

