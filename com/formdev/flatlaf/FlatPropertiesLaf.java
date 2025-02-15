/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class FlatPropertiesLaf
extends FlatLaf {
    private final String name;
    private final String baseTheme;
    private final boolean dark;
    private final Properties properties;

    public FlatPropertiesLaf(String name, File propertiesFile) throws IOException {
        this(name, new FileInputStream(propertiesFile));
    }

    public FlatPropertiesLaf(String name, InputStream in) throws IOException {
        this(name, FlatPropertiesLaf.loadProperties(in));
    }

    private static Properties loadProperties(InputStream in) throws IOException {
        Properties properties = new Properties();
        try (InputStream in2 = in;){
            properties.load(in2);
        }
        return properties;
    }

    public FlatPropertiesLaf(String name, Properties properties) {
        this.name = name;
        this.properties = properties;
        this.baseTheme = properties.getProperty("@baseTheme", "light");
        this.dark = "dark".equalsIgnoreCase(this.baseTheme) || "darcula".equalsIgnoreCase(this.baseTheme);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.name;
    }

    @Override
    public boolean isDark() {
        return this.dark;
    }

    public Properties getProperties() {
        return this.properties;
    }

    protected ArrayList<Class<?>> getLafClassesForDefaultsLoading() {
        ArrayList lafClasses = new ArrayList();
        lafClasses.add(FlatLaf.class);
        switch (this.baseTheme.toLowerCase()) {
            default: {
                lafClasses.add(FlatLightLaf.class);
                break;
            }
            case "dark": {
                lafClasses.add(FlatDarkLaf.class);
                break;
            }
            case "intellij": {
                lafClasses.add(FlatLightLaf.class);
                lafClasses.add(FlatIntelliJLaf.class);
                break;
            }
            case "darcula": {
                lafClasses.add(FlatDarkLaf.class);
                lafClasses.add(FlatDarculaLaf.class);
            }
        }
        return lafClasses;
    }

    @Override
    protected Properties getAdditionalDefaults() {
        return this.properties;
    }
}

