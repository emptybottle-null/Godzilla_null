/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialDeepOceanContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Deep Ocean Contrast (Material)";

    public static boolean install() {
        try {
            return FlatMaterialDeepOceanContrastIJTheme.install(new FlatMaterialDeepOceanContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDeepOceanContrastIJTheme.installLafInfo(NAME, FlatMaterialDeepOceanContrastIJTheme.class);
    }

    public FlatMaterialDeepOceanContrastIJTheme() {
        super(Utils.loadTheme("Material Deep Ocean Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

