/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

public abstract class NativeDetector {
    private static final boolean imageCode = System.getProperty("org.graalvm.nativeimage.imagecode") != null;

    public static boolean inNativeImage() {
        return imageCode;
    }
}

