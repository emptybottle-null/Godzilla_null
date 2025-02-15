/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.reflect.AnnotatedElement;
import java.util.Map;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;

public abstract class OrderUtils {
    private static final Object NOT_ANNOTATED = new Object();
    private static final String JAVAX_PRIORITY_ANNOTATION = "javax.annotation.Priority";
    private static final Map<AnnotatedElement, Object> orderCache = new ConcurrentReferenceHashMap<AnnotatedElement, Object>(64);

    public static int getOrder(Class<?> type, int defaultOrder) {
        Integer order = OrderUtils.getOrder(type);
        return order != null ? order : defaultOrder;
    }

    @Nullable
    public static Integer getOrder(Class<?> type, @Nullable Integer defaultOrder) {
        Integer order = OrderUtils.getOrder(type);
        return order != null ? order : defaultOrder;
    }

    @Nullable
    public static Integer getOrder(Class<?> type) {
        return OrderUtils.getOrder(type);
    }

    @Nullable
    public static Integer getOrder(AnnotatedElement element) {
        return OrderUtils.getOrderFromAnnotations(element, MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY));
    }

    @Nullable
    static Integer getOrderFromAnnotations(AnnotatedElement element, MergedAnnotations annotations) {
        if (!(element instanceof Class)) {
            return OrderUtils.findOrder(annotations);
        }
        Object cached = orderCache.get(element);
        if (cached != null) {
            return cached instanceof Integer ? (Integer)cached : null;
        }
        Integer result = OrderUtils.findOrder(annotations);
        orderCache.put(element, result != null ? result : NOT_ANNOTATED);
        return result;
    }

    @Nullable
    private static Integer findOrder(MergedAnnotations annotations) {
        MergedAnnotation<Order> orderAnnotation = annotations.get(Order.class);
        if (orderAnnotation.isPresent()) {
            return orderAnnotation.getInt("value");
        }
        MergedAnnotation priorityAnnotation = annotations.get(JAVAX_PRIORITY_ANNOTATION);
        if (priorityAnnotation.isPresent()) {
            return priorityAnnotation.getInt("value");
        }
        return null;
    }

    @Nullable
    public static Integer getPriority(Class<?> type) {
        return MergedAnnotations.from(type, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY).get(JAVAX_PRIORITY_ANNOTATION).getValue("value", Integer.class).orElse(null);
    }
}

