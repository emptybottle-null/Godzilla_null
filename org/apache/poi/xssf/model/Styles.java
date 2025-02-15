/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;

public interface Styles {
    public String getNumberFormatAt(short var1);

    public int putNumberFormat(String var1);

    public void putNumberFormat(short var1, String var2);

    public boolean removeNumberFormat(short var1);

    public boolean removeNumberFormat(String var1);

    public XSSFFont getFontAt(int var1);

    public int putFont(XSSFFont var1, boolean var2);

    public int putFont(XSSFFont var1);

    public XSSFCellStyle getStyleAt(int var1);

    public int putStyle(XSSFCellStyle var1);

    public XSSFCellBorder getBorderAt(int var1);

    public int putBorder(XSSFCellBorder var1);

    public XSSFCellFill getFillAt(int var1);

    public int putFill(XSSFCellFill var1);

    public int getNumCellStyles();

    public int getNumDataFormats();
}

