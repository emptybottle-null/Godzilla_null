/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import java.awt.Color;
import java.lang.reflect.Field;
import javax.swing.UIManager;

public class ColorDescriptor {
    private Color myColor;
    private String mySwingColor;
    private String mySystemColor;
    private String myAWTColor;
    static /* synthetic */ Class class$java$awt$SystemColor;
    static /* synthetic */ Class class$java$awt$Color;

    public static ColorDescriptor fromSwingColor(String swingColor) {
        ColorDescriptor result = new ColorDescriptor(null);
        result.myColor = null;
        result.mySwingColor = swingColor;
        return result;
    }

    public static ColorDescriptor fromSystemColor(String systemColor) {
        ColorDescriptor result = new ColorDescriptor(null);
        result.myColor = null;
        result.mySystemColor = systemColor;
        return result;
    }

    public static ColorDescriptor fromAWTColor(String awtColor) {
        ColorDescriptor result = new ColorDescriptor(null);
        result.myColor = null;
        result.myAWTColor = awtColor;
        return result;
    }

    private static Color getColorField(Class aClass, String fieldName) {
        try {
            Field field = aClass.getDeclaredField(fieldName);
            return (Color)field.get(null);
        } catch (NoSuchFieldException e) {
            return Color.black;
        } catch (IllegalAccessException e) {
            return Color.black;
        }
    }

    public ColorDescriptor(Color color) {
        this.myColor = color;
    }

    public Color getResolvedColor() {
        if (this.myColor != null) {
            return this.myColor;
        }
        if (this.mySwingColor != null) {
            return UIManager.getColor(this.mySwingColor);
        }
        if (this.mySystemColor != null) {
            return ColorDescriptor.getColorField(class$java$awt$SystemColor == null ? (class$java$awt$SystemColor = ColorDescriptor.class$("java.awt.SystemColor")) : class$java$awt$SystemColor, this.mySystemColor);
        }
        if (this.myAWTColor != null) {
            return ColorDescriptor.getColorField(class$java$awt$Color == null ? (class$java$awt$Color = ColorDescriptor.class$("java.awt.Color")) : class$java$awt$Color, this.myAWTColor);
        }
        return null;
    }

    public Color getColor() {
        return this.myColor;
    }

    public String getSwingColor() {
        return this.mySwingColor;
    }

    public String getSystemColor() {
        return this.mySystemColor;
    }

    public String getAWTColor() {
        return this.myAWTColor;
    }

    public String toString() {
        if (this.mySwingColor != null) {
            return this.mySwingColor;
        }
        if (this.mySystemColor != null) {
            return this.mySystemColor;
        }
        if (this.myAWTColor != null) {
            return this.myAWTColor;
        }
        if (this.myColor != null) {
            return "[" + this.myColor.getRed() + "," + this.myColor.getGreen() + "," + this.myColor.getBlue() + "]";
        }
        return "null";
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ColorDescriptor)) {
            return false;
        }
        ColorDescriptor rhs = (ColorDescriptor)obj;
        if (this.myColor != null) {
            return this.myColor.equals(rhs.myColor);
        }
        if (this.mySwingColor != null) {
            return this.mySwingColor.equals(rhs.mySwingColor);
        }
        if (this.mySystemColor != null) {
            return this.mySystemColor.equals(rhs.mySystemColor);
        }
        if (this.myAWTColor != null) {
            return this.myAWTColor.equals(rhs.myAWTColor);
        }
        return false;
    }

    public boolean isColorSet() {
        return this.myColor != null || this.mySwingColor != null || this.mySystemColor != null || this.myAWTColor != null;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

