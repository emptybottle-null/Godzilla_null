/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public abstract class DecoratingClassLoader
extends ClassLoader {
    private final Set<String> excludedPackages = Collections.newSetFromMap(new ConcurrentHashMap(8));
    private final Set<String> excludedClasses = Collections.newSetFromMap(new ConcurrentHashMap(8));

    public DecoratingClassLoader() {
    }

    public DecoratingClassLoader(@Nullable ClassLoader parent) {
        super(parent);
    }

    public void excludePackage(String packageName) {
        Assert.notNull((Object)packageName, "Package name must not be null");
        this.excludedPackages.add(packageName);
    }

    public void excludeClass(String className) {
        Assert.notNull((Object)className, "Class name must not be null");
        this.excludedClasses.add(className);
    }

    protected boolean isExcluded(String className) {
        if (this.excludedClasses.contains(className)) {
            return true;
        }
        for (String packageName : this.excludedPackages) {
            if (!className.startsWith(packageName)) continue;
            return true;
        }
        return false;
    }

    static {
        ClassLoader.registerAsParallelCapable();
    }
}

