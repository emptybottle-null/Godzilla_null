/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatVuesionIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Vuesion";

    public static boolean install() {
        try {
            return FlatVuesionIJTheme.install(new FlatVuesionIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatVuesionIJTheme.installLafInfo(NAME, FlatVuesionIJTheme.class);
    }

    public FlatVuesionIJTheme() {
        super(Utils.loadTheme("vuesion_theme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

