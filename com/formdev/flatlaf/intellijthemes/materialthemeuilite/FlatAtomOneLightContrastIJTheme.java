/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneLightContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Light Contrast (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneLightContrastIJTheme.install(new FlatAtomOneLightContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneLightContrastIJTheme.installLafInfo(NAME, FlatAtomOneLightContrastIJTheme.class);
    }

    public FlatAtomOneLightContrastIJTheme() {
        super(Utils.loadTheme("Atom One Light Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

