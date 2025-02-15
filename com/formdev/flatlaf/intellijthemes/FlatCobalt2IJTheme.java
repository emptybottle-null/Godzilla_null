/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatCobalt2IJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Cobalt 2";

    public static boolean install() {
        try {
            return FlatCobalt2IJTheme.install(new FlatCobalt2IJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatCobalt2IJTheme.installLafInfo(NAME, FlatCobalt2IJTheme.class);
    }

    public FlatCobalt2IJTheme() {
        super(Utils.loadTheme("Cobalt_2.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

