/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi;

public class Version {
    private static final String VERSION_STRING = "4.1.2";
    private static final String RELEASE_DATE = "20200217";

    public static String getVersion() {
        return VERSION_STRING;
    }

    public static String getReleaseDate() {
        return RELEASE_DATE;
    }

    public static String getProduct() {
        return "POI";
    }

    public static String getImplementationLanguage() {
        return "Java";
    }

    public static void main(String[] args) {
        System.out.println("Apache " + Version.getProduct() + " " + Version.getVersion() + " (" + Version.getReleaseDate() + ")");
    }
}

