/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.DecoratingProxy;
import org.springframework.core.OrderComparator;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.lang.Nullable;

public class AnnotationAwareOrderComparator
extends OrderComparator {
    public static final AnnotationAwareOrderComparator INSTANCE = new AnnotationAwareOrderComparator();

    @Override
    @Nullable
    protected Integer findOrder(Object obj) {
        Integer order = super.findOrder(obj);
        if (order != null) {
            return order;
        }
        return this.findOrderFromAnnotation(obj);
    }

    @Nullable
    private Integer findOrderFromAnnotation(Object obj) {
        MergedAnnotations annotations;
        Class<?> element = obj instanceof AnnotatedElement ? (Class<?>)obj : obj.getClass();
        Integer order = OrderUtils.getOrderFromAnnotations(element, annotations = MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY));
        if (order == null && obj instanceof DecoratingProxy) {
            return this.findOrderFromAnnotation(((DecoratingProxy)obj).getDecoratedClass());
        }
        return order;
    }

    @Override
    @Nullable
    public Integer getPriority(Object obj) {
        if (obj instanceof Class) {
            return OrderUtils.getPriority((Class)obj);
        }
        Integer priority = OrderUtils.getPriority(obj.getClass());
        if (priority == null && obj instanceof DecoratingProxy) {
            return this.getPriority(((DecoratingProxy)obj).getDecoratedClass());
        }
        return priority;
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
            AnnotationAwareOrderComparator.sort((Object[])value);
        } else if (value instanceof List) {
            AnnotationAwareOrderComparator.sort((List)value);
        }
    }
}

