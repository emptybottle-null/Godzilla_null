/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatDraculaIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Dracula (Material)";

    public static boolean install() {
        try {
            return FlatDraculaIJTheme.install(new FlatDraculaIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatDraculaIJTheme.installLafInfo(NAME, FlatDraculaIJTheme.class);
    }

    public FlatDraculaIJTheme() {
        super(Utils.loadTheme("Dracula.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

