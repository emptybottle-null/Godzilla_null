/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import org.springframework.cglib.core.KeyFactory;

public class ClassesKey {
    private static final Key FACTORY = (Key)((Object)KeyFactory.create(Key.class));

    private ClassesKey() {
    }

    public static Object create(Object[] array) {
        return FACTORY.newInstance(ClassesKey.classNames(array));
    }

    private static String[] classNames(Object[] objects) {
        if (objects == null) {
            return null;
        }
        String[] classNames = new String[objects.length];
        for (int i = 0; i < objects.length; ++i) {
            Object object = objects[i];
            if (object == null) continue;
            Class<?> aClass = object.getClass();
            classNames[i] = aClass == null ? null : aClass.getName();
        }
        return classNames;
    }

    static interface Key {
        public Object newInstance(Object[] var1);
    }
}

