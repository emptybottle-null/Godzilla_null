/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import java.awt.Font;
import javax.swing.UIManager;

public class FontDescriptor {
    private String myFontName;
    private int myFontSize;
    private int myFontStyle;
    private String mySwingFont;

    private FontDescriptor() {
    }

    public FontDescriptor(String fontName, int fontStyle, int fontSize) {
        this.myFontName = fontName;
        this.myFontSize = fontSize;
        this.myFontStyle = fontStyle;
    }

    public boolean isFixedFont() {
        return this.mySwingFont == null;
    }

    public boolean isFullyDefinedFont() {
        return this.myFontName != null && this.myFontSize >= 0 && this.myFontStyle >= 0;
    }

    public static FontDescriptor fromSwingFont(String swingFont) {
        FontDescriptor result = new FontDescriptor();
        result.mySwingFont = swingFont;
        return result;
    }

    public String getFontName() {
        return this.myFontName;
    }

    public int getFontSize() {
        return this.myFontSize;
    }

    public int getFontStyle() {
        return this.myFontStyle;
    }

    public Font getFont(Font defaultFont) {
        return new Font(this.myFontName != null ? this.myFontName : defaultFont.getFontName(), this.myFontStyle >= 0 ? this.myFontStyle : defaultFont.getStyle(), this.myFontSize >= 0 ? this.myFontSize : defaultFont.getSize());
    }

    public String getSwingFont() {
        return this.mySwingFont;
    }

    public Font getResolvedFont(Font defaultFont) {
        if (this.mySwingFont != null) {
            return UIManager.getFont(this.mySwingFont);
        }
        return this.getFont(defaultFont);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FontDescriptor)) {
            return false;
        }
        FontDescriptor rhs = (FontDescriptor)obj;
        if (this.mySwingFont != null) {
            return this.mySwingFont.equals(rhs.mySwingFont);
        }
        if (this.myFontName == null && rhs.myFontName != null) {
            return false;
        }
        if (this.myFontName != null && rhs.myFontName == null) {
            return false;
        }
        if (this.myFontName != null && !this.myFontName.equals(rhs.myFontName)) {
            return false;
        }
        return this.myFontSize == rhs.myFontSize && this.myFontStyle == rhs.myFontStyle;
    }
}

