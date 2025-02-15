/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMaterialDeepOceanIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Deep Ocean (Material)";

    public static boolean install() {
        try {
            return FlatMaterialDeepOceanIJTheme.install(new FlatMaterialDeepOceanIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDeepOceanIJTheme.installLafInfo(NAME, FlatMaterialDeepOceanIJTheme.class);
    }

    public FlatMaterialDeepOceanIJTheme() {
        super(Utils.loadTheme("Material Deep Ocean.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

