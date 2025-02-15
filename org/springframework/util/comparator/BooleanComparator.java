/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.comparator;

import java.io.Serializable;
import java.util.Comparator;
import org.springframework.lang.Nullable;

public class BooleanComparator
implements Comparator<Boolean>,
Serializable {
    public static final BooleanComparator TRUE_LOW = new BooleanComparator(true);
    public static final BooleanComparator TRUE_HIGH = new BooleanComparator(false);
    private final boolean trueLow;

    public BooleanComparator(boolean trueLow) {
        this.trueLow = trueLow;
    }

    @Override
    public int compare(Boolean v1, Boolean v2) {
        return v1 ^ v2 ? (v1 ^ this.trueLow ? 1 : -1) : 0;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof BooleanComparator && this.trueLow == ((BooleanComparator)other).trueLow;
    }

    public int hashCode() {
        return this.getClass().hashCode() * (this.trueLow ? -1 : 1);
    }

    public String toString() {
        return "BooleanComparator: " + (this.trueLow ? "true low" : "true high");
    }
}

