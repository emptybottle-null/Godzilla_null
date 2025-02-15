/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.function.Supplier;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public final class ReactiveTypeDescriptor {
    private final Class<?> reactiveType;
    private final boolean multiValue;
    private final boolean noValue;
    @Nullable
    private final Supplier<?> emptyValueSupplier;
    private final boolean deferred;

    private ReactiveTypeDescriptor(Class<?> reactiveType, boolean multiValue, boolean noValue, @Nullable Supplier<?> emptySupplier) {
        this(reactiveType, multiValue, noValue, emptySupplier, true);
    }

    private ReactiveTypeDescriptor(Class<?> reactiveType, boolean multiValue, boolean noValue, @Nullable Supplier<?> emptySupplier, boolean deferred) {
        Assert.notNull(reactiveType, "'reactiveType' must not be null");
        this.reactiveType = reactiveType;
        this.multiValue = multiValue;
        this.noValue = noValue;
        this.emptyValueSupplier = emptySupplier;
        this.deferred = deferred;
    }

    public Class<?> getReactiveType() {
        return this.reactiveType;
    }

    public boolean isMultiValue() {
        return this.multiValue;
    }

    public boolean isNoValue() {
        return this.noValue;
    }

    public boolean supportsEmpty() {
        return this.emptyValueSupplier != null;
    }

    public Object getEmptyValue() {
        Assert.state(this.emptyValueSupplier != null, "Empty values not supported");
        return this.emptyValueSupplier.get();
    }

    public boolean isDeferred() {
        return this.deferred;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        return this.reactiveType.equals(((ReactiveTypeDescriptor)other).reactiveType);
    }

    public int hashCode() {
        return this.reactiveType.hashCode();
    }

    public static ReactiveTypeDescriptor multiValue(Class<?> type, Supplier<?> emptySupplier) {
        return new ReactiveTypeDescriptor(type, true, false, emptySupplier);
    }

    public static ReactiveTypeDescriptor singleOptionalValue(Class<?> type, Supplier<?> emptySupplier) {
        return new ReactiveTypeDescriptor(type, false, false, emptySupplier);
    }

    public static ReactiveTypeDescriptor singleRequiredValue(Class<?> type) {
        return new ReactiveTypeDescriptor(type, false, false, null);
    }

    public static ReactiveTypeDescriptor noValue(Class<?> type, Supplier<?> emptySupplier) {
        return new ReactiveTypeDescriptor(type, false, true, emptySupplier);
    }

    public static ReactiveTypeDescriptor nonDeferredAsyncValue(Class<?> type, Supplier<?> emptySupplier) {
        return new ReactiveTypeDescriptor(type, false, false, emptySupplier, false);
    }
}

