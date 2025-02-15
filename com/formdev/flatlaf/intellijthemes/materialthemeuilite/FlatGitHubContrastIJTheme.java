/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.intellijthemes.materialthemeuilite;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.Utils;

public class FlatGitHubContrastIJTheme
extends IntelliJTheme.ThemeLaf {
    public static final String NAME = "GitHub Contrast (Material)";

    public static boolean install() {
        try {
            return FlatGitHubContrastIJTheme.install(new FlatGitHubContrastIJTheme());
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public static void installLafInfo() {
        FlatGitHubContrastIJTheme.installLafInfo(NAME, FlatGitHubContrastIJTheme.class);
    }

    public FlatGitHubContrastIJTheme() {
        super(Utils.loadTheme("GitHub Contrast.theme.json"));
    }

    @Override
    public String getName() {
        return NAME;
    }
}

