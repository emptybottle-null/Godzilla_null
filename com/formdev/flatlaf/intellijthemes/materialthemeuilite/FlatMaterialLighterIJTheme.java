/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialLighterIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Lighter (Material)";

    public static boolean install() {
        try {
            return FlatMaterialLighterIJTheme.install(new FlatMaterialLighterIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialLighterIJTheme.installLafInfo(NAME, FlatMaterialLighterIJTheme.class);
    }

    public FlatMaterialLighterIJTheme() {
        super(Utils.loadTheme("Material Lighter.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

