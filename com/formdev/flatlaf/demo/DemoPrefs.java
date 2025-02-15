/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.demo.intellijthemes.IJThemesPanel;
import com.formdev.flatlaf.util.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.prefs.Preferences;
import javax.swing.UIManager;

public class DemoPrefs {
    public static final String KEY_LAF = "laf";
    public static final String KEY_LAF_THEME = "lafTheme";
    public static final String RESOURCE_PREFIX = "res:";
    public static final String FILE_PREFIX = "file:";
    public static final String THEME_UI_KEY = "__FlatLaf.demo.theme";
    private static Preferences state;

    public static Preferences getState() {
        return state;
    }

    public static void init(String rootPath) {
        state = Preferences.userRoot().node(rootPath);
    }

    public static void initLaf(String[] args) {
        try {
            if (args.length > 0) {
                UIManager.setLookAndFeel(args[0]);
            } else {
                String lafClassName = state.get(KEY_LAF, FlatLightLaf.class.getName());
                if (IntelliJTheme.ThemeLaf.class.getName().equals(lafClassName)) {
                    String theme = state.get(KEY_LAF_THEME, "");
                    if (theme.startsWith(RESOURCE_PREFIX)) {
                        IntelliJTheme.install(IJThemesPanel.class.getResourceAsStream("/com/formdev/flatlaf/intellijthemes/themes/" + theme.substring(RESOURCE_PREFIX.length())));
                    } else if (theme.startsWith(FILE_PREFIX)) {
                        FlatLaf.install(IntelliJTheme.createLaf(new FileInputStream(theme.substring(FILE_PREFIX.length()))));
                    } else {
                        FlatLightLaf.install();
                    }
                    if (!theme.isEmpty()) {
                        UIManager.getLookAndFeelDefaults().put(THEME_UI_KEY, theme);
                    }
                } else if (FlatPropertiesLaf.class.getName().equals(lafClassName)) {
                    String theme = state.get(KEY_LAF_THEME, "");
                    if (theme.startsWith(FILE_PREFIX)) {
                        File themeFile = new File(theme.substring(FILE_PREFIX.length()));
                        String themeName = StringUtils.removeTrailing(themeFile.getName(), ".properties");
                        FlatLaf.install(new FlatPropertiesLaf(themeName, themeFile));
                    } else {
                        FlatLightLaf.install();
                    }
                    if (!theme.isEmpty()) {
                        UIManager.getLookAndFeelDefaults().put(THEME_UI_KEY, theme);
                    }
                } else {
                    UIManager.setLookAndFeel(lafClassName);
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            FlatLightLaf.install();
        }
        UIManager.addPropertyChangeListener(e -> {
            if ("lookAndFeel".equals(e.getPropertyName())) {
                state.put(KEY_LAF, UIManager.getLookAndFeel().getClass().getName());
            }
        });
    }
}

