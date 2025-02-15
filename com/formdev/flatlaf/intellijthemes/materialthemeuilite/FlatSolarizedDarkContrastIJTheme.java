/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedDarkContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Dark Contrast (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedDarkContrastIJTheme.install(new FlatSolarizedDarkContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedDarkContrastIJTheme.installLafInfo(NAME, FlatSolarizedDarkContrastIJTheme.class);
    }

    public FlatSolarizedDarkContrastIJTheme() {
        super(Utils.loadTheme("Solarized Dark Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

