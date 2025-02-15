/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum BoundType {
    OPEN(false),
    CLOSED(true);

    final boolean inclusive;

    private BoundType(boolean inclusive) {
        this.inclusive = inclusive;
    }

    static BoundType forBoolean(boolean inclusive) {
        return inclusive ? CLOSED : OPEN;
    }

    BoundType flip() {
        return BoundType.forBoolean(!this.inclusive);
    }
}

