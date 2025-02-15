/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.core.convert.converter.Converter;

final class PropertiesToStringConverter
implements Converter<Properties, String> {
    PropertiesToStringConverter() {
    }

    @Override
    public String convert(Properties source) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(256);
            source.store(os, null);
            return os.toString("ISO-8859-1");
        } catch (IOException ex) {
            throw new IllegalArgumentException("Failed to store [" + source + "] into String", ex);
        }
    }
}

