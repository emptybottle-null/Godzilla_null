/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.objenesis.strategy;

import java.lang.reflect.Field;
import org.springframework.objenesis.ObjenesisException;

public final class PlatformDescription {
    public static final String GNU = "GNU libgcj";
    public static final String HOTSPOT = "Java HotSpot";
    @Deprecated
    public static final String SUN = "Java HotSpot";
    public static final String OPENJDK = "OpenJDK";
    public static final String PERC = "PERC";
    public static final String DALVIK = "Dalvik";
    public static final String SPECIFICATION_VERSION = System.getProperty("java.specification.version");
    public static final String VM_VERSION = System.getProperty("java.runtime.version");
    public static final String VM_INFO = System.getProperty("java.vm.info");
    public static final String VENDOR_VERSION = System.getProperty("java.vm.version");
    public static final String VENDOR = System.getProperty("java.vm.vendor");
    public static final String JVM_NAME = System.getProperty("java.vm.name");
    public static final int ANDROID_VERSION = PlatformDescription.getAndroidVersion();
    public static final boolean IS_ANDROID_OPENJDK = PlatformDescription.getIsAndroidOpenJDK();
    public static final String GAE_VERSION = PlatformDescription.getGaeRuntimeVersion();

    public static String describePlatform() {
        String desc = "Java " + SPECIFICATION_VERSION + " (VM vendor name=\"" + VENDOR + "\", VM vendor version=" + VENDOR_VERSION + ", JVM name=\"" + JVM_NAME + "\", JVM version=" + VM_VERSION + ", JVM info=" + VM_INFO;
        if (ANDROID_VERSION != 0) {
            desc = desc + ", API level=" + ANDROID_VERSION;
        }
        desc = desc + ")";
        return desc;
    }

    public static boolean isThisJVM(String name) {
        return JVM_NAME.startsWith(name);
    }

    public static boolean isAndroidOpenJDK() {
        return IS_ANDROID_OPENJDK;
    }

    private static boolean getIsAndroidOpenJDK() {
        if (PlatformDescription.getAndroidVersion() == 0) {
            return false;
        }
        String bootClasspath = System.getProperty("java.boot.class.path");
        return bootClasspath != null && bootClasspath.toLowerCase().contains("core-oj.jar");
    }

    public static boolean isAfterJigsaw() {
        String version = SPECIFICATION_VERSION;
        return version.indexOf(46) < 0;
    }

    public static boolean isAfterJava11() {
        if (!PlatformDescription.isAfterJigsaw()) {
            return false;
        }
        int version = Integer.parseInt(SPECIFICATION_VERSION);
        return version >= 11;
    }

    public static boolean isGoogleAppEngine() {
        return GAE_VERSION != null;
    }

    private static String getGaeRuntimeVersion() {
        return System.getProperty("com.google.appengine.runtime.version");
    }

    private static int getAndroidVersion() {
        if (!PlatformDescription.isThisJVM(DALVIK)) {
            return 0;
        }
        return PlatformDescription.getAndroidVersion0();
    }

    private static int getAndroidVersion0() {
        int version;
        Field field;
        Class<?> clazz;
        try {
            clazz = Class.forName("android.os.Build$VERSION");
        } catch (ClassNotFoundException e) {
            throw new ObjenesisException(e);
        }
        try {
            field = clazz.getField("SDK_INT");
        } catch (NoSuchFieldException e) {
            return PlatformDescription.getOldAndroidVersion(clazz);
        }
        try {
            version = (Integer)field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return version;
    }

    private static int getOldAndroidVersion(Class<?> versionClass) {
        String version;
        Field field;
        try {
            field = versionClass.getField("SDK");
        } catch (NoSuchFieldException e) {
            throw new ObjenesisException(e);
        }
        try {
            version = (String)field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Integer.parseInt(version);
    }

    private PlatformDescription() {
    }
}

