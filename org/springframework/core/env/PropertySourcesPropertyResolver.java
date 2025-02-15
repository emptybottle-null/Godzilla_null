/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.springframework.core.env.AbstractPropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.lang.Nullable;

public class PropertySourcesPropertyResolver
extends AbstractPropertyResolver {
    @Nullable
    private final PropertySources propertySources;

    public PropertySourcesPropertyResolver(@Nullable PropertySources propertySources) {
        this.propertySources = propertySources;
    }

    @Override
    public boolean containsProperty(String key) {
        if (this.propertySources != null) {
            for (PropertySource propertySource : this.propertySources) {
                if (!propertySource.containsProperty(key)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    @Nullable
    public String getProperty(String key) {
        return this.getProperty(key, String.class, true);
    }

    @Override
    @Nullable
    public <T> T getProperty(String key, Class<T> targetValueType) {
        return this.getProperty(key, targetValueType, true);
    }

    @Override
    @Nullable
    protected String getPropertyAsRawString(String key) {
        return this.getProperty(key, String.class, false);
    }

    @Nullable
    protected <T> T getProperty(String key, Class<T> targetValueType, boolean resolveNestedPlaceholders) {
        if (this.propertySources != null) {
            for (PropertySource propertySource : this.propertySources) {
                Object value;
                if (this.logger.isTraceEnabled()) {
                    this.logger.trace("Searching for key '" + key + "' in PropertySource '" + propertySource.getName() + "'");
                }
                if ((value = propertySource.getProperty(key)) == null) continue;
                if (resolveNestedPlaceholders && value instanceof String) {
                    value = this.resolveNestedPlaceholders((String)value);
                }
                this.logKeyFound(key, propertySource, value);
                return this.convertValueIfNecessary(value, targetValueType);
            }
        }
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Could not find key '" + key + "' in any property source");
        }
        return null;
    }

    protected void logKeyFound(String key, PropertySource<?> propertySource, Object value) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Found key '" + key + "' in PropertySource '" + propertySource.getName() + "' with value of type " + value.getClass().getSimpleName());
        }
    }
}

