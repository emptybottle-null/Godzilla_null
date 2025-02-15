/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.jdom.Attribute
 *  org.jdom.Element
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.lw.ColorDescriptor;
import com.intellij.uiDesigner.lw.FontDescriptor;
import com.intellij.uiDesigner.lw.StringDescriptor;
import java.awt.Color;
import java.awt.Insets;
import java.lang.reflect.Method;
import org.jdom.Attribute;
import org.jdom.Element;

public final class LwXmlReader {
    static /* synthetic */ Class class$java$lang$String;

    private LwXmlReader() {
    }

    public static Element getChild(Element element, String childName) {
        return element.getChild(childName, element.getNamespace());
    }

    public static Element getRequiredChild(Element element, String childName) {
        Element child = LwXmlReader.getChild(element, childName);
        if (child == null) {
            throw new IllegalArgumentException("subtag '" + childName + "' is required: " + element);
        }
        return child;
    }

    public static String getString(Element element, String attributeName) {
        String value = element.getAttributeValue(attributeName);
        return value != null ? value.trim() : null;
    }

    public static String getRequiredString(Element element, String attributeName) {
        String value = LwXmlReader.getString(element, attributeName);
        if (value != null) {
            return value;
        }
        throw new IllegalArgumentException("attribute '" + attributeName + "' is required: " + element);
    }

    public static String getOptionalString(Element element, String attributeName, String defaultValue) {
        String value = element.getAttributeValue(attributeName);
        return value != null ? value.trim() : defaultValue;
    }

    public static int getRequiredInt(Element element, String attributeName) {
        String str = LwXmlReader.getRequiredString(element, attributeName);
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("attribute '" + attributeName + "' is not a proper integer: " + str);
        }
    }

    public static int getOptionalInt(Element element, String attributeName, int defaultValue) {
        String str = element.getAttributeValue(attributeName);
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("attribute '" + attributeName + "' is not a proper integer: " + str);
        }
    }

    public static boolean getOptionalBoolean(Element element, String attributeName, boolean defaultValue) {
        String str = element.getAttributeValue(attributeName);
        if (str == null) {
            return defaultValue;
        }
        return Boolean.valueOf(str);
    }

    public static double getRequiredDouble(Element element, String attributeName) {
        String str = LwXmlReader.getRequiredString(element, attributeName);
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("attribute '" + attributeName + "' is not a proper double: " + str);
        }
    }

    public static double getOptionalDouble(Element element, String attributeName, double defaultValue) {
        String str = element.getAttributeValue(attributeName);
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("attribute '" + attributeName + "' is not a proper double: " + str);
        }
    }

    public static float getRequiredFloat(Element element, String attributeName) {
        String str = LwXmlReader.getRequiredString(element, attributeName);
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("attribute '" + attributeName + "' is not a proper float: " + str);
        }
    }

    public static Object getRequiredPrimitiveTypeValue(Element element, String attributeName, Class valueClass) {
        String str = LwXmlReader.getRequiredString(element, attributeName);
        try {
            Method method = valueClass.getMethod("valueOf", class$java$lang$String == null ? (class$java$lang$String = LwXmlReader.class$("java.lang.String")) : class$java$lang$String);
            return method.invoke(null, str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("attribute '" + attributeName + "' is not a proper float: " + str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static StringDescriptor getStringDescriptor(Element element, String valueAttr, String bundleAttr, String keyAttr) {
        String title = element.getAttributeValue(valueAttr);
        if (title != null) {
            StringDescriptor descriptor = StringDescriptor.create(title);
            descriptor.setNoI18n(LwXmlReader.getOptionalBoolean(element, "noi18n", false));
            return descriptor;
        }
        String bundle = element.getAttributeValue(bundleAttr);
        if (bundle != null) {
            String key = LwXmlReader.getRequiredString(element, keyAttr);
            return new StringDescriptor(bundle, key);
        }
        return null;
    }

    public static FontDescriptor getFontDescriptor(Element element) {
        String swingFont = element.getAttributeValue("swing-font");
        if (swingFont != null) {
            return FontDescriptor.fromSwingFont(swingFont);
        }
        String fontName = element.getAttributeValue("name");
        int fontStyle = LwXmlReader.getOptionalInt(element, "style", -1);
        int fontSize = LwXmlReader.getOptionalInt(element, "size", -1);
        return new FontDescriptor(fontName, fontStyle, fontSize);
    }

    public static ColorDescriptor getColorDescriptor(Element element) throws Exception {
        Attribute attr = element.getAttribute("color");
        if (attr != null) {
            return new ColorDescriptor(new Color(attr.getIntValue()));
        }
        String swingColor = element.getAttributeValue("swing-color");
        if (swingColor != null) {
            return ColorDescriptor.fromSwingColor(swingColor);
        }
        String systemColor = element.getAttributeValue("system-color");
        if (systemColor != null) {
            return ColorDescriptor.fromSystemColor(systemColor);
        }
        String awtColor = element.getAttributeValue("awt-color");
        if (awtColor != null) {
            return ColorDescriptor.fromAWTColor(awtColor);
        }
        return new ColorDescriptor(null);
    }

    public static ColorDescriptor getOptionalColorDescriptor(Element element) {
        if (element == null) {
            return null;
        }
        try {
            return LwXmlReader.getColorDescriptor(element);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Insets readInsets(Element element) {
        int top = LwXmlReader.getRequiredInt(element, "top");
        int left = LwXmlReader.getRequiredInt(element, "left");
        int bottom = LwXmlReader.getRequiredInt(element, "bottom");
        int right = LwXmlReader.getRequiredInt(element, "right");
        return new Insets(top, left, bottom, right);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

