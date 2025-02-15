/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatNightOwlIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Night Owl (Material)";

    public static boolean install() {
        try {
            return FlatNightOwlIJTheme.install(new FlatNightOwlIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatNightOwlIJTheme.installLafInfo(NAME, FlatNightOwlIJTheme.class);
    }

    public FlatNightOwlIJTheme() {
        super(Utils.loadTheme("Night Owl.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

