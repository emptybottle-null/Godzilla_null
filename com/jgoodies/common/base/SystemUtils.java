/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.base;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class SystemUtils {
    protected static final String OS_NAME = SystemUtils.getSystemProperty("os.name");
    protected static final String OS_VERSION = SystemUtils.getSystemProperty("os.version");
    protected static final String JAVA_VERSION = SystemUtils.getSystemProperty("java.version");
    public static final boolean IS_OS_LINUX = SystemUtils.startsWith(OS_NAME, "Linux") || SystemUtils.startsWith(OS_NAME, "LINUX");
    public static final boolean IS_OS_MAC = SystemUtils.startsWith(OS_NAME, "Mac OS");
    public static final boolean IS_OS_SOLARIS = SystemUtils.startsWith(OS_NAME, "Solaris");
    public static final boolean IS_OS_WINDOWS = SystemUtils.startsWith(OS_NAME, "Windows");
    public static final boolean IS_OS_WINDOWS_98 = SystemUtils.startsWith(OS_NAME, "Windows 9") && SystemUtils.startsWith(OS_VERSION, "4.1");
    public static final boolean IS_OS_WINDOWS_ME = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "4.9");
    public static final boolean IS_OS_WINDOWS_2000 = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "5.0");
    public static final boolean IS_OS_WINDOWS_XP = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "5.1");
    public static final boolean IS_OS_WINDOWS_XP_64_BIT_OR_SERVER_2003 = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "5.2");
    public static final boolean IS_OS_WINDOWS_VISTA = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "6.0");
    public static final boolean IS_OS_WINDOWS_7 = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "6.1");
    public static final boolean IS_OS_WINDOWS_8 = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "6.2");
    public static final boolean IS_OS_WINDOWS_6_OR_LATER = SystemUtils.startsWith(OS_NAME, "Windows") && SystemUtils.startsWith(OS_VERSION, "6.");
    public static final boolean IS_JAVA_6 = SystemUtils.startsWith(JAVA_VERSION, "1.6");
    public static final boolean IS_JAVA_7 = SystemUtils.startsWith(JAVA_VERSION, "1.7");
    public static final boolean IS_JAVA_7_OR_LATER = !IS_JAVA_6;
    public static final boolean IS_JAVA_8 = SystemUtils.startsWith(JAVA_VERSION, "1.8");
    public static final boolean IS_JAVA_8_OR_LATER = !IS_JAVA_6 && !IS_JAVA_7;
    public static final boolean HAS_MODERN_RASTERIZER = SystemUtils.hasModernRasterizer();
    public static final boolean IS_LAF_WINDOWS_XP_ENABLED = SystemUtils.isWindowsXPLafEnabled();
    public static final boolean IS_LOW_RESOLUTION = SystemUtils.isLowResolution();
    private static final String AWT_UTILITIES_CLASS_NAME = "com.sun.awt.AWTUtilities";

    public static boolean isLafAqua() {
        return UIManager.getLookAndFeel().getID().equals("Aqua");
    }

    protected SystemUtils() {
    }

    protected static String getSystemProperty(String key) {
        try {
            return System.getProperty(key);
        } catch (SecurityException e) {
            Logger.getLogger(SystemUtils.class.getName()).warning("Can't access the System property " + key + ".");
            return "";
        }
    }

    protected static boolean startsWith(String str, String prefix) {
        return str != null && str.startsWith(prefix);
    }

    private static boolean hasModernRasterizer() {
        try {
            Class.forName(AWT_UTILITIES_CLASS_NAME);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean isWindowsXPLafEnabled() {
        return IS_OS_WINDOWS && Boolean.TRUE.equals(Toolkit.getDefaultToolkit().getDesktopProperty("win.xpstyle.themeActive")) && SystemUtils.getSystemProperty("swing.noxp") == null;
    }

    private static boolean isLowResolution() {
        try {
            return Toolkit.getDefaultToolkit().getScreenResolution() < 120;
        } catch (HeadlessException e) {
            return true;
        }
    }
}

