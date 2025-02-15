/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGradiantoDarkFuchsiaIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gradianto Dark Fuchsia";

    public static boolean install() {
        try {
            return FlatGradiantoDarkFuchsiaIJTheme.install(new FlatGradiantoDarkFuchsiaIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatGradiantoDarkFuchsiaIJTheme.installLafInfo(NAME, FlatGradiantoDarkFuchsiaIJTheme.class);
    }

    public FlatGradiantoDarkFuchsiaIJTheme() {
        super(Utils.loadTheme("Gradianto_dark_fuchsia.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

