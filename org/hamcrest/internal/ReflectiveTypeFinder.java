/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.internal;

import java.lang.reflect.Method;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ReflectiveTypeFinder {
    private final String methodName;
    private final int expectedNumberOfParameters;
    private final int typedParameter;

    public ReflectiveTypeFinder(String methodName, int expectedNumberOfParameters, int typedParameter) {
        this.methodName = methodName;
        this.expectedNumberOfParameters = expectedNumberOfParameters;
        this.typedParameter = typedParameter;
    }

    public Class<?> findExpectedType(Class<?> fromClass) {
        for (Class<?> c = fromClass; c != Object.class; c = c.getSuperclass()) {
            for (Method method : c.getDeclaredMethods()) {
                if (!this.canObtainExpectedTypeFrom(method)) continue;
                return this.expectedTypeFrom(method);
            }
        }
        throw new Error("Cannot determine correct type for " + this.methodName + "() method.");
    }

    protected boolean canObtainExpectedTypeFrom(Method method) {
        return method.getName().equals(this.methodName) && method.getParameterTypes().length == this.expectedNumberOfParameters && !method.isSynthetic();
    }

    protected Class<?> expectedTypeFrom(Method method) {
        return method.getParameterTypes()[this.typedParameter];
    }
}

