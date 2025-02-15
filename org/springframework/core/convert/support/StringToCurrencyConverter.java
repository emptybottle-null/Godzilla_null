/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.convert.support;

import java.util.Currency;
import org.springframework.core.convert.converter.Converter;

class StringToCurrencyConverter
implements Converter<String, Currency> {
    StringToCurrencyConverter() {
    }

    @Override
    public Currency convert(String source) {
        return Currency.getInstance(source);
    }
}

