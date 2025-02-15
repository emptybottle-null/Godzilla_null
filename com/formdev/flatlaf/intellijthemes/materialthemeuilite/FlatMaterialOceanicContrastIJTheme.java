/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialOceanicContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Oceanic Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialOceanicContrastIJTheme.install(new FlatMaterialOceanicContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialOceanicContrastIJTheme.installLafInfo(NAME, FlatMaterialOceanicContrastIJTheme.class);
    }

    public FlatMaterialOceanicContrastIJTheme() {
        super(Utils.loadTheme("Material Oceanic Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

