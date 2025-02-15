/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.UIDefaultsLoader;
import com.formdev.flatlaf.json.Json;
import com.formdev.flatlaf.json.ParseException;
import com.formdev.flatlaf.util.StringUtils;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;

public class IntelliJTheme {
    public final String name;
    public final boolean dark;
    public final String author;
    private final boolean isMaterialUILite;
    private final Map<String, String> colors;
    private final Map<String, Object> ui;
    private final Map<String, Object> icons;
    private Map<String, ColorUIResource> namedColors = Collections.emptyMap();
    private static Map<String, String> uiKeyMapping;
    private static Map<String, String> uiKeyCopying;
    private static Map<String, String> uiKeyInverseMapping;
    private static Map<String, String> checkboxKeyMapping;
    private static Map<String, String> checkboxDuplicateColors;

    public static boolean install(InputStream in) {
        try {
            return FlatLaf.install(IntelliJTheme.createLaf(in));
        } catch (Exception ex) {
            FlatLaf.LOG.log(Level.SEVERE, "FlatLaf: Failed to load IntelliJ theme", ex);
            return false;
        }
    }

    public static FlatLaf createLaf(InputStream in) throws IOException {
        return IntelliJTheme.createLaf(new IntelliJTheme(in));
    }

    public static FlatLaf createLaf(IntelliJTheme theme) {
        return new ThemeLaf(theme);
    }

    public IntelliJTheme(InputStream in) throws IOException {
        Map json;
        try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);){
            json = (Map)Json.parse(reader);
        } catch (ParseException ex) {
            throw new IOException(ex.getMessage(), ex);
        }
        this.name = (String)json.get("name");
        this.dark = Boolean.parseBoolean((String)json.get("dark"));
        this.author = (String)json.get("author");
        this.isMaterialUILite = this.author.equals("Mallowigi");
        this.colors = (Map)json.get("colors");
        this.ui = (Map)json.get("ui");
        this.icons = (Map)json.get("icons");
    }

    /*
     * WARNING - void declaration
     */
    private void applyProperties(UIDefaults defaults) {
        int rowHeight;
        void var6_11;
        if (this.ui == null) {
            return;
        }
        defaults.put("Component.isIntelliJTheme", (Object)true);
        defaults.put("Button.paintShadow", (Object)true);
        defaults.put("Button.shadowWidth", (Object)(this.dark ? 2 : 1));
        Map<Object, Object> themeSpecificDefaults = this.removeThemeSpecificDefaults(defaults);
        this.loadNamedColors(defaults);
        ArrayList<Object> defaultsKeysCache = new ArrayList<Object>();
        HashSet<String> uiKeys = new HashSet<String>();
        for (Map.Entry<String, Object> entry : this.ui.entrySet()) {
            this.apply(entry.getKey(), entry.getValue(), defaults, defaultsKeysCache, uiKeys);
        }
        this.applyColorPalette(defaults);
        this.applyCheckBoxColors(defaults);
        for (Map.Entry<String, Object> entry : uiKeyCopying.entrySet()) {
            defaults.put(entry.getKey(), defaults.get(entry.getValue()));
        }
        Object panelBackground = defaults.get("Panel.background");
        defaults.put("Button.disabledBackground", panelBackground);
        defaults.put("ToggleButton.disabledBackground", panelBackground);
        this.copyIfNotSet(defaults, "Button.focusedBorderColor", "Component.focusedBorderColor", uiKeys);
        defaults.put("Button.hoverBorderColor", defaults.get("Button.focusedBorderColor"));
        defaults.put("HelpButton.hoverBorderColor", defaults.get("Button.focusedBorderColor"));
        Object object = defaults.get("Button.startBackground");
        Object helpButtonBorderColor = defaults.get("Button.startBorderColor");
        if (object == null) {
            Object object2 = defaults.get("Button.background");
        }
        if (helpButtonBorderColor == null) {
            helpButtonBorderColor = defaults.get("Button.borderColor");
        }
        defaults.put("HelpButton.background", (Object)var6_11);
        defaults.put("HelpButton.borderColor", helpButtonBorderColor);
        defaults.put("HelpButton.disabledBackground", panelBackground);
        defaults.put("HelpButton.disabledBorderColor", defaults.get("Button.disabledBorderColor"));
        defaults.put("HelpButton.focusedBorderColor", defaults.get("Button.focusedBorderColor"));
        defaults.put("HelpButton.focusedBackground", defaults.get("Button.focusedBackground"));
        defaults.put("ComboBox.editableBackground", defaults.get("TextField.background"));
        defaults.put("Spinner.background", defaults.get("TextField.background"));
        defaults.put("Spinner.buttonBackground", defaults.get("ComboBox.buttonEditableBackground"));
        defaults.put("Spinner.buttonArrowColor", defaults.get("ComboBox.buttonArrowColor"));
        defaults.put("Spinner.buttonDisabledArrowColor", defaults.get("ComboBox.buttonDisabledArrowColor"));
        if (uiKeys.contains("TextField.background")) {
            Object textFieldBackground = defaults.get("TextField.background");
            if (!uiKeys.contains("FormattedTextField.background")) {
                defaults.put("FormattedTextField.background", textFieldBackground);
            }
            if (!uiKeys.contains("PasswordField.background")) {
                defaults.put("PasswordField.background", textFieldBackground);
            }
            if (!uiKeys.contains("EditorPane.background")) {
                defaults.put("EditorPane.background", textFieldBackground);
            }
            if (!uiKeys.contains("TextArea.background")) {
                defaults.put("TextArea.background", textFieldBackground);
            }
            if (!uiKeys.contains("TextPane.background")) {
                defaults.put("TextPane.background", textFieldBackground);
            }
            if (!uiKeys.contains("Spinner.background")) {
                defaults.put("Spinner.background", textFieldBackground);
            }
        }
        if (!uiKeys.contains("ToggleButton.startBackground") && !uiKeys.contains("*.startBackground")) {
            defaults.put("ToggleButton.startBackground", defaults.get("Button.startBackground"));
        }
        if (!uiKeys.contains("ToggleButton.endBackground") && !uiKeys.contains("*.endBackground")) {
            defaults.put("ToggleButton.endBackground", defaults.get("Button.endBackground"));
        }
        if (!uiKeys.contains("ToggleButton.foreground") && uiKeys.contains("Button.foreground")) {
            defaults.put("ToggleButton.foreground", defaults.get("Button.foreground"));
        }
        if (this.isMaterialUILite) {
            defaults.put("List.background", defaults.get("Tree.background"));
            defaults.put("Table.background", defaults.get("Tree.background"));
        }
        if ((rowHeight = defaults.getInt("Tree.rowHeight")) > 22) {
            defaults.put("Tree.rowHeight", (Object)22);
        }
        defaults.putAll(themeSpecificDefaults);
    }

    private Map<Object, Object> removeThemeSpecificDefaults(UIDefaults defaults) {
        ArrayList<String> themeSpecificKeys = new ArrayList<String>();
        for (Object key : defaults.keySet()) {
            if (!(key instanceof String) || !((String)key).startsWith("[")) continue;
            themeSpecificKeys.add((String)key);
        }
        HashMap<Object, Object> themeSpecificDefaults = new HashMap<Object, Object>();
        String currentThemePrefix = '[' + this.name.replace(' ', '_') + ']';
        String currentAuthorPrefix = "[author-" + this.author.replace(' ', '_') + ']';
        String allThemesPrefix = "[*]";
        String[] prefixes = new String[]{currentThemePrefix, currentAuthorPrefix, allThemesPrefix};
        block1: for (String key : themeSpecificKeys) {
            Object value = defaults.remove(key);
            for (String prefix : prefixes) {
                if (!key.startsWith(prefix)) continue;
                themeSpecificDefaults.put(key.substring(prefix.length()), value);
                continue block1;
            }
        }
        return themeSpecificDefaults;
    }

    private void loadNamedColors(UIDefaults defaults) {
        if (this.colors == null) {
            return;
        }
        this.namedColors = new HashMap<String, ColorUIResource>();
        for (Map.Entry<String, String> e : this.colors.entrySet()) {
            String value = e.getValue();
            ColorUIResource color = UIDefaultsLoader.parseColor(value);
            if (color == null) continue;
            String key = e.getKey();
            this.namedColors.put(key, color);
            defaults.put("ColorPalette." + key, color);
        }
    }

    private void apply(String key, Object value, UIDefaults defaults, ArrayList<Object> defaultsKeysCache, Set<String> uiKeys) {
        if (value instanceof Map) {
            for (Map.Entry e : ((Map)value).entrySet()) {
                this.apply(key + '.' + (String)e.getKey(), e.getValue(), defaults, defaultsKeysCache, uiKeys);
            }
        } else {
            if ("".equals(value)) {
                return;
            }
            uiKeys.add(key);
            if (this.isMaterialUILite && (key.equals("ComboBox.padding") || key.equals("Spinner.border"))) {
                return;
            }
            if ((key = uiKeyMapping.getOrDefault(key, key)).isEmpty()) {
                return;
            }
            String valueStr = value.toString();
            Object uiValue = this.namedColors.get(valueStr);
            if (uiValue == null) {
                List<String> parts;
                if (!valueStr.startsWith("#") && (key.endsWith("ground") || key.endsWith("Color"))) {
                    valueStr = this.fixColorIfValid("#" + valueStr, valueStr);
                } else if (valueStr.startsWith("##")) {
                    valueStr = this.fixColorIfValid(valueStr.substring(1), valueStr);
                } else if ((key.endsWith(".border") || key.endsWith("Border")) && (parts = StringUtils.split(valueStr, ',')).size() == 5 && !parts.get(4).startsWith("#")) {
                    parts.set(4, "#" + parts.get(4));
                    valueStr = String.join((CharSequence)",", parts);
                }
                try {
                    uiValue = UIDefaultsLoader.parseValue(key, valueStr);
                } catch (RuntimeException ex) {
                    UIDefaultsLoader.logParseError(Level.CONFIG, key, valueStr, ex);
                    return;
                }
            }
            if (key.startsWith("*.")) {
                String tail = key.substring(1);
                if (defaultsKeysCache.size() != defaults.size()) {
                    defaultsKeysCache.clear();
                    Enumeration e = defaults.keys();
                    while (e.hasMoreElements()) {
                        defaultsKeysCache.add(e.nextElement());
                    }
                }
                for (Object k : defaultsKeysCache) {
                    String km;
                    if (!(k instanceof String) || !(km = uiKeyInverseMapping.getOrDefault(k, (String)k)).endsWith(tail) || ((String)k).startsWith("CheckBox.icon.")) continue;
                    defaults.put(k, uiValue);
                }
            } else {
                defaults.put(key, uiValue);
            }
        }
    }

    private String fixColorIfValid(String newColorStr, String colorStr) {
        try {
            UIDefaultsLoader.parseColorRGBA(newColorStr);
            return newColorStr;
        } catch (IllegalArgumentException ex) {
            return colorStr;
        }
    }

    private void applyColorPalette(UIDefaults defaults) {
        if (this.icons == null) {
            return;
        }
        Object palette = this.icons.get("ColorPalette");
        if (!(palette instanceof Map)) {
            return;
        }
        Map colorPalette = (Map)palette;
        for (Map.Entry e : colorPalette.entrySet()) {
            ColorUIResource color;
            String key = (String)e.getKey();
            Object value = e.getValue();
            if (key.startsWith("Checkbox.") || !(value instanceof String)) continue;
            if (this.dark) {
                key = StringUtils.removeTrailing(key, ".Dark");
            }
            if ((color = this.toColor((String)value)) == null) continue;
            defaults.put(key, color);
        }
    }

    private ColorUIResource toColor(String value) {
        ColorUIResource color = this.namedColors.get(value);
        return color != null ? color : UIDefaultsLoader.parseColor(value);
    }

    private void applyCheckBoxColors(UIDefaults defaults) {
        if (this.icons == null) {
            return;
        }
        Object palette = this.icons.get("ColorPalette");
        if (!(palette instanceof Map)) {
            return;
        }
        boolean checkboxModified = false;
        Map colorPalette = (Map)palette;
        for (Map.Entry e : colorPalette.entrySet()) {
            ColorUIResource color;
            String newKey;
            String key = (String)e.getKey();
            Object value = e.getValue();
            if (!key.startsWith("Checkbox.") || !(value instanceof String)) continue;
            if (key.equals("Checkbox.Background.Default") || key.equals("Checkbox.Foreground.Selected")) {
                value = "#ffffff";
            }
            String key2 = checkboxDuplicateColors.get(key);
            if (this.dark) {
                key = StringUtils.removeTrailing(key, ".Dark");
            }
            if ((newKey = checkboxKeyMapping.get(key)) == null) continue;
            String checkBoxIconPrefix = "CheckBox.icon.";
            if (!this.dark && newKey.startsWith(checkBoxIconPrefix)) {
                newKey = "CheckBox.icon[filled].".concat(newKey.substring(checkBoxIconPrefix.length()));
            }
            if ((color = this.toColor((String)value)) != null) {
                defaults.put(newKey, color);
                if (key2 != null) {
                    String newKey2;
                    if (this.dark) {
                        key2 = StringUtils.removeTrailing(key2, ".Dark");
                    }
                    if ((newKey2 = checkboxKeyMapping.get(key2)) != null) {
                        defaults.put(newKey2, color);
                    }
                }
            }
            checkboxModified = true;
        }
        if (checkboxModified) {
            defaults.remove("CheckBox.icon.focusWidth");
            defaults.put("CheckBox.icon.hoverBorderColor", defaults.get("CheckBox.icon.focusedBorderColor"));
            defaults.remove("CheckBox.icon[filled].focusWidth");
            defaults.put("CheckBox.icon[filled].hoverBorderColor", defaults.get("CheckBox.icon[filled].focusedBorderColor"));
            defaults.put("CheckBox.icon[filled].selectedFocusedBackground", defaults.get("CheckBox.icon[filled].selectedBackground"));
            if (this.dark) {
                String[] focusedBorderColorKeys;
                for (String key : focusedBorderColorKeys = new String[]{"CheckBox.icon.focusedBorderColor", "CheckBox.icon.selectedFocusedBorderColor", "CheckBox.icon[filled].focusedBorderColor", "CheckBox.icon[filled].selectedFocusedBorderColor"}) {
                    Color color = defaults.getColor(key);
                    if (color == null) continue;
                    defaults.put(key, new ColorUIResource(new Color(color.getRGB() & 0xFFFFFF | 0xA6000000, true)));
                }
            }
        }
    }

    private void copyIfNotSet(UIDefaults defaults, String destKey, String srcKey, Set<String> uiKeys) {
        if (!uiKeys.contains(destKey)) {
            defaults.put(destKey, defaults.get(srcKey));
        }
    }

    static {
        Map.Entry[] entries;
        uiKeyMapping = new HashMap<String, String>();
        uiKeyCopying = new HashMap<String, String>();
        uiKeyInverseMapping = new HashMap<String, String>();
        checkboxKeyMapping = new HashMap<String, String>();
        checkboxDuplicateColors = new HashMap<String, String>();
        uiKeyMapping.put("ComboBox.background", "");
        uiKeyMapping.put("ComboBox.nonEditableBackground", "ComboBox.background");
        uiKeyMapping.put("ComboBox.ArrowButton.background", "ComboBox.buttonEditableBackground");
        uiKeyMapping.put("ComboBox.ArrowButton.disabledIconColor", "ComboBox.buttonDisabledArrowColor");
        uiKeyMapping.put("ComboBox.ArrowButton.iconColor", "ComboBox.buttonArrowColor");
        uiKeyMapping.put("ComboBox.ArrowButton.nonEditableBackground", "ComboBox.buttonBackground");
        uiKeyMapping.put("Component.inactiveErrorFocusColor", "Component.error.borderColor");
        uiKeyMapping.put("Component.errorFocusColor", "Component.error.focusedBorderColor");
        uiKeyMapping.put("Component.inactiveWarningFocusColor", "Component.warning.borderColor");
        uiKeyMapping.put("Component.warningFocusColor", "Component.warning.focusedBorderColor");
        uiKeyMapping.put("Link.activeForeground", "Component.linkColor");
        uiKeyMapping.put("Menu.border", "Menu.margin");
        uiKeyMapping.put("MenuItem.border", "MenuItem.margin");
        uiKeyCopying.put("CheckBoxMenuItem.margin", "MenuItem.margin");
        uiKeyCopying.put("RadioButtonMenuItem.margin", "MenuItem.margin");
        uiKeyMapping.put("PopupMenu.border", "PopupMenu.borderInsets");
        uiKeyCopying.put("MenuItem.underlineSelectionColor", "TabbedPane.underlineColor");
        uiKeyCopying.put("Menu.selectionBackground", "List.selectionBackground");
        uiKeyCopying.put("MenuItem.selectionBackground", "List.selectionBackground");
        uiKeyCopying.put("CheckBoxMenuItem.selectionBackground", "List.selectionBackground");
        uiKeyCopying.put("RadioButtonMenuItem.selectionBackground", "List.selectionBackground");
        uiKeyMapping.put("ProgressBar.background", "");
        uiKeyMapping.put("ProgressBar.foreground", "");
        uiKeyMapping.put("ProgressBar.trackColor", "ProgressBar.background");
        uiKeyMapping.put("ProgressBar.progressColor", "ProgressBar.foreground");
        uiKeyCopying.put("ProgressBar.selectionForeground", "ProgressBar.background");
        uiKeyCopying.put("ProgressBar.selectionBackground", "ProgressBar.foreground");
        uiKeyMapping.put("ScrollBar.trackColor", "ScrollBar.track");
        uiKeyMapping.put("ScrollBar.thumbColor", "ScrollBar.thumb");
        uiKeyMapping.put("Separator.separatorColor", "Separator.foreground");
        uiKeyMapping.put("Slider.trackWidth", "");
        uiKeyCopying.put("Slider.trackValueColor", "ProgressBar.foreground");
        uiKeyCopying.put("Slider.thumbColor", "ProgressBar.foreground");
        uiKeyCopying.put("Slider.trackColor", "ProgressBar.background");
        uiKeyCopying.put("TitlePane.inactiveBackground", "TitlePane.background");
        uiKeyMapping.put("TitlePane.infoForeground", "TitlePane.foreground");
        uiKeyMapping.put("TitlePane.inactiveInfoForeground", "TitlePane.inactiveForeground");
        for (Map.Entry<String, String> e : uiKeyMapping.entrySet()) {
            uiKeyInverseMapping.put(e.getValue(), e.getKey());
        }
        uiKeyCopying.put("ToggleButton.tab.underlineColor", "TabbedPane.underlineColor");
        uiKeyCopying.put("ToggleButton.tab.disabledUnderlineColor", "TabbedPane.disabledUnderlineColor");
        uiKeyCopying.put("ToggleButton.tab.selectedBackground", "TabbedPane.selectedBackground");
        uiKeyCopying.put("ToggleButton.tab.hoverBackground", "TabbedPane.hoverColor");
        uiKeyCopying.put("ToggleButton.tab.focusBackground", "TabbedPane.focusColor");
        checkboxKeyMapping.put("Checkbox.Background.Default", "CheckBox.icon.background");
        checkboxKeyMapping.put("Checkbox.Background.Disabled", "CheckBox.icon.disabledBackground");
        checkboxKeyMapping.put("Checkbox.Border.Default", "CheckBox.icon.borderColor");
        checkboxKeyMapping.put("Checkbox.Border.Disabled", "CheckBox.icon.disabledBorderColor");
        checkboxKeyMapping.put("Checkbox.Focus.Thin.Default", "CheckBox.icon.focusedBorderColor");
        checkboxKeyMapping.put("Checkbox.Focus.Wide", "CheckBox.icon.focusColor");
        checkboxKeyMapping.put("Checkbox.Foreground.Disabled", "CheckBox.icon.disabledCheckmarkColor");
        checkboxKeyMapping.put("Checkbox.Background.Selected", "CheckBox.icon.selectedBackground");
        checkboxKeyMapping.put("Checkbox.Border.Selected", "CheckBox.icon.selectedBorderColor");
        checkboxKeyMapping.put("Checkbox.Foreground.Selected", "CheckBox.icon.checkmarkColor");
        checkboxKeyMapping.put("Checkbox.Focus.Thin.Selected", "CheckBox.icon.selectedFocusedBorderColor");
        checkboxDuplicateColors.put("Checkbox.Background.Default.Dark", "Checkbox.Background.Selected.Dark");
        checkboxDuplicateColors.put("Checkbox.Border.Default.Dark", "Checkbox.Border.Selected.Dark");
        checkboxDuplicateColors.put("Checkbox.Focus.Thin.Default.Dark", "Checkbox.Focus.Thin.Selected.Dark");
        for (Map.Entry e : entries = checkboxDuplicateColors.entrySet().toArray(new Map.Entry[checkboxDuplicateColors.size()])) {
            checkboxDuplicateColors.put((String)e.getValue(), (String)e.getKey());
        }
    }

    public static class ThemeLaf
    extends FlatLaf {
        private final IntelliJTheme theme;

        public ThemeLaf(IntelliJTheme theme) {
            this.theme = theme;
        }

        @Override
        public String getName() {
            return this.theme.name;
        }

        @Override
        public String getDescription() {
            return this.getName();
        }

        @Override
        public boolean isDark() {
            return this.theme.dark;
        }

        public IntelliJTheme getTheme() {
            return this.theme;
        }

        @Override
        void applyAdditionalDefaults(UIDefaults defaults) {
            this.theme.applyProperties(defaults);
        }

        protected ArrayList<Class<?>> getLafClassesForDefaultsLoading() {
            ArrayList lafClasses = new ArrayList();
            lafClasses.add(FlatLaf.class);
            lafClasses.add(this.theme.dark ? FlatDarkLaf.class : FlatLightLaf.class);
            lafClasses.add(this.theme.dark ? FlatDarculaLaf.class : FlatIntelliJLaf.class);
            lafClasses.add(ThemeLaf.class);
            return lafClasses;
        }
    }
}

