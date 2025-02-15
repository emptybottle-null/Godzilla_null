/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.style;

import org.springframework.core.style.DefaultValueStyler;
import org.springframework.core.style.ValueStyler;

public abstract class StylerUtils {
    static final ValueStyler DEFAULT_VALUE_STYLER = new DefaultValueStyler();

    public static String style(Object value) {
        return DEFAULT_VALUE_STYLER.style(value);
    }
}

