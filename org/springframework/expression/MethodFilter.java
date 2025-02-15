/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression;

import java.lang.reflect.Method;
import java.util.List;

@FunctionalInterface
public interface MethodFilter {
    public List<Method> filter(List<Method> var1);
}

