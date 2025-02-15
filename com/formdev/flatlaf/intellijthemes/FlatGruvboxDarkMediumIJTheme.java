/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGruvboxDarkMediumIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gruvbox Dark Medium";

    public static boolean install() {
        try {
            return FlatGruvboxDarkMediumIJTheme.install(new FlatGruvboxDarkMediumIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatGruvboxDarkMediumIJTheme.installLafInfo(NAME, FlatGruvboxDarkMediumIJTheme.class);
    }

    public FlatGruvboxDarkMediumIJTheme() {
        super(Utils.loadTheme("gruvbox_dark_medium.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

