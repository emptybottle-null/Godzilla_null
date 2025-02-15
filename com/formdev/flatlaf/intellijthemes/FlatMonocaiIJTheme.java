/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.Utils;

public class FlatMonocaiIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "Monocai";

    public static boolean install() {
        try {
            return FlatMonocaiIJTheme.install(new FlatMonocaiIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatMonocaiIJTheme.installLafInfo(NAME, FlatMonocaiIJTheme.class);
    }

    public FlatMonocaiIJTheme() {
        super(Utils.loadTheme("Monocai.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

