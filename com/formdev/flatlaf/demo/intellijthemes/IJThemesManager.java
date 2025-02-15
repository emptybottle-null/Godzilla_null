/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.intellijthemes;

import com.formdev.flatlaf.demo.intellijthemes.IJThemeInfo;
import com.formdev.flatlaf.json.Json;
import com.formdev.flatlaf.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IJThemesManager {
    final List<IJThemeInfo> bundledThemes = new ArrayList<IJThemeInfo>();
    final List<IJThemeInfo> moreThemes = new ArrayList<IJThemeInfo>();
    private final Map<File, Long> lastModifiedMap = new HashMap<File, Long>();

    IJThemesManager() {
    }

    void loadBundledThemes() {
        Map json;
        this.bundledThemes.clear();
        try (InputStreamReader reader = new InputStreamReader(this.getClass().getResourceAsStream("themes.json"), StandardCharsets.UTF_8);){
            json = (Map)Json.parse(reader);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        for (Map.Entry e : json.entrySet()) {
            String resourceName = (String)e.getKey();
            Map value = (Map)e.getValue();
            String name = (String)value.get("name");
            boolean dark = Boolean.parseBoolean((String)value.get("dark"));
            String license = (String)value.get("license");
            String licenseFile = (String)value.get("licenseFile");
            String sourceCodeUrl = (String)value.get("sourceCodeUrl");
            String sourceCodePath = (String)value.get("sourceCodePath");
            this.bundledThemes.add(new IJThemeInfo(name, resourceName, dark, license, licenseFile, sourceCodeUrl, sourceCodePath, null, null));
        }
    }

    void loadThemesFromDirectory() {
        File directory = new File("").getAbsoluteFile();
        File[] themeFiles = directory.listFiles((dir, name) -> name.endsWith(".theme.json") || name.endsWith(".properties"));
        if (themeFiles == null) {
            return;
        }
        this.lastModifiedMap.clear();
        this.lastModifiedMap.put(directory, directory.lastModified());
        this.moreThemes.clear();
        for (File f : themeFiles) {
            String fname = f.getName();
            String name2 = fname.endsWith(".properties") ? StringUtils.removeTrailing(fname, ".properties") : StringUtils.removeTrailing(fname, ".theme.json");
            this.moreThemes.add(new IJThemeInfo(name2, null, false, null, null, null, null, f, null));
            this.lastModifiedMap.put(f, f.lastModified());
        }
    }

    boolean hasThemesFromDirectoryChanged() {
        for (Map.Entry<File, Long> e : this.lastModifiedMap.entrySet()) {
            if (e.getKey().lastModified() == e.getValue().longValue()) continue;
            return true;
        }
        return false;
    }
}

