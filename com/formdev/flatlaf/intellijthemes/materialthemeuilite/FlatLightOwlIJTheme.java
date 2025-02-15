/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatLightOwlIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Light Owl (Material)";

    public static boolean install() {
        try {
            return FlatLightOwlIJTheme.install(new FlatLightOwlIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatLightOwlIJTheme.installLafInfo(NAME, FlatLightOwlIJTheme.class);
    }

    public FlatLightOwlIJTheme() {
        super(Utils.loadTheme("Light Owl.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

