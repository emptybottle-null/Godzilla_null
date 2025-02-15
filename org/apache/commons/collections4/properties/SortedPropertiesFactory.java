/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.properties;

import org.apache.commons.collections4.properties.AbstractPropertiesFactory;
import org.apache.commons.collections4.properties.SortedProperties;

public class SortedPropertiesFactory
extends AbstractPropertiesFactory<SortedProperties> {
    public static final SortedPropertiesFactory INSTANCE = new SortedPropertiesFactory();

    private SortedPropertiesFactory() {
    }

    @Override
    protected SortedProperties createProperties() {
        return new SortedProperties();
    }
}

