/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.jetbrains.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class Async {

    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    public static @interface Execute {
    }

    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    public static @interface Schedule {
    }
}

