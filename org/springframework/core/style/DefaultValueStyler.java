/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.style;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import org.springframework.core.style.ValueStyler;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

public class DefaultValueStyler
implements ValueStyler {
    private static final String EMPTY = "[[empty]]";
    private static final String NULL = "[null]";
    private static final String COLLECTION = "collection";
    private static final String SET = "set";
    private static final String LIST = "list";
    private static final String MAP = "map";
    private static final String EMPTY_MAP = "map[[empty]]";
    private static final String ARRAY = "array";

    @Override
    public String style(@Nullable Object value) {
        if (value == null) {
            return NULL;
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Class) {
            return ClassUtils.getShortName((Class)value);
        }
        if (value instanceof Method) {
            Method method = (Method)value;
            return method.getName() + "@" + ClassUtils.getShortName(method.getDeclaringClass());
        }
        if (value instanceof Map) {
            return this.style((Map)value);
        }
        if (value instanceof Map.Entry) {
            return this.style((Map.Entry)value);
        }
        if (value instanceof Collection) {
            return this.style((Collection)value);
        }
        if (value.getClass().isArray()) {
            return this.styleArray(ObjectUtils.toObjectArray(value));
        }
        return String.valueOf(value);
    }

    private <K, V> String style(Map<K, V> value) {
        if (value.isEmpty()) {
            return EMPTY_MAP;
        }
        StringJoiner result = new StringJoiner(", ", "[", "]");
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.add(this.style(entry));
        }
        return MAP + result;
    }

    private String style(Map.Entry<?, ?> value) {
        return this.style(value.getKey()) + " -> " + this.style(value.getValue());
    }

    private String style(Collection<?> value) {
        String collectionType = this.getCollectionTypeString(value);
        if (value.isEmpty()) {
            return collectionType + EMPTY;
        }
        StringJoiner result = new StringJoiner(", ", "[", "]");
        for (Object o : value) {
            result.add(this.style(o));
        }
        return collectionType + result;
    }

    private String getCollectionTypeString(Collection<?> value) {
        if (value instanceof List) {
            return LIST;
        }
        if (value instanceof Set) {
            return SET;
        }
        return COLLECTION;
    }

    private String styleArray(Object[] array) {
        if (array.length == 0) {
            return "array<" + ClassUtils.getShortName(array.getClass().getComponentType()) + '>' + EMPTY;
        }
        StringJoiner result = new StringJoiner(", ", "[", "]");
        for (Object o : array) {
            result.add(this.style(o));
        }
        return "array<" + ClassUtils.getShortName(array.getClass().getComponentType()) + '>' + result;
    }
}

