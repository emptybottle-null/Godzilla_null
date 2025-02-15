/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.util.Hashtable;

public class SystemProperties {
    protected static Hashtable propertyH;

    public static String getProperty(String key) {
        if (propertyH == null) {
            try {
                propertyH = System.getProperties();
            } catch (SecurityException ex) {
                propertyH = new Hashtable();
                return null;
            }
        }
        return (String)propertyH.get(key);
    }

    public static String getProperty(String key, String defaultValue) {
        String result = SystemProperties.getProperty(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    public static void setPropertyH(Hashtable aPropertyH) {
        propertyH = aPropertyH;
    }
}

