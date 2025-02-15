/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import org.springframework.lang.Nullable;

public final class SpringVersion {
    private SpringVersion() {
    }

    @Nullable
    public static String getVersion() {
        Package pkg = SpringVersion.class.getPackage();
        return pkg != null ? pkg.getImplementationVersion() : null;
    }
}

