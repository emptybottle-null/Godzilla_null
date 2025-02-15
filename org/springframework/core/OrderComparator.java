/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

public class OrderComparator
implements Comparator<Object> {
    public static final OrderComparator INSTANCE = new OrderComparator();

    public Comparator<Object> withSourceProvider(OrderSourceProvider sourceProvider) {
        return (o1, o2) -> this.doCompare(o1, o2, sourceProvider);
    }

    @Override
    public int compare(@Nullable Object o1, @Nullable Object o2) {
        return this.doCompare(o1, o2, null);
    }

    private int doCompare(@Nullable Object o1, @Nullable Object o2, @Nullable OrderSourceProvider sourceProvider) {
        boolean p1 = o1 instanceof PriorityOrdered;
        boolean p2 = o2 instanceof PriorityOrdered;
        if (p1 && !p2) {
            return -1;
        }
        if (p2 && !p1) {
            return 1;
        }
        int i1 = this.getOrder(o1, sourceProvider);
        int i2 = this.getOrder(o2, sourceProvider);
        return Integer.compare(i1, i2);
    }

    private int getOrder(@Nullable Object obj, @Nullable OrderSourceProvider sourceProvider) {
        Object orderSource;
        Integer order = null;
        if (obj != null && sourceProvider != null && (orderSource = sourceProvider.getOrderSource(obj)) != null) {
            if (orderSource.getClass().isArray()) {
                Object source;
                Object[] objectArray = ObjectUtils.toObjectArray(orderSource);
                int n = objectArray.length;
                for (int i = 0; i < n && (order = this.findOrder(source = objectArray[i])) == null; ++i) {
                }
            } else {
                order = this.findOrder(orderSource);
            }
        }
        return order != null ? order.intValue() : this.getOrder(obj);
    }

    protected int getOrder(@Nullable Object obj) {
        Integer order;
        if (obj != null && (order = this.findOrder(obj)) != null) {
            return order;
        }
        return Integer.MAX_VALUE;
    }

    @Nullable
    protected Integer findOrder(Object obj) {
        return obj instanceof Ordered ? Integer.valueOf(((Ordered)obj).getOrder()) : null;
    }

    @Nullable
    public Integer getPriority(Object obj) {
        return null;
    }

    public static void sort(List<?> list) {
        if (list.size() > 1) {
            list.sort(INSTANCE);
        }
    }

    public static void sort(Object[] array) {
        if (array.length > 1) {
            Arrays.sort(array, INSTANCE);
        }
    }

    public static void sortIfNecessary(Object value) {
        if (value instanceof Object[]) {
            OrderComparator.sort((Object[])value);
        } else if (value instanceof List) {
            OrderComparator.sort((List)value);
        }
    }

    @FunctionalInterface
    public static interface OrderSourceProvider {
        @Nullable
        public Object getOrderSource(Object var1);
    }
}

