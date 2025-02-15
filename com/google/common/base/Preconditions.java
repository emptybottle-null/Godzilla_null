/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, Object @Nullable ... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void checkArgument(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (!b) {
            throw new IllegalArgumentException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkState(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object @Nullable ... errorMessageArgs) {
        if (!expression) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void checkState(boolean b, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (!b) {
            throw new IllegalStateException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T reference, @Nullable String errorMessageTemplate, Object @Nullable ... errorMessageArgs) {
        if (reference == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
        return reference;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static <T> T checkNotNull(T obj, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (obj == null) {
            throw new NullPointerException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int index, int size) {
        return Preconditions.checkElementIndex(index, size, "index");
    }

    @CanIgnoreReturnValue
    public static int checkElementIndex(int index, int size, @Nullable String desc) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Preconditions.badElementIndex(index, size, desc));
        }
        return index;
    }

    private static String badElementIndex(int index, int size, @Nullable String desc) {
        if (index < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", desc, index);
        }
        if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        }
        return Strings.lenientFormat("%s (%s) must be less than size (%s)", desc, index, size);
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int index, int size) {
        return Preconditions.checkPositionIndex(index, size, "index");
    }

    @CanIgnoreReturnValue
    public static int checkPositionIndex(int index, int size, @Nullable String desc) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(index, size, desc));
        }
        return index;
    }

    private static String badPositionIndex(int index, int size, @Nullable String desc) {
        if (index < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", desc, index);
        }
        if (size < 0) {
            throw new IllegalArgumentException("negative size: " + size);
        }
        return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", desc, index, size);
    }

    public static void checkPositionIndexes(int start, int end, int size) {
        if (start < 0 || end < start || end > size) {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndexes(start, end, size));
        }
    }

    private static String badPositionIndexes(int start, int end, int size) {
        if (start < 0 || start > size) {
            return Preconditions.badPositionIndex(start, size, "start index");
        }
        if (end < 0 || end > size) {
            return Preconditions.badPositionIndex(end, size, "end index");
        }
        return Strings.lenientFormat("end index (%s) must not be less than start index (%s)", end, start);
    }
}

