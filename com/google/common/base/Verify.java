/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Strings;
import com.google.common.base.VerifyException;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Verify {
    public static void verify(boolean expression) {
        if (!expression) {
            throw new VerifyException();
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object @Nullable ... errorMessageArgs) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, char p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1)));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, int p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, long p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, char p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, int p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, long p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1, char p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, Character.valueOf(p2)));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, char p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, int p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, long p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1, int p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, char p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, int p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, long p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1, long p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, char p1, @Nullable Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, Character.valueOf(p1), p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, int p1, @Nullable Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, long p1, @Nullable Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3));
        }
    }

    public static void verify(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object p1, @Nullable Object p2, @Nullable Object p3, @Nullable Object p4) {
        if (!expression) {
            throw new VerifyException(Strings.lenientFormat(errorMessageTemplate, p1, p2, p3, p4));
        }
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(@Nullable T reference) {
        return Verify.verifyNotNull(reference, "expected a non-null reference", new Object[0]);
    }

    @CanIgnoreReturnValue
    public static <T> T verifyNotNull(@Nullable T reference, @Nullable String errorMessageTemplate, @Nullable Object @Nullable ... errorMessageArgs) {
        Verify.verify(reference != null, errorMessageTemplate, errorMessageArgs);
        return reference;
    }

    private Verify() {
    }
}

