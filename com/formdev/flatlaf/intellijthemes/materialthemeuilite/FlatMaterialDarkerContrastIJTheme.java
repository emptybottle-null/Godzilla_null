/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialDarkerContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Darker Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialDarkerContrastIJTheme.install(new FlatMaterialDarkerContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDarkerContrastIJTheme.installLafInfo(NAME, FlatMaterialDarkerContrastIJTheme.class);
    }

    public FlatMaterialDarkerContrastIJTheme() {
        super(Utils.loadTheme("Material Darker Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

