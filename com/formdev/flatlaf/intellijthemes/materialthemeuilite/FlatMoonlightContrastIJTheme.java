/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMoonlightContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Moonlight Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMoonlightContrastIJTheme.install(new FlatMoonlightContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMoonlightContrastIJTheme.installLafInfo(NAME, FlatMoonlightContrastIJTheme.class);
    }

    public FlatMoonlightContrastIJTheme() {
        super(Utils.loadTheme("Moonlight Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

