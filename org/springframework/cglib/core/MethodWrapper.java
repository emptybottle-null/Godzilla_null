/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.cglib.core.ReflectUtils;

public class MethodWrapper {
    private static final MethodWrapperKey KEY_FACTORY = (MethodWrapperKey)((Object)KeyFactory.create(MethodWrapperKey.class));

    private MethodWrapper() {
    }

    public static Object create(Method method) {
        return KEY_FACTORY.newInstance(method.getName(), ReflectUtils.getNames(method.getParameterTypes()), method.getReturnType().getName());
    }

    public static Set createSet(Collection methods) {
        HashSet<Object> set = new HashSet<Object>();
        Iterator it = methods.iterator();
        while (it.hasNext()) {
            set.add(MethodWrapper.create((Method)it.next()));
        }
        return set;
    }

    public static interface MethodWrapperKey {
        public Object newInstance(String var1, String[] var2, String var3);
    }
}

