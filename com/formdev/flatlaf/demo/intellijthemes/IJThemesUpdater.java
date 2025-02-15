/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.intellijthemes;

import com.formdev.flatlaf.demo.intellijthemes.IJThemeInfo;
import com.formdev.flatlaf.demo.intellijthemes.IJThemesManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class IJThemesUpdater {
    public static void main(String[] args) {
        IJThemesManager themesManager = new IJThemesManager();
        themesManager.loadBundledThemes();
        for (IJThemeInfo ti : themesManager.bundledThemes) {
            if (ti.sourceCodeUrl == null || ti.sourceCodePath == null) continue;
            String fromUrl = ti.sourceCodeUrl + "/" + ti.sourceCodePath;
            if (fromUrl.contains("github.com")) {
                fromUrl = fromUrl + "?raw=true";
            } else if (fromUrl.contains("gitlab.com")) {
                fromUrl = fromUrl.replace("/blob/", "/raw/");
            }
            String toPath = "../flatlaf-intellij-themes/src/main/resources/com/formdev/flatlaf/intellijthemes/themes/" + ti.resourceName;
            IJThemesUpdater.download(fromUrl, toPath);
        }
    }

    private static void download(String fromUrl, String toPath) {
        System.out.println("Download " + fromUrl);
        Path out = new File(toPath).toPath();
        try {
            URL url = new URL(fromUrl.replace(" ", "%20"));
            URLConnection con = url.openConnection();
            Files.copy(con.getInputStream(), out, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

