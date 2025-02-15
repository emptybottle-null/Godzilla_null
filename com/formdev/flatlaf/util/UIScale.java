/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatSystemProperties;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.UIResource;

public class UIScale {
    private static final boolean DEBUG = false;
    private static PropertyChangeSupport changeSupport;
    private static Boolean jreHiDPI;
    private static float scaleFactor;
    private static boolean initialized;

    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport == null) {
            changeSupport = new PropertyChangeSupport(UIScale.class);
        }
        changeSupport.addPropertyChangeListener(listener);
    }

    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport == null) {
            return;
        }
        changeSupport.removePropertyChangeListener(listener);
    }

    public static boolean isSystemScalingEnabled() {
        if (jreHiDPI != null) {
            return jreHiDPI;
        }
        jreHiDPI = false;
        if (SystemInfo.isJava_9_orLater) {
            jreHiDPI = true;
        } else if (SystemInfo.isJetBrainsJVM) {
            try {
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                Class<?> sunGeClass = Class.forName("sun.java2d.SunGraphicsEnvironment");
                if (sunGeClass.isInstance(ge)) {
                    Method m = sunGeClass.getDeclaredMethod("isUIScaleOn", new Class[0]);
                    jreHiDPI = (Boolean)m.invoke(ge, new Object[0]);
                }
            } catch (Throwable throwable) {
                // empty catch block
            }
        }
        return jreHiDPI;
    }

    public static double getSystemScaleFactor(Graphics2D g) {
        return UIScale.isSystemScalingEnabled() ? UIScale.getSystemScaleFactor(g.getDeviceConfiguration()) : 1.0;
    }

    public static double getSystemScaleFactor(GraphicsConfiguration gc) {
        return UIScale.isSystemScalingEnabled() && gc != null ? gc.getDefaultTransform().getScaleX() : 1.0;
    }

    private static void initialize() {
        if (initialized) {
            return;
        }
        initialized = true;
        if (!UIScale.isUserScalingEnabled()) {
            return;
        }
        PropertyChangeListener listener = new PropertyChangeListener(){

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                switch (e.getPropertyName()) {
                    case "lookAndFeel": {
                        if (e.getNewValue() instanceof LookAndFeel) {
                            UIManager.getLookAndFeelDefaults().addPropertyChangeListener(this);
                        }
                        UIScale.updateScaleFactor();
                        break;
                    }
                    case "defaultFont": 
                    case "Label.font": {
                        UIScale.updateScaleFactor();
                    }
                }
            }
        };
        UIManager.addPropertyChangeListener(listener);
        UIManager.getDefaults().addPropertyChangeListener(listener);
        UIManager.getLookAndFeelDefaults().addPropertyChangeListener(listener);
        UIScale.updateScaleFactor();
    }

    private static void updateScaleFactor() {
        Font winFont;
        if (!UIScale.isUserScalingEnabled()) {
            return;
        }
        float customScaleFactor = UIScale.getCustomScaleFactor();
        if (customScaleFactor > 0.0f) {
            UIScale.setUserScaleFactor(customScaleFactor);
            return;
        }
        Font font = UIManager.getFont("defaultFont");
        if (font == null) {
            font = UIManager.getFont("Label.font");
        }
        float newScaleFactor = SystemInfo.isWindows ? (font instanceof UIResource ? (UIScale.isSystemScalingEnabled() ? 1.0f : UIScale.computeScaleFactor((winFont = (Font)Toolkit.getDefaultToolkit().getDesktopProperty("win.defaultGUI.font")) != null ? winFont : font)) : UIScale.computeScaleFactor(font)) : UIScale.computeScaleFactor(font);
        UIScale.setUserScaleFactor(newScaleFactor);
    }

    private static float computeScaleFactor(Font font) {
        float fontSizeDivider = 12.0f;
        if (SystemInfo.isWindows) {
            if ("Tahoma".equals(font.getFamily())) {
                fontSizeDivider = 11.0f;
            }
        } else if (SystemInfo.isMacOS) {
            fontSizeDivider = 13.0f;
        } else if (SystemInfo.isLinux) {
            fontSizeDivider = SystemInfo.isKDE ? 13.0f : 15.0f;
        }
        return (float)font.getSize() / fontSizeDivider;
    }

    private static boolean isUserScalingEnabled() {
        return FlatSystemProperties.getBoolean("flatlaf.uiScale.enabled", true);
    }

    public static FontUIResource applyCustomScaleFactor(FontUIResource font) {
        if (!UIScale.isUserScalingEnabled()) {
            return font;
        }
        float scaleFactor = UIScale.getCustomScaleFactor();
        if (scaleFactor <= 0.0f) {
            return font;
        }
        float fontScaleFactor = UIScale.computeScaleFactor(font);
        if (scaleFactor == fontScaleFactor) {
            return font;
        }
        int newFontSize = Math.round((float)font.getSize() / fontScaleFactor * scaleFactor);
        return new FontUIResource(font.deriveFont((float)newFontSize));
    }

    private static float getCustomScaleFactor() {
        return UIScale.parseScaleFactor(System.getProperty("flatlaf.uiScale"));
    }

    private static float parseScaleFactor(String s) {
        if (s == null) {
            return -1.0f;
        }
        float units = 1.0f;
        if (s.endsWith("x")) {
            s = s.substring(0, s.length() - 1);
        } else if (s.endsWith("dpi")) {
            units = 96.0f;
            s = s.substring(0, s.length() - 3);
        } else if (s.endsWith("%")) {
            units = 100.0f;
            s = s.substring(0, s.length() - 1);
        }
        try {
            float scale = Float.parseFloat(s);
            return scale > 0.0f ? scale / units : -1.0f;
        } catch (NumberFormatException ex) {
            return -1.0f;
        }
    }

    public static float getUserScaleFactor() {
        UIScale.initialize();
        return scaleFactor;
    }

    private static void setUserScaleFactor(float scaleFactor) {
        scaleFactor = scaleFactor <= 1.0f ? 1.0f : (float)Math.round(scaleFactor * 4.0f) / 4.0f;
        float oldScaleFactor = UIScale.scaleFactor;
        UIScale.scaleFactor = scaleFactor;
        if (changeSupport != null) {
            changeSupport.firePropertyChange("userScaleFactor", Float.valueOf(oldScaleFactor), Float.valueOf(scaleFactor));
        }
    }

    public static float scale(float value) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? value : value * scaleFactor;
    }

    public static int scale(int value) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? value : Math.round((float)value * scaleFactor);
    }

    public static int scale2(int value) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? value : (int)((float)value * scaleFactor);
    }

    public static float unscale(float value) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? value : value / scaleFactor;
    }

    public static int unscale(int value) {
        UIScale.initialize();
        return scaleFactor == 1.0f ? value : Math.round((float)value / scaleFactor);
    }

    public static void scaleGraphics(Graphics2D g) {
        UIScale.initialize();
        if (scaleFactor != 1.0f) {
            g.scale(scaleFactor, scaleFactor);
        }
    }

    public static Dimension scale(Dimension dimension) {
        UIScale.initialize();
        return dimension == null || scaleFactor == 1.0f ? dimension : (dimension instanceof UIResource ? new DimensionUIResource(UIScale.scale(dimension.width), UIScale.scale(dimension.height)) : new Dimension(UIScale.scale(dimension.width), UIScale.scale(dimension.height)));
    }

    public static Insets scale(Insets insets) {
        UIScale.initialize();
        return insets == null || scaleFactor == 1.0f ? insets : (insets instanceof UIResource ? new InsetsUIResource(UIScale.scale(insets.top), UIScale.scale(insets.left), UIScale.scale(insets.bottom), UIScale.scale(insets.right)) : new Insets(UIScale.scale(insets.top), UIScale.scale(insets.left), UIScale.scale(insets.bottom), UIScale.scale(insets.right)));
    }

    static {
        scaleFactor = 1.0f;
    }
}

