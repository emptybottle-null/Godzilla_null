/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialPalenightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Palenight (Material)";

    public static boolean install() {
        try {
            return FlatMaterialPalenightIJTheme.install(new FlatMaterialPalenightIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialPalenightIJTheme.installLafInfo(NAME, FlatMaterialPalenightIJTheme.class);
    }

    public FlatMaterialPalenightIJTheme() {
        super(Utils.loadTheme("Material Palenight.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

