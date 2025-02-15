/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.lang.reflect.Method;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public final class MethodClassKey
implements Comparable<MethodClassKey> {
    private final Method method;
    @Nullable
    private final Class<?> targetClass;

    public MethodClassKey(Method method, @Nullable Class<?> targetClass) {
        this.method = method;
        this.targetClass = targetClass;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MethodClassKey)) {
            return false;
        }
        MethodClassKey otherKey = (MethodClassKey)other;
        return this.method.equals(otherKey.method) && ObjectUtils.nullSafeEquals(this.targetClass, otherKey.targetClass);
    }

    public int hashCode() {
        return this.method.hashCode() + (this.targetClass != null ? this.targetClass.hashCode() * 29 : 0);
    }

    public String toString() {
        return this.method + (this.targetClass != null ? " on " + this.targetClass : "");
    }

    @Override
    public int compareTo(MethodClassKey other) {
        int result = this.method.getName().compareTo(other.method.getName());
        if (result == 0 && (result = this.method.toString().compareTo(other.method.toString())) == 0 && this.targetClass != null && other.targetClass != null) {
            result = this.targetClass.getName().compareTo(other.targetClass.getName());
        }
        return result;
    }
}

