/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.sqlite.date;

public class ExceptionUtils {
    public static <R> R rethrow(Throwable throwable) {
        return ExceptionUtils.typeErasure(throwable);
    }

    private static <R, T extends Throwable> R typeErasure(Throwable throwable) throws T {
        throw throwable;
    }
}

