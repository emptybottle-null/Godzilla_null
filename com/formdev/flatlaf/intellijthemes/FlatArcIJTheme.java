/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatArcIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Arc";

    public static boolean install() {
        try {
            return FlatArcIJTheme.install(new FlatArcIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatArcIJTheme.installLafInfo(NAME, FlatArcIJTheme.class);
    }

    public FlatArcIJTheme() {
        super(Utils.loadTheme("arc-theme.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

