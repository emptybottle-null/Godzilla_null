/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public abstract class AbstractPropertyResolver
implements ConfigurablePropertyResolver {
    protected final Log logger = LogFactory.getLog(this.getClass());
    @Nullable
    private volatile ConfigurableConversionService conversionService;
    @Nullable
    private PropertyPlaceholderHelper nonStrictHelper;
    @Nullable
    private PropertyPlaceholderHelper strictHelper;
    private boolean ignoreUnresolvableNestedPlaceholders = false;
    private String placeholderPrefix = "${";
    private String placeholderSuffix = "}";
    @Nullable
    private String valueSeparator = ":";
    private final Set<String> requiredProperties = new LinkedHashSet<String>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public ConfigurableConversionService getConversionService() {
        ConfigurableConversionService cs = this.conversionService;
        if (cs == null) {
            AbstractPropertyResolver abstractPropertyResolver = this;
            synchronized (abstractPropertyResolver) {
                cs = this.conversionService;
                if (cs == null) {
                    this.conversionService = cs = new DefaultConversionService();
                }
            }
        }
        return cs;
    }

    @Override
    public void setConversionService(ConfigurableConversionService conversionService) {
        Assert.notNull((Object)conversionService, "ConversionService must not be null");
        this.conversionService = conversionService;
    }

    @Override
    public void setPlaceholderPrefix(String placeholderPrefix) {
        Assert.notNull((Object)placeholderPrefix, "'placeholderPrefix' must not be null");
        this.placeholderPrefix = placeholderPrefix;
    }

    @Override
    public void setPlaceholderSuffix(String placeholderSuffix) {
        Assert.notNull((Object)placeholderSuffix, "'placeholderSuffix' must not be null");
        this.placeholderSuffix = placeholderSuffix;
    }

    @Override
    public void setValueSeparator(@Nullable String valueSeparator) {
        this.valueSeparator = valueSeparator;
    }

    @Override
    public void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders) {
        this.ignoreUnresolvableNestedPlaceholders = ignoreUnresolvableNestedPlaceholders;
    }

    @Override
    public void setRequiredProperties(String ... requiredProperties) {
        Collections.addAll(this.requiredProperties, requiredProperties);
    }

    @Override
    public void validateRequiredProperties() {
        MissingRequiredPropertiesException ex = new MissingRequiredPropertiesException();
        for (String key : this.requiredProperties) {
            if (this.getProperty(key) != null) continue;
            ex.addMissingRequiredProperty(key);
        }
        if (!ex.getMissingRequiredProperties().isEmpty()) {
            throw ex;
        }
    }

    @Override
    public boolean containsProperty(String key) {
        return this.getProperty(key) != null;
    }

    @Override
    @Nullable
    public String getProperty(String key) {
        return this.getProperty(key, String.class);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String value = this.getProperty(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        T value = this.getProperty(key, targetType);
        return value != null ? value : defaultValue;
    }

    @Override
    public String getRequiredProperty(String key) throws IllegalStateException {
        String value = this.getProperty(key);
        if (value == null) {
            throw new IllegalStateException("Required key '" + key + "' not found");
        }
        return value;
    }

    @Override
    public <T> T getRequiredProperty(String key, Class<T> valueType) throws IllegalStateException {
        T value = this.getProperty(key, valueType);
        if (value == null) {
            throw new IllegalStateException("Required key '" + key + "' not found");
        }
        return value;
    }

    @Override
    public String resolvePlaceholders(String text) {
        if (this.nonStrictHelper == null) {
            this.nonStrictHelper = this.createPlaceholderHelper(true);
        }
        return this.doResolvePlaceholders(text, this.nonStrictHelper);
    }

    @Override
    public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
        if (this.strictHelper == null) {
            this.strictHelper = this.createPlaceholderHelper(false);
        }
        return this.doResolvePlaceholders(text, this.strictHelper);
    }

    protected String resolveNestedPlaceholders(String value) {
        if (value.isEmpty()) {
            return value;
        }
        return this.ignoreUnresolvableNestedPlaceholders ? this.resolvePlaceholders(value) : this.resolveRequiredPlaceholders(value);
    }

    private PropertyPlaceholderHelper createPlaceholderHelper(boolean ignoreUnresolvablePlaceholders) {
        return new PropertyPlaceholderHelper(this.placeholderPrefix, this.placeholderSuffix, this.valueSeparator, ignoreUnresolvablePlaceholders);
    }

    private String doResolvePlaceholders(String text, PropertyPlaceholderHelper helper) {
        return helper.replacePlaceholders(text, this::getPropertyAsRawString);
    }

    @Nullable
    protected <T> T convertValueIfNecessary(Object value, @Nullable Class<T> targetType) {
        if (targetType == null) {
            return (T)value;
        }
        ConversionService conversionServiceToUse = this.conversionService;
        if (conversionServiceToUse == null) {
            if (ClassUtils.isAssignableValue(targetType, value)) {
                return (T)value;
            }
            conversionServiceToUse = DefaultConversionService.getSharedInstance();
        }
        return conversionServiceToUse.convert(value, targetType);
    }

    @Nullable
    protected abstract String getPropertyAsRawString(String var1);
}

