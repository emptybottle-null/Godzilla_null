/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

public interface FlatSystemProperties {
    public static final String UI_SCALE = "flatlaf.uiScale";
    public static final String UI_SCALE_ENABLED = "flatlaf.uiScale.enabled";
    public static final String USE_UBUNTU_FONT = "flatlaf.useUbuntuFont";
    public static final String USE_WINDOW_DECORATIONS = "flatlaf.useWindowDecorations";
    public static final String USE_JETBRAINS_CUSTOM_DECORATIONS = "flatlaf.useJetBrainsCustomDecorations";
    public static final String MENUBAR_EMBEDDED = "flatlaf.menuBarEmbedded";
    public static final String ANIMATION = "flatlaf.animation";
    public static final String USE_TEXT_Y_CORRECTION = "flatlaf.useTextYCorrection";

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = System.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public static Boolean getBooleanStrict(String key, Boolean defaultValue) {
        String value = System.getProperty(key);
        if ("true".equalsIgnoreCase(value)) {
            return Boolean.TRUE;
        }
        if ("false".equalsIgnoreCase(value)) {
            return Boolean.FALSE;
        }
        return defaultValue;
    }
}

