/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.comparator;

import java.util.Comparator;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class InstanceComparator<T>
implements Comparator<T> {
    private final Class<?>[] instanceOrder;

    public InstanceComparator(Class<?> ... instanceOrder) {
        Assert.notNull(instanceOrder, "'instanceOrder' array must not be null");
        this.instanceOrder = instanceOrder;
    }

    @Override
    public int compare(T o1, T o2) {
        int i1 = this.getOrder(o1);
        int i2 = this.getOrder(o2);
        return Integer.compare(i1, i2);
    }

    private int getOrder(@Nullable T object) {
        if (object != null) {
            for (int i = 0; i < this.instanceOrder.length; ++i) {
                if (!this.instanceOrder[i].isInstance(object)) continue;
                return i;
            }
        }
        return this.instanceOrder.length;
    }
}

