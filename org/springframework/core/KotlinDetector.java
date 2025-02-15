/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

public abstract class KotlinDetector {
    @Nullable
    private static final Class<? extends Annotation> kotlinMetadata;
    private static final boolean kotlinReflectPresent;

    public static boolean isKotlinPresent() {
        return kotlinMetadata != null;
    }

    public static boolean isKotlinReflectPresent() {
        return kotlinReflectPresent;
    }

    public static boolean isKotlinType(Class<?> clazz) {
        return kotlinMetadata != null && clazz.getDeclaredAnnotation(kotlinMetadata) != null;
    }

    public static boolean isSuspendingFunction(Method method) {
        Class<?>[] types;
        return KotlinDetector.isKotlinType(method.getDeclaringClass()) && (types = method.getParameterTypes()).length > 0 && "kotlin.coroutines.Continuation".equals(types[types.length - 1].getName());
    }

    static {
        Class<?> metadata;
        ClassLoader classLoader = KotlinDetector.class.getClassLoader();
        try {
            metadata = ClassUtils.forName("kotlin.Metadata", classLoader);
        } catch (ClassNotFoundException ex) {
            metadata = null;
        }
        kotlinMetadata = metadata;
        kotlinReflectPresent = ClassUtils.isPresent("kotlin.reflect.full.KClasses", classLoader);
    }
}

