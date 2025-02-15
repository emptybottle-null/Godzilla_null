/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneLightIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Light (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneLightIJTheme.install(new FlatAtomOneLightIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneLightIJTheme.installLafInfo(NAME, FlatAtomOneLightIJTheme.class);
    }

    public FlatAtomOneLightIJTheme() {
        super(Utils.loadTheme("Atom One Light.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

