/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatMonokaiProIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Monokai Pro (Material)";

    public static boolean install() {
        try {
            return FlatMonokaiProIJTheme.install(new FlatMonokaiProIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMonokaiProIJTheme.installLafInfo(NAME, FlatMonokaiProIJTheme.class);
    }

    public FlatMonokaiProIJTheme() {
        super(Utils.loadTheme("Monokai Pro.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

