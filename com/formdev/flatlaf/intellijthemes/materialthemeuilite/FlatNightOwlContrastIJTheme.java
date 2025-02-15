/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatNightOwlContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Night Owl Contrast (Material)";

    public static boolean install() {
        try {
            return FlatNightOwlContrastIJTheme.install(new FlatNightOwlContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatNightOwlContrastIJTheme.installLafInfo(NAME, FlatNightOwlContrastIJTheme.class);
    }

    public FlatNightOwlContrastIJTheme() {
        super(Utils.loadTheme("Night Owl Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

