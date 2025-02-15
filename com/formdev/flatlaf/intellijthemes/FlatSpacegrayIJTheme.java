/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatSpacegrayIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Spacegray";

    public static boolean install() {
        try {
            return FlatSpacegrayIJTheme.install(new FlatSpacegrayIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatSpacegrayIJTheme.installLafInfo(NAME, FlatSpacegrayIJTheme.class);
    }

    public FlatSpacegrayIJTheme() {
        super(Utils.loadTheme("Spacegray.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

