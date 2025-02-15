/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.LinkedHashSet;
import java.util.Set;

public class MissingRequiredPropertiesException
extends IllegalStateException {
    private final Set<String> missingRequiredProperties = new LinkedHashSet<String>();

    void addMissingRequiredProperty(String key) {
        this.missingRequiredProperties.add(key);
    }

    @Override
    public String getMessage() {
        return "The following properties were declared as required but could not be resolved: " + this.getMissingRequiredProperties();
    }

    public Set<String> getMissingRequiredProperties() {
        return this.missingRequiredProperties;
    }
}

