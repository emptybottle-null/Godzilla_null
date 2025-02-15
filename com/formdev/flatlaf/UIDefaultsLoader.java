/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf;

import com.formdev.flatlaf.FlatDefaultsAddon;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.DerivedColor;
import com.formdev.flatlaf.util.GrayFilter;
import com.formdev.flatlaf.util.HSLColor;
import com.formdev.flatlaf.util.StringUtils;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Level;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;

class UIDefaultsLoader {
    private static final String TYPE_PREFIX = "{";
    private static final String TYPE_PREFIX_END = "}";
    private static final String VARIABLE_PREFIX = "@";
    private static final String PROPERTY_PREFIX = "$";
    private static final String OPTIONAL_PREFIX = "?";
    private static final String WILDCARD_PREFIX = "*.";
    private static ValueType[] tempResultValueType = new ValueType[1];

    UIDefaultsLoader() {
    }

    static void loadDefaultsFromProperties(Class<?> lookAndFeelClass, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
        ArrayList lafClasses = new ArrayList();
        Class<?> lafClass = lookAndFeelClass;
        while (FlatLaf.class.isAssignableFrom(lafClass)) {
            lafClasses.add(0, lafClass);
            lafClass = lafClass.getSuperclass();
        }
        UIDefaultsLoader.loadDefaultsFromProperties(lafClasses, addons, additionalDefaults, dark, defaults);
    }

    /*
     * WARNING - void declaration
     */
    static void loadDefaultsFromProperties(List<Class<?>> lafClasses, List<FlatDefaultsAddon> addons, Properties additionalDefaults, boolean dark, UIDefaults defaults) {
        try {
            Properties properties = new Properties();
            for (Class<?> clazz : lafClasses) {
                String propertiesName = '/' + clazz.getName().replace('.', '/') + ".properties";
                InputStream in = clazz.getResourceAsStream(propertiesName);
                Throwable throwable = null;
                try {
                    if (in == null) continue;
                    properties.load(in);
                } catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                } finally {
                    if (in == null) continue;
                    if (throwable != null) {
                        try {
                            in.close();
                        } catch (Throwable throwable3) {
                            throwable.addSuppressed(throwable3);
                        }
                        continue;
                    }
                    in.close();
                }
            }
            for (FlatDefaultsAddon flatDefaultsAddon : addons) {
                for (Class<?> lafClass : lafClasses) {
                    InputStream in = flatDefaultsAddon.getDefaults(lafClass);
                    Throwable throwable = null;
                    try {
                        if (in == null) continue;
                        properties.load(in);
                    } catch (Throwable throwable4) {
                        throwable = throwable4;
                        throw throwable4;
                    } finally {
                        if (in == null) continue;
                        if (throwable != null) {
                            try {
                                in.close();
                            } catch (Throwable throwable5) {
                                throwable.addSuppressed(throwable5);
                            }
                            continue;
                        }
                        in.close();
                    }
                }
            }
            ArrayList<ClassLoader> addonClassLoaders = new ArrayList<ClassLoader>();
            for (FlatDefaultsAddon addon : addons) {
                ClassLoader addonClassLoader = addon.getClass().getClassLoader();
                if (addonClassLoaders.contains(addonClassLoader)) continue;
                addonClassLoaders.add(addonClassLoader);
            }
            List<Object> list = FlatLaf.getCustomDefaultsSources();
            int size = list != null ? list.size() : 0;
            for (int i = 0; i < size; ++i) {
                Object in;
                Iterator source = list.get(i);
                if (source instanceof String && i + 1 < size) {
                    ClassLoader classLoader;
                    String packageName = (String)((Object)source);
                    if ((classLoader = (ClassLoader)list.get(++i)) != null && !addonClassLoaders.contains(classLoader)) {
                        addonClassLoaders.add(classLoader);
                    }
                    packageName = packageName.replace('.', '/');
                    if (classLoader == null) {
                        ClassLoader classLoader2 = FlatLaf.class.getClassLoader();
                    }
                    for (Class<?> lafClass : lafClasses) {
                        void var12_28;
                        String propertiesName = packageName + '/' + lafClass.getSimpleName() + ".properties";
                        in = var12_28.getResourceAsStream(propertiesName);
                        Throwable throwable = null;
                        try {
                            if (in == null) continue;
                            properties.load((InputStream)in);
                        } catch (Throwable throwable6) {
                            throwable = throwable6;
                            throw throwable6;
                        } finally {
                            if (in == null) continue;
                            if (throwable != null) {
                                try {
                                    ((InputStream)in).close();
                                } catch (Throwable throwable7) {
                                    throwable.addSuppressed(throwable7);
                                }
                                continue;
                            }
                            ((InputStream)in).close();
                        }
                    }
                    continue;
                }
                if (!(source instanceof File)) continue;
                File folder = (File)((Object)source);
                for (Class<?> lafClass : lafClasses) {
                    File propertiesFile = new File(folder, lafClass.getSimpleName() + ".properties");
                    if (!propertiesFile.isFile()) continue;
                    FileInputStream in2 = new FileInputStream(propertiesFile);
                    in = null;
                    try {
                        properties.load(in2);
                    } catch (Throwable throwable) {
                        in = throwable;
                        throw throwable;
                    } finally {
                        if (in2 == null) continue;
                        if (in != null) {
                            try {
                                ((InputStream)in2).close();
                            } catch (Throwable throwable) {
                                ((Throwable)in).addSuppressed(throwable);
                            }
                            continue;
                        }
                        ((InputStream)in2).close();
                    }
                }
            }
            if (additionalDefaults != null) {
                properties.putAll(additionalDefaults);
            }
            ArrayList<String> platformSpecificKeys = new ArrayList<String>();
            for (Object okey : properties.keySet()) {
                String string = (String)okey;
                if (!string.startsWith("[") || !string.startsWith("[win]") && !string.startsWith("[mac]") && !string.startsWith("[linux]") && !string.startsWith("[light]") && !string.startsWith("[dark]")) continue;
                platformSpecificKeys.add(string);
            }
            if (!platformSpecificKeys.isEmpty()) {
                String lightOrDarkPrefix = dark ? "[dark]" : "[light]";
                for (String string : platformSpecificKeys) {
                    if (!string.startsWith(lightOrDarkPrefix)) continue;
                    properties.put(string.substring(lightOrDarkPrefix.length()), properties.remove(string));
                }
                String platformPrefix = SystemInfo.isWindows ? "[win]" : (SystemInfo.isMacOS ? "[mac]" : (SystemInfo.isLinux ? "[linux]" : "[unknown]"));
                for (Object key2 : platformSpecificKeys) {
                    Object value2 = properties.remove(key2);
                    if (!((String)key2).startsWith(platformPrefix)) continue;
                    properties.put(((String)key2).substring(platformPrefix.length()), value2);
                }
            }
            HashMap<String, String> wildcards = new HashMap<String, String>();
            Iterator it = properties.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = it.next();
                String key3 = (String)entry.getKey();
                if (!key3.startsWith(WILDCARD_PREFIX)) continue;
                wildcards.put(key3.substring(WILDCARD_PREFIX.length()), (String)entry.getValue());
                it.remove();
            }
            for (Object key2 : defaults.keySet()) {
                String wildcardKey;
                String wildcardValue;
                int dot;
                if (!(key2 instanceof String) || properties.containsKey(key2) || (dot = ((String)key2).lastIndexOf(46)) < 0 || (wildcardValue = (String)wildcards.get(wildcardKey = ((String)key2).substring(dot + 1))) == null) continue;
                properties.put(key2, wildcardValue);
            }
            Function<String, String> function = key -> properties.getProperty((String)key);
            Function<String, String> resolver = value -> UIDefaultsLoader.resolveValue(value, propertiesGetter);
            for (Map.Entry e : properties.entrySet()) {
                String key4 = (String)e.getKey();
                if (key4.startsWith(VARIABLE_PREFIX)) continue;
                String value3 = UIDefaultsLoader.resolveValue((String)e.getValue(), function);
                try {
                    defaults.put(key4, UIDefaultsLoader.parseValue(key4, value3, null, resolver, addonClassLoaders));
                } catch (RuntimeException ex) {
                    UIDefaultsLoader.logParseError(Level.SEVERE, key4, value3, ex);
                }
            }
        } catch (IOException ex) {
            FlatLaf.LOG.log(Level.SEVERE, "FlatLaf: Failed to load properties files.", ex);
        }
    }

    static void logParseError(Level level, String key, String value, RuntimeException ex) {
        FlatLaf.LOG.log(level, "FlatLaf: Failed to parse: '" + key + '=' + value + '\'', ex);
    }

    static String resolveValue(String value, Function<String, String> propertiesGetter) {
        String newValue;
        String value0 = value = value.trim();
        if (value.startsWith(PROPERTY_PREFIX)) {
            value = value.substring(PROPERTY_PREFIX.length());
        } else if (!value.startsWith(VARIABLE_PREFIX)) {
            return value;
        }
        boolean optional = false;
        if (value.startsWith(OPTIONAL_PREFIX)) {
            value = value.substring(OPTIONAL_PREFIX.length());
            optional = true;
        }
        if ((newValue = propertiesGetter.apply(value)) == null) {
            if (optional) {
                return "null";
            }
            throw new IllegalArgumentException("variable or property '" + value + "' not found");
        }
        if (newValue.equals(value0)) {
            throw new IllegalArgumentException("endless recursion in variable or property '" + value + "'");
        }
        return UIDefaultsLoader.resolveValue(newValue, propertiesGetter);
    }

    static Object parseValue(String key, String value) {
        return UIDefaultsLoader.parseValue(key, value, null, v -> v, Collections.emptyList());
    }

    static Object parseValue(String key, String value, ValueType[] resultValueType, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
        int end;
        if (resultValueType == null) {
            resultValueType = tempResultValueType;
        }
        switch (value = value.trim()) {
            case "null": {
                resultValueType[0] = ValueType.NULL;
                return null;
            }
            case "false": {
                resultValueType[0] = ValueType.BOOLEAN;
                return false;
            }
            case "true": {
                resultValueType[0] = ValueType.BOOLEAN;
                return true;
            }
        }
        if (value.startsWith("lazy(") && value.endsWith(")")) {
            resultValueType[0] = ValueType.LAZY;
            String uiKey = value.substring(5, value.length() - 1).trim();
            return t -> UIDefaultsLoader.lazyUIManagerGet(uiKey);
        }
        ValueType valueType = ValueType.UNKNOWN;
        if (value.startsWith("#")) {
            valueType = ValueType.COLOR;
        } else if (value.startsWith("\"") && value.endsWith("\"")) {
            valueType = ValueType.STRING;
            value = value.substring(1, value.length() - 1);
        } else if (value.startsWith(TYPE_PREFIX) && (end = value.indexOf(TYPE_PREFIX_END)) != -1) {
            try {
                String typeStr = value.substring(TYPE_PREFIX.length(), end);
                valueType = ValueType.valueOf(typeStr.toUpperCase(Locale.ENGLISH));
                value = value.substring(end + TYPE_PREFIX_END.length());
            } catch (IllegalArgumentException typeStr) {
                // empty catch block
            }
        }
        if (valueType == ValueType.UNKNOWN) {
            if (key.endsWith("UI")) {
                valueType = ValueType.STRING;
            } else if (key.endsWith("Color") || key.endsWith("ground") && (key.endsWith(".background") || key.endsWith("Background") || key.endsWith(".foreground") || key.endsWith("Foreground"))) {
                valueType = ValueType.COLOR;
            } else if (key.endsWith(".border") || key.endsWith("Border")) {
                valueType = ValueType.BORDER;
            } else if (key.endsWith(".icon") || key.endsWith("Icon")) {
                valueType = ValueType.ICON;
            } else if (key.endsWith(".margin") || key.endsWith(".padding") || key.endsWith("Margins") || key.endsWith("Insets")) {
                valueType = ValueType.INSETS;
            } else if (key.endsWith("Size")) {
                valueType = ValueType.DIMENSION;
            } else if (key.endsWith("Width") || key.endsWith("Height")) {
                valueType = ValueType.INTEGER;
            } else if (key.endsWith("Char")) {
                valueType = ValueType.CHARACTER;
            } else if (key.endsWith("grayFilter")) {
                valueType = ValueType.GRAYFILTER;
            }
        }
        resultValueType[0] = valueType;
        switch (valueType) {
            case STRING: {
                return value;
            }
            case CHARACTER: {
                return UIDefaultsLoader.parseCharacter(value);
            }
            case INTEGER: {
                return UIDefaultsLoader.parseInteger(value, true);
            }
            case FLOAT: {
                return UIDefaultsLoader.parseFloat(value, true);
            }
            case BORDER: {
                return UIDefaultsLoader.parseBorder(value, resolver, addonClassLoaders);
            }
            case ICON: {
                return UIDefaultsLoader.parseInstance(value, addonClassLoaders);
            }
            case INSETS: {
                return UIDefaultsLoader.parseInsets(value);
            }
            case DIMENSION: {
                return UIDefaultsLoader.parseDimension(value);
            }
            case COLOR: {
                return UIDefaultsLoader.parseColorOrFunction(value, resolver, true);
            }
            case SCALEDINTEGER: {
                return UIDefaultsLoader.parseScaledInteger(value);
            }
            case SCALEDFLOAT: {
                return UIDefaultsLoader.parseScaledFloat(value);
            }
            case SCALEDINSETS: {
                return UIDefaultsLoader.parseScaledInsets(value);
            }
            case SCALEDDIMENSION: {
                return UIDefaultsLoader.parseScaledDimension(value);
            }
            case INSTANCE: {
                return UIDefaultsLoader.parseInstance(value, addonClassLoaders);
            }
            case CLASS: {
                return UIDefaultsLoader.parseClass(value, addonClassLoaders);
            }
            case GRAYFILTER: {
                return UIDefaultsLoader.parseGrayFilter(value);
            }
        }
        Object color = UIDefaultsLoader.parseColorOrFunction(value, resolver, false);
        if (color != null) {
            resultValueType[0] = ValueType.COLOR;
            return color;
        }
        Integer integer = UIDefaultsLoader.parseInteger(value, false);
        if (integer != null) {
            resultValueType[0] = ValueType.INTEGER;
            return integer;
        }
        Float f = UIDefaultsLoader.parseFloat(value, false);
        if (f != null) {
            resultValueType[0] = ValueType.FLOAT;
            return f;
        }
        resultValueType[0] = ValueType.STRING;
        return value;
    }

    private static Object parseBorder(String value, Function<String, String> resolver, List<ClassLoader> addonClassLoaders) {
        if (value.indexOf(44) >= 0) {
            List<String> parts = UIDefaultsLoader.split(value, ',');
            Insets insets = UIDefaultsLoader.parseInsets(value);
            ColorUIResource lineColor = parts.size() >= 5 ? (ColorUIResource)UIDefaultsLoader.parseColorOrFunction(resolver.apply(parts.get(4)), resolver, true) : null;
            float lineThickness = parts.size() >= 6 ? UIDefaultsLoader.parseFloat(parts.get(5), true).floatValue() : 1.0f;
            return t -> lineColor != null ? new FlatLineBorder(insets, lineColor, lineThickness) : new FlatEmptyBorder(insets);
        }
        return UIDefaultsLoader.parseInstance(value, addonClassLoaders);
    }

    private static Object parseInstance(String value, List<ClassLoader> addonClassLoaders) {
        return t -> {
            try {
                return UIDefaultsLoader.findClass(value, addonClassLoaders).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                FlatLaf.LOG.log(Level.SEVERE, "FlatLaf: Failed to instantiate '" + value + "'.", ex);
                return null;
            }
        };
    }

    private static Object parseClass(String value, List<ClassLoader> addonClassLoaders) {
        return t -> {
            try {
                return UIDefaultsLoader.findClass(value, addonClassLoaders);
            } catch (ClassNotFoundException ex) {
                FlatLaf.LOG.log(Level.SEVERE, "FlatLaf: Failed to find class '" + value + "'.", ex);
                return null;
            }
        };
    }

    private static Class<?> findClass(String className, List<ClassLoader> addonClassLoaders) throws ClassNotFoundException {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            for (ClassLoader addonClassLoader : addonClassLoaders) {
                try {
                    return addonClassLoader.loadClass(className);
                } catch (ClassNotFoundException classNotFoundException) {
                }
            }
            throw ex;
        }
    }

    private static Insets parseInsets(String value) {
        List<String> numbers = UIDefaultsLoader.split(value, ',');
        try {
            return new InsetsUIResource(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)), Integer.parseInt(numbers.get(3)));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("invalid insets '" + value + "'");
        }
    }

    private static Dimension parseDimension(String value) {
        List<String> numbers = UIDefaultsLoader.split(value, ',');
        try {
            return new DimensionUIResource(Integer.parseInt(numbers.get(0)), Integer.parseInt(numbers.get(1)));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("invalid size '" + value + "'");
        }
    }

    private static Object parseColorOrFunction(String value, Function<String, String> resolver, boolean reportError) {
        if (value.endsWith(")")) {
            return UIDefaultsLoader.parseColorFunctions(value, resolver, reportError);
        }
        return UIDefaultsLoader.parseColor(value, reportError);
    }

    static ColorUIResource parseColor(String value) {
        return UIDefaultsLoader.parseColor(value, false);
    }

    private static ColorUIResource parseColor(String value, boolean reportError) {
        try {
            int rgba = UIDefaultsLoader.parseColorRGBA(value);
            return (rgba & 0xFF000000) == -16777216 ? new ColorUIResource(rgba) : new ColorUIResource(new Color(rgba, true));
        } catch (IllegalArgumentException ex) {
            if (reportError) {
                throw new IllegalArgumentException("invalid color '" + value + "'");
            }
            return null;
        }
    }

    static int parseColorRGBA(String value) {
        int len = value.length();
        if (len != 4 && len != 5 && len != 7 && len != 9 || value.charAt(0) != '#') {
            throw new IllegalArgumentException();
        }
        int n = 0;
        for (int i = 1; i < len; ++i) {
            int digit;
            char ch = value.charAt(i);
            if (ch >= '0' && ch <= '9') {
                digit = ch - 48;
            } else if (ch >= 'a' && ch <= 'f') {
                digit = ch - 97 + 10;
            } else if (ch >= 'A' && ch <= 'F') {
                digit = ch - 65 + 10;
            } else {
                throw new IllegalArgumentException();
            }
            n = n << 4 | digit;
        }
        if (len <= 5) {
            int n1 = n & 0xF000;
            int n2 = n & 0xF00;
            int n3 = n & 0xF0;
            int n4 = n & 0xF;
            n = n1 << 16 | n1 << 12 | n2 << 12 | n2 << 8 | n3 << 8 | n3 << 4 | n4 << 4 | n4;
        }
        return len == 4 || len == 7 ? 0xFF000000 | n : n >> 8 & 0xFFFFFF | (n & 0xFF) << 24;
    }

    private static Object parseColorFunctions(String value, Function<String, String> resolver, boolean reportError) {
        int paramsStart = value.indexOf(40);
        if (paramsStart < 0) {
            if (reportError) {
                throw new IllegalArgumentException("missing opening parenthesis in function '" + value + "'");
            }
            return null;
        }
        String function = value.substring(0, paramsStart).trim();
        List<String> params = UIDefaultsLoader.splitFunctionParams(value.substring(paramsStart + 1, value.length() - 1), ',');
        if (params.isEmpty()) {
            throw new IllegalArgumentException("missing parameters in function '" + value + "'");
        }
        switch (function) {
            case "rgb": {
                return UIDefaultsLoader.parseColorRgbOrRgba(false, params, resolver, reportError);
            }
            case "rgba": {
                return UIDefaultsLoader.parseColorRgbOrRgba(true, params, resolver, reportError);
            }
            case "hsl": {
                return UIDefaultsLoader.parseColorHslOrHsla(false, params);
            }
            case "hsla": {
                return UIDefaultsLoader.parseColorHslOrHsla(true, params);
            }
            case "lighten": {
                return UIDefaultsLoader.parseColorHSLIncreaseDecrease(2, true, params, resolver, reportError);
            }
            case "darken": {
                return UIDefaultsLoader.parseColorHSLIncreaseDecrease(2, false, params, resolver, reportError);
            }
            case "saturate": {
                return UIDefaultsLoader.parseColorHSLIncreaseDecrease(1, true, params, resolver, reportError);
            }
            case "desaturate": {
                return UIDefaultsLoader.parseColorHSLIncreaseDecrease(1, false, params, resolver, reportError);
            }
            case "fadein": {
                return UIDefaultsLoader.parseColorHSLIncreaseDecrease(3, true, params, resolver, reportError);
            }
            case "fadeout": {
                return UIDefaultsLoader.parseColorHSLIncreaseDecrease(3, false, params, resolver, reportError);
            }
            case "fade": {
                return UIDefaultsLoader.parseColorFade(params, resolver, reportError);
            }
            case "spin": {
                return UIDefaultsLoader.parseColorSpin(params, resolver, reportError);
            }
        }
        throw new IllegalArgumentException("unknown color function '" + value + "'");
    }

    private static ColorUIResource parseColorRgbOrRgba(boolean hasAlpha, List<String> params, Function<String, String> resolver, boolean reportError) {
        if (hasAlpha && params.size() == 2) {
            String colorStr = params.get(0);
            int alpha = UIDefaultsLoader.parseInteger(params.get(1), 0, 255, true);
            ColorUIResource color = (ColorUIResource)UIDefaultsLoader.parseColorOrFunction(resolver.apply(colorStr), resolver, reportError);
            return new ColorUIResource(new Color((alpha & 0xFF) << 24 | color.getRGB() & 0xFFFFFF, true));
        }
        int red = UIDefaultsLoader.parseInteger(params.get(0), 0, 255, true);
        int green = UIDefaultsLoader.parseInteger(params.get(1), 0, 255, true);
        int blue = UIDefaultsLoader.parseInteger(params.get(2), 0, 255, true);
        int alpha = hasAlpha ? UIDefaultsLoader.parseInteger(params.get(3), 0, 255, true) : 255;
        return hasAlpha ? new ColorUIResource(new Color(red, green, blue, alpha)) : new ColorUIResource(red, green, blue);
    }

    private static ColorUIResource parseColorHslOrHsla(boolean hasAlpha, List<String> params) {
        int hue = UIDefaultsLoader.parseInteger(params.get(0), 0, 360, false);
        int saturation = UIDefaultsLoader.parsePercentage(params.get(1));
        int lightness = UIDefaultsLoader.parsePercentage(params.get(2));
        int alpha = hasAlpha ? UIDefaultsLoader.parsePercentage(params.get(3)) : 100;
        float[] hsl = new float[]{hue, saturation, lightness};
        return new ColorUIResource(HSLColor.toRGB(hsl, (float)alpha / 100.0f));
    }

    private static Object parseColorHSLIncreaseDecrease(int hslIndex, boolean increase, List<String> params, Function<String, String> resolver, boolean reportError) {
        String colorStr = params.get(0);
        int amount = UIDefaultsLoader.parsePercentage(params.get(1));
        boolean relative = false;
        boolean autoInverse = false;
        boolean lazy = false;
        boolean derived = false;
        if (params.size() > 2) {
            String options = params.get(2);
            relative = options.contains("relative");
            autoInverse = options.contains("autoInverse");
            lazy = options.contains("lazy");
            derived = options.contains("derived");
            if (derived && !options.contains("noAutoInverse")) {
                autoInverse = true;
            }
        }
        ColorFunctions.HSLIncreaseDecrease function = new ColorFunctions.HSLIncreaseDecrease(hslIndex, increase, amount, relative, autoInverse);
        if (lazy) {
            return t -> {
                Object color = UIDefaultsLoader.lazyUIManagerGet(colorStr);
                return color instanceof Color ? new ColorUIResource(ColorFunctions.applyFunctions((Color)color, function)) : null;
            };
        }
        return UIDefaultsLoader.parseFunctionBaseColor(colorStr, function, derived, resolver, reportError);
    }

    private static Object parseColorFade(List<String> params, Function<String, String> resolver, boolean reportError) {
        String colorStr = params.get(0);
        int amount = UIDefaultsLoader.parsePercentage(params.get(1));
        boolean derived = false;
        if (params.size() > 2) {
            String options = params.get(2);
            derived = options.contains("derived");
        }
        ColorFunctions.Fade function = new ColorFunctions.Fade(amount);
        return UIDefaultsLoader.parseFunctionBaseColor(colorStr, function, derived, resolver, reportError);
    }

    private static Object parseColorSpin(List<String> params, Function<String, String> resolver, boolean reportError) {
        String colorStr = params.get(0);
        int amount = UIDefaultsLoader.parseInteger(params.get(1), true);
        boolean derived = false;
        if (params.size() > 2) {
            String options = params.get(2);
            derived = options.contains("derived");
        }
        ColorFunctions.HSLIncreaseDecrease function = new ColorFunctions.HSLIncreaseDecrease(0, true, amount, false, false);
        return UIDefaultsLoader.parseFunctionBaseColor(colorStr, function, derived, resolver, reportError);
    }

    private static Object parseFunctionBaseColor(String colorStr, ColorFunctions.ColorFunction function, boolean derived, Function<String, String> resolver, boolean reportError) {
        String resolvedColorStr = resolver.apply(colorStr);
        ColorUIResource baseColor = (ColorUIResource)UIDefaultsLoader.parseColorOrFunction(resolvedColorStr, resolver, reportError);
        if (baseColor == null) {
            return null;
        }
        Color newColor = ColorFunctions.applyFunctions(baseColor, function);
        if (derived) {
            ColorFunctions.ColorFunction[] functions2;
            if (baseColor instanceof DerivedColor && resolvedColorStr == colorStr) {
                ColorFunctions.ColorFunction[] baseFunctions = ((DerivedColor)baseColor).getFunctions();
                functions2 = new ColorFunctions.ColorFunction[baseFunctions.length + 1];
                System.arraycopy(baseFunctions, 0, functions2, 0, baseFunctions.length);
                functions2[baseFunctions.length] = function;
            } else {
                functions2 = new ColorFunctions.ColorFunction[]{function};
            }
            return new DerivedColor(newColor, functions2);
        }
        return new ColorUIResource(newColor);
    }

    private static int parsePercentage(String value) {
        int val;
        if (!value.endsWith("%")) {
            throw new NumberFormatException("invalid percentage '" + value + "'");
        }
        try {
            val = Integer.parseInt(value.substring(0, value.length() - 1));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("invalid percentage '" + value + "'");
        }
        if (val < 0 || val > 100) {
            throw new IllegalArgumentException("percentage out of range (0-100%) '" + value + "'");
        }
        return val;
    }

    private static Character parseCharacter(String value) {
        if (value.length() != 1) {
            throw new IllegalArgumentException("invalid character '" + value + "'");
        }
        return Character.valueOf(value.charAt(0));
    }

    private static Integer parseInteger(String value, int min, int max, boolean allowPercentage) {
        if (allowPercentage && value.endsWith("%")) {
            int percent = UIDefaultsLoader.parsePercentage(value);
            return max * percent / 100;
        }
        Integer integer = UIDefaultsLoader.parseInteger(value, true);
        if (integer < min || integer > max) {
            throw new NumberFormatException("integer '" + value + "' out of range (" + min + '-' + max + ')');
        }
        return integer;
    }

    private static Integer parseInteger(String value, boolean reportError) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            if (reportError) {
                throw new NumberFormatException("invalid integer '" + value + "'");
            }
            return null;
        }
    }

    private static Float parseFloat(String value, boolean reportError) {
        try {
            return Float.valueOf(Float.parseFloat(value));
        } catch (NumberFormatException ex) {
            if (reportError) {
                throw new NumberFormatException("invalid float '" + value + "'");
            }
            return null;
        }
    }

    private static UIDefaults.ActiveValue parseScaledInteger(String value) {
        int val = UIDefaultsLoader.parseInteger(value, true);
        return t -> UIScale.scale(val);
    }

    private static UIDefaults.ActiveValue parseScaledFloat(String value) {
        float val = UIDefaultsLoader.parseFloat(value, true).floatValue();
        return t -> Float.valueOf(UIScale.scale(val));
    }

    private static UIDefaults.ActiveValue parseScaledInsets(String value) {
        Insets insets = UIDefaultsLoader.parseInsets(value);
        return t -> UIScale.scale(insets);
    }

    private static UIDefaults.ActiveValue parseScaledDimension(String value) {
        Dimension dimension = UIDefaultsLoader.parseDimension(value);
        return t -> UIScale.scale(dimension);
    }

    private static Object parseGrayFilter(String value) {
        List<String> numbers = UIDefaultsLoader.split(value, ',');
        try {
            int brightness = Integer.parseInt(numbers.get(0));
            int contrast = Integer.parseInt(numbers.get(1));
            int alpha = Integer.parseInt(numbers.get(2));
            return t -> new GrayFilter(brightness, contrast, alpha);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("invalid gray filter '" + value + "'");
        }
    }

    private static List<String> split(String str, char delim) {
        List<String> result = StringUtils.split(str, delim);
        int size = result.size();
        for (int i = 0; i < size; ++i) {
            result.set(i, result.get(i).trim());
        }
        return result;
    }

    private static List<String> splitFunctionParams(String str, char delim) {
        ArrayList<String> strs = new ArrayList<String>();
        int nestLevel = 0;
        int start = 0;
        int strlen = str.length();
        for (int i = 0; i < strlen; ++i) {
            char ch = str.charAt(i);
            if (ch == '(') {
                ++nestLevel;
                continue;
            }
            if (ch == ')') {
                --nestLevel;
                continue;
            }
            if (nestLevel != 0 || ch != delim) continue;
            strs.add(str.substring(start, i).trim());
            start = i + 1;
        }
        strs.add(str.substring(start).trim());
        return strs;
    }

    private static Object lazyUIManagerGet(String uiKey) {
        Object value;
        boolean optional = false;
        if (uiKey.startsWith(OPTIONAL_PREFIX)) {
            uiKey = uiKey.substring(OPTIONAL_PREFIX.length());
            optional = true;
        }
        if ((value = UIManager.get(uiKey)) == null && !optional) {
            FlatLaf.LOG.log(Level.SEVERE, "FlatLaf: '" + uiKey + "' not found in UI defaults.");
        }
        return value;
    }

    static enum ValueType {
        UNKNOWN,
        STRING,
        BOOLEAN,
        CHARACTER,
        INTEGER,
        FLOAT,
        BORDER,
        ICON,
        INSETS,
        DIMENSION,
        COLOR,
        SCALEDINTEGER,
        SCALEDFLOAT,
        SCALEDINSETS,
        SCALEDDIMENSION,
        INSTANCE,
        CLASS,
        GRAYFILTER,
        NULL,
        LAZY;

    }
}

