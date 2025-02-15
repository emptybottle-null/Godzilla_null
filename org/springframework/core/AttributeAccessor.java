/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.function.Function;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public interface AttributeAccessor {
    public void setAttribute(String var1, @Nullable Object var2);

    @Nullable
    public Object getAttribute(String var1);

    default public <T> T computeAttribute(String name, Function<String, T> computeFunction) {
        Assert.notNull((Object)name, "Name must not be null");
        Assert.notNull(computeFunction, "Compute function must not be null");
        Object value = this.getAttribute(name);
        if (value == null) {
            value = computeFunction.apply(name);
            Assert.state(value != null, () -> String.format("Compute function must not return null for attribute named '%s'", name));
            this.setAttribute(name, value);
        }
        return (T)value;
    }

    @Nullable
    public Object removeAttribute(String var1);

    public boolean hasAttribute(String var1);

    public String[] attributeNames();
}

