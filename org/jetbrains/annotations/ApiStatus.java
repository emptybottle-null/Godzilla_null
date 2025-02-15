/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.jetbrains.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class ApiStatus {

    @Documented
    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.TYPE, ElementType.METHOD})
    public static @interface OverrideOnly {
    }

    @Documented
    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.TYPE, ElementType.METHOD})
    public static @interface NonExtendable {
    }

    @Documented
    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    public static @interface AvailableSince {
        public String value();
    }

    @Documented
    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    public static @interface ScheduledForRemoval {
        public String inVersion() default "";
    }

    @Documented
    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    public static @interface Internal {
    }

    @Documented
    @Retention(value=RetentionPolicy.CLASS)
    @Target(value={ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
    public static @interface Experimental {
    }
}

