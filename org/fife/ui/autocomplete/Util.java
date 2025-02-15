/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.net.URI;
import java.security.AccessControlException;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.UIManager;

public final class Util {
    public static final String PROPERTY_DONT_USE_SUBSTANCE_RENDERERS = "org.fife.ui.autocomplete.DontUseSubstanceRenderers";
    public static final String PROPERTY_ALLOW_DECORATED_AUTOCOMPLETE_WINDOWS = "org.fife.ui.autocomplete.allowDecoratedAutoCompleteWindows";
    public static final Color LIGHT_HYPERLINK_FG;
    private static final Pattern TAG_PATTERN;
    private static final boolean USE_SUBSTANCE_RENDERERS;
    private static boolean desktopCreationAttempted;
    private static Object desktop;
    private static final Object LOCK_DESKTOP_CREATION;

    private Util() {
    }

    public static boolean browse(URI uri) {
        Object desktop;
        boolean success = false;
        if (uri != null && (desktop = Util.getDesktop()) != null) {
            try {
                Method m = desktop.getClass().getDeclaredMethod("browse", URI.class);
                m.invoke(desktop, uri);
                success = true;
            } catch (RuntimeException re) {
                throw re;
            } catch (Exception exception) {
                // empty catch block
            }
        }
        return success;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Object getDesktop() {
        Object object = LOCK_DESKTOP_CREATION;
        synchronized (object) {
            if (!desktopCreationAttempted) {
                desktopCreationAttempted = true;
                try {
                    Class<?> desktopClazz = Class.forName("java.awt.Desktop");
                    Method m = desktopClazz.getDeclaredMethod("isDesktopSupported", new Class[0]);
                    boolean supported = (Boolean)m.invoke(null, new Object[0]);
                    if (supported) {
                        m = desktopClazz.getDeclaredMethod("getDesktop", new Class[0]);
                        desktop = m.invoke(null, new Object[0]);
                    }
                } catch (RuntimeException re) {
                    throw re;
                } catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        return desktop;
    }

    public static String getHexString(Color c) {
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("#");
        int r = c.getRed();
        if (r < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(r));
        int g = c.getGreen();
        if (g < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(g));
        int b = c.getBlue();
        if (b < 16) {
            sb.append('0');
        }
        sb.append(Integer.toHexString(b));
        return sb.toString();
    }

    static Color getHyperlinkForeground() {
        Color fg = UIManager.getColor("Label.foreground");
        if (fg == null) {
            fg = new JLabel().getForeground();
        }
        return Util.isLightForeground(fg) ? LIGHT_HYPERLINK_FG : Color.blue;
    }

    public static Rectangle getScreenBoundsForPoint(int x, int y) {
        GraphicsDevice[] devices;
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for (GraphicsDevice device : devices = env.getScreenDevices()) {
            GraphicsConfiguration config = device.getDefaultConfiguration();
            Rectangle gcBounds = config.getBounds();
            if (!gcBounds.contains(x, y)) continue;
            return gcBounds;
        }
        return env.getMaximumWindowBounds();
    }

    public static boolean getShouldAllowDecoratingMainAutoCompleteWindows() {
        try {
            return Boolean.getBoolean(PROPERTY_ALLOW_DECORATED_AUTOCOMPLETE_WINDOWS);
        } catch (AccessControlException ace) {
            return false;
        }
    }

    public static boolean getUseSubstanceRenderers() {
        return USE_SUBSTANCE_RENDERERS;
    }

    public static boolean isLightForeground(Color fg) {
        return fg.getRed() > 160 && fg.getGreen() > 160 && fg.getBlue() > 160;
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        int prefixLength = prefix.length();
        if (str.length() >= prefixLength) {
            return str.regionMatches(true, 0, prefix, 0, prefixLength);
        }
        return false;
    }

    public static String stripHtml(String text) {
        if (text == null || !text.startsWith("<html>")) {
            return text;
        }
        return TAG_PATTERN.matcher(text).replaceAll("");
    }

    static {
        boolean use;
        LIGHT_HYPERLINK_FG = new Color(0xD8FFFF);
        TAG_PATTERN = Pattern.compile("<[^>]*>");
        LOCK_DESKTOP_CREATION = new Object();
        try {
            use = !Boolean.getBoolean(PROPERTY_DONT_USE_SUBSTANCE_RENDERERS);
        } catch (AccessControlException ace) {
            use = true;
        }
        USE_SUBSTANCE_RENDERERS = use;
    }
}

