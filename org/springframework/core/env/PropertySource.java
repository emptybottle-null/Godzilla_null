/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public abstract class PropertySource<T> {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected final String name;
    protected final T source;

    public PropertySource(String name, T source) {
        Assert.hasText(name, "Property source name must contain at least one character");
        Assert.notNull(source, "Property source must not be null");
        this.name = name;
        this.source = source;
    }

    public PropertySource(String name) {
        this(name, new Object());
    }

    public String getName() {
        return this.name;
    }

    public T getSource() {
        return this.source;
    }

    public boolean containsProperty(String name) {
        return this.getProperty(name) != null;
    }

    @Nullable
    public abstract Object getProperty(String var1);

    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof PropertySource && ObjectUtils.nullSafeEquals(this.getName(), ((PropertySource)other).getName());
    }

    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.getName());
    }

    public String toString() {
        if (this.logger.isDebugEnabled()) {
            return this.getClass().getSimpleName() + "@" + System.identityHashCode(this) + " {name='" + this.getName() + "', properties=" + this.getSource() + "}";
        }
        return this.getClass().getSimpleName() + " {name='" + this.getName() + "'}";
    }

    public static PropertySource<?> named(String name) {
        return new ComparisonPropertySource(name);
    }

    static class ComparisonPropertySource
    extends StubPropertySource {
        private static final String USAGE_ERROR = "ComparisonPropertySource instances are for use with collection comparison only";

        public ComparisonPropertySource(String name) {
            super(name);
        }

        @Override
        public Object getSource() {
            throw new UnsupportedOperationException(USAGE_ERROR);
        }

        @Override
        public boolean containsProperty(String name) {
            throw new UnsupportedOperationException(USAGE_ERROR);
        }

        @Override
        @Nullable
        public String getProperty(String name) {
            throw new UnsupportedOperationException(USAGE_ERROR);
        }
    }

    public static class StubPropertySource
    extends PropertySource<Object> {
        public StubPropertySource(String name) {
            super(name, new Object());
        }

        @Override
        @Nullable
        public String getProperty(String name) {
            return null;
        }
    }
}

