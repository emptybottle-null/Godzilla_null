/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import java.awt.Color;
import java.awt.Insets;
import javax.swing.UIManager;

public interface FlatComponentExtension {
    public Object getClientProperty(Object var1);

    public void putClientProperty(Object var1, Object var2);

    default public boolean getClientPropertyBoolean(Object key, String defaultValueKey) {
        Object value = this.getClientProperty(key);
        return value instanceof Boolean ? (Boolean)value : UIManager.getBoolean(defaultValueKey);
    }

    default public boolean getClientPropertyBoolean(Object key, boolean defaultValue) {
        Object value = this.getClientProperty(key);
        return value instanceof Boolean ? (Boolean)value : defaultValue;
    }

    default public void putClientPropertyBoolean(Object key, boolean value, boolean defaultValue) {
        this.putClientProperty(key, value != defaultValue ? Boolean.valueOf(value) : null);
    }

    default public int getClientPropertyInt(Object key, String defaultValueKey) {
        Object value = this.getClientProperty(key);
        return value instanceof Integer ? (Integer)value : UIManager.getInt(defaultValueKey);
    }

    default public int getClientPropertyInt(Object key, int defaultValue) {
        Object value = this.getClientProperty(key);
        return value instanceof Integer ? (Integer)value : defaultValue;
    }

    default public Color getClientPropertyColor(Object key, String defaultValueKey) {
        Object value = this.getClientProperty(key);
        return value instanceof Color ? (Color)value : UIManager.getColor(defaultValueKey);
    }

    default public Insets getClientPropertyInsets(Object key, String defaultValueKey) {
        Object value = this.getClientProperty(key);
        return value instanceof Insets ? (Insets)value : UIManager.getInsets(defaultValueKey);
    }

    default public <T extends Enum<T>> T getClientPropertyEnumString(Object key, Class<T> enumType, String defaultValueKey, T defaultValue) {
        Object value = this.getClientProperty(key);
        if (!(value instanceof String) && defaultValueKey != null) {
            value = UIManager.getString(defaultValueKey);
        }
        if (value instanceof String) {
            try {
                return Enum.valueOf(enumType, (String)value);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        return defaultValue;
    }

    default public <T extends Enum<T>> void putClientPropertyEnumString(Object key, Enum<T> value) {
        this.putClientProperty(key, value != null ? value.toString() : null);
    }
}

