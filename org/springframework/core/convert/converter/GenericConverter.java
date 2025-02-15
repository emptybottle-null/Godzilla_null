/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.converter;

import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public interface GenericConverter {
    @Nullable
    public Set<ConvertiblePair> getConvertibleTypes();

    @Nullable
    public Object convert(@Nullable Object var1, TypeDescriptor var2, TypeDescriptor var3);

    public static final class ConvertiblePair {
        private final Class<?> sourceType;
        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "Source type must not be null");
            Assert.notNull(targetType, "Target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return this.sourceType;
        }

        public Class<?> getTargetType() {
            return this.targetType;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || other.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair otherPair = (ConvertiblePair)other;
            return this.sourceType == otherPair.sourceType && this.targetType == otherPair.targetType;
        }

        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }

        public String toString() {
            return this.sourceType.getName() + " -> " + this.targetType.getName();
        }
    }
}

