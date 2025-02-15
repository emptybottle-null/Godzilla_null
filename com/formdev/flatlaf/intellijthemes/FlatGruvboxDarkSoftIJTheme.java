/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGruvboxDarkSoftIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gruvbox Dark Soft";

    public static boolean install() {
        try {
            return FlatGruvboxDarkSoftIJTheme.install(new FlatGruvboxDarkSoftIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatGruvboxDarkSoftIJTheme.installLafInfo(NAME, FlatGruvboxDarkSoftIJTheme.class);
    }

    public FlatGruvboxDarkSoftIJTheme() {
        super(Utils.loadTheme("gruvbox_dark_soft.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

