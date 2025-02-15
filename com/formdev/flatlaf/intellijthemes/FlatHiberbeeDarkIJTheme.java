/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatHiberbeeDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Hiberbee Dark";

    public static boolean install() {
        try {
            return FlatHiberbeeDarkIJTheme.install(new FlatHiberbeeDarkIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatHiberbeeDarkIJTheme.installLafInfo(NAME, FlatHiberbeeDarkIJTheme.class);
    }

    public FlatHiberbeeDarkIJTheme() {
        super(Utils.loadTheme("HiberbeeDark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

