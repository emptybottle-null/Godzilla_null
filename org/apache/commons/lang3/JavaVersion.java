/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.math.NumberUtils;

public enum JavaVersion {
    JAVA_0_9(1.5f, "0.9"),
    JAVA_1_1(1.1f, "1.1"),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8"),
    JAVA_1_9(9.0f, "9"),
    JAVA_9(9.0f, "9"),
    JAVA_10(10.0f, "10"),
    JAVA_11(11.0f, "11"),
    JAVA_12(12.0f, "12"),
    JAVA_13(13.0f, "13"),
    JAVA_14(14.0f, "14"),
    JAVA_15(15.0f, "15"),
    JAVA_16(16.0f, "16"),
    JAVA_17(17.0f, "17"),
    JAVA_RECENT(JavaVersion.maxVersion(), Float.toString(JavaVersion.maxVersion()));

    private final float value;
    private final String name;

    private JavaVersion(float value, String name) {
        this.value = value;
        this.name = name;
    }

    public boolean atLeast(JavaVersion requiredVersion) {
        return this.value >= requiredVersion.value;
    }

    public boolean atMost(JavaVersion requiredVersion) {
        return this.value <= requiredVersion.value;
    }

    static JavaVersion getJavaVersion(String nom) {
        return JavaVersion.get(nom);
    }

    static JavaVersion get(String versionStr) {
        int end;
        int firstComma;
        if (versionStr == null) {
            return null;
        }
        switch (versionStr) {
            case "0.9": {
                return JAVA_0_9;
            }
            case "1.1": {
                return JAVA_1_1;
            }
            case "1.2": {
                return JAVA_1_2;
            }
            case "1.3": {
                return JAVA_1_3;
            }
            case "1.4": {
                return JAVA_1_4;
            }
            case "1.5": {
                return JAVA_1_5;
            }
            case "1.6": {
                return JAVA_1_6;
            }
            case "1.7": {
                return JAVA_1_7;
            }
            case "1.8": {
                return JAVA_1_8;
            }
            case "9": {
                return JAVA_9;
            }
            case "10": {
                return JAVA_10;
            }
            case "11": {
                return JAVA_11;
            }
            case "12": {
                return JAVA_12;
            }
            case "13": {
                return JAVA_13;
            }
            case "14": {
                return JAVA_14;
            }
            case "15": {
                return JAVA_15;
            }
            case "16": {
                return JAVA_16;
            }
            case "17": {
                return JAVA_17;
            }
        }
        float v = JavaVersion.toFloatVersion(versionStr);
        if ((double)v - 1.0 < 1.0 ? Float.parseFloat(versionStr.substring((firstComma = Math.max(versionStr.indexOf(46), versionStr.indexOf(44))) + 1, end = Math.max(versionStr.length(), versionStr.indexOf(44, firstComma)))) > 0.9f : v > 10.0f) {
            return JAVA_RECENT;
        }
        return null;
    }

    public String toString() {
        return this.name;
    }

    private static float maxVersion() {
        float v = JavaVersion.toFloatVersion(System.getProperty("java.specification.version", "99.0"));
        if (v > 0.0f) {
            return v;
        }
        return 99.0f;
    }

    private static float toFloatVersion(String value) {
        int defaultReturnValue = -1;
        if (value.contains(".")) {
            String[] toParse = value.split("\\.");
            if (toParse.length >= 2) {
                return NumberUtils.toFloat(toParse[0] + '.' + toParse[1], -1.0f);
            }
        } else {
            return NumberUtils.toFloat(value, -1.0f);
        }
        return -1.0f;
    }
}

