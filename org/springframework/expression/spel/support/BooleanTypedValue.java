/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.expression.spel.support;

import org.springframework.expression.TypedValue;

public final class BooleanTypedValue
extends TypedValue {
    public static final BooleanTypedValue TRUE = new BooleanTypedValue(true);
    public static final BooleanTypedValue FALSE = new BooleanTypedValue(false);

    private BooleanTypedValue(boolean b) {
        super(b);
    }

    public static BooleanTypedValue forValue(boolean b) {
        return b ? TRUE : FALSE;
    }
}

