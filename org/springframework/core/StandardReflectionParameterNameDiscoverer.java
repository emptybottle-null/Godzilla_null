/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.lang.Nullable;

public class StandardReflectionParameterNameDiscoverer
implements ParameterNameDiscoverer {
    @Override
    @Nullable
    public String[] getParameterNames(Method method) {
        return this.getParameterNames(method.getParameters());
    }

    @Override
    @Nullable
    public String[] getParameterNames(Constructor<?> ctor) {
        return this.getParameterNames(ctor.getParameters());
    }

    @Nullable
    private String[] getParameterNames(Parameter[] parameters) {
        String[] parameterNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; ++i) {
            Parameter param = parameters[i];
            if (!param.isNamePresent()) {
                return null;
            }
            parameterNames[i] = param.getName();
        }
        return parameterNames;
    }
}

