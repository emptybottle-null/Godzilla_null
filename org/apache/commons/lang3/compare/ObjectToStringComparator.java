/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.compare;

import java.io.Serializable;
import java.util.Comparator;

public final class ObjectToStringComparator
implements Comparator<Object>,
Serializable {
    public static final ObjectToStringComparator INSTANCE = new ObjectToStringComparator();
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        String string1 = o1.toString();
        String string2 = o2.toString();
        if (string1 == null && string2 == null) {
            return 0;
        }
        if (string1 == null) {
            return 1;
        }
        if (string2 == null) {
            return -1;
        }
        return string1.compareTo(string2);
    }
}

