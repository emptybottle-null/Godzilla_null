/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.filter;

import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

public class AssignableTypeFilter
extends AbstractTypeHierarchyTraversingFilter {
    private final Class<?> targetType;

    public AssignableTypeFilter(Class<?> targetType) {
        super(true, true);
        this.targetType = targetType;
    }

    public final Class<?> getTargetType() {
        return this.targetType;
    }

    @Override
    protected boolean matchClassName(String className) {
        return this.targetType.getName().equals(className);
    }

    @Override
    @Nullable
    protected Boolean matchSuperClass(String superClassName) {
        return this.matchTargetType(superClassName);
    }

    @Override
    @Nullable
    protected Boolean matchInterface(String interfaceName) {
        return this.matchTargetType(interfaceName);
    }

    @Nullable
    protected Boolean matchTargetType(String typeName) {
        if (this.targetType.getName().equals(typeName)) {
            return true;
        }
        if (Object.class.getName().equals(typeName)) {
            return false;
        }
        if (typeName.startsWith("java")) {
            try {
                Class<?> clazz = ClassUtils.forName(typeName, this.getClass().getClassLoader());
                return this.targetType.isAssignableFrom(clazz);
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return null;
    }
}

