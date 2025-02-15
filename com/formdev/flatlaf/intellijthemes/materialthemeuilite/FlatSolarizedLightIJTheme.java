/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedLightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Light (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedLightIJTheme.install(new FlatSolarizedLightIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedLightIJTheme.installLafInfo(NAME, FlatSolarizedLightIJTheme.class);
    }

    public FlatSolarizedLightIJTheme() {
        super(Utils.loadTheme("Solarized Light.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

