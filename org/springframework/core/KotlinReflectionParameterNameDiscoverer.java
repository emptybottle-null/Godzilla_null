/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  kotlin.reflect.KFunction
 *  kotlin.reflect.KParameter
 *  kotlin.reflect.KParameter$Kind
 *  kotlin.reflect.jvm.ReflectJvmMapping
 */
package org.springframework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.springframework.core.KotlinDetector;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.lang.Nullable;

public class KotlinReflectionParameterNameDiscoverer
implements ParameterNameDiscoverer {
    @Override
    @Nullable
    public String[] getParameterNames(Method method) {
        if (!KotlinDetector.isKotlinType(method.getDeclaringClass())) {
            return null;
        }
        try {
            KFunction function = ReflectJvmMapping.getKotlinFunction((Method)method);
            return function != null ? this.getParameterNames(function.getParameters()) : null;
        } catch (UnsupportedOperationException ex) {
            return null;
        }
    }

    @Override
    @Nullable
    public String[] getParameterNames(Constructor<?> ctor) {
        if (ctor.getDeclaringClass().isEnum() || !KotlinDetector.isKotlinType(ctor.getDeclaringClass())) {
            return null;
        }
        try {
            KFunction function = ReflectJvmMapping.getKotlinFunction(ctor);
            return function != null ? this.getParameterNames(function.getParameters()) : null;
        } catch (UnsupportedOperationException ex) {
            return null;
        }
    }

    @Nullable
    private String[] getParameterNames(List<KParameter> parameters) {
        List filteredParameters = parameters.stream().filter(p -> KParameter.Kind.VALUE.equals((Object)p.getKind()) || KParameter.Kind.EXTENSION_RECEIVER.equals((Object)p.getKind())).collect(Collectors.toList());
        String[] parameterNames = new String[filteredParameters.size()];
        for (int i = 0; i < filteredParameters.size(); ++i) {
            String name;
            KParameter parameter = (KParameter)filteredParameters.get(i);
            String string = name = KParameter.Kind.EXTENSION_RECEIVER.equals((Object)parameter.getKind()) ? "$receiver" : parameter.getName();
            if (name == null) {
                return null;
            }
            parameterNames[i] = name;
        }
        return parameterNames;
    }
}

