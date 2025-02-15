/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatGradiantoMidnightBlueIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Gradianto Midnight Blue";

    public static boolean install() {
        try {
            return FlatGradiantoMidnightBlueIJTheme.install(new FlatGradiantoMidnightBlueIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatGradiantoMidnightBlueIJTheme.installLafInfo(NAME, FlatGradiantoMidnightBlueIJTheme.class);
    }

    public FlatGradiantoMidnightBlueIJTheme() {
        super(Utils.loadTheme("Gradianto_midnight_blue.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

