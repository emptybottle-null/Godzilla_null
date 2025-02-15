/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.nio.charset.Charset;
import org.springframework.core.convert.converter.Converter;

class StringToCharsetConverter
implements Converter<String, Charset> {
    StringToCharsetConverter() {
    }

    @Override
    public Charset convert(String source) {
        return Charset.forName(source);
    }
}

