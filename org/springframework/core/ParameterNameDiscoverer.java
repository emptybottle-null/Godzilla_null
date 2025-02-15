/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.springframework.lang.Nullable;

public interface ParameterNameDiscoverer {
    @Nullable
    public String[] getParameterNames(Method var1);

    @Nullable
    public String[] getParameterNames(Constructor<?> var1);
}

