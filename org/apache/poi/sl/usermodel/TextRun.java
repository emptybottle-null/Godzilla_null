/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Color;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.sl.usermodel.Hyperlink;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.util.Internal;

public interface TextRun {
    public String getRawText();

    public void setText(String var1);

    public TextCap getTextCap();

    public PaintStyle getFontColor();

    public void setFontColor(Color var1);

    public void setFontColor(PaintStyle var1);

    public Double getFontSize();

    public void setFontSize(Double var1);

    public String getFontFamily();

    public String getFontFamily(FontGroup var1);

    public void setFontFamily(String var1);

    public void setFontFamily(String var1, FontGroup var2);

    public FontInfo getFontInfo(FontGroup var1);

    public void setFontInfo(FontInfo var1, FontGroup var2);

    public boolean isBold();

    public void setBold(boolean var1);

    public boolean isItalic();

    public void setItalic(boolean var1);

    public boolean isUnderlined();

    public void setUnderlined(boolean var1);

    public boolean isStrikethrough();

    public void setStrikethrough(boolean var1);

    public boolean isSubscript();

    public boolean isSuperscript();

    public byte getPitchAndFamily();

    public Hyperlink<?, ?> getHyperlink();

    public Hyperlink<?, ?> createHyperlink();

    @Internal
    public FieldType getFieldType();

    public TextParagraph<?, ?, ?> getParagraph();

    public static enum FieldType {
        SLIDE_NUMBER,
        DATE_TIME;

    }

    public static enum TextCap {
        NONE,
        SMALL,
        ALL;

    }
}

