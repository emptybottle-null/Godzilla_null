/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import java.util.Locale;
import java.util.StringTokenizer;

public class SystemInfo {
    public static final boolean isWindows;
    public static final boolean isMacOS;
    public static final boolean isLinux;
    public static final long osVersion;
    public static final boolean isWindows_10_orLater;
    public static final boolean isMacOS_10_11_ElCapitan_orLater;
    public static final boolean isMacOS_10_14_Mojave_orLater;
    public static final boolean isMacOS_10_15_Catalina_orLater;
    public static final long javaVersion;
    public static final boolean isJava_9_orLater;
    public static final boolean isJava_11_orLater;
    public static final boolean isJava_15_orLater;
    public static final boolean isJetBrainsJVM;
    public static final boolean isJetBrainsJVM_11_orLater;
    public static final boolean isKDE;

    public static long scanVersion(String version) {
        int major = 1;
        int minor = 0;
        int micro = 0;
        int patch = 0;
        try {
            StringTokenizer st = new StringTokenizer(version, "._-+");
            major = Integer.parseInt(st.nextToken());
            minor = Integer.parseInt(st.nextToken());
            micro = Integer.parseInt(st.nextToken());
            patch = Integer.parseInt(st.nextToken());
        } catch (Exception exception) {
            // empty catch block
        }
        return SystemInfo.toVersion(major, minor, micro, patch);
    }

    public static long toVersion(int major, int minor, int micro, int patch) {
        return ((long)major << 48) + ((long)minor << 32) + ((long)micro << 16) + (long)patch;
    }

    static {
        String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        isWindows = osName.startsWith("windows");
        isMacOS = osName.startsWith("mac");
        isLinux = osName.startsWith("linux");
        osVersion = SystemInfo.scanVersion(System.getProperty("os.version"));
        isWindows_10_orLater = isWindows && osVersion >= SystemInfo.toVersion(10, 0, 0, 0);
        isMacOS_10_11_ElCapitan_orLater = isMacOS && osVersion >= SystemInfo.toVersion(10, 11, 0, 0);
        isMacOS_10_14_Mojave_orLater = isMacOS && osVersion >= SystemInfo.toVersion(10, 14, 0, 0);
        isMacOS_10_15_Catalina_orLater = isMacOS && osVersion >= SystemInfo.toVersion(10, 15, 0, 0);
        javaVersion = SystemInfo.scanVersion(System.getProperty("java.version"));
        isJava_9_orLater = javaVersion >= SystemInfo.toVersion(9, 0, 0, 0);
        isJava_11_orLater = javaVersion >= SystemInfo.toVersion(11, 0, 0, 0);
        isJava_15_orLater = javaVersion >= SystemInfo.toVersion(15, 0, 0, 0);
        isJetBrainsJVM = System.getProperty("java.vm.vendor", "Unknown").toLowerCase(Locale.ENGLISH).contains("jetbrains");
        isJetBrainsJVM_11_orLater = isJetBrainsJVM && isJava_11_orLater;
        isKDE = isLinux && System.getenv("KDE_FULL_SESSION") != null;
    }
}

