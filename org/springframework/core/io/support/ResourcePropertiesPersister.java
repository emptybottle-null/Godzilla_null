/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.springframework.core.SpringProperties;
import org.springframework.util.DefaultPropertiesPersister;

public class ResourcePropertiesPersister
extends DefaultPropertiesPersister {
    public static final ResourcePropertiesPersister INSTANCE = new ResourcePropertiesPersister();
    private static final boolean shouldIgnoreXml = SpringProperties.getFlag("spring.xml.ignore");

    @Override
    public void loadFromXml(Properties props, InputStream is) throws IOException {
        if (shouldIgnoreXml) {
            throw new UnsupportedOperationException("XML support disabled");
        }
        super.loadFromXml(props, is);
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header) throws IOException {
        if (shouldIgnoreXml) {
            throw new UnsupportedOperationException("XML support disabled");
        }
        super.storeToXml(props, os, header);
    }

    @Override
    public void storeToXml(Properties props, OutputStream os, String header, String encoding) throws IOException {
        if (shouldIgnoreXml) {
            throw new UnsupportedOperationException("XML support disabled");
        }
        super.storeToXml(props, os, header, encoding);
    }
}

