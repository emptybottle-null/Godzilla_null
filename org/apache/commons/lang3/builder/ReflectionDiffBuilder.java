/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.reflect.FieldUtils;

public class ReflectionDiffBuilder<T>
implements Builder<DiffResult<T>> {
    private final Object left;
    private final Object right;
    private final DiffBuilder<T> diffBuilder;

    public ReflectionDiffBuilder(T lhs, T rhs, ToStringStyle style) {
        this.left = lhs;
        this.right = rhs;
        this.diffBuilder = new DiffBuilder<T>(lhs, rhs, style);
    }

    @Override
    public DiffResult<T> build() {
        if (this.left.equals(this.right)) {
            return this.diffBuilder.build();
        }
        this.appendFields(this.left.getClass());
        return this.diffBuilder.build();
    }

    private void appendFields(Class<?> clazz) {
        for (Field field : FieldUtils.getAllFields(clazz)) {
            if (!this.accept(field)) continue;
            try {
                this.diffBuilder.append(field.getName(), FieldUtils.readField(field, this.left, true), FieldUtils.readField(field, this.right, true));
            } catch (IllegalAccessException ex) {
                throw new InternalError("Unexpected IllegalAccessException: " + ex.getMessage());
            }
        }
    }

    private boolean accept(Field field) {
        if (field.getName().indexOf(36) != -1) {
            return false;
        }
        if (Modifier.isTransient(field.getModifiers())) {
            return false;
        }
        return !Modifier.isStatic(field.getModifiers());
    }
}

