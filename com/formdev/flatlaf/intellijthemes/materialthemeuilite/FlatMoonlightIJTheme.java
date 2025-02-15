/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMoonlightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Moonlight (Material)";

    public static boolean install() {
        try {
            return FlatMoonlightIJTheme.install(new FlatMoonlightIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMoonlightIJTheme.installLafInfo(NAME, FlatMoonlightIJTheme.class);
    }

    public FlatMoonlightIJTheme() {
        super(Utils.loadTheme("Moonlight.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

