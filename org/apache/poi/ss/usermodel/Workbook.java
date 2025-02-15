/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetVisibility;
import org.apache.poi.util.Removal;

public interface Workbook
extends Closeable,
Iterable<Sheet> {
    public static final int PICTURE_TYPE_EMF = 2;
    public static final int PICTURE_TYPE_WMF = 3;
    public static final int PICTURE_TYPE_PICT = 4;
    public static final int PICTURE_TYPE_JPEG = 5;
    public static final int PICTURE_TYPE_PNG = 6;
    public static final int PICTURE_TYPE_DIB = 7;

    public int getActiveSheetIndex();

    public void setActiveSheet(int var1);

    public int getFirstVisibleTab();

    public void setFirstVisibleTab(int var1);

    public void setSheetOrder(String var1, int var2);

    public void setSelectedTab(int var1);

    public void setSheetName(int var1, String var2);

    public String getSheetName(int var1);

    public int getSheetIndex(String var1);

    public int getSheetIndex(Sheet var1);

    public Sheet createSheet();

    public Sheet createSheet(String var1);

    public Sheet cloneSheet(int var1);

    public Iterator<Sheet> sheetIterator();

    public int getNumberOfSheets();

    public Sheet getSheetAt(int var1);

    public Sheet getSheet(String var1);

    public void removeSheetAt(int var1);

    public Font createFont();

    public Font findFont(boolean var1, short var2, short var3, String var4, boolean var5, boolean var6, short var7, byte var8);

    @Removal(version="4.2")
    public short getNumberOfFonts();

    public int getNumberOfFontsAsInt();

    @Removal(version="4.2")
    public Font getFontAt(short var1);

    public Font getFontAt(int var1);

    public CellStyle createCellStyle();

    public int getNumCellStyles();

    public CellStyle getCellStyleAt(int var1);

    public void write(OutputStream var1) throws IOException;

    @Override
    public void close() throws IOException;

    public int getNumberOfNames();

    public Name getName(String var1);

    public List<? extends Name> getNames(String var1);

    public List<? extends Name> getAllNames();

    @Deprecated
    @Removal(version="5.0.0")
    public Name getNameAt(int var1);

    public Name createName();

    @Deprecated
    @Removal(version="3.20")
    public int getNameIndex(String var1);

    @Deprecated
    @Removal(version="3.20")
    public void removeName(int var1);

    @Deprecated
    @Removal(version="3.20")
    public void removeName(String var1);

    public void removeName(Name var1);

    public int linkExternalWorkbook(String var1, Workbook var2);

    public void setPrintArea(int var1, String var2);

    public void setPrintArea(int var1, int var2, int var3, int var4, int var5);

    public String getPrintArea(int var1);

    public void removePrintArea(int var1);

    public Row.MissingCellPolicy getMissingCellPolicy();

    public void setMissingCellPolicy(Row.MissingCellPolicy var1);

    public DataFormat createDataFormat();

    public int addPicture(byte[] var1, int var2);

    public List<? extends PictureData> getAllPictures();

    public CreationHelper getCreationHelper();

    public boolean isHidden();

    public void setHidden(boolean var1);

    public boolean isSheetHidden(int var1);

    public boolean isSheetVeryHidden(int var1);

    public void setSheetHidden(int var1, boolean var2);

    public SheetVisibility getSheetVisibility(int var1);

    public void setSheetVisibility(int var1, SheetVisibility var2);

    public void addToolPack(UDFFinder var1);

    public void setForceFormulaRecalculation(boolean var1);

    public boolean getForceFormulaRecalculation();

    public SpreadsheetVersion getSpreadsheetVersion();

    public int addOlePackage(byte[] var1, String var2, String var3, String var4) throws IOException;
}

