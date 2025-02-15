/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.annotation.YamlClass;

public class SecClass {
    public static final ArrayList<Class> whiteList = new ArrayList();

    public static Class forName(String name) throws ClassNotFoundException {
        return SecClass.forName(name, false, Thread.currentThread().getContextClassLoader());
    }

    public static Class forName(String name, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
        Class<?> type = Class.forName(name, initialize, loader);
        if (!type.equals(Object.class) && Object.class.isAssignableFrom(type) && type.getAnnotation(YamlClass.class) == null && !type.isArray()) {
            boolean ok = false;
            for (Class whiteClass : whiteList) {
                if (!whiteClass.isAssignableFrom(type)) continue;
                ok = true;
                break;
            }
            if (!ok) {
                throw new ClassNotFoundException(name);
            }
        }
        return type;
    }

    static {
        whiteList.add(CharSequence.class);
        whiteList.add(Map.class);
        whiteList.add(List.class);
        whiteList.add(Number.class);
        whiteList.add(Set.class);
    }
}

