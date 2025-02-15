/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.style;

import org.springframework.lang.Nullable;

public interface ToStringStyler {
    public void styleStart(StringBuilder var1, Object var2);

    public void styleEnd(StringBuilder var1, Object var2);

    public void styleField(StringBuilder var1, String var2, @Nullable Object var3);

    public void styleValue(StringBuilder var1, Object var2);

    public void styleFieldSeparator(StringBuilder var1);
}

