/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatDarkPurpleIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Dark purple";

    public static boolean install() {
        try {
            return FlatDarkPurpleIJTheme.install(new FlatDarkPurpleIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatDarkPurpleIJTheme.installLafInfo(NAME, FlatDarkPurpleIJTheme.class);
    }

    public FlatDarkPurpleIJTheme() {
        super(Utils.loadTheme("DarkPurple.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

