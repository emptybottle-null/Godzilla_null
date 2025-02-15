/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import org.springframework.core.annotation.PackagesAnnotationFilter;

@FunctionalInterface
public interface AnnotationFilter {
    public static final AnnotationFilter PLAIN = AnnotationFilter.packages("java.lang", "org.springframework.lang");
    public static final AnnotationFilter JAVA = AnnotationFilter.packages("java", "javax");
    public static final AnnotationFilter ALL = new AnnotationFilter(){

        @Override
        public boolean matches(Annotation annotation) {
            return true;
        }

        @Override
        public boolean matches(Class<?> type) {
            return true;
        }

        @Override
        public boolean matches(String typeName) {
            return true;
        }

        public String toString() {
            return "All annotations filtered";
        }
    };
    @Deprecated
    public static final AnnotationFilter NONE = new AnnotationFilter(){

        @Override
        public boolean matches(Annotation annotation) {
            return false;
        }

        @Override
        public boolean matches(Class<?> type) {
            return false;
        }

        @Override
        public boolean matches(String typeName) {
            return false;
        }

        public String toString() {
            return "No annotation filtering";
        }
    };

    default public boolean matches(Annotation annotation) {
        return this.matches(annotation.annotationType());
    }

    default public boolean matches(Class<?> type) {
        return this.matches(type.getName());
    }

    public boolean matches(String var1);

    public static AnnotationFilter packages(String ... packages) {
        return new PackagesAnnotationFilter(packages);
    }
}

