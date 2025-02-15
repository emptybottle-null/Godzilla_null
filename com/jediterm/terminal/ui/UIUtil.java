/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.util.Util;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.lang.reflect.Field;
import java.util.Map;

public class UIUtil {
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version").toLowerCase();
    protected static final String _OS_NAME = OS_NAME.toLowerCase();
    public static final boolean isWindows = _OS_NAME.startsWith("windows");
    public static final boolean isOS2 = _OS_NAME.startsWith("os/2") || _OS_NAME.startsWith("os2");
    public static final boolean isMac = _OS_NAME.startsWith("mac");
    public static final boolean isLinux = _OS_NAME.startsWith("linux");
    public static final boolean isUnix = !isWindows && !isOS2;
    private static final boolean IS_ORACLE_JVM = UIUtil.isOracleJvm();
    public static final String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");

    public static boolean isRetina() {
        Float scaleFactor;
        if (UIUtil.isJavaVersionAtLeast("1.7.0_40") && IS_ORACLE_JVM) {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice device = env.getDefaultScreenDevice();
            try {
                Field field = device.getClass().getDeclaredField("scale");
                if (field != null) {
                    field.setAccessible(true);
                    Object scale = field.get(device);
                    if (scale instanceof Integer && (Integer)scale == 2) {
                        return true;
                    }
                }
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return (scaleFactor = (Float)Toolkit.getDefaultToolkit().getDesktopProperty("apple.awt.contentScaleFactor")) != null && scaleFactor.intValue() == 2;
    }

    private static boolean isOracleJvm() {
        String vendor = UIUtil.getJavaVmVendor();
        return vendor != null && Util.containsIgnoreCase(vendor, "Oracle");
    }

    public static String getJavaVmVendor() {
        return System.getProperty("java.vm.vendor");
    }

    public static boolean isJavaVersionAtLeast(String v) {
        return Util.compareVersionNumbers(JAVA_RUNTIME_VERSION, v) >= 0;
    }

    public static void applyRenderingHints(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Toolkit tk = Toolkit.getDefaultToolkit();
        Map map = (Map)tk.getDesktopProperty("awt.font.desktophints");
        if (map != null) {
            g2d.addRenderingHints(map);
        }
    }
}

