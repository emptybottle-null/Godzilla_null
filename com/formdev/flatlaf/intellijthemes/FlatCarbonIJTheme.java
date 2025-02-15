/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatCarbonIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Carbon";

    public static boolean install() {
        try {
            return FlatCarbonIJTheme.install(new FlatCarbonIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatCarbonIJTheme.installLafInfo(NAME, FlatCarbonIJTheme.class);
    }

    public FlatCarbonIJTheme() {
        super(Utils.loadTheme("Carbon.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

