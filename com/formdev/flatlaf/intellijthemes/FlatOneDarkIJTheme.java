/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatOneDarkIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "One Dark";

    public static boolean install() {
        try {
            return FlatOneDarkIJTheme.install(new FlatOneDarkIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatOneDarkIJTheme.installLafInfo(NAME, FlatOneDarkIJTheme.class);
    }

    public FlatOneDarkIJTheme() {
        super(Utils.loadTheme("one_dark.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

