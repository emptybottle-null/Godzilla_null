/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatLaf;

public class FlatLightLaf
extends FlatLaf {
    public static final String NAME = "FlatLaf Light";

    public static boolean install() {
        return FlatLightLaf.install(new FlatLightLaf());
    }

    public static void installLafInfo() {
        FlatLightLaf.installLafInfo(NAME, FlatLightLaf.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "FlatLaf Light Look and Feel";
    }

    @Override
    public boolean isDark() {
        return false;
    }
}

