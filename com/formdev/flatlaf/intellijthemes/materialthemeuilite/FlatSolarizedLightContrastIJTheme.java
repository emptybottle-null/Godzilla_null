/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedLightContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Light Contrast (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedLightContrastIJTheme.install(new FlatSolarizedLightContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedLightContrastIJTheme.installLafInfo(NAME, FlatSolarizedLightContrastIJTheme.class);
    }

    public FlatSolarizedLightContrastIJTheme() {
        super(Utils.loadTheme("Solarized Light Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

