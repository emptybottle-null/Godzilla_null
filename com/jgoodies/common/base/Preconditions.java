/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.base;

import com.jgoodies.common.base.Strings;

public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkArgument(boolean expression, String messageFormat, Object ... messageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(Preconditions.format(messageFormat, messageArgs));
        }
    }

    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null) {
            throw new NullPointerException(message);
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, String messageFormat, Object ... messageArgs) {
        if (reference == null) {
            throw new NullPointerException(Preconditions.format(messageFormat, messageArgs));
        }
        return reference;
    }

    public static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void checkState(boolean expression, String messageFormat, Object ... messageArgs) {
        if (!expression) {
            throw new IllegalStateException(Preconditions.format(messageFormat, messageArgs));
        }
    }

    public static String checkNotBlank(String str, String message) {
        Preconditions.checkNotNull(str, message);
        Preconditions.checkArgument(Strings.isNotBlank(str), message);
        return str;
    }

    public static String checkNotBlank(String str, String messageFormat, Object ... messageArgs) {
        Preconditions.checkNotNull(str, messageFormat, messageArgs);
        Preconditions.checkArgument(Strings.isNotBlank(str), messageFormat, messageArgs);
        return str;
    }

    static String format(String messageFormat, Object ... messageArgs) {
        return String.format(messageFormat, messageArgs);
    }
}

