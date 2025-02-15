/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.util.Removal;

public interface Font {
    public static final short COLOR_NORMAL = Short.MAX_VALUE;
    public static final short COLOR_RED = 10;
    public static final short SS_NONE = 0;
    public static final short SS_SUPER = 1;
    public static final short SS_SUB = 2;
    public static final byte U_NONE = 0;
    public static final byte U_SINGLE = 1;
    public static final byte U_DOUBLE = 2;
    public static final byte U_SINGLE_ACCOUNTING = 33;
    public static final byte U_DOUBLE_ACCOUNTING = 34;
    public static final byte ANSI_CHARSET = 0;
    public static final byte DEFAULT_CHARSET = 1;
    public static final byte SYMBOL_CHARSET = 2;

    public void setFontName(String var1);

    public String getFontName();

    public void setFontHeight(short var1);

    public void setFontHeightInPoints(short var1);

    public short getFontHeight();

    public short getFontHeightInPoints();

    public void setItalic(boolean var1);

    public boolean getItalic();

    public void setStrikeout(boolean var1);

    public boolean getStrikeout();

    public void setColor(short var1);

    public short getColor();

    public void setTypeOffset(short var1);

    public short getTypeOffset();

    public void setUnderline(byte var1);

    public byte getUnderline();

    public int getCharSet();

    public void setCharSet(byte var1);

    public void setCharSet(int var1);

    @Removal(version="4.2")
    public short getIndex();

    public int getIndexAsInt();

    public void setBold(boolean var1);

    public boolean getBold();
}

