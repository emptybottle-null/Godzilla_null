/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatMaterialDesignDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Material Design Dark";

    public static boolean install() {
        try {
            return FlatMaterialDesignDarkIJTheme.install(new FlatMaterialDesignDarkIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMaterialDesignDarkIJTheme.installLafInfo(NAME, FlatMaterialDesignDarkIJTheme.class);
    }

    public FlatMaterialDesignDarkIJTheme() {
        super(Utils.loadTheme("MaterialTheme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

