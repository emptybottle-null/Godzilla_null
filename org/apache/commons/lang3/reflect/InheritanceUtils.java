/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.reflect;

import org.apache.commons.lang3.BooleanUtils;

public class InheritanceUtils {
    public static int distance(Class<?> child, Class<?> parent) {
        if (child == null || parent == null) {
            return -1;
        }
        if (child.equals(parent)) {
            return 0;
        }
        Class<?> cParent = child.getSuperclass();
        int d = BooleanUtils.toInteger(parent.equals(cParent));
        if (d == 1) {
            return d;
        }
        return (d += InheritanceUtils.distance(cParent, parent)) > 0 ? d + 1 : -1;
    }
}

