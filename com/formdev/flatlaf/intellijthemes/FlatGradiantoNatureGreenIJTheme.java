/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGradiantoNatureGreenIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gradianto Nature Green";

    public static boolean install() {
        try {
            return FlatGradiantoNatureGreenIJTheme.install(new FlatGradiantoNatureGreenIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatGradiantoNatureGreenIJTheme.installLafInfo(NAME, FlatGradiantoNatureGreenIJTheme.class);
    }

    public FlatGradiantoNatureGreenIJTheme() {
        super(Utils.loadTheme("Gradianto_Nature_Green.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

