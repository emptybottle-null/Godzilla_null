/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class MoreObjects {
    public static <T> T firstNonNull(@Nullable T first, @Nullable T second) {
        if (first != null) {
            return first;
        }
        if (second != null) {
            return second;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(self.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> clazz) {
        return new ToStringHelper(clazz.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String className) {
        return new ToStringHelper(className);
    }

    private MoreObjects() {
    }

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;

        private ToStringHelper(String className) {
            this.holderTail = this.holderHead = new ValueHolder();
            this.omitNullValues = false;
            this.className = Preconditions.checkNotNull(className);
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, @Nullable Object value) {
            return this.addHolder(name, value);
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, boolean value) {
            return this.addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, char value) {
            return this.addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, double value) {
            return this.addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, float value) {
            return this.addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, int value) {
            return this.addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String name, long value) {
            return this.addHolder(name, String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(@Nullable Object value) {
            return this.addHolder(value);
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean value) {
            return this.addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char value) {
            return this.addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double value) {
            return this.addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float value) {
            return this.addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int value) {
            return this.addHolder(String.valueOf(value));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long value) {
            return this.addHolder(String.valueOf(value));
        }

        public String toString() {
            boolean omitNullValuesSnapshot = this.omitNullValues;
            String nextSeparator = "";
            StringBuilder builder = new StringBuilder(32).append(this.className).append('{');
            ValueHolder valueHolder = this.holderHead.next;
            while (valueHolder != null) {
                Object value = valueHolder.value;
                if (!omitNullValuesSnapshot || value != null) {
                    builder.append(nextSeparator);
                    nextSeparator = ", ";
                    if (valueHolder.name != null) {
                        builder.append(valueHolder.name).append('=');
                    }
                    if (value != null && value.getClass().isArray()) {
                        Object[] objectArray = new Object[]{value};
                        String arrayString = Arrays.deepToString(objectArray);
                        builder.append(arrayString, 1, arrayString.length() - 1);
                    } else {
                        builder.append(value);
                    }
                }
                valueHolder = valueHolder.next;
            }
            return builder.append('}').toString();
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder;
            this.holderTail = this.holderTail.next = (valueHolder = new ValueHolder());
            return valueHolder;
        }

        private ToStringHelper addHolder(@Nullable Object value) {
            ValueHolder valueHolder = this.addHolder();
            valueHolder.value = value;
            return this;
        }

        private ToStringHelper addHolder(String name, @Nullable Object value) {
            ValueHolder valueHolder = this.addHolder();
            valueHolder.value = value;
            valueHolder.name = Preconditions.checkNotNull(name);
            return this;
        }

        private static final class ValueHolder {
            @Nullable String name;
            @Nullable Object value;
            @Nullable ValueHolder next;

            private ValueHolder() {
            }
        }
    }
}

