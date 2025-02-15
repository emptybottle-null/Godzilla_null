/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.jetbrains.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.intellij.lang.annotations.Language;

public final class Debug {

    @Target(value={ElementType.TYPE})
    @Retention(value=RetentionPolicy.CLASS)
    public static @interface Renderer {
        @Language(value="JAVA", prefix="class Renderer{String $text(){return ", suffix=";}}")
        public String text() default "";

        @Language(value="JAVA", prefix="class Renderer{Object[] $childrenArray(){return ", suffix=";}}")
        public String childrenArray() default "";

        @Language(value="JAVA", prefix="class Renderer{boolean $hasChildren(){return ", suffix=";}}")
        public String hasChildren() default "";
    }
}

