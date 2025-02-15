/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDarkLaf;

public class FlatDarculaLaf
extends FlatDarkLaf {
    public static final String NAME = "FlatLaf Darcula";

    public static boolean install() {
        return FlatDarculaLaf.install(new FlatDarculaLaf());
    }

    public static void installLafInfo() {
        FlatDarculaLaf.installLafInfo(NAME, FlatDarculaLaf.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return "FlatLaf Darcula Look and Feel";
    }
}

