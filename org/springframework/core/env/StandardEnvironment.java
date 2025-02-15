/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.SystemEnvironmentPropertySource;

public class StandardEnvironment
extends AbstractEnvironment {
    public static final String SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME = "systemEnvironment";
    public static final String SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME = "systemProperties";

    public StandardEnvironment() {
    }

    protected StandardEnvironment(MutablePropertySources propertySources) {
        super(propertySources);
    }

    @Override
    protected void customizePropertySources(MutablePropertySources propertySources) {
        propertySources.addLast(new PropertiesPropertySource(SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, this.getSystemProperties()));
        propertySources.addLast(new SystemEnvironmentPropertySource(SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, this.getSystemEnvironment()));
    }
}

