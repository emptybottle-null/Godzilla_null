/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

public class CompositePropertySource
extends EnumerablePropertySource<Object> {
    private final Set<PropertySource<?>> propertySources = new LinkedHashSet();

    public CompositePropertySource(String name) {
        super(name);
    }

    @Override
    @Nullable
    public Object getProperty(String name) {
        for (PropertySource<?> propertySource : this.propertySources) {
            Object candidate = propertySource.getProperty(name);
            if (candidate == null) continue;
            return candidate;
        }
        return null;
    }

    @Override
    public boolean containsProperty(String name) {
        for (PropertySource<?> propertySource : this.propertySources) {
            if (!propertySource.containsProperty(name)) continue;
            return true;
        }
        return false;
    }

    @Override
    public String[] getPropertyNames() {
        LinkedHashSet<String> names = new LinkedHashSet<String>();
        for (PropertySource<?> propertySource : this.propertySources) {
            if (!(propertySource instanceof EnumerablePropertySource)) {
                throw new IllegalStateException("Failed to enumerate property names due to non-enumerable property source: " + propertySource);
            }
            names.addAll(Arrays.asList(((EnumerablePropertySource)propertySource).getPropertyNames()));
        }
        return StringUtils.toStringArray(names);
    }

    public void addPropertySource(PropertySource<?> propertySource) {
        this.propertySources.add(propertySource);
    }

    public void addFirstPropertySource(PropertySource<?> propertySource) {
        ArrayList existing = new ArrayList(this.propertySources);
        this.propertySources.clear();
        this.propertySources.add(propertySource);
        this.propertySources.addAll(existing);
    }

    public Collection<PropertySource<?>> getPropertySources() {
        return this.propertySources;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {name='" + this.name + "', propertySources=" + this.propertySources + "}";
    }
}

