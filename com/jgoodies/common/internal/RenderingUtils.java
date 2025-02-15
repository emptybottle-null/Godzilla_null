/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.internal;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.PrintGraphics;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.print.PrinterGraphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicGraphicsUtils;

public final class RenderingUtils {
    private static final String PROP_DESKTOPHINTS = "awt.font.desktophints";
    private static final String SWING_UTILITIES2_NAME = "sun.swing.SwingUtilities2";
    private static Method drawStringMethod = null;
    private static Method drawStringUnderlineCharAtMethod = null;
    private static Method getFontMetricsMethod = null;

    private RenderingUtils() {
    }

    public static void drawString(JComponent c, Graphics g, String text, int x, int y) {
        if (drawStringMethod != null) {
            try {
                drawStringMethod.invoke(null, c, g, text, x, y);
                return;
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
                // empty catch block
            }
        }
        Graphics2D g2 = (Graphics2D)g;
        Map oldRenderingHints = RenderingUtils.installDesktopHints(g2);
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, -1, x, y);
        if (oldRenderingHints != null) {
            g2.addRenderingHints(oldRenderingHints);
        }
    }

    public static void drawStringUnderlineCharAt(JComponent c, Graphics g, String text, int underlinedIndex, int x, int y) {
        if (drawStringUnderlineCharAtMethod != null) {
            try {
                drawStringUnderlineCharAtMethod.invoke(null, c, g, text, new Integer(underlinedIndex), new Integer(x), new Integer(y));
                return;
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
                // empty catch block
            }
        }
        Graphics2D g2 = (Graphics2D)g;
        Map oldRenderingHints = RenderingUtils.installDesktopHints(g2);
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, underlinedIndex, x, y);
        if (oldRenderingHints != null) {
            g2.addRenderingHints(oldRenderingHints);
        }
    }

    public static FontMetrics getFontMetrics(JComponent c, Graphics g) {
        if (getFontMetricsMethod != null) {
            try {
                return (FontMetrics)getFontMetricsMethod.invoke(null, c, g);
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException invocationTargetException) {
                // empty catch block
            }
        }
        return c.getFontMetrics(g.getFont());
    }

    private static Method getMethodDrawString() {
        try {
            Class<?> clazz = Class.forName(SWING_UTILITIES2_NAME);
            return clazz.getMethod("drawString", JComponent.class, Graphics.class, String.class, Integer.TYPE, Integer.TYPE);
        } catch (ClassNotFoundException e) {
        } catch (SecurityException e) {
        } catch (NoSuchMethodException noSuchMethodException) {
            // empty catch block
        }
        return null;
    }

    private static Method getMethodDrawStringUnderlineCharAt() {
        try {
            Class<?> clazz = Class.forName(SWING_UTILITIES2_NAME);
            return clazz.getMethod("drawStringUnderlineCharAt", JComponent.class, Graphics.class, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
        } catch (ClassNotFoundException e) {
        } catch (SecurityException e) {
        } catch (NoSuchMethodException noSuchMethodException) {
            // empty catch block
        }
        return null;
    }

    private static Method getMethodGetFontMetrics() {
        try {
            Class<?> clazz = Class.forName(SWING_UTILITIES2_NAME);
            return clazz.getMethod("getFontMetrics", JComponent.class, Graphics.class);
        } catch (ClassNotFoundException e) {
        } catch (SecurityException e) {
        } catch (NoSuchMethodException noSuchMethodException) {
            // empty catch block
        }
        return null;
    }

    private static Map installDesktopHints(Graphics2D g2) {
        HashMap<RenderingHints.Key, Object> oldRenderingHints = null;
        Map desktopHints = RenderingUtils.desktopHints(g2);
        if (desktopHints != null && !desktopHints.isEmpty()) {
            oldRenderingHints = new HashMap<RenderingHints.Key, Object>(desktopHints.size());
            for (RenderingHints.Key key : desktopHints.keySet()) {
                oldRenderingHints.put(key, g2.getRenderingHint(key));
            }
            g2.addRenderingHints(desktopHints);
        }
        return oldRenderingHints;
    }

    private static Map desktopHints(Graphics2D g2) {
        Object aaHint;
        if (RenderingUtils.isPrinting(g2)) {
            return null;
        }
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        GraphicsDevice device = g2.getDeviceConfiguration().getDevice();
        Map desktopHints = (Map)toolkit.getDesktopProperty("awt.font.desktophints." + device.getIDstring());
        if (desktopHints == null) {
            desktopHints = (Map)toolkit.getDesktopProperty(PROP_DESKTOPHINTS);
        }
        if (desktopHints != null && ((aaHint = desktopHints.get(RenderingHints.KEY_TEXT_ANTIALIASING)) == RenderingHints.VALUE_TEXT_ANTIALIAS_OFF || aaHint == RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)) {
            desktopHints = null;
        }
        return desktopHints;
    }

    private static boolean isPrinting(Graphics g) {
        return g instanceof PrintGraphics || g instanceof PrinterGraphics;
    }

    static {
        drawStringMethod = RenderingUtils.getMethodDrawString();
        drawStringUnderlineCharAtMethod = RenderingUtils.getMethodDrawStringUnderlineCharAt();
        getFontMetricsMethod = RenderingUtils.getMethodGetFontMetrics();
    }
}

