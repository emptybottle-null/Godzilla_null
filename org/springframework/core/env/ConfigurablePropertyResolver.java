/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.core.env.PropertyResolver;
import org.springframework.lang.Nullable;

public interface ConfigurablePropertyResolver
extends PropertyResolver {
    public ConfigurableConversionService getConversionService();

    public void setConversionService(ConfigurableConversionService var1);

    public void setPlaceholderPrefix(String var1);

    public void setPlaceholderSuffix(String var1);

    public void setValueSeparator(@Nullable String var1);

    public void setIgnoreUnresolvableNestedPlaceholders(boolean var1);

    public void setRequiredProperties(String ... var1);

    public void validateRequiredProperties() throws MissingRequiredPropertiesException;
}

