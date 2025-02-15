/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.Color;

public interface FontFormatting {
    public static final short SS_NONE = 0;
    public static final short SS_SUPER = 1;
    public static final short SS_SUB = 2;
    public static final byte U_NONE = 0;
    public static final byte U_SINGLE = 1;
    public static final byte U_DOUBLE = 2;
    public static final byte U_SINGLE_ACCOUNTING = 33;
    public static final byte U_DOUBLE_ACCOUNTING = 34;

    public short getEscapementType();

    public void setEscapementType(short var1);

    public short getFontColorIndex();

    public void setFontColorIndex(short var1);

    public Color getFontColor();

    public void setFontColor(Color var1);

    public int getFontHeight();

    public void setFontHeight(int var1);

    public short getUnderlineType();

    public void setUnderlineType(short var1);

    public boolean isBold();

    public boolean isItalic();

    public boolean isStruckout();

    public void setFontStyle(boolean var1, boolean var2);

    public void resetFontStyle();
}

