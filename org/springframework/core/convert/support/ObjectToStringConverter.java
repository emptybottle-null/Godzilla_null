/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

final class ObjectToStringConverter
implements Converter<Object, String> {
    ObjectToStringConverter() {
    }

    @Override
    public String convert(Object source) {
        return source.toString();
    }
}

