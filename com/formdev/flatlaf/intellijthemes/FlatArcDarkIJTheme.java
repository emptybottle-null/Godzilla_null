/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatArcDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc Dark";

    public static boolean install() {
        try {
            return FlatArcDarkIJTheme.install(new FlatArcDarkIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatArcDarkIJTheme.installLafInfo(NAME, FlatArcDarkIJTheme.class);
    }

    public FlatArcDarkIJTheme() {
        super(Utils.loadTheme("arc_theme_dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

