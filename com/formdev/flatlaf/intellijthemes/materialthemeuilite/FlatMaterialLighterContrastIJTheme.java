/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialLighterContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Lighter Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialLighterContrastIJTheme.install(new FlatMaterialLighterContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialLighterContrastIJTheme.installLafInfo(NAME, FlatMaterialLighterContrastIJTheme.class);
    }

    public FlatMaterialLighterContrastIJTheme() {
        super(Utils.loadTheme("Material Lighter Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

