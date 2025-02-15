/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public abstract class Assert {
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void state(boolean expression) {
        Assert.state(expression, "[Assertion failed] - this state invariant must be true");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void isTrue(boolean expression) {
        Assert.isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isNull(@Nullable Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(@Nullable Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void isNull(@Nullable Object object) {
        Assert.isNull(object, "[Assertion failed] - the object argument must be null");
    }

    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(@Nullable Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void notNull(@Nullable Object object) {
        Assert.notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void hasLength(@Nullable String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(@Nullable String text, Supplier<String> messageSupplier) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void hasLength(@Nullable String text) {
        Assert.hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void hasText(@Nullable String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasText(@Nullable String text, Supplier<String> messageSupplier) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void hasText(@Nullable String text) {
        Assert.hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static void doesNotContain(@Nullable String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void doesNotContain(@Nullable String textToSearch, String substring, Supplier<String> messageSupplier) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void doesNotContain(@Nullable String textToSearch, String substring) {
        Assert.doesNotContain(textToSearch, substring, () -> "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
    }

    public static void notEmpty(@Nullable Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(@Nullable Object[] array, Supplier<String> messageSupplier) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void notEmpty(@Nullable Object[] array) {
        Assert.notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static void noNullElements(@Nullable Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element != null) continue;
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void noNullElements(@Nullable Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            for (Object element : array) {
                if (element != null) continue;
                throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
            }
        }
    }

    @Deprecated
    public static void noNullElements(@Nullable Object[] array) {
        Assert.noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void notEmpty(@Nullable Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void notEmpty(@Nullable Collection<?> collection) {
        Assert.notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void noNullElements(@Nullable Collection<?> collection, String message) {
        if (collection != null) {
            for (Object element : collection) {
                if (element != null) continue;
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static void noNullElements(@Nullable Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            for (Object element : collection) {
                if (element != null) continue;
                throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
            }
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(Assert.nullSafeGet(messageSupplier));
        }
    }

    @Deprecated
    public static void notEmpty(@Nullable Map<?, ?> map) {
        Assert.notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void isInstanceOf(Class<?> type, @Nullable Object obj, String message) {
        Assert.notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            Assert.instanceCheckFailed(type, obj, message);
        }
    }

    public static void isInstanceOf(Class<?> type, @Nullable Object obj, Supplier<String> messageSupplier) {
        Assert.notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            Assert.instanceCheckFailed(type, obj, Assert.nullSafeGet(messageSupplier));
        }
    }

    public static void isInstanceOf(Class<?> type, @Nullable Object obj) {
        Assert.isInstanceOf(type, obj, "");
    }

    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, String message) {
        Assert.notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            Assert.assignableCheckFailed(superType, subType, message);
        }
    }

    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, Supplier<String> messageSupplier) {
        Assert.notNull(superType, "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            Assert.assignableCheckFailed(superType, subType, Assert.nullSafeGet(messageSupplier));
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        Assert.isAssignable(superType, subType, "");
    }

    private static void instanceCheckFailed(Class<?> type, @Nullable Object obj, @Nullable String msg) {
        String className = obj != null ? obj.getClass().getName() : "null";
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.hasLength(msg)) {
            if (Assert.endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = Assert.messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + "Object of class [" + className + "] must be an instance of " + type;
        }
        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, @Nullable Class<?> subType, @Nullable String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.hasLength(msg)) {
            if (Assert.endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = Assert.messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }
        if (defaultMessage) {
            result = result + subType + " is not assignable to " + superType;
        }
        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, @Nullable Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }

    @Nullable
    private static String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
        return messageSupplier != null ? messageSupplier.get() : null;
    }
}

