/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception.util;

import java.util.ArrayList;

public class ArgUtils {
    private ArgUtils() {
    }

    public static Object[] flatten(Object[] array) {
        ArrayList<Object> list = new ArrayList<Object>();
        if (array != null) {
            for (Object o : array) {
                if (o instanceof Object[]) {
                    for (Object oR : ArgUtils.flatten((Object[])o)) {
                        list.add(oR);
                    }
                    continue;
                }
                list.add(o);
            }
        }
        return list.toArray();
    }
}

