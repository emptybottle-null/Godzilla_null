/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatArcDarkOrangeIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc Dark - Orange";

    public static boolean install() {
        try {
            return FlatArcDarkOrangeIJTheme.install(new FlatArcDarkOrangeIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatArcDarkOrangeIJTheme.installLafInfo(NAME, FlatArcDarkOrangeIJTheme.class);
    }

    public FlatArcDarkOrangeIJTheme() {
        super(Utils.loadTheme("arc_theme_dark_orange.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

