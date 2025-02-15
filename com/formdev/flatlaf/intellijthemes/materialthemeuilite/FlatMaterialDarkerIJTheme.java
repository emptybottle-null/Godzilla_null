/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialDarkerIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Darker (Material)";

    public static boolean install() {
        try {
            return FlatMaterialDarkerIJTheme.install(new FlatMaterialDarkerIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDarkerIJTheme.installLafInfo(NAME, FlatMaterialDarkerIJTheme.class);
    }

    public FlatMaterialDarkerIJTheme() {
        super(Utils.loadTheme("Material Darker.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

