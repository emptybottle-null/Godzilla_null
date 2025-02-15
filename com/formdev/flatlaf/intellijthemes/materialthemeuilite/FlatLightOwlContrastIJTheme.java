/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatLightOwlContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Light Owl Contrast (Material)";

    public static boolean install() {
        try {
            return FlatLightOwlContrastIJTheme.install(new FlatLightOwlContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatLightOwlContrastIJTheme.installLafInfo(NAME, FlatLightOwlContrastIJTheme.class);
    }

    public FlatLightOwlContrastIJTheme() {
        super(Utils.loadTheme("Light Owl Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

