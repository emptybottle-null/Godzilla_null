/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.core.Predicate;
import org.springframework.cglib.core.Transformer;

public class CollectionUtils {
    private CollectionUtils() {
    }

    public static Map bucket(Collection c, Transformer t) {
        HashMap buckets = new HashMap();
        for (Object value : c) {
            Object key = t.transform(value);
            LinkedList bucket = (LinkedList)buckets.get(key);
            if (bucket == null) {
                bucket = new LinkedList();
                buckets.put(key, bucket);
            }
            bucket.add(value);
        }
        return buckets;
    }

    public static void reverse(Map source, Map target) {
        for (Object key : source.keySet()) {
            target.put(source.get(key), key);
        }
    }

    public static Collection filter(Collection c, Predicate p) {
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (p.evaluate(it.next())) continue;
            it.remove();
        }
        return c;
    }

    public static List transform(Collection c, Transformer t) {
        ArrayList<Object> result = new ArrayList<Object>(c.size());
        Iterator it = c.iterator();
        while (it.hasNext()) {
            result.add(t.transform(it.next()));
        }
        return result;
    }

    public static Map getIndexMap(List list) {
        HashMap indexes = new HashMap();
        int index = 0;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            indexes.put(it.next(), new Integer(index++));
        }
        return indexes;
    }
}

