/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatLightLaf;

public class FlatIntelliJLaf
extends FlatLightLaf {
    public static final String NAME = "FlatLaf IntelliJ";

    public static boolean install() {
        return FlatIntelliJLaf.install(new FlatIntelliJLaf());
    }

    public static void installLafInfo() {
        FlatIntelliJLaf.installLafInfo(NAME, FlatIntelliJLaf.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "FlatLaf IntelliJ Look and Feel";
    }
}

