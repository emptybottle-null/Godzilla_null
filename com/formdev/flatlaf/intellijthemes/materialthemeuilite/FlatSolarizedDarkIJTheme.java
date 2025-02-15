/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatSolarizedDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Solarized Dark (Material)";

    public static boolean install() {
        try {
            return FlatSolarizedDarkIJTheme.install(new FlatSolarizedDarkIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatSolarizedDarkIJTheme.installLafInfo(NAME, FlatSolarizedDarkIJTheme.class);
    }

    public FlatSolarizedDarkIJTheme() {
        super(Utils.loadTheme("Solarized Dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

