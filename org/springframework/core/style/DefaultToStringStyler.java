/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.style;

import org.springframework.core.style.ToStringStyler;
import org.springframework.core.style.ValueStyler;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

public class DefaultToStringStyler
implements ToStringStyler {
    private final ValueStyler valueStyler;

    public DefaultToStringStyler(ValueStyler valueStyler) {
        Assert.notNull((Object)valueStyler, "ValueStyler must not be null");
        this.valueStyler = valueStyler;
    }

    protected final ValueStyler getValueStyler() {
        return this.valueStyler;
    }

    @Override
    public void styleStart(StringBuilder buffer, Object obj) {
        if (!obj.getClass().isArray()) {
            buffer.append('[').append(ClassUtils.getShortName(obj.getClass()));
            this.styleIdentityHashCode(buffer, obj);
        } else {
            buffer.append('[');
            this.styleIdentityHashCode(buffer, obj);
            buffer.append(' ');
            this.styleValue(buffer, obj);
        }
    }

    private void styleIdentityHashCode(StringBuilder buffer, Object obj) {
        buffer.append('@');
        buffer.append(ObjectUtils.getIdentityHexString(obj));
    }

    @Override
    public void styleEnd(StringBuilder buffer, Object o) {
        buffer.append(']');
    }

    @Override
    public void styleField(StringBuilder buffer, String fieldName, @Nullable Object value) {
        this.styleFieldStart(buffer, fieldName);
        this.styleValue(buffer, value);
        this.styleFieldEnd(buffer, fieldName);
    }

    protected void styleFieldStart(StringBuilder buffer, String fieldName) {
        buffer.append(' ').append(fieldName).append(" = ");
    }

    protected void styleFieldEnd(StringBuilder buffer, String fieldName) {
    }

    @Override
    public void styleValue(StringBuilder buffer, @Nullable Object value) {
        buffer.append(this.valueStyler.style(value));
    }

    @Override
    public void styleFieldSeparator(StringBuilder buffer) {
        buffer.append(',');
    }
}

