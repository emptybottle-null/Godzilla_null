/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatAtomOneDarkContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Atom One Dark Contrast (Material)";

    public static boolean install() {
        try {
            return FlatAtomOneDarkContrastIJTheme.install(new FlatAtomOneDarkContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatAtomOneDarkContrastIJTheme.installLafInfo(NAME, FlatAtomOneDarkContrastIJTheme.class);
    }

    public FlatAtomOneDarkContrastIJTheme() {
        super(Utils.loadTheme("Atom One Dark Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

