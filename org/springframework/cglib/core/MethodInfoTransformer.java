/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.cglib.core.Transformer;

public class MethodInfoTransformer
implements Transformer {
    private static final MethodInfoTransformer INSTANCE = new MethodInfoTransformer();

    public static MethodInfoTransformer getInstance() {
        return INSTANCE;
    }

    public Object transform(Object value) {
        if (value instanceof Method) {
            return ReflectUtils.getMethodInfo((Method)value);
        }
        if (value instanceof Constructor) {
            return ReflectUtils.getMethodInfo((Constructor)value);
        }
        throw new IllegalArgumentException("cannot get method info for " + value);
    }
}

