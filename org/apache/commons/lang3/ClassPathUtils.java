/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.Validate;

public class ClassPathUtils {
    public static String toFullyQualifiedName(Class<?> context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return ClassPathUtils.toFullyQualifiedName(context.getPackage(), resourceName);
    }

    public static String toFullyQualifiedName(Package context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return context.getName() + "." + resourceName;
    }

    public static String toFullyQualifiedPath(Class<?> context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return ClassPathUtils.toFullyQualifiedPath(context.getPackage(), resourceName);
    }

    public static String toFullyQualifiedPath(Package context, String resourceName) {
        Validate.notNull(context, "context", new Object[0]);
        Validate.notNull(resourceName, "resourceName", new Object[0]);
        return context.getName().replace('.', '/') + "/" + resourceName;
    }
}

