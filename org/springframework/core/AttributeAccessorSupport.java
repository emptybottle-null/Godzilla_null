/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.core.AttributeAccessor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public abstract class AttributeAccessorSupport
implements AttributeAccessor,
Serializable {
    private final Map<String, Object> attributes = new LinkedHashMap<String, Object>();

    @Override
    public void setAttribute(String name, @Nullable Object value) {
        Assert.notNull((Object)name, "Name must not be null");
        if (value != null) {
            this.attributes.put(name, value);
        } else {
            this.removeAttribute(name);
        }
    }

    @Override
    @Nullable
    public Object getAttribute(String name) {
        Assert.notNull((Object)name, "Name must not be null");
        return this.attributes.get(name);
    }

    @Override
    public <T> T computeAttribute(String name, Function<String, T> computeFunction) {
        Assert.notNull((Object)name, "Name must not be null");
        Assert.notNull(computeFunction, "Compute function must not be null");
        Object value = this.attributes.computeIfAbsent(name, computeFunction);
        Assert.state(value != null, () -> String.format("Compute function must not return null for attribute named '%s'", name));
        return (T)value;
    }

    @Override
    @Nullable
    public Object removeAttribute(String name) {
        Assert.notNull((Object)name, "Name must not be null");
        return this.attributes.remove(name);
    }

    @Override
    public boolean hasAttribute(String name) {
        Assert.notNull((Object)name, "Name must not be null");
        return this.attributes.containsKey(name);
    }

    @Override
    public String[] attributeNames() {
        return StringUtils.toStringArray(this.attributes.keySet());
    }

    protected void copyAttributesFrom(AttributeAccessor source) {
        String[] attributeNames;
        Assert.notNull((Object)source, "Source must not be null");
        for (String attributeName : attributeNames = source.attributeNames()) {
            this.setAttribute(attributeName, source.getAttribute(attributeName));
        }
    }

    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof AttributeAccessorSupport && this.attributes.equals(((AttributeAccessorSupport)other).attributes);
    }

    public int hashCode() {
        return this.attributes.hashCode();
    }
}

